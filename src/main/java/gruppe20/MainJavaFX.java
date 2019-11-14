package gruppe20;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import gruppe20.controller.RedigerArrangementController;
import gruppe20.controller.MeldPaaController;
import gruppe20.model.Arrangement;
import gruppe20.model.Deltager;

import java.io.File;
import java.io.IOException;


public class MainJavaFX extends Application {


    private static MainJavaFX minApplikasjon;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        // Setter den statiske minApplikasjon objektet til vår egen instans, slik at den blir tilgjengelig fra alle andre klasser
        minApplikasjon = this;
        System.out.println(new File("MainJavaFX.java").getAbsolutePath());
        try{
            this.primaryStage = primaryStage;

            primaryStage.setTitle("Arrangementer");

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("view/Arrangementer.fxml"));

            Parent arrangementOversiktLayout = fxmlLoader.load();

            Scene hovedScene = new Scene(arrangementOversiktLayout, 1000, 600);

            primaryStage.setScene(hovedScene);

            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean visRedigerArrangementDialog(Arrangement arrangementSomSkalRedigeres) {
        try {
            // Oppretter og instansierer fxmlLoader
            FXMLLoader fxmlLoader = new FXMLLoader();

            // Setter hvilken FXML fil vi skal laste inn
            fxmlLoader.setLocation(getClass().getResource("view/RedigerArrangementDialog.fxml"));
            // Laster inn FXML innhold og legger det i et parent objekt
            Parent dialogLayout = fxmlLoader.load();

            // Oppretter og instansierer vinduet
            Stage dialogStage = new Stage();
            // Setter tittelen
            dialogStage.setTitle("Rediger Arrangement");
            // Setter modality til WINDOW_MODAL, dette gjør at vinduet må lukkes før man kan gjøre noe mer i "hovedvinduet"
            dialogStage.initModality(Modality.WINDOW_MODAL);
            // Setter eier til å være hovedvinduet ("hovedstagen")
            dialogStage.initOwner(primaryStage);

            // Lager en ny scene og legger til layoutet fra fxml-fila
            Scene dialogScene = new Scene(dialogLayout);
            // Legger scenen til vinduet
            dialogStage.setScene(dialogScene);

            // Henter ut kontrolleren som er knyttet til fxml'en
            RedigerArrangementController redigerController = fxmlLoader.getController();
            // Setter hvilken arrangement som skal redigeres
            redigerController.setArrangementSomSkalRedigeres(arrangementSomSkalRedigeres);

            // Viser dialogvinduet, og venter med å gå videre til det er lukket
            dialogStage.showAndWait();

            // Dialogvinduet er blitt lukket, og vi undersøker hvordan vi ble avsluttet (om OK ble valgt, eller om vi avsluttet på annnen måte)
            return redigerController.erOkValgt();
        }
        catch (IOException | IllegalStateException exception) {
            visAlert(exception.getMessage());
            exception.printStackTrace();
            return false;
        }
    }


    public boolean meldPaaVisning(Deltager deltagerSomSkalRedigeres){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("view/MeldPaaDeltager.fxml"));
            Parent dialogLayout = fxmlLoader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Meld paa deltager");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene dialogScene = new Scene(dialogLayout, 600, 400);
            dialogStage.setScene(dialogScene);

            MeldPaaController meldPaaController = fxmlLoader.getController();
            meldPaaController.setDeltagerSomSkalRedigeres(deltagerSomSkalRedigeres);

            dialogStage.showAndWait();

            return meldPaaController.erOkValgt();
        }
        catch (IOException ioe){
            visAlert("Feil");
            return false;
        }
    }

    private void visAlert(String melding) {
        Alert newAlert = new Alert(Alert.AlertType.ERROR);
        newAlert.setTitle("Feil");
        newAlert.setHeaderText(null);
        newAlert.setContentText("Noe gikk feil! " + melding);

        newAlert.showAndWait();
    }

    public static MainJavaFX getInstance() {
        return minApplikasjon;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
