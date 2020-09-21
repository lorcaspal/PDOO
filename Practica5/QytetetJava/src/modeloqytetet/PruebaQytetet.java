/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author Lorena
 */

public class PruebaQytetet {
    //Creación de objetos
//    private static ArrayList<Sorpresa> mazo = new ArrayList();
//    private static Tablero tablero;
        
    //Constructor
    public PruebaQytetet(){}
        
//    //Método de clase inicializarSorpresas
//    private static void inicializarSorpresas(){
//        //Construcción de cartas sopresa       
//        //2 casillas PAGARCOBRAR
//        mazo.add ( new Sorpresa("Errores de cálculos. El banco te devuelve $50", 50, TipoSorpresa.PAGARCOBRAR));
//        mazo.add ( new Sorpresa("Gastos en videojuegos. Pague $50", -50, TipoSorpresa.PAGARCOBRAR));
//        
//        //3 casillas IRACASILLA
//        mazo.add ( new Sorpresa("Avanza hasta la casilla 18", 18, TipoSorpresa.IRACASILLA));
//        mazo.add ( new Sorpresa("La liga antisupersticiión te envía de viaje al número 13", 13, TipoSorpresa.IRACASILLA));
//        mazo.add ( new Sorpresa("¡Oh no! Te han pillado en uno de tus chanchullos. Vas a la carcel", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
//        
//        //2 casillas PORCASAHOTEL
//        mazo.add ( new Sorpresa("Tus construcciones están mejorando. Toma $150 del banco", 150, TipoSorpresa.PORCASAHOTEL));
//        mazo.add ( new Sorpresa("Impuesto por el servicio de luz. Pague $15 al banco", -15, TipoSorpresa.PORCASAHOTEL));
//        
//        //2 casillas PORJUGADOR
//        mazo.add ( new Sorpresa("¡Felicidades! hoy es el día de tu no cumpleaños, recibes un regalo de todos", 200, TipoSorpresa.PORJUGADOR));
//        mazo.add ( new Sorpresa("Has perdido una apuesta. Paga $100 de penalización", -100, TipoSorpresa.PORJUGADOR));     
//        
//        //1 casilla SALIRCARCEL
//        mazo.add ( new Sorpresa("Fuiste perdonado por el presidente. Con esta carta sales de prisión", 0, TipoSorpresa.SALIRCARCEL));
//    }
    
    //Método 1: Sorpresas que tienen un valor mayor que 0
//    private static ArrayList sorMayorCero(){
//        ArrayList<Sorpresa> arrayValor = new ArrayList();
//        for(int i = 0; i < mazo.size(); i++){
//            if(mazo.get(i).getValor() > 0){
//               arrayValor.add(mazo.get(i));
//            }
//        }        
//        return arrayValor;
//    }
//    
//    //Método 2: Sorpresas de tipoSorpresa IRACASILLA.
//    private static ArrayList sorTipoCasilla(){
//        ArrayList<Sorpresa> arrayValor = new ArrayList();
//        for(int i = 0; i < mazo.size(); i++){
//            if(mazo.get(i).getTipo().equals(TipoSorpresa.IRACASILLA)){
//               arrayValor.add(mazo.get(i));
//            }
//        }        
//        return arrayValor;
//    }
//    
//    //Método 3: Sorpresa del TipoSorpresa especificado en el argumento del metodo
//    private static ArrayList sorTipoArgumento(TipoSorpresa sor){
//        ArrayList<Sorpresa> arrayValor = new ArrayList();
//        for(int i = 0; i < mazo.size(); i++){
//            if(mazo.get(i).getTipo().equals(sor)){
//               arrayValor.add(mazo.get(i));
//            }
//        }        
//        return arrayValor;
//    }
//    
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        ArrayList<String> jugadores = new ArrayList();
//        jugadores.add("Juan");
//        jugadores.add("Lorena");
//        jugadores.add("Javi");
//        
//        Qytetet qytetet = Qytetet.getInstance();
//        qytetet.inicializarJuego(jugadores);          
//        
//        System.out.print(qytetet);
//        
        // TODO code application logic here
        //Cargar mazo de cartas
        //inicializarCartasSorpresas();
        
//        //Muestra todo el mazo de cartas
//        for (int i = 0; i < mazo.size(); i++) {
//            System.out.println(mazo.get(i));     
//        }

        
//        //Muestra por pantalla metodos
//        //Método 1
//        System.out.println("\nMetodo 1: Mayores que 0 ----------------------------");
//        System.out.println(sorMayorCero());
//        
//        //Método 2
//        System.out.println("\nMetodo 2: Tipo IRACASILLA ----------------------------");
//        System.out.println(sorTipoCasilla());
//        
//        //Método 3
//        System.out.println("\nMetodo 3: Tipo argumento ----------------------------");
//        System.out.println(sorTipoArgumento(TipoSorpresa.SALIRCARCEL)); 
        
//        //Creacion del tablero
//        System.out.println("\nTablero ----------------------------");
//        System.out.println(tablero.toString()); 

//    }   
}
