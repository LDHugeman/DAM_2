# coding=utf-8
import sqlite3
import variables
from datetime import datetime
from conexion import Conexion


def limpiarentry(fila):
    for i in range(len(fila)):
        fila[i].set_text('')
    for i in range(len(variables.mensajes_label)):
        variables.mensajes_label[i].set_text('')
    variables.combo_habitaciones.set_active(-1)


def calcular_noches():
    dia_check_in = variables.entries_reserva[0].get_text()
    date_in = datetime.strptime(dia_check_in, '%d/%m/%Y').date()
    dia_check_out = variables.entries_reserva[1].get_text()
    date_out = datetime.strptime(dia_check_out, '%d/%m/%Y').date()
    noches = (date_out - date_in).days
    if noches <= 0:
        variables.mensajes_label[2].set_text('Check-Out debe ser posterior')
        variables.reserva = 0
    else:
        variables.reserva = 1
        variables.mensajes_label[2].set_text(str(noches))


def insertar_reserva(reserva):
    try:
        Conexion.cursor.execute('insert into  reservas'
                                '(dni, numhab, checkin, checkout, noches, activa) values(?,?,?,?,?,?)',
                                reserva)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_apellidos_cliente_por_dni(dni):
    try:
        Conexion.cursor.execute('select apel from clientes where dni = ?', (dni,))
        apelidos = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return apelidos
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def esta_libre(numero_habitacion):
    try:
        Conexion.cursor.execute('select libre from habitacion where numero = ?', (numero_habitacion,))
        lista = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        if lista[0] == 'SI':
            return True
        else:
            return False
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def cambiar_estado_reserva(codigo, activa):
    try:
        Conexion.cursor.execute('update reservas set activa = ? where codreser = ?', (activa, codigo))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_listado_reservas():
    try:
        Conexion.cursor.execute('select codreser, dni, numhab, checkin, checkout, noches from reservas')
        listado = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return listado
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_listado_reservas_activas():
    try:
        Conexion.cursor.execute('select codreser, dni, numhab, checkin, checkout, noches '
                                'from reservas where activa = ?', ('SI',))
        reservas_activas = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return reservas_activas
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def carga_lista_reservas(lista_reservas, formulario_lista):
    try:
        formulario_lista.clear()
        for reserva in lista_reservas:
            formulario_lista.append(reserva)
    except Exception as e:
        print(e)
        Conexion.conexion.rollback()


def recargar_lista_reservas():
    if variables.switch_reservas.get_active():
        carga_lista_reservas(obtener_listado_reservas(), variables.lista_reservas)
    else:
        carga_lista_reservas(obtener_listado_reservas_activas(), variables.lista_reservas)


def obtener_nombre_cliente_por_dni(dni):
    try:
        Conexion.cursor.execute('select nome from clientes where dni = ?', (dni,))
        nombre = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return nombre
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_precio_habitacion_por_numero_habitacion(numero_habitacion):
    try:
        Conexion.cursor.execute('select prezo from habitacion where numero = ?', (numero_habitacion,))
        precio_habitacion = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return precio_habitacion
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def modificar_reserva(reserva, codigo):
    try:
        Conexion.cursor.execute('update reservas set checkout = ?, noches = ? where codreser = ?',
                                (reserva[1], reserva[2], codigo))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        print('Error en modificar_reserva')
        Conexion.conexion.rollback()
