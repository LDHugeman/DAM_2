# coding=utf-8
'''Módulo que contiene funciones de carácter general.
'''

import variables


def mostrar_ventana_aviso(texto_ventana_aviso):
    '''
    Muestra una ventana de aviso.
        :param texto_ventana_aviso: texto que va a aparecer en la ventana de aviso
        :return: void
    '''
    variables.label_ventana_aviso.set_text(texto_ventana_aviso)
    variables.ventana_aviso.connect('delete-event', lambda w, e: w.hide() or True)
    variables.ventana_aviso.show()
