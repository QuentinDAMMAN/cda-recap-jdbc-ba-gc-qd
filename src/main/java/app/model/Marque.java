package app.model;

public class Marque {
	private int id_marque;
	private String libelle;

	public Marque(int id_marque, String libelle) {
		this.id_marque = id_marque;
		this.libelle = libelle;
	}

	public Marque(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getId_marque() {
		return id_marque;
	}

	public void setId_marque(int id_marque) {
		this.id_marque = id_marque;
	}

	@Override
	public String toString() {
		return "Marque [id_marque=" + id_marque + ", libelle=" + libelle + "]";
	}
}
