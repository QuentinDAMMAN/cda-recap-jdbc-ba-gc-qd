package app.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

import app.dao.VehiculeDao;
import app.model.Vehicule;
import app.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VehiculeDaoImpl implements VehiculeDao {
	final Logger logger = LoggerFactory.getLogger(CategorieDoaImpl.class);


	@Override
	public Vehicule createVehicule(Vehicule vehicule) {
		String request = "Insert into Vehicule(Annee,Id_Marque,Id_Modele) values (?,?,?)";
		ResultSet results;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, vehicule.getAnnee());
			stmt.setInt(2, vehicule.getId_marque());
			stmt.setInt(3, vehicule.getId_modele());
			stmt.executeUpdate();
			results = stmt.getGeneratedKeys();
			if (results.next()) {
				vehicule.setId_vehicule(results.getInt(1));
				return vehicule;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean deleteVehicule(int id) {
		String request = "delete from Vehicule where id_marque = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeUpdate();
			logger.warn("Vehicule supprimé, log id " + System.currentTimeMillis() );
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
	public boolean updateVehicule(Vehicule vehicule) {
		String request = "update Vehicule set Annee = ?, Id_Marque = ?, Id_Modele = ? where Id_Vehicule = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setInt(1, vehicule.getAnnee());
			stmt.setInt(2, vehicule.getId_marque());
			stmt.setInt(3, vehicule.getId_modele());
			stmt.setInt(4, vehicule.getId_vehicule());
			results = stmt.executeUpdate();
			logger.info("données entrés " + vehicule.getAnnee() +", "+ vehicule.getId_marque() +", " + vehicule.getAnnee() + " log id " + System.currentTimeMillis());
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
	public Vehicule findVehiculeById(int id) {
		String request = "select * from Vehicule where Id_Vehicule = ?";
		ResultSet results = null;
		Vehicule vehicule = null;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setInt(1, id);
			results = stmt.executeQuery();
			if (results.next()) {
				vehicule = new Vehicule(
						results.getInt(1),
						results.getInt(2),
						results.getInt(3),
						results.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if (results != null) {
			return vehicule;
		}
		return null;
	}

	@Override
	public List<Vehicule> listVehicule() {
		String request = "select * from Vehicule order by id_vehicule";
		ResultSet results = null;
		List<Vehicule> listVehicule = new LinkedList<Vehicule>();
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			results = stmt.executeQuery();
			while (results.next()) {
				Vehicule vehicule = new Vehicule(
						results.getInt(1),
						results.getInt(2),
						results.getInt(3),
						results.getInt(4));
				listVehicule.add(vehicule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if (results != null) {
			return listVehicule;
		}
		return null;
	}

}
