import java.util.ArrayList;
import java.util.Arrays;

public class Dato_10_21_LZWKomprimering {
    public static void main(String[] args) {
        TabellListe<Integer> tblListe = LZWkomprimer("ABBABABAC");
        System.out.println(tblListe.toString());
    }

    public static TabellListe<Integer> LZWkomprimer(String input) {
        TabellListe<Integer> kode = new TabellListe<>();
        char[] karakterTabell = input.toCharArray();
        String s;
        int kodeTall;
        int nesteKode= 256;
        // Runde 0
        char c = karakterTabell[0];
        s = ""+c;
        kodeTall = (int) c;
        ArrayList<String> ordbok = new ArrayList<>();
        for (int i = 1; i < karakterTabell.length; i++) {
            c = karakterTabell[i];
            // Er s + c i ordboka?
            if (ordbok.contains(s+c)) {
                s = s + c;
                kodeTall = finnKode(s, ordbok);
                // Sjekk neste bokstav
            } else {
                // Legg inn i ordboka
                ordbok.add(s+c);
                s = ""+c;
                // Gi ut koden
                kode.leggInn(kodeTall);
                kodeTall = finnKode(s, ordbok);
            }
        }
        kode.leggInn(kodeTall);
        return kode;
    }

    public static int finnKode(String s, ArrayList<String> ordbok) {
        if (s.length() == 1) {
            return (int) s.charAt(0);
        } else {
            return 256+ordbok.indexOf(s);
        }
    }
}
