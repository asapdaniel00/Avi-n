/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avion;

/**
 *
 * @author DanielFlorez
 */
import avion.File_Pry; // Usamos la clase de archivos
import java.io.*;
import java.util.Scanner;

public class GestorRutas {

    private Scanner scanner;
    private File_Pry archivoRutas;
    private File_Pry archivoAsignaciones; // Necesario para la conexión Rutas-Aviones/Aerolineas
    private final String RUTA_FILE = "Rutas.txt";
    private final String ASIGNACION_FILE = "RutasAsignadas.txt";
    // Nota: Necesitaríamos también una instancia de GestorAerolineas y GestorAviones
    // pero los simularemos por ahora.
    private GestorAerolineas gestorAerolineas; // Asumimos que esta clase existe para la lista

    public GestorRutas(GestorAerolineas ga) {
        this.scanner = new Scanner(System.in);
        this.gestorAerolineas = ga; 
        
        // Inicializamos los archivos
        this.archivoRutas = new File_Pry(null, "BaseDatos", ";");
        this.archivoRutas.Crear_Archivo(RUTA_FILE);
        
        this.archivoAsignaciones = new File_Pry(null, "BaseDatos", ";");
        this.archivoAsignaciones.Crear_Archivo(ASIGNACION_FILE); // Para guardar las asignaciones
    }

    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- GESTIÓN DE RUTAS ---");
            System.out.println("1. Ingresar Ruta");
            System.out.println("2. Retirar Ruta"); // El flujograma tiene Retirar aquí
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    ingresarRuta();
                    break;
                case 2:
                    retirarRuta();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // --- OPCIÓN 1: INGRESAR RUTA (Y ASIGNACIÓN) ---
    public void ingresarRuta() {
        System.out.println("\n--- INGRESAR NUEVA RUTA ---");
        
        // Paso 3: Se solicita que ingrese de donde a donde requiere su ruta.
        System.out.print("Ingrese el Código de la Ruta (Ej. R01): ");
        String codigo = scanner.nextLine();
        
        System.out.print("Ingrese el Origen: ");
        String origen = scanner.nextLine();
        
        System.out.print("Ingrese el Destino: ");
        String destino = scanner.nextLine();

        // Paso 4: Se guardan cambios del paso 3. (Guardar la definición de la ruta)
        String nuevaRuta = codigo + ";" + origen + ";" + destino;
        archivoRutas.Escribir_Archivo(nuevaRuta);
        System.out.println("Definición de Ruta guardada exitosamente.");

        // Paso 5: Se muestra al usuario opc. de asignar ruta. (Ver flujograma)
        System.out.print("¿Desea asignar esta ruta inmediatamente? (1: Si / 2: No): ");
        int deseaAsignar = scanner.nextInt();
        scanner.nextLine();

        if (deseaAsignar == 1) {
            asignarRuta(codigo); // Llamamos al método de asignación
        }

        // Paso 11: Se redirige al primer menú
        System.out.println("-> Volviendo al Menú de Gestión de Rutas...");
    }

    private void asignarRuta(String codigoRuta) {
        // Paso 6: Si la respuesta es si, se muestra la opción de asignarlo a aerolínea o avión.
        System.out.println("\n--- ASIGNAR RUTA " + codigoRuta + " ---");
        System.out.println("Asignar a:");
        System.out.println("1. Aerolínea");
        System.out.println("2. Avión");
        System.out.print("Opción (1 o 2): ");
        
        int opcionAsignar = scanner.nextInt();
        scanner.nextLine();
        
        String tipoAsignacion = "";
        String idAsignado = "";

        // --- ASIGNAR A AEROLÍNEA ---
        if (opcionAsignar == 1) {
            // Paso 7: Si la respuesta al paso 6 es aerolínea, Se muestra lista de aerolíneas creadas.
            // La lista la debería manejar GestorAerolineas (simulamos la llamada)
            System.out.println("Mostrando lista de Aerolíneas disponibles:");
            // Aquí llamarías a gestorAerolineas.mostrarListaAerolineas();
            gestorAerolineas.mostrarListaAerolineas(); // Asumiendo que agregaste este método a GestorAerolineas
            
            // Paso 8: Se solicita que seleccione una aerolínea y se guardan cambios.
            System.out.print("Ingrese el CÓDIGO de la Aerolínea a asignar: ");
            idAsignado = scanner.nextLine();
            tipoAsignacion = "AEROLINEA";
            
        // --- ASIGNAR A AVIÓN ---
        } else if (opcionAsignar == 2) {
            // Paso 9: Si la respuesta al paso 6 es avión se muestra lista de aviones creados.
            System.out.println("Mostrando lista de Aviones disponibles:");
            // Aquí llamarías a gestorAviones.mostrarListaAviones(); (SIMULADO)
            System.out.println("Avión 787 (A01), Avión A320 (A02)");

            // Paso 10: Se solicita que seleccione un avion y se guardan cambios.
            System.out.print("Ingrese el CÓDIGO del Avión a asignar: ");
            idAsignado = scanner.nextLine();
            tipoAsignacion = "AVION";
            
        } else {
            System.out.println("Opción de asignación no válida.");
            return;
        }
        
        // Guardar la asignación en un archivo de relación: RutasAsignadas.txt
        // Formato: TipoAsignacion;IDAsignado;CodigoRuta
        String asignacion = tipoAsignacion + ";" + idAsignado + ";" + codigoRuta;
        archivoAsignaciones.Escribir_Archivo(asignacion);
        
        System.out.println("Asignación guardada exitosamente: Ruta " + codigoRuta + " asignada a " + tipoAsignacion + " " + idAsignado);
    }
    
    // --- OPCIÓN 2: RETIRAR RUTA ---
    public void retirarRuta() {
        System.out.println("\n--- RETIRAR RUTA DE ASIGNACIÓN ---");
        
        // Paso 2: Se solicita que seleccione la ruta a retirar según aerolinea.
        System.out.print("Ingrese el CÓDIGO de la Aerolínea de donde quiere retirar la ruta: ");
        String codigoAerolinea = scanner.nextLine();
        
        // Paso 3: Se muestran los aviones los cuales tienen la ruta asignada (según la selección del paso 2)
        System.out.println("Simulación: Aviones de la Aerolínea " + codigoAerolinea + " con rutas asignadas:");
        System.out.println("- Avión A01 (Rutas: R01, R02)");
        System.out.println("- Avión A03 (Rutas: R01)");

        // Paso 4: Se solicita seleccionar el avión al que desea retirarle la ruta.
        System.out.print("Ingrese el CÓDIGO del Avión al que desea retirarle la ruta: ");
        String codigoAvion = scanner.nextLine();
        
        // Se necesita seleccionar la RUTA específica a retirar (no está en la tabla, pero es necesaria)
        System.out.print("Ingrese el CÓDIGO de la RUTA que desea retirar del Avión " + codigoAvion + ": ");
        String codigoRuta = scanner.nextLine();

        // Lógica de eliminación: Eliminar la línea del archivo RutasAsignadas.txt
        // SIMULACIÓN DE GUARDADO (la lógica real de eliminación es compleja y requiere reescritura)
        if (simularEliminacionAsignacion(codigoAvion, codigoRuta)) {
             // Paso 5: Se guardan cambios
            System.out.println("Ajustes guardados exitosamente. Ruta " + codigoRuta + " retirada del Avión " + codigoAvion + ".");
        } else {
            System.out.println("Error: No se pudo retirar la ruta (simulación).");
        }

        // Paso 6: Se redirige al primer menú
        System.out.println("-> Volviendo al Menú de Gestión de Rutas...");
    }

    // Simula la eliminación en el archivo de asignaciones
    private boolean simularEliminacionAsignacion(String idAvion, String idRuta) {
        // En la implementación real, esta función usaría la lógica de leer/reescribir 
        // RutasAsignadas.txt para quitar la línea que contenga "AVION;IDAvion;IDRuta"
        // Por ahora, asumimos éxito.
        return true; 
    }
}
