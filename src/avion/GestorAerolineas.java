/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avion;

import avion.File_Pry; // Importamos tu clase de archivos (asegúrate que el package sea correcto)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList; // Necesario para guardar las líneas temporalmente
import java.util.List;

public class GestorAerolineas {

    private Scanner scanner;
    private File_Pry archivoAerolineas; // Instancia para manejar el archivo
    private final String NOMBRE_ARCHIVO = "Aerolineas.txt";
    
    private final String RUTA_FILE = "Rutas.txt";

    public GestorAerolineas() {
        this.scanner = new Scanner(System.in);
        // Inicializamos tu clase File_Pry. 
        // null en ruta para usar la del proyecto, "BaseDatos" como carpeta, ";" como separador
        this.archivoAerolineas = new File_Pry(null, "BaseDatos", ";");
        // Aseguramos que el archivo exista al iniciar
        this.archivoAerolineas.Crear_Archivo(NOMBRE_ARCHIVO);
    }

    // --- ESTE ES EL MÉTODO QUE NO ENTENDÍAS ---
    // Paso 1: Se muestra al usuario opciones de administrador (ingresar aerolineas).
    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- GESTIÓN DE AEROLÍNEAS ---");
            System.out.println("1. Ingresar Aerolínea");
            System.out.println("2. Modificar Aerolínea");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            // Paso 2: Elección Opc. ingresar o aerolínea.
            switch (opcion) {
                case 1:
                    ingresarAerolinea();
                    break;
                case 2:
                    modificarAerolinea();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // --- LÓGICA PRINCIPAL DEL FLUJOGRAMA ---
    public void ingresarAerolinea() {
        System.out.println("\n--- INGRESAR NUEVA AEROLÍNEA ---");

        // Paso 3: Se determina si aerolínea es < 5.
        // Usamos un método auxiliar para contar las líneas del archivo
        int cantidadActual = contarRegistros(NOMBRE_ARCHIVO);

        if (cantidadActual >= 4) {
            // Paso 5: Si el paso 3 no se cumple se muestra msj de error... y repite paso 2 (retorna al menú)
            System.out.println("ERROR: Excede el límite permitido de aerolíneas (Máximo 4).");
            return;
        }

        // Paso 4: Si el paso 3 se cumple, se crea aerolínea y se guarda.
        System.out.print("Ingrese el Nombre de la Aerolínea: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el Código de la Aerolínea: "); // Dato extra necesario
        String codigo = scanner.nextLine();

        // Guardamos usando TU clase File_Pry
        // Formato: Nombre;Codigo
        String nuevaAerolinea = nombre + ";" + codigo;
        archivoAerolineas.Escribir_Archivo(nuevaAerolinea);
        System.out.println("¡Aerolínea guardada exitosamente!");

        // Paso 6: Se pregunta si desea asignar aviones a la aerolínea.
        System.out.print("¿Desea asignar aviones a esta aerolínea? (1: Si / 2: No): ");
        int deseaAsignarAviones = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (deseaAsignarAviones == 1) {
            gestionarAsignacionAviones(nombre); // Lógica separada para orden
        } else {
            // Paso 16: Si la respuesta es NO, se devuelve al menú (al finalizar este método vuelve al switch)
            return;
        }
    }

    private void gestionarAsignacionAviones(String nombreAerolinea) {
        // Paso 7: Si respuesta es SI, instrucción de que no puede tener mas de 10 aviones.
        System.out.println("NOTA: La aerolínea " + nombreAerolinea + " no puede tener más de 10 aviones asignados.");

        // Simulación: Verificamos si existen aviones creados (Paso en el flujograma "Existen aviones creados?")
        // Aquí deberías conectar con tu GestorAviones o revisar un archivo Aviones.txt
        boolean existenAviones = true; // Supongamos que sí para el ejemplo

        if (!existenAviones) {
            System.out.println("No existen aviones creados para asignar. Redirigiendo a crear aviones...");
            // Aquí llamarías a gestorAviones.ingresarAvion();
            return;
        }

        System.out.print("Indique la cantidad de aviones a asignar: ");
        int cantidadAviones = scanner.nextInt();
        scanner.nextLine();

        if (cantidadAviones <= 10) {
            // Paso 8: Se guarda asignación y se pregunta si desea asignar rutas.
            System.out.println("Asignación de " + cantidadAviones + " aviones guardada.");
            // Aquí usarías archivoAerolineas.Escribir_Archivo() si tuvieras un archivo de asignaciones

            preguntarAsignarRutas();

        } else {
            // Paso 9: Si no se cumple, mensaje de error.
            System.out.println("ERROR: La cantidad excede el límite permitido (Máx 10).");
            // El flujograma sugiere volver a pedir o salir, aquí retornamos.
        }
    }

    private void preguntarAsignarRutas() {
        System.out.print("¿Desea asignar rutas a la aerolínea? (1: Si / 2: No): ");
        int deseaRutas = scanner.nextInt();
        scanner.nextLine();

        if (deseaRutas == 1) {
            gestionarAsignacionRutas();
        }
        // Paso 16: Si es No, termina y vuelve al menú.
    }

   private void gestionarAsignacionRutas() {
    System.out.println("\n--- ASIGNACIÓN DE RUTAS ---");
    
    // Paso 10: Se valida si existen rutas creadas (Leyendo Rutas.txt).
    int cantidadRutasCreadas = contarRegistros(RUTA_FILE);
    
    if (cantidadRutasCreadas == 0) {
        // Paso 15: Error "no cuenta con rutas creadas".
        System.out.println("ERROR: No cuenta con rutas creadas. Vaya a la opción Rutas para crearlas.");
        return; // Sale del método y vuelve al menú
    }
    
    // Se informa que las rutas deben ser < 5.
    System.out.println("NOTA: Hay " + cantidadRutasCreadas + " rutas disponibles. Solo puede asignar máximo 4 rutas.");

    // Paso 11: Si las rutas están creadas se muestra lista de rutas para seleccionar.
    System.out.println("--- Lista de Rutas Disponibles ---");
    mostrarListaRutasDisponibles(); // Nuevo método para listar Rutas.txt

    System.out.print("¿Cuántas rutas desea asignar de la lista (Máx 4)?: ");
    int cantRutas = scanner.nextInt();
    scanner.nextLine();

    // Paso 12: Validar que sea <= 4
    if (cantRutas <= 4 && cantRutas > 0) {
        // Lógica de selección real de rutas (SIMULADA para brevedad)
        for (int i = 0; i < cantRutas; i++) {
            System.out.print("Ingrese el CÓDIGO de la Ruta #" + (i + 1) + " a asignar: ");
            String codigoRuta = scanner.nextLine();
            // Aquí iría la lógica para guardar la asignación en el archivo RutasAsignadas.txt
        }
        
        // Paso 14: Se guardan cambios.
        System.out.println("Rutas asignadas exitosamente.");
        
    } else {
        // Paso 13: Error y repite.
        System.out.println("ERROR: La cantidad debe ser entre 1 y 4.");
        gestionarAsignacionRutas(); // Recursividad para repetir el paso
    }
}
   // --- MÉTODO AUXILIAR PARA MOSTRAR RUTAS ---
// Muestra las rutas creadas en GestorRutas.java (archivo Rutas.txt)
private void mostrarListaRutasDisponibles() {
    String rutaCompleta = "BaseDatos\\" + RUTA_FILE;
    File archivo = new File(rutaCompleta);

    if (!archivo.exists()) {
        System.out.println("Aviso: El archivo de Rutas.txt no existe.");
        return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(";");
            if (partes.length >= 3) {
                // Formato de Rutas.txt: Codigo;Origen;Destino
                System.out.println("- Código: " + partes[0] + " | Ruta: " + partes[1] + " -> " + partes[2]);
            }
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo de Rutas: " + e.getMessage());
    }
}

    // --- MÉTODO AUXILIAR IMPORTANTE ---
    // Tu clase File_Pry no tiene un método para contar líneas fácilmente, 
    // así que creamos este pequeño ayudante localmente.
    private int contarRegistros(String nombreArchivo) {
        int contador = 0;
        // Construimos la ruta tal cual lo hace tu clase File_Pry
        String rutaCompleta = "BaseDatos\\" + nombreArchivo;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {
            while (br.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            // Si el archivo no existe o está vacío, el contador es 0
            return 0;
        }
        return contador;
    }

    // --- LÓGICA DE MODIFICAR (Requerimientos 1 al 8) ---
    public void modificarAerolinea() {
        System.out.println("\n--- MODIFICAR AEROLÍNEA ---");

        // Paso 3: Se muestra lista de aerolíneas creadas.
        System.out.println("Lista actual de aerolíneas:");
        boolean hayDatos = mostrarListaAerolineas();

        if (!hayDatos) {
            System.out.println("No hay aerolíneas para modificar.");
            return; // Si no hay datos, volvemos al menú
        }

        // Paso 4: Se le pide al usuario que seleccione aerolínea a modificar.
        // Usaremos el CÓDIGO como identificador único para buscarla.
        System.out.print("\nIngrese el CÓDIGO de la aerolínea que desea modificar: ");
        String codigoBuscado = scanner.nextLine();

        // Paso 5: Se solicita al usuario que ingrese todos los datos nuevos.
        System.out.println(">> Ingrese los NUEVOS datos para la aerolínea <<");

        System.out.print("Nuevo Nombre: ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Nuevo Código: ");
        String nuevoCodigo = scanner.nextLine();

        // Preparamos la nueva línea (Formato: Nombre;Codigo)
        String nuevaLinea = nuevoNombre + ";" + nuevoCodigo;

        // Paso 6: Se guarda modificación.
        boolean exito = actualizarAerolineaEnArchivo(codigoBuscado, nuevaLinea);

        if (exito) {
            System.out.println("¡Ajustes guardados exitosamente!");
        } else {
            System.out.println("ERROR: No se encontró una aerolínea con el código: " + codigoBuscado);
        }

        // Paso 7: Al terminar el método, automáticamente vuelve al menú (switch)
    }

    // --- MÉTODOS AUXILIARES PARA EL MANEJO DE ARCHIVOS ---
    // Este método lee el archivo y muestra las aerolíneas en consola
    public boolean mostrarListaAerolineas() {
        String rutaCompleta = "BaseDatos\\Aerolineas.txt";
        File archivo = new File(rutaCompleta);

        if (!archivo.exists()) {
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {
            String linea;
            boolean vacio = true;
            while ((linea = br.readLine()) != null) {
                // Separamos por punto y coma para mostrarlo bonito
                String[] partes = linea.split(";");
                if (partes.length >= 2) {
                    System.out.println("- Aerolínea: " + partes[0] + " | Código: " + partes[1]);
                    vacio = false;
                }
            }
            return !vacio;
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }
    }

    // Este método hace la "magia" de borrar lo viejo y poner lo nuevo en el .txt
    private boolean actualizarAerolineaEnArchivo(String codigoBuscado, String nuevaLineaDatos) {
        String rutaCompleta = "BaseDatos\\Aerolineas.txt";
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;

        // 1. LEER todo el archivo y cargarlo en memoria
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // Verificamos si esta es la línea que buscamos (comparando el CÓDIGO, que es la posición 1)
                if (partes.length >= 2 && partes[1].equals(codigoBuscado)) {
                    lineas.add(nuevaLineaDatos); // Agregamos la NUEVA información
                    encontrado = true;
                } else {
                    lineas.add(linea); // Agregamos la línea vieja tal cual estaba
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo para actualizar: " + e.getMessage());
            return false;
        }

        // Si no encontramos el código, no tiene sentido reescribir el archivo
        if (!encontrado) {
            return false;
        }

        // 2. REESCRIBIR el archivo completo con la lista modificada
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaCompleta))) {
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo el archivo actualizado: " + e.getMessage());
            return false;
        }

        return true;
    }
    
 

}
