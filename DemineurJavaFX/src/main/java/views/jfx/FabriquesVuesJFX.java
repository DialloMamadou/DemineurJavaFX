package views.jfx;

import controleur.Controleur;
import javafx.stage.Stage;
import views.ConnexionInterface;
import views.FabriqueVues;
import views.JeuInterface;
import views.ResultatInterface;

public class FabriquesVuesJFX implements FabriqueVues {

    Stage primaryStage;

    public FabriquesVuesJFX(Stage primaryStage){
        this.primaryStage=primaryStage;
    }

    @Override
    public ConnexionInterface buildConnexionView(Controleur c) {
        return Connexion.creerInstance(c,primaryStage);
    }

    @Override
    public JeuInterface buildJeuView(Controleur c) {
        return Jeu.creerInstance(c,primaryStage);
    }

    @Override
    public ResultatInterface buildResultatView(Controleur c) {
        return Resultat.creerInstance(c,primaryStage);
    }
}
