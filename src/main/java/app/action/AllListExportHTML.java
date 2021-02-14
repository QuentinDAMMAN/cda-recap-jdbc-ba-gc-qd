package app.action;

import java.util.List;

import app.menu.action.export.GetList;

public class AllListExportHTML extends GetList {

	private static final int ID = 9;
	private static final String DESC = "exporter toutes les listes";
	private static final String NOM = "ALL_LIST";

	public AllListExportHTML() {
		super(ID, DESC, NOM);
	}

	@Override
	public List<String[]> executer() {
		ModelesExportHTML.modeles();
		TransactionsExportHTML.transactions();
		MarquesExportHTML.marques();
		PiecesExportHTML.pieces();
		CategoriesExportHTML.categories();
		VehiculesExportHTML.vehicules();
		ReferencesExportHTML.references();
		ReferencesVehiculesExportHTML.referencesvehicules();
		return null;
	}

}
