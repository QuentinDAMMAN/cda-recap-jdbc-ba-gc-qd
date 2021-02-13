package app.controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import app.dao.TransactionsDao;
import app.menu.saisie.Ihm;
import app.model.Transactions;
import app.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionsDaoImpl implements TransactionsDao {
final Logger logger = LoggerFactory.getLogger(TransactionsDaoImpl.class);

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
			Ihm.afficherClient("Cette pièce est déjà vendue");
			logger.error("erreur violation contrainte",e);
		} catch (SQLException e) {
			logger.error("erreur sql",e);
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
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
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
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
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
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
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
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
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
}
