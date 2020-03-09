# coding=utf-8
import funciones_clientes
import variables
from objetos import Reserva

'''Módulo que gestiona el pdf de la factura.
'''
import os

from reportlab.lib.pagesizes import A4
from reportlab.pdfgen import canvas

import funciones_servicios


def basico():
    '''
    Genera el pdf con el encabezado y el pie de la factura
        :return: void
    '''
    try:
        factura = canvas.Canvas('prueba.pdf', pagesize=A4)
        texto_bienvenida = 'Bienvenido a nuestro hotel'
        cif = 'CIF:00000000A'
        factura.drawImage('./img/logohotel.png', 475, 675, width=64, height=64)
        factura.setFont('Helvetica-Bold', size=16)
        factura.drawString(250, 780, 'HOTEL LITE')
        factura.setFont('Times-Italic', size=10)
        factura.drawString(240, 765, texto_bienvenida)
        factura.drawString(260, 755, cif)
        factura.line(50, 670, 540, 670)
        texto_pie = 'Hotel Lite, Tlfo = 986291132 e-mail = info@hotellite.com'
        factura.setFont('Times-Italic', size=8)
        factura.drawString(170, 20, texto_pie)
        factura.line(50, 30, 540, 30)
        return factura
    except Exception as e:
        print(e)
        print('Error en basico')


def factura(reserva: Reserva):
    """
    Genera una factura con los datos del cliente y el importe de los distintos servicios
        :param reserva: Datos de la reserva
        :return: void
    """
    try:
        factura = basico()
        factura.setTitle('FACTURA')

        factura.setFont('Helvetica-Bold', size=8)
        numero_factura = 'Número de Factura:'
        factura.drawString(50, 735, numero_factura)
        factura.setFont('Helvetica', size=8)
        factura.drawString(140, 735, str(reserva.codigo_reserva))

        factura.setFont('Helvetica-Bold', size=8)
        fecha_factura = 'Fecha Factura:'
        factura.drawString(300, 735, fecha_factura)
        factura.setFont('Helvetica', size=8)
        factura.drawString(360, 735, str(reserva.check_out))

        factura.setFont('Helvetica-Bold', size=8)
        dni_cliente = 'DNI CLIENTE:'
        factura.drawString(50, 710, dni_cliente)
        factura.setFont('Helvetica', size=8)
        factura.drawString(120, 710, str(reserva.cliente.dni))

        factura.setFont('Helvetica-Bold', size=8)
        numero_habitacion = 'Nº de Habitación:'
        factura.drawString(300, 710, numero_habitacion)
        factura.setFont('Helvetica', size=8)
        factura.drawString(380, 710, str(reserva.habitacion.numero))

        factura.setFont('Helvetica-Bold', size=8)
        apellidos_cliente = 'APELLIDOS:'
        factura.drawString(50, 680, apellidos_cliente)
        factura.setFont('Helvetica', size=8)
        factura.drawString(120, 680, reserva.cliente.apellidos)

        factura.setFont('Helvetica-Bold', size=8)
        nombre_cliente = 'NOMBRE:'
        factura.drawString(300, 680, nombre_cliente)
        factura.setFont('Helvetica', size=8)
        factura.drawString(350, 680, reserva.cliente.nombre)

        alojamiento = ['Noches', str(reserva.numero_noches), str(reserva.habitacion.precio),
                       str(reserva.precio_reserva()) + " €"]
        servicios = funciones_servicios.obtener_listado_servicios_de_una_reserva(reserva.codigo_reserva)

        cabecera = ['CONCEPTO', 'UNIDADES', 'PRECIO/UNIDAD', 'TOTAL']
        lineas_servicios = []
        for servicio in servicios:
            lineas_servicios.append(
                [servicio.concepto, "", str(servicio.precio),
                 str(float(servicio.precio) * float(reserva.numero_noches)) + " €"])
        pintar_cabecera(factura, cabecera, 73, 655)
        pintar_noches(factura, 620, alojamiento)
        y = 620 - 30
        for linea in lineas_servicios:
            pintar_datos(factura, linea, 75, y)
            y -= 30
        factura.line(50, 645, 540, 645)

        factura.line(50, 250, 540, 250)
        factura.setFont('Helvetica-Bold', size=8)
        subtotal = 'Subtotal: '
        factura.drawString(430, 230, subtotal)
        factura.setFont('Helvetica', size=8)
        factura.drawString(470, 230, variables.labels_factura[6].get_text())

        factura.setFont('Helvetica-Bold', size=8)
        iva = 'Iva: '
        factura.drawString(430, 215, iva)
        factura.setFont('Helvetica', size=8)
        factura.drawString(470, 215, variables.labels_factura[7].get_text())

        factura.setFont('Helvetica-Bold', size=8)
        total = 'Total: '
        factura.drawString(430, 200, total)
        factura.setFont('Helvetica', size=8)
        factura.drawString(470, 200, variables.labels_factura[8].get_text())

        factura.showPage()
        factura.save()
        directorio_actual = os.getcwd()
        os.system('/usr/bin/xdg-open ' + directorio_actual + '/prueba.pdf')
    except Exception as e:
        print(e)
        print('Error en factura')


def pintar_datos(factura, linea_servicios, x, y):
    for item in linea_servicios:
        pintar_dato(factura, x, y, item)
        x += 130


def pintar_dato(factura, x, y, valor):
    factura.setFont('Helvetica', size=8)
    factura.drawString(x, y, valor)


def pintar_cabecera(factura, items_cabecera, x, y):
    for item in items_cabecera:
        factura.setFont('Helvetica-Bold', size=10)
        factura.drawString(x, y, item)
        x += 130


def pintar_noches(factura, y, alojamiento):
    x = 75
    for i in range(0, 4):
        pintar_dato(factura, x, y, alojamiento[i])
        x += 130


def obtener_listado_clientes():
    try:
        informacion_clientes = canvas.Canvas('clientes.pdf', pagesize=A4)
        informacion_clientes.setFont('Helvetica-Bold', size=16)
        informacion_clientes.drawString(250, 780, 'CLIENTES')
        informacion_clientes.setTitle('Clientes')

        clientes = funciones_clientes.obtener_listado_clientes()

        cabecera = ['DNI', 'NOMBRE', 'APELLIDOS', 'FECHA ALTA']
        informacion_clientes.line(50, 670, 540, 670)
        informacion_clientes.line(50, 645, 540, 645)
        pintar_cabecera(informacion_clientes, cabecera, 74, 655)
        lineas_clientes = []
        for cliente in clientes:
            lineas_clientes.append([cliente[1], cliente[3], cliente[2], cliente[4]])
        y = 655 - 30
        pintar_clientes(lineas_clientes, informacion_clientes, y)
        informacion_clientes.showPage()
        informacion_clientes.save()
        directorio_actual = os.getcwd()
        os.system('/usr/bin/xdg-open ' + directorio_actual + '/clientes.pdf')
    except Exception as e:
        print(e)

        print('Error en obtener_listado_clientes')


def pintar_clientes(lineas_clientes, informacion_clientes, y):
    for linea in lineas_clientes:
        pintar_datos(informacion_clientes, linea, 75, y)
        y -= 30