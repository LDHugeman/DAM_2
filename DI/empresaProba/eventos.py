import gi

import conexion
import funcionescliente
import variables

gi.require_version('Gtk','3.0')
from gi.repository import Gtk


class Eventos():

    def salir(self):
        try:
            conexion.Conexion.cerrarbbdd(self)
            Gtk.main_quit()
        except:
            print('error función salir')

    def on_ventanaPrincipal_destroy(self, widget):
        self.salir()

    def on_botonAltaCliente_clicked(self, widget):
        try:
            dni = variables.fila_clientes[0].get_text()
            apelidos = variables.fila_clientes[1].get_text()
            nome = variables.fila_clientes[2].get_text()
            data = variables.fila_clientes[3].get_text()
            registro = (dni, apelidos, nome, data)
            if funcionescliente.validoDNI(dni):
                funcionescliente.insertar_cliente(registro)
                funcionescliente.listado_clientes(variables.lista_clientes)
                funcionescliente.limpiar_entry(variables.fila_clientes)
            else:
                variables.mensajes_label[0].set_text('ERROR DNI')
        except:
            print('Error alta clientes')

    def on_botonBajaCliente_clicked(self, widget):
        try:
            dni = variables.fila_clientes[0].get_text()
            if dni != '':
                funcionescliente.baja_cliente(dni)
                funcionescliente.listado_clientes(variables.lista_clientes)
                funcionescliente.limpiar_entry(variables.fila_clientes)
            else:
                print('falta dni u otro error')
        except:
            print("error en botón baja cliente")

    def on_botonModificarCliente_clicked(self, widget):
        try:
            codigo = variables.mensajes_label[1].get_text()
            dni = variables.fila_clientes[0].get_text()
            apelidos = variables.fila_clientes[1].get_text()
            nome = variables.fila_clientes[2].get_text()
            data = variables.fila_clientes[3].get_text()
            registro = (dni, apelidos, nome, data)
            if dni !='':
                funcionescliente.modificar_cliente(registro, codigo)
                funcionescliente.listado_clientes(variables.lista_clientes)
                funcionescliente.limpiar_entry(variables.fila_clientes)
            else:
                print('falta el dni')
        except:
            print('error en botón modificar')

    def on_entryDni_focus_out_event(self, widget, dni):
        try:
            dni = variables.fila_clientes[0].get_text()
            if funcionescliente.validoDNI(dni):
                variables.mensajes_label[0].set_text('')
                pass
            else:
                variables.mensajes_label[0].set_text('ERROR')
        except:
            print("Error alta cliente en out focus")

    def on_treeClientes_cursor_changed(self, widget):
        try:
            model, iter = variables.tree_clientes.get_selection().get_selected()
            variables.mensajes_label[0].set_text('')
            funcionescliente.limpiar_entry(variables.fila_clientes)
            if iter!=None:
                dni_seleccionado = model.get_value(iter, 0)
                apelido_seleccionado = model.get_value(iter, 1)
                nome_seleccionado = model.get_value(iter, 2)
                data_seleccionada = model.get_value(iter, 3)
                if data_seleccionada == None:
                    data_seleccionada = ''
                codigo = funcionescliente.seleccionar_cliente(dni_seleccionado)
                variables.mensajes_label[1].set_text(str(codigo[0]))
                variables.fila_clientes[0].set_text(str(dni_seleccionado))
                variables.fila_clientes[1].set_text(str(apelido_seleccionado))
                variables.fila_clientes[2].set_text(str(nome_seleccionado))
                variables.fila_clientes[3].set_text(str(data_seleccionada))
        except:
            print("error carga cliente")

    def on_botonCalendario_clicked(self, widget):
        try:
            variables.ventana_calendario.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_calendario.show()
        except:
            print('error abrir calendario')

    def on_calendario_day_selected_double_click(self, widget):
       try:
           agno, mes, dia = variables.calendario.get_date()
           fecha = "%s/" % dia + "%s/" % (mes + 1) + "%s" % agno
           variables.fila_clientes[3].set_text(fecha)
           variables.ventana_calendario.hide()
       except:
           print('error al coger la fecha')

    def on_acercaDe_activate(self, widget):
        try:
            variables.ventana_acerca_de.show()
        except:
            print('error avrir acerca de')

    def on_botonCerrarAcercaDe_clicked(self, widget):
        try:
            variables.ventana_acerca_de.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_acerca_de.hide()
        except:
            print('Error cerrar ventana acerca de')

    def on_botonClienteToolBar_clicked(self,widget):
        try:
            panel_actual = variables.panel.get_current_page()
            if panel_actual != 0:
                variables.panel.set_current_page(0)
            else:
                pass
        except:
            print('Error boton cliente barra herramientas')

    def on_botonSalirToolBar_clicked(self, widget):
        variables.ventana_dialog_salir.show()

    def on_botonAceptarVentanaDialog_clicked(self, widget):
        self.salir()

    def on_botonCancelarVentanaDialog_clicked(self,widget):
        variables.ventana_dialog_salir.connect('delete-event', lambda w, e: w.hide() or True)
        variables.ventana_dialog_salir.hide()