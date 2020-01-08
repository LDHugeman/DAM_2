def es_dni_valido(dni: str):
    try:
        posibles_letras_dni = "TRWAGMYFPDXBNJZSQVHLCKE"
        posibles_numeros = "1234567890"
        valor_letra_extranjero_numero = {
            'X': '0',
            'Y': '1',
            'Z': '2'
        }

        dni = dni.upper()
        if es_tamaño_dni_valido(dni):
            letra_dni = dni[8]
            dni = dni[:8]
            if es_dni_extranjero(dni):
                dni = dni.replace(dni[0], valor_letra_extranjero_numero[dni[0]])
            return len(dni) == len([n for n in dni if n in posibles_numeros]) and posibles_letras_dni[
                int(dni) % 23] == letra_dni

        return False
    except:
        print('Error en la aplicación')
        return None


def es_tamaño_dni_valido(dni: str):
    return len(dni) == 9


def es_dni_extranjero(dni: str):
    posibles_letras_extranjero = "XYZ"
    return dni[0] in posibles_letras_extranjero
