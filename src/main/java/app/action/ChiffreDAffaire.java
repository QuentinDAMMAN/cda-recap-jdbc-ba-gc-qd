package app.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.sql.SQLConnection;

public class ChiffreDAffaire {

	public static List<String> exec() {
		Logger logger = LoggerFactory.getLogger(ChiffreDAffaire.class.getSimpleName());
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
			logger.debug("Erreur SQL");
			logger.error("Erreur SQL",e);
		}
		return listeChiffreDAffaire;
	}

}
