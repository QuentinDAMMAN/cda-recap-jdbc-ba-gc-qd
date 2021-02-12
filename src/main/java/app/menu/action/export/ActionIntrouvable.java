package app.menu.action.export;

import java.util.List;

public final class ActionIntrouvable extends GetList {

	private static final int ID = 99;
	private static final String DESC = "action introuvable";
	
	ActionIntrouvable() {
		super(ID, DESC,"");
	}

	@Override
	public List<String[]> executer() {
		System.out.println("> votre saisie est incorrecte.");
		return null;
	}
}
