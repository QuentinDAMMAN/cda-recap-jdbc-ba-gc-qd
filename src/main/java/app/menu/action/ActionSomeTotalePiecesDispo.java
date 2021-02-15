package app.menu.action;

import app.action.ChiffreDAffaire;
import app.action.SomeTotalePiecesDisponible;
import app.menu.action.Action;
import app.menu.saisie.Ihm;

import java.util.List;

public class ActionSomeTotalePiecesDispo extends Action {
    private static final int ID = 11;
    private static final String DESC = "le nombre et la somme totale des pièces disponibles(pas encore vendues)";

    ActionSomeTotalePiecesDispo() {
        super(ID, DESC);
    }

    @Override
    public boolean executer() {
       SomeTotalePiecesDisponible.exec();

        return Boolean.TRUE;
    }
}
