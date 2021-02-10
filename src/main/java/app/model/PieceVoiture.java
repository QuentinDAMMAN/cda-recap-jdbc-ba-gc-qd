package app.model;

public class PieceVoiture {
private int reference;
private int id_vehicule;
public PieceVoiture(int reference, int id_vehicule) {
	super();
	this.reference = reference;
	this.id_vehicule = id_vehicule;
}
public int getReference() {
	return reference;
}
public void setReference(int reference) {
	this.reference = reference;
}
public int getId_vehicule() {
	return id_vehicule;
}
public void setId_vehicule(int id_vehicule) {
	this.id_vehicule = id_vehicule;
}
@Override
public String toString() {
	return "PieceVoiture [reference=" + reference + ", id_vehicule=" + id_vehicule + "]";
}
}