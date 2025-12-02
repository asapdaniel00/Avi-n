package avion;

import java.util.*;


public class Avion {
    
public String ID;
public int CantP;
private String Aerolinea;
public String Ruta;
public String TipoDeVuelo;

    public Avion(String ID, int CanP, String Aer, String Rt, String TiV) {
        this.ID = ID;
        this.CantP = CanP;
        this.Aerolinea = Aer;
        this.Ruta = Rt;
        this.TipoDeVuelo = TiV;
    }

    public String getAerolinea() {
        return Aerolinea;
    }

    public void setAerolinea(String Aerolinea) {
        this.Aerolinea = Aerolinea;
    }


}


