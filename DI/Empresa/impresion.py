from reportlab.pdfgen import canvas
from reportlab.lib.pagesizes import A4

import funciones_clientes
import os


def basico():
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


def factura(datos_factura):
    try:
        factura = basico()
        factura.setTitle('FACTURA')

        factura.setFont('Helvetica-Bold', size=8)
        numero_factura = 'Número de Factura:'
        factura.drawString(50, 735, numero_factura)
        factura.setFont('Helvetica', size=8)
        factura.drawString(140, 735, str(datos_factura[0]))

        factura.setFont('Helvetica-Bold', size=8)
        fecha_factura = 'Fecha Factura:'
        factura.drawString(300, 735, fecha_factura)
        factura.setFont('Helvetica', size=8)
        factura.drawString(360, 735, str(datos_factura[4]))

        factura.setFont('Helvetica-Bold', size=8)
        dni_cliente = 'DNI CLIENTE:'
        factura.drawString(50, 710, dni_cliente)
        factura.setFont('Helvetica', size=8)
        factura.drawString(120, 710, str(datos_factura[2]))

        factura.setFont('Helvetica-Bold', size=8)
        numero_habitacion = 'Nº de Habitación:'
        factura.drawString(300, 710, numero_habitacion)
        factura.setFont('Helvetica', size=8)
        factura.drawString(380, 710, str(datos_factura[3]))

        nombre_y_apellidos = funciones_clientes.obtener_nombre_apellidos_por_dni(datos_factura[2])

        factura.setFont('Helvetica-Bold', size=8)
        apellidos_cliente = 'APELLIDOS:'
        factura.drawString(50, 680, apellidos_cliente)
        factura.setFont('Helvetica', size=8)
        factura.drawString(120, 680, nombre_y_apellidos[0])

        factura.setFont('Helvetica-Bold', size=8)
        nombre_cliente = 'NOMBRE:'
        factura.drawString(300, 680, nombre_cliente)
        factura.setFont('Helvetica', size=8)
        factura.drawString(350, 680, nombre_y_apellidos[1])

        factura.setFont('Helvetica-Bold', size=10)
        cabecera = ['CONCEPTO', 'UNIDADES', 'PRECIO/UNIDAD', 'TOTAL']
        x = 75
        for i in range(0, 4):
            factura.drawString(x, 655, cabecera[i])
            x += 130
        factura.line(50, 645, 540, 645)

        factura.showPage()
        factura.save()
        directorio_actual = os.getcwd()
        os.system('/usr/bin/xdg-open ' + directorio_actual + '/prueba.pdf')
    except Exception as e:
        print(e)
        print('Error en factura')