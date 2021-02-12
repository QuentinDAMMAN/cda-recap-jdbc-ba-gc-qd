package app.menu.action;

import app.menu.action.export.ActionExportExcel;

public interface LesActions {
	public static final Quitter QUITTER = new Quitter();
	public static final ActionIntrouvable ACTION_INTROUVABLE = new ActionIntrouvable();
	public static final ActionMarque ACTION_MARQUE = new ActionMarque();
	public static final ActionModele ACTION_MODELE = new ActionModele();
	public static final ActionCategorie ACTION_CATEGORIE = new ActionCategorie();
	public static final ActionReference ACTION_REFERENCE = new ActionReference();
	public static final ActionVehicule ACTION_VEHICULE = new ActionVehicule();
	public static final ActionPiece ACTION_PIECE = new ActionPiece();
	public static final ActionAfficherChiffreAffaire ACTION_AFFICHER_CHIFFRE_AFFAIRE = new ActionAfficherChiffreAffaire();
	public static final ActionPieceDispoVoitureRecente ACTION_PIECE_DISPO_VOITURE_RECENTE = new ActionPieceDispoVoitureRecente();
	public static final ActionExport ACTION_EXPORT = new ActionExport();
	public static final ActionExportExcel ACTION_EXPORT_EXCEL = new ActionExportExcel();
	public static final Retour RETOUR = new Retour();
	
}
