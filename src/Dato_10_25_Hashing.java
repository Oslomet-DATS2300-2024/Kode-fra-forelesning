import java.util.Objects;

public class Dato_10_25_Hashing {
    public static void testHashKoder() {
        MinKlasse mk = new MinKlasse(37, "Hallo");
        MinKlasse mk2 = new MinKlasse(15, "Hei");
        System.out.println(mk.hashCode()); // Kan få forskjellige verdier hver gang vi kjører
        System.out.println(mk2.hashCode());
        System.out.println(mk.hashCode()); // Må få samme verdi innad i programmet

        // Mulig problem:
        MinKlasse mk3 = new MinKlasse(15, "Hei");
        System.out.println(mk3.hashCode());
        System.out.println(mk2.equals(mk3));
        System.out.println(mk2.hashCode());
        System.out.println(15 + "Hei".hashCode());
        System.out.println(mk2.hashCode() == mk3.hashCode());
        // Burde disse egentlig være like? Og om ja, så burde de også ha samme hashcode.
    }

    public static void main(String[] args) {
        HashAvbildning<String, Integer> ha = new HashAvbildning<>(20);
        ha.leggInn("ABA", 6);
        ha.leggInn("BAB", 7);
        ha.leggInn("AAC", 8);
        ha.leggInn("AAC", 12);
        ha.taUt("AAC");
        System.out.println(ha.hent("AAC"));
    }
}

class MinKlasse {
    int minVerdi;
    String minStreng;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MinKlasse minKlasse = (MinKlasse) o;
        return minVerdi == minKlasse.minVerdi && Objects.equals(minStreng, minKlasse.minStreng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minVerdi, minStreng);
    }

    public MinKlasse(int verdi, String streng) {
        minVerdi = verdi; minStreng = streng;
    }
}

class HashAvbildning<K, V> {
    private static class Node<K, V> {
        K key; V value;
        Node<K,V> neste;
        public Node(K key, V value, Node<K,V> neste) {
            this.key = key; this.value = value; this.neste = neste;
        }
    }

    Node<K, V>[] lagringstabell;

    @SuppressWarnings("unchecked")
    public HashAvbildning(int plasser) {
        lagringstabell = new Node[plasser];
    }

    public boolean leggInn(K key, V value) {
        // Problem: Burde sjekke om nøkkelen allerede er i systemet først.
        Node<K, V> n = hentNode(key);
        if (n == null) {
            int hashVerdi = key.hashCode();
            hashVerdi = fiksHashVerdi(hashVerdi);
            lagringstabell[hashVerdi] = new Node<K, V>(key, value, lagringstabell[hashVerdi]);
            return true;
        } else {
            n.value = value;
            return false;
        }
    }

    private Node<K,V> hentNode(K key) {
        int hashVerdi = key.hashCode();
        hashVerdi = fiksHashVerdi(hashVerdi);
        Node<K,V> n = lagringstabell[hashVerdi];
        while (n != null) {
            if (n.key.equals(key)) {
                break;
            }
            n = n.neste;
        }
        return n;
    }

    public V hent(K key) {
        Node<K,V> n = hentNode(key);
        return n == null ? null : n.value;
    }

    public int fiksHashVerdi(int gammelVerdi) {
        // Denne skal ta inn en vilkårlig hashverdi og gjøre om til en verdi mellom 0
        // og lengden til lagringstabell;
        int nyVerdi = gammelVerdi % lagringstabell.length;
        if (nyVerdi < 0) nyVerdi = lagringstabell.length + nyVerdi;
        return nyVerdi;
    }

    public V taUt(K key) {
        throw new UnsupportedOperationException();
    }
}