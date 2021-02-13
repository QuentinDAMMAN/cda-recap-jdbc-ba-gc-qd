package app.menu.action;

import static app.menu.saisie.Ihm.IHM;

import java.util.Date;

import app.controllers.TransactionsDaoImpl;
import app.menu.saisie.Ihm;
import app.model.Transactions;;

final class ActionVente extends Action {

	private static final int ID = 10;
	private static final String DESC = "Vente";
	private TransactionsDaoImpl dao = new TransactionsDaoImpl();
	
	ActionVente() {
		super(ID, DESC);
	}

	@Override
	public boolean executer() {
		Ihm.afficherClient("Veuillez indiquer l'index de la pièce à vendre");
		int idPiece = IHM.inputInt();
		Ihm.afficherClient("Quand le client passera t'il la récupérer ?");
		Date date_recup = IHM.inputDate();
		Transactions vente = dao.createTransactions(new Transactions(new Date(), date_recup, idPiece));
		if (vente != null) {
			Ihm.afficherClient("La vente a été effectuée avec succès");
		}
		return Boolean.TRUE;
	}

}
