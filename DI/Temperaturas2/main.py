# coding=utf-8

import gi
import funciones
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

__autor__ = 'david'


class Conversor:
    def __init__(self):
        builder = Gtk.Builder()
        builder.add_from_file('ventana.glade')
        self.ventana_principal = builder.get_object('ventana_principal')
        self.radiobutton_celsius_entrada = builder.get_object('radiobutton_celsius_entrada')
        self.radiobutton_kelvin_entrada = builder.get_object('radiobutton_kelvin_entrada')
        self.radiobutton_fahrenheit_entrada = builder.get_object('radiobutton_fahrenheit_entrada')
        self.radiobutton_celsius_salida = builder.get_object('radiobutton_celsius_salida')
        self.radiobutton_kelvin_salida = builder.get_object('radiobutton_kelvin_salida')
        self.radiobutton_fahrenheit_salida = builder.get_object('radiobutton_fahrenheit_salida')
        self.entrada_temperatura = builder.get_object('entrada_temperatura')
        self.resultado_conversion = builder.get_object('salida_temperatura')
        eventos = {'on_ventana_principal_destroy': self.salir,
                   'on_boton_salir_clicked': self.salir,
                   'on_boton_calcular_clicked': self.calcular
                   }

        builder.connect_signals(eventos)
        self.ventana_principal.show()

    def salir(self, widget):
        self.ventana_principal.close()
        Gtk.main_quit()

    def calcular(self, widget):
        try:
            if self.radiobutton_celsius_entrada.get_active() and self.radiobutton_fahrenheit_salida.get_active():
                grados_fahrenheit = funciones.celsius_to_fahrenheit(self.entrada_temperatura.get_text())
                self.resultado_conversion.set_text('')
                self.resultado_conversion.set_text(str(grados_fahrenheit))
            elif self.radiobutton_celsius_entrada.get_active() and self.radiobutton_kelvin_salida.get_active():
                grados_kelvin = funciones.celsius_to_kelvin(self.entrada_temperatura.get_text())
                self.resultado_conversion.set_text('')
                self.resultado_conversion.set_text(str(grados_kelvin))
            elif self.radiobutton_kelvin_entrada.get_active() and self.radiobutton_celsius_salida.get_active():
                grados_celsius = funciones.kelvin_to_celsius(self.entrada_temperatura.get_text())
                grados_celsius = round(grados_celsius, 2)
                self.resultado_conversion.set_text('')
                self.resultado_conversion.set_text(str(grados_celsius))
            elif self.radiobutton_fahrenheit_entrada.get_active() and self.radiobutton_celsius_salida.get_active():
                grados_celsius = funciones.fahrenheit_to_celsius(self.entrada_temperatura.get_text())
                grados_celsius = round(grados_celsius, 2)
                self.resultado_conversion.set_text('')
                self.resultado_conversion.set_text(str(grados_celsius))
            elif self.radiobutton_kelvin_entrada.get_active() and self.radiobutton_fahrenheit_salida.get_active():
                grados_fahrenheit = funciones.kelvin_to_fahrenheit(self.entrada_temperatura.get_text())
                grados_fahrenheit = round(grados_fahrenheit, 2)
                self.resultado_conversion.set_text('')
                self.resultado_conversion.set_text(str(grados_fahrenheit))
            elif self.radiobutton_fahrenheit_entrada.get_active() and self.radiobutton_kelvin_salida.get_active():
                grados_kelvin = funciones.fahrenheit_to_kelvin(self.entrada_temperatura.get_text())
                grados_kelvin = round(grados_kelvin, 2)
                self.resultado_conversion.set_text('')
                self.resultado_conversion.set_text(str(grados_kelvin))

        except Exception as e:
            print(e)
            print('algún error')


if __name__ == '__main__':
    main = Conversor()
    Gtk.main()