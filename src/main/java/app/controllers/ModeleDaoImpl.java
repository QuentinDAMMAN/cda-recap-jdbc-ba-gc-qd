package app.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.ModeleDao;
import app.model.Modele;
import app.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModeleDaoImpl implements ModeleDao {
	final Logger logger = LoggerFactory.getLogger(CategorieDoaImpl.class);
//	private static ModeleDaoEmpl modeleTest = new ModeleDaoEmpl();

	@Override
	public Modele createModele(Modele modele) {
		try {
			PreparedStatement ps = SQLConnection.con.prepareStatement(
					"insert into modele (Libelle,Id_Marque) values (?,?); ", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, modele.getLibelle());
			ps.setInt(2, modele.getId_marque());
			ps.executeUpdate();
			logger.info("donnée entere " + modele.getLibelle() +", " +modele.getId_modele()+", "+ modele.getId_marque() + " log id " + System.currentTimeMillis());
			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				modele.setId_modele(resultat.getInt(1));
				logger.info("model ajouté, log id " + System.currentTimeMillis());
				return modele;
			}
		} catch (SQLException e) {
//			System.out.println("erreur sql");
			e.getMessage();
			logger.error(String.valueOf(e));
		}
		logger.warn("return null, log id " + System.currentTimeMillis()  );
		return null;
	}

	@Override
	public boolean deleteModele(int id) {
		try {
			PreparedStatement ps = SQLConnection.con.prepareStatement("delete from modele where Id_Modele=?");
			ps.setInt(1, id);
			int nbDeleted = ps.executeUpdate();
			logger.warn("Modele supprimé, log id " + System.currentTimeMillis());
			return nbDeleted == 1;
		} catch (SQLException e) {
//			System.out.println("erreur sql");
			e.getMessage();
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis() );
		}
		return false;
	}

	@Override
	public boolean updateModele(Modele modele) {
		String request = "update modele set Libelle =?, Id_Marque =? where Id_Modele =?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setString(1, modele.getLibelle());
			stmt.setInt(2, modele.getId_marque());
			stmt.setInt(3, modele.getId_modele());

			results = stmt.executeUpdate();
			logger.info("Maque mis à jour, log id  " + System.currentTimeMillis());
			return results == 1;
		} catch (SQLException e) {
//			System.out.println("erreur sql");
			e.getMessage();


		}
		return false;
	}

	@Override
	public Modele findModeleeById(int id) {
		Modele modele = null;
		try {
			PreparedStatement ps = SQLConnection.con.prepareStatement("select * from modele where Id_Modele = ?; ");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			if (r.next()) {
				modele = new Modele(r.getInt("Id_Modele"), r.getString("Libelle"), r.getInt("Id_Marque"));
			}
		} catch (SQLException e) {
//			System.out.println("erreur sql");
			e.getMessage();
		}
		return modele;
	}

	@Override
	public List<Modele> listModeles() {
		List<Modele> modeles = new ArrayList<>();
		try {
			PreparedStatement statement = SQLConnection.con.prepareStatement("select * from modele");
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				modeles.add(new Modele(r.getInt("Id_Modele"), r.getString("Libelle"), r.getInt("Id_Marque")));
			}
		} catch (SQLException e) {
//			System.out.println("erreur sql");
			e.getMessage();
		}
		return modeles;
	}
	
//	public static void main(String[] args) {
//	SQLConnection.connect();
//
//	boolean stop = false;
//	Scanner sc = new Scanner(System.in);
//
//	do {
//		System.out.println("0 => quitter");
//		System.out.println("1 => créer");
//		System.out.println("2 => supprimer");
//		System.out.println("3 => modifier");
//		System.out.println("4 => Rechercher par Id");
//		System.out.println("5 => Lister");
//		System.out.print("\t>choix :");
//		int i = sc.nextInt();
//
//		switch (i) {
//
//		case 0:// arrêt
//			stop = true;
//			sc.close();
//			System.out.println("A bientot !");
//			break;
//
//		case 1:
////			Create	
//			System.out.print("saisir le libellé du modèle :");
//			String lib = sc.next();
//			System.out.print("saisir l'id de la marque :");
//			int marque = sc.nextInt();
//
//			Modele test = modeleTest.createModele(new Modele(lib, marque));
//			if (test != null) {
//				System.out.println("  > Modele créé avec succès (id :" + test.getId_modele() + ")");
//			}
//			break;
//
//		case 2:
////			Delete
//			System.out.print("saisir l'id du modèle :");
//			int IdDelete = sc.nextInt();
//			boolean testDelete = modeleTest.deleteModele(IdDelete);
//			if (!testDelete) {
//				System.out.println("  > erreur de suppression");
//			} else if (testDelete) {
//				System.out.println("  > Modele supprimé avec succès\n" + IdDelete);
//			}
//			break;
//			
//		case 3:
////			Modifier
//			System.out.print("saisir l'id du du modèle à modifier:");
//			int newModel = sc.nextInt();
//			System.out.print("saisir le nouveau libellé du modèle :");
//			String newLib = sc.next();
//			System.out.print("saisir un nouveau id de  marque :");
//			int newIdMarque = sc.nextInt();
//			boolean testUpdate = modeleTest.updateModele(new Modele(newModel,newLib,newIdMarque));
//			if (!testUpdate) {
//				System.out.println("  > erreur de modification");
//			} else if(testUpdate) {
//				System.out.println("  > Modele modifier avec succès (id :" +newModel+ ")");
//			}
//			break;
//
//		case 4:
////			recherche par Id
//			System.out.print("saisir l'id du modèle :");
//			int IdModele = sc.nextInt();
//			Modele testIdModele = modeleTest.findModeleeById(IdModele);
//			if (testIdModele == null) {
//				System.out.println("  > Modele inexistant");
//			} else if (testIdModele != null) {
//				System.out.println("  > Modele trouvé avec succès\n" + testIdModele);
//			}
//			break;
//
//		case 5:
////			lister
//			List<Modele> listModeles = modeleTest.listModeles();
//
//			for (Modele modele : listModeles) {
//				System.out.println(modele);
//			}
//			break;
//
//		default:
//			break;
//		}
//		System.out.println();
//
//	} while (!stop);
//
//}
}
