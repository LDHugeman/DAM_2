# coding=utf-8

import gi
import Validar
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

__autor__ = 'david'


class ControlDni:
    def __init__(self):
        builder = Gtk.Builder()
        builder.add_from_file('ventana.glade')
        self.ventana_principal = builder.get_object('ventana_principal')
        self.entrada_nombre = builder.get_object('entrada_nombre')
        self.entrada_dni = builder.get_object('entrada_dni')
        self.salida_validez_dni = builder.get_object('salida_validez_dni')

        eventos = {'on_ventana_principal_destroy': self.salir,
                   'on_boton_cancelar_clicked': self.salir,
                   'on_entrada_dni_focus_out_event': self.mostrar_mensaje,
                   }

        builder.connect_signals(eventos)
        self.ventana_principal.show()

    def salir(self, widget):
        self.ventana_principal.close()
        Gtk.main_quit()

    def mostrar_mensaje(self, widget, Data  = None):
        dni = self.entrada_dni.get_text()
        if Validar.es_dni_valido(dni):
            self.salida_validez_dni.set_text('Dni correcto')
        else:
            self.salida_validez_dni.set_text('Dni incorrecto')


if __name__ == '__main__':
    main = ControlDni()
    Gtk.main()

