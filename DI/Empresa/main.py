import gi

import conexion
import eventos
import funcionescli
import funcioneshabi
import variables

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk, Gdk

'''
El main contiene los elementos necesarios para lanzar la aplicación
así como la declaración de los widgets que se usarán. También los módulos
que tenemos que importar de las librerías gráficas

'''


class Empresa:
    def __init__(self):
        builder = Gtk.Builder()
        builder.add_from_file('ventana.glade')
        self.ventana_principal = builder.get_object('ventanaPrincipal')
        variables.panel = builder.get_object('panel')
        variables.ventana_acerca_de = builder.get_object('ventanaAcercaDe')
        variables.ventana_dialog = builder.get_object('ventanaDialog')
        self.entrada_dni = builder.get_object('entryDni')
        self.entrada_apelidos = builder.get_object('entryApelidos')
        self.entrada_nome = builder.get_object('entryNome')
        self.salida_validez_dni = builder.get_object('labelErrorDni')
        self.codigo_cliente = builder.get_object('labelCodigoCliente')
        self.operacion_hecha = builder.get_object('labelOperacionHecha')
        self.fecha = builder.get_object('labelFecha')
        self.ventana_calendario = builder.get_object('ventanaCalendario')
        self.calendario = builder.get_object('calendario')
        self.entry_data = builder.get_object('entryData')
        self.entrada_numero = builder.get_object('entryNumero')
        self.radiobutton_simple = builder.get_object('radioButtonSimple')
        self.radiobutton_doble = builder.get_object('radioButtonDoble')
        self.radiobutton_familiar = builder.get_object('radioButtonFamiliar')
        self.entrada_precio = builder.get_object('entryPrecio')
        variables.calendario = self.calendario
        variables.ventana_calendario = self.ventana_calendario
        variables.fila_clientes = (self.entrada_dni, self.entrada_apelidos, self.entrada_nome, self.entry_data)
        variables.fila_habitaciones = (
            self.entrada_numero,
            self.radiobutton_simple,
            self.radiobutton_doble,
            self.radiobutton_familiar,
            self.entrada_precio)
        variables.lista_clientes = builder.get_object('listaClientes')
        variables.lista_habitaciones = builder.get_object('listaHabitaciones')
        variables.tree_clientes = builder.get_object('treeViewClientes')
        variables.tree_habitaciones = builder.get_object('treeViewHabitaciones')
        variables.mensajeserror = (self.salida_validez_dni, self.codigo_cliente, self.operacion_hecha, self.fecha)
        builder.connect_signals(eventos.Eventos())
        self.ventana_principal.show()
        self.ventana_principal.maximize()
        funcionescli.mostrar_fecha()
        conexion.Conexion().abrirbbdd()
        funcionescli.listado_clientes(variables.lista_clientes)
        funcioneshabi.listado_habitaciones(variables.lista_habitaciones)

        self.set_styles()
        menubar = builder.get_object('menuBar').get_style_context()
        menubar.add_class('menuBar')
        toolBar = builder.get_object('toolBar').get_style_context()
        toolBar.add_class('toolBar')
        menuFichero = builder.get_object('menuFichero').get_style_context()
        menuFichero.add_class('menuFichero')

    def set_styles(self):
        css_provider = Gtk.CssProvider()
        css_provider.load_from_path('estilos.css')
        Gtk.StyleContext().add_provider_for_screen(
            Gdk.Screen.get_default(),
            css_provider,
            Gtk.STYLE_PROVIDER_PRIORITY_APPLICATION)


if __name__ == '__main__':
    main = Empresa()
    Gtk.main()
