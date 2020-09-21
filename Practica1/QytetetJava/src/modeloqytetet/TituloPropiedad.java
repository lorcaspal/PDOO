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

    //Constructor
    public TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar){
        this.nombre = nombre;
        hipotecada = false;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
    }
    
    //Modificador 
    void setHipotecada(boolean condicion){
        hipotecada = condicion;
    }
    
    //Consultores
    boolean getHipotecada(){
        return hipotecada;
    }
    
    String getNombre(){
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
