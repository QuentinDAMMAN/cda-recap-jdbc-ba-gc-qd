package app.menu.action.export;

import java.util.List;

import app.menu.saisie.Ihm;

final class Retour extends GetList {

	private static final int ID = 0;
	private static final String DESC = "Retourner au menu précédent";
	
	Retour() {
		super(ID, DESC,"");
	}

	@Override
	public List<String[]> executer() {
		Ihm.afficherClient("Retour au menu principal !");
		return null;
	}

}
