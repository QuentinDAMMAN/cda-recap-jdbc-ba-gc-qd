package app.menu.action.export;

import static app.menu.saisie.Ihm.IHM;

import java.util.List;
import java.util.TreeMap;

import app.export.ExcelExport;
import app.menu.action.Action;
import app.menu.saisie.Ihm;

public final class ActionExportExcel extends Action {

	private static final int ID = 1;
	private static final String DESC = "Export HTML";
	
	public ActionExportExcel() {
		super(ID, DESC);
	}
	
	@Override
	public boolean executer() {
		TreeMap<Integer,GetList> actions = new TreeMap<>();
		newAction(actions, LesListes.RETOUR);
		newAction(actions, LesListes.LIST_PIECE_DISPO_VOITURE_RECENTE);
		newAction(actions, LesListes.LIST_CHIFFRE_AFFAIRE);
		List<String[]> returnMenu;
		int vActionSaisie;
		do {
			Ihm.afficherClient("--- Menu Export Format EXCEL --- ");
			Ihm.afficherClient("Saisissez une action");
			for (GetList action : actions.values()) {
				Ihm.afficherClient(action.getId()+ " - " +action.getDescription());
			}
			vActionSaisie = IHM.inputInt();
			returnMenu= actions.getOrDefault(vActionSaisie,LesListes.ACTION_INTROUVABLE).executer();
			if (returnMenu!=null) {
				ExcelExport.exec(returnMenu, actions.get(vActionSaisie).getDescription());
			}
		} while(returnMenu!=null);
		return Boolean.TRUE;
	}

	public static void newAction(TreeMap<Integer, GetList> actions, GetList getList) {
		actions.put(getList.getId(), getList);
	}
}
