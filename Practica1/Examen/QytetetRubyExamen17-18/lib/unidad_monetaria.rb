# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'tipo_moneda'
require_relative 'tipo_billete'

module ModeloQytetet
  class Unidad_monetaria
    #consultor 
    attr_reader :valor
    attr_accessor :tipo_m, :tipo_b
    
    #Constructor para los que son de tipo moneda
    def initialize(v, tm, tb)
       @valor = v 
       @tipo_m = tm
       @tipo_b = tb
    end
    
    #Constructor para los que son de tipo billete
    def self.billete(v,tb)
      Unidad_monetaria.new(v,0,tb)
    end
    
#Método toString() devuelve un String con el estado del objeto correspondiente
    def to_s
        "********************* Unidad Monetaria *********************\n
        Valor de la moneda: #{@valor}
        \n--------------------------------------\n
        Tipo de la moneda: #{@tipo_m}
        \n--------------------------------------\n
        Tipo del billete: #{@tipo_b}
        \n--------------------------------------\n"
    end
    
    
  end
end
