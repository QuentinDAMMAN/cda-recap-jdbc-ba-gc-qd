package app.action;

import java.util.List;

import app.controllers.ReferenceDaoImpl;
import app.export.HtmlExport;
import app.menu.action.export.GetList;
import app.model.Reference;

public class ReferencesExportHTML extends GetList{
	
	private static final int ID = 7;
	private static final String DESC = "Listes des références";
	private static final String NOM = "LISTE_REFERENCES";

	public ReferencesExportHTML() {
		super(ID, DESC, NOM);
	}
	
	public static void references() {
		// ************************** references *********************************
		ReferenceDaoImpl dao = new ReferenceDaoImpl();
		List<Reference> liste = dao.listReference();
		HtmlExport.exportHtml(liste, "references", "reference", "references");
	}

	@Override
	public List<String[]> executer() {
		references();
		return null;
	}
}
