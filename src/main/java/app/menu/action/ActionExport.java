package app.menu.action;

import static app.menu.saisie.Ihm.IHM;

import java.util.TreeMap;

import app.menu.saisie.Ihm;

final class ActionExport extends Action {

	private static final int ID = 9;
	private static final String DESC = "Exporter des Tableaux";
	
	ActionExport() {
		super(ID, DESC);
	}
	
	@Override
	public boolean executer() {
		TreeMap<Integer,Action> actions = new TreeMap<>();
		newAction(actions, LesActions.RETOUR);
		newAction(actions, LesActions.ACTION_EXPORT_EXCEL);
		boolean returnMenu;
		int vActionSaisie;
		do {
			Ihm.afficherClient("--- Menu Export --- ");
			Ihm.afficherClient("Saisissez une action");
			for (Action action : actions.values()) {
				Ihm.afficherClient(action.getId()+ " - " +action.getDescription());
			}
			vActionSaisie = IHM.inputInt();
			returnMenu= actions.getOrDefault(vActionSaisie,LesActions.ACTION_INTROUVABLE).executer();
		} while(returnMenu);
		return Boolean.TRUE;
	}

	public static void newAction(TreeMap<Integer, Action> actions, Action action) {
		actions.put(action.getId(), action);
	}
}
