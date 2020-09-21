# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'tipo_casilla'
require_relative 'titulo_propiedad'

module ModeloQytetet
  class Casilla
    #Consultores y modificadores
    
    attr_reader :numero_casilla, :tipo
    
    #Constructores
    #Para los que son de tipo calle
    def initialize(numero_casilla,tipo)
      @numero_casilla = numero_casilla
      @tipo = tipo
     
    end
    #Para los que NO son de tipo calle
    def self.no_calle(numero_casilla, tipo)
      Casilla.new(numero_casilla,tipo)
    end
    
    def get_coste_hipoteca
      return @titulo.hipotecaBase
    end
    
    def get_precio_edificar
      return @titulo.precioEdificar
    end
    
    def soy_edificable
      if(@tipo == TipoCasilla::CALLE)
        true
      else
        false
      end
    end
    #Métodos
    
    
   
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
    
  end
end
