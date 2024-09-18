import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class Dato_09_18_TestingTest {

    @Test
    void maksTomTest() {
        Integer[] tabell = {};
        assertThrows(IllegalArgumentException.class,
                () -> Dato_09_18_Testing.maks(tabell),
                "Gir ingen eller gal feilmelding på tom tabell.");
    }

    @Test
    void maksEttElementTest() {
        Integer[] tabell = {3};
        assertEquals(0, Dato_09_18_Testing.maks(tabell));
    }

    @Test
    void maksTabellTest() {
        Integer[] tabell = {7, 1, 5, 2, 9, 3};
        assertEquals(4, Dato_09_18_Testing.maks(tabell), "Største verdi burde være på plass 4.");
    }
}