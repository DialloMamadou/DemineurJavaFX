package views.jfx;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Case;
import modele.exceptions.BombeException;
import views.JeuInterface;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class Jeu implements JeuInterface{


    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    VBox topNiveau;

    @FXML
    private Label labelError;

    @FXML
    private GridPane demineur;

    private int taille;

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    private Stage primaryStage;

    private Button[][] mesBoutons;

    private void actualiserGrille(){
        for (int i=0;i<taille;i++){
            for (int j=0; j<taille;j++){
                if(monControleur.getCachee(i,j)){
                    mesBoutons[i][j].setText("?");
                }
                else{
                    mesBoutons[i][j].setText(Integer.toString(monControleur.getValeur(i,j)));
                }
            }
        }
    }

    private void decouvrir(int i, int j){
        try {
            if(monControleur.decouvrir(i, j)){
                monControleur.finJeu(true);
            }
            else{
                actualiserGrille();
            }
        }
        catch(BombeException e){
            monControleur.finJeu(false);
        }
    }

    private void creerGrille(Controleur c){
        taille=c.getGrilleLength();
        mesBoutons= new Button[taille][taille];
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                mesBoutons[i][j]=new Button();
                int finalJ = j;
                int finalI = i;
                mesBoutons[i][j].setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {decouvrir(finalI,finalJ);
                    }
                }));
                demineur.add(mesBoutons[i][j],i,j);
            }
        }
        actualiserGrille();
    }

    public static Jeu creerInstance(Controleur c, Stage primaryStage) {
        URL location = Jeu.class.getResource("/views/jfx/jeu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Jeu vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMonControleur(c);
        vue.creerGrille(c);
        return vue;
    }

    public void show(){
        primaryStage.setTitle("Jeu");
        primaryStage.setScene(new Scene(topNiveau,taille*30,taille*30));
        primaryStage.show();
        this.showMessageErreur("");
    }

    @Override
    public void showMessageErreur(String messageErreur) {
        this.labelError.setText(messageErreur);
    }

    public void hide(){
        primaryStage.hide();
    }

}
