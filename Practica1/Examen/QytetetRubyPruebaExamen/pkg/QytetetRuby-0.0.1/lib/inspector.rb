# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'casilla'

module ModeloQytetet
  
  class Inspector
    attr_accessor :nombre, :codigo_numerico
    @manual = 'Debo inspeccionar las casillas con cuidado y esmero'
    @@numero_inspectores = 0
    def initialize(n, cn)
      @nombre = n
      @codigo_numerico = cn
      @@numero_inspectores = @@numero_inspectores + 1 
      @casilla = Array.new
    end
    
    def self.inspectores(n)
      @codigo_numerico = @@numero_inspectores + 1
      Inspector.new(n,@codigo_numerico)           
    end
    
    def self.imprimir_manual
      puts @manual
    end
    
    def asignar_casilla(una_casilla)
      @casilla << una_casilla
    end
    
    def desasignar_casillas()
      @casilla.clear
    end
    
    def inspeccionar()
      for i in 0..@casilla.size()-1
        puts @casilla.at(i).titulo
      end
    end
    
        #Método toString() devuelve un String con el estado del objeto correspondiente
    def to_s
        "********************* Inspectores *********************\n
        #{@nombre}
        \n--------------------------------------\n
        #{@codigo_numerico}
        \n--------------------------------------\n
        #{@@numero_inspectores}
        "
        
    end
    
    private :desasignar_casillas
  end
end
