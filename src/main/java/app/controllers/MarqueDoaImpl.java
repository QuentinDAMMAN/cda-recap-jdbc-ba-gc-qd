package app.controllers;

import app.dao.MarqueDao;
import app.model.Marque;

import java.util.List;

public class MarqueDoaImpl  implements MarqueDao {
    @Override
    public Marque createMarque(Marque marque) {
        return null;
    }

    @Override
    public boolean deleteMarque(int id) {
        return false;
    }

    @Override
    public boolean updateMarque(Marque marque) {
        return false;
    }

    @Override
    public Marque findMarqueById(int id) {
        return null;
    }

    @Override
    public List<Marque> listMarques() {
        return null;
    }
}
