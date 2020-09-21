# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'sorpresa'
require_relative 'titulo_propiedad'
require_relative 'casilla'

module ModeloQytetet
  class Jugador
    
    #Consultores y modificadores
    attr_accessor :encarcelado
    attr_reader :nombre, :saldo
    attr_writer :carta_libertad, :casilla_actual
    
    #Constructor
    def initialize(nom)
      @nombre = nom
      @encarcelado = false
      @saldo = 7500
      @casilla_actual = nil
      @propiedades = Array.new
      @carta_libertad = nil
    end
    
    #Métodos
    def tengo_propiedades()
      
    end
    
    def actualizar_posicion(casilla)
      
    end
    
    def comprar_titulo()
      
    end
    
    def devolver_carta_libertad()
      
    end
    
    def ir_a_carcel(casilla)
      
    end
    
    def modificar_saldo(cantidad)
      
    end
    
    def obtener_capital()
      
    end
    
    def obtener_propiedades_hipotecadas(hipotecada)
      
    end
    
    def pagar_cobrar_por_casa_y_hotel(cantidad)
      
    end
    
    def pagar_libertad(cantidad)
      
    end

    def puedo_edificar_casa(casilla)
      
    end
    
    def puedo_edificar_hotel(casilla)
      
    end
    
    def puedo_hipotecar(casilla)
      
    end
    
    def puedo_pagar_hipoteca(casilla)
      
    end
    
    def puedo_vender_propiedad(casilla)
      
    end
    
    def tengo_carta_libertad()
      
    end
    
    def vender_propiedad(casilla)
      
    end
    
    def cuantas_casas_hoteles_tengo()
      
    end
    
    def eliminar_de_mis_propiedades(casilla)
      
    end
    
    def es_de_mi_propiedad(casilla)
      
    end
    
    def tengo_saldo(cantidad)
      
    end
    
    #Método toString() devuelve un String con el estado del objeto correspondiente
    def to_s
      "************************************************\n
       Nombre:  #{@nombre} \n
       Esta encarcelado? #{@encarcelado} \n
       Saldo:  #{@saldo} \n
       Tiene carta libertad? #{@carta_libertad} \n
       Propiedades: #{@propiedades} \n"
    end
    
    private :cuantas_casas_hoteles_tengo, :eliminar_de_mis_propiedades, :es_de_mi_propiedad, :tengo_saldo
    
  end
end
