package app.menu.action.export;

import java.util.LinkedList;
import java.util.List;

import app.action.ChiffreDAffaire;

public class ListChiffreAffaire extends GetList {
	private static final int ID = 2;
	private static final String DESC = "Listes des chiffres d'affaires annuels";
	private static final String NOM = "LISTE_CHIFFRE_AFFAIRE";

	protected ListChiffreAffaire() {
		super(ID, DESC, NOM);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String[]> executer() {
		List<String> liste = ChiffreDAffaire.exec();
		List<String[]> output = new LinkedList<String[]>();
		for (String string : liste) {
			String[] splitString = string.split("-");
			output.add(splitString);
		}
		return output;
	}

}
