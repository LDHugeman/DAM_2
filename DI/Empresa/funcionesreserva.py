import sqlite3

import conexion
import variables


def listado_numero_habitaciones():
    try:
        conexion.cursor.execute('select numero from habitaciones')
        listado = conexion.cursor.fetchall()
        for row in listado:
            variables.combo_habitaciones.append(row)
            conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        conexion.conexion.rollback()

