package app.model;

import java.util.Date;

public class Transactions {
	private int id_transactions;
	private Date date_Vente;
	private Date date_Recuperation;
	private int reference;

	public Transactions(int id_transactions, Date date_Vente, Date date_Recuperation, int reference) {
		super();
		this.id_transactions = id_transactions;
		this.date_Vente = date_Vente;
		this.date_Recuperation = date_Recuperation;
		this.reference = reference;
	}

	public int getId_transactions() {
		return id_transactions;
	}

	public void setId_transactions(int id_transactions) {
		this.id_transactions = id_transactions;
	}

	public Date getDate_Vente() {
		return date_Vente;
	}

	public void setDate_Vente(Date date_Vente) {
		this.date_Vente = date_Vente;
	}

	public Date getDate_Recuperation() {
		return date_Recuperation;
	}

	public void setDate_Recuperation(Date date_Recuperation) {
		this.date_Recuperation = date_Recuperation;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "Transactions [id_transactions=" + id_transactions + ", date_Vente=" + date_Vente
				+ ", date_Recuperation=" + date_Recuperation + ", reference=" + reference + "]";
	}

}
