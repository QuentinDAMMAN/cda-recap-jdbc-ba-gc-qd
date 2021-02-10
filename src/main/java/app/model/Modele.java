package app.model;

public class Modele {
	private int id_modele;
	private String libelle;
	private int id_marque;

	public Modele(int id_modele, String libelle, int id_marque) {
		this.id_modele = id_modele;
		this.libelle = libelle;
		this.id_marque = id_marque;
	}

	public int getId_modele() {
		return id_modele;
	}

	public void setId_modele(int id_modele) {
		this.id_modele = id_modele;
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
		return "Modele [id_modele=" + id_modele + ", libelle=" + libelle + ", id_marque=" + id_marque + "]";
	}
}
