import sqlite3
from datetime import datetime

import conexion
import variables


def listado_numero_habitaciones():
    try:
        conexion.cursor.execute('select numero from habitaciones')
        listado = conexion.cursor.fetchall()
        for row in listado:
            variables.combo_habitaciones.append(row)
            conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        conexion.Conexion.rollback()


def calculardias():
    diain = variables.fila_reserva[2].get_text()
    date_in = datetime.strptime(diain, '%d/%m/%Y').date()
    diaout = variables.fila_reserva[3].get_text()
    date_out = datetime.strptime(diaout, '%d/%m/%Y').date()
    noches = (date_out - date_in).days
    if noches <= 0:
        variables.menslabel[2].set_text('Check-Out debe ser posterior')
        variables.reserva = 0
    else:
        variables.menslabel[2].set_text(str(noches))
        variables.reserva = 1


def insertar_reserva(fila):
    try:
        conexion.cursor.execute(
            'insert into reservas(dni, habitacion, checkin, checkout, numeronoches) values(?,?,?,?,?)', fila)
        conexion.conexion.commit()

    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        conexion.Conexion.conexion.rollback()


def listar_reservas():
    try:
        conexion.cursor.execute('select * from  reservas')
        listado = conexion.cursor.fetchall()
        conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as excepcion:
        print(excepcion)
        conexion.conexion.rollback()


def listado_reservas(lista_reservas):
    try:
        variables.listado_reservas = listar_reservas()
        variables.lista_reservas.clear()
        for registro in variables.listado_reservas:
            variables.lista_reservas.append(registro[0:3])
    except Exception as e:
        print(e)
        print('Error en cargar treeview')