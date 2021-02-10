package app.model;

public class Piece {
	private int reference;
	private String Libelle;
	private float prix;
	private int stock;
	private int id_vehicule;
	private int id_categorie;

	public Piece(int reference, String libelle, float prix, int stock, int id_vehicule, int id_categorie) {
		super();
		this.reference = reference;
		Libelle = libelle;
		this.prix = prix;
		this.stock = stock;
		this.id_vehicule = id_vehicule;
		this.id_categorie = id_categorie;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public String getLibelle() {
		return Libelle;
	}

	public void setLibelle(String libelle) {
		Libelle = libelle;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getId_vehicule() {
		return id_vehicule;
	}

	public void setId_vehicule(int id_vehicule) {
		this.id_vehicule = id_vehicule;
	}

	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	@Override
	public String toString() {
		return "Piece [reference=" + reference + ", Libelle=" + Libelle + ", prix=" + prix + ", stock=" + stock
				+ ", id_vehicule=" + id_vehicule + ", id_categorie=" + id_categorie + "]";
	}
}
