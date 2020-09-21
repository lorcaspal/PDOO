# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require 'singleton'

module ModeloQytetet
  class Dado
    include Singleton

    #Método
    def tirar
      dado = Random.new
      dado = rand(1..6)
#      dado = 4
      dado
    end
  end
end
