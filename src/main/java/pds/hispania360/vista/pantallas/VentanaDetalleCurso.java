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
 * Ventana que muestra el detalle de un curso específico.
 */
public class VentanaDetalleCurso implements Ventana {
    private JPanel panelPrincipal;
    
    public VentanaDetalleCurso() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBackground(App.COLOR_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));
        
        // Panel superior con título y navegación
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        
        JLabel labelTitulo = new JLabel("Hispania Romana", SwingConstants.CENTER);
        labelTitulo.setFont(App.FUENTE_TITULO);
        labelTitulo.setForeground(App.COLOR_PRIMARIO);
        
        JPanel panelNavegacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNavegacion.setOpaque(false);
        
        JButton btnVolver = new JButton("← Volver a Cursos");
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setForeground(App.COLOR_PRIMARIO);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> {
            GestorVentanas.getInstancia().mostrarVentana(TipoVentana.CURSOS);
        });
        
        panelNavegacion.add(btnVolver);
        
        panelSuperior.add(panelNavegacion, BorderLayout.NORTH);
        panelSuperior.add(labelTitulo, BorderLayout.CENTER);
        
        // Panel central con información del curso
        JPanel panelCentral = new JPanel(new BorderLayout(20, 20));
        panelCentral.setOpaque(false);
        
        // Imagen del curso
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setOpaque(false);
        
        JLabel labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
        Image imagen = App.cargarImagen("/images/romana.png");
        if (imagen != null) {
            labelImagen.setIcon(new ImageIcon(imagen.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
        } else {
            labelImagen.setText("[Imagen no disponible]");
        }
        
        panelImagen.add(labelImagen, BorderLayout.CENTER);
        
        // Descripción del curso
        JPanel panelDescripcion = new JPanel();
        panelDescripcion.setLayout(new BoxLayout(panelDescripcion, BoxLayout.Y_AXIS));
        panelDescripcion.setOpaque(false);
        panelDescripcion.setBorder(new EmptyBorder(0, 20, 0, 20));
        
        JLabel labelSubtitulo = new JLabel("Conquista y romanización de la península");
        labelSubtitulo.setFont(App.FUENTE_SUBTITULO);
        
        JTextArea areaDescripcion = new JTextArea(
            "Este curso explora la transformación de la península ibérica bajo la influencia " +
            "romana, desde la llegada de los primeros legionarios en el siglo III a.C. hasta " +
            "el asentamiento de una de las provincias más prósperas del Imperio.\n\n" +
            "Temas principales:\n" +
            "• Segunda Guerra Púnica y entrada de Roma en Hispania\n" +
            "• Las guerras celtíberas y lusitanas\n" +
            "• La organización provincial de Hispania\n" +
            "• Ciudades, vías y monumentos romanos\n" +
            "• La economía y sociedad hispanorromana\n" +
            "• La cultura y el legado de Roma en España"
        );
        areaDescripcion.setEditable(false);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setOpaque(false);
        
        JPanel panelModulos = new JPanel(new GridLayout(3, 1, 10, 10));
        panelModulos.setOpaque(false);
        panelModulos.setBorder(BorderFactory.createTitledBorder("Módulos del curso"));
        
        panelModulos.add(crearPanelModulo("Módulo 1: La conquista romana", "8 lecciones - 3 cuestionarios"));
        panelModulos.add(crearPanelModulo("Módulo 2: La Hispania republicana", "6 lecciones - 2 cuestionarios"));
        panelModulos.add(crearPanelModulo("Módulo 3: Hispania imperial", "7 lecciones - 4 cuestionarios"));
        
        panelDescripcion.add(labelSubtitulo);
        panelDescripcion.add(Box.createRigidArea(new Dimension(0, 15)));
        panelDescripcion.add(areaDescripcion);
        panelDescripcion.add(Box.createRigidArea(new Dimension(0, 20)));
        panelDescripcion.add(panelModulos);
        
        // Ensamblar panel central
        panelCentral.add(panelImagen, BorderLayout.NORTH);
        panelCentral.add(panelDescripcion, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelInferior.setOpaque(false);
        
        CustomButton btnInscribirse = new CustomButton("Inscribirse al Curso", e -> {
            JOptionPane.showMessageDialog(panelPrincipal, 
                "¡Te has inscrito con éxito al curso 'Hispania Romana'!",
                "Inscripción Completada", JOptionPane.INFORMATION_MESSAGE);
        });
        
        CustomButton btnPDF = new CustomButton("Descargar Temario PDF", e -> {
            JOptionPane.showMessageDialog(panelPrincipal, 
                "Descargando temario en PDF...",
                "Descarga Iniciada", JOptionPane.INFORMATION_MESSAGE);
        });
        
        panelInferior.add(btnInscribirse);
        panelInferior.add(btnPDF);
        
        // Ensamblar panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelModulo(String titulo, String info) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel labelInfo = new JLabel(info);
        labelInfo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        
        JButton btnVer = new JButton("Ver contenido");
        btnVer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVer.addActionListener(e -> {
            JOptionPane.showMessageDialog(panelPrincipal, 
                "Esta funcionalidad estará disponible pronto",
                "Próximamente", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JPanel panelTexto = new JPanel(new GridLayout(2, 1));
        panelTexto.setOpaque(false);
        panelTexto.add(labelTitulo);
        panelTexto.add(labelInfo);
        
        panel.add(panelTexto, BorderLayout.CENTER);
        panel.add(btnVer, BorderLayout.EAST);
        
        return panel;
    }
    
    @Override
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    @Override
    public void alMostrar() {
        // Acciones al mostrar la ventana
    }
    
    @Override
    public void alOcultar() {
        // Acciones al ocultar la ventana
    }
    
    @Override
    public TipoVentana getTipo() {
        return TipoVentana.DETALLE_CURSO;
    }
}
