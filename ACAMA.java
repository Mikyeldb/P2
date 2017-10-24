/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg1.ep2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class ACAMA {
    
    private ArrayList<Moto> motos;
    private ArrayList<Cesion> cesiones;
    private ArrayList<Miembro> miembros;
    
    
    public ACAMA()
    {
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
    
    public int getCantidadMiembros()
    {
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
    
    public float calcularPrecioMotos(int id)
    {
        float precioTotal = 0;
        for(int i = 0; i < motos.size(); i++)
        {
            if(motos.get(i).getSocio() == id)
            {
                precioTotal += motos.get(i).getCoste();
            }
        }
        return precioTotal;
    }

    

    public void mostrarMotosMiembro(int id) 
    {
        for(int j = 0; j < motos.size(); j++)
        {
            if(motos.get(j).getSocio() == id)
            {
                System.out.println(mostrarMoto(j+1));
            }
        }
    }

    public String mostrarMoto(int moto) 
    {
        return "        "+moto+" - "+motos.get(moto - 1).toString(); 
    }

    public Moto getMoto(int moto) 
    {
        return motos.get(moto - 1);
    }

    public void registrarCesion(int moto, int origen, int destino) 
    {
        Cesion c = new Cesion(motos.get(moto),
                miembros.get(origen),
                miembros.get(destino), new Date());
                                         
        cesiones.add(c);
        motos.get(moto).setSocio(moto);
    }
    
    public void mostrarMiembrosYMotos()
    {                                      //FUNCION mostrarMiembrosYMotos
        if(miembros.isEmpty())                                                    //Parametros: ACAMA
        {                                                                                       //Return: void
            System.out.println("No hay ningun miembro registrado en la"                         //                
                + " asociacion ACAMA.");                                                        //Esta funcion muestra todos los miembros registrados en acama
        }                                                                                       //junto con la informacion de sus respectivas motos.
        else                                                                                    //
        {                                                                                       //
            for(int i = 0; i < miembros.size(); i++)                                //  
            {                                                                                   //
                System.out.println(miembros.get(i).toString());                      //
                                                                                                //
                if(calcularCantidadMotos(miembros.get(i).getNumSocio()) == 0)                     //
                {                                                                               //
                    System.out.println("    Ninguna moto en posesion.");                        //
                }                                                                               //
                else                                                                            //
                {                                                                               //
                    System.out.println("    Motos en posesion: ");                              //
                    mostrarMotosMiembro(miembros.get(i).getNumSocio());                         //
                }                                                                               //
            }                                                                                   //
        }                                                                                       //
    }
    
    public int calcularCantidadMotos(int id) 
    {
        int cantidad = 0;
        for(int i = 0; i < motos.size(); i++)
        {
            if(motos.get(i).getSocio() == id)
            {
                cantidad++;
            }
        }
        return cantidad;
    }
    
    public ArrayList<Miembro> mostrarMiembrosConMotoYMotos()
    {                 //FUNCION mostrarMiembrosConMotoYMotos                                                                             //Parametros: ACAMA
        ArrayList<Miembro> res = new ArrayList<Miembro>();                                      //Return: ArrayList<Miembro>
        int cont = 1;                                                                           //
                                                                                                //Esta funcion muestra los miembros que poseen al menos una moto
        if(getCantidadMiembros() == 0)                                                    //junto con la informacion de sus respectivas motos. Devuelve
        {                                                                                       //un array con los miembros que poseen al menos una moto.
            System.out.println("No hay ningun miembro registrado en la"                         //                
                + " asociacion ACAMA.");                                                        //
        }                                                                                       //
        else                                                                                    //
        {                                                                                       //
            for(int i = 0; i < miembros.size(); i++)                                //  
            {                                                                                   //
                if(calcularCantidadMotos(miembros.get(i).getNumSocio()) > 0)                      //
                {                                                                               //
                    System.out.println("Miembro numero "+cont+":");                             //
                    System.out.println("    "+miembros.get(i).toString());           //
                                                                                                //
                    System.out.println("    Motos en posesion: ");                              //
                                                                                                //
                    mostrarMotosMiembro(miembros.get(i).getNumSocio());                            //
                                                                                                //
                    res.add(miembros.get(i));                                        //
                    cont++;                                                                     //
                }                                                                               //
            }                                                                                   //
        }                                                                                       //
        return res;                                                                             //
    }
    
    public ArrayList<Miembro> mostrarPosiblesMiembrosDestinoCesion(          //FUNCION mostrarPosiblesMiembrosDestinoCesion
            int origen, int moto)
    {                                                              //Parametros: ACAMA, int, int
                                                                                                //Return: ArrayList<Miembro>
        ArrayList<Miembro> destinatarios = new ArrayList<Miembro>();                            //
        int i, cont = 1;                                                                        //Esta funcion muestra los miembros que a los que se les puede ceder
        boolean existe = false;                                                                 //una motocicleta a partir del coste de esta y de un miembro origen.
        float precio;                                                                           //Devuelve un array con los miembros a los que se les puede ceder 
        float precioOrigen = motos.get(moto).getCoste();   //una moto de coste igual al parametro 3.
                                                                                                //
        for (i = 0; i < miembros.size(); i++)
        {                                       //
            precio = calcularPrecioMotos(miembros.get(i).getNumSocio());                          //
            precio += precioOrigen;                                                             //
                                                                                                //
            if(precio <= 6000 && i != origen)                                                   //
            {                                                                                   //
                System.out.println(miembros.get(i).toString() +
                        "(introduce " + cont + ").");                                                            //
                existe = true;                                                                  //
                cont++;                                                                         //
                destinatarios.add(miembros.get(i));                                  //
            }                                                                                   //
        }                                                                                       //
                                                                                                //
        if(!existe)                                                                             //
        {                                                                                       //
            System.out.println("Ningun miembro puede aceptar esta moto sin "
                    + "superar los 6000€.");                                                           //
        }                                                                                       //
        return destinatarios;                                                                   //
    }                                                                                           //
    
    public ArrayList<Miembro> mostrarPosiblesMiembros(float coste)
    {         //FUNCION mostrarPosiblesMiembros
                                                                                                //Parametros: ACAMA, float
        int i, cont = 1;                                                                        //Return: ArrayList<Miembro>
        float precio;                                                                           //
        ArrayList<Miembro> destinatarios = new ArrayList<Miembro>();                            //Esta funcion muestra los miembros que pueden guardar una moto
                                                                                                //recien registrada y de un coste determinado. Devuelve un array 
        System.out.println("\nMiembros que pueden guardar la motocicleta:\n");                  //con los miembros que pueden almacenar una moto de coste igual
        for (i = 0; i < miembros.size(); i++)
        {                                       //al parametro 2.
            precio = calcularPrecioMotos(miembros.get(i).getNumSocio());                          //
            precio += coste;                                                                    //
                                                                                                //
            if(precio <= 6000)                                                                  //
            {                                                                                   //
                System.out.println("    "+miembros.get(i).toString()
                        + "(introduce " + cont + ").");                                                            //
                cont++;                                                                         //
                destinatarios.add(getMiembros().get(i));                                  //
            }                                                                                   //
        }                                                                                       //
        return destinatarios;                                                                   //
    }                                                                                           //
    
    public void generarFichero(Scanner sc)
    {                                 //FUNCION generarFichero
                                                                                                //Parametros: ACAMA, Scanner
        String nombreFich;                                                                      //Return: void
                                                                                                //  
        System.out.println("Se va a crear un fichero en el que se guardaran los miembros"       //Esta funcion pide un nombre de fichero y genera un fichero de texto
                + " registrados, sus motocicletas y las cesiones registradas.");                //con el nombre obtenido y en el que guarda la informacion de los
        System.out.println("Introduce el nombre del fichero: ");                                //miembros registrados en las asociacion acama junto con la informacion
        sc.nextLine();                                                                          //de las respectivas motos de cada miembro y junto con las cesiones
        nombreFich = sc.nextLine();                                                             //registradas en la asociacion.
                                                                                                //
        try 
        {                                                                                   //  
            File archivo = new File(nombreFich+".txt");                                         //
            FileWriter escribir = new FileWriter(archivo,true);                                 //
                                                                                                //
            if(getCantidadMiembros() == 0)                                                //
            {                                                                                   //    
                escribir.write("No hay ningun miembro registrado en la"                         //                
                    + " asociacion ACAMA.\r\n\r\n");                                            //
            }                                                                                   //
            else                                                                                //
            {                                                                                   //
                for(int i = 0; i < getCantidadMiembros(); i++)                            //  
                {                                                                               //
                    escribir.write(getMiembros().get(i).toString()+"\r\n");               //
                                                                                                //
                    if(calcularCantidadMotos(i + 1) == 0)                 //
                    {                                                                           //
                        escribir.write("    Ninguna moto en posesion.\r\n\r\n");                //
                    }                                                                           //
                    else                                                                        //
                    {                                                                           //
                        escribir.write("    Motos en posesion: \r\n");                          //
                                                                                                //
                        for(int j = 0; j < calcularCantidadMotos(i + 1)
                                ; j++)                                                          //
                        {                                                                   //
                            escribir.write("        "+(j+1)+" - "+
                                    mostrarMoto(j + 1)+"\r\n");                   //
                        }                                                                   //
                        escribir.write("\r\n");                                         //
                    }                                                                           //
                }                                                                               //
            }                                                                                   //
                                                                                                //
                                                                                                //
            if(getCantidadCesiones() == 0)                                                //
            {                                                                               //    
                escribir.write("No hay ninguna cesion registrada en la"                         //                
                    + " asociacion ACAMA.");                                                    //
            }                                                                                   //
            else                                                                                //
            {                                                                                   //    
                escribir.write("Cesiones registradas:\r\n\r\n");                                //
                for (int i = 0; i < getCantidadCesiones(); i++)                           //
                {                                                                               //
                    escribir.write("Cesion "+(i+1)+":\r\n");                                    //    
                    escribir.write("    "+getCesiones().get(i).toString());               //
                }                                                                               //
                                                                                                //
                escribir.write("\r\n\r\n");                                                     //
            }                                                                                   //
                                                                                                //
            escribir.close();                                                                   //
        }
        catch (IOException ex) 
        {                                                              //
            System.out.println("Error al escribir");                                            //
        }                                                                                       //
    }                                                                                           //
    
    public boolean motoAlmacenable(float coste)
    {                                //FUNCION motoAlmacenable
                                                                                                //Parametros: ACAMA, float
        boolean res = false;                                                                    //Return: boolean
        float precio;                                                                           //
                                                                                                //Esta funcion calcula a partir del coste de una moto, si dicha moto
        for(int i = 0; i < miembros.size(); i++)
        {                                       //puede ser almacenada por algun miembro de la asociacion acama.
            precio = calcularPrecioMotos(miembros.get(i).getNumSocio());                              //
            precio += coste;                                                                    //
                                                                                                //
            if(precio <= 6000) 
            {
                res = true;                                                                     //
            }
                
        }                                                                                       //
                                                                                                //
        return res;                                                                             //
    }                                                                                           //
    
    public void mostrarTodasLasMotosYSuMiembro()
    {                             //FUNCION mostrarTodasLasMotosYSuMiembro
                                                                                                //Parametros: ACAMA
        int aux;                                                                                //Return: void
                                                                                                //
        for (int i = 0; i < getCantidadMotos(); i++)
        {                                     //Esta funcion muestra todas las motos de la asociacion acama 
                                                                                                //y el miembro asociado a cada moto.
            System.out.println((i+1)+" - "+getMotos().get(i).toString());                 //
                                                                                                //
            aux = getMotos().get(i).getSocio();                                           //
            System.out.println("Miembro que la posee: "                                         //
                    +getMiembros().get(aux - 1).toString());                              //
        }                                                                                       //
                                                                                                //
    }                                                                                           //
    
    
    public void mostrarCesiones()
    {                                            //FUNCION mostrarCesiones
                                                                                                //Parametros: ACAMA
        for (int i = 0; i < getCantidadCesiones(); i++){                                  //Return: void
                                                                                                //
            System.out.println("Cesion "+(i+1)+":");                                            //Esta funcion muestra todas las cesiones juntos con todos sus datos.
                                                                                                //
            System.out.println("    "+getCesiones().get(i).toString());                   //
        }                                                                                       //
                                                                                                //
    }        
    
}
