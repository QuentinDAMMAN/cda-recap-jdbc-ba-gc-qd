package app.controllers;

import app.dao.MarqueDao;
import app.menu.saisie.Ihm;
import app.model.Marque;
import app.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

public class MarqueDaoImpl implements MarqueDao {
	final Logger logger = LoggerFactory.getLogger(MarqueDaoImpl.class);

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
			logger.info("Donnée entreer " + marque.getLibelle() + " log  id " + System.currentTimeMillis());
			if (results.next()) {
				marque.setId_marque(results.getInt(1));
				return marque;

			}
		} catch (SQLIntegrityConstraintViolationException e) {
			Ihm.afficherClient("La marque existe déjà");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis() );
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
			logger.warn("Marque supprimé, log id " +System.currentTimeMillis());
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis());
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean updateMarque(String champ, String value, int id) {
		String request = "update Marque set "+champ+" = ? where id_marque = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setString(1, value);
			stmt.setInt(2, id);
			results = stmt.executeUpdate();
			logger.info("maj marque, log id " + System.currentTimeMillis() );
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis());
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
			logger.info("marq trouve, log id "+ System.currentTimeMillis()  );
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage() +" log id " + System.currentTimeMillis() );
		}
		if (results != null) {
			return marque;
		}
		return null;
	}

	@Override
	public List<Marque> listMarques() {
		String request = "select * from Marque order by id_marque";
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
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis());
		}
		if (results != null) {
			logger.info("retourner list de marques, log id " + System.currentTimeMillis());
			return listMarque;
		}
		logger.info("list marques non trouver, log id  " + System.currentTimeMillis());
		return null;
	}

}
