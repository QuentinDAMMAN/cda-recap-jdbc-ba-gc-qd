package app.dao;

import java.util.List;

import app.model.Vehicule;

public interface VehiculeDao {
	Vehicule createVehicule(Vehicule vehicule);
	boolean deleteVehicule(int id);
	boolean updateVehicule(String champ, int value, int id);
	Vehicule findVehiculeById(int id);
	List<Vehicule> listVehicule();
}
