package pds.hispania360;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;

import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.util.EstilosApp;

public class App {

    public static void main(String[] args) {
        // Configuración FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            // Personalizar componentes UI
            EstilosApp.aplicarEstilosUI();
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Error al configurar FlatLaf: " + e.getMessage());
        }

        // Ejecutar en el EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            // Inicializar la aplicación a través del gestor de ventanas
            GestorVentanas gestor = GestorVentanas.getInstancia();
            gestor.inicializarAplicacion();
        });
    }
}