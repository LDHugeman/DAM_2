import gi
import crear
import variables

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk


class Eventos:

    def on_botonSalir_clicked(self, widget):
        variables.ventana_dialog.show()

    def on_botonAceptarDialog_clicked(self, widget):
        Gtk.main_quit()

    def on_botonCancelarDialog_clicked(self, widget):
        variables.ventana_dialog.hide()

    def on_botonAceptar_clicked(self, widget):
        valor_combo_loterias = variables.combo_loterias.get_active_text()
        if valor_combo_loterias == 'Primitiva':
            numeros = crear.obtener_numeros_primitiva()
            variables.label_resultado.set_text(numeros.__str__())
        elif valor_combo_loterias == 'Euromillones':
            numeros = crear.obtener_numeros_euromillones()
            variables.label_resultado.set_text(numeros.__str__())
        elif valor_combo_loterias == 'Lotería Nacional':
            numero = crear.obtener_numero_loteria_nacional()
            variables.label_resultado.set_text('[' + numero.__str__() + ']')
        elif valor_combo_loterias == 'Quiniela de fútbol':
            resultados = crear.obtener_resultados_quiniela_futbol()
            variables.label_resultado.set_text('[' + crear.quitarCorchetes(resultados) + ']')
