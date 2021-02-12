package app.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import app.dao.PieceDao;
import app.model.Piece;
import app.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PieceDaoImpl implements PieceDao {
	final Logger logger = LoggerFactory.getLogger(PieceDaoImpl.class);

	@Override
	public Piece createPiece(Piece piece) {
		String request = "insert into Piece(Date_Extraction,Id_Reference,Id_Vehicule) values (?,?,?)";
		ResultSet results;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request,
					PreparedStatement.RETURN_GENERATED_KEYS);
			java.sql.Date date = new java.sql.Date(piece.getDate_extraction().getTime());
			System.out.println(date);
			stmt.setDate(1, date);
			System.out.println(stmt);
			stmt.setString(2, piece.getId_reference());
			stmt.setInt(3, piece.getId_vehicule());
			stmt.executeUpdate();
			results = stmt.getGeneratedKeys();
			logger.info("pièce ajouté, log id " + System.currentTimeMillis());
			if (results.next()) {
				piece.setId_piece(results.getInt(1));
				return piece;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println(e.getMessage());
			logger.error(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deletePiece(int id) {
		String request = "delete from Piece where id_piece = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeUpdate();
			logger.info("Piece supprimé, log id " + System.currentTimeMillis());
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean updatePiece(String champ, String value, int id) {
		String request = "update Piece set " + champ + " = ? where Libelle = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			switch (champ.toLowerCase()) {
			case "annee":
				stmt.setDate(1, java.sql.Date.valueOf(LocalDate.parse(value)));
				break;
			case "id_reference":
				stmt.setString(1, value);
				break;
			case "id_vehicule":
				stmt.setInt(1, Integer.parseInt(value));
				break;
			}
			stmt.setInt(1, id);
			results = stmt.executeUpdate();
			logger.info("données entrés libelle du champ " + champ + ", value =  " + value + ", id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Piece findPieceById(int id) {
		String request = "select * from Piece where id_piece = ?";
		ResultSet results = null;
		Piece piece = null;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeQuery();
			logger.info("id " + id + ", log id " + System.currentTimeMillis());
			if (results.next()) {
				piece = new Piece(results.getInt(1), java.sql.Date.valueOf(results.getDate(2).toLocalDate()),
						results.getString(3), results.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis());
		}
		if (results != null) {
			return piece;
		}
		return null;
	}

	@Override
	public List<Piece> listPieces() {
		String request = "select * from Piece ";
		ResultSet results = null;
		List<Piece> listPiece = new LinkedList<Piece>();
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			results = stmt.executeQuery();
			while (results.next()) {
				Piece piece = new Piece(results.getInt(1), java.sql.Date.valueOf(results.getDate(2).toLocalDate()),
						results.getString(3), results.getInt(4));
				listPiece.add(piece);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis());
		}
		if (results != null) {
			return listPiece;
		}
		return null;
	}
}
