package app.menu.action;

import static app.menu.saisie.Ihm.IHM;

import java.util.List;

import app.controllers.CategorieDaoImpl;
import app.controllers.ReferenceDaoImpl;
import app.menu.saisie.Ihm;
import app.model.Reference;

public class ActionReference extends Action {

	private static final int ID = 4;
	private static final String DESC = "Gestion des Références de pièces";
	private ReferenceDaoImpl dao;

	protected ActionReference() {
		super(ID, DESC);
		this.dao = new ReferenceDaoImpl();
	}

	@Override
	public boolean executer() {
		int input;
		int idSecondaire;
		do {
			IHM.afficherMenu(this.getClass().getSimpleName());
			String itemName = this.getClass().getSimpleName().substring(6);
			input = IHM.inputInt();
			main: switch (input) {
			case 0:
				Ihm.afficherClient("Retour au menu");
				break;
			case 1:
				Ihm.afficherClient("Veuillez saisir l'id de la " + itemName + " (6 Caractères alphanumérique)");
				String idReference = IHM.inputString().toUpperCase();
				if (!idReference.matches("[A-Za-z0-9_]{6}") || idReference.length() != 6) {
					Ihm.afficherClient(idReference + " ne remplit pas les conditions");
					break;
				}
				Ihm.afficherClient("Veuillez entrer l'intitulé de la " + itemName);
				String name = IHM.inputString();
				Ihm.afficherClient("Veuillez entrer le prix de la " + itemName);
				double prix = IHM.inputDouble();
				Ihm.afficherClient("Veuillez saisir l'id de la categorie de la " + itemName);
				idSecondaire = IHM.inputInt();
				if (new CategorieDaoImpl().findCategorieById(idSecondaire) == null) {
					Ihm.afficherClient("Cette Categorie n'existe pas");
					break;
				}
				Reference testCreation = dao.createReference(new Reference(idReference, name, prix, idSecondaire));
				if (testCreation != null) {
					Ihm.afficherClient(itemName + " créé avec succès (id :" + testCreation.getId_reference() + ")");
				}
				break;
			case 2:
				Ihm.afficherClient("Veuillez saisir l'id de la " + itemName + " (6 Caractères alphanumérique)");
				String idDelete = IHM.inputString().toUpperCase();
				if (!idDelete.matches("[A-Za-z0-9_]{6}") || idDelete.length() != 6) {
					Ihm.afficherClient(idDelete + " ne remplit pas les conditions");
					break;
				}
				boolean testDelete = dao.deleteReference(idDelete);
				if (!testDelete) {
					Ihm.afficherClient("Erreur de suppression");
				} else {
					Ihm.afficherClient(itemName + " supprimée avec succès (id :" + idDelete + ")");
				}
				break;
			case 3:
				String fieldName = IHM.selectionChamp(Reference.class);
				if (fieldName==null) {
					break;
				}
				Ihm.afficherClient("Vous souhaitez modifier " + fieldName + ", entrez la modification : ");
				String newFieldData = "";
				switch (fieldName.toLowerCase()) {
				case "libelle":
					newFieldData = IHM.inputString(true);
					break;
				case "prix":
					newFieldData = Double.toString(IHM.inputDouble());
					break;
				case "id_categorie":
					idSecondaire = IHM.inputInt();
					if (new CategorieDaoImpl().findCategorieById(idSecondaire) == null) {
						Ihm.afficherClient("Cette categorie n'existe pas");
						break main;
					}
					newFieldData = Integer.toString(idSecondaire);
					break;
				default:
					break main;
				}
				Ihm.afficherClient("Veuillez saisir l'id de la " + itemName + " (6 Caractères alphanumérique)");
				String idUpdate = IHM.inputString().toUpperCase();
				if (!idUpdate.matches("[A-Za-z0-9_]{6}") || idUpdate.length() != 6) {
					Ihm.afficherClient(idUpdate + " ne remplit pas les conditions");
					break;
				}
				boolean testUpdate = dao.updateReference(fieldName, newFieldData, idUpdate);
				if (!testUpdate) {
					Ihm.afficherClient("Erreur de modification");
				} else {
					Ihm.afficherClient(itemName + " modifiée avec succès (id :" + idUpdate + ")");
				}
				break;
			case 4:
				Ihm.afficherClient("Veuillez saisir l'id de la " + itemName + " (6 Caractères alphanumérique)");
				String idRecherche = IHM.inputString().toUpperCase();
				if (!idRecherche.matches("[A-Za-z0-9_]{6}") || idRecherche.length() != 6) {
					Ihm.afficherClient(idRecherche + " ne remplit pas les conditions");
					break;
				}
				Reference modeleRecherche = dao.findReferenceById(idRecherche);
				if (modeleRecherche == null) {
					Ihm.afficherClient("La " + itemName + " n'existe pas");
				} else if (modeleRecherche != null) {
					Ihm.afficherClient(itemName + " trouvé avec succès\n" + modeleRecherche);
				}
				break;
			case 5:
				List<Reference> liste = dao.listReference();
				for (Reference item : liste) {
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
