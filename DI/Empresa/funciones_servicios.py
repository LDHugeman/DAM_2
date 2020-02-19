# coding=utf-8
'''Módulo que gestiona los servicios.
'''
import sqlite3

import variables
from conexion import Conexion


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


def obtener_listado_servicios_de_una_reserva(codigo_reserva):
    '''
    Devuelve un listado con los servicios de una reserva de la base de datos.
        :return servicios: listado de servicios
    '''
    try:
        Conexion.cursor.execute('select codigoServicio, concepto, precio from servicios where codigoReserva = ?', (codigo_reserva,))
        servicios = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return servicios
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def actualizar_lista_servicios(lista_servicios, codigo_reserva):
    '''
    Actualiza el listado de servicios.
        :param lista_servicios: listado de los servicios para actualizar
        :return: void
    '''
    try:
        variables.listado = obtener_listado_servicios_de_una_reserva(codigo_reserva)
        lista_servicios.clear()
        for servicio in variables.listado:
            lista_servicios.append(servicio)
    except Exception as e:
        print(e)
        print("Error en actualizar_lista_servicios")


def insertar_servicio_basico(servicio):
    '''
    Inserta un servicio básico en la base de datos.
        :param servicio: contiene un listado con los datos de un servicio básico
        :return: void
    '''
    try:
        Conexion.cursor.execute('insert into  servicios(concepto,precio,codigoReserva) values(?,?,?)', servicio)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()