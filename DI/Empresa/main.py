# coding=utf-8
'''Módulo que contiene los widgets usados y los estilos.
'''
import gi

import eventos
import funciones_habitacion
import funciones_reserva
import funciones_clientes
import funciones_servicios
import variables
from conexion import Conexion

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk, Gdk


class Empresa:
    def __init__(self):
        '''
        Carga los widgets con algún evento asociado o que son referenciados.
            :return: void
        '''
        builder = Gtk.Builder()
        builder.add_from_file('ventana.glade')

        ventana_principal = builder.get_object('ventanaPrincipal')
        variables.ventana_precios = builder.get_object('ventanaPrecios')
        variables.ventana_dialog_salir = builder.get_object('ventanaDialogSalir')
        variables.ventana_acerca_de = builder.get_object('ventanaAcercaDe')
        variables.panel = builder.get_object('panel')
        variables.ventana_backup = builder.get_object('ventanaBackup')
        variables.ventana_restaurar_backup = builder.get_object('ventanaRestaurarBackup')
        variables.ventana_calendario = builder.get_object('ventanaCalendario')
        variables.ventana_importar_clientes = builder.get_object('ventanaImportarClientes')
        variables.ventana_exportar_clientes = builder.get_object('ventanaExportarClientes')
        variables.calendario = builder.get_object('calendario')
        menu_bar = builder.get_object('menuBar').get_style_context()

        entry_dni = builder.get_object('entryDni')
        entry_apellidos = builder.get_object('entryApellidos')
        entry_nombre = builder.get_object('entryNome')
        entry_fecha_cliente = builder.get_object('entryFechaCliente')
        label_error_dni = builder.get_object('labelErrorDni')
        label_codigo_cliente = builder.get_object('labelCodigoCliente')
        label_numero_noches = builder.get_object('labelNumeroNoches')
        label_directorio_backup = builder.get_object('lblFolderbackup')
        label_dni_reserva = builder.get_object('labelDniReserva')
        label_apellidos_reserva = builder.get_object('labelApellidosReserva')
        variables.entries_cliente = (entry_dni, entry_apellidos, entry_nombre, entry_fecha_cliente)
        variables.lista_clientes = builder.get_object('listaClientes')
        variables.tree_clientes = builder.get_object('treeClientes')
        variables.mensajes_label = (label_error_dni, 
                                    label_codigo_cliente, 
                                    label_numero_noches, 
                                    label_directorio_backup, 
                                    label_dni_reserva, 
                                    label_apellidos_reserva)

        entry_numero_habitacion = builder.get_object('entryNumeroHabitacion')
        entry_precio_habitacion = builder.get_object('entryPrecioHabitacion')
        radiobutton_simple = builder.get_object('radioButtonSimple')
        radiobutton_doble = builder.get_object('radioButtonDoble')
        radiobutton_familiar = builder.get_object('radioButtonFamiliar')
        variables.tree_habitaciones = builder.get_object('treeHabitaciones')
        variables.lista_habitaciones = builder.get_object('listaHabitaciones')
        variables.entries_habitacion = (entry_numero_habitacion, entry_precio_habitacion)
        variables.radiobuttons_tipo_habitacion = (radiobutton_simple, radiobutton_doble, radiobutton_familiar)
        variables.lista_combo_habitaciones = builder.get_object('listaComboHabitaciones')
        variables.combo_habitaciones = builder.get_object('comboBoxHabitacionesReserva')
        variables.switch_habitaciones = builder.get_object('switchHabitaciones')

        entry_fecha_check_in = builder.get_object('entryCheckIn')
        entry_fecha_check_out = builder.get_object('entryCheckOut')
        variables.switch_reservas = builder.get_object('switchReservas')
        variables.tree_reservas = builder.get_object('treeReservas')
        variables.lista_reservas = builder.get_object('listaReservas')
        variables.entries_reserva = (entry_fecha_check_in, entry_fecha_check_out)

        label_dni_factura = builder.get_object('labelDniFactura')
        label_apelidos_factura = builder.get_object('labelApelidosFactura')
        label_nome_factura = builder.get_object('labelNomeFactura')
        label_codigo_reserva_factura = builder.get_object('labelCodigoReservaFactura')
        label_habitacion_factura = builder.get_object('labelHabitacionFactura')
        label_fecha_factura = builder.get_object('labelFechaFactura')
        label_unidades_factura = builder.get_object('labelUnidades')
        label_precio_unidad_factura = builder.get_object('labelPrecioUnidad')
        label_total_factura = builder.get_object('labelTotal')

        variables.labels_factura = (
            label_dni_factura,
            label_apelidos_factura,
            label_nome_factura,
            label_codigo_reserva_factura,
            label_habitacion_factura,
            label_fecha_factura,
            label_unidades_factura,
            label_precio_unidad_factura,
            label_total_factura)

        label_codigo_reserva_servicios = builder.get_object('labelCodigoReservaServicios')
        label_habitacion_servicios = builder.get_object('labelHabitacionServicios')
        variables.labels_servicios = (label_codigo_reserva_servicios, label_habitacion_servicios)
        radiobutton_solo_alojamiento = builder.get_object('radioButtonSoloAlojamiento')
        radiobutton_desayuno = builder.get_object('radioButtonDesayuno')
        radiobutton_comida = builder.get_object('radioButtonComida')
        variables.radiobuttons_servicios_basicos = (radiobutton_solo_alojamiento,
                                                    radiobutton_desayuno,
                                                    radiobutton_comida)
        variables.checkbox_parking = builder.get_object('checkBoxParking')
        entry_tipo_servicio = builder.get_object('entryTipoServicio')
        entry_precio_servicio = builder.get_object('entryPrecioServicio')
        variables.entries_servicios_adicionales = (entry_tipo_servicio, entry_precio_servicio)
        variables.tree_servicios = builder.get_object('treeServicios')
        variables.lista_servicios = builder.get_object('listaServicios')

        entry_precio_parking = builder.get_object('entryPrecioParking')
        entry_precio_desayuno = builder.get_object('entryPrecioDesayuno')
        entry_precio_pension_completa = builder.get_object('entryPrecioPensionCompleta')
        variables.entries_precios_servicios_basicos = (entry_precio_parking,
                                                       entry_precio_desayuno,
                                                       entry_precio_pension_completa)

        builder.connect_signals(eventos.Eventos())

        self.set_style()
        menu_bar.add_class('menuBar')

        pantalla = Gdk.Screen.get_default()
        anchura = pantalla.get_width()
        altura = pantalla.get_height()
        ventana_principal.show_all()
        ventana_principal.resize(anchura, altura)
        Conexion().abrirbbdd()
        funciones_habitacion.actualizar_numeros_habitacion()
        funciones_clientes.actualizar_lista_clientes(variables.lista_clientes)
        funciones_habitacion.actualizar_lista_habitaciones(variables.lista_habitaciones)
        funciones_reserva.actualizar_lista_reservas()

    def set_style(self):
        '''
        Establece los estilos.
            :return: void
        '''
        css_provider = Gtk.CssProvider()
        css_provider.load_from_path('estilos.css')
        Gtk.StyleContext().add_provider_for_screen(
            Gdk.Screen.get_default(),
            css_provider,
            Gtk.STYLE_PROVIDER_PRIORITY_APPLICATION
        )


if __name__ == '__main__':
    main = Empresa()
    Gtk.main()
