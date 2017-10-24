package practica.pkg1.ep2;

import java.util.ArrayList;

public class Miembro 
{
    private static int proximoSocio = 1;
    private int numSocio;
    private final String nombre;
    
    public Miembro(String nombre)
    {
        this.nombre = nombre;
        numSocio = proximoSocio;
        proximoSocio++;
    }
    
    @Override
    public String toString()
    {
        String res = ("Miembro con numero de socio "+numSocio+" y nombre "+nombre);
        return res;
    }

    public int getNumSocio() 
    {
        return numSocio;
    }

    public String getNombre() 
    {
        return nombre;
    }
}
