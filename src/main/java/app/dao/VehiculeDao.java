package app.dao;

import java.util.List;

import app.model.Vehicule;

public interface VehiculeDao {
	Vehicule createVehicule(Vehicule vehicule);
	boolean deleteVehicule(int id);
	boolean updateVehicule(Vehicule vehicule);
	Vehicule findVehiculeById(int id);
	List<Vehicule> listVehicule();
}
