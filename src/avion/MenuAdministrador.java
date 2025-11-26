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

            }

        }
    }
}
