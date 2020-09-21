# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'unidad_monetaria'
require 'singleton'

module ModeloQytetet
  class Banca
     include Singleton
     attr_accessor :caja
    def initialize
      inicializar_caja
    end
    
    def inicializar_caja()
      @caja = Array.new
      #5 de tipo moneda
      @caja << Unidad_monetaria.new(1, Tipo_moneda::EURO_ESP, nil)
      @caja << Unidad_monetaria.new(1, Tipo_moneda::EURO_IT, nil)
      @caja << Unidad_monetaria.new(2, Tipo_moneda::EUROS2_ESP, nil)
      @caja << Unidad_monetaria.new(0.50, Tipo_moneda::CTS50_ESP, nil)
      @caja << Unidad_monetaria.new(0.50, Tipo_moneda::CTS50_ESP, nil)
      
      #5 de tipo billete
      @caja << Unidad_monetaria.billete(5, Tipo_billete::EUROS5_ESP)
      @caja << Unidad_monetaria.billete(50, Tipo_billete::EUROS50_D)
      @caja << Unidad_monetaria.billete(50, Tipo_billete::EUROS50_ESP)
      @caja << Unidad_monetaria.billete(100, Tipo_billete::EUROS100_FR)
      @caja << Unidad_monetaria.billete(5, Tipo_billete::EUROS5_ESP)
      
    end
    
    private :inicializar_caja
    
  end
end
