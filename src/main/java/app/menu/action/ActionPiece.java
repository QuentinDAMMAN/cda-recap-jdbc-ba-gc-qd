package app.menu.action;

import static app.menu.saisie.Ihm.IHM;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import app.controllers.PieceDaoImpl;
import app.controllers.ReferenceDaoImpl;
import app.controllers.VehiculeDaoImpl;
import app.menu.saisie.Ihm;
import app.model.Piece;

public class ActionPiece extends Action {

	private static final int ID = 6;
	private static final String DESC = "Gestion des Pièces";
	private PieceDaoImpl dao;

	protected ActionPiece() {
		super(ID, DESC);
		this.dao = new PieceDaoImpl();
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
				Ihm.afficherClient("Veuillez entrer la date d'extraction de la " + itemName);
				Date annee = IHM.inputDate();
				Ihm.afficherClient("Veuillez saisir l'id de la reference (6 Caractères alphanumérique)");
				String idReference = IHM.inputString().toUpperCase();
				if (!idReference.matches("[A-Z0-9]{6}") || idReference.length() != 6) {
					Ihm.afficherClient(idReference + " ne remplit pas les conditions");
					break;
				}
				if (new ReferenceDaoImpl().findReferenceById(idReference) == null) {
					Ihm.afficherClient("Cette reference n'existe pas");
					break main;
				}
				Ihm.afficherClient("Veuillez saisir l'id du vehicule du " + itemName);
				int idVehicule = IHM.inputInt();
				if (new VehiculeDaoImpl().findVehiculeById(idVehicule) == null) {
					Ihm.afficherClient("Ce vehicule n'existe pas");
					break main;
				}
				Piece testCreation = dao.createPiece(new Piece(annee, idReference, idVehicule));
				if (testCreation != null) {
					Ihm.afficherClient(itemName + " créé avec succès (id :" + testCreation.getId_vehicule() + ")");
				}
				break;
			case 2:
				Ihm.afficherClient("Veuillez saisir l'id du " + itemName);
				int idDelete = IHM.inputInt();
				boolean testDelete = dao.deletePiece(idDelete);
				if (!testDelete) {
					Ihm.afficherClient("Erreur de suppression");
				} else {
					Ihm.afficherClient(itemName + " supprimée avec succès (id :" + idDelete + ")");
				}
				break;
			case 3:
				String fieldName = IHM.selectionChamp(Piece.class);
				if (fieldName == null) {
					break;
				}
				Ihm.afficherClient("Vous souhaitez modifier " + fieldName + ", entrez la modification : ");
				String newFieldData = "";
				switch (fieldName.toLowerCase()) {
				case "date_extraction":
					Date date = IHM.inputDate();
					newFieldData = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
					break;
				case "id_reference":
					idReference = IHM.inputString().toUpperCase();
					if (!idReference.matches("[A-Z0-9]{6}") || idReference.length() != 6) {
						Ihm.afficherClient(idReference + " ne remplit pas les conditions (6 Caractères alphanumérique)");
						break;
					}
					if (new ReferenceDaoImpl().findReferenceById(idReference) == null) {
						Ihm.afficherClient("Cette reference n'existe pas");
						break main;
					}
					newFieldData = idReference;
					break;
				case "id_vehicule":
					idSecondaire = IHM.inputInt();
					if (new VehiculeDaoImpl().findVehiculeById(idSecondaire) == null) {
						Ihm.afficherClient("Ce vehicule n'existe pas");
						break main;
					}
					newFieldData = Integer.toString(idSecondaire);
					break;
				default:
					break main;
				}
				Ihm.afficherClient("Veuillez saisir l'id de la " + itemName);
				int idUpdate = IHM.inputInt();
				boolean testUpdate = dao.updatePiece(fieldName,newFieldData,idUpdate);
				if (!testUpdate) {
					Ihm.afficherClient("Erreur de modification");
				} else {
					Ihm.afficherClient(itemName + " modifiée avec succès (id :" + idUpdate + ")");
				}
				break;
			case 4:
				Ihm.afficherClient("Veuillez saisir l'id du " + itemName);
				int idRecherche = IHM.inputInt();
				Piece modeleRecherche = dao.findPieceById(idRecherche);
				if (modeleRecherche == null) {
					Ihm.afficherClient("La " + itemName + " n'existe pas");
				} else if (modeleRecherche != null) {
					Ihm.afficherClient(itemName + " trouvé avec succès\n" + modeleRecherche);
				}
				break;
			case 5:
				List<Piece> liste = dao.listPieces();
				for (Piece item : liste) {
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
