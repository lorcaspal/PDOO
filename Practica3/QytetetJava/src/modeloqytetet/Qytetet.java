/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        cartaActual = new Sorpresa();
        mazo = new ArrayList();
        tablero = new Tablero();
        jugadores = new ArrayList();
        jugadorActual = new Jugador();
        dado = Dado.getInstance();;
    }
    
    //Modificadores
    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    
    public Jugador getJugadorActual(){
        return jugadorActual;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    //Metodos
    public static Qytetet getInstance() {
        return instance;
    }
    
    public boolean aplicarSorpresa(){
       boolean tienePropietario = false;
       
       if(cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR){
           jugadorActual.modificarSaldo(cartaActual.getValor());
       }
       else if(cartaActual.getTipo() == TipoSorpresa.IRACASILLA){
           boolean esCarcel = tablero.esCasillaCarcel(cartaActual.getValor());
           
           if(esCarcel){
               this.encarcelarJugador();
           }
           else{
               Casilla nuevaCasilla = tablero.obtenerCasillaNumero(cartaActual.getValor());
               //Preguntar
               tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
           }
       }
       else if(cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){
           jugadorActual.pagarCobrarPorCasaYHotel(cartaActual.getValor());
       }
       else if(cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
           for(Jugador jugador:jugadores){
               if(jugador != jugadorActual){
                   jugador.modificarSaldo(cartaActual.getValor());
                   jugadorActual.modificarSaldo(-cartaActual.getValor());
               }
           }
       }
       
       if(cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL){
           jugadorActual.setCartaLibertad(cartaActual);
       }
       else{
           mazo.add(cartaActual);
       }
       
       return tienePropietario;
    }
    
    public boolean cancelarHipoteca(Casilla casilla){
        boolean cancelada=false;    
        if(casilla.estaHipotecada()){
            casilla.cancelarHipoteca();
            cancelada=true;
        }
        return cancelada;
    }
    
    public boolean comprarTituloPropiedad(){
        boolean puedoComprar = jugadorActual.comprarTitulo();
        return puedoComprar;
    }
    
    public boolean edificarCasa(Casilla casilla){
        boolean puedoEdificar = false;
        boolean puedoEdificarCasa = false;
        int costeEdificarCasa = 0;
        if(casilla.soyEdificable()){       
            boolean sePuedeEdificar = casilla.sePuedeEdificarCasa();

            if(sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarCasa(casilla);                
            }
            
            if(puedoEdificar){
                costeEdificarCasa = casilla.edificarCasa();
                jugadorActual.modificarSaldo(-costeEdificarCasa);
                puedoEdificarCasa = puedoEdificar;
            }
        }
        return puedoEdificarCasa;
    }
    
    public boolean edificarHotel(Casilla casilla){
        boolean puedoEdificar = false;
        boolean puedoEdificarHotel = false;
        int costeEdificarHotel = 0;
        if(casilla.soyEdificable()){       
            boolean sePuedeEdificar = casilla.sePuedeEdificarHotel();

            if(sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarHotel(casilla);                
            }
            
            if(puedoEdificar){
                costeEdificarHotel = casilla.edificarHotel();
                jugadorActual.modificarSaldo(-costeEdificarHotel);
                puedoEdificarHotel = puedoEdificar;
            }
        }
        return puedoEdificarHotel;
    }
    
    public boolean hipotecarPropiedad(Casilla casilla){
        boolean puedoHipotecarPropiedad = false;
        boolean sePuedeHipotecar = false;
        boolean puedoHipotecar = false;
        int cantidadRecibida = 0;
        if(casilla.soyEdificable()){
            sePuedeHipotecar = !casilla.estaHipotecada();
            
            if(sePuedeHipotecar){
                puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
                
                if(puedoHipotecar){
                    cantidadRecibida = casilla.hipotecar();             
                    jugadorActual.modificarSaldo(cantidadRecibida);
                    puedoHipotecarPropiedad = puedoHipotecar;
                }
            }
                
        }
        return puedoHipotecarPropiedad;
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
        if(nombres.size() >= 2 && nombres.size() <= MAX_JUGADORES && mazo.size() <= MAX_CARTAS){
            inicializarJugadores(nombres);
            inicializarCartasSorpresa();
            inicializarTablero();
            salidaJugadores();
        }
        else{
            throw new UnsupportedOperationException("O el numero de jugadores es incorrecto (debe ser de 2 a " + MAX_JUGADORES + " jugadores)"
            + "o el numero de cartas es incorrecto (deben ser menor que " + MAX_CARTAS + ")");           
        }
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        
        boolean libre = false;
        boolean tengoSaldo = false;
        int valorDado = 0;
        int cantidad = -Qytetet.PRECIO_LIBERTAD;
        Dado dado = Dado.getInstance();
        
        
        if(metodo == MetodoSalirCarcel.TIRANDODADO){
            valorDado = dado.tirar();
            libre = valorDado>5;
        }
        
        else if(metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
            tengoSaldo = jugadorActual.pagarLibertad(cantidad);
            libre = tengoSaldo;
        }
        
        if(libre){
            jugadorActual.setEncarcelado(!libre);
        }
        
        return libre;
    }
    
    public boolean jugar(){
        boolean tienePropietario = false;
        Dado dado = Dado.getInstance();
        
        int valorDado = dado.tirar();
        Casilla casillaPosicion = jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
        
        if(!nuevaCasilla.soyEdificable()){
            if(nuevaCasilla.getTipo() == TipoCasilla.JUEZ){
                this.encarcelarJugador();
            }
            else if(nuevaCasilla.getTipo() == TipoCasilla.SORPRESA){
                cartaActual = mazo.get(0);
                mazo.remove(cartaActual);
            }
        }
        
        return tienePropietario;
    }
    
    //Hay que hacerlo de otra manera :(
    public ArrayList<List> obtenerRanking(){
        ArrayList<List> ranking = new ArrayList();
        ArrayList<Object> lista = new ArrayList<Object>();
        int capital = 0;
        for(Jugador jugador : jugadores){
            capital = jugador.obtenerCapital();        
            lista.add(jugador.getNombre());
            lista.add(capital);
            ranking.add(lista);
            lista.clear();
        }
        
        return ranking;
    }
    
    public ArrayList<Casilla> propiedadesHipotecadas(boolean hipotecadas){
        ArrayList<Casilla> casillaHipotecada = new ArrayList();
        ArrayList<TituloPropiedad> tPropiedad = jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
        
        for (int i = 0; i < tPropiedad.size(); i++){
           if( tablero.obtenerCasillaNumero(i).getTitulo() == tPropiedad.get(i)){
               casillaHipotecada.add(tablero.obtenerCasillaNumero(i));
           }
        }
        
        return casillaHipotecada;
    }
    
    public Jugador siguienteJugador(){
        int posicionArray = jugadores.indexOf(jugadorActual);
        if(posicionArray == jugadores.size()-1){
            jugadorActual = jugadores.get(0);
        }
        else{
            jugadorActual = jugadores.get(posicionArray+1);
        }
     
        return jugadorActual;
    }
    
    public boolean venderPropiedad(Casilla casilla){
        boolean puedoVender = false;
        if(casilla.soyEdificable()){
            puedoVender = jugadorActual.puedoVenderPropiedad(casilla);
            
            if(puedoVender){
                jugadorActual.venderPropiedad(casilla);
            }
        }
        return puedoVender;
    }
    
    private void encarcelarJugador(){
        Casilla casillaCarcel;
        Sorpresa carta;
        if(!jugadorActual.tengoCartaLibertad()){
            casillaCarcel = tablero.getCarcel();
            jugadorActual.irACarcel(casillaCarcel);
        }
        else{
            carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
        }
            
    }
    
    private void salidaJugadores(){
        Random aleatorio = new Random();
        int pos = aleatorio.nextInt(jugadores.size());
        jugadorActual = jugadores.get(pos);
        Casilla salida = tablero.obtenerCasillaNumero(0);
        for(Jugador j: jugadores){
            j.setCasillaActual(salida);
            j.modificarSaldo(7500);
        }
    }
    
    public ArrayList<Casilla> casillasPropiedadJugador(){
        ArrayList<Casilla> mias = new ArrayList();
        for (int i = 0; i < 20; i++) {
           if(jugadorActual.esDeMiPropiedad(tablero.obtenerCasillaNumero(i))){
               mias.add(tablero.obtenerCasillaNumero(i));
            }
        }
        return mias;    
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
