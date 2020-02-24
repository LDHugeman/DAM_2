class Servicio:

    def __init__(self, codigo_reserva: int, codigo: int, concepto: str, precio: float):
        self.codigo_reserva = codigo_reserva
        self.codigo = codigo
        self.concepto = concepto
        self.precio = precio

    def calcular_iva(self, numero_noches):
        if self.concepto == 'Desayuno' or self.concepto == 'Comida' or self.concepto == 'Parking':
            return self.precio * float(numero_noches) * 10 / 100
        else:
            return self.precio * float(numero_noches) * 21 / 100
