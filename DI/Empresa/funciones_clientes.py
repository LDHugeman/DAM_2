
import sqlite3

import funciones_excel
import variables
from typing import List
from xlrd.sheet import Cell
from xlrd import sheet
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


def insertar_cliente_BD(cliente):
    try:
        Conexion.cursor.execute('insert into  clientes(dni,apel,nome, data) values(?,?,?,?)', cliente)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def insertar_cliente_excel_BD(celdas_clientes: List[Cell]):
    cliente = []
    for celda_cliente in celdas_clientes:
        if celda_cliente.ctype == sheet.XL_CELL_DATE:
            cliente.append(funciones_excel.formatear_fecha_excel(celda_cliente))
        else:
            cliente.append(celda_cliente.value)
    insertar_cliente_BD(cliente)


def obtener_listado_clientes():
    try:
        Conexion.cursor.execute('select * from clientes')
        listado = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def carga_lista_clientes(lista_clientes):
    try:
        variables.listado = obtener_listado_clientes()
        lista_clientes.clear()
        for registro in variables.listado:
            lista_clientes.append(registro[1:5])
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


def modificar_cliente(registro, codigo_cliente):
    try:
        Conexion.cursor.execute('update clientes set dni = ?, apel= ?, nome = ?, data = ? where id = ?',
                                (registro[0], registro[1], registro[2], registro[3], codigo_cliente))
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


def obtener_nombre_apellidos_por_dni(dni):
    try:
        Conexion.cursor.execute('select apel, nome from clientes where dni = ?', (dni, ))
        listado = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()
