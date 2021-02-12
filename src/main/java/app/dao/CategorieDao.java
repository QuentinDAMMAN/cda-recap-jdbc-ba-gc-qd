package app.dao;

import java.util.List;
import app.model.Categorie;

public interface CategorieDao {
	Categorie createCategorie(Categorie categorie);
	boolean deleteCategorie(int id);
	boolean updateCategorie(String champ, String value, int id);
	Categorie findCategorieById(int id);
	List<Categorie> listCategories();
}
