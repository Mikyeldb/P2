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
}