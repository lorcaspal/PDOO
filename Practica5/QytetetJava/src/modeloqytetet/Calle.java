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

public class Calle extends Casilla{
    //Variables
    private int coste; //indica el coste de compra del titulo de la propiedad de esa casilla
    private int numHoteles, numCasas; // indica el numero de hoteles y de casas edificados en esa casilla
    private TituloPropiedad titulo; //para asociar la casilla a su titulo de propiedad
    
    //Constructor
    Calle(){}
    Calle(int numeroCasilla, int coste, TipoCasilla tipo, TituloPropiedad titulo){
        super(numeroCasilla, tipo);
        this.coste = coste;
        numHoteles = 0;
        numCasas = 0;
        this.titulo = titulo;
        
       titulo.setCasilla(this);
    }
    
    //Modificadores
    void setNumHoteles(int nuevoNumero){
        numHoteles = nuevoNumero;
    }
    
    void setNumCasas(int nuevoNumero){
        numCasas = nuevoNumero;
    }
    
    private void setTitulo(TituloPropiedad titulo){
        this.titulo = titulo;
    }
    
    //Consultores
    int getCoste(){
        return coste;
    }
    
    int getNumHoteles(){
        return numHoteles;
    }
    
    int getNumCasas(){
        return numCasas;
    }
    
    public TituloPropiedad getTitulo(){
        return titulo;
    }
    
    int getCosteHipoteca(){
        return titulo.getHipotecaBase();
    }
    
    int getPrecioEdificar(){
        return titulo.getPrecioEdificar();
    }
    
    //Métodos
    
    TituloPropiedad asignarPropietario(Jugador jugador){
        titulo.setPropietario(jugador);
        return titulo;
    }
    
    
    int calcularValorHipoteca(){
        int hipotecaBase = titulo.getHipotecaBase();
        int cantidadRecibida = hipotecaBase + (int)(this.getNumCasas()*0.5*hipotecaBase + this.getNumHoteles()*hipotecaBase);
        return cantidadRecibida;
    }
    
    
    int cancelarHipoteca(){
        int cantidadRecibida = (int)(this.calcularValorHipoteca() + 0.1);
        return cantidadRecibida;
    }
    
    
    int cobrarAlquiler(){
        int costeAlquilerBase = titulo.getAlquilerBase();
        int costeAlquiler = costeAlquilerBase + (int)(this.getNumCasas()*0.5 + this.getNumHoteles()*2);
        
        titulo.cobrarAlquiler(costeAlquiler);
        
        return costeAlquiler;
    }
    
    
    int edificarCasa(){
        int nuevoNumero = this.getNumCasas() + 1;
        this.setNumCasas(nuevoNumero);
        
        int costeEdificarCasa = titulo.getPrecioEdificar();
        return costeEdificarCasa;
    }
    
    
    int edificarHotel(){
        setNumCasas(0);
        int nuevoNumero = this.getNumHoteles() + 1;
        this.setNumHoteles(nuevoNumero);
        
        int costeEdificarHotel = titulo.getPrecioEdificar();
        return costeEdificarHotel;
    }
    
    
    boolean estaHipotecada(){
        return titulo.getHipotecada();
    }
    
    
    int hipotecar(){
        titulo.setHipotecada(true);
        return this.calcularValorHipoteca();
    }
    
    
    int precioTotalComprar(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    
    boolean propietarioEncarcelado(){
        return titulo.propietarioEncarcelado();
    }
    
    
    public boolean sePuedeEdificarCasa(int factorEspeculador){
        return getNumCasas() < 4*factorEspeculador;
    }
    
    
    public boolean sePuedeEdificarHotel(int factorEspeculador){
        if ( getNumCasas() == 4*factorEspeculador && getNumHoteles() < 4*factorEspeculador){
            return true;
        }
        else{
            return false;
        }
    }
       
    boolean tengoPropietario(){
        return titulo.tengoPropietario();
    }
    
    
    int venderTitulo(){
        int precioCompra = this.getCoste() + (this.getNumCasas() + this.getNumHoteles()) * titulo.getPrecioEdificar();
        int precioVenta = (int)(precioCompra + titulo.getFactorRevalorizacion()*precioCompra);
        
        titulo.setPropietario(null);
        this.setNumHoteles(0);
        this.setNumCasas(0);
        
        return precioVenta;
    }
    
    
    protected void asignarTituloPropiedad(){}
    
        //Método toString() devuelve un String con el estado del objeto correspondiente
    
    public String toString(){
        return "\n Numero de la Casilla = " + Integer.toString(super.getNumeroCasilla())
                + "\n Coste = " + Integer.toString(coste)
                + "\n Numero de hoteles = " + Integer.toString(numHoteles) 
                + "\n Numero de casas = " + Integer.toString(numCasas)
                + "\n Tipo de la casilla = " + super.getTipo()
                + "\n Titulo de propiedad = " + titulo.toString() + "\n";
    }

    
}
