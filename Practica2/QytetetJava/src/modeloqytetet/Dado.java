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
public class Dado {
    private static final Dado INSTANCE = new Dado();
    private Dado() {
    }
    
    public static Dado getInstance() {
        return INSTANCE;
    }
    
    //Método
    int tirar(){
        throw new UnsupportedOperationException("Sin implementar");
    }
}
