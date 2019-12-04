'''
Aquí vendrán todas las funciones que afectan a la gestión de los
clientes
Limpiarentry vaciará el contenido de los entry
'''
import datetime
import sqlite3

import conexion
import variables


def limpiar_entry(fila):
    variables.mensajeserror[1].set_text('')
    for i in range(len(fila)):
        fila[i].set_text('')


def es_dni_valido(dni: str):
    try:
        posibles_letras_dni = "TRWAGMYFPDXBNJZSQVHLCKE"
        posibles_numeros = "1234567890"
        valor_letra_extranjero_numero = {
            'X': '0',
            'Y': '1',
            'Z': '2'
        }

        dni = dni.upper()
        if es_tamaño_dni_valido(dni):
            letra_dni = dni[8]
            dni = dni[:8]
            if es_dni_extranjero(dni):
                dni = dni.replace(dni[0], valor_letra_extranjero_numero[dni[0]])
            return len(dni) == len([n for n in dni if n in posibles_numeros]) and posibles_letras_dni[
                int(dni) % 23] == letra_dni

        return False
    except:
        print('Error en la aplicación')
        return None


def es_tamaño_dni_valido(dni: str):
    return len(dni) == 9


def es_dni_extranjero(dni: str):
    posibles_letras_extranjero = "XYZ"
    return dni[0] in posibles_letras_extranjero


# inserta un registro
def insertarcliente(fila):
    try:
        conexion.cursor.execute('insert into clientes(dni, apelidos, nome, data) values(?,?,?,?)', fila)
        conexion.conexion.commit()

    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        conexion.Conexion.conexion.rollback()


# select para utilizar en las operaciones de datos

def listar():
    try:
        conexion.cursor.execute('select * from  clientes')
        listado = conexion.cursor.fetchall()
        conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        conexion.conexion.rollback()


def baja_cliente(dni):
    try:
        conexion.cursor.execute('delete from clientes where  dni = ?', (dni,))
        conexion.conexion.commit()
    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        print('Error en baja_cliente')
        conexion.conexion.rollback()


def modificar_cliente(registro, codigo):
    try:
        conexion.cursor.execute('update clientes set dni = ?, apelidos = ?, nome = ?, data = ?  where id = ?',
                                (registro[0],
                                 registro[1],
                                 registro[2],
                                 registro[3],
                                 codigo,))
        conexion.conexion.commit()
    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        print('Error en modificar_cliente')
        conexion.conexion.rollback()


# Esta función carga el treeview con los datos de la tabla clientes
def listado_clientes(lista_clientes):
    try:
        variables.listado = listar()
        variables.lista_clientes.clear()
        for registro in variables.listado:
            variables.lista_clientes.append(registro[1:5])
    except Exception as e:
        print(e)
        print('Error en cargar treeview')


def selectcliente(dni):
    try:
        conexion.cursor.execute('select id from  clientes where dni=?', (dni,))
        listado = conexion.cursor.fetchone()
        conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        conexion.conexion.rollback()


def mostrar_fecha():
    variables.mensajeserror[3].set_text(str(datetime.date.today()))


def mostrar_operacion(texto):
    variables.mensajeserror[2].set_text(texto)
