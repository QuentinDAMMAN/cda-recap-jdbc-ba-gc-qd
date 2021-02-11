package app.dao;

import java.util.List;

import app.model.Transactions;

public interface TransactionsDao {
	Transactions createTransactions(Transactions transactions);
	boolean deleteTransactions(int id);
	boolean updateTransactions(Transactions transactions);
	Transactions findPieceById(int id);
	List<Transactions> listTransactions();
}
