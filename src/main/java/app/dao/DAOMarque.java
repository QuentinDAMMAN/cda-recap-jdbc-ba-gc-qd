package app.dao;

import java.util.List;
import app.model.Marque;

public interface DAOMarque {
	Marque createMarque(Marque marque);
	boolean deleteMarque(int id);
	boolean updateMarque(Marque marque);
	Marque findMarqueById(int id);
	List<Marque> listMarques();
}
