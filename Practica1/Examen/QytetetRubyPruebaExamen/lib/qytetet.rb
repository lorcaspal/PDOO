# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'sorpresa'
require_relative 'dado'
require_relative 'jugador'
require_relative 'tablero'
require 'singleton'

module ModeloQytetet
  class Qytetet
    include Singleton
    
    attr_reader :cartaActual, :jugadorActual
    
    @@MAX_JUGADORES = 4
    @@MAX_CARTAS = 10
    @@MAX_CASILLAS = 20
    @@PRECIO_LIBERTAD = 200
    @@SALDO_SALIDA = 1000
    
    def initialize()
      @cartaActual = nil
      @mazo = Array.new
      @tablero = nil
      @jugadorActual = nil
      @jugadores = Array.new
      @dado = nil
      @metodo = nil
    end
    
    def aplicar_sorpresa()
      
    end
    
    def cancelar_hipoteca(casilla)
      
    end
    
    def comprar_titulo_propiedad()
      
    end
    
    def edificar_casa(casilla)
      
    end

    def edificar_hotel(casilla)
      
    end
    
    def hipotecar_propiedad(casilla)
      
    end
    
    def inicializar_juego(nombres)
      if(nombres.size() >= 2 && nombres.size() <= @@MAX_JUGADORES && @mazo.size() <= @@MAX_CARTAS)
        inicializar_jugadores(nombres)
        inicializar_tablero()
        inicializar_cartas_sorpresa()
        
      else
        puts "O el numero de jugadores es incorrecto (debe ser de 2 a " + @@MAX_JUGADORES + " jugadores)"
            + "o el numero de cartas es incorrecto (deben ser menor que " + @@MAX_CARTAS + ")"
      end
    end
    
    def intentar_salir_carcel(metodo)
      
    end
    
    def jugar()
      
    end
    
    def obtener_ranking()
      
    end
    
    def propiedades_hipotecadas_jugador(hipotecadas)
      
    end
    
    def siguiente_jugador()
      
    end
    
    def vender_propiedad(casilla)
      
    end
    
    def encarcelar_jugador()
      
    end
    
    def inicializar_cartas_sorpresa()
      @mazo << Sorpresa.new('Errores de calculos. El banco te devuelve $50', 50, TipoSorpresa::PAGARCOBRAR)
      @mazo << Sorpresa.new('Gastos en videojuegos. Pague $50', -50, TipoSorpresa::PAGARCOBRAR)
      @mazo << Sorpresa.new('Avanza hasta la casilla 26', 26, TipoSorpresa::IRACASILLA)
      @mazo << Sorpresa.new('La liga antisupersticion te envia de viaje al numero 13', 13, TipoSorpresa::IRACASILLA)
      @mazo << Sorpresa.new('Oh no! Te han pillado en uno de tus chanchullos. Vas a la carcel', 9, TipoSorpresa::IRACASILLA)
      @mazo << Sorpresa.new('Tus construcciones estan mejorando. Toma $150 del banco', 150, TipoSorpresa::PORCASAHOTEL)
      @mazo << Sorpresa.new('Impuesto por el servicio de luz. Pague $15 al banco', -15, TipoSorpresa::PORCASAHOTEL)
      @mazo << Sorpresa.new('Felicidades! hoy es el dia de tu no cumpleanios, recibes un regalo de todos', 200, TipoSorpresa::PORJUGADOR)
      @mazo << Sorpresa.new('Has perdido una apuesta. Paga $100 de penalizacion', -100, TipoSorpresa::PORJUGADOR)
      @mazo << Sorpresa.new('Fuiste perdonado por el presidente. Con esta carta sales de prision', 0, TipoSorpresa::SALIRCARCEL)
    end
    
    def inicializar_jugadores(nombres)
      for i in 0..nombres.size()-1
        @jugadores << Jugador.new(nombres.at(i))
      end
    end
    
    def inicializar_tablero()
      @tablero = Tablero.new()
    end
    
    def devolver_casilla(numero_casilla)
      @tablero.obtener_casilla_numero(numero_casilla)
    end
    
    def salida_jugadores()
      
    end
   
    #Método toString() devuelve un String con el estado del objeto correspondiente
    def to_s
        "********************* Mazo *********************\n
        #{@mazo.each {|i| puts i.to_s}}
        \n--------------------------------------\n
        ********************* Tablero *********************\n
        #{@tablero}
        \n--------------------------------------\n
        ********************* Jugadores *********************\n 
        #{@jugadores.each {|k| puts k.to_s}}
        \n--------------------------------------\n"
    end
           
    private :encarcelar_jugador, :inicializar_cartas_sorpresa, :inicializar_jugadores, :inicializar_tablero, :salida_jugadores
  end
end
