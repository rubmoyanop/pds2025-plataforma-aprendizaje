package pds.hispania360;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

public class App {

    // Colores de la aplicación
    private static final Color COLOR_PRIMARIO = new Color(198, 40, 40);     // Rojo oscuro (de la bandera española)
    private static final Color COLOR_FONDO = new Color(245, 245, 245);      // Fondo claro
    private static final Color COLOR_BOTON = new Color(198, 40, 40);        // Color de botones
    private static final Color COLOR_BOTON_HOVER = new Color(229, 57, 53);  // Color cuando el cursor está sobre el botón
    private static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 32);
    private static final Font FUENTE_SUBTITULO = new Font("Segoe UI", Font.PLAIN, 18);
    private static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 16);
    
    public static void main(String[] args) {
        // Configurar FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            // Personalizar componentes UI
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("Button.foreground", COLOR_PRIMARIO);
            UIManager.put("Panel.background", COLOR_FONDO);
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Error al configurar FlatLaf: " + e.getMessage());
        }

        // Ejecutar en el EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            crearVentanaInicio();
        });
    }

    private static void crearVentanaInicio() {
        JFrame ventana = new JFrame("Hispania 360");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1920, 1080);
        ventana.setLocationRelativeTo(null);
        ventana.setIconImage(cargarImagen("/images/hispania_icon.png"));
        
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBackground(COLOR_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));
        
        // Panel superior con logo e información
        JPanel panelSuperior = crearPanelSuperior();
        
        // Panel central con imagen de fondo
        JPanel panelCentral = crearPanelCentral();

        // Panel inferior con botones de acción
        JPanel panelInferior = crearPanelInferior();
        
        // Agregar paneles al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        // Agregar panel principal a la ventana
        ventana.setContentPane(panelPrincipal);
        
        // Mostrar ventana
        ventana.setVisible(true);
    }
    
    private static JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        // Logo y título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setOpaque(false);
        
        // Logo
        JLabel labelLogo = new JLabel();
        labelLogo.setIcon(new ImageIcon(cargarImagen("/images/hispania_logo.png").getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        
        // Título
        JLabel labelTitulo = new JLabel("Hispania 360");
        labelTitulo.setFont(FUENTE_TITULO);
        labelTitulo.setForeground(COLOR_PRIMARIO);
        
        panelTitulo.add(labelLogo);
        panelTitulo.add(Box.createRigidArea(new Dimension(15, 0)));  // Espacio entre logo y título
        panelTitulo.add(labelTitulo);
        
        // Subtítulo
        JLabel labelSubtitulo = new JLabel("Plataforma interactiva de aprendizaje sobre la Historia de España", SwingConstants.CENTER);
        labelSubtitulo.setFont(FUENTE_SUBTITULO);
        labelSubtitulo.setBorder(new EmptyBorder(5, 0, 15, 0));
        
        // Agregar componentes al panel
        panel.add(panelTitulo, BorderLayout.CENTER);
        panel.add(labelSubtitulo, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private static JPanel crearPanelCentral() {
        // Panel principal con imagen de fondo
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imgFondo = cargarImagen("/images/spain_map_bg.png");
                if (imgFondo != null) {
                    g.drawImage(imgFondo, 0, 0, getWidth(), getHeight(), this);
                }
                
                // Aplicar capa semitransparente para mejorar visibilidad del texto
                g.setColor(new Color(255, 255, 255, 200));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        
        // Panel con texto descriptivo
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        panelInfo.setBorder(new EmptyBorder(20, 40, 20, 40));
        
        // Texto descriptivo
        JTextPane textoDescriptivo = new JTextPane();
        textoDescriptivo.setContentType("text/html");
        textoDescriptivo.setText(
            "<html><div style='font-family:Segoe UI; font-size:16px; text-align:center;'>" +
            "<h2 style='color:#C62828;'>Bienvenido a la aventura histórica</h2>" +
            "<p style='text-align:center;'>Hispania 360 te ofrece una experiencia única para descubrir " +
            "la rica historia de España desde los primeros asentamientos hasta la actualidad.</p>" +
            "<p style='text-align:center;'>A través de cursos interactivos, mapas históricos y " +
            "cuestionarios, podrás sumergirte en los eventos más importantes que dieron forma " +
            "a la identidad española.</p>" +
            "<p style='text-align:center; font-weight:bold;'>¡Comienza tu viaje histórico ahora!</p>" +
            "</div></html>"
        );
        textoDescriptivo.setEditable(false);
        textoDescriptivo.setOpaque(false);
        
        panelInfo.add(Box.createVerticalGlue());
        panelInfo.add(textoDescriptivo);
        panelInfo.add(Box.createVerticalGlue());
        
        panel.add(panelInfo, BorderLayout.CENTER);
        
        return panel;
    }
    
    private static JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 0));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        // Botones con efecto hover
        JButton btnExplorar = crearBotonPersonalizado("Explorar cursos", e -> {
            JOptionPane.showMessageDialog(null, "Función de explorar cursos en desarrollo", 
                "Hispania 360", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JButton btnComenzar = crearBotonPersonalizado("Comenzar aventura", e -> {
            JOptionPane.showMessageDialog(null, "¡Pronto podrás iniciar tu aventura histórica!", 
                "Hispania 360", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JButton btnAcercaDe = crearBotonPersonalizado("Acerca del proyecto", e -> {
            JOptionPane.showMessageDialog(null, 
                "Hispania 360 - Proyecto desarrollado para Procesos de Desarrollo de Software\n\n" +
                "Equipo de Desarrollo:\n" +
                "- Antonio Teruel Ruiz\n" +
                "- Rubén Moyano Palazón\n" +
                "- Alejandro Cerezal Jiménez", 
                "Acerca de Hispania 360", JOptionPane.INFORMATION_MESSAGE);
        });
        
        panel.add(btnExplorar);
        panel.add(btnComenzar);
        panel.add(btnAcercaDe);
        
        return panel;
    }
    
    private static JButton crearBotonPersonalizado(String texto, ActionListener action) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Color de fondo según si el ratón está encima o no
                if (getModel().isRollover()) {
                    g2.setColor(COLOR_BOTON_HOVER);
                } else {
                    g2.setColor(COLOR_BOTON);
                }
                
                // Dibujar fondo redondeado
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
                
                // Dibujar texto
                FontMetrics fm = g2.getFontMetrics();
                Rectangle stringBounds = fm.getStringBounds(this.getText(), g2).getBounds();
                
                int textX = (getWidth() - stringBounds.width) / 2;
                int textY = (getHeight() - stringBounds.height) / 2 + fm.getAscent();
                
                g2.setColor(Color.WHITE);
                g2.setFont(FUENTE_BOTON);
                g2.drawString(getText(), textX, textY);
                g2.dispose();
            }
        };
        
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setFont(FUENTE_BOTON);
        boton.setForeground(Color.WHITE);
        boton.addActionListener(action);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(200, 50));
        
        return boton;
    }
    
    private static Image cargarImagen(String ruta) {
        try {
            InputStream is = App.class.getResourceAsStream(ruta);
            if (is != null) {
                return new ImageIcon(ImageIO.read(is)).getImage();
            } else {
                // Si no encuentra la imagen, usar una imagen por defecto o null
                System.err.println("No se encontró el recurso: " + ruta);
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            return null;
        }
    }
}