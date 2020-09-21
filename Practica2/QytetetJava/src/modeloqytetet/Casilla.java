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
public class Casilla {
    private int numeroCasilla; //indica numero de casilla
    private int coste; //indica el coste de compra del titulo de la propiedad de esa casilla
    private int numHoteles, numCasas; // indica el numero de hoteles y de casas edificados en esa casilla
    private TipoCasilla tipo; // indica el tipo de la casilla
    private TituloPropiedad titulo; //para asociar la casilla a su titulo de propiedad
    
    //Constructores:
    //Para los que son de tipo calle
    Casilla(int numeroCasilla, int coste, TipoCasilla tipo, TituloPropiedad titulo){
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        numHoteles = 0;
        numCasas = 0;
        this.tipo = tipo;
        this.titulo = titulo;
    }
    
    //Para los que NO son de tipo calle
    Casilla(int numeroCasilla, TipoCasilla tipo){
        this.numeroCasilla = numeroCasilla;
        this.tipo = tipo;
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
    int getNumeroCasilla(){
        return numeroCasilla;
    }
    
    int getCoste(){
        return coste;
    }
    
    int getNumHoteles(){
        return numHoteles;
    }
    
    int getNumCasas(){
        return numCasas;
    }
    
    TipoCasilla getTipo(){
        return tipo;
    }
    
    TituloPropiedad getTitulo(){
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
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int calcularValorHipoteca(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int cancelarHipoteca(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int cobrarAlquiler(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int edificarCasa(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int edificarHotel(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean estaHipotecada(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int hipotecar(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int precioTotalComprar(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean propietarioEncarcelado(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean sePuedeEdificarCasa(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean sePuedeEdificarHotel(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean soyEdificable(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean tengoPropietario(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int venderTitulo(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private void asignarTituloPropiedad(){}
    
    //Método toString() devuelve un String con el estado del objeto correspondiente
    @Override
    public String toString(){
        if(titulo != null){
            return "\n Numero de la Casilla =" + Integer.toString(numeroCasilla)
                    + "\n Coste = " + Integer.toString(coste)
                    + "\n Numero de hoteles =" + Integer.toString(numHoteles) 
                    + "\n Numero de casas =" + Integer.toString(numCasas)
                    + "\n Tipo de la casilla =" + tipo 
                    + "\n Titulo de propiedad =" + titulo.toString()
                    + "\n---------------------------------------------------------";
        }
        else{
            return "\n Numero de la Casilla =" + Integer.toString(numeroCasilla)
                + "\n Tipo de la casilla =" + tipo
                + "\n---------------------------------------------------------";

        }
    }

}
