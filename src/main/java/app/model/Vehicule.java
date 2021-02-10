package app.model;

public class Vehicule {
	private int id_vehicule;
	private int annee;
	private int id_marque;
	private int id_modele;

	public Vehicule(int id_vehicule, int annee, int id_marque, int id_modele) {
		super();
		this.id_vehicule = id_vehicule;
		this.annee = annee;
		this.id_marque = id_marque;
		this.id_modele = id_modele;
	}

	public int getId_vehicule() {
		return id_vehicule;
	}

	public void setId_vehicule(int id_vehicule) {
		this.id_vehicule = id_vehicule;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getId_marque() {
		return id_marque;
	}

	public void setId_marque(int id_marque) {
		this.id_marque = id_marque;
	}

	public int getId_modele() {
		return id_modele;
	}

	public void setId_modele(int id_modele) {
		this.id_modele = id_modele;
	}

	@Override
	public String toString() {
		return "Vehicule [id_vehicule=" + id_vehicule + ", annee=" + annee + ", id_marque=" + id_marque + ", id_modele="
				+ id_modele + "]";
	}
}
