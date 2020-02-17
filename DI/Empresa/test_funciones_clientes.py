from unittest import TestCase


class Test(TestCase):
    def test_es_dni_valido(self):
        from funciones_clientes import es_dni_valido
        self.assertTrue(es_dni_valido('00000000T'))
