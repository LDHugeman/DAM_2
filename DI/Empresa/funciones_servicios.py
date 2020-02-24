# coding=utf-8
'''Módulo que gestiona los servicios.
'''
import sqlite3
from typing import List

import variables
from conexion import Conexion
from objetos.Servicio import Servicio


def limpiar_entries_servicios(entries):
    '''
    Vacía los entries de la pestaña de servicios tras ejecutar un evento.
        :param entries: contiene un listado de entries de servicios
        :return: void
    '''
    for i in range(len(entries)):
        entries[i].set_text('')


def limpiar_labels_servicios(labels):
    '''
    Vacía los labels de la pestaña de servicios tras ejecutar un evento.
        :param labels: contiene un listado de labels de servicios
        :return: void
    '''
    for i in range(len(labels)):
        labels[i].set_text('')


def obtener_precios_servicios_basicos():
    '''
    Devuelve un listado con los precios de los servicios básicos.
        :return listado_precios: listado de precios
    '''
    try:
        Conexion.cursor.execute('select * from precios')
        listado_precios = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return listado_precios
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def modificar_precios_servicios_basicos(precios):
    '''
    Modifica los precios de los servicios básicos.
        :param precios: nuevos precios para los servicios básicos
        :return: void
    '''
    try:
        Conexion.cursor.execute('update precios set precioParking = ?, precioDesayuno = ?, precioPensionCompleta = ?',
                                (precios[0], precios[1], precios[2]))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_listado_servicios_de_una_reserva(codigo_reserva) -> List[Servicio]:
    """
    Devuelve un listado con los servicios de una reserva de la base de datos.
        :return servicios: listado de servicios
    """
    try:
        Conexion.cursor.execute('select codigoServicio, concepto, precio from servicios where codigoReserva = ?',
                                (codigo_reserva,))
        servicios = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        array_servicios = []
        for servicio in servicios:
            array_servicios.append(Servicio(codigo_reserva, servicio[0], servicio[1], servicio[2]))
        return array_servicios
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def actualizar_lista_servicios(lista_servicios, codigo_reserva):
    '''
    Actualiza el listado de servicios.
        :param codigo_reserva: código de la reserva
        :param lista_servicios: listado de los servicios para actualizar
        :return: void
    '''
    try:
        variables.listado = obtener_listado_servicios_de_una_reserva(codigo_reserva)
        lista_servicios.clear()
        for servicio in variables.listado:
            lista_servicios.append([servicio.codigo, servicio.concepto, servicio.precio])
    except Exception as e:
        print(e)
        print("Error en actualizar_lista_servicios")


def actualizar_lista_previsualizar_servicios(lista_servicios, codigo_reserva, noches, precio_habitacion):
    """
    Actualiza el listado de los servicios previsualizados.
        :param codigo_reserva: código de la reserva
        :param lista_servicios: listado de los servicios para actualizar
        :param precio_habitacion: precio de la habitacion
        :param noches: número de noches
        :return: void
    """
    try:
        variables.listado = obtener_listado_servicios_de_una_reserva(codigo_reserva)
        lista_servicios.clear()
        total_noches = float(noches) * precio_habitacion
        lista_servicios.append(["Noches", noches, precio_habitacion, total_noches])
        for servicio in variables.listado:
            lista_servicios.append([servicio.concepto, "", servicio.precio, float(noches) * servicio.precio])
    except Exception as e:
        print(e)
        print("Error en actualizar_lista_previsualizar_servicios")


def insertar_servicio(servicio):
    '''
    Inserta un servicio en la base de datos.
        :param servicio: contiene un listado con los datos de un servicio básico
        :return: void
    '''
    try:
        Conexion.cursor.execute('insert into  servicios(concepto,precio,codigoReserva) values(?,?,?)', servicio)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def baja_servicio(codigo_servicio):
    '''
    Elimina un servicio de la base de datos.
        :param codigo_servicio: código del servicio que deseamos eliminar
        :return: void
    '''
    try:
        Conexion.cursor.execute('delete from servicios where codigoServicio = ?', (codigo_servicio,))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def existe_servicio_en_reserva(concepto, codigo_reserva):
    '''
    Comprueba si existe un servicio en la reserva
        :param concepto: concepto del servicio que queremos ver si ya está en la reserva
        :param codigo_reserva: código de la reserva
        :return: boolean
    '''
    servicios = obtener_listado_servicios_de_una_reserva(codigo_reserva)
    for servicio in servicios:
        if servicio.concepto == concepto:
            return True
    return False
