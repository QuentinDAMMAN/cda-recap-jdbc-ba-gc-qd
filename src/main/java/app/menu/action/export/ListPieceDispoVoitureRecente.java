package app.menu.action.export;

import java.util.List;

import app.action.Afficher3voitureToutePieceDisponible;

public class ListPieceDispoVoitureRecente extends GetList {
	private static final int ID = 1;
	private static final String DESC = "le nombre et la somme totale des pièces disponibles(pas encore vendues)";
	private static final String NOM = "LISTE_PIECE_DISPO_VOITURE_RECENTE";

	protected ListPieceDispoVoitureRecente() {
		super(ID, DESC, NOM);
	}

	@Override
	public List<String[]> executer() {
		return Afficher3voitureToutePieceDisponible.exec();
	}

}
