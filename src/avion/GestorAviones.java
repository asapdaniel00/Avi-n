package avion;

import avion.File_Pry; // Importamos tu clase de archivos (asegúrate que el package sea correcto)
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList; // Necesario para guardar las líneas temporalmente
import java.util.List;
import java.util.Iterator;



public class GestorAviones {

    private Scanner scanner;
    private File_Pry archivoAviones; // Instancia para manejar el archivo
    private final String NOMBRE_ARCHIVO = "Aviones.txt";
    private List<String> listaAviones = new ArrayList<>();
    private GestorAviones gestorAviones;
   

    public GestorAviones() {
        
        this.scanner = new Scanner(System.in);
        this.archivoAviones = new File_Pry(null, "BaseDatos", "|");
        this.archivoAviones.Crear_Archivo(NOMBRE_ARCHIVO);
        
    }   
            public String ID;
            public int Sillas;

            
    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n GESTIÓN DE AVIONES ");
            System.out.println("1. Ingresar Avion");
            System.out.println("2. Retirar Avion");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    ingresarAvion();
                    break;
                case 2:
                    retirarAvion();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

        public void ingresarAvion(){
                 
                
                System.out.println("\n INGRESE DATOS DE AVION");
                
                System.out.println("Asignele un ID a su avion");
                  this.ID = scanner.nextLine();
                  
                System.out.println("Asignele la cantidad de sillas a su avion");
                    this.Sillas = scanner.nextInt();
                  
                     String nuevoAvion = ID +"|"+ Sillas;
                     listaAviones.add(nuevoAvion);
                    archivoAviones.Escribir_Archivo(nuevoAvion);
                    
                    System.out.println("Datos guardados con exito");
        }
    
            
        public void retirarAvion(){
            
            System.out.println("\n RETIRO DE AVIONES");
            
            System.out.println("Los aviones disponibles son:");
                for( String Avion : listaAviones){
                    
                    System.out.println(Avion);
                }
                
                System.out.println("Elija el ID de avion que desea retirar");
                
                    String AvionRetirar = scanner.nextLine();
                    boolean encontrado = false;
                    
                    Iterator<String> Busca = listaAviones.iterator();
                    while(Busca.hasNext()){
                        String Avion = Busca.next();
                        
                        if(Avion.startsWith(AvionRetirar + ":")|| Avion.equals(AvionRetirar)){
                            Busca.remove();
                            encontrado = true;
                            System.out.println("Avion retirado con exito");
                            break;
                        
                        }
                            
                    }
                        if(!encontrado){
                        
                                System.out.println("Error ID ingresado no encontrado");
                  }
       }
        
        public void mostrarAviones() {

        System.out.println("\n <LISTA DE AVIONES>");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoAviones.Ruta_completa))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (Exception e) {
            System.out.println("No hay aviones registrados.");
        }
    }
 }
        
   