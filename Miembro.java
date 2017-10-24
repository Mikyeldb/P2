package practica.pkg1.ep2;

import java.util.ArrayList;

public class Miembro 
{
    private static int proximoSocio = 1;
    private int numSocio;
    private final String nombre;
    private ArrayList<Moto> motos;             //motos que posee actualmente
    
    public Miembro(String nombre)
    {
        motos = new ArrayList<Moto>();
        this.nombre = nombre;
        numSocio = proximoSocio;
        proximoSocio++;
    }
    
    @Override
    public String toString(){
        String res = ("Miembro con numero de socio "+numSocio+" y nombre "+nombre);
        return res;
    }
    
    public void setMoto(Moto moto)
    {
        motos.add(moto);
    }
    
    public ArrayList<Moto> getMotos(){
        return motos;
    }
    
    public int calcularCantidadMotos()
    {
        return motos.size();
    }
    
    public float calcularPrecioMotos()
    {
        float precioTotal = 0;
        for(int i = 0; i < motos.size(); i++)
        {
            precioTotal += motos.get(i).getCoste();
        }
        return precioTotal;
    }

    public int getNumSocio() {
        return numSocio;
    }

    public String getNombre() {
        return nombre;
    }
}
