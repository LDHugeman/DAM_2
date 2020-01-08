# coding=utf-8
import os
import datetime
import zipfile

import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk  # import gi.repository.Gtk as Gtk

__autor__ = 'davidvp'


class Compresor:
    def __init__(self):
        builder = Gtk.Builder()

        builder.add_from_file('ventana.glade')
        self.ventana_principal = builder.get_object('ventanaPrincipal')
        self.ventana_dialog = builder.get_object('ventanaAbrir')
        self.label_mensaje = builder.get_object('labelMensaje')
        self.fichero = None
        self.ruta_fichero = None
        eventos = {'on_ventanaPrincipal_destroy': self.salir,
                   'on_botonAbrir_clicked': self.abrir_ventana_dialog,
                   'on_botonSalir_clicked': self.salir,
                   'on_ventanaAbrir_destroy': self.cerrar_ventana_dialog,
                   'on_botonComprimir_clicked': self.comprimir_archivo,
                   'on_botonSalirVentanaDialog_clicked': self.cerrar_ventana_dialog,
                   'on_ventanaAbrir_selection_changed': self.seleccionar_archivo
                   }
        builder.connect_signals(eventos)
        self.ventana_principal.show()

    def salir(self, widget):
        self.ventana_principal.close()
        Gtk.main_quit()

    def abrir_ventana_dialog(self, widget, data=None):
        self.ventana_dialog.show()

    def cerrar_ventana_dialog(self, widget, data=None):
        self.ventana_dialog.hide()

    def seleccionar_archivo(self, widget):
        self.fichero = os.path.basename(str(self.ventana_dialog.get_filename()))
        self.label_mensaje.set_text("Fichero: " + self.fichero)
        if self.fichero == str(None):
            self.label_mensaje.set_text("Elija un fichero")

    def comprimir_archivo(self, widget, data=None):
        self.ruta_fichero = os.path.abspath(str(self.ventana_dialog.get_filename()))
        if self.fichero == str(None):
            self.label_mensaje.set_text("Falta fichero a comprimir")
        else:
            fecha = datetime.datetime.now()
            fecha = fecha.strftime("%d-%m-%Y_%H.%M.%S")
            fichero_comprimido = zipfile.ZipFile(str(fecha) + "_copia.zip", "w")
            fichero_comprimido.write(self.ruta_fichero, self.fichero, zipfile.ZIP_DEFLATED)


if __name__ == '__main__':
    main = Compresor()
    Gtk.main()
