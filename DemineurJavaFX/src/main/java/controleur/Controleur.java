package controleur;

import javafx.stage.Stage;
import modele.Case;
import modele.GestionDemineur;
import modele.exceptions.BombeException;
import modele.exceptions.ExceptionLoginDejaPris;
import views.FabriqueVues;
import views.jfx.Connexion;
import views.jfx.Jeu;
import views.jfx.Resultat;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class Controleur {

    private GestionDemineur facade=new GestionDemineur();

    private Connexion vueConnexion;
    private Jeu vueJeu;
    private Resultat vueResultat;
    private FabriqueVues fabriqueVues;
    private String utilisateur;
    private Case[][] grille;


    public Controleur(FabriqueVues fabriqueVues) {

        this.fabriqueVues=fabriqueVues;
        this.vueConnexion = (Connexion) this.fabriqueVues.buildConnexionView(this);
        this.vueConnexion.show();
    }
    public boolean getCachee(int i,int j){
        return grille[i][j].getCachee();
    }
    public int getValeur(int i,int j){
        return grille[i][j].getValeur();
    }

    public int getGrilleLength(){
        return grille.length;
    }

    public boolean decouvrir(int i,int j)throws BombeException{
        return facade.decouvrir(utilisateur,i,j);
    }

    public void connexion(String pseudo,int taille, int pourcentage){
        try {
            facade.connexion(pseudo);
            utilisateur=pseudo;
            vueConnexion.hide();
            facade.associerNouvelleGrille(utilisateur,taille,pourcentage);
            grille=facade.getPlateau(utilisateur).getMonPlateau();
            vueJeu= (Jeu) this.fabriqueVues.buildJeuView(this);
            vueJeu.show();
        } catch (ExceptionLoginDejaPris e) {
            vueConnexion.showMessageErreur("Utilisateur déjà connecté.");
        }
    }

    public void finJeu(boolean resultat){
        vueJeu.hide();
        vueResultat=(Resultat) this.fabriqueVues.buildResultatView(this);
        vueResultat.setMessageResultat(resultat);
        vueResultat.show();
    }

    public void deconnexion(){
        facade.deconnexion(utilisateur);
        vueResultat.hide();
        this.vueConnexion = (Connexion) this.fabriqueVues.buildConnexionView(this);
        this.vueConnexion.show();
    }

    public void rejouer(){
        vueResultat.hide();
        facade.associerNouvelleGrille(utilisateur,10,5);
        grille=facade.getPlateau(utilisateur).getMonPlateau();
        vueJeu= (Jeu) this.fabriqueVues.buildJeuView(this);
        vueJeu.show();
    }

}
