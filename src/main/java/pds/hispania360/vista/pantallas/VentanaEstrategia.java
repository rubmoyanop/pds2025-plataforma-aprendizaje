package pds.hispania360.vista.pantallas;

import pds.hispania360.controlador.Controlador;
import pds.hispania360.factoria.FactoriaVentanaEjercicio;
import pds.hispania360.modelo.ProgresoCurso;
import pds.hispania360.modelo.ejercicios.Ejercicio;
import pds.hispania360.modelo.ejercicios.Flashcard;
import pds.hispania360.modelo.ejercicios.RellenarHueco;
import pds.hispania360.modelo.ejercicios.RespuestaMultiple;
import pds.hispania360.vista.core.Ventana;
import pds.hispania360.vista.core.Recargable;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.util.EstilosApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana que permite seleccionar la Estrategia de Aprendizaje para un nuevo ProgresoCurso.
 */
public class VentanaEstrategia implements Ventana, Recargable {
    private JPanel panelPrincipal;
    private JComboBox<String> comboEstrategias;
    private JButton btnConfirmar;
    private ProgresoCurso progresoCurso;
    
    public VentanaEstrategia() {
        this.progresoCurso = Controlador.INSTANCIA.getProgresoCursoActual();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(EstilosApp.COLOR_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(25, 40, 30, 40));
        
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setBackground(EstilosApp.COLOR_FONDO);
        
        JLabel labelTitulo = new JLabel("Selecciona tu Estrategia de Aprendizaje");
        labelTitulo.setFont(EstilosApp.FUENTE_TITULO);
        labelTitulo.setForeground(EstilosApp.COLOR_PRIMARIO);
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenedor.add(labelTitulo);
        panelContenedor.add(Box.createRigidArea(new Dimension(0,20)));
        
        // Usamos un JComboBox para simular las opciones de estrategia.
        // Puedes reemplazar estos valores por los que defina tu modelo (por ejemplo, del enum EstrategiaAprendizaje).
        comboEstrategias = new JComboBox<>(new String[] {"Secuencial", "Repeticion Espaciada", "Random"});
        comboEstrategias.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comboEstrategias.setMaximumSize(new Dimension(300, 40));
        comboEstrategias.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenedor.add(comboEstrategias);
        panelContenedor.add(Box.createRigidArea(new Dimension(0,20)));
        
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setBackground(EstilosApp.COLOR_PRIMARIO);
        btnConfirmar.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String estrategiaSeleccionada = (String) comboEstrategias.getSelectedItem();
                if(Controlador.INSTANCIA.configurarEstrategia(progresoCurso, estrategiaSeleccionada)){
                    JOptionPane.showMessageDialog(panelPrincipal, 
                        "Estrategia seleccionada: " + estrategiaSeleccionada,
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    // Obtener el siguiente ejercicio a partir del progreso actual
                    Ejercicio ejercicio = Controlador.INSTANCIA.siguienteEjercicio();
                    if(ejercicio != null){
                        VentanaEjercicio ventanaEjercicio = FactoriaVentanaEjercicio.crearVentana(ejercicio);
                        // Mostrar el ejercicio en la ventana correspondiente
                        GestorVentanas.INSTANCIA.mostrarVentana(ventanaEjercicio.getTipo());
                        
                            } 
                    else {
                        JOptionPane.showMessageDialog(panelPrincipal, 
                            "No hay ejercicio disponible.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panelPrincipal, 
                        "Error al configurar la estrategia.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelContenedor.add(btnConfirmar);
        
        panelPrincipal.add(panelContenedor, BorderLayout.CENTER);
    }
    
    @Override
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    @Override
    public void alMostrar() {
        // Se pueden realizar actualizaciones si fuera necesario
    }
    
    @Override
    public void alOcultar() {
        // Acciones al ocultar la ventana
    }
    
    @Override
    public TipoVentana getTipo() {
        return TipoVentana.ESTRATEGIA;
    }
    
    @Override
    public void recargar() {
        this.progresoCurso = Controlador.INSTANCIA.getProgresoCursoActual();
        panelPrincipal.removeAll();
        inicializarComponentes();
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
}