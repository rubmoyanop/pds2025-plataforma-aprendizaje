package pds.hispania360.vista.pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import pds.hispania360.controlador.Controlador;
import pds.hispania360.factoria.FactoriaVentanaEjercicio;
import pds.hispania360.modelo.ejercicios.Ejercicio;
import pds.hispania360.modelo.ejercicios.RellenarHueco;
import pds.hispania360.modelo.ejercicios.RespuestaMultiple;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;

import java.util.List;

public class VentanaRespuestaMultiple extends VentanaEjercicio {
    private RespuestaMultiple ejercicio;
    // Contenedor con CardLayout para la transición
    private JPanel panelContenedor;
    private final String FRONT = "FRONT";
    private final String BACK = "BACK";
    private ButtonGroup grupoOpciones;

    public VentanaRespuestaMultiple() {
        ejercicio = null;
        initComponents();
    }   
    
    public VentanaRespuestaMultiple(RespuestaMultiple ejercicio) {
        super();
        this.ejercicio = ejercicio;
        initComponents();
    }
    
    private void initComponents() {
        panelPrincipal.setLayout(new BorderLayout());
        // Encabezado estético
        JLabel titulo = new JLabel("Ejercicio Respuesta Múltiple", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        
        panelContenedor = new JPanel(new CardLayout());
        
        // Panel frontal: muestra la pregunta y las opciones
        JPanel frontPanel = new JPanel();
        frontPanel.setLayout(new BoxLayout(frontPanel, BoxLayout.Y_AXIS));
        JLabel labelEnunciado = new JLabel("¿Sabías que...?");
        if(ejercicio != null){
            labelEnunciado.setText(ejercicio.getEnunciado()); // Mensaje de la flashcard
        } 
        
        labelEnunciado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelEnunciado.setAlignmentX(Component.CENTER_ALIGNMENT);
        frontPanel.add(labelEnunciado);
        frontPanel.add(Box.createRigidArea(new Dimension(0,15)));
        
        // implementación específica para Respuesta Múltiple
        if(ejercicio != null) {
            List<String> opciones = ejercicio.getOpciones();
            grupoOpciones = new ButtonGroup();
            JPanel opcionesPanel = new JPanel();
            opcionesPanel.setLayout(new BoxLayout(opcionesPanel, BoxLayout.Y_AXIS));
            if(opciones != null) {
                for(String opcion : opciones) {
                    JRadioButton radio = new JRadioButton(opcion);
                    radio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    radio.setAlignmentX(Component.CENTER_ALIGNMENT);
                    grupoOpciones.add(radio);
                    opcionesPanel.add(radio);
                    opcionesPanel.add(Box.createRigidArea(new Dimension(0,5)));
                }
            }
            frontPanel.add(opcionesPanel);
            frontPanel.add(Box.createRigidArea(new Dimension(0,15)));
            }
        
        JButton btnConfirmar = new JButton("Confirmar Respuesta");
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        frontPanel.add(btnConfirmar);
        
        // Panel trasero: muestra feedback positivo al ser correcta la respuesta
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
        JLabel labelFeedback = new JLabel("¡Correcto!");
        labelFeedback.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        backPanel.add(labelFeedback);

        JButton btnSiguiente = new JButton("Siguiente Pregunta");
        btnSiguiente.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        JPanel backPanelWrong = new JPanel();
        backPanelWrong.setLayout(new BoxLayout(backPanelWrong, BoxLayout.Y_AXIS));
        JLabel labelFeedbackWrong = new JLabel("Respuesta Incorrecta. Intenta de nuevo.");
        labelFeedbackWrong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelFeedbackWrong.setAlignmentX(Component.CENTER_ALIGNMENT);
        backPanelWrong.add(labelFeedbackWrong);
        

        
        panelContenedor.add(frontPanel, FRONT);
        panelContenedor.add(backPanel, BACK);
        panelContenedor.add(backPanelWrong, "BACKWRONG");
        panelPrincipal.add(panelContenedor, BorderLayout.CENTER);
        
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String respuestaSeleccionada = null;
                for (AbstractButton button : java.util.Collections.list(grupoOpciones.getElements())) {
                    if (button.isSelected()) {
                        respuestaSeleccionada = button.getText();
                        break;
                    }
                }
                if(respuestaSeleccionada == null) {
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar una opción.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(validarRespuesta(respuestaSeleccionada)) {
                    backPanel.add(btnSiguiente);
                    CardLayout cl = (CardLayout)(panelContenedor.getLayout());
                    cl.show(panelContenedor, BACK);
                } else {
                    backPanelWrong.add(btnSiguiente);
                    CardLayout cl = (CardLayout)(panelContenedor.getLayout());
                    cl.show(panelContenedor, "BACKWRONG");
                }
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para cargar el siguiente ejercicio
                
                Ejercicio next = Controlador.INSTANCIA.siguienteEjercicio();
                    if(next != null){
                        VentanaEjercicio ventanaEjercicio = FactoriaVentanaEjercicio.crearVentana(next);
                        // Mostrar el ejercicio en la ventana correspondiente
                        GestorVentanas.INSTANCIA.mostrarVentana(ventanaEjercicio.getTipo());
                        
                            } 
                    else {
                        Controlador.INSTANCIA.actualizarProgresoCurso();
                        GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.DETALLE_CURSO);
                        }
            }
        });
    }
    
    @Override
    public void mostrarEjercicio() {
        
    }
    
    @Override
    public boolean validarRespuesta(String respuesta) {
        return ejercicio.validarRespuesta(respuesta);
    }
    
    // Implementaciones para la interfaz Ventana
    @Override
    public void alMostrar() {
        // Acción al mostrar la ventana (si se requiere)
    }
    
    @Override
    public void alOcultar() {
        // Acción al ocultar la ventana (si se requiere)
    }
    
    @Override
    public pds.hispania360.vista.core.TipoVentana getTipo() {
        return pds.hispania360.vista.core.TipoVentana.RESPUESTA_MULTIPLE;
    }

      @Override
    public void recargar() {
        if(Controlador.INSTANCIA.getEjercicioActual() instanceof RespuestaMultiple) {
            this.ejercicio = (RespuestaMultiple) Controlador.INSTANCIA.getEjercicioActual();
        } 
        else {
            this.ejercicio = null;
        }
        panelPrincipal.removeAll();
        initComponents();
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
}
