/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg1.ep2;

import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class ACAMA {
    
    private ArrayList<Moto> motos;
    private ArrayList<Cesion> cesiones;
    private ArrayList<Miembro> miembros;
    private float costeMaximo = 0;
    
    public ACAMA(){
        motos = new ArrayList<Moto>();
        cesiones = new ArrayList<Cesion>();
        miembros = new ArrayList<Miembro>();
        
    }

    public void regitrarMiembro(String nombre) {
        miembros.add(new Miembro(nombre));
    }

    public void registrarMoto(Moto m) {
        motos.add(m);
    }
    
    public void registrarCesion(Cesion c) {
        cesiones.add(c);
    }
    
    public int getCantidadMiembros(){
        return miembros.size();
    }
    
    public int getCantidadMotos(){
        return motos.size();
    }
    
    public int getCantidadCesiones(){
        return cesiones.size();
    }
    
    public ArrayList<Cesion> getCesiones (){
        return cesiones;
    }
    
    public ArrayList<Miembro> getMiembros (){
        return miembros;
    }

    public ArrayList<Moto> getMotos (){
        return motos;
    }
    
    public void setCosteMaximo(float costeMaximo) 
    {
        this.costeMaximo = costeMaximo;
    }

    public float getCosteMaximo() 
    {
        return costeMaximo;
    }
    
    
    public ArrayList<Miembro> miembrosConMasCesiones()
    {
        ArrayList<Miembro> miembrosMasCesiones = new ArrayList<Miembro>();
        int motosCedidas = 0;
        for (int i = 0; i < miembros.size(); i++)
        {
            int motos = 0;
            for(int  j = 0; j < cesiones.size(); j++)
            {
                if(miembros.get(i).getNumSocio() == cesiones.get(j).getMiembroDestino().getNumSocio())
                {
                    motos++;
                }

                if(motos > motosCedidas)
                {
                    motosCedidas = motos; 
                    miembrosMasCesiones.clear();
                    miembrosMasCesiones.add(miembros.get(i));
                }
                else if(motos == motosCedidas)
                {
                        miembrosMasCesiones.add(miembros.get(i));
                }
            }
        }
        return miembrosMasCesiones;
    }
}