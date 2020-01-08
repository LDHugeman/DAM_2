import os
import sqlite3

import variables


class Conexion:

    def abrirbbdd(self):
        try:
            global bbdd, conexion, cursor
            bbdd = 'empresa.sqlite'
            conexion = sqlite3.connect(bbdd)
            cursor = conexion.cursor()
            print("Conexión realizada correctamente")
        except sqlite3.OperationalError as excepcion:
            print("Error al abrir", excepcion)

    def cerrarbbdd(self):
        try:
            cursor.close()
            conexion.close()
            print("Base de datos cerrada correctamente")
        except sqlite3.OperationalError as excepcion:
            print("Error al cerrar", excepcion)
