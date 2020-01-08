import gi
import eventos, conexion
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class Empresa:
    def __init__(self):
        builder = Gtk.Builder()
        builder.add_from_file('ventana.glade')
        self.ventana_principal = builder.get_object('ventanaPrincipal')
        builder.connect_signals(eventos.Eventos())
        self.ventana_principal.show()
        conexion.Conexion().abrirbbdd()


if __name__ == '__main__':
    main = Empresa()
    Gtk.main()
