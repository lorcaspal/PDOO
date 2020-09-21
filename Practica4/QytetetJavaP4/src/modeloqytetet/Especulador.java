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
public class Especulador extends Jugador{
    static final int FactorEspeculador = 2;
    private int fianza;

    protected Especulador(Jugador jugador, int fianza){
        super(jugador);
        this.fianza = fianza;
        //super.setFactorEspeculador(FactorEspeculador);
    }
    
    protected void pagarImpuesto(int cantidad){
        super.modificarSaldo(-cantidad/2);
    }
    
    protected void irACarcel(Casilla casilla){
        boolean noCarcel = pagarFianza(fianza);
        if(!noCarcel){
            super.irACarcel(casilla);
        }
        else{
            super.modificarSaldo(-fianza);
        }
    }
    
    protected Especulador convertirme(int fianza){
        return this;
    }
    
    private boolean pagarFianza(int cantidad){
        if(super.getSaldo() < cantidad){
            return false;
        }
        else{
            return true;
        }
    }
    
    public static int getFactorEspeculador() {
        return FactorEspeculador;
    }
    
    
    
    //Método toString() devuelve un String con el estado del objeto correspondiente
    @Override
    public String toString(){
        return  super.toString() 
                +"Factor especulador: " + FactorEspeculador + "\n"
                + "Fianza: " + fianza + "\n";
    }

   
}
