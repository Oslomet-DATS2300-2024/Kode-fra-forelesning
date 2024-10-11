import java.util.Comparator;
import java.util.Iterator;
import java.util.StringJoiner;

class Binærsøketre<T> implements Iterable<T> {
    private class Node {
        T verdi;
        Node venstre;
        Node høyre;

        public Node(T verdi) {
            this.verdi = verdi;
        }
    }

    Node rot;
    Comparator<? super T> comp;
    int antall;

    public Binærsøketre(Comparator<? super T> comp) {
        rot = null;
        this.comp = comp;
        antall = 0;
    }

    public boolean leggInn(T verdi) {
        if (rot == null) {
            rot = new Node(verdi);
            antall++;
            return true;
        }

        Node n = rot;
        while (true) {
            int comparatorValue = comp.compare(verdi, n.verdi);
            if (comparatorValue < 0) {
                if (n.venstre == null) {
                    n.venstre = new Node(verdi);
                    break;
                }
                n = n.venstre;
            } else {
                if (n.høyre == null) {
                    n.høyre = new Node(verdi);
                    break;
                }
                n = n.høyre;
            }
        }
        antall++;
        return true;
    }

    public boolean leggInnRekursiv(T verdi) {
        rot = leggInnRekursiv(rot, verdi);
        return true;
    }

    private Node leggInnRekursiv(Node n, T verdi) {
        if (n == null) {
            return new Node(verdi);
        }

        int comparatorValue = comp.compare(verdi, n.verdi);
        if (comparatorValue < 0) {
            n.venstre = leggInnRekursiv(n.venstre, verdi);
        } else {
            n.høyre = leggInnRekursiv(n.høyre, verdi);
        }
        return n;
    }


    public void printVerdierIn() {
        printVerdierIn(rot);
    }

    private void printVerdierIn(Node n) {
        // Print denne noden og alle nodens barn
        if (n == null) return;

        printVerdierIn(n.venstre);
        System.out.println(n.verdi);
        printVerdierIn(n.høyre);
    }

    public String toString() {
        StringJoiner sJ = new StringJoiner(", ", "[", "]");
        toStringRekursivt(rot, sJ);
        return sJ.toString();
    }

    private void toStringRekursivt(Node n, StringJoiner sJ) {
        if (n == null) return;

        toStringRekursivt(n.venstre, sJ);
        sJ.add(n.verdi.toString());
        toStringRekursivt(n.høyre, sJ);
    }

    public boolean fjern(T verdi) {
        Node m = null;
        Node n = rot;
        int cV;

        while (n != null) {
            cV = comp.compare(verdi, n.verdi);
            if (cV < 0) {
                m = n;
                n = n.venstre;
            } else if (cV > 0) {
                m = n;
                n = n.høyre;
            } else {
                fjernNode(n, m);
                antall--;
                return true;
            }
        }
        return false;
    }

    private void fjernNode(Node n, Node f) {
        // Denne skal fjerne noden n, hvor f er forelderen til n.

        if (n.venstre == null && n.høyre == null) {
            // Situasjon 1: n har ingen barn.
            if (f == null)
                rot = null;
            else if (f.venstre == n)
                f.venstre = null;
            else
                f.høyre = null;
        } else if (n.høyre == null) {
            // Situasjon 2a: n har venstrebarn.
            if (f == null)
                rot = n.venstre;
            else if (f.venstre == n) {
                f.venstre = n.venstre;
            } else {
                f.høyre = n.venstre;
            }
            n.venstre = null;
        } else if (n.venstre == null) {
            // Situasjon 2b: n har høyrebarn.
            if (f == null)
                rot = n.høyre;
            else if (f.venstre == n) {
                f.venstre = n.høyre;
            } else {
                f.høyre = n.høyre;
            }
        } else {
            // n har to barn.
            // må finne neste element i inorden, bytte verdier, og så slette dette.
            Node o = n.høyre;
            Node p = n;
            while (o.venstre != null) {
                p = o;
                o = o.venstre;
            }
            n.verdi = o.verdi;
            fjernNode(o, p);
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new BinærsøketreIterator();
    }

    private class BinærsøketreIterator implements Iterator<T> {
        StabelEllerKø<Node> stabel = new LenketListeStabel<>();
        Node denne;

        public BinærsøketreIterator() {
            if (rot == null) {
                denne = null;
            } else {
                denne = nesteInorden(rot);
            }
        }

        private Node nesteInorden(Node n) {
            while (n != null) {
                stabel.leggInn(n);
                n = n.venstre;
            }
            return stabel.taUt();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            T verdi = denne.verdi;
            // finn neste i inorden fra denne.
            if (denne.høyre != null) {
                denne = nesteInorden(denne.høyre);
            } else {
                if (!stabel.tom())
                    denne = stabel.taUt();
                else
                    denne = null;
            }
            return verdi;
        }
    }
}

public class Dato_10_07_Pluss_Binærsøketre {
    public static void main(String[] args) {
        Binærsøketre<Integer> tre =
                new Binærsøketre<>(Comparator.naturalOrder());

        int[] a = {7, 5, 4, 9, 6, 11, 5, 8, 7, 8, 10};
        for (int i : a) tre.leggInnRekursiv(i);
        tre.fjern(8);
        tre.fjern(4);

        System.out.println(tre);

        for (Integer v : tre) {
            System.out.println(v);
        }
    }
}
