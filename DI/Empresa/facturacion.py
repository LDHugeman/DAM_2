# coding=utf-8
'''Módulo que gestiona la previsualización de la factura.
'''
import funciones_reserva
import funciones_servicios
import variables


def limpiar_labels_factura(labels_factura):
    '''
    Vacía los labels tras ejecutar un evento.
        :param labels_factura: contiene un listado de labels de la factura
        :return: void
    '''
    for i in range(len(labels_factura)):
        labels_factura[i].set_text('')


def visualizar_factura(cliente, numero_habitacion, check_out, codigo_reserva):
    '''
        Muestra los datos de la factura.
            :param cliente: Cliente de la factura
            :param numero_habitacion: numero de habitación del cliente
            :param check_out: fecha de la factura
            :param codigo_reserva: Codigo de la reserva de la factura
            :return: void
        '''
    try:

        servicios = funciones_servicios.obtener_listado_servicios_de_una_reserva(codigo_reserva)
        variables.labels_factura[0].set_text(str(cliente.dni))
        variables.labels_factura[1].set_text(str(cliente.apellidos))
        variables.labels_factura[2].set_text(str(cliente.nombre))
        variables.labels_factura[3].set_text(str(codigo_reserva))
        variables.labels_factura[4].set_text(str(numero_habitacion))
        variables.labels_factura[5].set_text(str(check_out))

        precio_habitacion = funciones_reserva.obtener_precio_habitacion_por_numero_habitacion(numero_habitacion)
        numero_noches = funciones_reserva.obtener_noches_por_codigo_reserva(codigo_reserva)
        precio_noches_total = precio_habitacion * float(numero_noches)
        precio_servicios_total = 0
        for servicio in servicios:
            precio_servicios_total += servicio.precio * float(numero_noches)
        subtotal = precio_noches_total + precio_servicios_total
        variables.labels_factura[6].set_text(str(subtotal) + "€")

        iva_noches = precio_noches_total * 10 / 100
        iva_total_servicios = 0
        for servicio in servicios:
            iva_total_servicios += servicio.calcular_iva(numero_noches)

        iva_total = iva_noches + iva_total_servicios
        iva_total = round(iva_total, 2)
        variables.labels_factura[7].set_text(str(iva_total) + "€")

        total = subtotal + iva_total
        variables.labels_factura[8].set_text(str(total) + "€")
    except Exception as e:
        print(e)
        print('Error en obtener_factura')
