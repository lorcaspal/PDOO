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
public class Sorpresa {
    //Declaracion de variables
    private String texto;
    private TipoSorpresa tipo;
    private int valor;
    
    //Constructor
    public Sorpresa(){
    
    }
    
    public Sorpresa(String texto, int valor, TipoSorpresa tipo){
        this.texto = texto;
        this.valor = valor;
        this.tipo = tipo;
    }
    
    //Consultores
    String getTexto(){
        return texto;
    }
    TipoSorpresa getTipo(){
        return tipo;
    }
    int getValor(){
        return valor;
    }
    
    //Método toString() devuelve un String con el estado del objeto correspondiente
    @Override
    public String toString(){
        return "Sorpresa:" + "\n texto=" + texto + "\n valor=" + Integer.toString(valor) + "\n tipo=" + tipo + "\n";
    }
    
}



