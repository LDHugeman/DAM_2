# coding=utf-8
'''Módulo que gestiona la previsualización de la factura.
'''
import funciones_reserva
import variables


def limpiar_labels_factura(labels_factura):
    '''
    Vacía los labels tras ejecutar un evento.
        :param labels_factura: contiene un listado de labels de la factura
        :return: void
    '''
    for i in range(len(labels_factura)):
        labels_factura[i].set_text('')


def obtener_factura(dni, apellidos, nombre, numero_habitacion, check_out, noches):
    '''
        Muestra los datos de la factura.
            :param dni: dni del cliente
            :param apellidos: apellidos del cliente
            :param nombre: nombre del cliente
            :param numero_habitacion: numero de habitación del cliente
            :param check_out: fecha de la factura
            :param noches: número de noches
            :return: void
        '''
    try:
        variables.labels_factura[0].set_text(str(dni))
        variables.labels_factura[1].set_text(str(apellidos[0]))
        variables.labels_factura[2].set_text(str(nombre[0]))
        variables.labels_factura[3].set_text(str(variables.codigo_reserva))
        variables.labels_factura[4].set_text(str(numero_habitacion))
        variables.labels_factura[5].set_text(str(check_out))
        variables.labels_factura[6].set_text(str(noches))
        precio_habitacion = funciones_reserva.obtener_precio_habitacion_por_numero_habitacion(numero_habitacion)
        variables.labels_factura[7].set_text(str(precio_habitacion[0]))
        total = float(str(noches)) * float(precio_habitacion[0])
        variables.labels_factura[8].set_text(str(total))
    except Exception as e:
        print(e)
        print('Error en obtener_factura')
