package app.action;

import java.util.List;

import app.controllers.VehiculeDaoImpl;
import app.export.HtmlExport;
import app.menu.action.export.GetList;
import app.model.Vehicule;

public class VehiculesExportHTML extends GetList{
	
	private static final int ID = 6;
	private static final String DESC = "Listes des véhicules";
	private static final String NOM = "LISTE_VEHICULES";

	public VehiculesExportHTML() {
		super(ID, DESC, NOM);
	}
	
	public static void vehicules() {
		// ************************** vehicules *********************************
		VehiculeDaoImpl dao = new VehiculeDaoImpl();
		List<Vehicule> liste = dao.listVehicule();
		HtmlExport.exportHtml(liste, "vehicules", "vehicule", "vehicules");
	}

	@Override
	public List<String[]> executer() {
		vehicules();
		return null;
	}

}
