package pds.hispania360.vista.pantallas;

import pds.hispania360.vista.componentes.Cabecera;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.core.Ventana;
import pds.hispania360.vista.util.EstilosApp;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la aplicación.
 */
public class VentanaPrincipal implements Ventana {
    private JPanel panelPrincipal;
    
    public VentanaPrincipal() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout(0, 0));
        panelPrincipal.setBackground(EstilosApp.COLOR_FONDO);
        
        // Usar el componente Cabecera en lugar de crear la barra de navegación directamente
        Cabecera cabecera = new Cabecera();
        
        // Agregar componentes al panel principal
        panelPrincipal.add(cabecera, BorderLayout.NORTH);
    }
    
    // Eliminado el método crearBarraNavegacion() ya que se ha movido a la clase Cabecera
    
    @Override
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    @Override
    public void alMostrar() {
        // Acciones al mostrar la ventana principal
    }
    
    @Override
    public void alOcultar() {
        // Acciones al ocultar la ventana principal
    }
    
    @Override
    public TipoVentana getTipo() {
        return TipoVentana.PRINCIPAL;
    }
}