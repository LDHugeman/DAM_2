import sqlite3

import conexion
import variables


def limpiar_entry(fila):
    fila[0].set_text('')
    fila[2].set_active(True)
    fila[4].set_text('')
    fila[5].set_active(True)


def listar_habitaciones():
    try:
        conexion.cursor.execute('select * from  habitaciones')
        listado = conexion.cursor.fetchall()
        conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        conexion.conexion.rollback()


def insertarhabitacion(fila):
    try:
        conexion.cursor.execute('insert into habitaciones(numero, tipo, precio, libre) values(?,?,?,?)', fila)
        conexion.conexion.commit()

    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        conexion.Conexion.conexion.rollback()


def listado_habitaciones(lista_habitaciones):
    try:
        variables.listado_habitaciones = listar_habitaciones()
        variables.lista_habitaciones.clear()
        for registro in variables.listado_habitaciones:
            variables.lista_habitaciones.append(registro[0:3])
    except Exception as e:
        print(e)
        print('Error en cargar treeview')


def baja_habitacion(numero):
    try:
        conexion.cursor.execute('delete from habitaciones where  numero = ?', (numero,))
        conexion.conexion.commit()
    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        print('Error en baja_habitacion')
        conexion.conexion.rollback()


def modificar_habitacion(registro, numero):
    try:
        conexion.cursor.execute('update habitaciones set numero = ?, tipo = ?, precio = ?, libre = ? where numero = ?',
                                (registro[0],
                                 registro[1],
                                 registro[2],
                                 numero,))
        conexion.conexion.commit()
    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        print('Error en modificar_habitación')
        conexion.conexion.rollback()

