import os
import shutil

import gi

import conexion
import facturacion
import funciones_habitacion
import funciones_reserva
import funcionescli
import funcionesvar
import impresion
import variables

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class Eventos():

    # eventos generales
    def on_acercade_activate(self, widget):
        try:
            variables.venacercade.show()
        except:
            print('error abrira acerca de')

    def on_btnCerrarabout_clicked(self, widget):
        try:
            variables.venacercade.connect('delete-event', lambda w, e: w.hide() or True)
            variables.venacercade.hide()
        except:
            print('error abrir calendario')

    def on_menuBarsalir_activate(self, widget):
        try:
            self.salir()
        except:
            print('salir en menubar')

    def salir(self):
        try:
            conexion.Conexion.cerrarbbdd(self)
            funcionesvar.cerrartimer()
            Gtk.main_quit()
        except Exception as e:
            print(e)
            print('error función salir')

    def on_venPrincipal_destroy(self, widget):
        self.salir()

    def on_btnSalirtool_clicked(self, widget):
        variables.vendialogsalir.show()

    def on_btnCancelarsalir_clicked(self, widget):
        variables.vendialogsalir.connect('delete-event', lambda w, e: w.hide() or True)
        variables.vendialogsalir.hide()

    def on_btnAceptarsalir_clicked(self, widget):
        self.salir()

    """
    Eventos Clientes
    """

    def on_btnAltacli_clicked(self, widget):
        try:
            dni = variables.entries_cliente[0].get_text()
            apel = variables.entries_cliente[1].get_text()
            nome = variables.entries_cliente[2].get_text()
            data = variables.entries_cliente[3].get_text()
            registro = (dni, apel, nome, data)
            if funcionescli.es_dni_valido(dni):
                funcionescli.insertar_cliente(registro)
                funcionescli.carga_lista_clientes(variables.listclientes)
                funcionescli.limpiarentry(variables.entries_cliente)
            else:
                variables.menslabel[0].set_text('ERROR DNI')
        except:
            print("Error alta cliente")

    def on_btnBajacli_clicked(self, widget):
        try:
            dni = variables.entries_cliente[0].get_text()
            if dni != '':
                funcionescli.baja_cliente(dni)
                funcionescli.carga_lista_clientes(variables.listclientes)
                funcionescli.limpiarentry(variables.entries_cliente)
            else:
                print('falta dni u otro error')
        except:
            print("error en botón baja cliente")

    #  modificamos cliente
    def on_btnModifcli_clicked(self, widget):
        try:
            cod = variables.menslabel[1].get_text()
            dni = variables.entries_cliente[0].get_text()
            apel = variables.entries_cliente[1].get_text()
            nome = variables.entries_cliente[2].get_text()
            data = variables.entries_cliente[3].get_text()
            registro = (dni, apel, nome, data)
            if dni != '':
                funcionescli.modificar_cliente(registro, cod)
                funcionescli.carga_lista_clientes(variables.listclientes)
                funcionescli.limpiarentry(variables.entries_cliente)
            else:
                print('falta el dni')
        except:
            print('error en botón modificar')

    # controla el valor del deni
    def on_entDni_focus_out_event(self, widget, dni):
        try:
            dni = variables.entries_cliente[0].get_text()
            if funcionescli.es_dni_valido(dni):
                variables.menslabel[0].set_text('')
                pass
            else:
                variables.menslabel[0].set_text('ERROR')
        except:
            print("Error alta cliente en out focus")

    def on_treeClientes_cursor_changed(self, widget):
        try:
            model, iter = variables.treeclientes.get_selection().get_selected()
            # model es el modelo de la tabla de datos
            # iter es el número que identifica a la fila que marcamos
            variables.menslabel[0].set_text('')
            funcionescli.limpiarentry(variables.entries_cliente)
            if iter != None:
                sdni = model.get_value(iter, 0)
                sapel = model.get_value(iter, 1)
                snome = model.get_value(iter, 2)
                sdata = model.get_value(iter, 3)
                if sdata == None:
                    sdata = ''
                cod = funcionescli.obtener_id_cliente_por_dni(sdni)
                variables.menslabel[1].set_text(str(cod[0]))
                variables.entries_cliente[0].set_text(str(sdni))
                variables.entries_cliente[1].set_text(str(sapel))
                variables.entries_cliente[2].set_text(str(snome))
                variables.entries_cliente[3].set_text(str(sdata))
                variables.menslabel[4].set_text(str(sdni))
                variables.menslabel[5].set_text(str(sapel))
        except:
            print("error carga cliente")

    def on_btnCalendar_clicked(self, widget):
        try:
            variables.semaforo = 1
            variables.vencalendar.connect('delete-event', lambda w, e: w.hide() or True)
            variables.vencalendar.show()

        except:
            print('error abrir calendario')

    def on_btnCalendarResIn_clicked(self, widget):
        try:
            variables.semaforo = 2
            variables.vencalendar.connect('delete-event', lambda w, e: w.hide() or True)
            variables.vencalendar.show()
        except:
            print('error abrir calendario')

    def on_btnCalendarResOut_clicked(self, widget):
        try:
            variables.semaforo = 3
            variables.vencalendar.connect('delete-event', lambda w, e: w.hide() or True)
            variables.vencalendar.show()
        except:
            print('error abrir calendario')

    def on_Calendar_day_selected_double_click(self, widget):
        try:
            agno, mes, dia = variables.calendar.get_date()
            fecha = "%s/" % dia + "%s/" % (mes + 1) + "%s" % agno
            if variables.semaforo == 1:
                variables.entries_cliente[3].set_text(fecha)
            elif variables.semaforo == 2:
                variables.filareserva[2].set_text(fecha)
            elif variables.semaforo == 3:
                variables.filareserva[3].set_text(fecha)
                funciones_reserva.calculardias()
            else:
                pass
            # variables.semaforo = 0
            variables.vencalendar.hide()
        except:
            print('error al coger la fecha')

    # Eventos de las habitaciones

    def on_btnAltahab_clicked(self, widget):
        try:
            numhab = variables.filahab[0].get_text()
            prezohab = variables.filahab[1].get_text()
            prezohab = prezohab.replace(',', '.')
            prezohab = float(prezohab)
            prezohab = round(prezohab, 2)
            if variables.filarbt[0].get_active():
                tipo = 'simple'
            elif variables.filarbt[1].get_active():
                tipo = 'doble'
            elif variables.filarbt[2].get_active():
                tipo = 'family'
            else:
                pass

            if variables.switch.get_active():
                libre = 'SI'
            else:
                libre = 'NO'
            registro = (numhab, tipo, prezohab, libre)
            if numhab is not None:
                funciones_habitacion.insertar_habitacion(registro)
                funciones_habitacion.carga_lista_habitaciones(variables.listhab)
                funciones_habitacion.listado_numeros_habitaciones()
                funciones_habitacion.limpiar_entries(variables.filahab)
            else:
                pass
        except Exception as e:
            print(e)
            print("Error alta habitacion")

    def on_treeHab_cursor_changed(self, widget):
        try:
            model, iter = variables.treehab.get_selection().get_selected()
            # model es el modelo de la tabla de datos
            # iter es el número que identifica a la fila que marcamos
            funciones_habitacion.limpiar_entries(variables.filahab)
            if iter != None:
                snumhab = model.get_value(iter, 0)
                stipo = model.get_value(iter, 1)
                sprezo = model.get_value(iter, 2)
                sprezo = round(sprezo, 2)
                variables.filahab[0].set_text(str(snumhab))
                variables.filahab[1].set_text(str(sprezo))
                if stipo == str('simple'):
                    variables.filarbt[0].set_active(True)
                elif stipo == str('doble'):
                    variables.filarbt[1].set_active(True)
                elif stipo == str('family'):
                    variables.filarbt[2].set_active(True)
                slibre = model.get_value(iter, 3)
                if slibre == str('SI'):
                    variables.switch.set_active(True)
                else:
                    variables.switch.set_active(False)
        except Exception as e:
            print(e)
            print("error carga habitacion")

    def on_btnBajahab_clicked(self, widget):
        try:
            numhab = variables.filahab[0].get_text()
            if numhab != '':
                funciones_habitacion.baja_habitacion(numhab)
                funciones_habitacion.limpiar_entries(variables.filahab)
                funciones_habitacion.carga_lista_habitaciones(variables.listhab)
            else:
                pass
        except:
            print('borrar baja hab')

    def on_btnModifhab_clicked(self, widget):
        try:
            numero_habitacion = variables.filahab[0].get_text()
            prezo = variables.filahab[1].get_text()
            if variables.switch.get_active():
                libre = 'SI'
            else:
                libre = 'NO'
            if variables.filarbt[0].get_active():
                tipo = 'simple'
            elif variables.filarbt[1].get_active():
                tipo = 'doble'
            elif variables.filarbt[2].get_active():
                tipo = 'family'
            habitacion = (prezo, tipo, libre)
            if numero_habitacion != '':
                funciones_habitacion.modificar_habitacion(habitacion, numero_habitacion)
                funciones_habitacion.carga_lista_habitaciones(variables.listhab)
                funciones_habitacion.limpiar_entries(variables.filahab)
            else:
                print('falta el numhab')
        except Exception as e:
            print(e)
            print('error modif hab')

    # eventos de los botones del toolbar
    def on_Panel_select_page(self, widget):
        try:
            funciones_habitacion.listado_numeros_habitaciones()
        except:
            print("error botón cliente barra herramientas")

    def on_btnClitool_clicked(self, widget):
        try:
            panelactual = variables.panel.get_current_page()
            if panelactual != 0:
                variables.panel.set_current_page(0)
            else:
                pass
        except:
            print("error botón cliente barra herramientas")

    def on_btnReservatool_clicked(self, widget):
        try:
            panelactual = variables.panel.get_current_page()
            if panelactual != 1:
                variables.panel.set_current_page(1)
                funciones_habitacion.listado_numeros_habitaciones()
            else:
                pass
        except:
            print("error botón cliente barra herramientas")

    def on_btnHabita_clicked(self, widget):
        try:
            panelactual = variables.panel.get_current_page()
            if panelactual != 2:
                variables.panel.set_current_page(2)
            else:
                pass
        except:
            print("error botón habitacion barra herramientas")

    def on_btnCalc_clicked(self, widget):
        try:
            os.system('/snap/bin/gnome-calculator')
        except:
            print('error lanzar calculadora')

    def on_btnRefresh_clicked(self, widget):
        try:
            funciones_habitacion.limpiar_entries(variables.filahab)
            funcionescli.limpiarentry(variables.entries_cliente)
            funciones_reserva.limpiarentry(variables.filareserva)
            facturacion.limpiar_labels_factura(variables.labels_factura)
        except:
            print('error referes')

    def on_btnBackup_clicked(self, widget):
        try:
            variables.filechooserbackup.show()
            variables.neobackup = funcionesvar.backup()
            variables.neobackup = str(os.path.abspath(variables.neobackup))
            print(variables.neobackup)

        except:
            print('error abrir file choorse backup')

    def on_btnGrabarbackup_clicked(self, widget):
        try:
            destino = variables.filechooserbackup.get_filename()
            destino = destino + '/'
            variables.menslabel[3].set_text(str(destino))
            if shutil.move(str(variables.neobackup), str(destino)):
                variables.menslabel[3].set_text('Copia de Seguridad Creada')
        except:
            print('error dselect fichero')

    def on_btnCancelfilechooserbackup_clicked(self, widget):
        try:
            variables.filechooserbackup.connect('delete-event', lambda w, e: w.hide() or True)
            variables.filechooserbackup.hide()
        except:
            print('error cerrar file chooser')

    ## reservas

    def on_cmbNumres_changed(self, widget):
        try:
            index = variables.cmbhab.get_active()
            model = variables.cmbhab.get_model()
            if index != -1:
                item = model[index]
                variables.numhabres = item[0]
        except Exception as e:
            print(e.with_traceback())
            print('error mostrar habitacion combo')

    def on_btnAltares_clicked(self, widget):
        try:
            if variables.reserva == 1:
                dni_reserva = variables.menslabel[4].get_text()
                check_in = variables.filareserva[2].get_text()
                check_out = variables.filareserva[3].get_text()
                noches = int(variables.menslabel[2].get_text())
                reserva = (dni_reserva, variables.numhabres, check_in, check_out, noches, 'SI')
                if funciones_reserva.esta_libre(variables.numhabres) and dni_reserva != '':
                    funciones_reserva.insertar_reserva(reserva)
                    funciones_reserva.recargar_lista_reservas()
                    funciones_habitacion.cambiar_estado_habitacion('NO', variables.numhabres)
                    funciones_habitacion.carga_lista_habitaciones(variables.listhab)
                    funciones_habitacion.limpiar_entries(variables.filahab)
                    funciones_reserva.limpiarentry(variables.filareserva)
                else:
                    print('Habitación ocupada o falta el dni del cliente')
        except Exception as e:
            print(e)
            print('error en alta res')

    def on_btnRefreshcmbhab_clicked(self, widget):
        try:
            variables.cmbhab.set_active(-1)
            funciones_habitacion.listado_numeros_habitaciones()
        except:
            print('error limpiar combo hotel')

    def on_treeReservas_cursor_changed(self, widget):
        try:
            model, iter = variables.treereservas.get_selection().get_selected()
            funciones_reserva.limpiarentry(variables.filareserva)
            if iter != None:
                variables.cod = model.get_value(iter, 0)
                dni_seleccionado = model.get_value(iter, 1)
                apellidos_seleccionados = funciones_reserva.obtener_apellidos_cliente_por_dni(str(dni_seleccionado))
                nombre_seleccionado = funciones_reserva.obtener_nombre_cliente_por_dni(str(dni_seleccionado))
                numero_habitacion_seleccionado = model.get_value(iter, 2)
                listado_habitaciones = funciones_habitacion.listado_habitaciones_reserva()
                m = -1
                for i, x in enumerate(listado_habitaciones):
                    if str(x[0]) == str(numero_habitacion_seleccionado):
                        m = i
                variables.cmbhab.set_active(m)
                check_in_seleccionado = model.get_value(iter, 3)
                check_out_seleccionado = model.get_value(iter, 4)
                numero_noches_seleccionadas = model.get_value(iter, 5)
                variables.menslabel[4].set_text(str(dni_seleccionado))
                variables.menslabel[5].set_text(str(apellidos_seleccionados[0]))
                variables.menslabel[2].set_text(str(numero_noches_seleccionadas))
                variables.filareserva[2].set_text(str(check_in_seleccionado))
                variables.filareserva[3].set_text(str(check_out_seleccionado))
                facturacion.obtener_factura(dni_seleccionado,
                                            apellidos_seleccionados,
                                            nombre_seleccionado,
                                            numero_habitacion_seleccionado,
                                            check_out_seleccionado,
                                            numero_noches_seleccionadas)
        except Exception as e:
            print(e)
            print('Error en on_treeReservas_cursor_changed')

    '''
    def on_botonModificarReservas_clicked(self, widget):
        try:
            codigo = variables.cod
            check_in = variables.filareserva[2].get_text()
            check_out = variables.filareserva[3].get_text()
            noches = int(variables.menslabel[2].get_text())
            reserva = (check_in, check_out, noches)

            funciones_reserva.modificar_reserva(reserva, codigo)
            funciones_reserva.recargar_lista_reservas()
            funciones_reserva.limpiarentry(variables.filareserva)
        except Exception as e:
            print(e)
            print('Error en on_botonModificarReservas_clicked')
    '''

    def on_botonCheckout_clicked(self, widget):
        try:
            funciones_habitacion.cambiar_estado_habitacion('SI', variables.numhabres)
            funciones_habitacion.carga_lista_habitaciones(variables.listhab)
            funciones_reserva.cambiar_estado_reserva(variables.cod, 'NO')
            funciones_reserva.recargar_lista_reservas()
        except Exception as e:
            print(e)
            print('Error en on_botonCheckout_clicked')

    def on_switchReservas_state_set(self, widget, value):
        funciones_reserva.recargar_lista_reservas()

    def on_botonImprimirFactura_clicked(self, widget):
        try:
            impresion.factura()
        except:
            print('Error en on_botonImprimirFactura_clicked')