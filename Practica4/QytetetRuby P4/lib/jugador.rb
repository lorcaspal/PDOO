# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'sorpresa'
require_relative 'titulo_propiedad'
require_relative 'casilla'
require_relative 'calle'



module ModeloQytetet
  class Jugador
    @@FACTOR_ESPECULADOR = 1
    
    #Consultores y modificadores
    attr_accessor :encarcelado , :carta_libertad, :casilla_actual , :propiedades ,:carta_libertad , :FACTOR_ESPECULADOR
    attr_reader :nombre, :saldo
    
    #Constructor
#    def initialize(nom)
#      @nombre = nom
#      @encarcelado = false
#      @saldo = 0
#      @propiedades = Array.new
#      @casilla_actual = nil
#      @carta_libertad = nil
#    end
 #  @propiedades = Array.new
     def initialize(nom,encarcelado,saldo,casilla_a,carta_l,propiedades)
      @nombre = nom
      @encarcelado = encarcelado
      @saldo = saldo
      @casilla_actual = casilla_a
      @carta_libertad = carta_l
      @propiedades = propiedades
      
    end
    
    def self.nuevo_jugador(nom)
      Jugador.new(nom,false,0,nil,nil,Array.new)
    end
    
    def self.copia_jugador(jugador)
#      puts jugador
      Jugador.new(jugador.nombre,jugador.encarcelado,jugador.saldo,jugador.casilla_actual,jugador.carta_libertad,jugador.propiedades)
    end
    
    
    
    #Métodos
 
    
    def tengo_propiedades()
      if @propiedades.empty?
        return false
      else
        return true
      end
    end
    
    def actualizar_posicion(casilla) 
      if casilla.numero_casilla < @casilla_actual.numero_casilla
        modificar_saldo(1000)
      end
      
      tiene_propietario = false
      @casilla_actual = casilla
      
      if casilla.soy_edificable()
        if casilla.titulo.tengo_propietario()
          @encarcelado = casilla.propietario_encarcelado()
          if !@encarcelado
            coste_alquiler = casilla.cobrar_alquiler()
            modificar_saldo(-coste_alquiler)
          end
          tiene_propietario = true
        end   
      elsif casilla.tipo == TipoCasilla::IMPUESTO
        coste = casilla.coste
        modificar_saldo(-coste)
      
      end 
      tiene_propietario
    end
    
    def comprar_titulo()
      puedo_comprar = false
      if @casilla_actual.soy_edificable()
        tengo_propietario = @casilla_actual.tengo_propiedad()
        if !tengo_propietario
          coste_compra = @casilla_actual.coste
          
          if coste_compra <= @saldo
            titulo = @casilla_actual.asignar_propietario(self)
            @propiedades << titulo
            modificar_saldo(-coste_compra)
            puedo_comprar = true
          end
        end
      end
      puedo_comprar
    end
    
    def devolver_carta_libertad()
      devolver = @carta_libertad
      if(tengo_carta_libertad)
        @carta_libertad = nil
        @encarcleado = false
      end
      devolver
    end
    
    def ir_a_carcel(casilla)
      @casilla_actual = casilla 
      @encarcelado = true
    end
    
    def modificar_saldo(cantidad)
      @saldo += cantidad
    end
    
    def obtener_capital()
      @propiedades.each do |i|
        if !@propiedades.at(i).hipotecada
          prop += @propiedades.at(i).casilla.coste + cuantas_casas_hoteles_tengo + @propiedades.at(i).precio_edificar
        else
          prop += @propiedades.at(i).casilla.coste + cuantas_casas_hoteles_tengo + @propiedades.at(i).precio_edificar 
          - @propiedades.at(i).hipoteca_base
 
        end
      end
      capital = @saldo + prop
      capital
    end
    
    def obtener_propiedades_hipotecadas(esta_hipotecada)
      estan_hipotecados = Array.new
      for i in 0..@propiedades.size()-1
        if @propiedades.at(i).hipotecada == esta_hipotecada
          estan_hipotecados << @propiedades.at(i)
        end
      end
      estan_hipotecados
    end
    
    def pagar_cobrar_por_casa_y_hotel(cantidad)
      numero_total = cuantas_casas_hoteles_tengo()
      modificar_saldo(numero_total * cantidad)
    end
    
    def pagar_libertad(cantidad)
      tengo_saldo = tengo_saldo(cantidad)
      
      if tengo_saldo
        modificar_saldo(cantidad)
      end
      
      tengo_saldo
    end

    def puedo_edificar_casa(casilla)
      esMia = es_de_mi_propiedad(casilla)
      if(esMia)
        cantidad = casilla.get_precio
        tengo_saldo = tengo_saldo(cantidad)
      end
      return tengo_saldo
    end
    
    def puedo_edificar_hotel(casilla)
      esMia = es_de_mi_propiedad(casilla)
      if(esMia)
        cantidad = casilla.titulo.precio_edificar
        tengo_saldo = tengo_saldo(cantidad)
      end
      return tengo_saldo
    end
    
    def puedo_hipotecar(casilla)
      return es_de_mi_propiedad(casilla)
    end
    
    def puedo_pagar_hipoteca(casilla)
      
    end
    
    def puedo_vender_propiedad(casilla)
      esta_hipotecada = casilla.esta_hipotecada
      if(!esta_hipotecada && es_de_mi_propiedad(casilla))
        true
      else 
        false
      end
    end
    
    def tengo_carta_libertad()
      if(@cartaLibertad != nil)
        true
      else
        false
      end
    end
    
    def vender_propiedad(casilla)
      precio_venta = casilla.vender_titulo()
      modificar_saldo(precio_venta)
      eliminar_de_mis_propiedades(casilla)
    end
    
    def cuantas_casas_hoteles_tengo()
      @propiedades.each do |i|
        total += @propiedades.at(i).casilla.num_casas + @propiedades.at(i).casilla.num_hoteles
      end 
      total
    end
    
    def eliminar_de_mis_propiedades(casilla)
      @propiedades.delete(casilla.titulo)
    end
    
    def es_de_mi_propiedad(casilla)
      esmia = false
      for i in 0..@propiedades.size()-1
        if @propiedades.at(i) == casilla.titulo
          esmia=true         
        end
      end
      esmia
    end
    
    def tengo_saldo(cantidad)
      if @saldo >= cantidad
        true
      else
        false
      end
    end
    
    def pagar_impuestos(cantidad)
      modificar_saldo(-cantidad)
    end
    
    def convertirme(fianza)      
      espe = Especulador.new(self,fianza)
      espe
    end
    
    def get_factor_especulacion()
      @@FACTOR_ESPECULADOR
    end
    
    #Método toString() devuelve un String con el estado del objeto correspondiente
    def to_s
      "************************************************\n
       Nombre:  #{@nombre} \n
       Esta encarcelado? #{@encarcelado} \n
       Saldo:  #{@saldo} \n
       Tiene carta libertad? #{@carta_libertad} \n
       Propiedades: #{@propiedades} \n"
       #Factor especulación: #{@factor_especulador} \n"
       
    end
    
    private :cuantas_casas_hoteles_tengo, :eliminar_de_mis_propiedades, :tengo_saldo
    
  end
end
