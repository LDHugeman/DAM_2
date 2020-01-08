import gi

import conexion
import eventos
import funcionescli
import funcioneshabi
import funcionesreserva
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
        variables.ventana_dialog_backup = builder.get_object('ventanaDialogBackup')
        variables.ventana_dialog_restaurar_backup = builder.get_object('ventanaDialogRestaurarBackup')
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
        self.switch_habitacion = builder.get_object('switchHabitacion')
        self.label_mostrar_dni = builder.get_object('labelMostrarDni')
        self.label_mostrar_apellidos = builder.get_object('labelMostrarApellidos')
        self.combo_habitaciones = builder.get_object('comboBoxHabitaciones')
        self.entry_check_in = builder.get_object('entryCheckIn')
        self.entry_check_out = builder.get_object('entryCheckOut')
        self.label_mostrar_noches = builder.get_object('labelMostrarNumeroNoches')
        variables.combo_habitaciones = self.combo_habitaciones
        variables.calendario = self.calendario
        variables.ventana_calendario = self.ventana_calendario
        variables.fila_clientes = (self.entrada_dni, self.entrada_apelidos, self.entrada_nome, self.entry_data)
        variables.fila_habitaciones = (
            self.entrada_numero,
            self.radiobutton_simple,
            self.radiobutton_doble,
            self.radiobutton_familiar,
            self.entrada_precio,
            self.switch_habitacion)
        variables.fila_reservas = (
            self.label_mostrar_dni,
            self.label_mostrar_apellidos,
            self.combo_habitaciones,
            self.entry_check_in,
            self.entry_check_out,
            self.label_mostrar_noches)
        variables.lista_clientes = builder.get_object('listaClientes')
        variables.lista_habitaciones = builder.get_object('listaHabitaciones')
        variables.lista_reservas = builder.get_object('listaReservas')
        variables.tree_clientes = builder.get_object('treeViewClientes')
        variables.tree_habitaciones = builder.get_object('treeViewHabitaciones')
        variables.tree_reservas = builder.get_object('treeViewReservas')
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

    # funcionesreserva.listado_numero_habitaciones()


if __name__ == '__main__':
    main = Empresa()
    Gtk.main()
