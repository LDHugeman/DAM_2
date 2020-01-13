import os, threading, sys
from datetime import datetime, date
import time
import conexion, zipfile
import variables


def backup():
    try:
        conexion.Conexion().cerrarbbdd()
        backup = 'backup.zip'
        #destino = '/home/pruebas/copias/'
        copia = zipfile.ZipFile(backup, 'w')
        copia.write('empresa.sqlite', compress_type = zipfile.ZIP_DEFLATED)
        copia.close()
        neobackup = str(datetime.now()) + str(backup)
        os.rename(backup, neobackup)
        conexion.Conexion().abrirbbdd()
        return neobackup
    except:
        print('error backup')


def controlhab():
    variables.t = threading.Timer(0.5, controlhab)
    variables.t.daemon = True
    variables.t.start()
    fechahoy = time.strftime('%H:%M:%S')
    fechacontrol = '20:08:00'
    if str(fechacontrol) == str(fechahoy):
        actualizarhab()


def cerrartimer():
    variables.t.join(0)

'''
def controlhab():
    try:
        time_of_day = time.strftime("%H:%M:%S")
        target_time = '19:57:00'
        print(time_of_day)
        print(target_time)
        while True:
            while str(time_of_day) != str(target_time):
                time.sleep(.1)
            actualizarhab()
            while time_of_day == target_time:
                print('hola')
                time.sleep(.1)
    except:
        print('error control habitacion')

'''
def actualizarhab():
    print('hola actualizasdor de habitaciones')