import threading
import time
import variables


def control_habitacion():
    variables.hilo_demonio = threading.Timer(0.5, control_habitacion)
    variables.hilo_demonio.daemon = True
    variables.hilo_demonio.start()
    fecha_hoy = time.strftime('%H:%M:%S')
    fecha_control = '20:08:00'
    if str(fecha_control) == str(fecha_hoy):
        actualizar_habitacion()

def cerrar_timer():
    variables.hilo_demonio.join(0)

def actualizar_habitacion():
    print('Actualizador de habitaciones')