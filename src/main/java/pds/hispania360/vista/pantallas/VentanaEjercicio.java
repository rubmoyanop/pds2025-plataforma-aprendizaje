package pds.hispania360.vista.pantallas;

import javax.swing.JPanel;

import pds.hispania360.vista.core.Recargable;
import pds.hispania360.vista.core.Ventana;

public abstract class VentanaEjercicio implements Ventana, Recargable {
    protected JPanel panelPrincipal;
    
    // Constructor común que puede inicializar el panel principal
    public VentanaEjercicio() {
        // ...inicialización común...
        panelPrincipal = new JPanel();
    }
    
    // Devuelve el panel principal de la ventana
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    // Método para mostrar la ventana de ejercicio; se puede definir comportamiento común o abstracto
    public abstract void mostrarEjercicio();
    
    // Método para validar la respuesta ingresada por el usuario
    public abstract boolean validarRespuesta(String respuesta);
    
}
