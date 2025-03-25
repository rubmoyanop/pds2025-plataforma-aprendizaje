package pds.hispania360;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;

import pds.hispania360.vista.core.GestorVentanas;

public class App {

    // Colores de la aplicación - Podrían moverse a una clase de estilo o tema si se usan en varios lugares
    public static final String NOMBRE_APP = "Hispania 360";
    public static final java.awt.Color COLOR_PRIMARIO = new java.awt.Color(198, 40, 40);     // Rojo oscuro (de la bandera española)
    public static final java.awt.Color COLOR_FONDO = new java.awt.Color(245, 245, 245);      // Fondo claro
    public static final java.awt.Color COLOR_BOTON = new java.awt.Color(198, 40, 40);        // Color de botones
    public static final java.awt.Color COLOR_BOTON_HOVER = new java.awt.Color(229, 57, 53);  // Color cuando el cursor está sobre el botón
    public static final java.awt.Font FUENTE_TITULO = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 32);
    public static final java.awt.Font FUENTE_SUBTITULO = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18);
    public static final java.awt.Font FUENTE_BOTON = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16);

    public static void main(String[] args) {
        // Configurar FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            // Personalizar componentes UI - Estos podrían moverse a una clase de estilo o tema
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("Button.foreground", COLOR_PRIMARIO);
            UIManager.put("Panel.background", COLOR_FONDO);
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

    /**
     * Carga una imagen desde los recursos de la aplicación.
     * @param ruta Ruta relativa de la imagen dentro del directorio de recursos
     * @return La imagen cargada o null si no se encuentra
     */
    public static java.awt.Image cargarImagen(String ruta) {
        try {
            java.io.InputStream is = App.class.getResourceAsStream(ruta);
            if (is != null) {
                return new javax.swing.ImageIcon(javax.imageio.ImageIO.read(is)).getImage();
            } else {
                // Si no encuentra la imagen, usar una imagen por defecto o null
                System.err.println("No se encontró el recurso: " + ruta);
                return null;
            }
        } catch (java.io.IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            return null;
        }
    }
}