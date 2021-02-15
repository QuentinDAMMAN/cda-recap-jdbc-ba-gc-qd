package app.action;

import app.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SomeTotalePiecesDisponible {


    public static void  exec() {
        try {
            String request = "create temporary table piece_info(\n" +
                    "\t\tpiece_id int(10) not null,\n" +
                    "\t\tpiece_libelle varchar(250) not null,\n" +
                    "\t\tpiece_prix int(10) not null,\n" +
                    "\t\tpiece_date_recuperation Date,\n" +
                    "\t\tpiece_date_vente Date,\n" +
                    "\t\tpiece_vehicule_id int(10)\n" +
                    "\t);";
            PreparedStatement ps = SQLConnection.con.prepareStatement("create temporary table piece_info(piece_id int(10) not null,piece_libelle varchar(250) not null,piece_prix int(10) not null,piece_date_recuperation Date,piece_date_vente Date,piece_vehicule_id int(10));");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
