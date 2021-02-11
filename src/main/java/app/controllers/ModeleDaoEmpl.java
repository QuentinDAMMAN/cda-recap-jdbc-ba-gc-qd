package app.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.ModeleDao;
import app.model.Modele;
import app.sql.SQLConnection;

public class ModeleDaoEmpl implements ModeleDao {
	static ModeleDaoEmpl modeleTest = new ModeleDaoEmpl();

	@Override
	public Modele createModele(Modele modele) {
		try {
			PreparedStatement ps = SQLConnection.con.prepareStatement(
					"insert into modele (Libelle,Id_Marque) values (?,?); ", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, modele.getLibelle());
			ps.setInt(2, modele.getId_marque());
			ps.executeUpdate();
			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				modele.setId_modele(resultat.getInt(1));
				return modele;
			}
		} catch (SQLException e) {
			System.out.println("erreur sql");
		}
		return null;
	}

	@Override
	public boolean deleteModele(int id) {
		try {
			PreparedStatement ps = SQLConnection.con.prepareStatement("delete from modele where Id_Modele=?");
			ps.setInt(1, id);
			int nbDeleted = ps.executeUpdate();
			return nbDeleted == 1;
		} catch (SQLException e) {
			System.out.println("erreur sql");
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
			return results == 1;
		} catch (SQLException e) {
			System.out.println("erreur sql");
		}
		return Boolean.FALSE;
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
			System.out.println("erreur sql");
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
			System.out.println("erreur sql");
		}
		return modeles;
	}
}
