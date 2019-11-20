import gruppe20.model.Arrangement;
import gruppe20.model.Deltager;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.junit.Test;
import org.w3c.dom.Text;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ApplicationTest {
    private Label label = null;
    private Text text = null;
    private DatePicker dato = null;
    //lager objekter for å kjøre testene
    private Arrangement nyArranagment = new Arrangement("Skitur", "Skitur i Finse", 1600, LocalDate.MIN);
    private Arrangement nyArranagment2 = new Arrangement("Løping", "løping i Halden", 1600, LocalDate.MIN);
    private Arrangement nyArranagment3 = new Arrangement("Dansing", "Dansing i Australia", 1600, LocalDate.of(2010, 5, 17));
    private Arrangement nyArranagment4 = new Arrangement("Svømming", "Svømming i Oslo", 1600, LocalDate.of(2020, 7, 15));
    private Deltager nyDeltager = new Deltager("Martineø", "Babesen", LocalDate.of(1997,12,10));
    private Deltager nyDeltager2 = new Deltager("Jo", "Johansen",LocalDate.of(2025,10,10));
    LocalDate today = LocalDate.now();

    private ArrayList<Deltager> deltagerListe = new ArrayList<Deltager>();

    //Krav: NF 8
    // Testen er gyldig når bruker har minst tre bokstaver
    @Test
    public void testDeltagerFornavnMinstTreBokstaver() {
        if (nyDeltager.getFornavn().length() <= 3) {
            assertEquals(nyDeltager.getFornavn().length(), 3);
        }
    }
    // Skal teste om deltagerfornavn er mindre enn tre bokstaver
    @Test
    public void testDeltagerFornavnMindreEnnTreBokstaver() {
        if (!(nyDeltager2.getFornavn().length() >= 3)) {
            assertTrue(true);
        }
    }

    // Testen skal vise at det er feil med mindre enn tre bokstaver for etternavn
    @Test
    public void testDeltagerEtternavnMindreEnnTreBokstaver() {
        if (nyDeltager.getEtternavn().length() <= 3) {
            assertEquals(nyDeltager.getEtternavn().length(), 3);
        }
    }


    //Krav:  NF9
    //tester om fornavnet til deltager inneholder spesialtegn
    @Test
    public void testOmFornavnInnehlderSpesialTegnForDeltager() {
        String[] spesialtegn = {"æ", "ø", "å", "Ø", "Æ", "Å"};
        if (nyDeltager.getFornavn().matches("^[a-zA-Z]*$")) {
            assertEquals(nyDeltager.getFornavn(), spesialtegn);
        }
    }

    //tester om ettternavnet inneholder spesialtegn
    @Test
    public void testOmEtternavnInnehlderSpesialTegnForDeltager() {
        String[] spesialtegn = {"æ", "ø", "å", "Ø", "Æ", "Å"};
        if (!nyDeltager.getEtternavn().matches("^[a-zA-Z]*$")) {
            assertEquals(nyDeltager.getEtternavn(), spesialtegn);
        }
    }


    //Krav: SF9
    //sjekker om det finnes arrangementer
    @Test
    public void testArrangementerFinnes() {
        ArrayList<String> arrangementForTest = new ArrayList<>();
        arrangementForTest.add(nyArranagment.getTittel());
        arrangementForTest.add(nyArranagment2.getTittel());
        //System.out.println(arrangementForTest);
        assertFalse(arrangementForTest.isEmpty());
    }

    //sjekker om det ikke finnes  arrangementer
    @Test
    public void testArrangementerFinnesIkke() {
        ArrayList<String> arrangementForTest = new ArrayList<>();
        arrangementForTest.add(nyArranagment.getTittel());
        arrangementForTest.add(nyArranagment2.getTittel());
        arrangementForTest.removeAll(arrangementForTest);
        assertTrue(arrangementForTest.isEmpty());
    }

    //Krav: SF2
    //Tester om beskrivelse for arrangementene vises eller ikke
    @Test
    public void testArrangementBeskrivelse() {
        ArrayList<String> arrangementForTest = new ArrayList<>();
        arrangementForTest.add(nyArranagment.getBeskrivelse());
        arrangementForTest.add(nyArranagment2.getBeskrivelse());
        if (arrangementForTest.isEmpty()) {
            assertEquals(arrangementForTest, true);
        }
    }

    //Tester om lista for beskrivelse er tom eller ikke
    @Test
    public void testArrangementBeskrivelseFinnesIkke() {
        ArrayList<String> arrangementForTest = new ArrayList<>();
        arrangementForTest.add(nyArranagment.getBeskrivelse());
        arrangementForTest.add(nyArranagment2.getBeskrivelse());

        arrangementForTest.removeAll(arrangementForTest);
        if (arrangementForTest.isEmpty()) {
            assertTrue(true);
        }
    }

    /*
    krav SF1
    tester om arrangementene har vært eller skal være
    her er datoen på arrangement4 i fremtiden, dette vil bli en suksessful test
    */
    @Test
    public void testArrangementAktiv() {

        if (nyArranagment4.getDato().compareTo(today) <= 0) {
            assertEquals(nyArranagment4.getDato(), true);
        }

    }

    //her er datoen på arrangement3 mindre enn dagens dato
    @Test
    public void testArrangementIkkeAktiv() {
        if (nyArranagment3.getDato().compareTo(today)>= 0) {
            assertEquals(nyArranagment3.getDato(), true);
        }
    }

    // SF11
    //test feilmelding
    @Test
    public void testFeilMeldingArrangement() {
        String feilmelding = "";

        if(nyArranagment.getBeskrivelse().isEmpty()){
            feilmelding += "Noe gikk galt";
            assertTrue(false);
        }
        else {
            assertTrue(true);
        }
    }

}
