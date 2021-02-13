package app.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

import app.dao.ReferenceDao;
import app.model.Reference;
import app.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceDaoImpl implements ReferenceDao{
	final Logger logger = LoggerFactory.getLogger(ReferenceDaoImpl.class);


	@Override
	public Reference createReference(Reference reference) {
		String request = "insert into reference(Id_Reference,Libelle,Prix,Id_Categorie) values (?,?,?,?)";
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setString(1, reference.getId_reference());
			stmt.setString(2, reference.getLibelle());
			stmt.setDouble(3, reference.getPrix());
			stmt.setInt(4, reference.getId_categorie());
			logger.info("données entré " + reference.getLibelle() +", "+reference.getPrix()+", " + reference.getId_categorie() + " log id"+ System.currentTimeMillis());
			int results = stmt.executeUpdate();

			if (results==1) {
				logger.info("Reference ajouté, log id  " + System.currentTimeMillis() );
				return reference;

			}
		} catch (SQLIntegrityConstraintViolationException e) {
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		} catch (SQLException e) {
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		return null;
	}

	@Override
	public boolean deleteReference(String id) {
		String request = "delete from reference where id_reference = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setString(1, id);
			results = stmt.executeUpdate();
			logger.warn("Reference supprimé, log id " + System.currentTimeMillis());
		} catch (SQLException e) {
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean updateReference(String champ, String value, String id) {
		String request = "update reference set "+champ+" = ? where id_reference = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			switch(champ) {
			case "libelle":
				stmt.setString(1, value);
				break;
			case "prix":
				stmt.setDouble(1, Double.parseDouble(value));
				break;
			case "id_categorie":
				stmt.setInt(1, Integer.parseInt(value));
				break;
			}
			stmt.setString(2, id);
			results = stmt.executeUpdate();
			logger.info("MaJ Reference, log id " +System.currentTimeMillis());
		} catch (SQLException e) {
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Reference findReferenceById(String id) {
		String request = "select * from Reference where id_reference = ?";
		ResultSet results = null;
		Reference reference = null;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setString(1, id);
			results = stmt.executeQuery();
			logger.info("trouver un ref par son id, log id " + System.currentTimeMillis() );
			if (results.next()) {
				reference = new Reference(
						results.getString(1), 
						results.getString(2), 
						results.getFloat(3), 
						results.getInt(4));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		if (results != null) {
			return reference;
		}
		return null;
	}

	@Override
	public List<Reference> listReference() {
		String request = "select * from Reference ";
		ResultSet results = null;
		List<Reference> listReference = new LinkedList<Reference>();
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			results = stmt.executeQuery();
			while (results.next()) {
				Reference reference = new Reference(
						results.getString(1), 
						results.getString(2), 
						results.getFloat(3), 
						results.getInt(4));
				listReference.add(reference);
			}
			logger.info("retourner un list des refs, log id " +System.currentTimeMillis() );
		} catch (SQLException e) {
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		if (results != null) {
			return listReference;
		}
		return null;
	}
	
}
