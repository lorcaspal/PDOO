# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'tipo_casilla'
require_relative 'titulo_propiedad'

module ModeloQytetet
  class Casilla
    #Consultores y modificadores
    attr_accessor :num_hoteles, :num_casas, :titulo
    attr_reader :numero_casillas, :coste, :tipo
    
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
      return nil
    end
    
    def calcular_valor_hipoteca
      return nil
    end
    
    def cancelar_hipoteca
      return nil
    end
    
    def cobrar_alquiler
      return nil
    end
    
    def edificar_casa
      return nil
    end
    
    def edificar_hotel
      return nil
    end
    
    def esta_hipotecada
      return nil
    end
    
    def hipotecar
      return nil
    end
    
    def precio_total_comprar
      return nil
    end
    
    def propietario_encarcelado
      return nil
    end
    
    def se_puede_edificar_casa
      return nil
    end
    
    def se_puede_edificar_hotel
      return nil
    end
    
    def soy_edificable
      return nil
    end
    
    def tengo_propiedad
      return nil
    end
    
    def vender_titulo
      return nil
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
