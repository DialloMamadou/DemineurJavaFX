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

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class Connexion implements ConnexionInterface{


    private Controleur monControleur;

    public void setMonControleur(Controleur monControleur) {
        this.monControleur = monControleur;
    }

    @FXML
    VBox topNiveau;

    @FXML
    private Button boutonConnexion;

    @FXML
    private Label labelError;

    @FXML
    private TextField champPseudo;

    @FXML
    private TextField champTaille;

    @FXML
    private TextField champPourenctage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private Stage primaryStage;

    public static Connexion creerInstance(Controleur c,Stage primaryStage) {
        URL location = Connexion.class.getResource("/views/jfx/connexion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connexion vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMonControleur(c);
        return vue;
    }

    public void show(){
        primaryStage.setTitle("Connexion");
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

    public void connexion(ActionEvent actionEvent) {
        try{
            this.monControleur.connexion(this.champPseudo.getText(),Integer.parseInt(this.champTaille.getText()),Integer.parseInt(this.champPourenctage.getText()));
        }
        catch(Exception e){
            this.monControleur.connexion(this.champPseudo.getText(),6,6);
        }
    }
}
