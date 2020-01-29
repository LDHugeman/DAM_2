import os
import shutil
import zipfile
from datetime import datetime

import gi
import xlrd
import xlwt

import conexion
from conexion import Conexion
import facturacion
import funciones_habitacion
import funciones_reserva
import funciones_clientes
import funciones_varias
import impresion
import variables
from typing import List

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class Eventos():

    # eventos generales
    def on_menuBarAcercaDe_activate(self, widget):
        try:
            variables.ventana_acerca_de.show()
        except:
            print('Error en on_menuBarAcercaDe_activate')

    def on_botonCerrarVentanaAcercaDe_clicked(self, widget):
        try:
            variables.ventana_acerca_de.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_acerca_de.hide()
        except:
            print('Error en on_botonCerrarVentanaAcercaDe_clicked')

    def salir(self):
        try:
            Conexion().cerrarbbdd()
            funciones_varias.cerrar_timer()
            Gtk.main_quit()
        except Exception as e:
            print(e)
            print('Error en salir')

    def on_menuBarSalir_activate(self, widget):
        try:
            self.salir()
        except:
            print('Error en on_menuBarSalir_activate')

    def on_botonSalirToolBar_clicked(self, widget):
        variables.ventana_dialog_salir.show()

    def on_ventanaPrincipal_destroy(self, widget):
        self.salir()

    def on_botonCancelarSalir_clicked(self, widget):
        variables.ventana_dialog_salir.connect('delete-event', lambda w, e: w.hide() or True)
        variables.ventana_dialog_salir.hide()

    def on_botonAceptarSalir_clicked(self, widget):
        self.salir()

    def on_botonAltaCliente_clicked(self, widget):
        try:
            dni = variables.entries_cliente[0].get_text()
            apel = variables.entries_cliente[1].get_text()
            nome = variables.entries_cliente[2].get_text()
            data = variables.entries_cliente[3].get_text()
            registro = (dni, apel, nome, data)
            if funciones_clientes.es_dni_valido(dni):
                funciones_clientes.insertar_cliente_BD(registro)
                funciones_clientes.carga_lista_clientes(variables.lista_clientes)
                funciones_clientes.limpiarentry(variables.entries_cliente)
            else:
                variables.mensajes_label[0].set_text('ERROR DNI')
        except:
            print("Error en on_botonAltaCliente_clicked")

    def on_botonBajaCliente_clicked(self, widget):
        try:
            dni = variables.entries_cliente[0].get_text()
            if dni != '':
                funciones_clientes.baja_cliente(dni)
                funciones_clientes.carga_lista_clientes(variables.lista_clientes)
                funciones_clientes.limpiarentry(variables.entries_cliente)
            else:
                print('Falta dni u otro error')
        except:
            print("Error en on_botonBajaCliente_clicked")

    #  modificamos cliente
    def on_botonModificarCliente_clicked(self, widget):
        try:
            codigo_cliente = variables.mensajes_label[1].get_text()
            dni = variables.entries_cliente[0].get_text()
            apellidos = variables.entries_cliente[1].get_text()
            nombre = variables.entries_cliente[2].get_text()
            fecha_alta = variables.entries_cliente[3].get_text()
            cliente = (dni, apellidos, nombre, fecha_alta)
            if dni != '':
                funciones_clientes.modificar_cliente(cliente, codigo_cliente)
                funciones_clientes.carga_lista_clientes(variables.lista_clientes)
                funciones_clientes.limpiarentry(variables.entries_cliente)
            else:
                print('Falta el dni')
        except:
            print('Error en on_botonModificarCliente_clicked')

    # controla el valor del deni
    def on_entryDni_focus_out_event(self, widget, dni):
        try:
            dni = variables.entries_cliente[0].get_text()
            if funciones_clientes.es_dni_valido(dni):
                variables.mensajes_label[0].set_text('')
                pass
            else:
                variables.mensajes_label[0].set_text('ERROR')
        except:
            print("Error en on_entryDni_focus_out_event")

    def on_treeClientes_cursor_changed(self, widget):
        try:
            model, iter = variables.tree_clientes.get_selection().get_selected()
            variables.mensajes_label[0].set_text('')
            funciones_clientes.limpiarentry(variables.entries_cliente)
            if iter != None:
                dni_seleccionado = model.get_value(iter, 0)
                apellidos_seleccionados = model.get_value(iter, 1)
                nombre_seleccionado = model.get_value(iter, 2)
                fecha_alta_seleccionada = model.get_value(iter, 3)
                if fecha_alta_seleccionada == None:
                    fecha_alta_seleccionada = ''
                codigo_cliente = funciones_clientes.obtener_id_cliente_por_dni(dni_seleccionado)
                variables.mensajes_label[1].set_text(str(codigo_cliente[0]))
                variables.entries_cliente[0].set_text(str(dni_seleccionado))
                variables.entries_cliente[1].set_text(str(apellidos_seleccionados))
                variables.entries_cliente[2].set_text(str(nombre_seleccionado))
                variables.entries_cliente[3].set_text(str(fecha_alta_seleccionada))
                variables.mensajes_label[4].set_text(str(dni_seleccionado))
                variables.mensajes_label[5].set_text(str(apellidos_seleccionados))
        except:
            print("Error en on_treeClientes_cursor_changed")

    def on_botonCalendario_clicked(self, widget):
        try:
            variables.semaforo = 1
            variables.ventana_calendario.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_calendario.show()
        except:
            print('Error en on_botonCalendario_clicked')

    def on_botonCalendarioCheckIn_clicked(self, widget):
        try:
            variables.semaforo = 2
            variables.ventana_calendario.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_calendario.show()
        except:
            print('Error en on_botonCalendarioCheckIn_clicked')

    def on_botonCalendarioCheckOut_clicked(self, widget):
        try:
            variables.semaforo = 3
            variables.ventana_calendario.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_calendario.show()
        except:
            print('Error en on_botonCalendarioCheckOut_clicked')

    def on_calendario_day_selected_double_click(self, widget):
        try:
            agno, mes, dia = variables.calendario.get_date()
            fecha = "%02d/" % dia + "%02d/" % (mes + 1) + "%s" % agno
            if variables.semaforo == 1:
                variables.entries_cliente[3].set_text(fecha)
            elif variables.semaforo == 2:
                variables.entries_reserva[0].set_text(fecha)
            elif variables.semaforo == 3:
                variables.entries_reserva[1].set_text(fecha)
                funciones_reserva.calcular_noches()
            else:
                pass
            variables.ventana_calendario.hide()
        except Exception as e:
            print(e)
            print('Error en on_calendario_day_selected_double_click')

    # Eventos de las habitaciones

    def on_botonAltaHabitacion_clicked(self, widget):
        try:
            numero_habitacion = variables.entries_habitacion[0].get_text()
            precio_habitacion = variables.entries_habitacion[1].get_text()
            precio_habitacion = precio_habitacion.replace(',', '.')
            precio_habitacion = float(precio_habitacion)
            precio_habitacion = round(precio_habitacion, 2)
            if variables.radiobuttons_tipo_habitacion[0].get_active():
                tipo = 'simple'
            elif variables.radiobuttons_tipo_habitacion[1].get_active():
                tipo = 'doble'
            elif variables.radiobuttons_tipo_habitacion[2].get_active():
                tipo = 'family'
            else:
                pass

            if variables.switch_habitaciones.get_active():
                libre = 'SI'
            else:
                libre = 'NO'
            registro = (numero_habitacion, tipo, precio_habitacion, libre)
            if numero_habitacion is not None:
                funciones_habitacion.insertar_habitacion_BD(registro)
                funciones_habitacion.carga_lista_habitaciones(variables.lista_habitaciones)
                funciones_habitacion.listado_numeros_habitaciones()
                funciones_habitacion.limpiar_entries(variables.entries_habitacion)
            else:
                pass
        except Exception as e:
            print(e)
            print("Error en on_botonAltaHabitacion_clicked")

    def on_treeHabitaciones_cursor_changed(self, widget):
        try:
            model, iter = variables.tree_habitaciones.get_selection().get_selected()
            funciones_habitacion.limpiar_entries(variables.entries_habitacion)
            if iter != None:
                numero_habitacion_seleccionado = model.get_value(iter, 0)
                tipo_seleccionado = model.get_value(iter, 1)
                precio_seleccionado = model.get_value(iter, 2)
                precio_seleccionado = round(precio_seleccionado, 2)
                variables.entries_habitacion[0].set_text(str(numero_habitacion_seleccionado))
                variables.entries_habitacion[1].set_text(str(precio_seleccionado))
                if tipo_seleccionado == str('simple'):
                    variables.radiobuttons_tipo_habitacion[0].set_active(True)
                elif tipo_seleccionado == str('doble'):
                    variables.radiobuttons_tipo_habitacion[1].set_active(True)
                elif tipo_seleccionado == str('family'):
                    variables.radiobuttons_tipo_habitacion[2].set_active(True)
                estado_libre_seleccionado = model.get_value(iter, 3)
                if estado_libre_seleccionado == str('SI'):
                    variables.switch_habitaciones.set_active(True)
                else:
                    variables.switch_habitaciones.set_active(False)
        except Exception as e:
            print(e)
            print("Error en on_treeHabitaciones_cursor_changed")

    def on_botonBajaHabitacion_clicked(self, widget):
        try:
            numero_habitacion = variables.entries_habitacion[0].get_text()
            if numero_habitacion != '':
                funciones_habitacion.baja_habitacion(numero_habitacion)
                funciones_habitacion.limpiar_entries(variables.entries_habitacion)
                funciones_habitacion.carga_lista_habitaciones(variables.lista_habitaciones)
            else:
                pass
        except:
            print('Error en on_botonBajaHabitacion_clicked')

    def on_botonModificarHabitacion_clicked(self, widget):
        try:
            numero_habitacion = variables.entries_habitacion[0].get_text()
            prezo = variables.entries_habitacion[1].get_text()
            if variables.switch_habitaciones.get_active():
                libre = 'SI'
            else:
                libre = 'NO'
            if variables.radiobuttons_tipo_habitacion[0].get_active():
                tipo = 'simple'
            elif variables.radiobuttons_tipo_habitacion[1].get_active():
                tipo = 'doble'
            elif variables.radiobuttons_tipo_habitacion[2].get_active():
                tipo = 'family'
            habitacion = (prezo, tipo, libre)
            if numero_habitacion != '':
                funciones_habitacion.modificar_habitacion(habitacion, numero_habitacion)
                funciones_habitacion.carga_lista_habitaciones(variables.lista_habitaciones)
                funciones_habitacion.limpiar_entries(variables.entries_habitacion)
            else:
                print('Falta el número de la habitación')
        except Exception as e:
            print(e)
            print('Error en on_botonModificarHabitacion_clicked')

    def on_panel_select_page(self, widget):
        try:
            funciones_habitacion.listado_numeros_habitaciones()
        except:
            print("Error en on_panel_select_page")

    def on_botonClientesToolBar_clicked(self, widget):
        try:
            panelactual = variables.panel.get_current_page()
            if panelactual != 0:
                variables.panel.set_current_page(0)
            else:
                pass
        except:
            print("Error en on_botonClientesToolBar_clicked")

    def on_botonReservasToolBar_clicked(self, widget):
        try:
            panel_actual = variables.panel.get_current_page()
            if panel_actual != 1:
                variables.panel.set_current_page(1)
                funciones_habitacion.listado_numeros_habitaciones()
            else:
                pass
        except:
            print("Error en on_botonReservasToolBar_clicked")

    def on_botonHabitacionesToolBar_clicked(self, widget):
        try:
            panelactual = variables.panel.get_current_page()
            if panelactual != 2:
                variables.panel.set_current_page(2)
            else:
                pass
        except:
            print("Error en on_botonHabitacionesToolBar_clicked")

    def on_botonCalculadoraToolBar_clicked(self, widget):
        try:
            os.system('/snap/bin/gnome-calculator')
        except:
            print('Error en on_botonCalculadoraToolBar_clicked')

    def on_botonRefrescarToolBar_clicked(self, widget):
        try:
            funciones_habitacion.limpiar_entries(variables.entries_habitacion)
            funciones_clientes.limpiarentry(variables.entries_cliente)
            funciones_reserva.limpiarentry(variables.entries_reserva)
            facturacion.limpiar_labels_factura(variables.labels_factura)
        except:
            print('Error en on_botonRefrescarToolBar_clicked')

    # Eventos backup

    def on_botonBackupToolBar_clicked(self, widget):
        try:
            variables.ventana_backup.show()
        except:
            print("Error en on_botonBackupToolBar_clicked")

    def on_botonBackupVentana_clicked(self, widget):
        try:
            conexion.Conexion().cerrarbbdd()
            backup = 'backup.zip'
            destino = str(variables.ventana_backup.get_filename())
            if os.path.exists(destino):
                pass
            else:
                os.system('mkdir ' + destino)
                os.system('chmod 0777 ' + destino)
            copia = zipfile.ZipFile(backup, 'w')
            copia.write('empresa.sqlite', compress_type=zipfile.ZIP_DEFLATED)
            copia.close()
            neobackup = str(datetime.now()) + str(backup)
            os.rename(backup, neobackup)
            shutil.move(neobackup, destino)
            Conexion().abrirbbdd()
        except Exception as e:
            Conexion().abrirbbdd()
            print(e)
            print("Error en on_botonBackupVentana_clicked")

    def on_botonSalirVentanaBackup_clicked(self, widget):
        try:
            variables.ventana_backup.connect('delete_event', lambda w, e: w.hide() or True)
            variables.ventana_backup.hide()
        except:
            print('Error en on_botonSalirVentanaBackup_clicked')

    def on_menuBarBackup_activate(self, widget):
        try:
            variables.ventana_restaurar_backup.show()
        except:
            print("Error en on_menuBarBackup_activate")

    def on_botonRestaurarBackup_clicked(self, widget):
        try:
            Conexion().cerrarbbdd()
            fichero = variables.ventana_restaurar_backup.get_filename()
            copia = zipfile.ZipFile(fichero, 'r')
            os.system("rm empresa.sqlite")
            copia.extract("empresa.sqlite")
            copia.close()
            Conexion().abrirbbdd()
        except Exception as e:
            Conexion().abrirbbdd()
            print(e)
            print("Error en on_botonRestaurarBackup_clicked")

    def on_botonSalirRestaurarBackup_clicked(self, widget):
        try:
            variables.ventana_restaurar_backup.connect('delete-event', lambda w, e: w.hide() or True)
            variables.ventana_restaurar_backup.hide()
        except:
            print('Error en on_botonSalirRestaurarBackup_clicked')

    # Eventos reservas

    def on_comboBoxHabitacionesReserva_changed(self, widget):
        try:
            index = variables.combo_habitaciones.get_active()
            model = variables.combo_habitaciones.get_model()
            if index != -1:
                item = model[index]
                variables.numero_habitacion_reserva = item[0]
        except Exception as e:
            print(e)
            print('Error en on_comboBoxHabitacionesReserva_changed')

    def on_botonAltaReservas_clicked(self, widget):
        try:
            if variables.reserva == 1:
                dni_reserva = variables.mensajes_label[4].get_text()
                check_in = variables.entries_reserva[0].get_text()
                check_out = variables.entries_reserva[1].get_text()
                noches = int(variables.mensajes_label[2].get_text())
                reserva = (dni_reserva, variables.numero_habitacion_reserva, check_in, check_out, noches, 'SI')
                if funciones_reserva.esta_libre(variables.numero_habitacion_reserva) and dni_reserva != '':
                    funciones_reserva.insertar_reserva(reserva)
                    funciones_reserva.recargar_lista_reservas()
                    funciones_habitacion.cambiar_estado_habitacion('NO', variables.numero_habitacion_reserva)
                    funciones_habitacion.carga_lista_habitaciones(variables.lista_habitaciones)
                    funciones_habitacion.limpiar_entries(variables.entries_habitacion)
                    funciones_reserva.limpiarentry(variables.entries_reserva)
                else:
                    print('Habitación ocupada o falta el dni del cliente')
        except Exception as e:
            print(e)
            print('Error en on_botonAltaReservas_clicked')

    def on_botonRefrescarComboHabitaciones_clicked(self, widget):
        try:
            variables.combo_habitaciones.set_active(-1)
            funciones_habitacion.listado_numeros_habitaciones()
        except:
            print('Error en on_botonRefrescarComboHabitaciones_clicked')

    def on_treeReservas_cursor_changed(self, widget):
        try:
            model, iter = variables.tree_reservas.get_selection().get_selected()
            funciones_reserva.limpiarentry(variables.entries_reserva)
            if iter != None:
                variables.codigo_reserva = model.get_value(iter, 0)
                dni_seleccionado = model.get_value(iter, 1)
                apellidos_seleccionados = funciones_reserva.obtener_apellidos_cliente_por_dni(str(dni_seleccionado))
                nombre_seleccionado = funciones_reserva.obtener_nombre_cliente_por_dni(str(dni_seleccionado))
                numero_habitacion_seleccionado = model.get_value(iter, 2)
                listado_habitaciones = funciones_habitacion.listado_habitaciones_reserva()
                m = -1
                for i, x in enumerate(listado_habitaciones):
                    if str(x[0]) == str(numero_habitacion_seleccionado):
                        m = i
                variables.combo_habitaciones.set_active(m)
                check_in_seleccionado = model.get_value(iter, 3)
                check_out_seleccionado = model.get_value(iter, 4)
                numero_noches_seleccionadas = model.get_value(iter, 5)
                variables.mensajes_label[4].set_text(str(dni_seleccionado))
                variables.mensajes_label[5].set_text(str(apellidos_seleccionados[0]))
                variables.mensajes_label[2].set_text(str(numero_noches_seleccionadas))
                variables.entries_reserva[0].set_text(str(check_in_seleccionado))
                variables.entries_reserva[1].set_text(str(check_out_seleccionado))
                variables.datos_factura = (variables.codigo_reserva,
                                           numero_noches_seleccionadas,
                                           dni_seleccionado,
                                           numero_habitacion_seleccionado,
                                           check_out_seleccionado,
                                           funciones_reserva.obtener_precio_habitacion_por_numero_habitacion(numero_habitacion_seleccionado))
                facturacion.obtener_factura(dni_seleccionado,
                                            apellidos_seleccionados,
                                            nombre_seleccionado,
                                            numero_habitacion_seleccionado,
                                            check_out_seleccionado,
                                            numero_noches_seleccionadas)
        except Exception as e:
            print(e)
            print('Error en on_treeReservas_cursor_changed')

    def on_botonModificarReservas_clicked(self, widget):
        try:
            codigo = variables.codigo_reserva
            check_in = variables.entries_reserva[0].get_text()
            check_out = variables.entries_reserva[1].get_text()
            noches = int(variables.mensajes_label[2].get_text())
            reserva = (check_in, check_out, noches)
            funciones_reserva.modificar_reserva(reserva, codigo)
            funciones_reserva.recargar_lista_reservas()
            funciones_reserva.limpiarentry(variables.entries_reserva)
        except Exception as e:
            print(e)
            print('Error en on_botonModificarReservas_clicked')

    def on_botonCheckout_clicked(self, widget):
        try:
            funciones_habitacion.cambiar_estado_habitacion('SI', variables.numero_habitacion_reserva)
            funciones_habitacion.carga_lista_habitaciones(variables.lista_habitaciones)
            funciones_reserva.cambiar_estado_reserva(variables.codigo_reserva, 'NO')
            funciones_reserva.recargar_lista_reservas()
        except Exception as e:
            print(e)
            print('Error en on_botonCheckout_clicked')

    def on_switchReservas_state_set(self, widget, value):
        funciones_reserva.recargar_lista_reservas()

    def on_botonImprimirFactura_clicked(self, widget):
        try:
            impresion.factura(variables.datos_factura)
        except:
            print('Error en on_botonImprimirFactura_clicked')

    def on_menuBarImportarClientes_activate(self, widget):
        try:
            fichero_excel = xlrd.open_workbook("clientes.xls")
            hoja_clientes = fichero_excel.sheet_by_index(0)
            numero_filas_clientes = hoja_clientes.nrows
            numero_columnas_clientes = hoja_clientes.ncols

            for i in range(numero_filas_clientes):
                celdas_cliente = []
                if i > 0:
                    for j in range(numero_columnas_clientes):
                        celdas_cliente.append(hoja_clientes.cell(i, j))
                    funciones_clientes.insertar_cliente_excel_BD(celdas_cliente)
                    funciones_clientes.carga_lista_clientes(variables.lista_clientes)
        except Exception as e:
            print(e)
            print('Error en on_menuBarImportarClientes_activate')

    def on_menuBarExportarClientes_activate(self, widget):
        try:
            estilo_cabecera = xlwt.easyxf('font: name Times New Roman, colour red, bold on')
            estilo_celda = xlwt.easyxf(num_format_str='DD-MM-YY')
            fichero_excel = xlwt.Workbook()

            hoja_excel = fichero_excel.add_sheet('NuevoClientes', cell_overwrite_ok=True)
            hoja_excel.write(0, 0, 'DNI', estilo_cabecera)
            hoja_excel.write(0, 1, 'APELIDOS', estilo_cabecera)
            hoja_excel.write(0, 2, 'NOMBRE', estilo_cabecera)
            hoja_excel.write(0, 3, 'FECHA_ALTA', estilo_cabecera)

            listado_clientes: List[List] = funciones_clientes.obtener_listado_clientes()

            for i in range(len(listado_clientes)):
                for j in range(len(listado_clientes[0])):
                    hoja_excel.write(i, j, listado_clientes[i][j], estilo_celda)
            fichero_excel.save('clientes_exportados.xls')
        except Exception as e:
            print(e)
            print('Error en on_menuBarExportarClientes_activate')