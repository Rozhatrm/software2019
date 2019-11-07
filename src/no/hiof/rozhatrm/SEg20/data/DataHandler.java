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
        arrangementListe.add(new Arrangement("Løping",
                "Vi gleder oss! Kom og delta på årets tøffeste løp! Vinneren stikker av med 200 000 kr.\n\nStor gata 10, Fredrikstad sentrum" ,
                1430,
                LocalDate.of(2019, 12, 27)));
        arrangementListe.add(new Arrangement("Svømming",
                "Endelig nærmer vi oss den store dagen. Bli med og delta på Norges største svømme konkuranse.\n\nTunevannet, Sarpsborg",
                1240,
                LocalDate.of(2020, 1, 21)));
        arrangementListe.add(new Arrangement("Ski løp", "Ski sesongen nærmer seg, og vi varmer opp med 10 km ski løp. Bli med på morroa.\n\nBRA veien 3, Halden" , 1700,
                LocalDate.of(2020,2,10)));
    }


    private final static ObservableList<Deltager> deltagerListe = FXCollections.observableArrayList();

    public static ObservableList<Deltager> hentDeltagerData() {
        if(deltagerListe.isEmpty())
            genererDeltagerData();
        return deltagerListe;
    }

    private static void genererDeltagerData() {
        deltagerListe.add(new Deltager("Ole", "Hansen", LocalDate.of(1997,10,10)));
        deltagerListe.add(new Deltager("Per", "Persen", LocalDate.of(1999,9,9)));
        deltagerListe.add(new Deltager("Martine","Nielsen",LocalDate.of(2000,7,7)));
    }



}
