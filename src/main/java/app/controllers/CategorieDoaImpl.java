package app.controllers;

import app.dao.CategorieDao;
import app.model.Categorie;
import app.sql.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDoaImpl implements CategorieDao {
    ResultSet res;
   
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
            System.out.println(categorie.getLibelle() + " ajouté au categorie avec succès");
        } catch (SQLException se) {
            se.printStackTrace();


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
            if(i == 0) {
                System.out.println("il n'y a pas de categorie avec l'id " + id);
            } else {
            System.out.println("le categorie avec l'id " + id + " est supprimé avec succès");
            }
            res = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getSQLState();
        }
        return res;
    }

    @Override
    public boolean updateCategorie(Categorie categorie) {
        boolean res = false;
        PreparedStatement ps;
        try {
            ps = SQLConnection.con.prepareStatement("update categorie set Libelle  = ? where Id_Categorie  = ?");
            ps.setString(1,categorie.getLibelle());
            ps.setInt(2,categorie.getId_categorie());
            int i = ps.executeUpdate();
            if(i == 0) {
                System.out.println("MAJ échec");
            } else {
                System.out.println("MAJ avec succès");
            }
            res = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
            while(r.next()) {
                res.add(new Categorie(
                        r.getInt("Id_Categorie"),
                        r.getString("Libelle")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }
}
