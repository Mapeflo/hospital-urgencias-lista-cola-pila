public class Cola {
    private int cuenta;
    private Nodo frente; // primer elemento (head)
    private Nodo fin;    // último elemento (tail)

    public Cola() {
        cuenta = 0;
        frente = null;
        fin = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    // Encolar (agregar al final)
    public void encolar(Object dato) throws Exception {
        if (dato == null) {
            throw new Exception("El dato no puede ser nulo");
        }

        Nodo nuevo = new Nodo(dato);

        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setDerecha(nuevo);
            nuevo.setIzquierda(fin);
            fin = nuevo;
        }
        ++cuenta;
    }

    // Desencolar (eliminar del frente)
    public Object desencolar() throws Exception {
        if (esVacia()) {
            throw new Exception("La cola está vacía");
        }

        Object dato = frente.getDato();
        frente = frente.getDerecha();

        if (frente != null) {
            frente.setIzquierda(null);
        } else {
            fin = null; // la cola quedó vacía
        }

        --cuenta;
        return dato;
    }

    // Consultar el frente
    public Object peek() throws Exception {
        if (esVacia()) {
            throw new Exception("La cola está vacía");
        }
        return frente.getDato();
    }

    // Tamaño
    public int tamanio() {
        return cuenta;
    }

    public boolean contiene(Object dato) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getDerecha();
        }
        return false;
    }

    public void limpiar() {
        frente = null;
        fin = null;
        cuenta = 0;
    }

    public void mostrar() {
        if (esVacia()) {
            System.out.println("La cola está vacía");
            return;
        }
        Nodo actual = frente;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getDerecha();
        }
    }
}
