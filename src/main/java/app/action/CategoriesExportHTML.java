package app.action;

import java.util.List;

import app.controllers.CategorieDaoImpl;
import app.export.HtmlExport;
import app.menu.action.export.GetList;
import app.model.Categorie;

public class CategoriesExportHTML extends GetList {

	private static final int ID = 5;
	private static final String DESC = "Listes des catégories";
	private static final String NOM = "LISTE_CATEGORIES";

	public CategoriesExportHTML() {
		super(ID, DESC, NOM);
	}

	public static void categories() {
		// ************************** categories *********************************
		CategorieDaoImpl dao = new CategorieDaoImpl();
		List<Categorie> liste = dao.listCategories();
		HtmlExport.exportHtml(liste, "categories", "categorie", "categories");
	}

	@Override
	public List<String[]> executer() {
		categories();
		return null;
	}
}
