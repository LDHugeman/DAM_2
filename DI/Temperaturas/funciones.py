def celsius_to_fahrenheit(grados_celsius):
    grados_celsius = float(grados_celsius)
    return (grados_celsius * 9 / 5) + 32  # resultado = 1.4 * float(grados_celsius) + 32.0


def celsius_to_kelvin(grados_celsius):
    grados_kelvin = float(grados_celsius) + 273.15
    if grados_kelvin < 0:
        return False
    else:
        return grados_kelvin
