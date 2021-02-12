package app.dao;

import java.util.List;


import app.model.Modele;

public interface ModeleDao {
	Modele createModele(Modele modele);
	boolean deleteModele(int id);
	boolean updateModele(String champ,String value, int id);
	Modele findModeleeById(int id);
	List<Modele> listModeles();
}
