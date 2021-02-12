package app.menu.action;

import java.util.List;

import app.action.ChiffreDAffaire;
import app.menu.saisie.Ihm;

final class ActionAfficherChiffreAffaire extends Action {

	private static final int ID = 7;
	private static final String DESC = "Afficher le chiffre d'affaire par année";
	
	ActionAfficherChiffreAffaire() {
		super(ID, DESC);
	}

	@Override
	public boolean executer() {
		List<String> liste = ChiffreDAffaire.exec();
		for (String string : liste) {
			Ihm.afficherClient(string);
		}
		return Boolean.TRUE;
	}

}
