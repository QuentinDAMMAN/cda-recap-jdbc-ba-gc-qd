package app.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.sql.SQLConnection;

public class AfficherChiffreDAffaire {

	public static List<String> exec() {
		String request = "select year(t.Date_Vente ) as Annee, sum(prix) as Total from transactions t inner join piece p on t.Id_Piece = p.Id_Piece inner join reference r on r.Id_Reference= p.Id_Reference group by year(t.Date_Vente)";
		ResultSet results = null;
		List<String> listeChiffreDAffaire= new LinkedList<>();
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			results = stmt.executeQuery();
			String titre =("Annee - Total Chiffre d'Affaire");
			listeChiffreDAffaire.add(titre);
			while (results.next()) {
				String l = (" " +results.getInt("Annee")+ " - " + results.getInt("Total")+"€");
				listeChiffreDAffaire.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeChiffreDAffaire;
	}

}
