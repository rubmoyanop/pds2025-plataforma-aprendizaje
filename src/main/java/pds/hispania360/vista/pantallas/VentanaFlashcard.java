package pds.hispania360.vista.pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pds.hispania360.controlador.Controlador;
import pds.hispania360.factoria.FactoriaVentanaEjercicio;
import pds.hispania360.modelo.ejercicios.Ejercicio;
import pds.hispania360.modelo.ejercicios.Flashcard;
import pds.hispania360.modelo.ejercicios.RellenarHueco;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;

public class VentanaFlashcard extends VentanaEjercicio {
    private Flashcard flashcard;
    // Usamos un contenedor con CardLayout para alternar entre frente y atrás.
    private JPanel panelContenedor;
    private final String FRONT = "FRONT";
    private final String BACK = "BACK";
    
    // Constructor recibe la flashcard a mostrar
    public VentanaFlashcard(){
        flashcard = null;
        initComponents();
    }
    
    public VentanaFlashcard(Flashcard flashcard) {
        super();
        
        this.flashcard = flashcard;
        initComponents();
    }
    
    public void initComponents() {
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
        JLabel labelFrente = new JLabel("¿Sabías que...?");
        if(flashcard != null){
            labelFrente.setText(flashcard.getEnunciado()); // Mensaje de la flashcard
        } 
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
        JLabel labelDetras = new JLabel("Respuesta: ");
        if(flashcard != null){
            labelDetras.setText(flashcard.getDetras()); // Mensaje de la flashcard
        }
        
        labelDetras.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelDetras.setAlignmentX(Component.CENTER_ALIGNMENT);
        backPanel.add(labelDetras);

        JButton btnSiguiente = new JButton("Siguiente Pregunta");
        btnSiguiente.setAlignmentX(Component.CENTER_ALIGNMENT);
        backPanel.add(btnSiguiente);
        
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
        // No se valida una respuesta en un ejercicio tipo flashcard.
        return true;
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
        return pds.hispania360.vista.core.TipoVentana.FLASHCARD;
    }

      @Override
    public void recargar() {
        if(Controlador.INSTANCIA.getEjercicioActual() instanceof Flashcard) {
            this.flashcard = (Flashcard) Controlador.INSTANCIA.getEjercicioActual();
        } 
        else {
            this.flashcard = null;
        }
        panelPrincipal.removeAll();
        initComponents();
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
}