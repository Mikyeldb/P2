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
public class Moto {
    
    private final String modelo;
    private final int caballos;
    private final float coste;
    private String matricula;
    private int socio;
    private int otrosGastos;
    
    
    public Moto(String modelo, int caballos, float coste, String matricula, int g){
        
        this.modelo = modelo;
        this.caballos = caballos;
        this.coste = coste;
        this.matricula = matricula;
        socio = 0;
        otrosGastos = g;
    }
    
    @Override
    public String toString(){
        return ("Motocicleta: Modelo "+modelo+" "+caballos+" CC "+coste+" € Matricula: "+matricula);
    }
    
    
    public void setSocio(int s){
        socio = s;
    }
    
    public int getSocio(){
        return socio;
    }
    
    public float getCoste(){
        return coste;
    }
    
    public String getModelo(){
        return modelo;
    }
    
    public int getCaballos(){
        return caballos;
    }
    
    public String getMatricula(){
        return matricula;
    }
    
    public int getOtrosGastos(){
        return otrosGastos;
    }
    
    public void anyadirOtrosGastos(int f){
        otrosGastos += f;
    }
    
    public void setOtrosGastos(int f){
        otrosGastos = f;
    }
}