# coding=utf-8
'''Módulo con funciones relacionadas con excel.
'''

import datetime

import xlrd


def formatear_fecha_excel(celda):
    '''
    Cambia el formato de la fecha que proviene del excel a un formato que pueda ser guardado en la base da datos.
        :param celda: contiene una fecha que proviene del excel
        :return: fecha con el nuevo formato para base de datos
    '''
    archivo_excel = xlrd.open_workbook("clientes.xls")
    fecha_formateada = datetime.datetime(*xlrd.xldate_as_tuple(celda.value, archivo_excel.datemode))
    return fecha_formateada.strftime('%d/%m/%Y')