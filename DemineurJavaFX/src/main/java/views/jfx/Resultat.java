package views.jfx;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import views.ConnexionInterface;
import views.ResultatInterface;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class Resultat implements ResultatInterface{


    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    VBox topNiveau;

    @FXML
    Label labelResultat;

    @FXML
    Button boutonRejouer;

    @FXML
    Button boutonDeconnexion;

    @FXML
    private Label labelError;


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private Stage primaryStage;

    public void rejouer(){
        monControleur.rejouer();
    }

    public void deconnexion(){
        monControleur.deconnexion();
    }

    public void setMessageResultat(boolean resultat){
        if(resultat)
            labelResultat.setText("Bravo, vous avez isolé toutes les mines !");
        else labelResultat.setText("Aïe ! Vous avez marché sur une mine !");
    }

    public static Resultat creerInstance(Controleur c, Stage primaryStage) {
        URL location = Resultat.class.getResource("/views/jfx/resultat.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Resultat vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMonControleur(c);
        return vue;
    }

    public void show(){
        primaryStage.setTitle("Resultat");
        primaryStage.setScene(new Scene(topNiveau,300,275));
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
