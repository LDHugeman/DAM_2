from unittest import TestCase

from objetos.Servicio import Servicio


class TestServicio(TestCase):
    def test_calcular_iva_10(self):
        servicioTest: Servicio = Servicio(1, 1, "Comida", 2.50)
        self.assertTrue(servicioTest.calcular_iva(1) == 0.25)

    def test_calcular_iva_21(self):
        servicioTest: Servicio = Servicio(1, 1, "Minibar", 2.50)
        self.assertTrue(servicioTest.calcular_iva(1) == 0.525)
