package pds.hispania360.vista.pantallas;

import pds.hispania360.App;
import pds.hispania360.vista.componentes.CustomButton;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.core.Ventana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBackground(App.COLOR_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(50, 50, 50, 50));
        
        // Panel central con logo y mensaje de bienvenida
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);
        
        // Logo
        JLabel labelLogo = new JLabel();
        labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        Image logo = App.cargarImagen("/images/hispania_logo.png");
        if (logo != null) {
            labelLogo.setIcon(new ImageIcon(logo.getScaledInstance(400, 250, Image.SCALE_SMOOTH)));
        } else {
            labelLogo.setText("HISPANIA 360");
            labelLogo.setFont(new Font("Arial", Font.BOLD, 60));
            labelLogo.setForeground(App.COLOR_PRIMARIO);
        }
        
        // Título de bienvenida
        JLabel labelBienvenida = new JLabel("Bienvenido a la plataforma de aprendizaje", SwingConstants.CENTER);
        labelBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelBienvenida.setFont(App.FUENTE_TITULO);
        labelBienvenida.setForeground(App.COLOR_PRIMARIO);
        
        // Descripción
        JTextArea areaDescripcion = new JTextArea(
            "Explora la historia de España de manera interactiva con cursos especializados, " +
            "ejercicios prácticos y recursos multimedia. Sumérgete en un viaje a través del tiempo " +
            "que te permitirá conocer los eventos más importantes que han moldeado la identidad española."
        );
        areaDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        areaDescripcion.setEditable(false);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setOpaque(false);
        areaDescripcion.setFont(App.FUENTE_SUBTITULO);
        areaDescripcion.setForeground(new Color(60, 60, 60));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setOpaque(false);
        
        CustomButton btnLogin = new CustomButton("Iniciar Sesión", e -> {
            GestorVentanas.getInstancia().mostrarVentana(TipoVentana.LOGIN);
        });
        
        CustomButton btnCursos = new CustomButton("Ver Cursos", e -> {
            GestorVentanas.getInstancia().mostrarVentana(TipoVentana.CURSOS);
        });
        
        panelBotones.add(btnLogin);
        panelBotones.add(btnCursos);
        
        // Agregar todos los componentes al panel central
        panelCentral.add(Box.createVerticalGlue());
        panelCentral.add(labelLogo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 30)));
        panelCentral.add(labelBienvenida);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 30)));
        panelCentral.add(areaDescripcion);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 50)));
        panelCentral.add(panelBotones);
        panelCentral.add(Box.createVerticalGlue());
        
        // Agregar todo al panel principal
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
    }
    
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