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
    private int numHoteles, numCasas; // inidca el numero de hoteles y de cassas edificados en esa casilla
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
    void setNumHoteles(int numHoteles){
        this.numHoteles = numHoteles;
    }
    
    void setNumCasas(int numCasas){
        this.numCasas = numCasas;
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
