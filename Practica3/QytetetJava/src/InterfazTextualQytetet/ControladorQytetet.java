/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazTextualQytetet;

import java.util.ArrayList;
import java.util.Scanner;
import modeloqytetet.*;

/**
 *
 * @author jopoku
 */
public class ControladorQytetet {
    
    
    Jugador jugador;
    Casilla casilla;
    Qytetet juego = Qytetet.getInstance();
    VistaTextualQytetet vista = new VistaTextualQytetet();
    
    public void inicializarJuego(){
        
        ArrayList<String> nombres = vista.obtenerNumeroJugadores();
        juego.inicializarJuego(nombres);
        jugador = juego.getJugadorActual();
        casilla = jugador.getCasillaActual();
        desarrolloJuego();
        
    }
    
    private Casilla elegirCasilla(ArrayList<Casilla> propiedades){return null;
    }
    
    public void desarrolloJuego(){
        int tipo_Casilla = 0;
        boolean bancarrota=true;
       if(jugador.getSaldo() > 0){
            bancarrota= false;
       }
        while(!bancarrota){
        System.out.println("El jugador actual es : " + jugador.getNombre() + " con el saldo : "+ jugador.getSaldo() +" y la casilla en la que esta es : "+ jugador.getCasillaActual());
        
        if(jugador.getEncarcelado()){
            System.out.println("Estas en la carcel , tienes dos metodos : tirar el dado(escribe dado) o pagar la libertad(escribe libertad)");
            Scanner sc = new Scanner(System.in);
            String lectura = sc.nextLine();
            boolean salir=false;
            if(lectura.endsWith("dado")){
               salir = juego.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
            }else if(lectura.endsWith("libertad")){
                salir = juego.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
            }
            if(!salir){
                System.out.println("OHHHHHHHHHH no has salido , pierdes el turno");
                jugador = juego.siguienteJugador();
            }else {
                System.out.println("Felicidades has salido de la carcel!!!!!! ya puedes seguir robando");
            }
        }
        if(!jugador.getEncarcelado()){ 
                //System.out.println("El jugador actual es : " + jugador.getNombre() + " y la casilla en la que esta es : "+ jugador.getCasillaActual());
                System.out.println("tirandooooo dadoooo .......");
                boolean notienepropietario = juego.jugar();
                casilla = jugador.getCasillaActual();
                System.out.println("El jugador actual es : " + jugador.getNombre());
                if(casilla.getTipo() == TipoCasilla.SORPRESA){
                    tipo_Casilla=1;
                     System.out.println(juego.getCartaActual());
                    boolean aplicarSorpresa = juego.aplicarSorpresa();
                     //System.out.println("El jugador actual es : " + jugador.getNombre() + " y la casilla en la que esta es : "+ casilla);
                     if(jugador.getSaldo() > 0){
                        bancarrota= false;
                        }else{
                         bancarrota = true;
                     }
                }
                else if(casilla.getTipo() == TipoCasilla.CARCEL){
                    System.out.println("ohhhhh has caido en la casilla del juez o te ha tocado una carta sorpresa y te vas a la carceel");
                    tipo_Casilla=1;
                }
                else{
                    tipo_Casilla=2;
                    System.out.println(casilla);
                    if(!notienepropietario){
                        int recibido = vista.menuGestionInmobiliaria(3);
                         switch(recibido){
                    case 0: boolean comprada = juego.comprarTituloPropiedad();
                        if(comprada){
                            System.out.println("Has conseguido comprarla" + " y te queda de saldo : " + jugador.getSaldo());
                        }else{
                            System.out.println("ohhh no has podido comprarla");
                            tipo_Casilla=1;
                        }
                            break;
                    case 1: tipo_Casilla=1; 
                        break;
                         
                    }
                    }else {
                          System.out.println("Ohhhh te toca pagar alquiler y te quedas con : " + jugador.getSaldo());
                                                   
                    }
                }
                boolean salir=false;
                while(!salir){
                    int recibido = vista.menuGestionInmobiliaria(tipo_Casilla);
                    switch(recibido ){
                    case 0: juego.siguienteJugador();
                            salir=true;
                            break;
                    case 1: if(jugador.tengoPropiedades()){
                            ArrayList<Casilla> mias = juego.casillasPropiedadJugador();
                            System.out.println("Estas son tus casillas para edificar, dale al numero para el que quieras edificar");
                            for (int i = 0; i < mias.size(); i++) {
                                System.out.println( "en la posicion " + i + " " +mias.get(i).getTitulo().getNombre());
                             }
                            Scanner sc = new Scanner(System.in);
                            String lectura = sc.nextLine(); 
                            boolean puedoConstruir = juego.edificarCasa(mias.get(Integer.parseInt(lectura)));
                                if(puedoConstruir){
                                    System.out.println("Felicidades has construido una casa");
                                    System.out.println(mias.get(Integer.parseInt(lectura)));
                                }else{
                                    System.out.println("Oh no has podido construir la casa");
                                }
                            }
                            else{
                                System.out.println("No tienes propiedades donde edificar");
                            }
     
                            break;
                    case 2: if(jugador.tengoPropiedades()){
                            ArrayList<Casilla> mias = juego.casillasPropiedadJugador();
                            System.out.println("Estas son tus casillas para edificar, dale al numero para el que quieras edificar");
                            for (int i = 0; i < mias.size(); i++) {
                                System.out.println( "en la posicion " + i + " " +mias.get(i).getTitulo().getNombre());
                             }
                            Scanner sc = new Scanner(System.in);
                            String lectura = sc.nextLine(); 
                            boolean puedoConstruir = juego.edificarHotel(mias.get(Integer.parseInt(lectura)));
                                if(puedoConstruir){
                                    System.out.println("Felicidades has construido un hotel");
                                    System.out.println(mias.get(Integer.parseInt(lectura)));
                                }else{
                                    System.out.println("Oh no has podido construir el hotel, bien por falta de saldo o porque no tienes 4 casas construidas antes");
                                }
                            }
                            else{
                                System.out.println("No tienes propiedades donde edificar");
                            }
                            break;
                    case 3: if(jugador.tengoPropiedades()){
                            ArrayList<Casilla> mias = juego.casillasPropiedadJugador();
                            System.out.println("Estas son tus casillas para vender, dale al numero para el que quieras vender");
                            for (int i = 0; i < mias.size(); i++) {
                                System.out.println( "en la posicion " + i + " " +mias.get(i).getTitulo().getNombre());
                             }
                            Scanner sc = new Scanner(System.in);
                            String lectura = sc.nextLine(); 
                               boolean vendida = juego.venderPropiedad(mias.get(Integer.parseInt(lectura)));
                               if(vendida){
                                    System.out.println("Felicidades la has vendido");
                                }else{
                                    System.out.println("Oh no has podido vender");
                                }
                            }
                            else{
                                System.out.println("No tienes propiedades para vender");
                            }
                            juego.siguienteJugador();
                            break;
                    case 4: if(jugador.tengoPropiedades()){
                            ArrayList<Casilla> mias = juego.casillasPropiedadJugador();
                            System.out.println("Estas son tus casillas para hipotecar, dale al numero correspondiente al que quieras hipotecar");
                            for (int i = 0; i < mias.size(); i++) {
                                System.out.println( "en la posicion " + i + " " +mias.get(i).getTitulo().getNombre());
                             }
                            Scanner sc = new Scanner(System.in);
                            String lectura = sc.nextLine(); 
                             boolean hipotecada =juego.hipotecarPropiedad(mias.get(Integer.parseInt(lectura)));
                                if(hipotecada){
                                    System.out.println("has conseguido hipotecarla");
                                }else {
                                    System.out.println("no has conseguido hipotecarla");
                                }
                                
                            }
                            else{
                                System.out.println("No tienes propiedades para hipotecar");
                            }
                            break;
                    case 5: if(jugador.tengoPropiedades()){
                            ArrayList<Casilla> mias = juego.casillasPropiedadJugador();
                            System.out.println("Estas son tus casillas para cancelar, dale al numero correspondiente al que quieras cancelar");
                            for (int i = 0; i < mias.size(); i++) {
                                System.out.println( "en la posicion " + i + " " + mias.get(i).getTitulo().getNombre());
                             }
                            Scanner sc = new Scanner(System.in);
                            String lectura = sc.nextLine(); 
                             boolean hipotecada =juego.cancelarHipoteca(mias.get(Integer.parseInt(lectura)));
                                if(hipotecada){
                                    System.out.println("has conseguido cancerla la hipoteca");
                                }else {
                                    System.out.println("no has conseguido cancelar la hipoteca");
                                }
                                
                            }
                            else{
                                System.out.println("No tienes propiedades para cancelar hipotecar");
                            }
                            break;
                         
                     }
                }
               
                
             }  
              jugador = juego.getJugadorActual();
            }
       
        
        //fin del while
        if(bancarrota){
            System.out.println("Se ha acabado el juego.");
        }
    }
    
    public static void main(String[] args) {
        ControladorQytetet prueba = new ControladorQytetet();
        prueba.inicializarJuego();
    }
}
