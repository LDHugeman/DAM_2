# coding=utf-8
'''Módulo que gestiona los clientes.
'''
from xlrd import sheet
import funciones_excel
from conexion import Conexion
import sqlite3
import variables


def limpiar_entries_cliente(entries):
    '''
    Vacía los entries del cliente tras ejecutar un evento.
        :param entries: contiene un listado de entries del cliente
        :return: void
    '''
    for i in range(len(entries)):
        entries[i].set_text('')


def es_dni_valido(dni):
    '''
    Controla que el dni es correcto.
        :param dni: valor del dni del cliente
        :return: boolean
    '''
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


def insertar_cliente_BD(cliente):
    '''
    Inserta un cliente en la base de datos.
        :param cliente: contiene un listado con los datos de un cliente
        :return: void
    '''
    try:
        Conexion.cursor.execute('insert into  clientes(dni,apel,nome, data) values(?,?,?,?)', cliente)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def insertar_cliente_excel_BD(celdas_clientes):
    '''
    Inserta los clientes provenientes de un excel en la base de datos.
        :param celdas_clientes: contiene los datos de un cliente del excel
        :return: void
    '''
    cliente = []
    for celda_cliente in celdas_clientes:
        if celda_cliente.ctype == sheet.XL_CELL_DATE:
            cliente.append(funciones_excel.formatear_fecha_excel(celda_cliente))
        else:
            cliente.append(celda_cliente.value)
    insertar_cliente_BD(cliente)


def obtener_listado_clientes():
    '''
    Devuelve un listado con los clientes de la base de datos.
        :return clientes: listado de clientes
    '''
    try:
        Conexion.cursor.execute('select * from clientes')
        clientes = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return clientes
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def actualizar_lista_clientes(lista_clientes):
    '''
    Actualiza el listado de los clientes.
        :param lista_clientes: listado de los clientes para actualizar
        :return: void
    '''
    try:
        variables.listado = obtener_listado_clientes()
        lista_clientes.clear()
        for registro in variables.listado:
            lista_clientes.append(registro[1:5])
    except Exception as e:
        print(e)
        print("Error en actualizar_lista_clientes")


def baja_cliente(dni):
    '''
    Elimina un cliente de la base de datos.
        :param dni: dni del cliente que deseamos eliminar
        :return: void
    '''
    try:
        Conexion.cursor.execute('delete from clientes where dni = ?', (dni,))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def modificar_cliente(cliente, codigo_cliente):
    '''
    Modifica los datos de un cliente.
        :param cliente: contiene un listado con los nuevos datos para el cliente
        :param codigo_cliente: id del cliente que queremos modificar
        :return: void
    '''
    try:
        Conexion.cursor.execute('update clientes set dni = ?, apel= ?, nome = ?, data = ? where id = ?',
                                (cliente[0], cliente[1], cliente[2], cliente[3], codigo_cliente))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_id_cliente_por_dni(dni):
    '''
    Devuelve el id de un cliente.
        :param dni: dni del cliente
        :return: id del cliente
    '''
    try:
        Conexion.cursor.execute('select id from clientes where dni = ?', (dni,))
        id_cliente = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return id_cliente
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_nombre_apellidos_por_dni(dni):
    '''
    Devuelve el nombre y apellidos de un cliente.
        :param dni: dni del cliente
        :return: nombre y apellidos del cliente en un listado
    '''
    try:
        Conexion.cursor.execute('select apel, nome from clientes where dni = ?', (dni,))
        nombre_y_apellidos = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return nombre_y_apellidos
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()
