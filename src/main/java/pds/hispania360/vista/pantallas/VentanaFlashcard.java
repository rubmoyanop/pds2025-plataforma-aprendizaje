package pds.hispania360.vista.pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pds.hispania360.modelo.ejercicios.Flashcard;

public class VentanaFlashcard extends VentanaEjercicio {
    private Flashcard flashcard;
    // Usamos un contenedor con CardLayout para alternar entre frente y atrás.
    private JPanel panelContenedor;
    private final String FRONT = "FRONT";
    private final String BACK = "BACK";
    
    // Constructor recibe la flashcard a mostrar
    public VentanaFlashcard(Flashcard flashcard) {
        super();
        this.flashcard = flashcard;
        initComponents();
    }
    
    private void initComponents() {
        panelPrincipal.setLayout(new BorderLayout());
        // Encabezado con la estética de la aplicación
        JLabel titulo = new JLabel("Ejercicio Flashcard", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        
        // Creamos el panel contenedor con CardLayout para la transición
        panelContenedor = new JPanel(new CardLayout());
        
        // Panel del frente (pregunta o "Sabías que?")
        JPanel frontPanel = new JPanel();
        frontPanel.setLayout(new BoxLayout(frontPanel, BoxLayout.Y_AXIS));
        JLabel labelFrente = new JLabel(flashcard.getEnunciado());
        labelFrente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelFrente.setAlignmentX(Component.CENTER_ALIGNMENT);
        frontPanel.add(labelFrente);
        frontPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Botón para mostrar la respuesta con transición
        JButton btnMostrar = new JButton("Mostrar Respuesta");
        btnMostrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        frontPanel.add(btnMostrar);
        
        // Panel de atrás (respuesta)
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
        JLabel labelDetras = new JLabel(flashcard.getDetras());
        labelDetras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelDetras.setAlignmentX(Component.CENTER_ALIGNMENT);
        backPanel.add(labelDetras);
        
        // Agregamos ambos paneles al contenedor con distintos identificadores
        panelContenedor.add(frontPanel, FRONT);
        panelContenedor.add(backPanel, BACK);
        
        panelPrincipal.add(panelContenedor, BorderLayout.CENTER);
        
        // Acción del botón: simula una transición (podemos extender con Timer para efectos más avanzados)
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Con CardLayout se cambia de "FRONT" a "BACK", mostrando la respuesta.
                CardLayout cl = (CardLayout)(panelContenedor.getLayout());
                cl.show(panelContenedor, BACK);
            }
        });
    }
    
    @Override
    public void mostrarEjercicio() {
        // Se asume que GestorVentanas cuenta con un método mostrarPanel(JPanel panel)
        // para actualizar la ventana principal de la aplicación.
    }
    
    @Override
    public boolean validarRespuesta(String respuesta) {
        // No se valida una respuesta en un ejercicio tipo flashcard.
        return true;
    }
}