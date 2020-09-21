/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIQytetet;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 *
 * @author jopoku
 */
public class VistaTextualQytetet {
    Scanner sc = new Scanner(System.in);
    private static final Scanner in = new Scanner (System.in);

    private boolean comprobarOpcion(String lectura, int min, int max){  
     
     boolean valido=true;   
     int opcion;
     try {  
          opcion =Integer.parseInt(lectura);
          if (opcion<min || opcion>max) { // No es un entero entre los v·lidos
               this.mostrar("el numero debe estar entre min y max");
                valido = false;}
        
      } catch (NumberFormatException e) { // No se ha introducido un entero
              this.mostrar("debes introducir un numero");
              valido = false;  
      }
      if (!valido) {
        this.mostrar("\n\n Seleccion erronea. Intentalo de nuevo.\n\n");
      }
      return valido;
    }
    
    public boolean elegirQuieroComprar(){
      System.out.println("0: Comprar propiedad \n , 1: No hacer nada y pasar turno \n");
      int aux = in.nextInt();
      if(aux==1){
          return true;
      }
      return false ;
    }
    
    public int menuGestionInmobiliaria(int tipo_casilla){
        int salida = 0;
        if(tipo_casilla == 1){
            this.mostrar("Elige la gestion inmobiliaria que deseas hacer");
            Map<Integer, String> menuGI = new TreeMap();
            menuGI.put(0, "Siguiente Jugador"); 
            salida=this.seleccionMenu(menuGI); 
        }
        else if(tipo_casilla == 2){
            this.mostrar("Elige la gestion inmobiliaria que deseas hacer");
            Map<Integer, String> menuGI = new TreeMap();
            menuGI.put(0, "Siguiente Jugador"); 
            menuGI.put(1, "Edificar casa");
            menuGI.put(2, "Edificar Hotel"); 
            menuGI.put(3, "Vender propiedad ");  	
            menuGI.put(4, "Hipotecar Propiedad"); 
            menuGI.put(5, "Cancelar Hipoteca");
            salida=this.seleccionMenu(menuGI);
        }
        else{
        this.mostrar("Felicidades has caido en una casilla que no tiene propietario que quieres hacer :");
        Map<Integer, String> menuGI = new TreeMap();
        menuGI.put(0, "Comprar propiedad"); 
        menuGI.put(1, "No hacer nada y pasar turno");
        salida=this.seleccionMenu(menuGI);
       }
        return salida;
    }
    
    public int menuSalirCarcel(){return 0;
    }
    
    public void mostrar(String texto){
        System.out.println(texto);
       }
    
    public ArrayList<String> obtenerNumeroJugadores(){
        
        boolean valido = true; 
        String lectura;
        ArrayList<String> nombres = new ArrayList();
        do{ //repetir mientras que el usuario no escriba un n˙mero correcto 
            this.mostrar("Escribe el numero de jugadores: (de 2 a 4):");
            lectura = sc.nextLine();  //lectura de teclado
            valido=this.comprobarOpcion(lectura, 2, 4); //mÈtodo para comprobar la elecciÛn correcta
        }while (!valido);

        for (int i = 1; i <= Integer.parseInt(lectura); i++) { //solicitud del nombre de cada jugador
          this.mostrar("Nombre del jugador " + i + ": ");
          nombres.add (sc.nextLine());
        }
        return nombres;
    }
    
    public int menuElegirPropiedad(ArrayList<String> listaPropiedades){  //numero y nombre de propiedades            
       Map<Integer, String> menuEP = new TreeMap();
       int numeroOpcion=0;
       for(String prop: listaPropiedades) {
           menuEP.put(numeroOpcion, prop); //opcion de menu, numero y nombre de propiedad
           numeroOpcion=numeroOpcion+1;
       }
       int salida=this.seleccionMenu(menuEP); // Método para controlar la elección correcta en el menú 
       return salida;

    }
    
    private int seleccionMenu(Map<Integer,String> menu){
            boolean valido = true; 
    int numero;
    String lectura;
    do { // Hasta que se hace una selecciÛn v·lida
      for(Map.Entry<Integer, String> fila : menu.entrySet()) {
            numero = fila.getKey();
            String texto = fila.getValue();
            this.mostrar(numero + " : " + texto);  // n˙mero de opciÛn y texto
      }
      this.mostrar("\n Elige una opciÛn: ");
      lectura = sc.nextLine();  //lectura de teclado
      valido=this.comprobarOpcion(lectura, 0, menu.size()-1); //mÈtodo para comprobar la elecciÛn correcta
    } while (!valido);
    return Integer.parseInt(lectura);
    }
    
}
