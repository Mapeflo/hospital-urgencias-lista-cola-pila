public class Pila {
    private int cuenta;
    private Nodo cima; // el tope de la pila

    public Pila() {
        cuenta = 0;
        cima = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    // Apilar (push)
    public void apilar(Object dato) throws Exception {
        if (dato == null) {
            throw new Exception("El dato no puede ser nulo");
        }

        Nodo nuevo = new Nodo(dato);

        if (esVacia()) {
            cima = nuevo;
        } else {
            nuevo.setIzquierda(cima);
            cima.setDerecha(nuevo);
            cima = nuevo;
        }
        ++cuenta;
    }

    // Desapilar (pop)
    public Object desapilar() throws Exception {
        if (esVacia()) {
            throw new Exception("La pila está vacía");
        }

        Object dato = cima.getDato();
        cima = cima.getIzquierda(); // el nodo de abajo

        if (cima != null) {
            cima.setDerecha(null);
        }

        --cuenta;
        return dato;
    }

    // Consultar el tope (peek)
    public Object peek() throws Exception {
        if (esVacia()) {
            throw new Exception("La pila está vacía");
        }
        return cima.getDato();
    }

    // Tamaño
    public int tamanio() {
        return cuenta;
    }

    public boolean contiene(Object dato) {
        Nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getIzquierda();
        }
        return false;
    }

    public void limpiar() {
        cima = null;
        cuenta = 0;
    }

    public void mostrar() {
        if (esVacia()) {
            System.out.println("La pila está vacía");
            return;
        }
        Nodo actual = cima;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getIzquierda();
        }
    }

    public Object buscar(Object dato) throws Exception {
        Nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getIzquierda();
        }
        throw new Exception("El elemento no está en la pila");
    }
}