package app.action;

import java.util.List;

import app.controllers.RefVehiculeImpl;
import app.export.HtmlExport;
import app.menu.action.export.GetList;
import app.model.ReferenceVehicule;

public class ReferencesVehiculesExportHTML extends GetList{
	
	private static final int ID = 8;
	private static final String DESC = "Listes des références par véhicules";
	private static final String NOM = "LISTE_REFERENCES_VEHICULES";

	public ReferencesVehiculesExportHTML() {
		super(ID, DESC, NOM);
	}
	
	public static void referencesvehicules() {
		// ************************** references *********************************
		RefVehiculeImpl dao = new RefVehiculeImpl();
		List<ReferenceVehicule> liste = dao.listRefVehicule();
		HtmlExport.exportHtml(liste, "referencesvehicules", "reference vehicule", "referencesvehicules");
	}

	@Override
	public List<String[]> executer() {
		referencesvehicules();
		return null;
	}

}
