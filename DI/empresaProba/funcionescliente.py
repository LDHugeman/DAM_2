import sqlite3

import conexion
import variables


def limpiar_entry(fila):
    variables.mensajes_label[1].set_text('')
    for i in range(len(fila)):
        fila[i].set_text('')


def validoDNI(dni):
    try:
        tabla = "TRWAGMYFPDXBNJZSQVHLCKE"   #letras del dni, es estandar
        dig_ext = "XYZ"
        #tabla letras extranjeroreemp_
        reemp_dig_ext = {'X':'0', 'Y':'1', 'Z':'2'}
        numeros = "1234567890"
        dni = dni.upper()
        if len(dni) == 9: #el dni debe tener 9 caracteres
            dig_control = dni[8]
            dni = dni[:8]                                          #el número que son los 8 primeros
            if dni[0] in dig_ext:
                print(dni)
                dni = dni.replace(dni[0],reemp_dig_ext[dni[0]])
            return len(dni) == len([n for n in dni if n in numeros]) and tabla[int(dni)%23] == dig_control
        return False
    except:
        print("Error")
        return None


def insertar_cliente(fila):
    try:
        conexion.cur.execute('insert into clientes(dni, apelidos, nome, data) values(?,?,?,?)', fila)
        conexion.conex.commit()
    except sqlite3.OperationalError as e:
        print(e)
        print('Error en insertar_cliente')
        conexion.conex.rollback()


def listar_clientes():
    try:
        conexion.cur.execute('select * from clientes')
        listado = conexion.cur.fetchall()
        conexion.conex.commit()
        return listado
    except sqlite3.OperationalError as e:
        print(e)
        print('Error en listar_clientes')
        conexion.conex.rollback()


def baja_cliente(dni):
    try:
        conexion.cur.execute('delete from clientes where dni= ?', (dni,))
        conexion.conex.commit()
    except sqlite3.OperationalError as e:
        print(e)
        conexion.conex.rollback()


def modificar_cliente(registro, codigo):
    try:
        conexion.cur.execute('update clientes set dni = ?, apelidos = ?, nome = ?, data = ? where codigo = ?',
                             (registro[0],registro[1],registro[2],registro[3], codigo))
    except sqlite3.OperationalError as e:
        print(e)
        conexion.conex.rollback()


def listado_clientes(lista_clientes):
    try:
        variables.listado = listar_clientes()
        lista_clientes.clear()
        for registro in variables.listado:
            lista_clientes.append(registro[1:5])
    except:
        print("error en cargar treeview clientes")

def seleccionar_cliente(dni):
    try:
        conexion.cur.execute('select codigo from clientes where dni = ?', (dni,))
        listado = conexion.cur.fetchone()
        conexion.conex.commit()
        return listado
    except sqlite3.OperationalError as e:
        print(e)
        print('Error en seleccionar_cliente')
        conexion.conex.rollback()

