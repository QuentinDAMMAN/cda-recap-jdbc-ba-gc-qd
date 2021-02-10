package app.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

import app.dao.PieceDao;
import app.model.Marque;
import app.model.Piece;
import app.sql.SQLConnection;

public class PieceDaoImpl implements PieceDao {
	@Override
	public Piece createPiece(Piece piece) {
		String request = "insert into Piece(Prix,Id_Vehicule,Id_Categorie,Libelle,Stock) values (?,?,?,?,?)";
		ResultSet results;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setFloat(1, piece.getPrix());
			stmt.setInt(2, piece.getId_vehicule());
			stmt.setInt(3, piece.getId_categorie());
			stmt.setString(4, piece.getLibelle());
			stmt.setInt(5, piece.getStock());
			stmt.executeUpdate();
			results = stmt.getGeneratedKeys();
			if (results.next()) {
				piece.setReference(results.getInt(1));
				return piece;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println(e.getMessage());
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean updatePiece(Piece piece, String champ) {
		String request = "update Piece set " + champ + " = ? where Libelle = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			switch (champ.toLowerCase()) {
			case "prix":
				stmt.setFloat(1, piece.getPrix());
				break;
			case "libelle":
				stmt.setString(1, piece.getLibelle());
				break;
			case "stock":
				stmt.setInt(1, piece.getStock());
				break;
			default:
				return Boolean.FALSE;
			}
			stmt.setString(2, piece.getLibelle());
			results = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Piece findPieceById(int id) {
		String request = "select * from Piece where reference = ?";
		ResultSet results = null;
		Piece piece = null;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeQuery();
			if (results.next()) {
				piece = new Piece(
						results.getInt(1),
						results.getString(2),
						results.getFloat(3),
						results.getInt(4),
						results.getInt(5),
						results.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (results != null) {
			return piece;
		}
		return null;
	}

	@Override
	public List<Piece> listPieces() {
		String request = "select * from Piece";
		ResultSet results = null;
		List<Piece> listPiece = new LinkedList<Piece>();
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			results = stmt.executeQuery();
			while(results.next()) {
				Piece piece = new Piece(
						results.getInt(1),
						results.getString(2),
						results.getFloat(3),
						results.getInt(4),
						results.getInt(5),
						results.getInt(6));
				listPiece.add(piece);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (results != null) {
			return listPiece;
		}
		return null;
	}
}
