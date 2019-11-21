package gruppe20.model;
import java.time.LocalDate;

public class Arrangement extends Arrangor {
    public Arrangement(String tittel, String beskrivelse, int tidspunkt, LocalDate utgivelsesdato) {
        super(tittel, beskrivelse, tidspunkt, utgivelsesdato);
    }

    public Arrangement() {
        super();
    }


    @Override
    public String toString() {
        return getTittel() + (getDato() != LocalDate.MIN ? " (" + getDato().getYear() + ")" :"");

    }

}
