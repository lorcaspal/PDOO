# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'tipo_casilla'
require_relative 'titulo_propiedad'

module ModeloQytetet
  class Casilla
    #Consultores y modificadores
    attr_accessor :num_hoteles, :num_casas, :titulo
    attr_reader :numero_casilla, :coste, :tipo
    
    #Constructores
    #Para los que son de tipo calle
    def initialize(numero_casilla, coste, tipo, titulo)
      @numero_casilla = numero_casilla
      @coste = coste
      @tipo = tipo
      @titulo = titulo
      @num_hoteles = 0
      @num_casas = 0
    end
    #Para los que NO son de tipo calle
    def self.no_calle(numero_casilla, tipo)
      Casilla.new(numero_casilla, 0, tipo, 0)
    end
    
    def get_coste_hipoteca
      return @titulo.hipotecaBase
    end
    
    def get_precio_edificar
      return @titulo.precioEdificar
    end
    
    #Métodos
    def asignar_propietario(jugador)
      @titulo.propietario = jugador
      @titulo
    end
    
    def calcular_valor_hipoteca
      hipoteca_base = @titulo.hipoteca_base
      cantidad_recibida = hipoteca_base + (@num_casas*0.5*hipoteca_base + @num_hoteles*hipoteca_base).round
      cantidad_recibida
    end
    
    def cancelar_hipoteca
      coste_alquiler_base = @titulo.alquiler_base
      cantidad_recibida = coste_alquiler_base + (@num_casas * 0.5 + @num_hoteles * 2).round
      cantidad_recibida
    end
    
    def cobrar_alquiler
      coste_alquiler_base = @titulo.alquiler_base
      coste_alquiler = coste_alquiler_base + (@num_casas*0.5 + @num_hoteles*2).round
      @titulo.cobrar_alquiler(coste_alquiler)
      
      coste_alquiler
    end
    
    def edificar_casa
      @num_casas = @num_casas + 1
      coste_edificar_casa = @titulo.precio_edificar
      coste_edificar_casa
    end
    
    def edificar_hotel
      @num_casas = 0
      @num_hoteles = @num_hoteles + 1
      coste_edificar_hotel = @titulo.precio_edificar
      coste_edificar_hotel

    end
    
    def esta_hipotecada
      @titulo.hipotecada
    end
    
    def hipotecar
      @titulo.hipotecada = true
      calcular_valor_hipoteca()
    end
    
    def precio_total_comprar
      return nil
    end
    
    def propietario_encarcelado
      @titulo.propietario_encarcelado()
    end
    
    def se_puede_edificar_casa
      if @num_casas < 4
        return true
      else
        return false
      end
    end
    
    def se_puede_edificar_hotel
      if @num_casas == 4 && @num_hoteles < 4
        return true
      else
        return false
      end
      
    end
    
    def soy_edificable
      if(@tipo == TipoCasilla::CALLE)
        true
      else
        false
      end
    end
    
    def tengo_propiedad
      @titulo.tengo_propietario
    end
    
    def get_precio
      @titulo.precio_edificar
    end
    
    def vender_titulo
      precio_compra = @coste + (@num_casas + @num_hoteles * @titulo.precio_edificar)
      precio_venta = (precio_compra + @titulo.factor_revalorizacion * precio_compra).round 
    
      @titulo.propietario = nil
      @num_casas = 0
      @num_hoteles = 0
      
      precio_venta
    end
    
    def asignar_titulo_propiedad
      return nil
    end
    #Método toString devuelve un String con el estado del objeto correspondiente
    def to_s
      if(@coste !=0)
        "\n Numero de la casilla: #{@numero_casilla} 
        \n Coste: #{@coste} 
        \n Numero de hoteles: #{@num_hoteles}
        \n Numero de casas: #{@num_casas}
        \n Tipo de la casilla: #{@tipo}
        \n Titulo de propiedad: #{@titulo}
        \n-----------------------------------------\n"
      else
      "\n Numero de la casilla: #{@numero_casilla}
      \n Tipo de la casilla: #{@tipo}
      \n-----------------------------------------\n"
      end
    end 
    private :titulo= 
    private :asignar_titulo_propiedad
  end
end
