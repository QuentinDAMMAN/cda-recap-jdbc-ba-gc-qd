package app;

import static app.menu.saisie.Ihm.IHM;

import java.util.TreeMap;

import app.menu.action.Action;
import app.menu.action.LesActions;
import app.menu.saisie.Ihm;
import app.sql.SQLConnection;

public class Main {

	public static void main(String[] args) {
		    SQLConnection.connect();
			TreeMap<Integer,Action> actions = new TreeMap<>();
			newAction(actions, LesActions.QUITTER);
			newAction(actions, LesActions.ACTION_MARQUE);
			newAction(actions, LesActions.ACTION_MODELE);
			newAction(actions, LesActions.ACTION_CATEGORIE);
			newAction(actions, LesActions.ACTION_REFERENCE);
			newAction(actions, LesActions.ACTION_VEHICULE);
			newAction(actions, LesActions.ACTION_PIECE);
			newAction(actions, LesActions.ACTION_AFFICHER_CHIFFRE_AFFAIRE);
			newAction(actions, LesActions.ACTION_PIECE_DISPO_VOITURE_RECENTE);
			newAction(actions, LesActions.ACTION_EXPORT);
			
			boolean quit;
			int vActionSaisie;
			Ihm.afficherClient("Bienvenue sur votre interface de gestion des pièces détachées.");
			do {
				Ihm.afficherClient("********************\nSaisissez une action");
				for (Action action : actions.values()) {
					Ihm.afficherClient(action.getId()+ " - " +action.getDescription());
				}
				vActionSaisie = IHM.inputInt();
				quit= actions.getOrDefault(vActionSaisie,LesActions.ACTION_INTROUVABLE).executer();
			} while(quit);
			
			
	}
	
	public static void newAction(TreeMap<Integer, Action> actions, Action action) {
		actions.put(action.getId(), action);

            
    }
}
