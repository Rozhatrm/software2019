package no.hiof.rozhatrm.SEg20.controller;

import com.sun.tools.javac.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import no.hiof.rozhatrm.SEg20.MainJavaFX;
import no.hiof.rozhatrm.SEg20.data.DataHandler;
import no.hiof.rozhatrm.SEg20.model.Arrangement;
import no.hiof.rozhatrm.SEg20.model.Deltager;

import java.time.LocalDate;
import java.util.Optional;

public class ArrangementController {

    @FXML
    private ListView<Arrangement> arrangementListView;
    @FXML
    private ListView<Deltager> deltagerListView;
    @FXML
    private Text tittelText;
    @FXML
    private TextField datoTextField, klokkeslettTextField;
    @FXML
    private TextArea beskrivelseTextArea;
    @FXML
    private Button redigerButton, meldPaaButton ;



    @FXML
    private void initialize() {
        arrangementListView.setItems(DataHandler.hentArrangementData());
        deltagerListView.setItems(DataHandler.hentDeltagerData());

        redigerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Henter ut en referanse til filmen som er valgt, og som skal redigeres
                Arrangement arrangementSomSkalRedigeres = arrangementListView.getSelectionModel().getSelectedItem();

                if (arrangementSomSkalRedigeres != null) {
                    // Viser det nye vinduet, og sender filmen som skal redigeres, får tilbake true/false avhengig av hvordan det gikk
                    boolean arrangementRedigert = MainJavaFX.getInstance().visRedigerArrangementDialog(arrangementSomSkalRedigeres);

                    if (arrangementRedigert) {
                        // (liten hack for å få ListViewet til å oppdatere seg med ny tittel)
                        // Setter første elementet til valgt, før den som ble redigert
                        arrangementListView.getSelectionModel().selectFirst();
                        // Setter filmen som er redigert til å være valgt
                        arrangementListView.getSelectionModel().select(arrangementSomSkalRedigeres);
                        oppdaterArrangementDetaljer(arrangementSomSkalRedigeres);
                    }
                }
                // Hvis ikke viser vi en liten alert melding om at arrangement må velges
                else {
                    Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
                    alertDialog.setTitle("Ingen arrangement valgt");
                    alertDialog.setHeaderText("Vennligst velg en arrangement");
                    alertDialog.showAndWait();
                }
            }
        });

        // Legger til lytter på hvert element i ListView'et, så vi får beskjed når noe er endret, eller valgt
        arrangementListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Arrangement>() {
            @Override
            public void changed(ObservableValue<? extends Arrangement> observableValue, Arrangement gammelFilm, Arrangement nyFilm) {
                // Oppdaterer den detaljerte visningen av et arrangement
                oppdaterArrangementDetaljer(nyFilm);
            }
        });

        // Velger første element i lista, slik at det er valgt når vi starter programmet
        arrangementListView.getSelectionModel().selectFirst();



        meldPaaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Deltager deltagerSomSkalRedigeres = deltagerListView.getSelectionModel().getSelectedItem();

                if(deltagerSomSkalRedigeres != null) {
                    boolean deltagerRedigert = MainJavaFX.getInstance().meldPaaVisning(deltagerSomSkalRedigeres);

                    if (deltagerRedigert) {
                        deltagerListView.getSelectionModel().selectFirst();
                        deltagerListView.getSelectionModel().select(deltagerSomSkalRedigeres);
                        oppdaterDeltagerDetaljer(deltagerSomSkalRedigeres);
                    }
                }
                else {
                    Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
                    alertDialog.setTitle("Ingen arrangement valgt");
                    alertDialog.setHeaderText("Vennligst velg en arrangement");
                    alertDialog.showAndWait();
                }
            }
        });

        deltagerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Deltager>() {
            @Override
            public void changed(ObservableValue<? extends Deltager> observableValue, Deltager gammelDeltager, Deltager nyDeltager) {
                oppdaterDeltagerDetaljer(nyDeltager);
            }
        });

        deltagerListView.getSelectionModel().selectFirst();

    }

    private void oppdaterDeltagerDetaljer (Deltager enDeltager) {

        if (enDeltager != null) {
            beskrivelseTextArea.setText(enDeltager.getFulltNavn());
        }
    }




    private void oppdaterArrangementDetaljer(Arrangement enArrangement) {

        if (enArrangement != null) {
            // Setter filmtittelen til titteltekstfeltet
            tittelText.setText(enArrangement.getTittel());
            // Setter beskrivelsen til beskrivelsetekstfeltet
            beskrivelseTextArea.setText(enArrangement.getBeskrivelse());

            datoTextField.setText(enArrangement.getDato() != LocalDate.MIN ? enArrangement.getDato().toString() : "");

            klokkeslettTextField.setText(enArrangement.getTidspunkt() > 0 ? enArrangement.getTidspunkt() + " " : "");
        }
    }

    public void nyButtonClicked(ActionEvent actionEvent) {

        Arrangement nyArrangement = new Arrangement();

        boolean nyArrangementLagetVellyket = MainJavaFX.getInstance().visRedigerArrangementDialog(nyArrangement);

        if (nyArrangementLagetVellyket) {

            DataHandler.hentArrangementData().add(nyArrangement);

            arrangementListView.getSelectionModel().select(nyArrangement);
        }
    }

    public void redigerDeltagerClicked(ActionEvent actionEvent){

        Deltager nyDeltager = new Deltager();

        boolean nyDeltagerVellyket = MainJavaFX.getInstance().meldPaaVisning(nyDeltager);

        if (nyDeltagerVellyket) {
            DataHandler.hentDeltagerData().add(nyDeltager);

            deltagerListView.getSelectionModel().select(nyDeltager);
        }
    }



    @FXML
    public void slettButtonClicked(ActionEvent actionEvent) {

        Arrangement enArrangement = arrangementListView.getSelectionModel().getSelectedItem();


        if (enArrangement != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slette arrangement");
            alert.setHeaderText(null);
            alert.setContentText("Er du sikker på at du ønsker å slette arrangementet " + enArrangement.getTittel() + "?");


            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(ButtonType.OK) == ButtonType.OK){

                tomArrangementInfo();

                arrangementListView.getSelectionModel().selectPrevious();

                DataHandler.hentArrangementData().remove(enArrangement);
            }
        }
    }

    private void tomArrangementInfo() {
        tittelText.setText("");
        beskrivelseTextArea.setText("");
        datoTextField.setText("");
        klokkeslettTextField.setText("");
    }
}
