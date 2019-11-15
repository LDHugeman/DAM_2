import os
import shutil
import zipfile

import gi
import conexion, variables, funcionescli, main
import funcioneshabi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class Eventos:

    # Eventos generales

    def salir(self):
        conexion.Conexion.cerrarbbdd(self)
        Gtk.main_quit()

    def on_ventanaPrincipal_destroy(self, widget):
        self.salir()

    def on_botonSalirTool_clicked(self, widget):
        self.salir()

    # Eventos clientes

    def on_botonAltaClientes_clicked(self, widget):
        try:
            dni = variables.fila_clientes[0].get_text()
            apelidos = variables.fila_clientes[1].get_text()
            nome = variables.fila_clientes[2].get_text()
            data = variables.fila_clientes[3].get_text()
            registro = (dni, apelidos, nome, data)
            if funcionescli.es_dni_valido(dni):
                funcionescli.insertarcliente(registro)
                funcionescli.listado_clientes(variables.lista_clientes)
                funcionescli.limpiar_entry(variables.fila_clientes)
                funcionescli.mostrar_operacion('Cliente dado de alta correctamente')
            else:
                print('Dni erróneo')
        except:
            print('Error alta clientes')

    def on_botonAltaHabitacion_clicked(self, widget):
        try:
            numero = variables.fila_habitaciones[0].get_text()
            if variables.fila_habitaciones[1].get_active():
                tipo = 'Simple'
            elif variables.fila_habitaciones[2].get_active():
                tipo = 'Doble'
            elif variables.fila_habitaciones[3].get_active():
                tipo = 'Familiar'
            precio = variables.fila_habitaciones[4].get_text()
            registro = (numero, tipo, precio)
            if numero != '' and precio != '':
                funcioneshabi.insertarhabitacion(registro)
                funcioneshabi.listado_habitaciones(variables.lista_habitaciones)
                funcioneshabi.limpiar_entry(variables.fila_habitaciones)
        except Exception as e:
            print(e)
            print('Error alta habitaciones')

    def on_botonBajaClientes_clicked(self, widget):
        try:
            dni = variables.fila_clientes[0].get_text()
            if dni != '':
                funcionescli.baja_cliente(dni)
                funcionescli.listado_clientes(variables.lista_clientes)
                funcionescli.limpiar_entry(variables.fila_clientes)
                funcionescli.mostrar_operacion('Cliente dado de baja correctamente')
            else:
                print('Falta dni u otro error')
        except:
            print("Error en botón baja clientes")

    def on_botonBajaHabitacion_clicked(self, widget):
        try:
            numero = variables.fila_habitaciones[0].get_text()
            if numero != '':
                funcioneshabi.baja_habitacion(numero)
                funcioneshabi.listado_habitaciones(variables.lista_habitaciones)
                funcioneshabi.limpiar_entry(variables.fila_habitaciones)
            else:
                print('Falta dni u otro error')
        except:
            print("Error en botón baja clientes")

    def on_botonModificarClientes_clicked(self, widget):
        try:
            codigo = variables.mensajeserror[1].get_text()
            dni = variables.fila_clientes[0].get_text()
            apelidos = variables.fila_clientes[1].get_text()
            nome = variables.fila_clientes[2].get_text()
            data = variables.fila_clientes[3].get_text()
            registro = (dni, apelidos, nome, data)
            if dni != '':
                funcionescli.modificar_cliente(registro, codigo)
                funcionescli.listado_clientes(variables.lista_clientes)
                funcionescli.limpiar_entry(variables.fila_clientes)
                funcionescli.mostrar_operacion('Cliente modificado correctamente')
            else:
                print('Falta el dni')
        except Exception as e:
            print(e)
            print('Error en botón modificar cliente')

    def on_botonModificarHabitacion_clicked(self, widget):
        try:
            model, iter = variables.tree_habitaciones.get_selection().get_selected()
            numero = variables.fila_habitaciones[0].get_text()
            numero_busqueda = model.get_value(iter, 0)
            if variables.fila_habitaciones[1].get_active():
                tipo = 'Simple'
            elif variables.fila_habitaciones[2].get_active():
                tipo = 'Doble'
            elif variables.fila_habitaciones[3].get_active():
                tipo = 'Familiar'
            precio = variables.fila_habitaciones[4].get_text()
            registro = (numero, tipo, precio)
            if numero != '':
                funcioneshabi.modificar_habitacion(registro, numero_busqueda)
                funcioneshabi.listado_habitaciones(variables.lista_habitaciones)
                funcioneshabi.limpiar_entry(variables.fila_habitaciones)
        except Exception as e:
            print("Error en botón modificar habitación")
            print(e)

    def on_treeViewClientes_cursor_changed(self, widget):
        try:
            model, iter = variables.tree_clientes.get_selection().get_selected()
            # model es el modelo de la tabla de datos
            # iter es el numero que identifica a la fila que marcamos
            variables.mensajeserror[0].set_text('')
            funcionescli.limpiar_entry(variables.fila_clientes)
            if iter != None:
                dni_seleccionado = model.get_value(iter, 0)
                apelidos_seleccionados = model.get_value(iter, 1)
                nome_seleccionado = model.get_value(iter, 2)
                data_seleccionada = model.get_value(iter, 3)
                if (data_seleccionada == None):
                    data_seleccionada = ""
                codigo_seleccionado = funcionescli.selectcliente(dni_seleccionado)
                variables.mensajeserror[1].set_text(str(codigo_seleccionado[0]))
                variables.fila_clientes[0].set_text(str(dni_seleccionado))
                variables.fila_clientes[1].set_text(str(apelidos_seleccionados))
                variables.fila_clientes[2].set_text(str(nome_seleccionado))
                variables.fila_clientes[3].set_text(str(data_seleccionada))

        except Exception as e:
            print('Error carga cliente')
            print(e)

    def on_treeViewHabitaciones_cursor_changed(self, widget):
        try:
            model, iter = variables.tree_habitaciones.get_selection().get_selected()
            if iter != None:
                numero_seleccionado = model.get_value(iter, 0)
                tipo_seleccionado = model.get_value(iter, 1)
                precio_seleccionado = model.get_value(iter, 2)
                variables.fila_habitaciones[0].set_text(str(numero_seleccionado))
                if tipo_seleccionado == 'Simple':
                    variables.fila_habitaciones[1].set_active(True)
                elif tipo_seleccionado == 'Doble':
                    variables.fila_habitaciones[2].set_active(True)
                elif tipo_seleccionado == 'Familar':
                    variables.fila_habitaciones[3].set_active(True)
                variables.fila_habitaciones[4].set_text(str(precio_seleccionado))
        except Exception as e:
            print('Error carga habitación')
            print(e)

    def on_entryDni_focus_out_event(self, widget, Data=None):
        dni = variables.fila_clientes[0].get_text()
        if funcionescli.es_dni_valido(dni):
            variables.mensajeserror[0].set_text('')
        else:
            variables.mensajeserror[0].set_text('Dni incorrecto')

    def on_botonCalendario_clicked(self, widget):
        try:
            variables.ventana_calendario.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_calendario.show()
        except Exception as e:
            print(e)
            print('Error al abrir el calendario')

    def on_calendario_day_selected_double_click(self, widget):
        try:
            agno, mes, dia = variables.calendario.get_date()
            fecha = "%s/" % dia + "%s/" % (mes + 1) + "%s" % agno
            variables.fila_clientes[3].set_text(fecha)
            variables.ventana_calendario.hide()
        except:
            print('Error al coger la fecha')

    # Eventos de los botones de la toolbar

    def on_botonClienteTool_clicked(self, widget):
        try:
            panel_actual = variables.panel.get_current_page()
            if panel_actual != 0:
                variables.panel.set_current_page(0)
            else:
                pass
        except Exception as e:
            print('Error botón cliente barra herramientas')
            print(e)

    def on_botonReservaTool_clicked(self, widget):
        try:
            panel_actual = variables.panel.get_current_page()
            if panel_actual != 1:
                variables.panel.set_current_page(1)
        except Exception as e:
            print('Error botón reserva barra herramientas')
            print(e)

    def on_botonHabitacionTool_clicked(self, widget):
        try:
            panel_actual = variables.panel.get_current_page()
            if panel_actual != 2:
                variables.panel.set_current_page(2)
        except Exception as e:
            print('Error botón habitación barra herramientas')
            print(e)

    def on_botonLimpiarTool_clicked(self, widget):
        try:
            funcionescli.limpiar_entry(variables.fila_clientes)
            funcioneshabi.limpiar_entry(variables.fila_habitaciones)
        except Exception as e:
            print('Error botón limpiar barra herramientas')
            print(e)

    def on_botonCalculadoraTool_clicked(self, widget):
        os.system('gnome-calculator')

    def on_menuBarSalir_activate(self, widged):
        try:
            self.salir()
        except:
            print('Error en el menubar salir')

    def on_menuBarAcercaDe_activate(self, widget):
        variables.ventana_acerca_de.show()

    def on_menuBarBackup_activate(self, widget):
        variables.ventana_dialog.show()

    def on_botonSalirDialog_clicked(self, widget):
        try:
            variables.ventana_dialog.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_dialog.hide()
        except:
            print('Error al salir de la ventana dialog')

    def on_botonSalirAcercaDe_clicked(self, widget):
        try:
            variables.ventana_acerca_de.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_acerca_de.hide()
        except:
            print('Error al salir de acerca de')

    '''
    def on_botonComprimir_clicked(self, widget):
        try:
            import zlib
            compression = zipfile.ZIP_DEFLATED
            fichero = variables.ventana_dialog.get_filename()
            backup = zipfile.ZipFile(' ', mode='w')
            backup.write(fichero, compress_type=compression)
        except Exception as e:
            print('Error al comprimir base datos')
            print(e)
    '''

    def on_botonBackupTool_clicked(self, widget):
        try:
            import zlib
            conexion.Conexion().cerrarbbdd()
            compression = zipfile.ZIP_DEFLATED
            fichero = 'empresa.sqlite'
            backup = zipfile.ZipFile('backup', mode='w')
            backup.write(fichero, compress_type=compression)
            shutil.move('/media/a18luisdvp/Pen64/Repositorios/DAM_2/DI/Empresa/backup.zip', '/home/a18luisdvp/DI')
        except Exception as e:
            print('Error al comprimir base datos')
            print(e)
