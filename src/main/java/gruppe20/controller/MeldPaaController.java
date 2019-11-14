package gruppe20.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

import gruppe20.model.Deltager;

public class MeldPaaController {

    // Alle feltvariablene for komponenter definert i tilhørende .fxml
    @FXML
    private TextArea fornavnTextArea, etternavnTextArea;
    @FXML
    private DatePicker fodselsdatoPicker;
    @FXML
    private Label feilmeldingLabel;
    @FXML
    private Button okButton;

    // Feltvariabler som tilhører RedigerArrangementController, som ikke finnes i FXML fil
    private Stage dialogStage;
    // Deltager vi skal redigere
    private Deltager deltagerSomRedigeres;
    private boolean okClicked = false;

    // Metode initialize som blir kalt etter at den tilhørende fxml'en er lastet inn
    @FXML
    private void initialize() {
        // Setter at okButton skal være "hovedknappen", dette gjør at den blir "kalt" når vi trykker ENTER
        okButton.setDefaultButton(true);
    }

    public void setDeltagerSomSkalRedigeres(Deltager deltagerSomRedigeres) {
        // Setter hvilken arrangement som skal redigeres
        this.deltagerSomRedigeres = deltagerSomRedigeres;

        // Sjekker om arrangement har en verdi, eller er null,
        // har den en verdi skal den redigeres og vi fyller inn data fra den
        if (deltagerSomRedigeres != null) {

            fornavnTextArea.setText(deltagerSomRedigeres.getFornavn());
            etternavnTextArea.setText(deltagerSomRedigeres.getEtternavn());
            if (!deltagerSomRedigeres.getFodselsDato().equals(LocalDate.MIN))
                fodselsdatoPicker.setValue(deltagerSomRedigeres.getFodselsDato());

        }
    }

    @FXML
    private void okValgt() {
        // Sjekker om inputfeltene er gyldige
        if (sjekkOmInputErGyldig()) {
            // Hvis de er det, fyller vi opp arrangementobjektet vårt med den nye dataen fra feltene
            deltagerSomRedigeres.setFornavn(fornavnTextArea.getText());
            deltagerSomRedigeres.setEtternavn(etternavnTextArea.getText());
            if (fodselsdatoPicker.getValue() != null)
                deltagerSomRedigeres.setFodselsDato(fodselsdatoPicker.getValue());


            // Setter at vi avsluttet ved å trykke OK
            okClicked = true;

            // Henter ut en referanse til Stage (vinduet) ved hjelp av en av komponentene vi har i grensesnittet
            dialogStage = (Stage)okButton.getScene().getWindow();
            // Lukker vinduet
            dialogStage.close();
        }
    }

    @FXML
    private void avbrytValgt() {
        // Henter ut en referanse til Stage (vinduet) ved hjelp av en av komponentene vi har i grensesnittet
        dialogStage = (Stage)okButton.getScene().getWindow();
        // Lukker vinduet uten å gjøre noe mer (okClicked er fortsatt false)
        dialogStage.close();
    }


    public boolean erOkValgt() {
        // Mulighet til å hente ut om vi avsluttet ved hjelp av OK eller ikke
        return okClicked;
    }

    public boolean sjekkOmInputErGyldig() {
        // Lager en string vi fyller opp med feilmeldinger for visning til brukeren
        String feilmelding = "";

        // Sjekk om deltager har fornavn
        if (fornavnTextArea.getText() == null || fornavnTextArea.getText().length() == 0){
            feilmelding += "Fyll inn fornavn! / ";
        }
        // Sjekk om fornavn er for kort
        if (fornavnTextArea.getText().length() < 3 ) {
            feilmelding += "Fornavn er for kort\n";
        }
        //Sjekk om fornavn inneholder kun bokstaver
        if (!fornavnTextArea.getText().matches("^[a-zA-Z]*$")) {
            feilmelding += "Fornavn kan kun inneholde bokstaver A-Z\n";
        }

        // Sjekk om deltager har etternavn
        if (etternavnTextArea.getText() == null || etternavnTextArea.getText().length() == 0) {
            feilmelding += "Fyll inn etternavn! / ";
        }
        // Sjekk om etternavn er for kort
        if(etternavnTextArea.getText().length() <3){
            feilmelding +="Etternavn er for kort\n";
        }
        // Sjekk om etternavn inneholder kun bokstaver
        if (!etternavnTextArea.getText().matches("^[a-zA-Z]*$")) {
            feilmelding += "Etternavn kan kun inneholde bokstaver A-Z\n";
        }
        // Sjekk om etternavn ikke er et stygr ord
        if (etternavnTextArea.getText().matches("^.*?(Faen|Fitte|Fuck).*$")){
            feilmelding += "Etternavn kan ikke inneholde stygge ord\n";
        }

        // Sjkke om deltager har fodseldato
        if (fodselsdatoPicker.getValue() == null) {
            feilmelding += "Fødselsdato mangler";
        }

        // Sjekker om vi har noen feilmelding eller ikke
        if (feilmelding.length() == 0) {
            // Returner true, som i at vi ikke har noen feilmeldinger og all input er gyldig
            return true;
        }
        else {
            // Skriv info til feilmeldingslabel som blir vist til brukeren
            feilmeldingLabel.setText("Vennligs rett følgende feil:\n" + feilmelding);
            // Returner false, som sier at input ikke er gyldig
            return false;
        }
    }

}
