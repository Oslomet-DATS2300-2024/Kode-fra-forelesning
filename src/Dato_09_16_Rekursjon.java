import java.util.Arrays;

public class Dato_09_16_Rekursjon {
    public static void main(String[] args) {
        //System.out.println(sumTil(7));
        //System.out.println(fib(4));
        //hanoisTårn(7, 'A', 'B', 'C');
        int[] tabell = {1, 3, 2, 4, 7, 2, 8};
        quickSortIterativ(tabell);
        System.out.println(Arrays.toString(tabell));;
    }

    public static int sumTil(int n) {
        System.out.println("Begynner metode sumTil(" + n+")");
        int svar;
        if (n <= 0) throw new IllegalArgumentException();
        if (n == 1) svar = 1;
        else svar = n + sumTil(n-1);
        System.out.println("Avslutter metode sumTil("+n+")");
        return svar;
    }

    public static int fib(int n) {
        System.out.println("Begynner metode fib("+n+")");
        int svar;
        if (n < 0) throw new IllegalArgumentException();
        if (n < 2) svar = 1;
        else svar = fib(n-1) + fib(n-2);
        System.out.println("Avslutter metode fib("+n+")");
        return svar;
    }

    public static void hanoisTårn(int n, char fra, char hjelp, char til) {
        if (n <= 0) return;
        hanoisTårn(n-1, fra, til, hjelp);
        System.out.println("Flytt disk fra "+fra+" til "+til+".");
        hanoisTårn(n-1,hjelp, fra, til);
    }

    public static void quickSortIterativ(int[] tabell) {
        // Vi må lage vår egen stabel.
        int[] stabel = new int[2*tabell.length];
        int i = 0;
        stabel[i++] = 0; stabel[i++] = tabell.length-1;
        while (i > 0) {
            int til = stabel[--i]; // her var feilen i forelesning, stod "i--" i stedet for "--i"
            int fra = stabel[--i]; // Jeg legger inn verdi og så øker i, så må trekke fra i før jeg henter ut verdi.
            while (fra < til) {
                int k = partisjoner(tabell, fra, til);
                // Sorterer alt til pivot
                //quickSort(tabell, fra, k - 1);
                stabel[i++] = fra;
                stabel[i++] = k-1;
                // Sorterer alt etter pivot
                fra = k + 1;
            }
        }
    }

    public static void quickSort(int[] tabell) {
        quickSort(tabell, 0, tabell.length-1);
    }

    public static void quickSort(int[] tabell, int fra, int til) {
        if (fra >= til) return;

        // Deler opp tabellen i:
        // - Alt mindre enn Pivot
        // - Pivot (på plass k)
        // - Alt større enn eller lik Pivot
        int k = partisjoner(tabell, fra, til);

        // Sorterer alt til pivot
        quickSort(tabell, fra, k-1);
        // Sorterer alt etter pivot
        quickSort(tabell, k+1, til);
    }

    public static int partisjoner(int[] tabell, int fra, int til) {
        if (fra >= til) return fra;
        int v = fra, h = til-1;
        int pivot = tabell[til];
        while (true) {
            while (v <= h && tabell[v] < pivot) v++;
            while (v <= h && tabell[h] >= pivot) h--;
            if (v >= h) {
                bytt(tabell, til, v);
                return v;
            }
            bytt(tabell, v++, h--);
        }
    }
    public static void bytt(int[] tabell, int i, int j) {
        int tmp = tabell[i];
        tabell[i] = tabell[j];
        tabell[j] = tmp;
    }
}
