import gi

import eventos
import funciones_habitacion
import funciones_reserva
import funcionescli
import funcionesvar
import variables
from conexion import Conexion

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk, Gdk
'''
El main contiene los elementos necesarios para lanzar la aplicación,
así como la declaración de los widgets que se usarán. También los módulos
que tenemos que importar de las librerías gráficas.

'''


class Empresa:
    def __init__(self):
        # Iniciamos la libreria Gtk
        builder = Gtk.Builder()
        builder.add_from_file('ventana.glade')

        # Cargamos los widgets con algún evente asociado o que son referenciados
        vprincipal = builder.get_object('venPrincipal')
        builder.vendialog = builder.get_object('venDialog')
        variables.venacercade = builder.get_object('venAcercade')
        variables.panel = builder.get_object('Panel')
        variables.filechooserbackup = builder.get_object('fileChooserbackup')
        menubar = builder.get_object('menuBar').get_style_context()

        # Declaración de wigdets
        entdni = builder.get_object('entDni')
        entapel = builder.get_object('entApel')
        entnome = builder.get_object('entNome')
        entdatacli = builder.get_object('entDatacli')
        lblerrdni = builder.get_object('lblErrdni')
        lblcodcli = builder.get_object('lblCodcli')
        lblnumnoches = builder.get_object('lblNumnoches')
        lbldirbackup = builder.get_object('lblFolderbackup')
        lbldnires = builder.get_object('lblDnires')
        lblapelres = builder.get_object('lblApelres')
        variables.vencalendar = builder.get_object('venCalendar')
        variables.vendialogsalir = builder.get_object('vendialogSalir')
        variables.calendar = builder.get_object('Calendar')
        variables.entries_cliente = (entdni, entapel, entnome, entdatacli)
        variables.listclientes = builder.get_object('listaClientes')
        variables.treereservas = builder.get_object('treeReservas')
        variables.listreservas = builder.get_object('listaReservas')
        variables.treeclientes = builder.get_object('treeClientes')
        variables.menslabel = (lblerrdni, lblcodcli, lblnumnoches, lbldirbackup, lbldnires, lblapelres)

        # Widgets habitaciones
        entnumhab = builder.get_object('entNumhab')
        entprezohab = builder.get_object('entPrezohab')
        rbtsimple = builder.get_object('rbtSimple')
        rbtdoble = builder.get_object('rbtDoble')
        rbtfamily = builder.get_object('rbtFamily')
        variables.treehab = builder.get_object('treeHab')
        variables.listhab = builder.get_object('listaHabitaciones')
        variables.filahab = (entnumhab, entprezohab)
        variables.filarbt = (rbtsimple, rbtdoble, rbtfamily)
        variables.listcmbhab = builder.get_object('listaComboHabitaciones')
        variables.cmbhab = builder.get_object('cmbNumres')
        variables.switch = builder.get_object('switch')

        # Widgets reservas

        entdatain = builder.get_object('entDatain')
        entdataout = builder.get_object('entDataout')
        variables.switch_reservas = builder.get_object('switchReservas')

        variables.filareserva = (entdni, entapel, entdatain, entdataout)

        # Widgets factura

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

        # Conectamos eventos
        builder.connect_signals(eventos.Eventos())

        # Conexion estilos

        self.set_style()
        menubar.add_class('menuBar')
        '''
        for i in range(len(variables.menserror)):
            variables.menserror[i].add_class('label')
        '''
        vprincipal.show_all()
        vprincipal.maximize()
        Conexion().abrirbbdd()
        funciones_habitacion.listado_numeros_habitaciones()
        funcionescli.carga_lista_clientes(variables.listclientes)
        funciones_habitacion.carga_lista_habitaciones(variables.listhab)
        funciones_reserva.recargar_lista_reservas()
        funcionesvar.controlhab()

    def set_style(self):
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
