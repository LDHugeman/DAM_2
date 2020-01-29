import funciones_reserva
import variables


def limpiar_labels_factura(labels_factura):
    for i in range(len(labels_factura)):
        labels_factura[i].set_text('')


def obtener_factura(dni, apellidos, nombre, numero_habitacion, check_out, noches):
    try:
        variables.labels_factura[0].set_text(str(dni))
        variables.labels_factura[1].set_text(str(apellidos[0]))
        variables.labels_factura[2].set_text(str(nombre[0]))
        variables.labels_factura[3].set_text(str(variables.codigo_reserva))
        variables.labels_factura[4].set_text(str(numero_habitacion))
        variables.labels_factura[5].set_text(str(check_out))
        variables.labels_factura[6].set_text(str(noches))
        precio_habitacion = funciones_reserva.obtener_precio_habitacion_por_numero_habitacion(numero_habitacion)
        variables.labels_factura[7].set_text(str(precio_habitacion[0]))
        total = float(str(noches)) * float(precio_habitacion[0])
        variables.labels_factura[8].set_text(str(total))
    except Exception as e:
        print(e)
        print('Error en obtener_factura')
