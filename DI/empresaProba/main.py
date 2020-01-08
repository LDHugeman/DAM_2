import gi

import conexion
import eventos
import funcionescliente
import variables

gi.require_version('Gtk','3.0')
from gi.repository import Gtk, Gdk


class EmpresaProba:
    def __init__(self):
        builder = Gtk.Builder()
        builder.add_from_file('ventanaProba.glade')

        ventana_principal = builder.get_object('ventanaPrincipal')
        variables.ventana_acerca_de = builder.get_object('ventanaAcercaDe')
        variables.ventana_dialog_salir = builder.get_object('ventanaDialogSalir')
        variables.panel = builder.get_object('panel')
        variables.ventana_calendario = builder.get_object('ventanaCalendario')
        variables.calendario = builder.get_object('calendario')
        menuBar = builder.get_object('menuBar').get_style_context()

        entry_dni = builder.get_object('entryDni')
        entry_apelidos = builder.get_object('entryApelidos')
        entry_nome = builder.get_object('entryNome')
        entry_data_cliente = builder.get_object('entryDataCliente')
        label_error_dni = builder.get_object('labelErrorDni')
        label_codigo_cliente = builder.get_object('labelCodigoCliente')

        variables.fila_clientes = (entry_dni, entry_apelidos, entry_nome, entry_data_cliente)
        variables.lista_clientes = builder.get_object('listaClientes')
        variables.tree_clientes = builder.get_object('treeClientes')
        variables.mensajes_label = (label_error_dni, label_codigo_cliente)

        builder.connect_signals(eventos.Eventos())

        self.set_style()
        menuBar.add_class('menuBar')

        ventana_principal.show_all()
        ventana_principal.maximize()
        conexion.Conexion().abrirbbdd()
        funcionescliente.listado_clientes(variables.lista_clientes)



    def set_style(self):
        css_provider = Gtk.CssProvider()
        css_provider.load_from_path('estilos.css')
        Gtk.StyleContext().add_provider_for_screen(
            Gdk.Screen.get_default(),
            css_provider,
            Gtk.STYLE_PROVIDER_PRIORITY_APPLICATION
        )

if __name__ == '__main__':
    main = EmpresaProba()
    Gtk.main()