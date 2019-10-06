def celsius_to_fahrenheit(grados_celsius):
    resultado = 1.4 * float(grados_celsius) + 32.0  # resultado = 1.4 * float(grados_celsius) + 32.0
    return resultado


def celsius_to_kelvin(grados_celsius):
    grados_kelvin = float(grados_celsius) + 273.15
    if grados_kelvin < 0:
        return False
    else:
        return grados_kelvin
