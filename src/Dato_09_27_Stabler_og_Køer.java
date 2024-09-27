interface StabelEllerKø<T> {
    boolean leggInn(T t);
    T taUt();
    T kikk();
    boolean tom();
}

class TabellStabel<T> implements StabelEllerKø<T>{
    int slutt;
    T[] tabell;

    @SuppressWarnings("unchecked")
    public TabellStabel(int maks) {
        tabell = (T[]) new Object[maks];
        slutt = 0;
    }

    public TabellStabel() {
        this(30);
    }

    public boolean leggInn(T t) {
        tabell[slutt++] = t;
        return true;
    }
    public T taUt() {
        T verdi = tabell[--slutt];
        tabell[slutt] = null;
        return verdi;
    }
    public T kikk() {
        return tabell[slutt-1];
    }
    public boolean tom() {
        return slutt == 0;
    }
}

class LenketListeStabel<T> implements StabelEllerKø<T> {
    private class Node {
        Node neste;
        T verdi;
        public Node(T verdi, Node neste) {
            this.verdi = verdi;
            this.neste = neste;
        }
        public Node(T verdi) {
            this(verdi, null);
        }
    }

    Node hode;
    public LenketListeStabel() {
        hode = null;
    }

    public boolean leggInn(T t) {
        hode = new Node(t, hode);
        return true;
    }
    public T taUt() {
       T verdi = hode.verdi;
       Node slettet = hode;
       hode = slettet.neste;
       slettet.neste = null;
       return verdi;
    }
    public T kikk() {
        return hode.verdi;
    }

    public boolean tom() {
        return hode == null;
    }
}

class LenketListeKø<T> implements StabelEllerKø<T> {

    private class Node {
        Node neste;
        T verdi;
        public Node(T verdi, Node neste) {
            this.verdi = verdi;
            this.neste = neste;
        }
        public Node(T verdi) {
            this(verdi, null);
        }
    }
    Node hode = null;
    Node hale = null;

    public boolean leggInn(T t) {
        if (tom()) {
            hode = new Node(t);
            hale = hode;
        }
        hale.neste = new Node(t);
        hale = hale.neste;
        return true;
    }

    public T taUt() {
        T verdi = hode.verdi;
        Node slettet = hode;
        hode = slettet.neste;
        slettet.neste = null;
        if (tom()) hale = null;
        return verdi;
    }

    public T kikk() {
        return hode.verdi;
    }

    public boolean tom() {
        return hode == null;
    }
}

class TabellKø<T> implements StabelEllerKø<T> {

    T[] tabell;
    int start;
    int slutt;

    @SuppressWarnings("unchecked")
    public TabellKø(int maks) {
        start = 0;
        slutt = 0;
        tabell = (T[]) new Object[maks];
    }

    public TabellKø() {
        this(30);
    }

    public boolean leggInn(T t) {
        tabell[slutt++] = t;
        if (slutt == tabell.length) slutt = 0;
        if (start == slutt) throw new Error("Her har noe skjedd galt.");
        return true;
    }

    public T taUt() {
        T verdi = tabell[start++];
        tabell[start-1] = null;
        if (start == tabell.length) start = 0;
        return verdi;
    }

    public T kikk() {
        return tabell[start];
    }

    public boolean tom() {
        return start == slutt;
    }
}

public class Dato_09_27_Stabler_og_Køer {

    public static void main(String[] args) {
        StabelEllerKø<Integer> stabel = new TabellKø<>(7);
        int[] tabell = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : tabell) stabel.leggInn(i);
        while (!stabel.tom()) System.out.println(stabel.taUt());
    }
}
