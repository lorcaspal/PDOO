/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author Lorena
 */
public class TituloPropiedad {
    private String nombre; //nombre de la calle
    private boolean hipotecada; //indica si esta hipotecada la propiedad o no
    private int alquilerBase; //precio base (sin tener en cuenta las edificaciones) que debe pagar quien caiga en la casilla.
    private float factorRevalorizacion; //indica cuánto se revaloriza el título de propiedad en el periodo transcurrido entre su compra y su venta.
    private int hipotecaBase; //indica cuál es el valor base de su hipoteca,
    private int precioEdificar; //indica cuánto cuesta edificar casas y hoteles,

    //Variables referenciadas
    Casilla casilla;
    Jugador propietario;
    
    //Constructor
    public TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar){
        this.nombre = nombre;
        hipotecada = false;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
        casilla = new Casilla();
        propietario = null;
    }
    
    //Modificador 
    void setHipotecada(boolean condicion){
        hipotecada = condicion;
    }
    void setCasilla(Casilla casilla){
        this.casilla = casilla;
    }
    void setPropietario(Jugador propietario){
        this.propietario = propietario;
    }
    
    //Consultores
    boolean getHipotecada(){
        return hipotecada;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    int getAlquilerBase(){
        return alquilerBase;
    }
    
    float getFactorRevalorizacion(){
        return factorRevalorizacion;
    }
    
    int getHipotecaBase(){
        return hipotecaBase;
    }
    
    int getPrecioEdificar(){
        return precioEdificar;
    }
    
    Casilla getCasillaTituloPropiedad(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void cobrarAlquiler(int coste){
        propietario.modificarSaldo(-coste);
    }
    
    boolean propietarioEncarcelado(){
        return propietario.getEncarcelado();
    }
    
    boolean tengoPropietario(){
       if(propietario != null){
           return true;
       }
       else{
           return false;
       }
    }
    
    
    //Método toString() devuelve un String con el estado del objeto correspondiente
    @Override
    public String toString(){
        return " Nombre:" + nombre 
                + "\n Esta hipotecada?:" + Boolean.toString(hipotecada)
                + "\n Precio base alquiler:" + Integer.toString(alquilerBase) 
                + "\n Factor de revalorizacion:" + Float.toString(factorRevalorizacion)
                + "\n Valor base hipoteca:" + Integer.toString(hipotecaBase) 
                + "\n Precio de edificacion:" + Integer.toString(precioEdificar);
    }
    
}    
