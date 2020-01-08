def celsius_to_fahrenheit(grados_celsius):
    grados_celsius = float(grados_celsius)
    return (grados_celsius * 9 / 5) + 32  # resultado = 1.4 * float(grados_celsius) + 32.0


def celsius_to_kelvin(grados_celsius):
    grados_kelvin = float(grados_celsius) + 273.15
    if grados_kelvin < 0:
        return False
    else:
        return grados_kelvin


def kelvin_to_celsius(grados_kelvin):
    grados_celsius = float(grados_kelvin) - 273.15
    if grados_celsius < -273.15:
        return False
    else:
        return grados_celsius


def kelvin_to_fahrenheit(grados_kelvin):
    grados_celsius = kelvin_to_celsius(grados_kelvin)
    return celsius_to_fahrenheit(grados_celsius)


def fahrenheit_to_celsius(grados_fahrenheit):
    return (float(grados_fahrenheit)-32)/1.4


def fahrenheit_to_kelvin(grados_fahrenheit):
    grados_celsius = fahrenheit_to_celsius(grados_fahrenheit)
    return celsius_to_kelvin(grados_celsius)


