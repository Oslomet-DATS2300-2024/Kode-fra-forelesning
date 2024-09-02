import java.util.Arrays;

public class Sortering_09_02 {
    public static void main(String[] args) {
        // int[] tabell = {2, 1, 3, 4, 7, 5, 4, 1};
        int[] tabell = EkstraFunksjoner.randPerm(100000000);
        quickSort(tabell);
        System.out.println(Arrays.toString(tabell));
    }

    public static void bubbleSort(int[] tabell) {
        for (int i = tabell.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (tabell[j] > tabell[j+1]) bytt(tabell, j, j+1);
            }
        }
    }

    public static void quickSort(int[] tabell) {
        quickSort(tabell, 0, tabell.length-1);
    }

    public static void quickSort(int[] tabell, int fra, int til) {
        if (fra >= til) return;

        bytt(tabell, til, fra + (til-fra)/2); // hvorfor ikke (fra+til)/2? Svar: Overflow
        int pivot = tabell[til];
        int v = fra, h = til-1;
        while (true) {
            while (v <= h && tabell[v] < pivot) v++;
            while (v <= h && tabell[h] >= pivot) h--;
            if (v >= h) break;
            bytt(tabell, v++, h--);
        }
        bytt(tabell, til, v);
        quickSort(tabell, fra, v-1);
        quickSort(tabell, v+1, til);
    }

    public static void bytt(int[] tabell, int i, int j) {
        int tmp = tabell[i];
        tabell[i] = tabell[j];
        tabell[j] = tmp;
    }
}
