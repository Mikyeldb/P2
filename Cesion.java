package practica.pkg1.ep2;

import java.util.Date;

public class Cesion 
{
    private final Moto moto;
    private final Miembro miembroOrigen, miembroDestino;
    private final Date fecha;
    
    public Cesion(Moto moto, Miembro miembroOrigen, Miembro miembroDestino, Date 
    fecha)
    {
        this.moto = moto;
        this.miembroOrigen = miembroOrigen;
        this.miembroDestino = miembroDestino;
        this.fecha = fecha;
    }
    
    @Override
    public String toString(){
        String res = (moto.toString()+"\r\n    Miembro origen: "+miembroOrigen.toString()+"\r\n    Miembro destino: "+miembroDestino.toString()+"\r\n    Fecha: "+fecha);
        return res;
    }
    
    public Moto getMoto(){
        return moto;
    }
    
    public Miembro getMiembroOrigen(){
        return miembroOrigen;
    }
    
    public Miembro getMiembroDestino(){
        return miembroDestino;
    }
    
    public Date getDate(){
        return fecha;
    }
    
}

