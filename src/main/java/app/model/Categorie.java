package app.model;

public class Categorie {
	private int id_categorie;
	private String libelle;

	public Categorie(int id_categorie, String libelle) {
		this.id_categorie = id_categorie;
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	@Override
	public String toString() {
		return "Marque [id_marque=" + id_categorie + ", libelle=" + libelle + "]";
	}
}
