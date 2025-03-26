package pds.hispania360.vista.componentes;

import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.util.EstilosApp;
import pds.hispania360.vista.util.ImagenUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Componente de cabecera reutilizable para todas las pantallas de la aplicación.
 */
public class Cabecera extends JPanel {
    
    /**
     * Constructor de la cabecera.
     */
    public Cabecera() {
        initialize();
    }
    
    /**
     * Constructor con opciones para personalizar los enlaces mostrados.
     * @param mostrarBotonesAdicionales Si es true, muestra botones adicionales según sea necesario
     */
    public Cabecera(boolean mostrarBotonesAdicionales) {
        initialize();
        // Añaidr botones adicionales según sea necesario
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(EstilosApp.COLOR_TARJETA);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, EstilosApp.COLOR_BORDE),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        // Logo pequeño para la barra
        JLabel logo = new JLabel("HISPANIA 360");
        logo.setFont(EstilosApp.FUENTE_NAVBAR);
        logo.setForeground(EstilosApp.COLOR_PRIMARIO);
        
        Image imgLogo = ImagenUtil.cargarImagen("/images/hispania_logo.png");
        if (imgLogo != null) {
            logo.setIcon(new ImageIcon(imgLogo.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
            logo.setIconTextGap(10);
        }
        
        // Hacer el logo clickeable para volver a la página principal
        logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GestorVentanas.getInstancia().mostrarVentana(TipoVentana.PRINCIPAL);
            }
        });
        
        // Panel de navegación
        JPanel navLinks = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navLinks.setOpaque(false);
        
        String[] enlaces = {"Inicio", "Cursos"};
        for (String enlace : enlaces) {
            JLabel linkLabel = new JLabel(enlace);
            linkLabel.setFont(EstilosApp.FUENTE_NAVBAR);
            linkLabel.setForeground(EstilosApp.COLOR_TEXTO_SECUNDARIO);
            linkLabel.setBorder(new EmptyBorder(5, 15, 5, 15));
            linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Efecto hover
            linkLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Navegación según el enlace
                    switch (enlace) {
                        case "Inicio":
                            GestorVentanas.getInstancia().mostrarVentana(TipoVentana.PRINCIPAL);
                            break;
                        case "Cursos":
                            GestorVentanas.getInstancia().mostrarVentana(TipoVentana.CURSOS);
                            break;
                        // Añadir más casos según sea necesario
                    }
                }
            });
            
            navLinks.add(linkLabel);
        }
        
        // Botón de inicio de sesión en la barra
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(EstilosApp.FUENTE_NAVBAR);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(EstilosApp.COLOR_PRIMARIO);
        btnLogin.setBorder(new EmptyBorder(8, 20, 8, 20));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(e -> GestorVentanas.getInstancia().mostrarVentana(TipoVentana.LOGIN));
        
        navLinks.add(btnLogin);
        
        add(logo, BorderLayout.WEST);
        add(navLinks, BorderLayout.EAST);
    }
}
