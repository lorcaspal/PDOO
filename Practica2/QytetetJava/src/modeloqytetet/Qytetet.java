/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lorena
 */
public class Qytetet {
    //Variables
    private static final Qytetet instance = new Qytetet();
    public static final int MAX_JUGADORES = 4;
    static final int MAX_CARTAS = 10;
    static final int PRECIO_LIBERTAD = 200;
    static final int SALIDA = 1000;
    
    //Variables referenciadas
    private Sorpresa cartaActual;
    private ArrayList<Sorpresa> mazo;
    private Tablero tablero;
    private Jugador jugadorActual;
    private ArrayList<Jugador> jugadores;
    private Dado dado;
    
    //Constructores
    private Qytetet(){
        cartaActual = null;
        mazo = new ArrayList();
        tablero = null;
        jugadorActual = null;
        jugadores = new ArrayList();
        dado = null;
    }
    
    //Modificadores
    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    
    public Jugador getJugadorActual(){
        return jugadorActual;
    }
    
    //Metodos
    public static Qytetet getInstance() {
        return instance;
    }
    
    public boolean aplicarSorpresa(){
       throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean cancelarHipoteca(Casilla casilla){
       throw new UnsupportedOperationException("Sin implementar");        
    }
    
    public boolean comprarTituloPropiedad(){
       throw new UnsupportedOperationException("Sin implementar");

    }
    
    public boolean edificarCasa(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean edificarHotel(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean hipotecarPropiedad(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
        if(nombres.size() >= 2 && nombres.size() <= MAX_JUGADORES && mazo.size() <= MAX_CARTAS){
            inicializarJugadores(nombres);
            inicializarTablero();
            inicializarCartasSorpresa();
        }
        else{
            throw new UnsupportedOperationException("O el numero de jugadores es incorrecto (debe ser de 2 a " + MAX_JUGADORES + " jugadores)"
            + "o el numero de cartas es incorrecto (deben ser menor que " + MAX_CARTAS + ")");           
        }
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean jugar(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public ArrayList<List> obtenerRanking(){
        //Falta la restricción de 2...MAX_JUGADORES
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public ArrayList<Casilla> propiedadesHipotecadas(boolean hipotecadas){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public Jugador siguienteJugador(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean venderPropiedad(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private void encarcelarJugador(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private void salidaJugadores(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    //Metodos inicializadores, puesto a public para hacer las pruebas

    private void inicializarCartasSorpresa(){
        //Construcción de cartas sopresa
        //2 casillas PAGARCOBRAR
        mazo.add ( new Sorpresa("Errores de cálculos. El banco te devuelve $50", 50, TipoSorpresa.PAGARCOBRAR));
        mazo.add ( new Sorpresa("Gastos en videojuegos. Pague $50", -50, TipoSorpresa.PAGARCOBRAR));

        //3 casillas IRACASILLA
        mazo.add ( new Sorpresa("Avanza hasta la casilla 18", 18, TipoSorpresa.IRACASILLA));
        mazo.add ( new Sorpresa("La liga antisuperstición te envía de viaje al número 13", 13, TipoSorpresa.IRACASILLA));
        mazo.add ( new Sorpresa("¡Oh no! Te han pillado en uno de tus chanchullos. Vas a la carcel", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));

        //2 casillas PORCASAHOTEL
        mazo.add ( new Sorpresa("Tus construcciones están mejorando. Toma $150 del banco", 150, TipoSorpresa.PORCASAHOTEL));
        mazo.add ( new Sorpresa("Impuesto por el servicio de luz. Pague $15 al banco", -15, TipoSorpresa.PORCASAHOTEL));

        //2 casillas PORJUGADOR
        mazo.add ( new Sorpresa("¡Felicidades! hoy es el día de tu no cumpleaños, recibes un regalo de todos", 200, TipoSorpresa.PORJUGADOR));
        mazo.add ( new Sorpresa("Has perdido una apuesta. Paga $100 de penalización", -100, TipoSorpresa.PORJUGADOR));     

        //1 casilla SALIRCARCEL
        mazo.add ( new Sorpresa("Fuiste perdonado por el presidente. Con esta carta sales de prisión", 0, TipoSorpresa.SALIRCARCEL));

        //Barajar mazo
        Collections.shuffle(mazo);

    }

    private void inicializarJugadores(ArrayList<String> nombres){
        //Bucle de iteradores
        for(String nombre:nombres){
            jugadores.add(new Jugador(nombre));
        }
    }
    
    private void inicializarTablero(){
        tablero = new Tablero();
        
    }
    

    @Override
    public String toString() {
        return "********************* Mazo *********************\n" 
                + mazo 
                + "\n--------------------------------------\n" 
                + "********************* Tablero *********************\n" 
                + tablero 
                + "\n--------------------------------------\n" 
                + "********************* Jugadores *********************\n"  
                + jugadores 
                + "\n--------------------------------------\n" ;
    }
    
}
