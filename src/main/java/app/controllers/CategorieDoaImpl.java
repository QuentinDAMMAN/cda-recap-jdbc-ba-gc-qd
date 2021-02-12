package app.controllers;

import app.dao.CategorieDao;
import app.model.Categorie;
import app.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDoaImpl implements CategorieDao {
    final Logger logger = LoggerFactory.getLogger(CategorieDoaImpl.class);
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
            logger.info("donnée  entré " + categorie.getLibelle() + " log id :" + System.currentTimeMillis());
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            System.out.println(categorie.getLibelle() + " ajouté au categorie avec succès");
            logger.info("categoery ajouté, log id" + System.currentTimeMillis());

        } catch (SQLException se) {
            se.getMessage();
            logger.error(se.getMessage());
        }
        return new Categorie(id, categorie.getLibelle());
    }

    @Override
    public boolean deleteCategorie(int id) {
        final Logger logger = LoggerFactory.getLogger(CategorieDoaImpl.class);

        PreparedStatement ps;
        boolean res = false;
        try {
            ps = SQLConnection.con.prepareStatement("delete from  categorie where Id_Categorie = ?");
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i == 0) {
                System.out.println("il n'y a pas de categorie avec l'id " + id);
                logger.info("categorie non trouvé, log id : " + System.currentTimeMillis());
            } else {
                System.out.println("le categorie avec l'id " + id + " est supprimé avec succès");
                logger.info("categorie supprimé, log id : " + System.currentTimeMillis());
            }
            res = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getSQLState();
            logger.error(throwables.getMessage() + "log id " + System.currentTimeMillis());
        }
        return res;
    }

    @Override
    public boolean updateCategorie(Categorie categorie) {
        final Logger logger = LoggerFactory.getLogger(CategorieDoaImpl.class);
        boolean res = false;
        PreparedStatement ps;
        try {
            ps = SQLConnection.con.prepareStatement("update categorie set Libelle  = ? where Id_Categorie  = ?");
            ps.setString(1, categorie.getLibelle());
            ps.setInt(2, categorie.getId_categorie());
            int i = ps.executeUpdate();
            if (i == 0) {
                System.out.println("MAJ échec");
                logger.warn("MAJ écho, log id" + System.currentTimeMillis());
            } else {
                System.out.println("MAJ avec succès");
                logger.info("MaJ avec succes, log id " + System.currentTimeMillis());
            }
            res = true;
        } catch (SQLException throwables) {
            throwables.getMessage();
        }
        return res;
    }

    @Override
    public Categorie findCategorieById(int id) {
        return null;
    }

    @Override
    public List<Categorie> listCategories() {
        List<Categorie> res = new ArrayList<>();
        PreparedStatement ps;
        try {
            ps = SQLConnection.con.prepareStatement("select * from categorie ");
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                res.add(new Categorie(
                        r.getInt("Id_Categorie"),
                        r.getString("Libelle")
                ));
            }
            logger.info("retourner un list des categories, log id  " + System.currentTimeMillis());
        } catch (SQLException throwables) {
            throwables.getMessage();
            logger.error(throwables.getMessage() + "log id " + System.currentTimeMillis());
        }
        return res;
    }
}
