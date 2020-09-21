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
public class Jugador {
    private boolean encarcelado;
    private String nombre;
    private int saldo;
    private Casilla casillaActual;
    private Sorpresa cartaLibertad;
    private ArrayList<TituloPropiedad> propiedades;

    //Constructores
    public Jugador(String nombre){
        this.nombre = nombre;
        encarcelado = false;
        saldo = 0;
        casillaActual = new Casilla();
        propiedades = new ArrayList();
    }

    Jugador() {
        
    }
    //Consultores y modificadores
    public String getNombre(){
        return nombre;
    }
    
    public int getSaldo(){
        return saldo;
    }
    
    public Casilla getCasillaActual(){
        return casillaActual;
    }
    
    public boolean getEncarcelado(){
        return encarcelado;
    }
    
    void setCartaLibertad(Sorpresa carta){
        cartaLibertad = carta;
    }
    
    void setCasillaActual(Casilla casilla){
        casillaActual = casilla;
    }
    
    public void setEncarcelado(boolean encarcelado){
        System.out.println(encarcelado);
        this.encarcelado = encarcelado;
    }
    
    //Métodos
    public boolean tengoPropiedades(){
        if(propiedades.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    
    boolean actualizarPosicion(Casilla casilla){
        
        if(casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla()){
            int cantidad = Qytetet.SALIDA;
            this.modificarSaldo(cantidad);
        }
        boolean tienePropietario = false;
        this.setCasillaActual(casilla);
        
        if(casilla.soyEdificable()){
            
            if(casilla.tengoPropietario()){
               encarcelado = casilla.propietarioEncarcelado();
               if(!encarcelado){
                int costeAlquiler = casilla.cobrarAlquiler();
                this.modificarSaldo(-costeAlquiler);
                }
               tienePropietario=true;
            }
           
        }
        else if(casilla.getTipo() == TipoCasilla.IMPUESTO){
            int coste = casilla.getCoste();
            this.modificarSaldo(-coste);
        }
            
        return tienePropietario;    
    }
    
    boolean comprarTitulo(){
        boolean puedoComprar = false;
        if(casillaActual.soyEdificable()){
            boolean tengoPropietario = casillaActual.tengoPropietario();
            
            if(!tengoPropietario){
                int costeCompra = casillaActual.getCoste();
                
                if(costeCompra <= saldo){
                    TituloPropiedad titulo = casillaActual.asignarPropietario(this);
                    propiedades.add(titulo);
                    int cantidad = -costeCompra;
                    this.modificarSaldo(cantidad);
                    puedoComprar = true;
                }
            }
        }
        return puedoComprar;
    }
    
    //Preguntar
    Sorpresa devolverCartaLibertad(){
        Sorpresa devolver = null;
        if(tengoCartaLibertad()){
            devolver = null;
            cartaLibertad = devolver;
        }           
        return cartaLibertad;
    }
    
    void irACarcel(Casilla casilla){
        this.setCasillaActual(casilla);
        this.setEncarcelado(true);
    }
    
    void modificarSaldo(int cantidad){
        saldo += cantidad;
    }
    
    int obtenerCapital(){
        int capital = 0;
        int prop = 0;
        for (int i = 0; i < propiedades.size(); i++){
            if(!propiedades.get(i).getHipotecada()){
                prop +=  propiedades.get(i).casilla.getCoste() + this.cuantasCasasHotelesTengo() + propiedades.get(i).getPrecioEdificar();
            }
            else{
                prop += propiedades.get(i).casilla.getCoste() + this.cuantasCasasHotelesTengo() + propiedades.get(i).getPrecioEdificar() 
                        - propiedades.get(i).getHipotecaBase();
            }
        }
        capital = saldo + prop;
        return capital;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada){
        ArrayList<TituloPropiedad> estanHipotecados = new ArrayList();
        for(int i = 0; i < propiedades.size(); i++){
            if(propiedades.get(i).getHipotecada() == hipotecada){
                estanHipotecados.add(propiedades.get(i));
            }
        }
        return estanHipotecados;
    }
    
    void pagarCobrarPorCasaYHotel(int cantidad){
        int numeroTotal = this.cuantasCasasHotelesTengo();
        this.modificarSaldo(numeroTotal*cantidad);
    }
    
    boolean pagarLibertad(int cantidad){
        boolean tengoSaldo = this.tengoSaldo(cantidad);
        
        if(tengoSaldo){
            this.modificarSaldo(cantidad);
        }
        return tengoSaldo;
    }
    
    boolean puedoEdificarCasa(Casilla casilla){
        boolean esMia = this.esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        if(esMia){          
            int cantidad = casilla.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(cantidad);
        }
        return tengoSaldo;
    }
    
    boolean puedoEdificarHotel(Casilla casilla){
        boolean esMia = this.esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        if(esMia){          
            int cantidad = casilla.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(cantidad);
        }
        return tengoSaldo;
    }
    
    boolean puedoHipotecar(Casilla casilla){
        return this.esDeMiPropiedad(casilla);
    }
    
    boolean puedoPagarHipoteca(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean puedoVenderPropiedad(Casilla casilla){        
        boolean hipotecada = casilla.estaHipotecada();
        if(!hipotecada && esDeMiPropiedad(casilla) )
            return true;
        else 
            return false;
    }
    
   
    boolean tengoCartaLibertad(){
        if(cartaLibertad != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    void venderPropiedad(Casilla casilla){
        int precioVenta = casilla.venderTitulo();
        this.modificarSaldo(precioVenta);
        this.eliminarDeMisPropiedades(casilla);
    }
    
    private int cuantasCasasHotelesTengo(){
        int total = 0;
        for(int i = 0; i < propiedades.size(); i++){
            total += propiedades.get(i).casilla.getNumCasas() + propiedades.get(i).casilla.getNumHoteles();
        }
        return total;
    }
    
    private void eliminarDeMisPropiedades(Casilla casilla){
        propiedades.remove(casilla.getTitulo());
    }
    
    public boolean esDeMiPropiedad(Casilla casilla){
        boolean esMia = false;
        for(int i = 0; i < propiedades.size(); i++){
            if(propiedades.get(i) == casilla.getTitulo()){
                esMia = true;
            }
        }
        return esMia;
    }
    
    private boolean tengoSaldo(int cantidad){
        if(saldo >= cantidad){
            return true;
        }
        else{
            return false;           
        }
    }
    

    
       //Método toString() devuelve un String con el estado del objeto correspondiente
    @Override
    public String toString(){
        return "Nombre: " + nombre + "\n"
                + "Está encarcelado? " + encarcelado + "\n"
                + "Saldo: " + saldo + "\n"
                + "Casilla actual: " + casillaActual + "\n"
                + "Tiene carta libertad? " + cartaLibertad + "\n"
                + "Propiedades: " + propiedades.toString() + "\n";
    }
    
}
