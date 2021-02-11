package app.model;

public class ReferenceVehicule {
private String Id_Rererence;
private int id_vehicule;

	public ReferenceVehicule(String id_Rererence, int id_vehicule) {
		Id_Rererence = id_Rererence;
		this.id_vehicule = id_vehicule;
	}

	public String getId_Rererence() {
		return Id_Rererence;
	}

	public void setId_Rererence(String id_Rererence) {
		Id_Rererence = id_Rererence;
	}

	public int getId_vehicule() {
		return id_vehicule;
	}

	public void setId_vehicule(int id_vehicule) {
		this.id_vehicule = id_vehicule;
	}

	@Override
	public String toString() {
		return "ReferenceVehicule{" +
				"Id_Rererence='" + Id_Rererence + '\'' +
				", id_vehicule=" + id_vehicule +
				'}';
	}
}
