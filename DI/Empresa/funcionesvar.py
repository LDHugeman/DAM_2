import threading
import time
import variables


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

def actualizarhab():
    print('Actualizador de habitaciones')