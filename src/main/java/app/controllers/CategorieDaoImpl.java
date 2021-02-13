
package app.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.dao.CategorieDao;
import app.model.Categorie;
import app.sql.SQLConnection;

public class CategorieDaoImpl implements CategorieDao {
	final Logger logger = LoggerFactory.getLogger(CategorieDaoImpl.class);
    ResultSet res;
    String request;

    @Override
    public Categorie createCategorie(Categorie categorie) {
        PreparedStatement ps;

        int id = 0;

        try {

             ps = SQLConnection.con.prepareStatement(
                    "Insert into categorie(Libelle) values (?)", PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, categorie.getLibelle());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()) {
               id = generatedKeys.getInt(1);
            }
        } catch (SQLException se) {
        	logger.error("erreur sql",se);
        }
        return new Categorie(id, categorie.getLibelle());
    }

    @Override
    public boolean deleteCategorie(int id) {
        PreparedStatement ps;
        boolean res = false;
        try {
            ps = SQLConnection.con.prepareStatement("delete from  categorie where Id_Categorie = ?");
            ps.setInt(1,id);
            int i = ps.executeUpdate();
            res = true;
        } catch (SQLException e) {
        	logger.error("erreur sql",e);
        }
        return res;
    }

    @Override
    public boolean updateCategorie(String champ, String value, int id) {
        boolean res = false;
        PreparedStatement ps;
        try {
            ps = SQLConnection.con.prepareStatement("update categorie set "+champ+" = ? where Id_Categorie  = ?");
            ps.setString(1,value);
            ps.setInt(2,id);
            int i = ps.executeUpdate();
            res = true;
        } catch (SQLException e) {
        	logger.error("erreur sql",e);
        }
        return res;
    }

    @Override
    public Categorie findCategorieById(int id) {
    	String request = "select * from Categorie where id_categorie = ?";
		ResultSet results = null;
		Categorie categoerie = null;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeQuery();
			results.next();
			categoerie = new Categorie(results.getInt(1),results.getString(2));
		} catch (SQLException e) {
			logger.error("erreur sql",e);
		}
		if (results != null) {
			return categoerie;
		}
        return null;
    }

    @Override
    public List<Categorie> listCategories() {
            List<Categorie> res = new ArrayList<>();
        PreparedStatement ps;
        try {
            ps = SQLConnection.con.prepareStatement("select * from categorie order by id_categorie");
            ResultSet r = ps.executeQuery();
            while(r.next()) {
                res.add(new Categorie(
                        r.getInt("Id_Categorie"),
                        r.getString("Libelle")
                ));
            }
        } catch (SQLException e) {
        	logger.error("erreur sql",e);
        }

        return res;
    }
}
