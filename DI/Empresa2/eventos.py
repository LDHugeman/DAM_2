import gi
import conexion
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class Eventos:

    # Eventos generales

    def on_ventanaPrincipal_destroy(self, widget):
        conexion.Conexion.cerrarbbdd(self)
        Gtk.main_quit()

    def on_botonSalir_clicked(self, widget):
        conexion.Conexion.cerrarbbdd(self)
        Gtk.main_quit()
