import sqlite3


class Conexion:

    cursor = None
    conexion = None

    def abrirbbdd(self):
        try:
            global bbdd, conex, cur
            bbdd = 'empresa.sqlite'
            Conexion.conexion = sqlite3.connect(bbdd)
            Conexion.cursor = Conexion.conexion.cursor()
            print("Conexión realizada correctamente")
        except sqlite3.OperationalError as e:
            print("Error al abrir: ", e)

    def cerrarbbdd(self):
        try:
            Conexion.cursor.close()
            Conexion.conexion.close()
            print("Base de datos cerrada correctamente ")
        except sqlite3.OperationalError as e:
            print("Error al cerrar: ", e)