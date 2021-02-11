package app.controllers;

import app.dao.RefVehiculeDoa;
import app.model.ReferenceVehicule;
import app.sql.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RefVehiculeImpl implements RefVehiculeDoa {
    @Override
    public ReferenceVehicule createReVehicule(ReferenceVehicule referenceVehicule) {
        try {
            PreparedStatement ps = SQLConnection.con.prepareStatement(
                    "insert into ReferenceVehicule(Id_Vehicule,Id_Reference) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, referenceVehicule.getId_vehicule());
            ps.setString(2, referenceVehicule.getId_Rererence());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
                System.out.println(referenceVehicule.getId_Rererence() + " ajouté au categorie avec succès");
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println(se.getMessage());
        }
        return new ReferenceVehicule(referenceVehicule.getId_Rererence(), referenceVehicule.getId_vehicule());
    }

    @Override
    public boolean deleteRefVehicule(String id) {
        boolean res = false;
        try {
            PreparedStatement ps = SQLConnection.con.prepareStatement("delete from  referencevehicule where Id_Reference = ?");
            ps.setString(1, id);
            int i = ps.executeUpdate();
            if (i == 0) {
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
    public boolean updateRefVehicule(String referenceVehicule, String id) {
        boolean res = false;
//        PreparedStatement ps;
//        try {
//            ps = SQLConnection.con.prepareStatement("update referencevehicule set Id_Reference  = ? where Id_Reference  = ?");
//            ps.setString(1,referenceVehicule);
//            ps.setString(2,id);
//            int i = ps.executeUpdate();
//            if(i == 0) {
//                System.out.println("MAJ échec");
//            } else {
//                System.out.println("MAJ avec succès");
//            }
//            res = true;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        return res;
    }

    @Override
    public ReferenceVehicule findReVehiculeById(String id) {
        ReferenceVehicule res = null;
        try {
            PreparedStatement ps = SQLConnection.con.prepareStatement("select * from referencevehicule where Id_Reference = ?");
            ps.setString(1, id);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                res = new ReferenceVehicule(
                        r.getString("Id_Reference"),
                        r.getInt("Id_Vehicule")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public List<ReferenceVehicule> listRefVehicule() {
        List<ReferenceVehicule> res = new ArrayList<>();
        try {
            PreparedStatement ps = SQLConnection.con.prepareStatement("select * from referencevehicule");
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                res.add(new ReferenceVehicule(
                        r.getString("Id_Reference"),
                        r.getInt("Id_Vehicule")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }
}
