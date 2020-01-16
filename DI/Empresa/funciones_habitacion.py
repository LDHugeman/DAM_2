"""
Aquí vendrán todas las funciones que afectan a la ¡gestión de los
habitaciones
Limpiarentry vaciará el contenido de los entry

"""

import sqlite3
import variables
from conexion import Conexion


def limpiar_entries(entries):
    for i in range(len(entries)):
        entries[i].set_text('')


def insertar_habitacion(habitacion):
    try:
        Conexion.cursor.execute('insert into habitacion(numero,tipo,prezo,libre) values(?,?,?,?)', habitacion)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_listado_habitaciones():
    try:
        Conexion.cursor.execute('select * from habitacion')
        listado = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def carga_lista_habitaciones(lista_habitaciones):
    try:
        lista_habitaciones.clear()
        for habitacion in obtener_listado_habitaciones():
            lista_habitaciones.append(habitacion)
    except Exception as e:
        print(e)
        print("Error en carga_lista_habitaciones")


def baja_habitacion(numhab):
    try:
        Conexion.cursor.execute('delete from habitacion where numero = ?', (numhab,))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def modificar_habitacion(habitacion, numero_habitacion):
    try:
        Conexion.cursor.execute('update habitacion set tipo = ?, prezo = ?, libre = ? where numero = ?',
                                (habitacion[1], habitacion[0], habitacion[2], numero_habitacion))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def listado_numeros_habitaciones():
    try:
        Conexion.cursor.execute('select numero from habitacion')
        listado = Conexion.cursor.fetchall()
        variables.listcmbhab.clear()
        for row in listado:
            variables.listcmbhab.append(row)
        Conexion.conexion.commit()

    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def listado_habitaciones_reserva():
    try:
        Conexion.cursor.execute('select numero from habitacion')
        lista = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return lista
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def cambiar_estado_habitacion(habitacion_libre, numero_habitacion_reserva):
    try:
        Conexion.cursor.execute('update habitacion set libre = ? where numero = ?', (
            habitacion_libre,
            numero_habitacion_reserva))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()