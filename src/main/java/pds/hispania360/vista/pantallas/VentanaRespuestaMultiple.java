package pds.hispania360.vista.pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import pds.hispania360.modelo.ejercicios.RespuestaMultiple;
import java.util.List;

public class VentanaRespuestaMultiple extends VentanaEjercicio {
    private RespuestaMultiple ejercicio;
    // Contenedor con CardLayout para la transición
    private JPanel panelContenedor;
    private final String FRONT = "FRONT";
    private final String BACK = "BACK";
    private ButtonGroup grupoOpciones;
    
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
        JLabel labelEnunciado = new JLabel(ejercicio.getEnunciado());
        labelEnunciado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelEnunciado.setAlignmentX(Component.CENTER_ALIGNMENT);
        frontPanel.add(labelEnunciado);
        frontPanel.add(Box.createRigidArea(new Dimension(0,15)));
        
        // Asumimos que RespuestaMultiple posee un método getOpciones() que devuelve List<String>
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
        
        panelContenedor.add(frontPanel, FRONT);
        panelContenedor.add(backPanel, BACK);
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
                    CardLayout cl = (CardLayout)(panelContenedor.getLayout());
                    cl.show(panelContenedor, BACK);
                } else {
                    JOptionPane.showMessageDialog(panelPrincipal, "Respuesta incorrecta. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
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
}
