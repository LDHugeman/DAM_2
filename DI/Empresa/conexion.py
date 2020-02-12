# coding=utf-8
'''Módulo que gestiona la conexión con la base de datos.
'''
import sqlite3


class Conexion:

    cursor = None
    conexion = None

    def abrirbbdd(self):
        '''
        Abre una conexión con la base de datos.
            :return:void
        '''
        try:
            global bbdd, conex, cur
            bbdd = 'empresa.sqlite'
            Conexion.conexion = sqlite3.connect(bbdd)
            Conexion.cursor = Conexion.conexion.cursor()
            print("Conexión realizada correctamente")
        except sqlite3.OperationalError as e:
            print("Error al abrir: ", e)

    def cerrarbbdd(self):
        '''
        Cierra la conexión con la base de datos.
            :return: void
        '''
        try:
            Conexion.cursor.close()
            Conexion.conexion.close()
            print("Base de datos cerrada correctamente ")
        except sqlite3.OperationalError as e:
            print("Error al cerrar: ", e)