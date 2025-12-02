/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avion;

import java.io.*;

/**
 *
 * @author karol
 */
public class File_Pry {
    
 String Ruta;
    String Carpeta;
    String Ruta_completa;
    String Seperador;

    //Metodo constructor que recibre R: La ruta donde quisiera crear el archivo, El nombre de la carpeta si quiero que queden dentro de una carpeta
    public File_Pry(String R, String C, String S){
        this.Ruta = R;
        this.Carpeta = C;
        this.Seperador = S;
        this.Ruta_completa=null;
    }
    
    //Metodo que crea un archivo y recibe por parámetro el nombre del archivo a crear
    public Boolean Crear_Archivo(String Nombre){
        boolean creado = false;
        File Archivo;
        
        //se crea la ruta de la carpeta
        if(this.Carpeta!=null){
            if(this.Ruta!=null){
                this.Ruta_completa=this.Ruta+"\\"+this.Carpeta+"\\";
            }
            else{
                this.Ruta_completa=this.Carpeta+"\\";
            }
        }else{
            if(this.Ruta!=null){
                this.Ruta_completa=this.Ruta+"\\";
            }
        }
        
        //Se crea la carpeta de acuerdo a los solicitado
        if(this.Ruta_completa!=null){
            File Folder = new File(this.Ruta_completa);
            if(!Folder.exists()){
                if(Folder.mkdirs()){
                    System.out.println("Carpeta: "+this.Ruta_completa+" Creada con éxito");
                }
                else{
                    System.out.println("Carpeta: "+this.Ruta_completa+" NO CREADA con éxito");                
                }
            }
            else{
                System.out.println("NO SE CREO LA CARPETA");
            }        
        }
        
        //Se crean los archivos dentro de las carpetas si así se solicitaron crear
        if(this.Ruta_completa!=null){ this.Ruta_completa+=Nombre;}
        else{this.Ruta_completa = Nombre;}
        
        try{
            Archivo = new File(this.Ruta_completa);
            if(Archivo.createNewFile()){
                System.out.println("Archivo: "+Nombre+" Creada con exito en la ruta "+this.Ruta_completa);
                creado = true;
            }
            else{
                System.out.println("Archivo: "+Nombre+" NO CREADO con exito en la ruta "+this.Ruta_completa);
                creado = false;
            }
        }catch (IOException e){
            System.out.println("EXCEPTION: "+e);
        }
        
        return creado;
    }
    
    //Método para escribir al final del archivo
    public void Escribir_Archivo(String Informacion){       
        System.out.println("DATOS: "+Informacion);
	//Ahora se sobreescribirá el archivo con toda la información de los datos.
	try {
            FileWriter FW = new FileWriter(this.Ruta_completa, true);
            BufferedWriter Escribe = new BufferedWriter(FW); 

	    Escribe.write(Informacion+"\n");
	    Escribe.close();

        }catch(IOException e) {e.printStackTrace(System.out);}	//modificado el (system.out) de {e.ptintStackTracecr
	
    }
    /*
    //Método para actualizar un registro de un archivo a partir de un campo único
    public void Escribir_Archivo(String ID, int Pos, Char Separador, String Linea_Nueva){
	Leer_Archivo = null;
	String Datos = "";
	String Linea; 	
	
	//Leemos el archivo hasta que se recorra completamente y cuando se encuentre el registro a actualizar se actualizad
	try{
		Leer_Archivo = new BufferedReader(new FileReader(this.Ruta_Completa));
		
		// Almacenar una linea del objeto 'br' en una variable tipo String.
	        Linea = Leer_Archivo.readLine();

		// Crear un ciclo que permita leer cada linea del objeto mientras no sea NULA
		while(Linea != null){
			String[] Arreglo_Datos = Linea.split(Separador);
			if(Arreglo_Datos[Pos]==ID){
				Datos+=Linea_Nueva+"\n";
			}
			else{
				Datos += Linea + "\n";
			}
			Linea = Leer_Archivo.readLine();
			
		}
		Leer_Archivo.close(); // Cerramos el archivo
	}catch(IOException e) {System.out.println("EXCEPTION: "+e);}

	//Ahora se sobreescribirá el archivo con toda la información de los datos.
	try {

            FileWriter FW = new FileWriter(this.Ruta_Completa, true);

            BufferedWriter Escritor = new BufferedWriter(FW));

	    Escritor.write(Datos);
	    Escritor.close();

        }catch(IOException e) {e.printStackTrace();}	

    }
*/
    //Método que devuelve un registro en un archivo
    public String Encontrar_Registro(String ID, int Pos){
	BufferedReader Leer_Archivo;
	String Linea="";
	boolean Seguir = true; 
	
	//Leemos el archivo hasta que se encuentre el registro solicitado
	try{
            Leer_Archivo = new BufferedReader(new FileReader(this.Ruta_completa));
		
            // Almacenar una linea del objeto 'br' en una variable tipo String.
	    Linea = Leer_Archivo.readLine();
            // Crear un ciclo que permita leer cada linea del objeto mientras no sea NULA
            while(Seguir){
                String[] Arreglo_Datos = Linea.split(this.Seperador);
                //Validamos si el dato a encontrar está en la línea deseada
		if(Arreglo_Datos[Pos].equals(ID)){
                    Seguir = false;	
		}
		else{
                    Linea = Leer_Archivo.readLine();
		}
                
            }
            Leer_Archivo.close(); // Cerramos el archivo
	}catch(IOException e) {System.out.println("EXCEPTION: "+e);}

	return Linea;
    }
}
