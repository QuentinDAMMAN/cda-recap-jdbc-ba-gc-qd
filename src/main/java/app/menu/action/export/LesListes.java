package app.menu.action.export;

import app.action.AllListExportHTML;
import app.action.CategoriesExportHTML;
import app.action.MarquesExportHTML;
import app.action.ModelesExportHTML;
import app.action.PiecesExportHTML;
import app.action.ReferencesExportHTML;
import app.action.ReferencesVehiculesExportHTML;
import app.action.TransactionsExportHTML;
import app.action.VehiculesExportHTML;

public interface LesListes {
	public static final Retour RETOUR = new Retour();
	public static final ListPieceDispoVoitureRecente LIST_PIECE_DISPO_VOITURE_RECENTE = new ListPieceDispoVoitureRecente();
	public static final ActionIntrouvable ACTION_INTROUVABLE = new ActionIntrouvable();
	public static final ListChiffreAffaire LIST_CHIFFRE_AFFAIRE = new ListChiffreAffaire();
	public static final ModelesExportHTML MODELES = new ModelesExportHTML();
	public static final TransactionsExportHTML TRANSACTIONS = new TransactionsExportHTML();;
	public static final MarquesExportHTML MARQUES = new MarquesExportHTML();
	public static final PiecesExportHTML PIECES = new PiecesExportHTML();
	public static final CategoriesExportHTML CATEGORIES = new CategoriesExportHTML();
	public static final VehiculesExportHTML VEHICULES = new VehiculesExportHTML();
	public static final ReferencesExportHTML REFERENCES = new ReferencesExportHTML();
	public static final ReferencesVehiculesExportHTML REFERENCES_VEHICULES = new ReferencesVehiculesExportHTML();
	public static final AllListExportHTML ALL_LIST = new AllListExportHTML();

}
