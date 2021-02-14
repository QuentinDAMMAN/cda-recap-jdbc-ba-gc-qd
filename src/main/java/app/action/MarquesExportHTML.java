package app.action;

import java.util.List;

import app.controllers.MarqueDaoImpl;
import app.export.HtmlExport;
import app.menu.action.export.GetList;
import app.model.Marque;

public class MarquesExportHTML extends GetList {

	private static final int ID = 3;
	private static final String DESC = "Listes des marques";
	private static final String NOM = "LISTE_MARQUES";

	public MarquesExportHTML() {
		super(ID, DESC, NOM);
	}

	public static void marques() {
		// ************************** marques *********************************
		MarqueDaoImpl dao = new MarqueDaoImpl();
		List<Marque> liste = dao.listMarques();
		HtmlExport.exportHtml(liste, "marques", "marque", "marques");
	}

	@Override
	public List<String[]> executer() {
		marques();
		return null;
	}
}
