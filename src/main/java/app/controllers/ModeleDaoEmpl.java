package app.controllers;

import app.dao.ModeleDao;
import app.model.Modele;

import java.util.List;

public class ModeleDaoEmpl  implements ModeleDao {
    @Override
    public Modele createModele(Modele modele) {
        return null;
    }

    @Override
    public boolean deleteModele(int id) {
        return false;
    }

    @Override
    public boolean updateModele(Modele modele) {
        return false;
    }

    @Override
    public Modele findModeleeById(int id) {
        return null;
    }

    @Override
    public List<Modele> listModeles() {
        return null;
    }
}
