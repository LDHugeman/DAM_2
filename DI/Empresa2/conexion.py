import os
import pymysql

class Conexion:

    def abrirbbdd(self):
        try:
            global server, bbdd, conexion, cursor, user, passw
            bbdd = 'empresa2'
            server = 'localhost'
            user = 'root'
            passw = 'abc123.'
            conexion = pymysql.connect(server, user, passw, bbdd)
            cursor = conexion.cursor()
            print("Conexión realizada correctamente")
        except pymysql.err.OperationalError as excepcion:
            print("Error al abrir", excepcion)

    def cerrarbbdd(self):
        try:
            cursor.close()
            conexion.close()
            print("Base de datos cerrada correctamente")
        except pymysql.err.OperationalError as excepcion:
            print("Error al cerrar", excepcion)