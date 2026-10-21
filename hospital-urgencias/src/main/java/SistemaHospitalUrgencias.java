import java.util.Scanner;

public class SistemaHospitalUrgencias {

    private Lista listaGeneral;
    private Cola colaPendientes;
    private Pila historialProcesados;
    private Scanner scanner;

    public SistemaHospitalUrgencias() {
        listaGeneral = new Lista();
        colaPendientes = new Cola();
        historialProcesados = new Pila();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> registrarPaciente();
                    case 2 -> verTodosLosPacientes();
                    case 3 -> verPendientes();
                    case 4 -> procesarSiguiente();
                    case 5 -> verHistorial();
                    case 6 -> buscarPaciente();
                    case 7 -> cancelarPendiente();
                    case 8 -> deshacerUltimoProcesamiento();
                    case 9 -> verCantidades();
                    case 10 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        } while (opcion != 10);
    }

    private void mostrarMenu() {
        System.out.println("========== HOSPITAL URGENCIAS ==========");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Ver todos los pacientes registrados");
        System.out.println("3. Ver pacientes pendientes");
        System.out.println("4. Procesar siguiente paciente");
        System.out.println("5. Ver historial de pacientes atendidos");
        System.out.println("6. Buscar paciente por código triage");
        System.out.println("7. Cancelar paciente pendiente");
        System.out.println("8. Deshacer último procesamiento");
        System.out.println("9. Ver cantidad de elementos");
        System.out.println("10. Salir");
        System.out.println("========================================");
    }

    // 1. Registrar paciente
    private void registrarPaciente() throws Exception {
        System.out.println("\n--- Registrar nuevo paciente ---");
        String codigo = leerTexto("Código triage: ");
        String nombre = leerTexto("Nombre del paciente: ");
        String nivel = leerTexto("Nivel de urgencia (Rojo/Naranja/Amarillo/Verde): ");
        String sintomas = leerTexto("Síntomas: ");

        PacienteUrgencia paciente = new PacienteUrgencia(codigo, nombre, nivel, sintomas);

        listaGeneral.agregar(paciente);
        colaPendientes.encolar(paciente);

        System.out.println("Paciente registrado y agregado a la cola de pendientes.");
    }

    // 2. Ver todos los pacientes
    private void verTodosLosPacientes() {
        System.out.println("\n--- Todos los pacientes registrados ---");
        listaGeneral.mostrarAdelante();
    }

    // 3. Ver pendientes
    private void verPendientes() {
        System.out.println("\n--- Pacientes pendientes ---");
        colaPendientes.mostrar();
    }

    // 4. Procesar siguiente
    private void procesarSiguiente() throws Exception {
        if (colaPendientes.esVacia()) {
            System.out.println("No hay pacientes pendientes.");
            return;
        }

        PacienteUrgencia procesado = (PacienteUrgencia) colaPendientes.desencolar();
        procesado.setEstado("Atendido");
        historialProcesados.apilar(procesado);

        System.out.println("Paciente atendido: " + procesado);
    }

    // 5. Ver historial
    private void verHistorial() {
        System.out.println("\n--- Historial de pacientes atendidos ---");
        historialProcesados.mostrar();
    }

    // 6. Buscar paciente
    private void buscarPaciente() throws Exception {
        String codigo = leerTexto("Ingrese el código triage a buscar: ");
        PacienteUrgencia buscado = new PacienteUrgencia(codigo, "", "", "");

        if (listaGeneral.contiene(buscado)) {
            int posicion = listaGeneral.buscarDato(buscado);
            Object encontrado = listaGeneral.buscarDato(posicion);
            System.out.println("Paciente encontrado: " + encontrado);
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    // 7. Cancelar paciente pendiente (usando cola auxiliar)
    private void cancelarPendiente() throws Exception {
        if (colaPendientes.esVacia()) {
            System.out.println("No hay pacientes pendientes.");
            return;
        }

        String codigo = leerTexto("Código triage del paciente a cancelar: ");
        PacienteUrgencia aCancelar = new PacienteUrgencia(codigo, "", "", "");

        Cola auxiliar = new Cola();
        boolean encontrado = false;

        while (!colaPendientes.esVacia()) {
            PacienteUrgencia actual = (PacienteUrgencia) colaPendientes.desencolar();
            if (actual.equals(aCancelar) && !encontrado) {
                actual.setEstado("Cancelado");
                System.out.println("Paciente cancelado: " + actual);
                encontrado = true;
            } else {
                auxiliar.encolar(actual);
            }
        }

        // Devolver los pacientes a la cola original
        while (!auxiliar.esVacia()) {
            colaPendientes.encolar(auxiliar.desencolar());
        }

        if (!encontrado) {
            System.out.println("No se encontró el paciente en la cola de pendientes.");
        }
    }

    // 8. Deshacer último procesamiento
    private void deshacerUltimoProcesamiento() throws Exception {
        if (historialProcesados.esVacia()) {
            System.out.println("No hay pacientes en el historial para deshacer.");
            return;
        }

        PacienteUrgencia ultimo = (PacienteUrgencia) historialProcesados.desapilar();
        ultimo.setEstado("Pendiente");
        colaPendientes.encolar(ultimo);

        System.out.println("Se deshizo el último procesamiento. Paciente devuelto a pendientes: " + ultimo);
    }

    // 9. Ver cantidades
    private void verCantidades() {
        System.out.println("\n--- Cantidad de elementos ---");
        System.out.println("Pacientes registrados (Lista): " + listaGeneral.cuentaElementos());
        System.out.println("Pacientes pendientes (Cola): " + colaPendientes.tamanio());
        System.out.println("Pacientes atendidos (Pila): " + historialProcesados.tamanio());
    }

    // Métodos auxiliares
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return valor;
    }

    // Método main
    public static void main(String[] args) {
        SistemaHospitalUrgencias sistema = new SistemaHospitalUrgencias();
        sistema.iniciar();
    }
}
