# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class TituloPropiedad
    #Modificadores y consultores
    attr_accessor :hipotecada
    attr_reader :nombre, :alquilerBase, :factorRevalorizacion, :hipotecaBase, :precioEdificar
    
    def initialize(n, aBase, fReva, hBase, pEdi)
      @nombre = n
      @alquilerBase = aBase
      @factorRevalorizacion = fReva
      @hipotecaBase = hBase
      @precioEdificar = pEdi
      @hipotecada = false
    end
    
    #Método toString() devuelve un String con el estado del objeto correspondiente
    def to_s
        "\n Nombre: #{@nombre}
        \n Esta hipotecada?: #{@hipotecada}
        \n Precio base alquiler: #{@alquilerBase} 
        \n Factor de revalorizacion: #{@factorRevalorizacion}
        \n Valor base hipoteca: #{@hipotecaBase}
        \n Precio de edificacion: #{@precioEdificar} \n"
    end
  end
end

