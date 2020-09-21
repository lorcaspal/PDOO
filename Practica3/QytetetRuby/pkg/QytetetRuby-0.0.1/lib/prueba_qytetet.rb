## To change this license header, choose License Headers in Project Properties.
## To change this template file, choose Tools | Templates
## and open the template in the editor.
## encoding: utf-8
#
#require_relative 'sorpresa'
#require_relative 'tipo_sorpresa'
#require_relative 'tablero'
#require_relative 'casilla'
#require_relative 'tipo_casilla'
#require_relative 'titulo_propiedad'
#require_relative 'qytetet'
#require_relative 'jugador'
#
#module ModeloQytetet
#  class PruebaQytetet
##    #Creación de atributo de clase de tipo array
##    @@mazo = Array.new
##    @@tablero = Tablero.new
#    
#    #Metodo de clase inicializarSorpresas
##    def self.inicializar_sorpresas
#      #Construccion de cartas
##      
##      @@mazo << Sorpresa.new('Errores de calculos. El banco te devuelve $50', 50, TipoSorpresa::PAGARCOBRAR)
##      @@mazo << Sorpresa.new('Gastos en videojuegos. Pague $50', -50, TipoSorpresa::PAGARCOBRAR)
##      @@mazo << Sorpresa.new('Avanza hasta la casilla 26', 26, TipoSorpresa::IRACASILLA)
##      @@mazo << Sorpresa.new('La liga antisupersticion te envia de viaje al numero 13', 13, TipoSorpresa::IRACASILLA)
##      @@mazo << Sorpresa.new('Oh no! Te han pillado en uno de tus chanchullos. Vas a la carcel', 9, TipoSorpresa::IRACASILLA)
##      @@mazo << Sorpresa.new('Tus construcciones estan mejorando. Toma $150 del banco', 150, TipoSorpresa::PORCASAHOTEL)
##      @@mazo << Sorpresa.new('Impuesto por el servicio de luz. Pague $15 al banco', -15, TipoSorpresa::PORCASAHOTEL)
##      @@mazo << Sorpresa.new('Felicidades! hoy es el dia de tu no cumpleanios, recibes un regalo de todos', 200, TipoSorpresa::PORJUGADOR)
##      @@mazo << Sorpresa.new('Has perdido una apuesta. Paga $100 de penalizacion', -100, TipoSorpresa::PORJUGADOR)
##      @@mazo << Sorpresa.new('Fuiste perdonado por el presidente. Con esta carta sales de prision', 0, TipoSorpresa::SALIRCARCEL)                  
##    end
#    
#    #Método 1: Sorpresas que tienen un valor mayor que 0
##    def self.sorMayorCero
##      arrayValor = Array.new
##      #Bucle que va desde i hasta el tamaño del mazo
##      @@mazo.each { |sorpresa| arrayValor << sorpresa if sorpresa.valor > 0}
##      return arrayValor 
##    end
##    
##    #Método 2: Sorpresas de tipoSorpresa IRACASILLA.
##    def self.sorTipoCasilla
##      arrayValor = Array.new
##      @@mazo.each { |sorpresa| arrayValor << sorpresa if sorpresa.tipo == TipoSorpresa::IRACASILLA}
##      return arrayValor 
##    end
##    
##    #Método 3: Sorpresa del TipoSorpresa especificado en el argumento del metodo
##    def self.sorTipoArgumento(sor)
##      arrayValor = Array.new
##      @@mazo.each { |sorpresa| arrayValor << sorpresa if sorpresa.tipo == sor}
##      return arrayValor 
##    end
#    public
#    #Metodo main
#    def self.main
#      #Cargar mazo de cartas
#      #inicializar_sorpresas
#      
##      #Muestra el mazo completo
##      @@mazo.each { |sorpresa| puts sorpresa.to_s}
##      
##      #Metodo1
##      puts "\n Metodo 1: Mayores que 0 ----------------------------"
##      puts sorMayorCero
##      
##      #Metodo2
##      puts "\n Metodo 2: Tipo IRACASILLA ----------------------------"
##      puts sorTipoCasilla
##      
##      #Metodo3
##      puts "\n Metodo 3: Tipo argumento ----------------------------"
##      puts sorTipoArgumento(TipoSorpresa::SALIRCARCEL)
##      
##      #Tablero
##      puts "\n Tablero ----------------------------"
##      puts @@tablero
#
#      
#      @jugadores = Array.new
#      
#      @jugadores << "Juan"
#      @jugadores << "Lorena"
#      @jugadores << "Javi"
#      
#      @qytetet = Qytetet.instance
#      @qytetet.inicializar_juego(@jugadores)
#      
#      puts @qytetet
#      
#      
#    end
#  end
#  PruebaQytetet.main
#end 