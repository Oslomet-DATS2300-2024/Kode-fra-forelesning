public class Dato_10_29_DobbeltLenketListeAnderledes {
}

class DobbeltLenketListeV1<T> {
    private static final class Node<T> {
        T verdi;
        Node<T> forrige, neste;

        public Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }
    }

    Node<T> hode;
    Node<T> hale;
    int antall;

    public DobbeltLenketListeV1() {
        hode = new Node(null, null, null);
        hale = new Node(null, hode, null);
        hode.neste = hale;
        antall = 0;
    }

    private Node<T> finnNode(int indeks) {
        Node<T> n = hode.neste;
        for (int i = 0; i < indeks; i++) {
            n = n.neste;
        }
        return n;
    }

    public boolean leggInn(int indeks, T verdi) {
        Node<T> n = finnNode(indeks);
        Node<T> nyNode = new Node(verdi, n.forrige, n);
        nyNode.forrige.neste = nyNode;
        nyNode.neste.forrige = nyNode;
        return true;
    }
}

class DobbeltLenketListeV2<T> {
    interface Node<T> {
        T getVerdi();
        void setVerdi(T verdi);
        Node<T> getNeste();
        void setNeste(Node<T> neste);
        Node<T> getForrige();
        void setForrige(Node<T> forrige);
    }

    class HodeNode<T> implements Node<T> {
        private Node<T> neste;

        @Override
        public T getVerdi() {
            throw new UnsupportedOperationException("Prøver hente verdi fra HodeNode.");
        }

        @Override
        public void setVerdi(T verdi) {
            throw new UnsupportedOperationException("Prøver sette verdi til HodeNode.");
        }

        @Override
        public Node<T> getNeste() {
            return neste;
        }

        @Override
        public void setNeste(Node<T> neste) {
            this.neste = neste;
        }

        @Override
        public Node<T> getForrige() {
            throw new UnsupportedOperationException("Prøver hente forrige node til HodeNode.");
        }

        @Override
        public void setForrige(Node<T> forrige) {
            throw new UnsupportedOperationException("Prøver sette forrige node til HodeNode");
        }

        public HodeNode(Node<T> neste) {
            this.neste = neste;
        }
    }

    private class HaleNode<T> implements Node<T> {
        Node<T> forrige;

        @Override
        public T getVerdi() {
            throw new UnsupportedOperationException("Prøver hente verdi fra HaleNode.");
        }

        @Override
        public void setVerdi(T verdi) {
            throw new UnsupportedOperationException("Prøver sette verdi til HaleNode.");
        }

        @Override
        public Node<T> getNeste() {
            throw new UnsupportedOperationException("Prøver finne neste node til HaleNode.");
        }

        @Override
        public void setNeste(Node<T> neste) {
            throw new UnsupportedOperationException("Prøver sette neste node til HaleNode.");
        }

        @Override
        public Node<T> getForrige() {
            return forrige;
        }

        @Override
        public void setForrige(Node<T> forrige) {
            this.forrige = forrige;
        }

        public HaleNode(Node<T> forrige) {
            this.forrige = forrige;
        }
    }

    class MidtNode<T> implements Node<T> {
        T verdi;
        Node<T> forrige, neste;

        @Override
        public T getVerdi() {
            return verdi;
        }

        @Override
        public void setVerdi(T verdi) {
            this.verdi = verdi;
        }

        @Override
        public Node<T> getNeste() {
            return neste;
        }

        @Override
        public void setNeste(Node<T> neste) {
            this.neste = neste;
        }

        @Override
        public Node<T> getForrige() {
            return forrige;
        }

        @Override
        public void setForrige(Node<T> forrige) {
            this.forrige = forrige;
        }

        public MidtNode(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }
    }

    Node<T> hode, hale;
    int antall;

    public DobbeltLenketListeV2() {
        hode = new HodeNode<>(null);
        hale = new HaleNode<>(hode);
        hode.setNeste(hale);
        antall = 0;
    }

    private Node<T> finnNode(int indeks) {
        Node<T> n = hode.getNeste();
        for (int i = 0; i < indeks; i++) {
            n = n.getNeste();
        }
        return n;
    }

    public boolean leggInn(int indeks, T verdi) {
        Node<T> n = finnNode(indeks);
        Node<T> nyNode = new MidtNode<>(verdi, n.getForrige(), n);
        nyNode.getForrige().setNeste(nyNode);
        nyNode.getNeste().setForrige(nyNode);
        return true;
    }
}