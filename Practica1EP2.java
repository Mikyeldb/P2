/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg1.ep2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author juan y miquel
 */
public class Practica1EP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        ACAMA acama = new ACAMA();                                                              //CREACIÓN DE VARIABLES
        Scanner sc = new Scanner(System.in);                                                    //
        boolean salir = false;                                                                  //
                                                                                                //
        do                                                                                      //
        {                                                                                       //
            int menu = menu(sc);                                                                //Esta función muestra el menú y permite elegir una opción
                                                                                                //
            while (menu < 1 || menu > 7)                                                        //
            {                                                       //Esta parte comprueba que se introduce una opcion correcta
                System.out.println("Debes introducir un valor entre 1 y 7.");                   //si no es asi, se vuelve a mostrar el menu
                sc.nextLine();                                                                  //
                menu = menu(sc);                                                                //
            }                                                                                   //
                                                                                                //
            switch(menu)                                                                        //
            {                                                                                   //
                case 1:                                                                         //REGISTRAR NUEVO MIEMBRO EN EL SISTEMA////////////////////////////////////////
                    
                    System.out.println("Registrando nuevo miembro en el sistema.\n");           //
                    System.out.println("Introduce el nombre del nuevo miembro.");               //se pide el nombre y se guarda al miembro
                    sc.nextLine();                                                              //en el array de miembros de acama.
                    String nombre = sc.nextLine();                                              //
                    acama.regitrarMiembro(nombre);                                              //
                    System.out.println("Nuevo miembro registrado correctamente.");              //
                    
                break;                                                                          //
                case 2:                                                                         //RESGISTRAR NUEVA MOTOCICLETA EN EL SISTEMA/////////////////////////////////////
                                                                                                //
                    float coste;                                                                //
                    String modelo, matricula;                                                   //  
                    int caballos, select;                                                       //
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
                        System.out.println("Registrando nueva motocicleta.\n"                   //
                                + "\nIntroduce el coste de la motocicleta:");                   //superar os 6000€)
                        sc.nextLine();                                                          //Se comienza el registro de la motocicleta y se pide
                        try                                                                     //el precio de la misma.
                        {                                                                       //
                            coste = sc.nextFloat();                                             //
                        }                                                                       //
                        catch(Exception e)
                        {
                            coste = -1;                                                         //
                        }                                                                       //
                                                                                                //
                        if(coste < 0 || coste > 1000000000)                                     //
                        {                                                                       //
                            coste = controlarValoresEnteros((int)coste, 0, 1000000000, sc);     //
                        }                                                                       //
                                                                                                //
                        if(!acama.motoAlmacenable(coste))                                       //si existen miebros registrados pero ninguno puede guardar la moto
                        {                                                                       //sin exceder los 6000€...
                            System.out.println("No hay ningun miembro regitrado en "            //Se cancela el registro de la motocicleta.    
                                    + "la asociacion ACAMA que pueda guardar esta moto "        //
                                    + "sin superar el limite de 6000 euros."                    //
                                    + "\nEsta moto no puede darse de alta en "                  //
                                    + "la asociacion ACAMA.");                                  //    
                        }                                                                       //
                        else                                                                    //
                        {                                                                   //si existen miembros registrados y alguno puede almacenar la moto
                                                                                                //sin exceder los 6000€
                            System.out.println("Introduce el modelo de la motocicleta:");       //Se piede el modelo de la motocicleta.
                            sc.nextLine();                                                      //
                            modelo = sc.nextLine();                                             //
                                                                                                //
                            System.out.println("Introduce los caballos de la motocicleta:");    //
                            try                                                                 //
                            {                                                                   //
                                caballos = sc.nextInt();                                        //
                            }                                                                   //
                            catch(Exception e)
                            {                                                 //
                                caballos = -1;                                                  //    
                            }                                                                   //
                                                                                                //    
                            if(caballos < 0 || caballos > 2000)                                 //
                            {                                                                   //
                                caballos = controlarValoresEnteros(caballos, 0, 2000, sc);      //
                            }                                                                   //
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
                                            + "con esta matricula, "
                                            + "por favor introduzca otra.");                           //
                                }                                                               //
                                matricula = sc.nextLine();                                      //
                                repetida = matriculaRepetida(acama, matricula);                 //
                            }                                                                   //
                                                                                                //
                            Moto moto = new Moto(modelo,caballos,coste,matricula);              //Se crea la moto.
                                                                                                //
                            acama.registrarMoto(moto);                                          //Se guarda la moto en el array de motos de acama. 
                                                                                                //
                            destinos = acama.mostrarPosiblesMiembros(coste);                   //Se calculan los miembros registrados que pueden almacenar la moto.
                                                                                                //
                            if (destinos.size() > 0)                                            //Si existe algun miembro registrado capaz de almacenar la moto...
                            {                                                                   //
                                System.out.println("Selecciona el miembro que guardara esta "   //Se selecciona uno de los posibles miembros.
                                        + "motocicleta.");                                      //
                                try                                                             //
                                {                                                               //
                                    select = sc.nextInt();                                      //
                                }                                                               //
                                catch(Exception e)
                                {
                                    select = -1;                                                //
                                }                                                               //
                                                                                                //
                                if (select < 1 || select > destinos.size())
                                {
                                    select = controlarValoresEnteros(select,1,                  //
                                            destinos.size(), sc);                               //
                                }                                                               //
                                                                                                //
                                moto.setSocio(destinos.get(select - 1).getNumSocio());          //Se asigna a la moto el miembro que la va a almacenar.
                                                                                                //                                                              //
                                System.out.println("Motocicleta guardada correctamente en el "  //
                                    + "sistema.\nLa motocicleta ha sido asignada a: "           //
                                    +acama.getMiembros().get(destinos.get(select - 1)           //
                                        .getNumSocio() - 1).toString());                        //
                            }                                                                   //
                        }                                                                       //    
                    }                                                                           //
                    
                break;                                                                          ////////////////////////////////////////////////////////////////////////////////
                case 3:                                                                         //REGISTRAR UNA CESIÓN//////////////////////////////////////////////////////////
                                                                                                //
                    int origen, moto, destino;                                                  //
                    ArrayList<Miembro> destinatarios = new ArrayList<Miembro>()
                            , miembrosConMoto = new ArrayList<Miembro>();                                           //
                                                                                                //
                    if(acama.getCantidadMotos() == 0)                                           //Si no existen motos registradas...
                    {                                                                           //(no se puede registrar una cesion).
                        System.out.println("No existe ninguna moto registrada"                  //
                                + "\nNo puede realizarse ninguna cesion.");                     //
                    }                                                                           //
                    else if(acama.getCantidadMiembros() < 2)                                    //Si no existen miembros registrados suficientes para hacer la cesión...
                    {                                                                           //(no se puede registrar una cesion).
                        System.out.println("El numero de miembros registrados "
                                + "en la asociacion es inferior a 2."
                                + "\nNo puede realizarse ninguna cesion.");              //
                    }                                                                           //
                    else                                                                        //Si existe al menos 1 moto registrada y al menos 2 miembros registrados...
                    {                                                                           //(se puede hacer la cesión)
                        System.out.println("Miembros que disponen de una moto para ceder:");    //
                        miembrosConMoto = acama.mostrarMiembrosConMotoYMotos();                  //Se muestran los miembros que tienen motocicletas.
                                                                                                //
                        System.out.println("Selecciona el miembro origen para la cesion.");     //Se selecciona uno de los miembros mostrados como miembro origen para la cesion.
                        sc.nextLine();                                                          //
                        try                                                                     //
                        {                                                                       //
                            origen = sc.nextInt();                                              //
                        }                                                                       //
                        catch(Exception e)
                        {                                                                       //
                            origen = -1;                                                        //  
                        }                                                                       //
                                                                                                //
                        if (origen < 1 || origen > miembrosConMoto.size())
                        {                                                                       //   
                            origen = controlarValoresEnteros(origen,1,
                                    miembrosConMoto.size(), sc);                                                        //
                        }                                                                       //
                                                                                                //
                        System.out.println("Motos del "                                         //Se muestran las motos que puede ceder el miembro origen(todas sus motos).
                                +miembrosConMoto.get(origen - 1).toString()+":");               //
                                                                                                //
                        acama.mostrarMotosMiembro(miembrosConMoto.get(origen - 1).getNumSocio());                 //
                                                                                                //
                                                                                                //
                        System.out.println("Selecciona la moto que se va a ceder.");            //Se selecciona una de las motos del miembro origen para ser cedida.
                        sc.nextLine();                                                          //
                        try                                                                     //
                        {                                                                       //
                            moto = sc.nextInt();                                                //
                        }                                                                       //
                        catch(Exception e)
                        {                                                                       //
                            moto =-1;                                                           //
                        }                                                                       //
                                                                                                //
                        if (moto < 1 || moto > acama.calcularCantidadMotos(origen))                   //
                        {                                                                       //
                            moto = controlarValoresEnteros(moto,1,
                                    acama.calcularCantidadMotos(origen), sc);                   //                                             
                        }                                                                       //
                                                                                                //
                        System.out.println("Miembros a los que se les puede ceder la moto sin"  //Se muestran los miembros de la asociacion que pueden guardar la moto que se quiere
                                + " superar el limite de 6000€:");                              //ceder sin superar los 6000€
                                                                                                //
                        destinatarios = acama.mostrarPosiblesMiembrosDestinoCesion(             //
                                (miembrosConMoto.get(origen - 1).getNumSocio() - 1), moto - 1); //
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
                            catch(Exception e)
                            {                                                                   //
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
                            System.out.println("Cesion realizada correctamente."                //Se muestra la correcta realizacion de la cesion.
                            + "La "+acama.mostrarMoto(moto)
                                +"ha sido cedida por el "+acama.getMiembros()                   //
                                        .get(origen).toString()+                                //
                                "y ahora dispone de ella el "+acama.getMiembros()               //
                                        .get(destino).toString());                              //
                                                                 //
                            acama.registrarCesion(moto, origen, destino);                       //Se guarda la cesion en el array de cesiones de acama.
                        }                                                                       //
                    }                                                                           //
                                                                                                //
                break;                                                                          //////////////////////////////////////////////////////////////////////////////////////////
                case 4:                                                                         //LISTAR MIEMBROS JUNTO CON SUS DATOS Y SUS MOTOS/////////////////////////////////////////
                    
                    acama.mostrarMiembrosYMotos();                                               //Si hay miembros registrados los muestra junto con sus respectivas motos.
                
                break;                                                                          //////////////////////////////////////////////////////////////////////////////////////////
                case 5:                                                                         //LISTAR TODAS LAS MOTOS Y EL MIEMBRO QUE LAS POSEE//////////////////////////////////////
                                                                                                //
                    if(acama.getCantidadMotos() == 0)                                           //Si no hay motos registradas...
                    {                                                                           //(no se pueden mostrar)
                        System.out.println("No existe ninguna moto registrada");                //
                    }                                                                           //
                    else                                                                        //Si hay moyos registradas...
                    {                                                                           //
                        acama.mostrarTodasLasMotosYSuMiembro();                                  //muestra las motos registradas juntos con el miembro que las posee
                    }                                                                           //
                                                                                                //
                break;                                                                          //////////////////////////////////////////////////////////////////////////////////////////  
                case 6:                                                                         //MOSTRAR HISTORIAL DE CESIONES CON TODOS LOS DATOS//////////////////////////////////////
                                                                                                //
                    if(acama.getCantidadCesiones() == 0)                                        //Si no existen cesiones registradas...
                    {                                                                           //(no se pueden mostrar)
                        System.out.println("No existe ninguna cesion registrada");              //
                    }                                                                           //
                    else                                                                        //Si existen cesiones registradas...
                    {                                                                           //
                        acama.mostrarCesiones();                                                 //Muestra todas las cesiones reistradas junto con sus datos.
                    }                                                                           //
                                                                                                //
                break;                                                                          /////////////////////////////////////////////////////////////////////////////////////////
                case 7:                                                                         //SALIR DEL PROGRAMA CREANDO UN FICHERO DE TEXTO Y GUARDANDO EN EL///////////////////////
                                                                                                //LA INFORMACION DE LOS MIEMBROS, SUS MOTOS Y LAS CESIONES REALIZADAS////////////////////
                    acama.generarFichero(sc);                                                  //Genera un fichero que guarda la informacion de los miembros, sus motos
                                                                                                //y las cesiones realizadas. Despues finaliza el programa.
                    salir = true;                                                               //
                    
                break;                                                                          /////////////////////////////////////////////////////////////////////////////////////////
            }
        }
        while(!salir);
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
        System.out.println("7. Salir del programa");                                            //
                                                                                                //
        try
        {                                                                                    //
            numMenu = sc.nextInt();                                                             //
        }                                                                                       //
        catch(Exception e)
        {                                                                     //
            return 0;                                                                           //
        }                                                                                       //
                                                                                                //
                                                                                                //
        return numMenu;                                                                         //
    }                                                                                           //
    
    public static int controlarValoresEnteros(int valor, int izq, int drch, Scanner sc)
    {        //FUNCION controlarValoresEnteros
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
            catch(Exception e)
            {                                                                 //
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
    }        
    
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
    }
}