class Binærtre<T> {
    public class Node {
        T verdi;
        Node venstre;
        Node høyre;

        public Node(T verdi) {
            this.verdi = verdi;
        }
    }

    public Node rot;

    public void printVerdierPost() {
        printVerdierPost(rot);
    }

    private void printVerdierPost(Node n) {
        // Print denne noden og alle nodens barn
        if (n == null) return;

        printVerdierPost(n.venstre);
        printVerdierPost(n.høyre);
        System.out.println(n.verdi);
    }

    public void printVerdierPre() {
        printVerdierPre(rot);
    }

    private void printVerdierPre(Node n) {
        // Print denne noden og alle nodens barn
        if (n == null) return;

        System.out.println(n.verdi);
        printVerdierPre(n.venstre);
        printVerdierPre(n.høyre);
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

public class Dato_10_07_Binærtrær {
    public static void main(String[] args) {
        Binærtre<Integer> tre = new Binærtre<>();
        tre.rot = tre.new Node(1);
        tre.rot.venstre = tre.new Node(2);
        tre.rot.høyre = tre.new Node(3);
        tre.rot.venstre.høyre = tre.new Node(5);
        tre.rot.venstre.høyre.venstre = tre .new Node(10);
        tre.rot.høyre.høyre = tre.new Node(7);

        tre.printVerdierIn();
    }
}
