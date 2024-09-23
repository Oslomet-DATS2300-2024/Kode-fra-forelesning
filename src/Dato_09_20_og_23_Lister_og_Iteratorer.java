import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

interface Beholder<T> extends Iterable<T> {
    boolean leggInn(T t);
    boolean inneholder(T t);
    boolean fjern(T t);
    int antall();
    boolean tom();
}

interface Liste<T> extends Beholder<T> {
    boolean leggInn(int indeks, T t);
    T oppdater(int indeks, T t);
    T hent(int indeks);
    boolean fjern(int indeks);
}

class TabellListe<T> implements Liste<T> {
    private T[] tabellListe;
    private int antall;

    @SuppressWarnings("unchecked")
    public TabellListe(int størrelse) {
        int antall = 0;
        tabellListe = (T[]) new Object[størrelse];
    }

    public TabellListe() {
        this(10);
    }

    public int antall() {
        return antall;
    }

    public boolean tom() {
        return antall == 0;
    }

    @SuppressWarnings("unchecked")
    public boolean leggInn(T t) {
        Objects.requireNonNull(t, "Nullelement ikke lov å legge inn");
        if (antall == tabellListe.length) {
            Object[] nyTabellListe = new Object[2*tabellListe.length];
            for (int i = 0; i < antall; i++) {
                nyTabellListe[i] = tabellListe[i];
            }
            tabellListe = (T[]) nyTabellListe;
        }
        tabellListe[antall++] = t;

        return true;
    }

    public T hent(int i) {
        if (i < 0 || i >= antall) {
            throw new IndexOutOfBoundsException("Du er utafor lista.");
        }
        return tabellListe[i];
    }

    public T oppdater(int i, T t) {
        if (i < 0 || i >= antall) {
            throw new IndexOutOfBoundsException("Du er utafor lista.");
        }
        T gammelVerdi = tabellListe[i];
        tabellListe[i] = t;
        return gammelVerdi;
    }

    public boolean leggInn(int i, T t) {throw new UnsupportedOperationException();}
    public boolean fjern(T t) {throw new UnsupportedOperationException();}
    public boolean fjern(int i) {throw new UnsupportedOperationException();}
    public boolean inneholder(T t) {throw new UnsupportedOperationException();}

    public Iterator<T> iterator() {
        return new TabellListeIterator();
    }

    private class TabellListeIterator implements Iterator<T> {
        int i;
        public TabellListeIterator() {
            i = 0;
        }

        public boolean hasNext() {
            return i < antall;
        }

        public T next() {
            return tabellListe[i++];
        }
    }
}

class LenketListe<T> implements Liste<T> {
    private class Node {
        T verdi;
        Node neste;

        public Node(T verdi, Node neste) {
            this.verdi = verdi;
            this.neste = neste;
        }

        public Node(T verdi) {
            this(verdi, null);
        }
    }

    Node hode;
    int antall;
    int endringer;

    public LenketListe() {
        antall = 0;
        endringer = 0;
        hode = null;
    }

    public int antall() {return antall;}
    public boolean tom() {return (antall == 0);}

    public boolean leggInn(T t) {
        // Legger inn i _starten_ av lista.
        hode = new Node(t, hode);
        antall++; endringer++;
        return true;
    }

    public boolean leggInn(int indeks, T t) {
        // Ikke ferdig!
        // Hva med tom liste?
        // Hva med liste med ett element?
        // Hva om indeks = antall?
        if (indeks < 0 || indeks > antall)
            throw new IndexOutOfBoundsException("Du er utafor lista.");
        Node n = hode;
        Node f = null;
        for (int i = 0; i < indeks; i++) {
            f = n; n = n.neste;
        }
        f.neste = new Node(t, n);
        // Kunne droppa n, siden n = f.neste.
        // f.neste = new Node(t, f.neste)
        antall++; endringer++;
        return true;
    }

    public T hent(int indeks) {
        if (indeks < 0 || indeks >= antall)
            throw new IndexOutOfBoundsException("Du er utafor lista.");
        Node n = hode;
        for (int i = 0; i < indeks; i++) {
            n = n.neste;
        }
        return n.verdi;
    }

    public T oppdater(int i, T t) {throw new UnsupportedOperationException();}
    public boolean fjern(int i) {throw new UnsupportedOperationException();}
    public boolean fjern(T t) {
        // Gir feilmelding om vi prøver fjerne hode
        Node n = hode;
        Node f = null;
        while (n != null) {
            if (n.verdi == t) {
                f.neste = n.neste;
                n.neste = null;
                antall--; endringer++;
                return true;
            }
            f = n;
            n = n.neste;
        }
        return false;
    }
    public boolean inneholder(T t) {throw new UnsupportedOperationException();}

    public Iterator<T> iterator() {
        return new LenketListeIterator();
    }

    private class LenketListeIterator implements Iterator<T> {
        Node denne = hode;
        int startEndringer;

        public LenketListeIterator() {
            startEndringer = endringer;
        }
        public boolean hasNext() {
            return denne != null;
        }
        public T next() {
            if (startEndringer != endringer) throw new ConcurrentModificationException();
            T verdi = denne.verdi;
            denne = denne.neste;
            return verdi;
        }
    }
}

class AllePositiveHeltall implements Iterable<Integer> {
    public Iterator<Integer> iterator() {
        return new AllePositiveHeltallIterator();
    }

    private class AllePositiveHeltallIterator implements Iterator<Integer> {
        int i = 1;
        public boolean hasNext() {
            return true;
        }
        public Integer next() {
            return i++;
        }
    }
}

public class Dato_09_20_og_23_Lister_og_Iteratorer {
    public static void testTabellListe() {
        TabellListe<Integer> tblist = new TabellListe<>(4);
        int[] tabell = {3, 7, 5, 2, 4, 3, 10, 8, 19};
        for (int i: tabell) tblist.leggInn(i);
        tblist.oppdater(5, 13);
        for (Integer i : tblist) {
            System.out.println(i);
        }
        // Dette er ekvivalent med:
        Iterator<Integer> it = tblist.iterator();
        while (it.hasNext()) {
            Integer i = it.next();

            System.out.println(i);
        }
    }

    public static void testLenketListe() {
        LenketListe<Integer> llist = new LenketListe<>();
        int[] tabell = {3, 7, 5, 2, 4, 3, 10, 8, 19};
        for (int i : tabell) llist.leggInn(i);
        //System.out.println(llist.hent(3));
        for (int i = 0; i < 9; i++) {
            System.out.println(llist.hent(i));
        }
        // Nå, bedre utgave:
        for(Integer i : llist) {
            System.out.println(i);
        }
    }

    public static void testLenketListeSletting() {
        LenketListe<String> llstring = new LenketListe<>();
        String[] stabell = {"Hei", "Hallo", "Hvordan går det?", "Dårlig", "Bra", "Ok"};
        for (String s : stabell) llstring.leggInn(s);
        for (String s : llstring) {
            if (s.equals("Bra")) {
                llstring.fjern("Dårlig");
            }
            System.out.println(s);
        }
        for (String s : llstring) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        //testTabellListe();
        //testLenketListe();
        testLenketListeSletting();

        // Ikke kjør dette:
        /*
        for (Integer i : new AllePositiveHeltall()) {
            System.out.println(i);
        }
        */
    }
}
