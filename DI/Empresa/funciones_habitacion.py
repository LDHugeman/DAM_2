# coding=utf-8
'''Módulo que gestiona las habitaciones.
'''
import sqlite3
import variables
from conexion import Conexion


def limpiar_entries(entries):
    '''
    Vacía los entries de la habitación tras ejecutar un evento.
        :param entries: contiene un listado de entries de la habitación
        :return: void
    '''
    for i in range(len(entries)):
        entries[i].set_text('')


def obtener_listado_habitaciones():
    '''
    Devuelve un listado con las habitaciones de la base de datos.
        :return habitaciones: listado de habitaciones
    '''
    try:
        Conexion.cursor.execute('select * from habitacion')
        habitaciones = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return habitaciones
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def actualizar_lista_habitaciones(lista_habitaciones):
    '''
    Actualiza el listado de habitaciones.
        :param lista_habitaciones: listado de las habitaciones para actualizar
        :return: void
    '''
    try:
        lista_habitaciones.clear()
        for habitacion in obtener_listado_habitaciones():
            lista_habitaciones.append(habitacion)
    except Exception as e:
        print(e)
        print("Error en carga_lista_habitaciones")


def insertar_habitacion_BD(habitacion):
    '''
    Inserta una habitación en la base de datos.
        :param habitacion: contiene un listado con los datos de una habitacion
        :return: void
    '''
    try:
        Conexion.cursor.execute('insert into habitacion(numero,tipo,prezo,libre) values(?,?,?,?)', habitacion)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def baja_habitacion(numero_habitacion):
    '''
    Elimina una habitación de la base de datos.
        :param numero_habitacion: número de la habitación que deseamos eliminar
        :return: void
    '''
    try:
        Conexion.cursor.execute('delete from habitacion where numero = ?', (numero_habitacion,))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def modificar_habitacion(habitacion, numero_habitacion):
    '''
    Modifica los datos de una habitación.
        :param habitacion: contiene un listado con los nuevos datos para la habitación
        :param numero_habitacion: número de la habitación que queremos modificar
        :return: void
    '''
    try:
        Conexion.cursor.execute('update habitacion set tipo = ?, prezo = ?, libre = ? where numero = ?',
                                (habitacion[1], habitacion[0], habitacion[2], numero_habitacion))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def actualizar_numeros_habitacion():
    '''
    Actualiza la lista de habitaciones del combo box.
        :return: void
    '''
    try:
        Conexion.cursor.execute('select numero from habitacion')
        listado = Conexion.cursor.fetchall()
        variables.lista_combo_habitaciones.clear()
        for row in listado:
            variables.lista_combo_habitaciones.append(row)
        Conexion.conexion.commit()

    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def listado_habitaciones_reserva():
    '''
    Devuelve un listado con los números de habitación.
        :return: listado de números de habitación
    '''
    try:
        Conexion.cursor.execute('select numero from habitacion')
        habitaciones = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return habitaciones
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def cambiar_estado_habitacion(habitacion_libre, numero_habitacion_reserva):
    '''
    Modifica el estado de la habitación.
        :param habitacion_libre: nuevo estado para la habitación
        :param numero_habitacion_reserva: número de la habitación de la que queremos cambiar el estado
        :return: void
    '''
    try:
        Conexion.cursor.execute('update habitacion set libre = ? where numero = ?', (
            habitacion_libre,
            numero_habitacion_reserva))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()
