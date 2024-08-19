public class MaksElement_08_19 {
    public static void main(String[] args) {
        int[] tabell = {1, 3, 2, 7, 5, 0, 4, 4, 2, -3};
        int maksVerdi = maks(tabell);
        System.out.println(maksVerdi);
        int[] tabell2 = {};
        System.out.println(maks(tabell2));
    }

    public static int maks(int[] tabell) {
        // Burde sjekke her om tabellen er tom, og kaste feilmelding
        int største = tabell[0];
        for (int i = 1; i < tabell.length; i++) {
            if (tabell[i] > største) {
                største = tabell[i];
            }
        }
        return største;
    }
}
