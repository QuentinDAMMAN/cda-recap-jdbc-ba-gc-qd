package app.menu.action;

import static app.menu.saisie.Ihm.IHM;

import java.util.List;

import app.controllers.CategorieDaoImpl;
import app.menu.saisie.Ihm;
import app.model.Categorie;

public class ActionCategorie extends Action {

	private static final int ID = 3;
	private static final String DESC = "Gestion des Catégories de pièces";
	private CategorieDaoImpl dao;

	protected ActionCategorie() {
		super(ID, DESC);
		this.dao = new CategorieDaoImpl();
	}

	@Override
	public boolean executer() {
		int input;
		do {
			IHM.afficherMenu(this.getClass().getSimpleName());
			String itemName = this.getClass().getSimpleName().substring(6);
			input = IHM.inputInt();
			switch (input) {
			case 0:
				Ihm.afficherClient("Retour au menu");
				break;
			case 1:
				Ihm.afficherClient("Veuillez entrer le nom du "+itemName);
				String name = IHM.inputString(true);
				Categorie testCreation = dao.createCategorie(new Categorie(name));
				if (testCreation != null) {
					Ihm.afficherClient(itemName+" créé avec succès (id :" + testCreation.getId_categorie() + ")");
				}
				break;
			case 2:
				Ihm.afficherClient("Saisir l'id du " + itemName);
				int idDelete = IHM.inputInt();
				boolean testDelete = dao.deleteCategorie(idDelete);
				if (!testDelete) {
					Ihm.afficherClient("Erreur de suppression");
				} else {
					Ihm.afficherClient(itemName+" supprimée avec succès (id :" + idDelete + ")");
				}
				break;
			case 3:
				String fieldName = IHM.selectionChamp(Categorie.class);
				if (fieldName==null) {
					break;
				}
				Ihm.afficherClient("Vous souhaitez modifier " + fieldName + ", entrez la modification : ");
				String newFieldData = IHM.inputString(true);
				Ihm.afficherClient("Saisir l'id du "+itemName);
				int idUpdate = IHM.inputInt();
				boolean testUpdate = dao.updateCategorie(fieldName, newFieldData, idUpdate);
				if (!testUpdate) {
					Ihm.afficherClient("Erreur de modification");
				} else {
					Ihm.afficherClient(itemName+" modifiée avec succès (id :" + idUpdate + ")");
				}
				break;
			case 4:
				Ihm.afficherClient("Saisir l'id du "+itemName);
				int idRecherche = IHM.inputInt();
				Categorie modeleRecherche = dao.findCategorieById(idRecherche);
				if (modeleRecherche == null) {
					Ihm.afficherClient("La "+itemName+" n'existe pas");
				} else if (modeleRecherche != null) {
					Ihm.afficherClient(itemName+" trouvé avec succès\n" + modeleRecherche);
				}
				break;
			case 5:
				List<Categorie> liste = dao.listCategories();
				for (Categorie item : liste) {
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
