public class Pair<U, V> {
    // Pair class
    public final U first;       // el primer campo de un par
    public final V second;      // el segundo campo de un par

    // Construye un nuevo par con valores especificados
<<<<<<< HEAD
    private Pair(U first, V second)
=======
    public Pair(U first, V second)
>>>>>>> Documents
    {
        this.first = first;
        this.second = second;
    }

    @Override
    // Verifica que el objeto especificado sea "igual a" el objeto actual o no
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

<<<<<<< HEAD
        // llamar al método `equals()` de los objetos subyacentes
=======
        // llamar al método equals() de los objetos subyacentes
>>>>>>> Documents
        if (!first.equals(pair.first)) {
            return false;
        }
        return second.equals(pair.second);
    }

    @Override
    // Calcula el código hash de un objeto para admitir tablas hash
    public int hashCode()
    {
        // usa códigos hash de los objetos subyacentes
        return 31 * first.hashCode() + second.hashCode();
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    // Método de fábrica para crear una instancia inmutable de par con tipo
    public static <U, V> Pair <U, V> of(U a, V b)
    {
        // llama al constructor privado
        return new Pair<>(a, b);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> Documents
