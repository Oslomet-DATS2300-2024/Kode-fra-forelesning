import java.util.Comparator;

class Binærsøketre<T> { // implements Beholder<T>
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
        return false;
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
}

public class Dato_10_07_Pluss_Binærsøketre {
    public static void main(String[] args) {
        Binærsøketre<Integer> tre =
                new Binærsøketre<>(Comparator.naturalOrder());

        int[] a = {7, 5, 4, 9, 6, 11, 5, 8, 7, 8, 10};
        for (int i : a) tre.leggInnRekursiv(i);

        tre.printVerdierIn();
    }
}
