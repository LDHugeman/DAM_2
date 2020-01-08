from random import randint, shuffle, choices


def obtener_numeros_primitiva():
    numeros = []
    for i in range(1, 7):
        numero = randint(1, 49)
        numeros.append(numero)
    reintegro = randint(0, 9)
    numeros.append(reintegro)
    return numeros


def obtener_numeros_euromillones():
    numeros = []
    for i in range(1, 6):
        numero = randint(1, 50)
        numeros.append(numero)
    for i in range(1, 3):
        numero = randint(1, 11)
        numeros.append(numero)
    return numeros


def obtener_numero_loteria_nacional():
    numeros = []
    numero_loteria = ''
    for i in range(1, 6):
        numero = randint(0, 9)
        numeros.append(numero)
    for numero in numeros:
        numero_loteria += str(numero)
    return numero_loteria


def obtener_resultados_quiniela_futbol():
    resultados_posibles = [1, 'X', 2]
    resultados = []
    for i in range(1, 16):
        resultado = choices(resultados_posibles)
        resultados.append(resultado)
    return resultados


def quitarCorchetes(list1):
    return str(list1).replace('[', '').replace(']', '')
