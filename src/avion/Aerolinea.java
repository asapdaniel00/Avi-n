package avion;

import java.util.*;

public class Aerolinea {
    
    public String Cantidad;
    private String Avion;
    public String Ruta;
    public String Nombre;

    public Aerolinea(String Canti, String Av, String Ruta) {
        this.Cantidad = Canti;
        this.Avion = Av;
        this.Ruta = Ruta;
    }

    public String getAvion() {
        return Avion;
    }

    public void setAvion(String Avion) {
        this.Avion = Avion;
    }
    
}
