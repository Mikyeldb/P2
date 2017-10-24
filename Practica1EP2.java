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
 * @author juan y miquel
 */
public class Practica1EP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ACAMA acama = new ACAMA();                                                              //CREACIÓN DE VARIABLES
        Scanner sc = new Scanner(System.in);                                                    //
        boolean salir = false;                                                                  //
        
        
        float costeMaximo = -1;
        while(costeMaximo == -1)
        {
            try                                                                    
            {
                System.out.print("Introduce un coste maximo para todos los miembros: ");
                costeMaximo = sc.nextFloat();
            }
            catch(Exception e)
            {
                System.out.print("\nError al introducir el coste, vuelva a intentarlo. ");
                costeMaximo = -1;
            }
            sc.nextLine(); 
        }
        
        acama.setCosteMaximo(costeMaximo);
        
        
        do
        {
            int menu = menu(sc);                                                                //Esta función muestra el menú y permite elegir una opción

            while (menu < 1 || menu > 10){                                                       //Esta parte comprueba que se introduce una opcion correcta
                System.out.println("Debes introducir un valor entre 1 y 10.");                   //si no es asi, se vuelve a mostrar el menu
                sc.nextLine();                                                                  //
                menu = menu(sc);                                                                //
            }

            switch(menu){

                case 1:                                                                         //REGISTRAR NUEVO MIEMBRO EN EL SISTEMA////////////////////////////////////////
                    System.out.println("Registrando nuevo miembro en el sistema.\n");           //
                    System.out.println("Introduce el nombre del nuevo miembro.");               //se pide el nombre y se guarda al miembro
                    sc.nextLine();                                                              //en el array de miembros de acama.
                    String nombre = sc.nextLine();                                              //
                    acama.regitrarMiembro(nombre);                                              //
                    System.out.println("Nuevo miembro registrado correctamente.");              //
                    break;                                                                      ////////////////////////////////////////////////////////////////////////////////

                    
                    
                case 2:                                                                         //RESGISTRAR NUEVA MOTOCICLETA EN EL SISTEMA/////////////////////////////////////
                                                                                                //
                    float coste;                                                                //
                    String modelo, matricula;                                                   //  
                    int caballos, select, otrosgastos;                                                       //
                    ArrayList<Miembro> destinos = new ArrayList<Miembro>();                     //
                                                                                                //
                    if(acama.getCantidadMiembros() == 0)                                        //si no existen miembros registrados...
                    {                                                                           //(no se puede registrar una motocicleta)    
                        System.out.println("No hay ningun miembro regitrado en la"              //                
                                + " asociacion ACAMA.\nPara dar de alta una moto es necesario"  //
                                + " que exista un miembro que pueda guardala en"                //
                                + " su garaje");                                                //
                    }                                                                           //
                    else                                                                        //si existen miembros registrados...
                    {                                                                           //(se podra registrar una motocicleta siempre que
                        System.out.println("Registrando nueva motocicleta.\n");                 //alguno de los miembros pueda guardarla sin
                        System.out.println("Introduce el coste de la motocicleta:");            //superar os 6000€)
                        sc.nextLine();                                                          //Se comienza el registro de la motocicleta y se pide
                        try                                                                     //el precio de la misma.
                        {                                                                       //
                            coste = sc.nextFloat();                                             //
                        }                                                                       //
                        catch(Exception e){                                                     //
                            coste = -1;                                                         //
                        }                                                                       //
                                                                                                //
                        if(coste < 0 || coste > 1000000000)                                     //
                            coste = controlarValoresEnteros((int)coste, 0, 1000000000, sc);     //
                                                                                                //
                        if(!motoAlmacenable(acama,coste, acama.getCosteMaximo()))               //si existen miebros registrados pero ninguno puede guardar la moto
                        {                                                                       //sin exceder los 6000€...
                            System.out.println("No hay ningun miembro regitrado en "            //Se cancela el registro de la motocicleta.    
                                    + "la asociacion ACAMA que pueda guardar esta moto "        //
                                    + "sin superar el limite de 6000 euros."                    //
                                    + "\nEsta moto no puede darse de alta en "                  //
                                    + "la asociacion ACAMA.");                                  //    
                        }                                                                       //
                        else{                                                                   //si existen miembros registrados y alguno puede almacenar la moto
                                                                                                //sin exceder los 6000€
                            System.out.println("Introduce el modelo de la motocicleta:");       //Se piede el modelo de la motocicleta.
                            sc.nextLine();                                                      //
                            modelo = sc.nextLine();                                             //
                                                                                                //
                                                                                                //
                            System.out.println("Introduce los caballos de la motocicleta:");    //
                            try                                                                 //
                            {                                                                   //
                                caballos = sc.nextInt();                                        //
                            }                                                                   //
                            catch(Exception e){                                                 //
                                caballos = -1;                                                  //    
                            }                                                                   //
                                                                                                //    
                            if(caballos < 0 || caballos > 2000)                                 //
                                caballos = controlarValoresEnteros(caballos, 0, 2000, sc);      //
                                                                                                //    
                            System.out.println("Introduce la matricula de la motocicleta"       //Se pide la matricula de la motocicleta.    
                                    + "(4 digitos seguidos por 3 letras minusculas "            //    
                                    + "esceptuando 'q' y 'ñ': ddddlll):");                      //
                            sc.nextLine();                                                      //
                            matricula = sc.nextLine();                                          //
                                                                                                //
                            boolean repetida = matriculaRepetida(acama, matricula);             //Aqui se calcula si la matricula obtenida esta ya registrada.
                                                                                                //
                            while(repetida || !matriculaCorrecta(matricula))                    //Mientras la matricula este repetida o sea invalida se pide de nuevo.
                            {                                                                   //
                                if(repetida)                                                    //
                                {                                                               //
                                    System.out.println("Ya existe una moto "                    //
                                            + "con esta matricula.");                           //
                                }                                                               //
                                matricula = sc.nextLine();                                      //
                                repetida = matriculaRepetida(acama, matricula);                 //
                            }                                                                   
                            
                            System.out.println("Introduce otros gastos para la motocicleta:");  //
                            try                                                                 //
                            {                                                                   //
                                otrosgastos = sc.nextInt();                                     //
                            }                                                                   //
                            catch(Exception e){                                                 //
                                otrosgastos = -1;                                               //    
                            }                                                                   //
                                                                                                //    
                            if(otrosgastos < 0 || otrosgastos > 1000000)                        //
                                otrosgastos = controlarValoresEnteros(otrosgastos,
                                        0, 1000000, sc);                                        //
                                                                                                //
                                                                                                //
                            Moto moto = new Moto(modelo,caballos,coste,matricula,otrosgastos);  //Se crea la moto.
                                                                                                //
                            acama.registrarMoto(moto);                                          //Se guarda la moto en el array de motos de acama. 
                                                                                                //
                            destinos = mostrarPosiblesMiembros(acama, coste,                    //Se calculan los miembros registrados que pueden almacenar la moto.
                                    acama.getCosteMaximo());                                    //
                                                                                                //
                            if (destinos.size() > 0)                                            //Si existe algun miembro registrado capaz de almacenar la moto...
                            {                                                                   //
                                System.out.println("Selecciona el miembro que guardara esta "   //Se selecciona uno de los posibles miembros.
                                        + "motocicleta.");                                      //
                                try                                                             //
                                {                                                               //
                                    select = sc.nextInt();                                      //
                                }                                                               //
                                catch(Exception e){                                             //
                                    select = -1;                                                //
                                }                                                               //
                                                                                                //
                                if (select < 1 || select > destinos.size()){                    //    
                                    select = controlarValoresEnteros(select,1,                  //
                                            destinos.size(), sc);                               //
                                }                                                               //
                                                                                                //
                                moto.setSocio(destinos.get(select - 1).getNumSocio());          //Se asigna a la moto el miembro que la va a almacenar.
                                                                                                //
                                acama.getMiembros().get(destinos.get(select - 1)                //Se guarda la moto en el array de motos del miembro que la va a guardar.
                                        .getNumSocio() - 1).getMotos().add(moto);               //
                                                                                                //
                                System.out.println("Motocicleta guardada correctamente en el "  //
                                    + "sistema.\nLa motocicleta ha sido asignada a: "           //
                                    +acama.getMiembros().get(destinos.get(select - 1)           //
                                        .getNumSocio() - 1).toString());                        //
                            }                                                                   //
                        }                                                                       //    
                    }                                                                           //
                    break;                                                                      ////////////////////////////////////////////////////////////////////////////////

                    
                    
                case 3:                                                                         //REGISTRAR UNA CESIÓN//////////////////////////////////////////////////////////
                                                                                                //
                    int origen, moto, destino;                                                  //
                    ArrayList<Miembro> destinatarios =                                          //
                            new ArrayList<Miembro>();                                           //
                    ArrayList<Miembro> miembrosConMoto =                                        //
                            new ArrayList<Miembro>();                                           //
                                                                                                //
                    if(acama.getCantidadMotos() == 0)                                           //Si no existen motos registradas...
                    {                                                                           //(no se puede registrar una cesion).
                        System.out.println("No existe ninguna moto registrada"                  //
                                + "\nNo puede realizarse ninguna cesion.");                     //
                    }                                                                           //
                    else if(acama.getCantidadMiembros() < 2)                                    //Si no existen miembros registrados suficientes para hacer la cesión...
                    {                                                                           //(no se puede registrar una cesion).
                        System.out.println("El numero de miembros registrados en la "           //
                                + "asociacion es inferior a 2.");                               //
                        System.out.println("No puede realizarse ninguna cesion.");              //
                    }                                                                           //
                    else                                                                        //Si existe al menos 1 moto registrada y al menos 2 miembros registrados...
                    {                                                                           //(se puede hacer la cesión)
                        System.out.println("Miembros que disponen de una moto para ceder:");    //
                        miembrosConMoto = mostrarMiembrosConMotoYMotos(acama);                  //Se muestran los miembros que tienen motocicletas.
                                                                                                //
                        System.out.println("Selecciona el miembro origen para la cesion.");     //Se selecciona uno de los miembros mostrados como miembro origen para la cesion.
                        sc.nextLine();                                                          //
                        try                                                                     //
                        {                                                                       //
                            origen = sc.nextInt();                                              //
                        }                                                                       //
                        catch(Exception e){                                                     //
                            origen = -1;                                                        //  
                        }                                                                       //
                                                                                                //
                        if (origen < 1 || origen > miembrosConMoto.size()){                     //   
                            origen = controlarValoresEnteros(origen,1,miembrosConMoto.size(),   //
                                    sc);                                                        //
                        }                                                                       //
                                                                                                //
                        System.out.println("Motos del "                                         //Se muestran las motos que puede ceder el miembro origen(todas sus motos).
                                +miembrosConMoto.get(origen - 1).toString()+":");               //
                                                                                                //
                        mostrarMotosDeUnMiembro(miembrosConMoto, (origen - 1));                 //
                                                                                                //
                                                                                                //
                        System.out.println("Selecciona la moto que se va a ceder.");            //Se selecciona una de las motos del miembro origen para ser cedida.
                        sc.nextLine();                                                          //
                        try                                                                     //
                        {                                                                       //
                            moto = sc.nextInt();                                                //
                        }                                                                       //
                        catch(Exception e){                                                     //
                            moto =-1;                                                           //
                        }                                                                       //
                                                                                                //
                        if (moto < 1 || moto > miembrosConMoto.get(origen - 1)                  //
                                .calcularCantidadMotos())                                       //
                        {                                                                       //
                            moto = controlarValoresEnteros(moto,1,miembrosConMoto               //
                                    .get(origen - 1).calcularCantidadMotos(),sc);               //                                             
                        }                                                                       //
                                                                                                //
                        System.out.println("Miembros a los que se les puede ceder la moto sin"  //Se muestran los miembros de la asociacion que pueden guardar la moto que se quiere
                                + " superar el limite de "+acama.getCosteMaximo()+"€:");        //ceder sin superar los 6000€
                                                                                                //
                        destinatarios = mostrarPosiblesMiembrosDestinoCesion(acama,             //
                                (miembrosConMoto.get(origen - 1).getNumSocio() - 1), moto - 1   //
                                    , acama.getCosteMaximo());                                  //
                                                                                                //
                                                                                                //
                        if(destinatarios.size() > 0)                                            //Si existe algun miembro capaz de guardar la moto que se quiere ceder sin superar
                        {                                                                       //los 6000€...
                            System.out.println("Selecciona el miembro destino para la cesion"   //Se selecciona uno de los posibles miembros destino.
                                    + "(usa el numero entre parentesis "                        //
                                    + "no el numero de socio).");                               //
                            sc.nextLine();                                                      //
                            try                                                                 //
                            {                                                                   //
                                destino = sc.nextInt();                                         //
                            }                                                                   //
                            catch(Exception e){                                                 //
                                destino =-1;                                                    //
                            }                                                                   //
                                                                                                //
                            if (destino < 1 || destino > destinatarios.size())                  //
                            {                                                                   //
                                destino = controlarValoresEnteros(destino,1,                    //
                                        destinatarios.size(),sc);                               //                             
                            }                                                                   //
                                                                                                //
                            destino = (destinatarios.get(destino - 1).getNumSocio() -1);        //
                                                                                                //
                            origen = (miembrosConMoto.get(origen - 1).getNumSocio() - 1);       //
                                                                                                //
                            moto--;                                                             //
                                                                                                //
                            System.out.println("Cesion realizada correctamente.");              //Se muestra la correcta realizacion de la cesion.
                            System.out.println("La "+acama                                      //
                                .getMiembros().get(origen).getMotos()                           //    
                                .get(moto).toString()                                           //
                                +"ha sido cedida por el "+acama.getMiembros()                   //
                                        .get(origen).toString()+                                //
                                "y ahora dispone de ella el "+acama.getMiembros()               //
                                        .get(destino).toString());                              //
                                                                                                //            
                            Date fecha = new Date();                                            //
                                                                                                //
                            Cesion c = new Cesion(acama.getMiembros().get(origen).              //Se crea la cesion.
                                            getMotos().get(moto),                               //
                                    acama.getMiembros().get(origen),                            //
                                    acama.getMiembros().get(destino), fecha);                   //
                                                                                                //
                            acama.registrarCesion(c);                                           //Se guarda la cesion en el array de cesiones de acama.
                                                                                                //
                            acama.getMiembros().get(destino).getMotos()                         //Se guarda la moto a ceder en el array de motos del miembro destino
                                    .add(acama.getMiembros().get(origen).                       //(ahora posee la moto).
                                            getMotos().get(moto));                              //
                            acama.getMiembros().get(origen).getMotos()                          //Se elimina la moto a ceder del array de motos del miembro origen
                                    .remove(acama.getMiembros().get(origen).                    //(ya no posee la moto).
                                            getMotos().get(moto));                              //
                                                                                                //
                            acama.getMiembros().get(destino).getMotos().get((acama.getMiembros()//Se le cambia a la moto cedida el el numero de miembro por el del nuevo
                                    .get(destino).getMotos().size() - 1)).setSocio(destino + 1);//miembro que la posee.
                                                                                                //
                        }                                                                       //
                    }                                                                           //
                                                                                                //
                    break;                                                                      //////////////////////////////////////////////////////////////////////////////////////////
                    
                    
                case 4:                                                                         //LISTAR MIEMBROS JUNTO CON SUS DATOS Y SUS MOTOS/////////////////////////////////////////
                    mostrarMiembrosYMotos(acama);                                               //Si hay miembros registrados los muestra junto con sus respectivas motos.
                    break;                                                                      //////////////////////////////////////////////////////////////////////////////////////////
                          
                    
                case 5:                                                                         //LISTAR TODAS LAS MOTOS Y EL MIEMBRO QUE LAS POSEE//////////////////////////////////////
                                                                                                //
                    if(acama.getCantidadMotos() == 0)                                           //Si no hay motos registradas...
                    {                                                                           //(no se pueden mostrar)
                        System.out.println("No existe ninguna moto registrada");                //
                    }                                                                           //
                    else                                                                        //Si hay moyos registradas...
                    {                                                                           //
                        mostrarTodasLasMotosYSuMiembro(acama);                                  //muestra las motos registradas juntos con el miembro que las posee
                    }                                                                           //
                                                                                                //
                    break;                                                                      //////////////////////////////////////////////////////////////////////////////////////////
                    
                    
                case 6:                                                                         //MOSTRAR HISTORIAL DE CESIONES CON TODOS LOS DATOS//////////////////////////////////////
                                                                                                //
                    if(acama.getCantidadCesiones() == 0)                                        //Si no existen cesiones registradas...
                    {                                                                           //(no se pueden mostrar)
                        System.out.println("No existe ninguna cesion registrada");              //
                    }                                                                           //
                    else                                                                        //Si existen cesiones registradas...
                    {                                                                           //
                        mostrarCesiones(acama);                                                 //Muestra todas las cesiones reistradas junto con sus datos.
                    }                                                                           //
                                                                                                //
                    break;                                                                      /////////////////////////////////////////////////////////////////////////////////////////
                    
                case 7:
                    
                    int nuevogasto, m, idmiembro;
                    
                    mostrarTodasLasMotosYSuMiembro(acama);
                    
                    System.out.println("Selecciona la moto a la que añadir el gasto.");         //
                        sc.nextLine();                                                          //
                        try                                                                     //
                        {                                                                       //
                            m = sc.nextInt();                                                //
                        }                                                                       //
                        catch(Exception e){                                                     //
                            m =-1;                                                           //
                        }                                                                       //
                                                                                                //
                        if (m < 1 || m > acama.getCantidadMotos())                                       //
                        {                                                                       //
                            m = controlarValoresEnteros(m,1,acama.getCantidadMotos(),sc);               //                                             
                        }  
                    
                        try                                                                    
                        {
                            System.out.print("Introduce un incremento de otros gastos para la motocicleta: ");
                            nuevogasto = sc.nextInt();
                        }
                        catch(Exception e)
                        {
                            nuevogasto = -1;
                        }
                        sc.nextLine(); 
                        
                        if (nuevogasto < 1 || nuevogasto > 1000000){                     //   
                            origen = controlarValoresEnteros(nuevogasto,1,1000000,   //
                                    sc);                                                        //
                        }
                        
                        acama.getMotos().get(m - 1).anyadirOtrosGastos(nuevogasto);
                        
                        idmiembro = acama.getMotos().get(m - 1).getSocio();
                        
                        for (int k = 0; k < acama.getMiembros().get(idmiembro).calcularCantidadMotos(); k++)
                        {
                            if(acama.getMiembros().get(idmiembro).getMotos().get(k).getModelo().equals(acama.getMotos().get(m - 1).getModelo()))
                            {
                                acama.getMiembros().get(idmiembro).getMotos().get(k).setOtrosGastos(acama.getMotos().get(m - 1).getOtrosGastos());
                            }
                        }
                        
                        System.out.print("Otros gastos actualizados correctamente en la motocicleta seleccionada. ");
                    
                    break;
                case 8: 
                        int s, mot, desti, ori;
                        ArrayList<Miembro> dest = new ArrayList<Miembro>();
                    
                        System.out.println("Selecciona un miembro para eliminarlo:");           //
                        mostrarMiembrosYMotos(acama);

                                                                                                //
                        sc.nextLine();                                                          //
                        try                                                                     //
                        {                                                                       //
                            s = sc.nextInt();                                              //
                        }                                                                       //
                        catch(Exception e){                                                     //
                            s = -1;                                                        //  
                        }                                                                       //
                                                                                                //
                        if (s < 1 || s > acama.getCantidadMiembros()){                     //   
                            s = controlarValoresEnteros(s,1,acama.getCantidadMiembros(),   //
                                    sc);                                                        //
                        }
                        
                        if(acama.getMiembros().get(s - 1).calcularCantidadMotos() == 0)
                        {
                            acama.getMiembros().remove(s - 1);
                        }
                        else if(acama.getMiembros().get(s - 1).calcularCantidadMotos() > 0)
                        {
                            while(acama.getMiembros().get(s - 1).calcularCantidadMotos() > 0)
                            {
                                mostrarMotosDeUnMiembro(acama.getMiembros(), s- 1);
                                
                                System.out.println("Selecciona la moto que se va a ceder.");            //Se selecciona una de las motos del miembro origen para ser cedida.
                                sc.nextLine();                                                          //
                                try                                                                     //
                                {                                                                       //
                                    mot = sc.nextInt();                                                //
                                }                                                                       //
                                catch(Exception e){                                                     //
                                    mot =-1;                                                           //
                                }                                                                       //
                                                                                                        //
                                if (mot < 1 || mot > acama.getMiembros().get(s - 1).calcularCantidadMotos())                                       //
                                {                                                                       //
                                    moto = controlarValoresEnteros(mot,1,acama.getMiembros().get(s - 1).calcularCantidadMotos(),sc);               //                                             
                                }
                                
                                dest = mostrarPosiblesMiembrosDestinoCesion(acama,             //
                                s - 1, mot - 1   //
                                    , acama.getCosteMaximo());                                  //
                                
                                if(dest.size() > 0)                                            //Si existe algun miembro capaz de guardar la moto que se quiere ceder sin superar
                                {                                                                       //los 6000€...
                                    System.out.println("Selecciona el miembro destino para la cesion"   //Se selecciona uno de los posibles miembros destino.
                                            + "(usa el numero entre parentesis "                        //
                                            + "no el numero de socio).");                               //
                                    sc.nextLine();                                                      //
                                    try                                                                 //
                                    {                                                                   //
                                        desti = sc.nextInt();                                         //
                                    }                                                                   //
                                    catch(Exception e){                                                 //
                                        desti =-1;                                                    //
                                    }                                                                   //
                                                                                                        //
                                    if (desti < 1 || desti > dest.size())                  //
                                    {                                                                   //
                                        desti = controlarValoresEnteros(desti,1,                    //
                                                dest.size(),sc);                               //                             
                                    }                                                                   //
                                                                                                        //
                                    desti = (dest.get(desti - 1).getNumSocio() -1);        //
                                                                                                        //
                                    ori = s - 1;       //
                                                                                                        //
                                    mot--;                                                             //
                                                                                                        //
                                    System.out.println("Cesion realizada correctamente.");              //Se muestra la correcta realizacion de la cesion.
                                    System.out.println("La "+acama                                      //
                                        .getMiembros().get(ori).getMotos()                           //    
                                        .get(mot).toString()                                           //
                                        +"ha sido cedida por el "+acama.getMiembros()                   //
                                                .get(ori).toString()+                                //
                                        "y ahora dispone de ella el "+acama.getMiembros()               //
                                                .get(desti).toString());                              //
                                                                                                        //            
                                    Date fecha = new Date();                                            //
                                                                                                        //
                                    Cesion c = new Cesion(acama.getMiembros().get(ori).              //Se crea la cesion.
                                                    getMotos().get(mot),                               //
                                            acama.getMiembros().get(ori),                            //
                                            acama.getMiembros().get(desti), fecha);                   //
                                                                                                        //
                                    acama.registrarCesion(c);                                           //Se guarda la cesion en el array de cesiones de acama.
                                                                                                        //
                                    acama.getMiembros().get(desti).getMotos()                         //Se guarda la moto a ceder en el array de motos del miembro destino
                                            .add(acama.getMiembros().get(ori).                       //(ahora posee la moto).
                                                    getMotos().get(mot));                              //
                                    acama.getMiembros().get(ori).getMotos()                          //Se elimina la moto a ceder del array de motos del miembro origen
                                            .remove(acama.getMiembros().get(ori).                    //(ya no posee la moto).
                                                    getMotos().get(mot));                              //
                                                                                                        //
                                    acama.getMiembros().get(desti).getMotos().get((acama.getMiembros()//Se le cambia a la moto cedida el el numero de miembro por el del nuevo
                                            .get(desti).getMotos().size() - 1)).setSocio(desti + 1);//miembro que la posee.
                                                                                                        //
                                }
                                else
                                {
                                    acama.getMiembros().get(s - 1).getMotos().remove(mot - 1);
                                }

                                
                            }
                            
                            acama.getMiembros().remove(s - 1);
                        }
                    
                    break;
                case 9:
                    ArrayList<Miembro> maxc = acama.miembrosConMasCesiones();
                    
                    for(int h = 0; h < maxc.size(); h++)
                    {
                        System.out.println(maxc.get(h).toString());
                        mostrarMotosDeUnMiembro(maxc, h);
                    }
                    break;
                case 10:                                                                                //SALIR DEL PROGRAMA CREANDO UN FICHERO DE TEXTO Y GUARDANDO EN EL///////////////////////
                                                                                                //LA INFORMACION DE LOS MIEMBROS, SUS MOTOS Y LAS CESIONES REALIZADAS////////////////////
                    generarFichero(acama, sc);                                                  //Genera un fichero que guarda la informacion de los miembros, sus motos
                                                                                                //y las cesiones realizadas. Despues finaliza el programa.
                    salir = true;                                                               //
                    break;                                                                      /////////////////////////////////////////////////////////////////////////////////////////
            }
        }while(!salir);
    }
    
    
    
    
    public static int menu(Scanner sc){                                                         //FUNCION menu  
                                                                                                //Parametros: Scanner
                                                                                                //Return: int
        int numMenu ;                                                                           //
                                                                                                //Esta funcion muesta el menu principal del programa, pide seleccionar una 
        System.out.println("1. Registrar un nuevo miembro");                                    //opcion y devuelve la opcion seleccionada.
        System.out.println("2. Registrar una nueva motocicleta");                               //
        System.out.println("3. Registrar una cesion");                                          //
        System.out.println("4. Listar en pantalla los miembros y sus motos");                   //
        System.out.println("5. Listar todas las motos");                                        //
        System.out.println("6. Mostrar las cesiones realizadas");                               //
        System.out.println("7. Incrementar otros gastos a una moto");                           //
        System.out.println("8. Eliminar miembro");                                              //
        System.out.println("9. Miembros con mas cesiones");                                     //
        System.out.println("10. Salir del programa");                                           //
                                                                                                //
        try{                                                                                    //
            numMenu = sc.nextInt();                                                             //
        }                                                                                       //
        catch(Exception e){                                                                     //
            return 0;                                                                           //
        }                                                                                       //
                                                                                                //
                                                                                                //
        return numMenu;                                                                         //
    }                                                                                           //
    
    
    public static boolean motoAlmacenable(ACAMA a, float coste, float costeMax){                //FUNCION motoAlmacenable
                                                                                                //Parametros: ACAMA, float
        boolean res = false;                                                                    //Return: boolean
        float precio;                                                                           //
                                                                                                //Esta funcion calcula a partir del coste de una moto, si dicha moto
        for(int i = 0; i < a.getCantidadMiembros(); i++){                                       //puede ser almacenada por algun miembro de la asociacion acama.
            precio = a.getMiembros().get(i).calcularPrecioMotos();                              //
            precio += coste;                                                                    //
                                                                                                //
            if(precio <= costeMax)                                                                  //
                res = true;                                                                     //
        }                                                                                       //
                                                                                                //
        return res;                                                                             //
    }                                                                                           //
    
    
    public static void mostrarTodasLasMotosYSuMiembro(ACAMA acama){                             //FUNCION mostrarTodasLasMotosYSuMiembro
                                                                                                //Parametros: ACAMA
        int aux;                                                                                //Return: void
                                                                                                //
        for (int i = 0; i < acama.getCantidadMotos(); i++){                                     //Esta funcion muestra todas las motos de la asociacion acama 
                                                                                                //y el miembro asociado a cada moto.
            System.out.println((i+1)+" - "+acama.getMotos().get(i).toString());                 //
                                                                                                //
            aux = acama.getMotos().get(i).getSocio();                                           //
            System.out.println("Miembro que la posee: "                                         //
                    +acama.getMiembros().get(aux - 1).toString());                              //
        }                                                                                       //
                                                                                                //
    }                                                                                           //
    
    
    public static void mostrarCesiones(ACAMA acama){                                            //FUNCION mostrarCesiones
                                                                                                //Parametros: ACAMA
        for (int i = 0; i < acama.getCantidadCesiones(); i++){                                  //Return: void
                                                                                                //
            System.out.println("Cesion "+(i+1)+":");                                            //Esta funcion muestra todas las cesiones juntos con todos sus datos.
                                                                                                //
            System.out.println("    "+acama.getCesiones().get(i).toString());                   //
        }                                                                                       //
                                                                                                //
    }                                                                                           //
    
    
    
    public static void mostrarMiembrosYMotos(ACAMA acama){                                      //FUNCION mostrarMiembrosYMotos
        if(acama.getCantidadMiembros() == 0)                                                    //Parametros: ACAMA
        {                                                                                       //Return: void
            System.out.println("No hay ningun miembro registrado en la"                         //                
                + " asociacion ACAMA.");                                                        //Esta funcion muestra todos los miembros registrados en acama
        }                                                                                       //junto con la informacion de sus respectivas motos.
        else                                                                                    //
        {                                                                                       //
            for(int i = 0; i < acama.getCantidadMiembros(); i++)                                //  
            {                                                                                   //
                System.out.println(acama.getMiembros().get(i).toString());                      //
                                                                                                //
                if(acama.getMiembros().get(i).calcularCantidadMotos() == 0)                     //
                {                                                                               //
                    System.out.println("    Ninguna moto en posesion.");                        //
                }                                                                               //
                else                                                                            //
                {                                                                               //
                    System.out.println("    Motos en posesion: ");                              //
                    mostrarMotosDeUnMiembro(acama.getMiembros(), i);                            //
                }                                                                               //
            }                                                                                   //
        }                                                                                       //
    }                                                                                           //
    
    
    public static ArrayList<Miembro> mostrarMiembrosConMotoYMotos(ACAMA acama){                 //FUNCION mostrarMiembrosConMotoYMotos
                                                                                                //Parametros: ACAMA
        ArrayList<Miembro> res = new ArrayList<Miembro>();                                      //Return: ArrayList<Miembro>
        int cont = 1;                                                                           //
                                                                                                //Esta funcion muestra los miembros que poseen al menos una moto
        if(acama.getCantidadMiembros() == 0)                                                    //junto con la informacion de sus respectivas motos. Devuelve
        {                                                                                       //un array con los miembros que poseen al menos una moto.
            System.out.println("No hay ningun miembro registrado en la"                         //                
                + " asociacion ACAMA.");                                                        //
        }                                                                                       //
        else                                                                                    //
        {                                                                                       //
            for(int i = 0; i < acama.getCantidadMiembros(); i++)                                //  
            {                                                                                   //
                if(acama.getMiembros().get(i).calcularCantidadMotos() > 0)                      //
                {                                                                               //
                    System.out.println("Miembro numero "+cont+":");                             //
                    System.out.println("    "+acama.getMiembros().get(i).toString());           //
                                                                                                //
                    System.out.println("    Motos en posesion: ");                              //
                                                                                                //
                    mostrarMotosDeUnMiembro(acama.getMiembros(), i);                            //
                                                                                                //
                    res.add(acama.getMiembros().get(i));                                        //
                    cont++;                                                                     //
                }                                                                               //
            }                                                                                   //
        }                                                                                       //
        return res;                                                                             //
    }                                                                                           //
    
   
    public static void mostrarMotosDeUnMiembro(ArrayList<Miembro> miembros, int i){             //FUNCION mostrarMotosDeUnMiembro
                                                                                                //Parametros: ArrayList<Miembro>, int
        if(miembros.get(i).calcularCantidadMotos() == 0)                                        //Return: void
        {                                                                                       //
            System.out.println("Ninguna moto en posesion.");                                    //Esta funcion muestra las motos de un miembro concreto.
        }                                                                                       //
        else                                                                                    //
        {                                                                                       //
            for(int j = 0; j < miembros.get(i).calcularCantidadMotos(); j++)                    //
            {                                                                                   //
                System.out.println("        "+(j+1)+" - "+miembros.get(i).getMotos()            //  
                .get(j).toString());                                                            //
            }                                                                                   //
        }                                                                                       //
    }                                                                                           //

    
    public static ArrayList<Miembro> mostrarPosiblesMiembrosDestinoCesion(ACAMA acama,          //FUNCION mostrarPosiblesMiembrosDestinoCesion
            int origen, int moto, float costeMax){                                              //Parametros: ACAMA, int, int
                                                                                                //Return: ArrayList<Miembro>
        ArrayList<Miembro> destinatarios = new ArrayList<Miembro>();                            //
        int i, cont = 1;                                                                        //Esta funcion muestra los miembros que a los que se les puede ceder
        boolean existe = false;                                                                 //una motocicleta a partir del coste de esta y de un miembro origen.
        float precio;                                                                           //Devuelve un array con los miembros a los que se les puede ceder 
        float precioOrigen = acama.getMiembros().get(origen).getMotos().get(moto).getCoste();   //una moto de coste igual al parametro 3.
                                                                                                //
        for (i = 0; i < acama.getMiembros().size(); i++){                                       //
            precio = acama.getMiembros().get(i).calcularPrecioMotos();                          //
            precio += precioOrigen;                                                             //
                                                                                                //
            if(precio <= costeMax && i != origen)                                               //
            {                                                                                   //
                System.out.println(acama.getMiembros().get(i).toString()+"(introduce "          //
                        +cont+").");                                                            //
                existe = true;                                                                  //
                cont++;                                                                         //
                destinatarios.add(acama.getMiembros().get(i));                                  //
            }                                                                                   //
        }                                                                                       //
                                                                                                //
        if(!existe)                                                                             //
        {                                                                                       //
            System.out.println("Ningun miembro puede aceptar esta moto sin superar"             //
                    + " los "+acama.getCosteMaximo()+"€.");                                     //
        }                                                                                       //
        return destinatarios;                                                                   //
    }                                                                                           //
    
    
    
    
    public static ArrayList<Miembro> mostrarPosiblesMiembros(ACAMA acama, float coste,          //FUNCION mostrarPosiblesMiembros
            float costeMax){                                                                    //
                                                                                                //Parametros: ACAMA, float
        int i, cont = 1;                                                                        //Return: ArrayList<Miembro>
        float precio;                                                                           //
        ArrayList<Miembro> destinatarios = new ArrayList<Miembro>();                            //Esta funcion muestra los miembros que pueden guardar una moto
                                                                                                //recien registrada y de un coste determinado. Devuelve un array 
        System.out.println("\nMiembros que pueden guardar la motocicleta:\n");                  //con los miembros que pueden almacenar una moto de coste igual
        for (i = 0; i < acama.getMiembros().size(); i++){                                       //al parametro 2.
            precio = acama.getMiembros().get(i).calcularPrecioMotos();                          //
            precio += coste;                                                                    //
                                                                                                //
            if(precio <= costeMax)                                                              //
            {                                                                                   //
                System.out.println("    "+acama.getMiembros().get(i).toString()+"(introduce "   //
                        +cont+").");                                                            //
                cont++;                                                                         //
                destinatarios.add(acama.getMiembros().get(i));                                  //
            }                                                                                   //
        }                                                                                       //
        return destinatarios;                                                                   //
    }                                                                                           //
    
    
    
    
    
    
    public static void generarFichero(ACAMA acama, Scanner sc){                                 //FUNCION generarFichero
                                                                                                //Parametros: ACAMA, Scanner
        String nombreFich;                                                                      //Return: void
                                                                                                //  
        System.out.println("Se va a crear un fichero en el que se guardaran los miembros"       //Esta funcion pide un nombre de fichero y genera un fichero de texto
                + " registrados, sus motocicletas y las cesiones registradas.");                //con el nombre obtenido y en el que guarda la informacion de los
        System.out.println("Introduce el nombre del fichero: ");                                //miembros registrados en las asociacion acama junto con la informacion
        sc.nextLine();                                                                          //de las respectivas motos de cada miembro y junto con las cesiones
        nombreFich = sc.nextLine();                                                             //registradas en la asociacion.
                                                                                                //
        try {                                                                                   //  
            File archivo = new File(nombreFich+".txt");                                         //
            FileWriter escribir = new FileWriter(archivo,true);                                 //
                                                                                                //
            if(acama.getCantidadMiembros() == 0)                                                //
            {                                                                                   //    
                escribir.write("No hay ningun miembro registrado en la"                         //                
                    + " asociacion ACAMA.\r\n\r\n");                                            //
            }                                                                                   //
            else                                                                                //
            {                                                                                   //
                for(int i = 0; i < acama.getCantidadMiembros(); i++)                            //  
                {                                                                               //
                    escribir.write(acama.getMiembros().get(i).toString()+"\r\n");               //
                                                                                                //
                    if(acama.getMiembros().get(i).calcularCantidadMotos() == 0)                 //
                    {                                                                           //
                        escribir.write("    Ninguna moto en posesion.\r\n\r\n");                //
                    }                                                                           //
                    else                                                                        //
                    {                                                                           //
                        escribir.write("    Motos en posesion: \r\n");                          //
                                                                                                //
                        for(int j = 0; j < acama.getMiembros().get(i).calcularCantidadMotos()   //
                                ; j++)                                                          //
                            {                                                                   //
                                escribir.write("        "+(j+1)+" - "+acama.getMiembros()       //
                                .get(i).getMotos().get(j).toString()+"\r\n");                   //
                            }                                                                   //
                                escribir.write("\r\n");                                         //
                    }                                                                           //
                }                                                                               //
            }                                                                                   //
                                                                                                //
                                                                                                //
            if(acama.getCantidadCesiones() == 0)                                                //
                {                                                                               //    
                escribir.write("No hay ninguna cesion registrada en la"                         //                
                    + " asociacion ACAMA.");                                                    //
            }                                                                                   //
            else                                                                                //
            {                                                                                   //    
                escribir.write("Cesiones registradas:\r\n\r\n");                                //
                for (int i = 0; i < acama.getCantidadCesiones(); i++)                           //
                {                                                                               //
                    escribir.write("Cesion "+(i+1)+":\r\n");                                    //    
                    escribir.write("    "+acama.getCesiones().get(i).toString());               //
                    escribir.write("\r\n\r\n");                                                 //
                }                                                                               //
                                                                                                //
            }                                                                                   //
                                                                                                //
            escribir.close();                                                                   //
        } catch (IOException ex) {                                                              //
            System.out.println("Error al escribir");                                            //
        }                                                                                       //
    }                                                                                           //
    
    
    public static int controlarValoresEnteros(int valor, int izq, int drch, Scanner sc){        //FUNCION controlarValoresEnteros
                                                                                                //Parametros: int, int, int, Scanner
        while (valor < izq || valor > drch)                                                     //Return: int
        {                                                                                       //
            System.out.println("Debes introducir un valor entre "+izq+" y "                     //Esta funcion verifica que un valor entero introducido por teclaro
            +drch+".");                                                                         //se encuentra dentro de un determinado rango. Si el valor entero no
            sc.nextLine();                                                                      //se encuentra dentro del rango especificado se vuelve a pedir por teclado
            try                                                                                 //hasta que esta dentro de rango.Devuelve el entero una vez esta dentro del
            {                                                                                   //rango valido.
                valor = sc.nextInt();                                                           //
            }                                                                                   //
            catch(Exception e){                                                                 //
                valor = -1;                                                                     //
            }                                                                                   //
        }                                                                                       //
        return valor;                                                                           //
    }                                                                                           //
    
    
    private static boolean matriculaRepetida(ACAMA acama, String matricula)                     //FUNCION matriculaRepetida
    {                                                                                           //Parametros: ACAMA, String
        boolean res = false;                                                                    //Return: boolean
                                                                                                //
        for (int i = 0; i < acama.getCantidadMotos(); i++)                                      //Esta funcion comprueba si una matricula ya esta registrada en alguna
        {                                                                                       //de las motos de la asociacion acama. Devuelve true si la matricula ya
            if(acama.getMotos().get(i).getMatricula().equals(matricula))                        //esta en uso y false si no lo esta.
            {                                                                                   //
                res = true;                                                                     //
            }                                                                                   //
        }                                                                                       //
                                                                                                //
        return res;                                                                             //
    }                                                                                           //
                        
    
    private static boolean matriculaCorrecta(String matricula)                                  //FUNCION matriculaCorrecta
    {                                                                                           //Parametros: String
        boolean correcta = false;                                                               //Return: boolean
        if(matricula.length() == 7)                                                             //
        {                                                                                       //Esta funcion comprueba si una matricula introducida por teclado
            if(Character.isDigit(matricula.charAt(0)) &&                                        //es valida para el formato ddddlll donde d es un digito y l es una
                    Character.isDigit(matricula.charAt(1)) &&                                   //letra consonante y diferente de 'q' y 'ñ'. Devuelve true si la matricula
                    Character.isDigit(matricula.charAt(2)) &&                                   //se ajusta al formato y false si la matricula no se ajusta al formato.
                    Character.isDigit(matricula.charAt(3)))                                     //
            {                                                                                   //
                if(Character.isAlphabetic(matricula.charAt(4)) &&                               //
                    Character.isAlphabetic(matricula.charAt(5)) &&                              //
                    Character.isAlphabetic(matricula.charAt(6)))                                //
                {                                                                               //
                    CharSequence [] mal = {"a", "A", "e", "E", "i", "I", "o", "O", "u",         //
                        "U", "ñ", "Ñ", "q", "Q"};                                               //
                    boolean noParar = true;                                                     //
                    for(int i = 0; i < mal.length && noParar; i++)                              //
                    {                                                                           //
                        if(Character.toString(matricula.charAt(4)).contains(mal[i]) ||          //
                            Character.toString(matricula.charAt(5)).contains(mal[i]) ||         //
                            Character.toString(matricula.charAt(6)).contains(mal[i]))           //
                        {                                                                       //
                            noParar = false;                                                    //
                        }                                                                       //
                    }                                                                           //
                    if(!noParar)                                                                //
                    {                                                                           //
                        System.out.print("\nUno de los ultimos 3 caracteres no"                 //
                            + " es una letra permitida.\nNo estan permitidas"                   //
                            + ": las vocales, ñ ni q.\n");                                      //
                    }                                                                           //
                    else                                                                        //
                    {                                                                           //        
                        correcta = true;                                                        //
                    }                                                                           //
                }                                                                               //
                else                                                                            //
                {                                                                               //
                    System.out.print("\nUno de los ultimos 3 caracteres no es "                 //
                            + "una letra.\n");                                                  //
                }                                                                               //
            }                                                                                   //
            else                                                                                //
            {                                                                                   //
                System.out.print("\nUno de los primeros 4 caracteres no es un "                 //
                        + "digito.\n");                                                         //
            }                                                                                   //
        }                                                                                       //
        else                                                                                    //
        {                                                                                       //
            System.out.print("\nFormato incorrecto.\n");                                        //
        }                                                                                       //
        return correcta;                                                                        //  
    }                                                                                           //
    
    
}