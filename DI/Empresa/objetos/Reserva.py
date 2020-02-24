from objetos import Cliente
from objetos import Habitacion


class Reserva:
    def __init__(self, codigo_reserva, cliente: Cliente, habitacion: Habitacion, numero_noches, check_in,
                 check_out):
        self.codigo_reserva = codigo_reserva
        self.cliente = cliente
        self.habitacion = habitacion
        self.numero_noches = numero_noches
        self.check_in = check_in
        self.check_out = check_out

    def precio_reserva(self):
        return float(self.habitacion.precio) * float(self.numero_noches)
