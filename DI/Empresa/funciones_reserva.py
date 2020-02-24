# coding=utf-8
'''Módulo que gestiona las reservas.
'''
import sqlite3
from datetime import datetime

import variables
from conexion import Conexion
from objetos.Cliente import Cliente


def limpiar_entries_reserva(entries):
    '''
    Vacía los entries de la reserva tras ejecutar un evento.
        :param entries: contiene un listado de entries de la reserva
        :return: void
    '''
    for i in range(len(entries)):
        entries[i].set_text('')
    for i in range(len(variables.mensajes_label)):
        variables.mensajes_label[i].set_text('')
    variables.combo_habitaciones.set_active(-1)


def calcular_noches():
    '''
    Calcula el número de noches y lo muestra.
        :return: void
    '''
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
    '''
    Inserta una  en la base de datos.
        :param reserva: contiene un listado con los datos de una reserva
        :return: void
    '''
    try:
        Conexion.cursor.execute('insert into  reservas'
                                '(dni, numhab, checkin, checkout, noches, activa) values(?,?,?,?,?,?)',
                                reserva)
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_cliente_por_dni(dni) -> Cliente:
    """
    Obtiene un cliente de la BD
    :param dni: Dni del cliente
    :return: Objeto cliente
    """
    try:
        Conexion.cursor.execute('select * from clientes where dni = ?', (dni,))
        cliente = Conexion.cursor.fetchone()
        return Cliente(cliente[1], cliente[2], cliente[3])
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_apellidos_cliente_por_dni(dni):
    '''
    Devuelve los apellidos de un cliente.
        :param dni: dni del cliente del que queremos obtener sus apellidos
        :return: apellidos del cliente
    '''
    try:
        Conexion.cursor.execute('select apel from clientes where dni = ?', (dni,))
        apellidos = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return apellidos
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def esta_libre(numero_habitacion):
    '''
    Nos dice si la habitación está o no libre.
        :param numero_habitacion: número de la habitación de la cual queremos saber su estado
        :return: boolean
    '''
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
    '''
    Cambia el estado de la reserva.
        :param codigo: código de la reserva que queremos modificar su estado
        :param activa: nuevo estado para la reserva
        :return: void
    '''
    try:
        Conexion.cursor.execute('update reservas set activa = ? where codreser = ?', (activa, codigo))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_listado_reservas():
    '''
    Devuelve un listado con las reservas de la base de datos.
        :return reservas: listado de reservas
    '''
    try:
        Conexion.cursor.execute('select codreser, dni, numhab, checkin, checkout, noches from reservas')
        reservas = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return reservas
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_listado_reservas_activas():
    '''
    Devuelve un listado con las reservas con estado activo.
        :return reservas_activas: listado de reservas activas
    '''
    try:
        Conexion.cursor.execute('select codreser, dni, numhab, checkin, checkout, noches '
                                'from reservas where activa = ?', ('SI',))
        reservas_activas = Conexion.cursor.fetchall()
        Conexion.conexion.commit()
        return reservas_activas
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def carga_lista_reservas(lista_reservas):
    '''
    Actualiza el listado de reservas
        :param lista_reservas: listado de reservas para actualizar
        :return:
    '''
    try:
        variables.lista_reservas.clear()
        for reserva in lista_reservas:
            variables.lista_reservas.append(reserva)
    except Exception as e:
        print(e)
        Conexion.conexion.rollback()


def actualizar_lista_reservas():
    '''
    Actualiza el listado de reservas para mostrar las activas o todas.
        :return: void
    '''
    if variables.switch_reservas.get_active():
        carga_lista_reservas(obtener_listado_reservas())
    else:
        carga_lista_reservas(obtener_listado_reservas_activas())


def obtener_nombre_cliente_por_dni(dni):
    '''
    Devuelve el nombre de un cliente.
        :param dni: dni del cliente del que queremos obtener su nombre
        :return nombre: nombre del cliente
    '''
    try:
        Conexion.cursor.execute('select nome from clientes where dni = ?', (dni,))
        nombre = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return nombre
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_precio_habitacion_por_numero_habitacion(numero_habitacion):
    '''
    Devuelve el precio de la habitación.
    :param numero_habitacion: número de la habitación de la que queremos saber su precio
    :return precio_habitacion: precio de la habitación
    '''
    try:
        Conexion.cursor.execute('select prezo from habitacion where numero = ?', (numero_habitacion,))
        precio_habitacion = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return precio_habitacion[0]
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def obtener_noches_por_codigo_reserva(codigo_reserva):
    '''
    Devuelve el precio de la habitación.
    :param codigo_reserva: código de la reserva de la que queremos saber el número de noches
    :return noches: número de noches de la reserva
    '''
    try:
        Conexion.cursor.execute('select noches from reservas where codreser = ?', (codigo_reserva,))
        noches = Conexion.cursor.fetchone()
        Conexion.conexion.commit()
        return noches[0]
    except sqlite3.OperationalError as e:
        print(e)
        Conexion.conexion.rollback()


def modificar_reserva(reserva, codigo):
    '''
    Modifica los datos de una reserva.
        :param reserva: contiene un listado con los nuevos datos para la reserva
        :param codigo: código de la reserva que queremos modificar
        :return: void
    '''
    try:
        Conexion.cursor.execute('update reservas set checkout = ?, noches = ? where codreser = ?',
                                (reserva[1], reserva[2], codigo))
        Conexion.conexion.commit()
    except sqlite3.OperationalError as e:
        print(e)
        print('Error en modificar_reserva')
        Conexion.conexion.rollback()
