package views;

import controleur.Controleur;
import modele.Case;
import views.jfx.Connexion;

public interface FabriqueVues {

    ConnexionInterface buildConnexionView(Controleur c);
    JeuInterface buildJeuView(Controleur c);
    ResultatInterface buildResultatView(Controleur c);

}
