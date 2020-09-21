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
    static final int FactorEspeculador = 1;

    //Constructores
    public Jugador(String nombre) {
        this.nombre = nombre;
        encarcelado = false;
        saldo = 0;
        casillaActual = null;
        propiedades = new ArrayList();
    }

    Jugador() {

    }

    //Constructor de copia
    protected Jugador(Jugador jugador) {
        nombre = jugador.nombre;
        encarcelado = jugador.encarcelado;
        saldo = jugador.saldo;
        casillaActual = jugador.casillaActual;
        propiedades = jugador.propiedades;
    }

    //Consultores y modificadores
    public String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    public static int getFactorEspeculador() {
        return FactorEspeculador;
    }

    public Casilla getCasillaActual() {
        return casillaActual;
    }

    public ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }

    public boolean getEncarcelado() {
        return encarcelado;
    }

    void setCartaLibertad(Sorpresa carta) {
        cartaLibertad = carta;
    }

    void setCasillaActual(Casilla casilla) {
        casillaActual = casilla;
    }

    public void setEncarcelado(boolean encarcelado) {
        System.out.println(encarcelado);
        this.encarcelado = encarcelado;
    }

//    public static void setFactorEspeculador(int FactorEspeculador) {
//        Jugador.FactorEspeculador = FactorEspeculador;
//    }
    
    
    //Métodos
    public boolean tengoPropiedades() {
        if (propiedades.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    protected boolean actualizarPosicion(Casilla casilla) {

        if (casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla()) {
            int cantidad = Qytetet.SALIDA;
            this.modificarSaldo(cantidad);
        }
        boolean tienePropietario = false;
        this.setCasillaActual(casilla);

        if (casilla.soyEdificable()) {
            Calle aux = (Calle) casilla;
            if (aux.tengoPropietario()) {
                encarcelado = aux.propietarioEncarcelado();
                if (!encarcelado) {
                    int costeAlquiler = aux.cobrarAlquiler();
                    this.modificarSaldo(-costeAlquiler);
                }
                tienePropietario = true;
            }

        } else if (casilla.getTipo() == TipoCasilla.IMPUESTO) {
            Calle aux = (Calle) casilla;
            int coste = aux.getCoste();
            pagarImpuesto(coste);
        }

        return tienePropietario;
    }

    boolean comprarTitulo() {
        boolean puedoComprar = false;
        if (casillaActual.soyEdificable()) {
            Calle aux = (Calle) casillaActual;
            boolean tengoPropietario = aux.tengoPropietario();

            if (!tengoPropietario) {
                int costeCompra = aux.getCoste();

                if (costeCompra <= saldo) {
                    TituloPropiedad titulo = aux.asignarPropietario(this);
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
    Sorpresa devolverCartaLibertad() {
        Sorpresa devolver = null;
        if (tengoCartaLibertad()) {
            devolver = null;
            cartaLibertad = devolver;
        }
        return cartaLibertad;
    }

    void irACarcel(Casilla casilla) {
        this.setCasillaActual(casilla);
        this.setEncarcelado(true);
    }

    void modificarSaldo(int cantidad) {
        saldo += cantidad;
    }

    int obtenerCapital() {
        int capital = 0;
        int prop = 0;

        for (int i = 0; i < propiedades.size(); i++) {
            Calle aux = (Calle) propiedades.get(i).casilla;
            if (!propiedades.get(i).getHipotecada()) {
                prop += aux.getCoste() + this.cuantasCasasHotelesTengo() + propiedades.get(i).getPrecioEdificar();
            } else {
                prop += aux.getCoste() + this.cuantasCasasHotelesTengo() + propiedades.get(i).getPrecioEdificar()
                        - propiedades.get(i).getHipotecaBase();
            }
        }
        capital = saldo + prop;
        return capital;
    }

    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada) {
        ArrayList<TituloPropiedad> estanHipotecados = new ArrayList();
        for (int i = 0; i < propiedades.size(); i++) {
            if (propiedades.get(i).getHipotecada() == hipotecada) {
                estanHipotecados.add(propiedades.get(i));
            }
        }
        return estanHipotecados;
    }

    void pagarCobrarPorCasaYHotel(int cantidad) {
        int numeroTotal = this.cuantasCasasHotelesTengo();
        this.modificarSaldo(numeroTotal * cantidad);
    }

    boolean pagarLibertad(int cantidad) {
        boolean tengoSaldo = this.tengoSaldo(cantidad);

        if (tengoSaldo) {
            this.modificarSaldo(cantidad);
        }
        return tengoSaldo;
    }

    boolean puedoEdificarCasa(Calle casilla) {
        boolean esMia = this.esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        if (esMia) {
            int cantidad = casilla.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(cantidad);
        }
        return tengoSaldo;
    }

    boolean puedoEdificarHotel(Calle casilla) {
        boolean esMia = this.esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        if (esMia) {
            int cantidad = casilla.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(cantidad);
        }
        return tengoSaldo;
    }

    boolean puedoHipotecar(Calle casilla) {
        return this.esDeMiPropiedad(casilla);
    }

    boolean puedoPagarHipoteca(Casilla casilla) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    boolean puedoVenderPropiedad(Calle casilla) {
        boolean hipotecada = casilla.estaHipotecada();
        if (!hipotecada && esDeMiPropiedad(casilla)) {
            return true;
        } else {
            return false;
        }
    }

    boolean tengoCartaLibertad() {
        if (cartaLibertad != null) {
            return true;
        } else {
            return false;
        }
    }

    void venderPropiedad(Casilla casilla) {
        Calle aux = (Calle) casilla;
        int precioVenta = aux.venderTitulo();
        this.modificarSaldo(precioVenta);
        this.eliminarDeMisPropiedades(casilla);
    }

    private int cuantasCasasHotelesTengo() {
        int total = 0;
        for (int i = 0; i < propiedades.size(); i++) {
            Calle aux = (Calle) propiedades.get(i).casilla;
            total += aux.getNumCasas() + aux.getNumHoteles();
        }
        return total;
    }

    private void eliminarDeMisPropiedades(Casilla casilla) {
        Calle aux = (Calle) casilla;
        propiedades.remove(aux.getTitulo());
    }

    public boolean esDeMiPropiedad(Calle casilla) { //??
        boolean esMia = false;
        for (int i = 0; i < propiedades.size(); i++) {
            if (propiedades.get(i) == casilla.getTitulo()) {
                esMia = true;
            }
        }
        return esMia;
    }

    private boolean tengoSaldo(int cantidad) {
        if (saldo >= cantidad) {
            return true;
        } else {
            return false;
        }
    }

    protected void pagarImpuesto(int cantidad) {
        modificarSaldo(-cantidad);
    }

    protected Especulador convertirme(int fianza) {
        Especulador especulador = new Especulador(this, fianza);
        // this.setFactorEspeculador(especulador.getFactorEspeculador());
        return especulador;
    }

    //Método toString() devuelve un String con el estado del objeto correspondiente
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n"
                + "Está encarcelado? " + encarcelado + "\n"
                + "Saldo: " + saldo + "\n"
                //+ "Casilla actual: " + casillaActual + "\n"
                + "Tiene carta libertad? " + cartaLibertad + "\n"
                + "Propiedades: " + propiedades.toString() + "\n";
                //+ "Factor especulador: " + FactorEspeculador + "\n";
    }

}
