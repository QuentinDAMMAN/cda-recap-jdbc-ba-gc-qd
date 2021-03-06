package app.model;

public class Reference {
	private String id_reference;
	private String libelle;
	private double prix;
	private int id_categorie;

	public Reference(String id_reference, String libelle, double prix, int id_categorie) {
		super();
		this.id_reference = id_reference;
		this.libelle = libelle;
		this.prix = prix;
		this.id_categorie = id_categorie;
	}

	public Reference(String libelle, double prix, int id_categorie) {
		super();
		this.libelle = libelle;
		this.prix = prix;
		this.id_categorie = id_categorie;
	}

	public String getId_reference() {
		return id_reference;
	}

	public void setId_reference(String id_reference) {
		this.id_reference = id_reference;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	@Override
	public String toString() {
		return "Reference [id_reference=" + id_reference + ", libelle=" + libelle + ", prix=" + prix + ", id_categorie="
				+ id_categorie + "]";
	}
}
