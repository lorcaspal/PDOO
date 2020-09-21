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
    
      attr_reader :carta_actual, :jugador_actual, :jugadores, :SALDO_SALIDA
    
    @@MAX_JUGADORES = 4
    @@MAX_CARTAS = 10
    @@MAX_CASILLAS = 20
    @@PRECIO_LIBERTAD = 200
    @@SALDO_SALIDA = 1000
    
    def initialize()
      @carta_actual = nil
      @mazo = Array.new
      @tablero = Tablero.new
      @jugador_actual = nil
      @jugadores = Array.new
      @dado = Dado.instance
    end
    
    def get_saldo()
      @@SALDO_SALIDA
    end
    
    
    def aplicar_sorpresa()
      if(@carta_actual.tipo == TipoSorpresa::PAGARCOBRAR)
        @jugador_actual.modificar_saldo(@carta_actual.valor)
        
      elsif @carta_actual.tipo == TipoSorpresa::IRACASILLA 
        es_carcel = @tablero.es_casilla_carcel(@carta_actual.valor)
        
        if(es_carcel)
            encarcelar_jugador
        else
          nueva_casilla = @tablero.obtener_casilla_numero(@carta_actual.valor)
          tiene_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)
       end
       
      elsif @carta_actual.tipo == TipoSorpresa::PORCASAHOTEL
          @jugador_actual.pagar_cobrar_por_casa_y_hotel(@carta_actual.valor)
      elsif @carta_actual.tipo == TipoSorpresa::PORJUGADOR
        @jugadores.each do |jugador|
          if jugador != @jugador_actual
            jugador.modificar_saldo(@carta_actual.valor)
            @jugador_actual.modificar_saldo(-@carta_actual.valor)
          end
        end
      elsif @carta_actual.tipo == TipoSorpresa::CONVERTIRME
#        puts @jugador_actual.get_factor_especulacion()
        espe = @jugador_actual.convertirme(@carta_actual.valor)
#        puts espe.get_factor_especulacion()
#        puts @jugadores.size()
        for i in 0..@jugadores.size()-1 do
          if (@jugadores.at(i).nombre == @jugador_actual.nombre)
           @jugadores.insert(i, espe)
          end
        end
 
      end
      
      if @carta_actual.tipo == TipoSorpresa::SALIRCARCEL
        @jugador_actual.carta_libertad(@carta_actual)
      else
        @mazo << @carta_actual
      end
      tiene_propietario
    end
    
    def cancelar_hipoteca(casilla)
      if casilla.esta_hipotecada
        casilla.cancelar_hipoteca
        casilla.titulo.hipotecada = false
        return true
      else
        return false
      end
      
    end
    
    def comprar_titulo_propiedad()
      puedo_comprar = @jugador_actual.comprar_titulo()
      puedo_comprar
    end
    
    def edificar_casa(casilla)
      if (casilla.soy_edificable)     
        se_puede_edificar = casilla.se_puede_edificar_casa(@jugador_actual.get_factor_especulacion())
            if(se_puede_edificar)
                puedo_edificar = @jugador_actual.puedo_edificar_casa(casilla)                
            
            end
            if(puedo_edificar)
                coste_edificar_casa = casilla.edificar_casa()
                @jugador_actual.modificar_saldo(-coste_edificar_casa)
                puedo_edificar_casa = puedo_edificar
            end
      end
      puedo_edificar_casa
    end

    def edificar_hotel(casilla)
      if (casilla.soy_edificable)     
            se_puede_edificar = casilla.se_puede_edificar_hotel(@jugador_actual.get_factor_especulacion)

            if(se_puede_edificar)
                puedo_edificar = @jugador_actual.puedo_edificar_hotel(casilla)                
            
            end
            if(puedo_edificar)
                coste_edificar_hotel = casilla.edificar_hotel()
                @jugador_actual.modificar_saldo(-coste_edificar_hotel)
                puedo_edificar_hotel = puedo_edificar
            end
      end
      puedo_edificar_hotel
    end
    
    def hipotecar_propiedad(casilla)
      if casilla.soy_edificable()
        se_puede_hipotecar = !casilla.esta_hipotecada
        
        if(se_puede_hipotecar)
          puedo_hipotecar = @jugador_actual.puedo_hipotecar(casilla)
          
          if(puedo_hipotecar)
            cantidadRecibida = casilla.hipotecar()
            @jugador_actual.modificar_saldo(cantidadRecibida)
            puedo_hipotecar_propiedad = puedo_hipotecar
          end
        end
      end
      puedo_hipotecar_propiedad
    end
    
    def inicializar_juego(nombres)
      if(nombres.size() >= 2 && nombres.size() <= @@MAX_JUGADORES && @mazo.size() <= @@MAX_CARTAS)      
        inicializar_jugadores(nombres)
        inicializar_cartas_sorpresa()
        inicializar_tablero()
        salida_jugadores()
      else
        puts "O el numero de jugadores es incorrecto (debe ser de 2 a " + @@MAX_JUGADORES + " jugadores)"
            + "o el numero de cartas es incorrecto (deben ser menor que " + @@MAX_CARTAS + ")"
      end
    end
    
    def intentar_salir_carcel(metodo)
      dado = Dado.instance
      tengo_saldo = false
      if metodo == MetodoSalirCarcel::TIRANDODADO
        valor_dado = dado.tirar()
        libre = valor_dado > 5
        
      elsif metodo == MetodoSalirCarcel::PAGANDOLIBERTAD
        tengo_saldo = @jugador_actual.pagar_libertad(-@@PRECIO_LIBERTAD)
        libre = tengo_saldo
      end
      
      if libre
        @jugador_actual.encarcelado = !libre
      end      
      libre
    end
    
    def jugar()
      dado = Dado.instance
      valor_dado = dado.tirar()
      casilla_posicion = @jugador_actual.casilla_actual
      nueva_casilla = @tablero.obtener_nueva_casilla(casilla_posicion, valor_dado)
      tiene_propietario = @jugador_actual.actualizar_posicion(nueva_casilla)
      
      if !nueva_casilla.soy_edificable
        if nueva_casilla.tipo == TipoCasilla::JUEZ
          encarcelar_jugador()
          
        elsif nueva_casilla.tipo == TipoCasilla::SORPRESA
          @carta_actual = @mazo.at(0)
          @mazo.delete(@carta_actual)
        end
      end
      tiene_propietario
    end
    
    def obtener_ranking()
      
    end
    
    def propiedades_hipotecadas(hipotecadas)
      casilla_hipotecada = Array.new
      t_propiedad = Array.new 
      t_propiedad = @jugador_actual.obtener_propiedades_hipotecadas(hipotecadas)
      
      for i in 0..t_propiedad.size()-1
        if (@tablero.obtener_casilla_numero(i).titulo == t_propiedad.at(i))
          casilla_hipotecada << @tablero.obtener_casilla_numero(i)
        end
      end
      
      casilla_hipotecada
    end
    
    def siguiente_jugador()
      posicionArray = @jugadores.index(@jugador_actual)
      #posicionArray = (posicionArray + 1)%@jugadores.size()
      if(posicionArray == @jugadores.size()-1)
       @jugador_actual = @jugadores.at(0)
      
      else
        @jugador_actual = @jugadores.at(posicionArray+1)
      end
      @jugador_actual
    end
    
    def vender_propiedad(casilla)
      if casilla.soy_edificable
        puedo_vender = @jugador_actual.puedo_vender_propiedad(casilla)
        
        if puedo_vender
          @jugador_actual.vender_propiedad(casilla)
        end
      end
      puedo_vender
    end
    
    def encarcelar_jugador()
      if !@jugador_actual.tengo_carta_libertad()
        casilla_carcel = @tablero.carcel
        @jugador_actual.ir_a_carcel(casilla_carcel)
        
      else
        carta = @jugador_actual.devolver_carta_libertad()
        @mazo << carta
      end
    end
    
    def inicializar_cartas_sorpresa()
      @mazo << Sorpresa.new("Enhorabuena! Te conviertes en especulador", 3000, TipoSorpresa::CONVERTIRME)
      @mazo << Sorpresa.new("Enhorabuena! Te conviertes en especulador", 5000, TipoSorpresa::CONVERTIRME)
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
       #@jugadores << Jugador.new(nombres.at(i))
       @jugadores << Jugador.nuevo_jugador(nombres.at(i))
      end
    end
    
    def inicializar_tablero()
      @tablero = Tablero.new()
    end
    
    def salida_jugadores()
      pos = rand(0..@jugadores.length()-1)
      @jugador_actual = @jugadores.at(pos)
      for i in 0..@jugadores.size()-1
        casilla = @tablero.obtener_casilla_numero(0)
        @jugadores.at(i).casilla_actual = casilla
       # puts @jugadores.at(i).casilla_actual
        @jugadores.at(i).modificar_saldo(7500)
      end
    end
    
    def casillas_propiedad_jugador
      mias = Array.new
      for i in 0..19
        casilla = @tablero.obtener_casilla_numero(i)
        if @jugador_actual.es_de_mi_propiedad(casilla)
          mias << casilla
        end
      end
      mias
    end
    
#    #Método toString() devuelve un String con el estado del objeto correspondiente
##    def to_s
##        "********************* Mazo *********************\n
#        #{@mazo.each {|i| puts i.to_s}}
#        \n--------------------------------------\n
#        ********************* Tablero *********************\n
#        #{@tablero}
#        \n--------------------------------------\n
#        ********************* Jugadores *********************\n 
#        #{@jugadores.each {|k| puts k.to_s}}
#        \n--------------------------------------\n#"
##    end
           
    private :inicializar_cartas_sorpresa, :inicializar_jugadores, :inicializar_tablero, :salida_jugadores, :encarcelar_jugador
  end
end
