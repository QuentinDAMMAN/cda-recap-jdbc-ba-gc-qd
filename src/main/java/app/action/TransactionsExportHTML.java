package app.action;

import java.util.List;

import app.controllers.TransactionsDaoImpl;
import app.export.HtmlExport;
import app.menu.action.export.GetList;
import app.model.Transactions;

public class TransactionsExportHTML extends GetList{
	
	private static final int ID = 2;
	private static final String DESC = "Listes des transactions";
	private static final String NOM = "LISTE_TRANSACTIONS";

	public TransactionsExportHTML() {
		super(ID, DESC, NOM);
	}
	
	public static void transactions() {
		// ************************** transactions *********************************
		TransactionsDaoImpl dao = new TransactionsDaoImpl();
		List<Transactions> liste = dao.listTransactions();
		HtmlExport.exportHtml(liste, "transactions", "Transaction", "transactions");
	}

	@Override
	public List<String[]> executer() {
		transactions();
		return null;
	}

}
