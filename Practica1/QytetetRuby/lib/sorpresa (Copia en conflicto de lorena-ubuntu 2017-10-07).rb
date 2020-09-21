# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
# encoding: utf-8

# author: Lorena

module ModuloQytetet
  class Sorpresa
    
    #Consultor (get)
    attr_reader :texto , :valor , :tipo
    
    #Constructor
    def initialize(tex, val, tip)
      @texto = tex
      @valor = val
      @tipo = tip
    end
 
    #Método toString devuelve un String con el estado del objeto correspondiente
    def to_s
      "Texto: #{@texto} \n Valor: #{@valor} \n Tipo: #{@tipo}"
    end 
  end
end
