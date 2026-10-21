public class Lista {
    private int cuenta;
    private Nodo primero;
    private Nodo ultimo;

    public Lista() {
        cuenta = 0;
        primero = null;
        ultimo = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    public void agregar(Object dato) throws Exception {
        if (dato == null) {
            throw new Exception("El dato no puede ser nulo");
        }

        Nodo objetivo = new Nodo(dato);

        if (esVacia()) {
            objetivo.setIzquierda(null);
            objetivo.setDerecha(null);
            primero = objetivo;
            ultimo = objetivo;
        } else {
            ultimo.setDerecha(objetivo);
            objetivo.setIzquierda(ultimo);
            objetivo.setDerecha(null);
            ultimo = objetivo;
        }
        ++cuenta;
    }

    public void agregarAlInicio(Object dato) throws Exception {
        if (dato == null) {
            throw new Exception("El dato no puede ser nulo");
        }

        Nodo objetivo = new Nodo(dato);

        if (esVacia()) {
            objetivo.setIzquierda(null);
            objetivo.setDerecha(null);
            primero = objetivo;
            ultimo = objetivo;
        } else {
            objetivo.setDerecha(primero);
            objetivo.setIzquierda(null);
            primero.setIzquierda(objetivo);
            primero = objetivo;
        }
        ++cuenta;
    }

    private Nodo buscar(int indice) throws Exception {
        if (esVacia()) {
            throw new Exception("La lista está vacía");
        }
        if (indice < 0 || indice >= cuenta) {
            throw new Exception("La posición " + indice + " está fuera del rango 0 - " + (cuenta - 1));
        }

        Nodo actual = primero;
        for (int i = 0; i < cuenta; i++) {
            if (indice == i) {
                break;
            }
            actual = actual.getDerecha();
        }
        return actual;
    }

    private int buscar(Object dato) throws Exception {
        if (esVacia()) {
            throw new Exception("La lista está vacía");
        }

        Nodo actual = primero;
        for (int i = 0; i < cuenta; i++) {
            if (actual.getDato().equals(dato)) {
                return i;
            }
            actual = actual.getDerecha();
        }
        throw new Exception("El elemento no está en la lista");
    }

    public Object buscarDato(int indice) throws Exception {
        Nodo objetivo = buscar(indice);
        return objetivo.getDato();
    }

    public int buscarDato(Object dato) throws Exception {
        return buscar(dato);
    }

    public int cuentaElementos() {
        return cuenta;
    }

    public void mostrarAdelante() {
        if (esVacia()) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo actual = primero;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getDerecha();
        }
    }

    public void mostrarAtras() {
        if (esVacia()) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo actual = ultimo;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getIzquierda();
        }
    }

    // Métodos adicionales
    public void eliminarPrimero() throws Exception {
        if (esVacia()) {
            throw new Exception("La lista está vacía");
        }
        if (cuenta == 1) {
            primero = null;
            ultimo = null;
        } else {
            primero = primero.getDerecha();
            primero.setIzquierda(null);
        }
        --cuenta;
    }

    public void eliminarUltimo() throws Exception {
        if (esVacia()) {
            throw new Exception("La lista está vacía");
        }
        if (cuenta == 1) {
            primero = null;
            ultimo = null;
        } else {
            ultimo = ultimo.getIzquierda();
            ultimo.setDerecha(null);
        }
        --cuenta;
    }

    public boolean contiene(Object dato) {
        try {
            buscar(dato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void limpiar() {
        primero = null;
        ultimo = null;
        cuenta = 0;
    }
}
