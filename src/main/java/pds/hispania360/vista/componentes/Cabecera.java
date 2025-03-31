package pds.hispania360.vista.componentes;

import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.util.EstilosApp;
import pds.hispania360.vista.util.ImagenUtil;
import pds.hispania360.sesion.Sesion;
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
                GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.PRINCIPAL);
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
                            GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.PRINCIPAL);
                            break;
                        case "Cursos":
                            GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.CURSOS);
                            break;
                        // Añadir más casos según sea necesario
                    }
                }
            });
            
            navLinks.add(linkLabel);
        }
        
        // Verificar si hay sesión activa
        if (Sesion.INSTANCIA.haySesion()) {
            // Crear un icono de perfil en lugar de mostrar toda la información
            JLabel iconoPerfil = new JLabel();
            ImageIcon avatarIcon = new ImageIcon(ImagenUtil.cargarImagen("/images/avatar_default.png")
                    .getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            iconoPerfil.setIcon(avatarIcon);
            iconoPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
            iconoPerfil.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
            
            // Añadir un borde circular al icono para estilizarlo
            iconoPerfil.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(EstilosApp.COLOR_PRIMARIO, 2),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)
            ));
            
            // Menú desplegable para el usuario
            JPopupMenu userMenu = new JPopupMenu();
            
            // Añadir encabezado al menú con nombre del usuario
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(EstilosApp.COLOR_TARJETA);
            headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            
            JLabel nombreUsuario = new JLabel(Sesion.INSTANCIA.getUsuarioActual().getNombre());
            nombreUsuario.setFont(new Font(EstilosApp.FUENTE_NAVBAR.getFamily(), Font.BOLD, 12));
            nombreUsuario.setForeground(EstilosApp.COLOR_TEXTO);
            
            JLabel tipoUsuario = new JLabel(Sesion.INSTANCIA.esCreador() ? "Creador" : "Estudiante");
            tipoUsuario.setFont(new Font(EstilosApp.FUENTE_NAVBAR.getFamily(), Font.ITALIC, 10));
            tipoUsuario.setForeground(EstilosApp.COLOR_TEXTO_SECUNDARIO);
            
            JPanel infoUsuario = new JPanel();
            infoUsuario.setLayout(new BoxLayout(infoUsuario, BoxLayout.Y_AXIS));
            infoUsuario.setOpaque(false);
            infoUsuario.add(nombreUsuario);
            infoUsuario.add(tipoUsuario);
            
            headerPanel.add(infoUsuario, BorderLayout.CENTER);
            
            // Agregar panel personalizado al menú
            userMenu.add(headerPanel);
            userMenu.addSeparator();
            
            // Opciones del menú
            JMenuItem miPerfil = new JMenuItem("Mi Perfil");
            miPerfil.setIcon(new ImageIcon(ImagenUtil.cargarImagen("/images/perfil_icon.png")
                    .getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        
            JMenuItem cerrarSesion = new JMenuItem("Cerrar Sesión");
            cerrarSesion.setIcon(new ImageIcon(ImagenUtil.cargarImagen("/images/logout_icon.png")
                    .getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
            
            miPerfil.addActionListener(e -> GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.PERFIL));
            
            userMenu.add(miPerfil);
            userMenu.addSeparator();
            userMenu.add(cerrarSesion);
            
            // Mostrar menú al hacer clic en el icono
            iconoPerfil.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    userMenu.show(iconoPerfil, 0, iconoPerfil.getHeight());
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Efecto hover - resaltar el borde 
                    iconoPerfil.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(EstilosApp.COLOR_SECUNDARIO, 2),
                        BorderFactory.createEmptyBorder(2, 2, 2, 2)
                    ));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    // Volver al borde normal
                    iconoPerfil.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(EstilosApp.COLOR_PRIMARIO, 2),
                        BorderFactory.createEmptyBorder(2, 2, 2, 2)
                    ));
                }
            });
            
            navLinks.add(iconoPerfil);
        } else {
            // Botón de inicio de sesión con icono
            JButton btnLogin = new JButton("Iniciar Sesión");
            btnLogin.setFont(EstilosApp.FUENTE_NAVBAR);
            btnLogin.setForeground(Color.WHITE);
            btnLogin.setBackground(EstilosApp.COLOR_PRIMARIO);
            btnLogin.setBorder(new EmptyBorder(8, 20, 8, 20));
            btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnLogin.setFocusPainted(false);
            
            // Añadir icono al botón
            ImageIcon loginIcon = new ImageIcon(ImagenUtil.cargarImagen("/images/login_icon.png")
                    .getScaledInstance(16, 16, Image.SCALE_SMOOTH));
            btnLogin.setIcon(loginIcon);
            btnLogin.setIconTextGap(10);
            
            // Efectos hover para el botón
            btnLogin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btnLogin.setBackground(EstilosApp.COLOR_PRIMARIO.darker());
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    btnLogin.setBackground(EstilosApp.COLOR_PRIMARIO);
                }
            });
            
            btnLogin.addActionListener(e -> GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.LOGIN));
            
            navLinks.add(btnLogin);
        }
        
        add(logo, BorderLayout.WEST);
        add(navLinks, BorderLayout.EAST);
    }
}
