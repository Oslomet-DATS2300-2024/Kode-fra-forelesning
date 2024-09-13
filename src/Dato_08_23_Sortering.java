import java.util.Arrays;

public class Dato_08_23_Sortering {
    public static void main(String[] args) {
        int[] tabell = EkstraFunksjoner.randPerm(1000);
        int maks = maksIndeks(tabell);
        System.out.println(maks);
        System.out.println(tabell[maks]);
        sorter(tabell);
        System.out.println(Arrays.toString(tabell));
    }

    public static void sorter(int[] tabell) {
        for (int i = tabell.length; i > 0; i--) {
            int maks = maksIndeks(tabell, 0, i);
            bytt(tabell, maks, i-1);
        }
    }

    public static int maksIndeks(int[] tabell) {
        return maksIndeks(tabell, 0, tabell.length);
    }

    public static int maksIndeks(int[] tabell, int fra, int til) {
        // burde fremdeles sjekke om tabellen er tom
        int størsteIndeks = fra;
        int største = tabell[fra];
        for (int i = fra+1; i < til; i++) {
            if (største < tabell[i]) {
                største = tabell[i];
                størsteIndeks = i;
            }
        }
        return størsteIndeks;
    }

    public static void bytt(int[] tabell, int i, int j) {
        int tmp = tabell[i];
        tabell[i] = tabell[j];
        tabell[j] = tmp;
    }
}
