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
public class OtraCasilla extends Casilla{
    //Constructor 
    OtraCasilla(){}
    OtraCasilla(int numeroCasilla, TipoCasilla tipo){
        super(numeroCasilla,tipo);
    }

    //Método toString() devuelve un String con el estado del objeto correspondiente
    @Override
    public String toString(){
        return "\n Numero de la Casilla = " + Integer.toString(getNumeroCasilla())
            + "\n Tipo de la casilla = " + getTipo();
    }


    int getCoste() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    int getNumCasas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int getNumHoteles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
