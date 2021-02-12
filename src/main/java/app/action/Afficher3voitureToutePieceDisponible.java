package app.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import app.sql.SQLConnection;

public class Afficher3voitureToutePieceDisponible {
	public static List<String[]> exec() {
		StringBuilder bld = new StringBuilder();
		bld.append(" select ma.Libelle, mo.Libelle,");
		bld.append(" count(case when c.Id_Categorie =1 then 1 else null end) as 'Moteur', ");
		bld.append(" count(case when c.Id_Categorie =2 then 1 else null end) as 'Echappement',");
		bld.append(" count(case when c.Id_Categorie =3 then 1 else null end) as 'Eclairage',");
		bld.append(" count(case when c.Id_Categorie =4 then 1 else null end) as 'Climatisation',");
		bld.append(" count(case when c.Id_Categorie =5 then 1 else null end) as 'Chauffage',");
		bld.append(" count(case when c.Id_Categorie =6 then 1 else null end) as 'Freinage',");
		bld.append(" count(case when c.Id_Categorie =7 then 1 else null end) as 'Direction',");
		bld.append(" count(case when c.Id_Categorie =8 then 1 else null end) as 'Carrosserie',");
		bld.append(" count(case when c.Id_Categorie =9 then 1 else null end) as 'Electronique'");
		bld.append(" from categorie c ");
		bld.append(" inner join reference r using(id_categorie) ");
		bld.append(" inner join piece p using(id_reference)");
		bld.append(" left join transactions t using(id_piece) ");
		bld.append(" inner join referencevehicule rf using(id_reference)");
		bld.append(" inner join vehicule v on rf.Id_Vehicule = v.Id_Vehicule ");
		bld.append(" inner join modele mo using(id_modele)");
		bld.append(" inner join marque ma on ma.Id_Marque =mo.Id_Marque ");
		bld.append(" where t.id_vente is null");
		bld.append(" group by rf.Id_Vehicule");
		bld.append(" order by annee");
		bld.append(" limit 3;");
		String request = bld.toString();
		ResultSet results;
		List<String[]> exportResults = new LinkedList<>();
	//	Ihm.afficherClient("Combien de vehicule souhaitez vous voir ?");
	//	int input = Ihm.IHM.inputInt();
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
	//		stmt.setInt(1, input);
			results = stmt.executeQuery();
			ResultSetMetaData rsmd = results.getMetaData();
			int columnNumber = rsmd.getColumnCount();
			StringBuilder columnName = new StringBuilder();
			String[] titles = new String[columnNumber]; 
			for (int i = 1; i <= columnNumber; i++) {
				columnName.append(rsmd.getColumnName(i) + " ");
				titles[i-1] = rsmd.getColumnName(i);
			}
			exportResults.add(titles);
			while (results.next()) {
				String[] line = new String[columnNumber];
				StringBuilder strBuild = new StringBuilder();
				for (int i = 1; i <= columnNumber; i++) {
					int size = rsmd.getColumnName(i).length();
					String format = "%1$"+size+"s";
					String tempStr = String.format(format, results.getString(i));
					strBuild.append(tempStr + " ");
					line[i-1]=tempStr;
				}
				exportResults.add(line);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exportResults;
	}
}
