"""
Aquí vendrán todas las funciones que afectan a la ¡gestión de los
clientes
Limpiarentry vaciará el contenido de los entry

"""

import sqlite3
import variables
from conexion import Conexion


def limpiarentry(fila):
    for i in range(len(fila)):
        fila[i].set_text('')


def es_dni_valido(dni):
    try:
        tabla = "TRWAGMYFPDXBNJZSQVHLCKE"  # Letras del dni, es estándar
        dig_ext = "XYZ"
        # Tabla letras extranjero a reemplazar
        reemp_dig_ext = {'X': '0', 'Y': '1', 'Z': '2'}
        numeros = "1234567890"
        dni = dni.upper()
        if len(dni) == 9:  # El dni debe tener 9 caracteres
            dig_control = dni[8]
            dni = dni[:8]  # El número que son los 8 primeros
            if dni[0] in dig_ext:
                print(dni)
                dni = dni.replace(dni[0], reemp_dig_ext[dni[0]])
            return len(dni) == len([n for n in dni if n in numeros]) and tabla[int(dni) % 23] == dig_control
        return False
    except Exception as e:
        print(e)
        print("Error en es_dni_valido")
        return None


def insertar_cliente(fila):
    try:
        Conexion.cursor.execute('insert into  clientes(dni,apel,nome, data) values(?,?,?,?)', fila)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_listado_clientes():
    try:
        Conexion.cursor.execute('select * from clientes')
        listado = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def carga_lista_clientes(listclientes):
    try:
        variables.listado = obtener_listado_clientes()
        listclientes.clear()
        for registro in variables.listado:
            listclientes.append(registro[1:5])
    except Exception as e:
        print(e)
        print("Error en carga_lista_clientes")


def baja_cliente(dni):
    try:
        Conexion.cursor.execute('delete from clientes where dni = ?', (dni,))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def modificar_cliente(registro, cod):
    try:
        Conexion.cursor.execute('update clientes set dni = ?, apel= ?, nome = ?, data = ? where id = ?',
                                (registro[0], registro[1], registro[2], registro[3], cod))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_id_cliente_por_dni(dni):
    try:
        Conexion.cursor.execute('select id from clientes where dni = ?', (dni,))
        listado = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


'''
def limpiarentry(entries):

    variables.menslabel[1].set_text('')
    for i in range(len(entries)):
        entries[i].set_text('')
'''
