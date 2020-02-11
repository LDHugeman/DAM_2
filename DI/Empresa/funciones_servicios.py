# coding=utf-8
import sqlite3

import variables
from conexion import Conexion


def obtener_precios_servicios_basicos():
    try:
        Conexion.cursor.execute('select * from precios')
        listado_precios = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return listado_precios
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def modificar_precios_servicios_basicos(precios):
    try:
        Conexion.cursor.execute('update precios set precioParking = ?, precioDesayuno = ?, precioPensionCompleta = ?',
                                (precios[0], precios[1], precios[2]))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_listado_servicios():
    try:
        Conexion.cursor.execute('select * from servicios')
        listado_servicios = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return listado_servicios
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def actualizar_lista_servicios(lista_servicios):
    try:
        variables.listado = obtener_listado_servicios()
        lista_servicios.clear()
        for servicio in variables.listado:
            lista_servicios.append(servicio)
    except Exception as e:
        print(e)
        print("Error en actualizar_lista_servicios")


def insertar_servicio_basico(servicio):
    try:
        Conexion.cursor.execute('insert into  servicios(concepto,precio) values(?,?)', servicio)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()