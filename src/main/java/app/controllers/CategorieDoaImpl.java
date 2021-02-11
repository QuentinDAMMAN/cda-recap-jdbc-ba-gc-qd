package app.controllers;

import app.dao.CategorieDao;
import app.model.Categorie;
import app.sql.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategorieDoaImpl implements CategorieDao {
    PreparedStatement ps;
    ResultSet res;
    String request;

    public static void main(String[] args) {
        CategorieDoaImpl categorieDoa = new CategorieDoaImpl();

    }

    @Override
    public Categorie createCategorie(Categorie categorie) {


        try {

            ps = SQLConnection.con.prepareStatement(
                    "Insert into Marque(Libelle) values (?)"
            );
            ps.setString(1, categorie.getLibelle());
            ps.executeQuery();
        } catch (SQLException se) {
            se.printStackTrace();
            se.getSQLState();

        }


        return null;
    }

    @Override
    public boolean deleteCategorie(int id) {
        return false;
    }

    @Override
    public boolean updaCategorie(Categorie categorie) {
        return false;
    }

    @Override
    public Categorie findCategorieById(int id) {
        return null;
    }

    @Override
    public List<Categorie> listCategories() {
        return null;
    }
}
