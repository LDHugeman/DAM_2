class Cliente:

    def __init__(self, dni, apellidos, nombre=""):
        self.dni = dni
        self.apellidos = apellidos
        self.nombre = nombre

    def set_nombre(self, nombre):
        self.nombre = nombre
