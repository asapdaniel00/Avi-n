/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avion;

import java.util.*;
import static avion.File_Pry.*; // Agregado para el manejo de archivos cr

public class MainPrincipal {

    Scanner scanner = new Scanner(System.in);
    MenuAdministrador menuAdmin = new MenuAdministrador();
    MenuUsuario menuUser = new MenuUsuario();

    public void iniciarApp() {
        boolean salir = false;
        int opcion = 0;

        while (!salir) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("Seleccione el rol con el que desea ingresar:");
            System.out.println("1. Adm.");
            System.out.println("2. User");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuAdmin.mostrarMenu();
                    break;
                case 2:
                    System.out.println("-> Accediendo al Menú de Usuario");
                    
                    System.out.println("Elija la opcion que requiere consultar");// agregado para entrar a las opciones de ususario cr
                        switch (opcion){
                            case 1:
                                menuUser.mostrarMenu();
                        }
                    break;
                case 3:
                    System.out.println("¡Gracias por usar la aplicación! Finalizando...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese 1, 2 o 3.");
                    break;
            }
            
        }
        scanner.close();
    }
}
