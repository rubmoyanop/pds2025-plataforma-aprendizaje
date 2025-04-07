package pds.hispania360.vista.pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pds.hispania360.modelo.ejercicios.RellenarHueco;

public class VentanaRellenarHueco extends VentanaEjercicio {
    private RellenarHueco ejercicio;
    // Usamos CardLayout para la transición (de pregunta con entrada a feedback)
    private JPanel panelContenedor;
    private final String FRONT = "FRONT";
    private final String BACK = "BACK";
    
    public VentanaRellenarHueco(RellenarHueco ejercicio) {
        super();
        this.ejercicio = ejercicio;
        initComponents();
    }
    
    private void initComponents() {
        panelPrincipal.setLayout(new BorderLayout());
        // Encabezado con la estética de la aplicación
        JLabel titulo = new JLabel("Ejercicio Rellenar Hueco", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        
        // Contenedor con CardLayout para la transición
        panelContenedor = new JPanel(new CardLayout());
        
        // Panel frontal: muestra el enunciado y permite ingresar la respuesta
        JPanel frontPanel = new JPanel();
        frontPanel.setLayout(new BoxLayout(frontPanel, BoxLayout.Y_AXIS));
        JLabel labelEnunciado = new JLabel(ejercicio.getEnunciado());
        labelEnunciado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelEnunciado.setAlignmentX(Component.CENTER_ALIGNMENT);
        frontPanel.add(labelEnunciado);
        frontPanel.add(Box.createRigidArea(new Dimension(0,15)));
        
        JTextField campoRespuesta = new JTextField(20);
        campoRespuesta.setMaximumSize(new Dimension(300, 30));
        campoRespuesta.setAlignmentX(Component.CENTER_ALIGNMENT);
        frontPanel.add(campoRespuesta);
        frontPanel.add(Box.createRigidArea(new Dimension(0,15)));
        
        JButton btnComprobar = new JButton("Comprobar Respuesta");
        btnComprobar.setAlignmentX(Component.CENTER_ALIGNMENT);
        frontPanel.add(btnComprobar);
        
        // Panel trasero: muestra retroalimentación en caso de respuesta correcta
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
        JLabel labelFeedback = new JLabel("¡Correcto!");
        labelFeedback.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        backPanel.add(labelFeedback);
        
        // Agregamos los paneles con identificadores distintos
        panelContenedor.add(frontPanel, FRONT);
        panelContenedor.add(backPanel, BACK);
        panelPrincipal.add(panelContenedor, BorderLayout.CENTER);
        
        // Acción del botón: si la respuesta es correcta, se muestra el panel trasero; sino se avisará.
        btnComprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String respuesta = campoRespuesta.getText();
                if(validarRespuesta(respuesta)){
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
