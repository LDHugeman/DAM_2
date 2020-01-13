from reportlab.pdfgen import canvas
from reportlab.lib.pagesizes import A4
import os


def basico():
    try:
        factura = canvas.Canvas('prueba.pdf', pagesize=A4)
        texto_bienvenida = 'Bienvenido a nuestro hotel'
        cif = 'CIF:00000000A'
        factura.drawImage('./img/logohotel.png', 475, 670, width=64, height=64)
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


def factura():
    try:
        factura = basico()
        factura.showPage()
        factura.save()
        directorio_actual = os.getcwd()
        os.system('/usr/bin/xdg-open ' + directorio_actual + '/prueba.pdf')
    except:
        print('Error en factura')