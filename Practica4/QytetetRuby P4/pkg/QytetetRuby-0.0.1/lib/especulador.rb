# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetet
  class Especulador < Jugador
    
    @@FACTOR_ESPECULADOR = 2
    attr_accessor :FACTOR_ESPECULADOR
    def initialize(jugador , fianza)
      super(jugador.nombre,jugador.encarcelado,jugador.saldo,jugador.casilla_actual,jugador.carta_libertad,jugador.propiedades)
      @fianza = fianza
    end
    
    def pagar_impuestos(cantidad)
      super.modificar_saldo(-cantidad/2)
    end
    
    def ir_a_carcel(casilla)
      no_carcel = pagar_fianza(@fianza)
      if(!no_carcel)
        super.ir_a_carcel(casilla)
      else
        super.modificar_saldo(-@fianza)
      end
     end
    
    def get_factor_especulacion()
      @@FACTOR_ESPECULADOR
    end
    
    
    def convertirme(fianza)
      return self
    end
    
    def pagar_fianza(cantidad)
      if(super.saldo < cantidad)
        resultado = false
      else
        resultado = true
      end
      resultado
    end
    
    def to_s
      "************************************************\n
       #{super.to_s} \n
       Fianza: #{@fianza}"
       puts "hola estoy en especulador"
    end
    
    private :pagar_fianza
    
  end
end
