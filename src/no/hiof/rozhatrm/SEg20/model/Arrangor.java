package no.hiof.rozhatrm.SEg20.model;

import java.time.LocalDate;

public abstract class Arrangor {
    private String tittel, beskrivelse;
    private int tidspunkt;
    private LocalDate dato;

    public Arrangor(String tittel, String beskrivelse, int tidspunkt, LocalDate Dato) {
        this.tittel = tittel;
        this.beskrivelse = beskrivelse;
        this.tidspunkt = tidspunkt;
        this.dato = Dato;
    }

    public Arrangor() {
        tittel = "";
        beskrivelse = "";
        tidspunkt = 0;
        dato = LocalDate.MIN;

    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }



    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }


    public int getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(int tidspunkt) {
        this.tidspunkt = tidspunkt;
    }


}
