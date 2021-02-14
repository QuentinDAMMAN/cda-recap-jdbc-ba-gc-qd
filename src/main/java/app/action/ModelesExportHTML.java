package app.action;

import java.util.List;

import app.controllers.ModeleDaoImpl;
import app.export.HtmlExport;
import app.menu.action.export.GetList;
import app.model.Modele;

public class ModelesExportHTML extends GetList {

	private static final int ID = 1;
	private static final String DESC = "Listes des modèles";
	private static final String NOM = "LISTE_MODELES";

	public ModelesExportHTML() {
		super(ID, DESC, NOM);
	}

	public static void modeles() {
		// ************************** modeles *********************************
		ModeleDaoImpl dao = new ModeleDaoImpl();
		List<Modele> liste = dao.listModeles();
		HtmlExport.exportHtml(liste, "modeles", "modele", "modeles");
	}

	@Override
	public List<String[]> executer() {
		modeles();
		return null;
	}

}
