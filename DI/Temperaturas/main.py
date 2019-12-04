# coding=utf-8

import gi

import funciones

gi.require_version('Gtk', '3.0')

from gi.repository import Gtk

__autor__ = 'david'


class Conversor:
    def __init__(self):
        builder = Gtk.Builder()
        builder.add_from_file('../ventana.glade')
        self.ventana_principal = builder.get_object('ventana_principal')
        self.combo_entrada = builder.get_object('combo_entrada')
        self.combo_salida = builder.get_object('combo_salida')
        self.entrada_temperatura = builder.get_object('entrada_temperatura')
        self.resultado_conversion = builder.get_object('salida_temperatura')
        eventos = {'on_ventana_principal_destroy': self.salir,
                   'on_boton_salir_clicked': self.salir,
                   'on_boton_calcular_clicked': self.calcular
                   }

        builder.connect_signals(eventos)  # conecta los eventos de glade con el codigo
        self.ventana_principal.show()

    def salir(self, widget):
        self.ventana_principal.close()
        Gtk.main_quit()

    def calcular(self, widget):
        try:
            valor_combo_entrada = self.combo_entrada.get_active_text()
            valor_combo_salida = self.combo_salida.get_active_text()
            if valor_combo_entrada == 'Celsius' and valor_combo_salida == 'Fahrenheit':
                grados_fahrenheit = funciones.celsius_to_fahrenheit(self.entrada_temperatura.get_text())
                self.resultado_conversion.set_text('')
                self.resultado_conversion.set_text(str(grados_fahrenheit))
            elif valor_combo_entrada == 'Celsius' and valor_combo_salida == 'Kelvin':
                grados_kelvin = funciones.celsius_to_kelvin(self.entrada_temperatura.get_text())
                self.resultado_conversion.set_text('')
                self.resultado_conversion.set_text(str(grados_kelvin))
        except Exception as e:
            print(e)
            print('algún error')


if __name__ == '__main__':
    main = Conversor()
    Gtk.main()
