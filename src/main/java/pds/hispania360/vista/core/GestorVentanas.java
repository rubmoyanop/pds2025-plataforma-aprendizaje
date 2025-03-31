package pds.hispania360.vista.core;

import pds.hispania360.vista.pantallas.*;
import pds.hispania360.vista.util.EstilosApp;
import pds.hispania360.vista.util.ImagenUtil;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestor de ventanas implementado como Singleton Enumerado.
 * Se encarga de manejar la navegación entre las diferentes ventanas de la aplicación.
 */
public enum GestorVentanas {
    INSTANCIA;
    
    private JFrame frameContenedor;
    private Map<TipoVentana, Ventana> ventanas;
    private Ventana ventanaActual;
    private JPanel panelContenedor;
    
    /**
     * Constructor del enum que inicializa las ventanas
     */
    GestorVentanas() {
        ventanas = new HashMap<>();
        inicializarVentanas();
    }
    
    /**
     * Inicializa la aplicación creando y configurando el frame principal.
     */
    public void inicializarAplicacion() {
        // Crear el frame principal básico
        frameContenedor = new JFrame(EstilosApp.NOMBRE_APP);
        frameContenedor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameContenedor.setSize(1920, 1080);
        frameContenedor.setLocationRelativeTo(null);
        frameContenedor.setIconImage(ImagenUtil.cargarImagen("/images/hispania_icon.png"));
        
        // Crear panel contenedor principal
        panelContenedor = new JPanel(new BorderLayout());
        panelContenedor.setBackground(EstilosApp.COLOR_FONDO);
        panelContenedor.setBorder(new EmptyBorder(0, 0, 0, 0));
        frameContenedor.setContentPane(panelContenedor);
        
        // Mostrar la ventana principal
        frameContenedor.setVisible(true);
        
        // Mostrar la pantalla principal por defecto
        mostrarVentana(TipoVentana.PRINCIPAL);
    }
    
    /**
     * Inicializa el frame contenedor principal.
     * @param frame JFrame principal de la aplicación
     * @param panelContenedor Panel donde se mostrarán las diferentes ventanas
     */
    public void inicializar(JFrame frame, JPanel panelContenedor) {
        this.frameContenedor = frame;
        this.panelContenedor = panelContenedor;
    }
    
    /**
     * Inicializa las ventanas disponibles en la aplicación.
     */
    private void inicializarVentanas() {
        // Inicializar todas las ventanas disponibles
        ventanas.put(TipoVentana.PRINCIPAL, new VentanaPrincipal());
        ventanas.put(TipoVentana.LOGIN, new VentanaLogin());
        ventanas.put(TipoVentana.REGISTRO, new VentanaRegistro());
        ventanas.put(TipoVentana.CURSOS, new VentanaCursos());
        ventanas.put(TipoVentana.DETALLE_CURSO, new VentanaDetalleCurso());
    }
    
    /**
     * Muestra una ventana específica.
     * @param tipo Tipo de ventana a mostrar
     */
    public void mostrarVentana(TipoVentana tipo) {
        if (ventanaActual != null) {
            ventanaActual.alOcultar();
        }
        
        Ventana nuevaVentana = ventanas.get(tipo);
        if (nuevaVentana != null) {
            panelContenedor.removeAll();
            panelContenedor.add(nuevaVentana.getPanelPrincipal());
            panelContenedor.revalidate();
            panelContenedor.repaint();
            
            ventanaActual = nuevaVentana;
            ventanaActual.alMostrar();
            
            frameContenedor.setTitle(EstilosApp.NOMBRE_APP + " - " + tipo.toString());
        }
    }
    
    /**
     * Obtiene la ventana actual.
     * @return La ventana actual
     */
    public Ventana getVentanaActual() {
        return ventanaActual;
    }
}
