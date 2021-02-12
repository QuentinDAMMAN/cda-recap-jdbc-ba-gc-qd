package app.dao;

import java.util.List;
import app.model.Marque;

public interface MarqueDao {
	Marque createMarque(Marque marque);
	boolean deleteMarque(int id);
	boolean updateMarque(String champ, String value, int id);
	Marque findMarqueById(int id);
	List<Marque> listMarques();
}
