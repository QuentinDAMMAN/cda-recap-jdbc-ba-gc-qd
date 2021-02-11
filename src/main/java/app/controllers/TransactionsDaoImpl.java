package app.controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import app.dao.TransactionsDao;
import app.model.Transactions;
import app.sql.SQLConnection;

public class TransactionsDaoImpl implements TransactionsDao {
//	private static TransactionsDaoImpl transactionsTest = new TransactionsDaoImpl();
//	private static String dateFormat = "dd-MM-yyyy";
//	private static DateFormat df = new SimpleDateFormat(dateFormat);

	@Override
	public Transactions createTransactions(Transactions transactions) {
		try {
			PreparedStatement ps = SQLConnection.con.prepareStatement(
					"insert into transactions (Date_Vente,Date_Recuperation,Id_Piece) values (?,?,?); ",
					PreparedStatement.RETURN_GENERATED_KEYS);

//			convert date Java Date to SQL Date
			Date vente = convertToSqlDate(transactions.getDate_Vente());
			Date recuperation = convertToSqlDate(transactions.getDate_Recuperation());

			ps.setDate(1, vente);
			ps.setDate(2, recuperation);
			ps.setInt(3, transactions.getReference());
			ps.executeUpdate();
			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				transactions.setId_transactions(resultat.getInt(1));
				return transactions;
			}

		} catch (SQLIntegrityConstraintViolationException e) {
//			System.out.println("erreur de contraintes");
			e.printStackTrace();

		}

		catch (SQLException e) {
//			System.out.println("erreur sql");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteTransactions(int id) {
		try {
			PreparedStatement ps = SQLConnection.con.prepareStatement("delete from transactions where Id_Vente=?");
			ps.setInt(1, id);
			int nbDeleted = ps.executeUpdate();
			return nbDeleted == 1;
		} catch (SQLException e) {
//			System.out.println("erreur sql");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateTransactions(Transactions transactions) {
		String request = "update transactions set Date_Vente =?, Date_Recuperation =?, Id_Piece=?  where Id_Vente =?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
//			convert date Java Date to SQL Date
			Date vente = convertToSqlDate(transactions.getDate_Vente());
			Date recuperation = convertToSqlDate(transactions.getDate_Recuperation());

			stmt.setDate(1, vente);
			stmt.setDate(2, recuperation);
			stmt.setInt(3, transactions.getReference());
			stmt.setInt(4, transactions.getId_transactions());
			results = stmt.executeUpdate();
			return results == 1;
		} catch (SQLException e) {
//			System.out.println("erreur sql");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Transactions findTransactionById(int id) {

		Transactions transactions = null;
		try {
			PreparedStatement ps = SQLConnection.con
					.prepareStatement("select * from transactions where Id_Vente = ?; ");
			ps.setInt(1, id);
			ResultSet r = ps.executeQuery();
			if (r.next()) {
				transactions = new Transactions(r.getInt("Id_Vente"), r.getDate("Date_Vente"),
						r.getDate("Date_Recuperation"), r.getInt("Id_Piece"));
			}
		} catch (SQLException e) {
//			System.out.println("erreur sql");
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public List<Transactions> listTransactions() {
		List<Transactions> transactions = new ArrayList<>();
		try {
			PreparedStatement statement = SQLConnection.con.prepareStatement("select * from transactions");
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				transactions.add(new Transactions(r.getInt("Id_Vente"), r.getDate("Date_Vente"),
						r.getDate("Date_Recuperation"), r.getInt("Id_Piece")));
			}
		} catch (SQLException e) {
//			System.out.println("erreur sql");
			e.printStackTrace();
		}
		return transactions;
	}
	
	private static java.sql.Date convertToSqlDate(java.util.Date date) {
		java.sql.Date sqlDate = null;
		if (date != null) {
			sqlDate = new Date(date.getTime());
		}
		return sqlDate;
	}

//	public static void main(String[] args) {
//		SQLConnection.connect();
//
//		boolean stop = false;
//		Scanner sc = new Scanner(System.in);
//
//		do {
//			System.out.println("0 => quitter");
//			System.out.println("1 => créer");
//			System.out.println("2 => supprimer");
//			System.out.println("3 => modifier");
//			System.out.println("4 => Rechercher par Id");
//			System.out.println("5 => Lister");
//			System.out.print("\t>choix :");
//			int i = sc.nextInt();
//
//			switch (i) {
//
//			case 0:// arrêt
//				stop = true;
//				sc.close();
//				System.out.println("A bientot !");
//				break;
//
//			case 1:
////			Create	
//				System.out.print("saisir la date de vente (" + dateFormat + ") :");
//				String venteStr = sc.next();
//				System.out.print("saisir la date de recuperation (" + dateFormat + ") :");
//				String recuperationStr = sc.next();
//				System.out.print("saisir l'id de la piece:");
//				int piece = sc.nextInt();
//
//				java.util.Date vente = null;
//				java.util.Date recuperation = null;
//
//				try {
//					vente = df.parse(venteStr);
//					recuperation = df.parse(recuperationStr);
////					System.out.println("****** string convertit en date");
////					System.out.println(vente);
////					System.out.println(recuperation);
//
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//
//				System.out.println("********************************************");
//
//				Transactions test = transactionsTest.createTransactions(new Transactions(vente, recuperation, piece));
//				if (test != null) {
//					System.out.println("  > Transactions créé avec succès (id :" + test.getId_transactions() + ")");
//				}
//				break;
//
//			case 2:
////			Delete
//				System.out.print("saisir l'id du modèle :");
//				int IdDelete = sc.nextInt();
//				boolean testDelete = transactionsTest.deleteTransactions(IdDelete);
//				if (!testDelete) {
//					System.out.println("  > erreur de suppression");
//				} else if (testDelete) {
//					System.out.println("  > transaction supprimé avec succès (id :" + IdDelete + ")");
//				}
//				break;
//
//			case 3:
////			Modifier
//				System.out.print("saisir l'id de la transaction :");
//				int idTransaction = sc.nextInt();
//				System.out.print("saisir la nouvelle date de vente (" + dateFormat + ") :");
//				String newVenteStr = sc.next();
//				System.out.print("saisir la nouvelle de recuperation (" + dateFormat + ") :");
//				String newRecuperationStr = sc.next();
//				System.out.print("saisir le nouvelle id de la piece :");
//				int newIdPiece = sc.nextInt();
//
//				java.util.Date newVente = null;
//				java.util.Date newRecuperation = null;
//
//				try {
//					newVente = df.parse(newVenteStr);
//					newRecuperation = df.parse(newRecuperationStr);
//
////					System.out.println("****** string convertit en date");
////					System.out.println(newVente);
////					System.out.println(newRecuperation);
//
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//
//				boolean testUpdate = transactionsTest
//						.updateTransactions(new Transactions(idTransaction, newVente, newRecuperation, newIdPiece));
//				if (!testUpdate) {
//					System.out.println("  > erreur de modification");
//				} else if (testUpdate) {
//					System.out.println("  > Modele modifier avec succès (id :" + idTransaction + ")");
//				}
//				break;
////
//			case 4:
////			recherche par Id
//				System.out.print("saisir l'id du modèle :");
//				int IdModele = sc.nextInt();
//				Transactions testIdTransaction = transactionsTest.findTransactionById(IdModele);
//				if (testIdTransaction == null) {
//					System.out.println("  > Transaction inexistante");
//				} else if (testIdTransaction != null) {
//					System.out.println("  > Transaction trouvé avec succès\n" + testIdTransaction);
//				}
//				break;
////
//			case 5:
////			lister
//				List<Transactions> listTransactions = transactionsTest.listTransactions();
//
//				for (Transactions transaction : listTransactions) {
//					System.out.println(transaction);
//				}
//				break;
//
//			default:
//				break;
//			}
//			System.out.println();
//
//		} while (!stop);
//
//	}
	
}
