# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'tipo_casilla'
require_relative 'casilla'
require_relative 'calle'

module ModeloQytetet
  class Tablero
    #Consultor
    attr_reader :carcel

    #Constructor
    def initialize()
      @casillas = Array.new
      @carcel
      inicializar
    end
    
    #Metodos
    def es_casilla_carcel (numero_casilla)
      numero_casilla == carcel.numero_casillas
    end
    
    def obtener_casilla_numero(numero_casilla) 
      obt_casilla = @casillas.at(numero_casilla)      
      obt_casilla
    end
    
    def obtener_nueva_casilla(casilla,desplazamiento)
      nueva_casilla = casilla.numero_casilla + desplazamiento
      if(nueva_casilla > 19)
        nueva_casilla = (nueva_casilla%19) - 1
      end
      c = obtener_casilla_numero(nueva_casilla)
      c
    end
    
    def inicializar
      
        #Casilla de salida
        @casillas << (Casilla.no_calle(0, TipoCasilla::SALIDA))
        
        #Casillas de tipo calle
        @casillas << (Calle.new(1, 100, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Piruleta', 60, 0.1, 150, 250)))
        @casillas << (Calle.new(2, 300, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Nube', 50,  0.16, 156, 270)))
        @casillas << (Calle.new(3, 210, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Gominola', 55,  0.11, 160, 300)))
        
        #Casilla de tipo sopresa
        @casillas << (Casilla.no_calle(4, TipoCasilla::SORPRESA))
        
        #Casillas de tipo calle
        @casillas << (Calle.new(5, 400, TipoCasilla::CALLE,  TituloPropiedad.new('Calle de Chocolate', 70,  -0.1, 400, 300)))
        @casillas << (Calle.new(6, 450, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Bombon', 75,  -0.16, 500, 350)))
        
        #Casilla tipo Juez
        @casillas << (Casilla.no_calle(7, TipoCasilla::JUEZ))
        
        #Casilla tipo calle
        @casillas << (Calle.new(8, 100, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Perla', 50,  -0.2, 150, 250)))
        
        #Casilla tipo Carcel
        @casillas << (Casilla.no_calle(9, TipoCasilla::CARCEL))
        
        #Casilla tipo Sorpresa
        @casillas << (Casilla.no_calle(10, TipoCasilla::SORPRESA))
        
        #Casillas de tipo calle
        @casillas << (Calle.new(11, 470, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Baston de Caramelo', 70,  0.1, 600, 300)))
        @casillas << (Calle.new(12, 480, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Cupcake', 75,  0.16, 700, 350)))
        @casillas << (Calle.new(13, 490, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Manzana de Caramelo', 80, 0.17, 750, 400)))
        
        #Casilla tipo Sorpresa
        @casillas << (Casilla.no_calle(14, TipoCasilla::SORPRESA))
        
        #Casilla tipo Parking
        @casillas << (Casilla.no_calle(15, TipoCasilla::PARKING))
        
        #Casillas de tipo calle
        @casillas << (Calle.new(16, 600, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Galleta', 80, 0.18, 800, 560)))
        @casillas << (Calle.new(17, 650, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Bizcocho', 90, 0.19, 900, 600)))
        
        #Casilla tipo Impuesto
        @casillas << (Casilla.no_calle(18, TipoCasilla::IMPUESTO))
        
        #Casillas de tipo calle
        @casillas << (Calle.new(19, 700, TipoCasilla::CALLE,  TituloPropiedad.new('Calle Lemon Pie', 100, 0.2, 1000, 750)))
        
        #Inicializar casilla carcel en el numero 9
        @carcel = @casillas.at(9)
        
        
      
    end
 
    #Método toString() devuelve un String con el estado del objeto correspondiente
    def to_s
      "Casillas #{@casillas.each {|i| puts i.to_s}}
       \nCarcel en: #{@carcel}"
    end
    
  end
end
