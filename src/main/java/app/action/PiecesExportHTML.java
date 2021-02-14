package app.action;

import java.util.List;

import app.controllers.PieceDaoImpl;
import app.export.HtmlExport;
import app.menu.action.export.GetList;
import app.model.Piece;

public class PiecesExportHTML extends GetList {

	private static final int ID = 4;
	private static final String DESC = "Listes des pieces";
	private static final String NOM = "LISTE_PIECES";

	public PiecesExportHTML() {
		super(ID, DESC, NOM);
	}

	public static void pieces() {
		// ************************** pieces *********************************
		PieceDaoImpl dao = new PieceDaoImpl();
		List<Piece> liste = dao.listPieces();
		HtmlExport.exportHtml(liste, "pieces", "piece", "pieces");
	}

	@Override
	public List<String[]> executer() {
		pieces();
		return null;
	}
}
