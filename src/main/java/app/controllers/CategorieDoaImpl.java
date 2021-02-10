package app.controllers;

import app.dao.CategorieDao;
import app.model.Categorie;

import java.util.List;

public class CategorieDoaImpl implements CategorieDao {
    @Override
    public Categorie createCategorie(Categorie categorie) {
        return null;
    }

    @Override
    public boolean deleteCategorie(int id) {
        return false;
    }

    @Override
    public boolean updaCategorie(Categorie categorie) {
        return false;
    }

    @Override
    public Categorie findCategorieById(int id) {
        return null;
    }

    @Override
    public List<Categorie> listCategories() {
        return null;
    }
}
