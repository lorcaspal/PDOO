# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'qytetet.rb'
require_relative 'casilla.rb'
require_relative 'dado.rb'
require_relative 'jugador.rb'
require_relative 'metodo_salir_carcel.rb'
require_relative 'sorpresa.rb'
require_relative 'tablero.rb'
require_relative 'tipo_casilla.rb'
require_relative 'tipo_sorpresa.rb'
require_relative 'titulo_propiedad.rb'
require_relative 'especulador.rb'
#require_relative 'ModeloQytetet'

require_relative 'vista_textual_qytetet'

include ModeloQytetet

module InterfazTextualQytetet
  class ControladorQytetet  

    def initialize
      @vista = VistaTextualQytetet.new
      @jugador = nil
      @casilla = nil
      @juego = Qytetet.instance
    end
    
    def desarrollo_juego()
      tipo_casilla=0
      bancarrota = false
      
      while(!bancarrota)
          puts "El jugador actual es #{@jugador.nombre} con saldo #{@jugador.saldo} y se encuentra en la casilla #{@casilla}"
          
        if @jugador.encarcelado
            puts "estas en la carcel tienes dos metodos : tirar el dado para salir (escribe dado) o pagar libertad ( escribe libertad )"
          # metodo de lectura
          opcion = @vista.menu_salir_carcel
          salir=false
          if opcion == 0            
              salir = @juego.intentar_salir_carcel(MetodoSalirCarcel::TIRANDODADO)
          elsif opcion == 1
              salir = @juego.intentar_salir_carcel(MetodoSalirCarcel::PAGANDOLIBERTAD)
          end
          if !salir
              puts "ohhhh no has salido , pierdes turno"
              @jugador = @juego.siguiente_jugador
              #@jugador = @juego.jugador_actual
          else
              puts "felicidades sales de la carcer ya puedes seguir robando"
          end
        end
        if !@jugador.encarcelado
            salir = false
            puts "tirandooo el dadooooo"
            no_tiene_propietario = @juego.jugar()
            @casilla = @jugador.casilla_actual
            puts "El jugador actual es #{@jugador.nombre} con saldo #{@jugador.saldo} y se encuentra en la casilla #{@casilla}"
            if @casilla.tipo == TipoCasilla::SORPRESA                            
              aplicar_sorpresa = @juego.aplicar_sorpresa
              puts @juego.carta_actual
              
              if @jugador.saldo > 0
                bancarrota = false
              else 
                bancarrota = true
              end
            elsif @casilla.tipo == TipoCasilla::CARCEL
                puts "Estas en la carcel bien por el juez o porque te ha tocado una carta sorpresa de ir a carcel pierdes turno"
                @juego.siguiente_jugador
                salir=true
            else
              if !no_tiene_propietario
                recibido = @vista.elegir_quiero_comprar
                
                if (recibido == 0)
                  comprada = @juego.comprar_titulo_propiedad
                  if comprada
                    puts "Has conseguido comprarla!! Tu saldo es #{@jugador.saldo}"
                  else
                    puts "Tu saldo es insuficiente, no consigues comprarla"
                  end 
                  
                elsif (recibido == 1)
                  puts "Has decidido no comprar la propiedad"
                end
                
              else
                puts "Ohhh te toca pagar el alquiler y te queda de saldo #{@jugador.saldo}"
              end
            
            end
            
            while(!salir)
              recibido = @vista.menu_gestion_inmobiliaria
              case recibido
              when 0
                @juego.siguiente_jugador
                #@jugador = @juego.jugador_actual
                salir=true
              when 1
                if @jugador.tengo_propiedades
                 mias = Array.new
                 mias = @juego.jugador_actual.propiedades
                 captura = @vista.menu_elegir_propiedad(mias)
                  puts mias.at(Integer(captura)).casilla
                  puedo_construir = @juego.edificar_casa(mias.at(Integer(captura)).casilla)
                  if puedo_construir
                    puts "felicidades has podido construir"
                    puts mias.at(Integer(captura)).casilla
                  else
                    puts "ohhhh no has podido construir"
                  end
                end
                when 2
                  if @jugador.tengo_propiedades
                    mias = Array.new
                    mias= @juego.jugador_actual.propiedades
                    captura = @vista.menu_elegir_propiedad(mias)


                    puedo_construir = @juego.edificar_hotel(mias.at(Integer(captura)).casilla)
                    if puedo_construir
                      puts "felicidades has podido construir"
                      puts mias.at(Integer(captura)).casilla
                    else
                      puts "ohhhh no has podido construir"
                    end
                  end
                when 3
                  if @jugador.tengo_propiedades()
                    mias= @juego.jugador_actual.propiedades
                    captura = @vista.menu_elegir_propiedad(mias)

                    vendida = @juego.vender_propiedad(mias.at(Integer(captura)).casilla)
                    if vendida
                      puts "felicidades la has vendido"
                      
                    else
                      puts "oh no has podido vender"
                    end
                  else
                    puts "No tienes propiedades para vender"
                  end
                  @juego.siguiente_jugador()
                  
              when 4
                if @jugador.tengo_propiedades()
                  mias = Array.new
                  mias = @juego.jugador_actual.propiedades
                  captura = @vista.menu_elegir_propiedad(mias)

                  hipotecada = @juego.hipotecar_propiedad(mias.at(Integer(captura)).casilla)
                  if hipotecada
                    puts "has conseguido hipotecarla"
                    puts mias.at(Integer(captura)).casilla
                  else
                    puts "no has conseguido hipotecarla"
                  end
                else
                  puts "No tienes propiedades para hipotecar"  
                end
                
               when 5
                if @jugador.tengo_propiedades()
                  mias = Array.new
                  mias= @juego.jugador_actual.propiedades
                  captura = @vista.menu_elegir_propiedad(mias)

                  cancelada = @juego.cancelar_hipoteca(mias.at(Integer(captura)).casilla)
                  if cancelada
                    puts "has conseguido cancelar la hipoteca"
                    puts mias.at(Integer(captura)).casilla
                  else
                    puts "no has conseguido cancelar la hipoteca"
                  end
                else
                  puts "No tienes propiedades para cancelar" 
                end                        
              end
            end
          end
          @jugador = @juego.jugador_actual
        end
      
      #Fin del while
      if(bancarrota)
        puts "Se ha acabado el juego"
      end
    end
    
    def inicializacion_juego()
      nombres = Array.new
      nombres = @vista.obtener_nombre_jugadores
      @juego.inicializar_juego(nombres)
      @jugador = @juego.jugador_actual
      @casilla = @jugador.casilla_actual
      desarrollo_juego()
      
    end
       
    def elegir_propiedad(propiedades)
      @vista.mostrar("estas son tus propiedades")
      puts propiedades.size()
      propiedades.each do p
        puts p
        
      end
    end
    
    
    
    private :elegir_propiedad
    
    public
    def self.main
      prueba = ControladorQytetet.new
      prueba.inicializacion_juego
     
    end
    
  end
  ControladorQytetet.main
end
