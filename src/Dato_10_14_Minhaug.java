import java.util.Comparator;

class MinHaug<T> { // implements Beholder
    TabellListe<T> tblist;
    Comparator<? super T> comp;

    public MinHaug(Comparator<? super T> comp) {
        this.comp = comp;
        tblist = new TabellListe<>();
    }

    public int antall() {
        return tblist.antall();
    }

    public boolean tom() {
        return tblist.antall() <= 0;
    }

    public boolean leggInn(T verdi) {
        tblist.leggInn(verdi);
        int i = tblist.antall()-1;
        while (i > 1) {
            int forelderposisjon = i / 2;
            int cmp = comp.compare(verdi, tblist.hent(forelderposisjon-1));
            if (cmp <0) {
                // bytt plass på denne posisjon og forelderposisjon
                tblist.oppdater(i, tblist.hent(forelderposisjon-1));
                tblist.oppdater(forelderposisjon-1, verdi);
            } else {
                break;
            }
            i = forelderposisjon;
        }
        return true;
    }

    public T taUt() {
        throw new UnsupportedOperationException();
    }

    public T kikk() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return tblist.toString();
    }
}

public class Dato_10_14_Minhaug {
    public static void main(String[] args) {
        MinHaug<Integer> haug = new MinHaug<>(Comparator.naturalOrder());
        int[] a = {5, 3, 7, 1, 4, 8, 13};
        for (int verdi : a)
            haug.leggInn(verdi);
        System.out.println(haug);
    }
}
