import java.util.Arrays;

interface Test {
    boolean test(char c);
}

class ErVokal implements Test {
    public boolean test(char c) {
        char[] vokaler = {'A', 'E', 'I', 'O', 'U', 'Y', 'Æ', 'Ø', 'Å', 'a', 'e', 'i', 'o', 'u', 'y', 'æ', 'ø', 'å'};
        for (int i = 0; i < vokaler.length; i++) {
            if (c == vokaler[i]) return true;
        }
        return false;
    }
}

class ErStorBokstav implements Test {
    public boolean test(char c) {
        return ((65 <= (int) c && (int) c <= 90) || (int) c == 197 || (int) c == 198 || (int) c == 216);
    }
}

class ErA implements Test {
    public boolean test(char c) {
        return (c == 'a') || (c == 'A');
    }
}

public class Dato_09_09_Partisjonering {
    public static void main(String[] args) {
        char[] bokstaver = {'A', 'c', 'D', 'e', 'H', 'i', 'k', 'O', 'i', 'r', 'u', 'z'};
        //char[] bokstaver = {'s', 't', 'q', 'R', 'k', 'V'};
        int skille = partisjoner(bokstaver, new ErStorBokstav());
        System.out.println(Arrays.toString(bokstaver));
        System.out.println(skille);
        char[] sorterAerFørst = {'a', 'A', 'e', 'k', 'a', 'V', 'A', 'j'};
        partisjoner(sorterAerFørst, c -> (c == 'a' || c == 'A'));
        System.out.println(Arrays.toString(sorterAerFørst));
        partisjoner(bokstaver, Dato_09_09_Partisjonering::erVokal);
        System.out.println(Arrays.toString(bokstaver));
    }

    public static int partisjoner(char[] tabell, Test t) {
        int v = 0;
        int h = tabell.length-1;
        while (true) {
            while (v <= h && t.test(tabell[v])) v++;
            while (v <= h && !t.test(tabell[h])) h--;
            if (v > h) break;
            bytt(tabell, v, h);
        }
        return v;
    }

    public static int partisjonerVokaler(char[] tabell) {
        int v = 0;
        int h = tabell.length-1;
        while (true) {
            while (v <= h && erVokal(tabell[v])) v++;
            while (v <= h && !erVokal(tabell[h])) h--;
            if (v > h) break;
            bytt(tabell, v, h);
        }
        return v;
    }

    public static int partisjonerStoreSmå(char[] tabell) {
        int v = 0;
        int h = tabell.length-1;
        while (true) {
            while (v <= h && erStor(tabell[v])) v++;
            while (v <= h && !erStor(tabell[h])) h--;
            if (v > h) break;
            bytt(tabell, v, h);
        }
        return v;
    }
    public static void bytt(char[] tabell, int i, int j) {
        char tmp = tabell[i];
        tabell[i] = tabell[j];
        tabell[j] = tmp;
    }

    public static boolean erVokal(char c) {
        char[] vokaler = {'A', 'E', 'I', 'O', 'U', 'Y', 'Æ', 'Ø', 'Å', 'a', 'e', 'i', 'o', 'u', 'y', 'æ', 'ø', 'å'};
        for (int i = 0; i < vokaler.length; i++) {
            if (c == vokaler[i]) return true;
        }
        return false;
    }

    public static boolean erStor(char c) {
        return ((65 <= (int) c && (int) c <= 90) || (int) c == 197 || (int) c == 198 || (int) c == 216);
    }
}
