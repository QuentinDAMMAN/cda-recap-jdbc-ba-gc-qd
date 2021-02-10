package app.controllers;

import app.dao.MarqueDao;
import app.model.Marque;
import app.sql.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

public class MarqueDaoImpl implements MarqueDao {
	@Override
	public Marque createMarque(Marque marque) {
		String request = "Insert into Marque(Libelle) values (?)";
		ResultSet results;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, marque.getLibelle());
			stmt.executeUpdate();
			results = stmt.getGeneratedKeys();
			if (results.next()) {
				marque.setId_marque(results.getInt(1));
				return marque;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteMarque(int id) {
		String request = "delete from Marque where id_marque = ?";
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
	public boolean updateMarque(Marque marque) {
		String request = "update Marque set Libelle = ? where Libelle = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setString(1, marque.getLibelle());
			stmt.setString(2, marque.getLibelle());
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
	public Marque findMarqueById(int id) {
		String request = "select * from Marque where id_marque = ?";
		ResultSet results = null;
		Marque marque = null;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeQuery();
			results.next();
			marque = new Marque(results.getInt(1),results.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (results != null) {
			return marque;
		}
		return null;
	}

	@Override
	public List<Marque> listMarques() {
		String request = "select * from Marque";
		ResultSet results = null;
		List<Marque> listMarque = new LinkedList<Marque>();
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			results = stmt.executeQuery();
			while(results.next()) {
				Marque marque = new Marque(results.getInt(1),results.getString(2));
				listMarque.add(marque);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (results != null) {
			return listMarque;
		}
		return null;
	}

}
