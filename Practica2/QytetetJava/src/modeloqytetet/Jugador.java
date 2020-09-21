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
public class Jugador {
    private boolean encarcelado;
    private String nombre;
    private int saldo;
    private Casilla casillaActual;
    private Sorpresa cartaLibertad=null;
    private ArrayList<TituloPropiedad> propiedades;

    //Constructores
    public Jugador(String nombre){
        this.nombre = nombre;
        encarcelado = false;
        saldo = 7500;
        casillaActual = null;
        propiedades = new ArrayList();
    }
    //Consultores y modificadores
    public Casilla getCasillaActual(){
        return casillaActual;
    }
    
    public boolean getEncarcelado(){
        return encarcelado;
    }
    
    void setCartaLibertad(Sorpresa carta){
        cartaLibertad = carta;
    }
    
    void setCasillaActual(Casilla casilla){
        casillaActual = casilla;
    }
    
    void setEncarcelado(boolean encarcelado){
        this.encarcelado = encarcelado;
    }
    
    //Métodos
    public boolean tengoPropiedades(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean actualizarPosicion(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean comprarTitulo(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    Sorpresa devolverCartaLibertad(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void irACarcel(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void modificarSaldo(int cantidad){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int obtenerCapital(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void pagarCobrarPorCasaYHotel(int cantidad){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean pagarLibertad(int cantidad){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean puedoEdificarCasa(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean puedoEdificarHotel(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean puedoHipotecar(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean puedoPagarHipoteca(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean puedoVenderPropiedad(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
   
    boolean tengoCartaLibertad(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void venderPropiedad(Casilla casilla){
        
    }
    
    private int cuantasCasasHotelesTengo(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private void eliminarDeMisPropiedades(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private boolean esDeMiPropiedad(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private boolean tengoSaldo(int cantidad){
        throw new UnsupportedOperationException("Sin implementar");
    }
    

    
       //Método toString() devuelve un String con el estado del objeto correspondiente
    @Override
    public String toString(){
        return "Nombre: " + nombre + "\n"
                + "Está encarcelado? " + encarcelado + "\n"
                + "Saldo: " + saldo + "\n"
                + "Tiene carta libertad? " + cartaLibertad + "\n"
                + "Propiedades: " + propiedades.toString() + "\n";
    }
    
}
