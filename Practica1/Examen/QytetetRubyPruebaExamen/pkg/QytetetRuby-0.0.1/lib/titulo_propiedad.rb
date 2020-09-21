# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'jugador'
require_relative 'casilla'

module ModeloQytetet
  class TituloPropiedad
    #Modificadores y consultores
    attr_accessor :hipotecada
    attr_reader :nombre, :alquiler_base, :factor_revalorizacion, :hipoteca_base, :precio_edificar
    attr_writer :casilla, :propietario
    
    def initialize(n, aBase, fReva, hBase, pEdi)
      @nombre = n
      @alquiler_base = aBase
      @factor_revalorizacion = fReva
      @hipoteca_base = hBase
      @precio_edificar = pEdi
      @hipotecada = false
      @propietario = nil
      @casilla = nil
    end
    
    #Métodos
    def cobrar_alquiler(coste)
      
    end
    
    def propietario_encarcelado()
      
    end
    
    def tengo_propietario()
      
    end
    
    #Método toString() devuelve un String con el estado del objeto correspondiente
    def to_s
        "\n Nombre: #{@nombre}
        \n Esta hipotecada?: #{@hipotecada}
        \n Precio base alquiler: #{@alquiler_base} 
        \n Factor de revalorizacion: #{@factor_revalorizacion}
        \n Valor base hipoteca: #{@hipoteca_base}
        \n Precio de edificacion: #{@precio_edificar} \n"
    end
  end
end

