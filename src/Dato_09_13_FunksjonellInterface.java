public class Dato_09_13_FunksjonellInterface {
    public static void main(String[] args) {
        Funksjon f = new GangMedSelv();
        System.out.println(g(f, 4));
        g(x -> x*x*x, 4);
        // Betyr egentlig:
        class F implements Funksjon{
            public int call(int x) {
                return x*x*x;
            }
        }
        g(new F(), 4);

        // Kan også skrive:
        g(Dato_09_13_FunksjonellInterface::f, 4);
    }
    public static int f(int x) {
        return x*x;
    }

    public static int g(Funksjon f, int x) {
        // I Java må vi gi inn primitive typer eller klasser.
        return f.call(x);
    }
}

@FunctionalInterface
interface Funksjon {
    int call(int x);
}


class GangMedSelv implements Funksjon {
    public int call(int x) {
        return x*x;
    }
}
