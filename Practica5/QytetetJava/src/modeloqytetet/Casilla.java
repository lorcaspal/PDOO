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
public abstract class Casilla {
    private int numeroCasilla; //indica numero de casilla
    private TipoCasilla tipo; // indica el tipo de la casilla
    
    //Constructores:
    //Por defecto
    public Casilla(){}
    
    //Normal
    Casilla(int numeroCasilla, TipoCasilla tipo){
        this.numeroCasilla = numeroCasilla;
        this.tipo = tipo;
    }
    
    //Consultores
    public int getNumeroCasilla(){
        return numeroCasilla;
    }
    
    public TipoCasilla getTipo(){
        return tipo;
    }
    
    //Metodos
    boolean soyEdificable(){
        return tipo == TipoCasilla.CALLE;
    }
    
    public abstract String toString();
     
}
