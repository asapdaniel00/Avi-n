/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avion;

import java.util.*;

/**
 *
 * @author DanielFlorez
 */
public class MenuAdministrador {

    Scanner scanner = new Scanner(System.in);
    private GestorAerolineas gestorAerolineas = new GestorAerolineas();
    private GestorRutas gestorRutas = new GestorRutas(gestorAerolineas); // Pasamos GestorAerolineas al constructor

    public void mostrarMenu() {
        boolean volverAlPrincipal = false;
        int opcion = 0;

        while (!volverAlPrincipal) {
            System.out.println("\n=== Opciones de Administrador ===");
            System.out.println("1. Aerolíneas");
            System.out.println("2. Rutas");
            System.out.println("3. Aviones");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestorAerolineas.mostrarMenu();
                    System.out.println("-> Gestionando Aerolíneas...");
                    break;
                case 2:
                    gestorRutas.mostrarMenu();
                    System.out.println("-> Gestionando Rutas...");
                    break; 
                case 3:
                    // gestorAviones.mostrarMenu();
                    System.out.println("-> Gestionando Aviones...");
                    break;
                case 4:
                    System.out.println("-> Volviendo al Menú Principal...");
                    volverAlPrincipal = true;
                    break;
                default:
                    // Si no es 1, 2, 3 o 4
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 4.");
                    break;
            }

        }
    }
}
