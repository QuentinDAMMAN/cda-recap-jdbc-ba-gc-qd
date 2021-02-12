package app.menu.action;

import static app.menu.saisie.Ihm.IHM;

import java.util.List;

import app.controllers.MarqueDaoImpl;
import app.controllers.ModeleDaoImpl;
import app.menu.saisie.Ihm;
import app.model.Modele;

public class ActionModele extends Action {

	private static final int ID = 2;
	private static final String DESC = "Gestion des Modeles";
	private ModeleDaoImpl dao;

	protected ActionModele() {
		super(ID, DESC);
		this.dao = new ModeleDaoImpl();
	}

	@Override
	public boolean executer() {
		int input;
		int idMarqueTest;
		do {
			IHM.afficherMenu(this.getClass().getSimpleName());
			input = IHM.inputInt();
			switch (input) {
			case 0:
				Ihm.afficherClient("Retour au menu");
				break;
			case 1:
				Ihm.afficherClient("Veuillez entrer le nom du Modele");
				String name = IHM.inputString(true);
				Ihm.afficherClient("Veuillez saisir l'id de la marque du modele");
				idMarqueTest = IHM.inputInt();
				if (new MarqueDaoImpl().findMarqueById(idMarqueTest) == null) {
					Ihm.afficherClient("Cette marque n'existe pas");
					break;
				}
				Modele testCreation = dao.createModele(new Modele(name, idMarqueTest));
				if (testCreation != null) {
					Ihm.afficherClient("Modele créée avec succès (id :" + testCreation.getId_modele() + ")");
				}
				break;
			case 2:
				Ihm.afficherClient("Saisir l'id du modele :");
				int idDelete = IHM.inputInt();
				boolean testDelete = dao.deleteModele(idDelete);
				if (!testDelete) {
					Ihm.afficherClient("Erreur de suppression");
				} else {
					Ihm.afficherClient("Modele supprimée avec succès (id :" + idDelete + ")");
				}
				break;
			case 3:
				String fieldName = IHM.selectionChamp(Modele.class);
				if (fieldName==null) {
					break;
				}
				Ihm.afficherClient("Vous souhaitez modifier " + fieldName + ", entrez la modification : ");
				String newFieldData = "";
				if (fieldName.equalsIgnoreCase("libelle")) {
					newFieldData = IHM.inputString(true);
				} else {
					idMarqueTest = IHM.inputInt();
					if (new MarqueDaoImpl().findMarqueById(idMarqueTest) == null) {
						Ihm.afficherClient("Cette marque n'existe pas");
						break;
					}
					newFieldData = idMarqueTest+"";
				}
				Ihm.afficherClient("Saisir l'id du modele");
				int idUpdate = IHM.inputInt();
				boolean testUpdate = dao.updateModele(fieldName, newFieldData, idUpdate);
				if (!testUpdate) {
					Ihm.afficherClient("Erreur de modification");
				} else {
					Ihm.afficherClient("Modele modifiée avec succès (id :" + idUpdate + ")");
				}
				break;
			case 4:
				Ihm.afficherClient("Saisir l'id du modele");
				int idModele = IHM.inputInt();
				Modele modeleRecherche = dao.findModeleeById(idModele);
				if (modeleRecherche == null) {
					Ihm.afficherClient("Le Modele n'existe pas");
				} else if (modeleRecherche != null) {
					Ihm.afficherClient("Modele trouvé avec succès\n" + modeleRecherche);
				}
				break;
			case 5:
				List<Modele> liste = dao.listModeles();
				for (Modele item : liste) {
					Ihm.afficherClient(item.toString());
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
