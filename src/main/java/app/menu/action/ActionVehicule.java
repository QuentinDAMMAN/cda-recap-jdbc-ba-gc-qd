package app.menu.action;

import static app.menu.saisie.Ihm.IHM;

import java.util.List;

import app.controllers.MarqueDaoImpl;
import app.controllers.ModeleDaoImpl;
import app.controllers.VehiculeDaoImpl;
import app.menu.saisie.Ihm;
import app.model.Vehicule;

public class ActionVehicule extends Action {

	private static final int ID = 5;
	private static final String DESC = "Gestion des Vehicules";
	private VehiculeDaoImpl dao;

	protected ActionVehicule() {
		super(ID, DESC);
		this.dao = new VehiculeDaoImpl();
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
				Ihm.afficherClient("Veuillez entrer l'année de la " + itemName);
				int annee = IHM.inputInt();
				Ihm.afficherClient("Veuillez entrer l'id de la marque du " + itemName);
				int idMarque = IHM.inputInt();
				if (new MarqueDaoImpl().findMarqueById(idMarque) == null) {
					Ihm.afficherClient("Cette marque n'existe pas");
					break main;
				}
				Ihm.afficherClient("Veuillez saisir l'id du modele du " + itemName);
				int idModele = IHM.inputInt();
				if (new ModeleDaoImpl().findModeleeById(idModele) == null) {
					Ihm.afficherClient("Ce modele n'existe pas");
					break main;
				}
				Vehicule testCreation = dao.createVehicule(new Vehicule(annee, idMarque, idModele));
				if (testCreation != null) {
					Ihm.afficherClient(itemName + " créé avec succès (id :" + testCreation.getId_vehicule() + ")");
				}
				break;
			case 2:
				Ihm.afficherClient("Veuillez saisir l'id du " + itemName);
				int idDelete = IHM.inputInt();
				boolean testDelete = dao.deleteVehicule(idDelete);
				if (!testDelete) {
					Ihm.afficherClient("Erreur de suppression");
				} else {
					Ihm.afficherClient(itemName + " supprimée avec succès (id :" + idDelete + ")");
				}
				break;
			case 3:
				String fieldName = IHM.selectionChamp(Vehicule.class);
				if (fieldName == null) {
					break;
				}
				Ihm.afficherClient("Vous souhaitez modifier " + fieldName + ", entrez la modification : ");
				int newFieldData = 0;
				switch (fieldName.toLowerCase()) {
				case "annee":
					idSecondaire = IHM.inputInt();
					break;
				case "id_marque":
					idSecondaire = IHM.inputInt();
					if (new MarqueDaoImpl().findMarqueById(idSecondaire) == null) {
						Ihm.afficherClient("Cette marque n'existe pas");
						break main;
					}
					break;
				case "id_modele":
					idSecondaire = IHM.inputInt();
					if (new ModeleDaoImpl().findModeleeById(idSecondaire) == null) {
						Ihm.afficherClient("Ce modele n'existe pas");
						break main;
					}
					break;
				default:
					break main;
				}
				Ihm.afficherClient("Veuillez saisir l'id du " + itemName);
				int idUpdate = IHM.inputInt();
				boolean testUpdate = dao.updateVehicule(fieldName, newFieldData, idUpdate);
				if (!testUpdate) {
					Ihm.afficherClient("Erreur de modification");
				} else {
					Ihm.afficherClient(itemName + " modifiée avec succès (id :" + idUpdate + ")");
				}
				break;
			case 4:
				Ihm.afficherClient("Veuillez saisir l'id du " + itemName);
				int idRecherche = IHM.inputInt();
				Vehicule modeleRecherche = dao.findVehiculeById(idRecherche);
				if (modeleRecherche == null) {
					Ihm.afficherClient("La " + itemName + " n'existe pas");
				} else if (modeleRecherche != null) {
					Ihm.afficherClient(itemName + " trouvé avec succès\n" + modeleRecherche);
				}
				break;
			case 5:
				List<Vehicule> liste = dao.listVehicule();
				for (Vehicule item : liste) {
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
