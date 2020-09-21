# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'tipo_casilla'
require_relative 'titulo_propiedad'

module ModeloQytetet
  class Casilla
    #Consultores y modificadores
    attr_accessor :numHoteles, :numCasas, :titulo
    attr_reader :numeroCasillas, :coste, :tipo
    
    #Constructores
    #Para los que son de tipo calle
    def initialize(numeroCasilla, coste, tipo, titulo)
      @numeroCasilla = numeroCasilla
      @coste = coste
      @tipo = tipo
      @titulo = titulo
      @numHoteles = 0
      @numCasas = 0
    end
    #Para los que NO son de tipo calle
    def self.noCalle(numeroCasilla, tipo)
      Casilla.new(numeroCasilla, 0, tipo, 0)
    end
    
    #Método toString devuelve un String con el estado del objeto correspondiente
    def to_s
      if(@coste !=0)
        "\n Numero de la casilla: #{@numeroCasilla} 
        \n Coste: #{@coste} 
        \n Numero de hoteles: #{@numHoteles}
        \n Numero de casas: #{@numCasas}
        \n Tipo de la casilla: #{@tipo}
        \n Titulo de propiedad: #{@titulo}
        \n-----------------------------------------\n"
      else
      "\n Numero de la casilla: #{@numeroCasilla}
      \n Tipo de la casilla: #{@tipo}
      \n-----------------------------------------\n"
      end
    end 
    private :titulo=
  end
end
