package no.hiof.rozhatrm.SEg20.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import no.hiof.rozhatrm.SEg20.model.Arrangement;
import no.hiof.rozhatrm.SEg20.model.Deltager;

import java.time.LocalDate;

public class DataHandler {
    private final static ObservableList<Arrangement> arrangementListe = FXCollections.observableArrayList();

    public static ObservableList<Arrangement> hentArrangementData() {
        if (arrangementListe.isEmpty())
            genererArrangementData();
        return arrangementListe;
    }

    private static void genererArrangementData() {
        arrangementListe.add(new Arrangement("5km løping",
                "Vi gleder oss! Kom og delta på årets tøffeste løp! Vinneren stikker av med 200 000 kr.\n\nFredrikstad sentrum, Stor gata 10" ,
                1430,
                LocalDate.of(2019, 12, 27)));
        arrangementListe.add(new Arrangement("500m Svømming",
                "Endelig nærmer vi oss den store dagen. Bli med og delta på Norges største svømme konkuranse.\n\nTunevannet, Sarpsborg",
                1240,
                LocalDate.of(2020, 1, 21)));
    }



    private final static ObservableList<Deltager> deltagerListe = FXCollections.observableArrayList();

    public static ObservableList<Deltager> hentDeltagerData() {
        if(deltagerListe.isEmpty())
            genererDeltagerData();
        return deltagerListe;
    }

    private static void genererDeltagerData() {
        deltagerListe.add(new Deltager("Ole", "Hanse", LocalDate.of(1997,10,10)));
        deltagerListe.add(new Deltager("Per", "Persen", LocalDate.of(1999,9,9)));
    }



}
