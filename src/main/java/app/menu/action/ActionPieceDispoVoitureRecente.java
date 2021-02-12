package app.menu.action;

import java.util.List;

import app.action.Afficher3voitureToutePieceDisponible;
import app.menu.saisie.Ihm;

final class ActionPieceDispoVoitureRecente extends Action {

	private static final int ID = 8;
	private static final String DESC = "Afficher les pièces disponibles pour les 3 modeles les plus récents";
	
	ActionPieceDispoVoitureRecente() {
		super(ID, DESC);
	}

	@Override
	public boolean executer() {
		List<String[]> liste = Afficher3voitureToutePieceDisponible.exec();
		for (String[] line : liste) {
			for (String value : line) {
				System.out.print(value + " ");
			}
			Ihm.afficherClient("");
		}
		return Boolean.TRUE;
	}

}
