package app.dao;

import java.util.List;

import app.model.ReferenceVehicule;

public interface RefVehiculeDoa {
    ReferenceVehicule createReVehicule(ReferenceVehicule referenceVehicule);
    boolean deleteRefVehicule(String id);
    boolean updateRefVehicule(String referenceVehicule, String id);
    ReferenceVehicule findReVehiculeById(String id);
    List<ReferenceVehicule> listRefVehicule();

}
