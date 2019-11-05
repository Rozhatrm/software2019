package no.hiof.rozhatrm.SEg20.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.hiof.rozhatrm.SEg20.MainJavaFX;
import no.hiof.rozhatrm.SEg20.data.DataHandler;
import no.hiof.rozhatrm.SEg20.model.Arrangement;
import no.hiof.rozhatrm.SEg20.model.Deltager;

import java.time.LocalDate;
import java.util.Optional;

public class MeldPaaController {
    @FXML
    private ListView<Deltager> deltagerListView;
    @FXML
    private Text tittelText;
    @FXML
    private TextField utgivelsesdatoTextField, spilletidTextField;
    @FXML
    private TextArea beskrivelseTextArea;
    @FXML
    private Button redigerButton;

    @FXML
    private void initialize() {
        deltagerListView.setItems(DataHandler.hentDeltagerData());


        deltagerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Deltager>() {
            @Override
            public void changed(ObservableValue<? extends Deltager> observableValue, Deltager gammelDeltager, Deltager nyDeltager) {
                oppdaterDeltagerDetaljer(nyDeltager);
            }
        });

        deltagerListView.getSelectionModel().selectFirst();

    }

    private void oppdaterDeltagerDetaljer(Deltager enDeltager) {
        if(enDeltager != null) {
            beskrivelseTextArea.setText(enDeltager.getFulltNavn());
        }
    }
}
