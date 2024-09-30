import java.util.Comparator;

class SortertPrioritetsKø<T> implements StabelEllerKø<T> {
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
    Comparator<T> c;

    public SortertPrioritetsKø(Comparator<T> sammenlikner) {
        c = sammenlikner;
        hode = null;
    }

    @Override
    public boolean leggInn(T t) {
        Node denne = hode;
        // Problem: Hva om t er minst? Må sjekkes.
        while (denne.neste != null && c.compare(t, denne.neste.verdi) < 0) {
            denne = denne.neste;
        }
        denne.neste = new Node(t, denne.neste);
        return true;
    }

    @Override
    public T taUt() {
        Node denne = hode;
        hode = denne.neste;
        denne.neste = null;
        return denne.verdi;
    }

    @Override
    public T kikk() {
        return hode.verdi;
    }

    @Override
    public boolean tom() {
        return hode == null;
    }
}
public class Dato_09_30_Prioritetskø {
    public static void main(String[] args) {
        Comparator<Integer> c = Comparator.naturalOrder();
        System.out.println(c.compare(7, 10));
    }
}
