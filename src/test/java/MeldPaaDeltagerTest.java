import gruppe20.model.Arrangor;
import gruppe20.controller.MeldPaaController;
import gruppe20.controller.ArrangementController;
import gruppe20.model.Deltager;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class MeldPaaDeltagerTest {
    private Label label=null;
    private Text text = null;
    private DatePicker dato= null;
    private Arrangor nyArranagment = new Arrangor("Skitur", "Skitur i finse", 1600,LocalDate.MIN);
    private Deltager nyDeltager = new Deltager("Martine","Babesen");
    private Deltager nyDeltager3 = new Deltager("Martineøæå","Babesen");
    private Deltager nyDeltager2 = new Deltager("Jo","Johansen");

    private ArrayList <Deltager> deltagerListe = new ArrayList<Deltager>();


    //NF 8
    // Testen er gyldig når bruker har minst tre bokstaver
    @Test
    public void testDeltagerFornavnMinstTreBokstaver() {
        if (nyDeltager.getFornavn().length() <= 3) {
            assertEquals(nyDeltager.getFornavn().length(), 3);
        }
    }

    // Testen skal vise at det er feil med mindre enn tre bokstaver
    @Test
    public void testDeltagerFornavnMindreEnnTreBokstaver() {
        if (nyDeltager2.getFornavn().length() <= 3) {
            assertEquals(nyDeltager2.getFornavn().length(), 3);
        }
    }
    @Test
    public void testOmFornavnInnehlderSpesialTegn() {
        String [] spesialtegn= {"æ","ø","å","Ø","Æ","Å"};
        if (!nyDeltager.getFornavn().matches("^[a-zA-Z]*$")) {
            assertEquals(nyDeltager.getFornavn(),spesialtegn );
        }
    }

    @Test
    public void testSlettDeltager(){

    }

       @Test
    public void sjekkArrangorKonstruktor() {
         Arrangor arg1 = new Arrangor("tittel", "beskrivelse", 12, LocalDate.MIN);

         assertSame(arg1.getBeskrivelse() == "Beskrivelse", false);
    }



}
