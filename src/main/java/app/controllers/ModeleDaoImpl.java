package app.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.ModeleDao;
import app.model.Modele;
import app.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModeleDaoImpl implements ModeleDao {
	final Logger logger = LoggerFactory.getLogger(ModeleDaoImpl.class);

	@Override
	public Modele createModele(Modele modele) {
		try {
			PreparedStatement ps = SQLConnection.con.prepareStatement(
					"insert into modele (Libelle,Id_Marque) values (?,?); ", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, modele.getLibelle());
			ps.setInt(2, modele.getId_marque());
			ps.executeUpdate();
			logger.info("donnée entere " + modele.getLibelle() +", " +modele.getId_modele()+", "+ modele.getId_marque() + " log id " + System.currentTimeMillis());
			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				modele.setId_modele(resultat.getInt(1));
				logger.info("model ajouté, log id " + System.currentTimeMillis());
				return modele;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		logger.warn("return null, log id " + System.currentTimeMillis()  );
		return null;
	}

	@Override
	public boolean deleteModele(int id) {
		try {
			PreparedStatement ps = SQLConnection.con.prepareStatement("delete from modele where Id_Modele=?");
			ps.setInt(1, id);
			int nbDeleted = ps.executeUpdate();
			logger.warn("Modele supprimé, log id " + System.currentTimeMillis());
			return nbDeleted == 1;
		} catch (SQLException e) {
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		return false;
	}

	@Override
	public boolean updateModele(String champ, String value, int id) {
		String request = "update modele set "+champ+" =? where Id_Modele =?";
		int results = 0;
		try {
			PreparedStatement stmt = SQLConnection.con.prepareStatement(request);
			if (champ.equalsIgnoreCase("libelle")) {
				stmt.setString(1, value);
			} else {
					stmt.setInt(1,Integer.parseInt(value));
			}
			stmt.setInt(2, id);

			results = stmt.executeUpdate();
			logger.info("Marque mis à jour, log id  " + System.currentTimeMillis());
			return results == 1;
		} catch (SQLException e) {
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		return false;
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
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
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
			logger.error(e.getMessage() + " log id " + System.currentTimeMillis(),e );
		}
		return modeles;
	}
}
