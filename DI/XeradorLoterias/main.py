import gi
import eventos
import variables
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class XeradorLoterias:
    def __init__(self):
        builder = Gtk.Builder()
        builder.add_from_file('ventana.glade')
        self.ventana_principal = builder.get_object('ventanaPrincipal')
        self.ventana_dialog = builder.get_object('dialogSalir')
        variables.combo_loterias = builder.get_object('comboLoterias')
        variables.label_resultado = builder.get_object('labelResultado')
        variables.ventana_dialog = self.ventana_dialog
        variables.ventana_principal = self.ventana_principal
        builder.connect_signals(eventos.Eventos())
        self.ventana_principal.show()


if __name__ == '__main__':
    main = XeradorLoterias()
    Gtk.main()