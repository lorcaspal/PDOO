/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author Lorena
 */
public class Tablero {
   private ArrayList<Casilla> casillas;
   private Casilla carcel;
   
   //Constructores
   //Sin argumentos
   Tablero(){
       casillas = new ArrayList();
       inicializar();
   }
   
   //consultores
   Casilla getCarcel(){
       return carcel;
   }
   

    //Metodos 
    boolean esCasillaCarcel(int numeroCasilla){
        return numeroCasilla == getCarcel().getNumeroCasilla();
    }
    
    Casilla obtenerCasillaNumero(int numeroCasilla){
        Casilla obtCasilla = null;
        for(int i = 0; i < casillas.size(); i++){
            if(numeroCasilla == casillas.get(i).getNumeroCasilla()){
                obtCasilla =  casillas.get(i);                
            }
        }
        return obtCasilla;
    }
    
    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento){
        int nuevaCasilla = casilla.getNumeroCasilla() + desplazamiento;
        if (nuevaCasilla > 19){
            nuevaCasilla = (nuevaCasilla%19) - 1;
        }
        Casilla c = obtenerCasillaNumero(nuevaCasilla);
        return c;
    }
    
    //Método inicializar
    private void inicializar(){
        
        
        //Casilla de salida
        casillas.add(new Casilla(0, TipoCasilla.SALIDA));
        
        //Casillas de tipo calle
        casillas.add(new Casilla(1, 100, TipoCasilla.CALLE, new TituloPropiedad("Calle Piruleta", 60, (float) 0.1, 150, 250)));
        casillas.add(new Casilla(2, 300, TipoCasilla.CALLE, new TituloPropiedad("Calle Nube", 50, (float) 0.16, 156, 270)));
        casillas.add(new Casilla(3, 210, TipoCasilla.CALLE, new TituloPropiedad("Calle Gominola", 55, (float) 0.11, 160, 300)));
        
        //Casilla de tipo sopresa
        casillas.add(new Casilla(4, TipoCasilla.SORPRESA));
        
        //Casillas de tipo calle
        casillas.add(new Casilla(5, 400, TipoCasilla.CALLE, new TituloPropiedad("Calle de Chocolate", 70, (float) -0.1, 400, 300)));
        casillas.add(new Casilla(6, 450, TipoCasilla.CALLE, new TituloPropiedad("Calle Bombon", 75, (float) -0.16, 500, 350)));
        
        //Casilla tipo Juez
        casillas.add(new Casilla(7, TipoCasilla.JUEZ));
        
        //Casilla tipo calle
        casillas.add(new Casilla(8, 100, TipoCasilla.CALLE, new TituloPropiedad("Calle Perla", 50, (float) -0.2, 150, 250)));
        
        //Casilla tipo Carcel
        casillas.add(new Casilla(9, TipoCasilla.CARCEL));
        
        //Casilla tipo Sorpresa
        casillas.add(new Casilla(10, TipoCasilla.SORPRESA));
        
        //Casillas de tipo calle
        casillas.add(new Casilla(11, 470, TipoCasilla.CALLE, new TituloPropiedad("Calle Bastón de Caramelo", 70, (float) 0.1, 600, 300)));
        casillas.add(new Casilla(12, 480, TipoCasilla.CALLE, new TituloPropiedad("Calle Cupcake", 75, (float) 0.16, 700, 350)));
        casillas.add(new Casilla(13, 490, TipoCasilla.CALLE, new TituloPropiedad("Calle Manzana de Caramelo", 80, (float) 0.17, 750, 400)));
        
        //Casilla tipo Sorpresa
        casillas.add(new Casilla(14, TipoCasilla.SORPRESA));
        
        //Casilla tipo Parking
        casillas.add(new Casilla(15, TipoCasilla.PARKING));
        
        //Casillas de tipo calle
        casillas.add(new Casilla(16, 600, TipoCasilla.CALLE, new TituloPropiedad("Calle Galleta", 80, (float) 0.18, 800, 560)));
        casillas.add(new Casilla(17, 650, TipoCasilla.CALLE, new TituloPropiedad("Calle Bizcocho", 90, (float) 0.19, 900, 600)));
        
        //Casilla tipo Impuesto
        casillas.add(new Casilla(18, TipoCasilla.IMPUESTO));
        
        //Casillas de tipo calle
        casillas.add(new Casilla(19, 700, TipoCasilla.CALLE, new TituloPropiedad("Calle Lemon Pie", 100, (float) 0.2, 1000, 750)));
        
        //Inicializar casilla carcel en el numero 9
        carcel = casillas.get(9);
        
    }
    
    //Método toString() devuelve un String con el estado del objeto correspondiente
    @Override
    public String toString(){
        return "Casillas " + casillas.toString() 
               + "\n Carcel en" + carcel + "\n";
    }
}
