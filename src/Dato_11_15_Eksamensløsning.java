public class Dato_11_15_Eksamensløsning {
    public static void main(String[] args) {
        StringSet ss = new StringSet(8);
        ss.leggInn("Hei");
        ss.leggInn("Hallo");
        ss.leggInn("Lykke til på eksamen!");
        System.out.println(ss.leggInn("Hei"));
    }

    public static int[] parterMLS(int[] x, int fra, int til) {
        int v = fra, h = til, k = h;
        int pivot = x[h];
        while (v <= k){
            if (x[k] < pivot) bytt(x,v++,k);
            else if (x[k] == pivot) k--;
            else bytt(x,h--,k--);
        }
        return new int[]{v, h};
    }

    public static void quicksortRep(int[] x) {
        quicksortRep(x, 0, x.length-1);
    }

    public static void quicksortRep(int[] x, int fra, int til) {
        if (fra >= til) return;
        int[] a = parterMLS(x, fra, til);
        int v = a[0]; int h = a[1];
        quicksortRep(x, fra, v-1);
        quicksortRep(x, h+1, til);
    }

    public static void bytt(int[] c, int i, int j) {
        int tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}

class StringSet {
    /*Bruker hashkoder for  ̊a lagre en mengde strenger effektivt.Bruker lenkede lister dersom en hashkode gjenbrukes.*/
    private int size;
    private Node[] hashtabell;
    private final class Node {
        Node neste;
        String verdi;
        private Node(String verdi, Node neste) {
            this.verdi = verdi; this.neste = neste;
        }
    }
    public StringSet(int size) {
        this.size = size;
        hashtabell = new Node[size];
    }
    private int beregnPosisjon(String verdi) {
        int i = verdi.hashCode();
        i = i % size;
        if (i < 0) i = size + i;
        return i;
    }
    public boolean leggInn(String verdi) {
        if (inneholder(verdi)) return false;
        int i = beregnPosisjon(verdi);
        System.out.println(i);
        hashtabell[i] = new Node(verdi, hashtabell[i]);
        return true;
    }
    public boolean inneholder(String verdi) {
        int i = beregnPosisjon(verdi);
        Node n = hashtabell[i];
        while (n != null) {
            if (n.verdi.equals(verdi))
                return true;
            n = n.neste;
        }
        return false;
    }
}
