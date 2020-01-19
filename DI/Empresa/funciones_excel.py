import datetime
from xlrd.sheet import Cell
import xlrd


def formatear_fecha_excel(celda: Cell):
    archivo_excel = xlrd.open_workbook("clientes.xls")
    fecha_formateada = datetime.datetime(*xlrd.xldate_as_tuple(celda.value, archivo_excel.datemode))
    return fecha_formateada.strftime('%d/%m/%Y')
