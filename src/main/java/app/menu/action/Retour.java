package app.menu.action;

import app.menu.saisie.Ihm;

final class Retour extends Action {

	private static final int ID = 0;
	private static final String DESC = "Retourner au menu précédent";
	
	Retour() {
		super(ID, DESC);
	}

	@Override
	public boolean executer() {
		Ihm.afficherClient("Retour au menu principal !");
		return Boolean.FALSE;
	}

}
