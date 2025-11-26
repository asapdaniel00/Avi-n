package avion;

import java.util.Scanner.*;


public class Avion {
    
public String ID;
public int CantP;
private String Aerolinea;
public String Ruta;

    public Avion(String ID, int CanP, String Aer, String Rt) {
        this.ID = ID;
        this.CantP = CanP;
        this.Aerolinea = Aer;
        this.Ruta = Rt;
    }

    public String getAerolinea() {
        return Aerolinea;
    }

    public void setAerolinea(String Aerolinea) {
        this.Aerolinea = Aerolinea;
    }


}


