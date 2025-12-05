package avion;

import java.io.*;
import java.util.*;
public class MenuUsuario {
    
    private GestorAviones gestor;
    
    
    public MenuUsuario(GestorAviones gestor){
        this.gestor = gestor;
    }

    public void mostrarMenu() {
        boolean salir = false;
        Scanner scanner = new Scanner(System.in);

        File archivo = new File("C:\\Users\\karol\\Aviones\\aviones.txt");

        while (!salir) {
            System.out.println("\n < Menú Usuario >");
            System.out.println("1. Ver vuelos disponibles");
            System.out.println("2. Ver sillas disponibles");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarVuelos(archivo);
                    break;
                case 2:
                    mostrarSillas(archivo);
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void mostrarVuelos(File archivo) {
        System.out.println("\n <Vuelos disponibles> ");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                System.out.println("Avión " + datos[0] + " → Ruta: " + datos[2]);
            }
        } catch (Exception e) {
            System.out.println("No hay vuelos registrados.");
        }
    }

    public void mostrarSillas(File archivo) {
        System.out.println("\n < Sillas disponibles >");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                System.out.println("Avión " + datos[0] + "Sillas: " + datos[1]);
            }
        } catch (Exception e) {
            System.out.println("No hay aviones registrados.");
        }
    }
       
  }
