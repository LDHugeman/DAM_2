import os, sqlite3

class Conexion:

    cursor = None
    conexion = None

    def abrirbbdd(self):
        try:
            global bbdd, conex, cur
            bbdd = 'empresa.sqlite'
            conex = sqlite3.connect(bbdd)
            Conexion.conexion = conex
            Conexion.cursor = conex.cursor()
            cur = conex.cursor()
            print("Conexión realizada correctamente")
        except sqlite3.OperationalError as e:
            print("Error al abrir: ", e)

    def cerrarbbdd(self):
        try:
            Conexion.cursor.close()
            conex.close()
            print("Base de datos cerrada correctamente ")
        except sqlite3.OperationalError as e:
            print("Error al cerrar: ", e)