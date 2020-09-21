/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.Random;

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
        Random aleatorio = new Random();
        int dado = aleatorio.nextInt(6) +1;
        return dado;
    }
}
