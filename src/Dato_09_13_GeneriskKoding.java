class StringBoks {
    String element;
    public void leggInn(String s) {
        element = s;
    }
    public String hentUt() {
        return element;
    }
}

// Liten Forbedring
class ObjBoks {
    Object element;
    public void leggInn(Object o) {
        element = o;
    }

    public Object hentUt() {
        return element;
    }
}

// Enda bedre variant
class Boks<T> {
    T element;

    public void leggInn(T element) {
        this.element = element;
    }

    public T hentUt() {
        return element;
    }
}

class Person implements Comparable<Person>{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Person p) {
        return this.age - p.getAge();
    }
}

class A implements Comparable<A> {
    public int compareTo(A a) {
        return 0;
    }
}

class B extends A {}

class C implements Comparable<Object> {
    public int compareTo(Object o) {
        return 0;
    }
}

class Maks {
    public static <T extends Comparable<? super T>> int maks(T[] tabell) {
        int størstPlassering = 0;
        T størstVerdi = tabell[0];
        for (int i = 1; i < tabell.length-1; i++) {
            if (størstVerdi.compareTo(tabell[i]) < 0) {
                størstVerdi = tabell[i];
                størstPlassering = i;
            }
        }
        return størstPlassering;
    }
}

public class Dato_09_13_GeneriskKoding {
    public static void main(String[] args) {
        //bokser();
        maksTest();
    }

    public static void rarSituasjon() {
        A[] a = new A[0];
        B[] b = new B[0];
        C[] c = new C[0];

        Maks.maks(a);
        Maks.maks(b);
        Maks.maks(c); // Får kompileringsfeil om vi ikke bruker Comparable<? super T>
    }

    public static void maksTest() {
        Integer[] tabell = {1, 3, 7, 5, 2, 0};
        int maks = Maks.maks(tabell);
        System.out.println(tabell[maks]);

        Person[] personTabell = new Person[3];
        personTabell[0] = new Person("Nikolai", 35);
        personTabell[1] = new Person("Thomas", 33);
        personTabell[2] = new Person("Peder", 31);
        maks = Maks.maks(personTabell);
        System.out.println(personTabell[maks].getName());
    }

    public static void bokser() {
        StringBoks sb = new StringBoks();
        sb.leggInn("Hei hvordan går det?");
        System.out.println(sb.hentUt());

        Person n = new Person("Nikolai", 35);
        ObjBoks ob = new ObjBoks();
        ob.leggInn(n);
        gratulerer((Person) ob.hentUt());

        Boks<Person> pb = new Boks<>();
        pb.leggInn(n);
        gratulerer(pb.hentUt());

        Boks<String> bedresb = new Boks<>();
        bedresb.leggInn("Kult, det går fint med String.");
        System.out.println(bedresb.hentUt());

        // Dette vil ikke funke:
        // Boks<int> intBoks = new Boks<>();
        // I stedet:
        Boks<Integer> intBoks = new Boks<>();
        intBoks.leggInn(7);
        // i bakgrunnen lages en new Integer(7);
        int j = intBoks.hentUt();
        // i bakgrunnen her gjøres Integer om til int
        // omgjøring av tabeller litt stress
        Integer[] integerTabell = {2, 4, 7, 1, 3, 5};
        int[] intTabell = {2, 4, 7, 1, 3, 5};
        // Vil ikke funke:
        //Integer[] oversattTabell = (Integer[]) intTabell;
        Integer[] oversattTabell = new Integer[intTabell.length];
        for (int i = 0; i < intTabell.length; i++) {
            oversattTabell[i] = intTabell[i];
        }
    }

    public static void gratulerer(Person p) {
        System.out.println("Gratulerer med alderen din, " + p.getName()+ ". Du er " + p.getAge() + " år!");
    }
}
