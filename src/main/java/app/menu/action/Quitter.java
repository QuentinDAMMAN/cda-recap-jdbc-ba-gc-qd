package app.menu.action;

import app.menu.saisie.Ihm;

final class Quitter extends Action {

	private static final int ID = 0;
	private static final String DESC = "quitter le programme";
	
	Quitter() {
		super(ID, DESC);
	}

	@Override
	public boolean executer() {
		Ihm.afficherClient("A bientot !");
		return Boolean.FALSE;
	}

}
