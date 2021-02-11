package app.dao;

import app.model.Piece;
import app.model.ReferenceVehicule;

import java.util.List;

public interface RefVehiculeDoa {
    ReferenceVehicule createReVehicule(ReferenceVehicule referenceVehicule);
    boolean deleteRefVehicule(String id);
    boolean updateRefVehicule(String referenceVehicule, String id);
    ReferenceVehicule findReVehiculeById(String id);
    List<ReferenceVehicule> listRefVehicule();

}
