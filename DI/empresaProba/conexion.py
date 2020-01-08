import os, sqlite3

class Conexion:
    def abrirbbdd(self):
        try:
            global bbdd, conex, cur
            bbdd = 'empresaProba.sqlite'         #variable que almacena la base de datos
            conex = sqlite3.connect(bbdd)   #la abrimos
            cur = conex.cursor()            #la variable cursor que hará las operaciones
            print("Conexión realizada correctamente")
        except sqlite3.OperationalError as e:
            print("Error al abrir: ", e)

    def cerrarbbdd(self):
        try:
            cur.close()
            conex.close()
            print("Base de datos cerrada correctamente ")
        except sqlite3.OperationalError as e:
            print("Error al cerrar: ", e)
