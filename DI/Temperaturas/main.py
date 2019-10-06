# coding=utf-8

import gi
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

__autor__ = 'david'


import funciones


class Conversor:
    def __init__(self):
        # iniciamos la libreria Gtk
        b = Gtk.Builder()
        b.add_from_file('../ventana.glade')
        # cargamos los widgets con algún evento asociado o que son referenciados
        self.vprincipal = b.get_object('vPrincipal')
        self.btnsalir = b.get_object('btnSalir')
        self.cmbt1 = b.get_object('cmbT1')
        self.cmbt2 = b.get_object('cmbT2')
        self.enttemp = b.get_object('entTemp')
        self.lblresul = b.get_object('lblResul')
        # Diccionario de eventos
        dic = {'on_vPrincipal_destroy': self.salir, 'on_btnSalir_clicked': self.salir,
               'on_btnCalcular_clicked': self.calcular}
        self.selec1 = ""
        self.selec2 = ""

        # conectamostodo y mostramos
        b.connect_signals(dic)  # conecta los eventos de glade con el codigo
        self.vprincipal.show()

    # ahora codificamos las funciones

    def salir(self, widget):
        self.btnsalir.exit()
        Gtk.main_quit()

    def calcular(self, widget):
        try:
            self.selec1 = self.cmbt1.get_active_text()
            self.selec2 = self.cmbt2.get_active_text()
            if self.select1 == 'Celsius' and self.selec2 == 'Farenheit':
                var = funciones.celtofah(self.enttemp.get_text())
                self.lblresul.set_text('')
                self.lblresul.set_text(var)
        except:
            print('algún error')


if __name__ == '__main__':
    main = Conversor()
    Gtk.main()
