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

public class ReferenceDaoImpl implements ReferenceDao{

	@Override
	public Reference createReference(Reference reference) {
		String request = "insert into reference(Id_Reference,Libelle,Prix,Id_Categorie) values (?,?,?,?)";
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setString(1, reference.getId_reference());
			stmt.setString(2, reference.getLibelle());
			stmt.setDouble(3, reference.getPrix());
			stmt.setInt(4, reference.getId_categorie());
			int results = stmt.executeUpdate();
			if (results==1) {
				return reference;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println(e.getMessage());	
		} catch (SQLException e) {
			e.printStackTrace();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (results == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean updateReference(Reference reference) {
		String request = "update reference set Libelle = ? , Prix = ? , Id_Categorie = ? where id_reference = ?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setString(1, reference.getLibelle());
			stmt.setDouble(2, reference.getPrix());
			stmt.setInt(3, reference.getId_categorie());
			stmt.setString(4, reference.getId_reference());
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
	public Reference findReferenceById(String id) {
		String request = "select * from Reference where id_reference = ?";
		ResultSet results = null;
		Reference reference = null;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			stmt.setString(1, id);
			results = stmt.executeQuery();
			if (results.next()) {
				reference = new Reference(
						results.getString(1), 
						results.getString(2), 
						results.getFloat(3), 
						results.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (results != null) {
			return listReference;
		}
		return null;
	}
	
}
