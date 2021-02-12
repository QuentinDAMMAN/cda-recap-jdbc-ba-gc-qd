package app.menu.action;

import static app.menu.saisie.Ihm.IHM;

import java.util.List;

import app.controllers.MarqueDaoImpl;
import app.menu.saisie.Ihm;
import app.model.Marque;

public class ActionMarque extends Action {

	private static final int ID = 1;
	private static final String DESC = "Gestion des Marques";
	private MarqueDaoImpl dao;

	protected ActionMarque() {
		super(ID, DESC);
		this.dao = new MarqueDaoImpl();
	}

	@Override
	public boolean executer() {
		int input;
		do {
			IHM.afficherMenu(this.getClass().getSimpleName());
			input = IHM.inputInt();
			switch (input) {
			case 0:
				Ihm.afficherClient("Retour au menu");
				break;
			case 1:
				Ihm.afficherClient("Veuillez entrer le nom de la marque");
				String name = IHM.inputString(true);
				Marque testCreation = dao.createMarque(new Marque(name));
				if (testCreation != null) {
					Ihm.afficherClient("Marque créée avec succès (id :" + testCreation.getId_marque() + ")");
				}
				break;
			case 2:
				Ihm.afficherClient("Saisir l'id de la marque :");
				int idDelete = IHM.inputInt();
				boolean testDelete = dao.deleteMarque(idDelete);
				if (!testDelete) {
					Ihm.afficherClient("Erreur de suppression");
				} else {
					Ihm.afficherClient("Marque supprimée avec succès (id :" + idDelete + ")");
				}
				break;
			case 3:
				String fieldName = IHM.selectionChamp(Marque.class);
				if (fieldName==null) {
					break;
				}
				Ihm.afficherClient("Vous souhaitez modifier " + fieldName + ", entrez la modification : ");
				String newFieldData = IHM.inputString(true);
				Ihm.afficherClient("Entrez l'id de la marque");
				int idUpdate = IHM.inputInt();
				boolean testUpdate = dao.updateMarque(fieldName, newFieldData, idUpdate);
				if (!testUpdate) {
					Ihm.afficherClient("Erreur de modification");
				} else {
					Ihm.afficherClient("Marque modifiée avec succès (id :" + idUpdate + ")");
				}
				break;
			case 4:
				Ihm.afficherClient("Saisir l'id de la marque :");
				int idMarque = IHM.inputInt();
				Marque marqueRecherche = dao.findMarqueById(idMarque);
				if (marqueRecherche == null) {
					Ihm.afficherClient("La Marque n'existe pas");
				} else if (marqueRecherche != null) {
					Ihm.afficherClient("Marque trouvée avec succès\n" + marqueRecherche);
				}
				break;
			case 5:
				List<Marque> listMarque = dao.listMarques();
				for (Marque marque : listMarque) {
					Ihm.afficherClient(marque.toString());
				}
				break;
			default:
				System.out.println("Mauvaise saisie");
				break;
			}
		} while (input != 0);
		return Boolean.TRUE;
	}

}
