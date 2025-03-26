package pds.hispania360.vista.pantallas;

import pds.hispania360.vista.componentes.Cabecera;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.core.Ventana;
import pds.hispania360.vista.util.EstilosApp;
import pds.hispania360.vista.util.ImagenUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ventana que muestra el detalle de un curso específico.
 */
public class VentanaDetalleCurso implements Ventana {
    private JPanel panelPrincipal;
    
    public VentanaDetalleCurso() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(EstilosApp.COLOR_FONDO);
        
        // Agregar cabecera común
        Cabecera cabecera = new Cabecera();
        panelPrincipal.add(cabecera, BorderLayout.NORTH);
        
        // Panel contenedor principal con scroll para todo el contenido
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setBackground(EstilosApp.COLOR_FONDO);
        panelContenedor.setBorder(new EmptyBorder(25, 40, 30, 40));
        
        // Creamos un scroll para todo el contenido
        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Botón volver
        JPanel panelNavegacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNavegacion.setOpaque(false);
        panelNavegacion.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNavegacion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        JButton btnVolver = new JButton("← Volver a Cursos");
        btnVolver.setFont(EstilosApp.FUENTE_BOTON);
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setForeground(EstilosApp.COLOR_PRIMARIO);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> {
            GestorVentanas.getInstancia().mostrarVentana(TipoVentana.CURSOS);
        });
        
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnVolver.setForeground(EstilosApp.COLOR_PRIMARIO.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btnVolver.setForeground(EstilosApp.COLOR_PRIMARIO);
            }
        });
        
        panelNavegacion.add(btnVolver);
        panelContenedor.add(panelNavegacion);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Panel de título del curso
        JLabel labelTitulo = new JLabel("Hispania Romana");
        labelTitulo.setFont(EstilosApp.FUENTE_TITULO);
        labelTitulo.setForeground(EstilosApp.COLOR_PRIMARIO);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel labelSubtitulo = new JLabel("Conquista y romanización de la península");
        labelSubtitulo.setFont(EstilosApp.FUENTE_SUBTITULO);
        labelSubtitulo.setForeground(EstilosApp.COLOR_TEXTO_SECUNDARIO);
        labelSubtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelContenedor.add(labelTitulo);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 5)));
        panelContenedor.add(labelSubtitulo);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Panel de imagen
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setOpaque(false);
        panelImagen.setBorder(BorderFactory.createLineBorder(EstilosApp.COLOR_BORDE, 1, true));
        panelImagen.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelImagen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        
        JLabel labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
        Image imagen = ImagenUtil.cargarImagen("/images/romana.png");
        if (imagen != null) {
            labelImagen.setIcon(new ImageIcon(imagen.getScaledInstance(800, 300, Image.SCALE_SMOOTH)));
        } else {
            labelImagen.setText("[Imagen no disponible]");
            labelImagen.setFont(EstilosApp.FUENTE_SUBTITULO);
        }
        
        panelImagen.add(labelImagen, BorderLayout.CENTER);
        panelContenedor.add(panelImagen);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Dos columnas: descripción y detalles
        JPanel panelDosColumnas = new JPanel();
        panelDosColumnas.setLayout(new BoxLayout(panelDosColumnas, BoxLayout.X_AXIS));
        panelDosColumnas.setOpaque(false);
        panelDosColumnas.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDosColumnas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        
        // Columna izquierda: Descripción
        JPanel panelColumnaIzq = new JPanel();
        panelColumnaIzq.setLayout(new BoxLayout(panelColumnaIzq, BoxLayout.Y_AXIS));
        panelColumnaIzq.setOpaque(false);
        panelColumnaIzq.setBorder(new EmptyBorder(0, 0, 0, 20));
        
        JLabel labelDescTitulo = new JLabel("Descripción del curso");
        labelDescTitulo.setFont(EstilosApp.FUENTE_TARJETA_TITULO);
        labelDescTitulo.setForeground(EstilosApp.COLOR_PRIMARIO);
        labelDescTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
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
        areaDescripcion.setFont(EstilosApp.FUENTE_TARJETA_TEXTO);
        areaDescripcion.setForeground(EstilosApp.COLOR_TEXTO);
        areaDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelColumnaIzq.add(labelDescTitulo);
        panelColumnaIzq.add(Box.createRigidArea(new Dimension(0, 10)));
        panelColumnaIzq.add(areaDescripcion);
        
        // Columna derecha: Detalles y botones
        JPanel panelColumnaDer = new JPanel();
        panelColumnaDer.setLayout(new BoxLayout(panelColumnaDer, BoxLayout.Y_AXIS));
        panelColumnaDer.setOpaque(false);
        panelColumnaDer.setBorder(new EmptyBorder(0, 20, 0, 0));
        panelColumnaDer.setPreferredSize(new Dimension(250, 250));
        panelColumnaDer.setMaximumSize(new Dimension(250, 250));
        
        // Panel tarjeta con los detalles
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new GridLayout(5, 2, 10, 10));
        panelDetalles.setBackground(EstilosApp.COLOR_TARJETA);
        panelDetalles.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(EstilosApp.COLOR_BORDE, 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        añadirFilaDetalle(panelDetalles, "Categoría:", "Antigüedad");
        añadirFilaDetalle(panelDetalles, "Duración:", "15 horas");
        añadirFilaDetalle(panelDetalles, "Valoración:", "4.8 ★★★★★");
        añadirFilaDetalle(panelDetalles, "Lecciones:", "21");
        añadirFilaDetalle(panelDetalles, "Cuestionarios:", "9");
        
        panelDosColumnas.add(panelColumnaIzq);
        panelDosColumnas.add(panelColumnaDer);
        
        panelContenedor.add(panelDosColumnas);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 40)));
        
        // Sección de módulos
        JLabel labelModulosTitulo = new JLabel("Módulos del curso");
        labelModulosTitulo.setFont(EstilosApp.FUENTE_TARJETA_TITULO);
        labelModulosTitulo.setForeground(EstilosApp.COLOR_PRIMARIO);
        labelModulosTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelContenedor.add(labelModulosTitulo);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Panel para módulos
        for (int i = 1; i <= 3; i++) {
            JPanel panelModulo = crearPanelModuloMinimalista(
                "Módulo " + i + (i == 1 ? ": La conquista romana" : 
                                i == 2 ? ": La Hispania republicana" : 
                                ": Hispania imperial"), 
                (i == 1 ? "8" : i == 2 ? "6" : "7") + " lecciones - " + 
                (i == 1 ? "3" : i == 2 ? "2" : "4") + " cuestionarios"
            );
            panelContenedor.add(panelModulo);
            if (i < 3) {
                panelContenedor.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void añadirFilaDetalle(JPanel panel, String etiqueta, String valor) {
        JLabel labelEtiqueta = new JLabel(etiqueta);
        labelEtiqueta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelEtiqueta.setForeground(EstilosApp.COLOR_TEXTO);
        
        JLabel labelValor = new JLabel(valor);
        labelValor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelValor.setForeground(EstilosApp.COLOR_TEXTO);
        
        panel.add(labelEtiqueta);
        panel.add(labelValor);
    }
    
    private JPanel crearPanelModuloMinimalista(String titulo, String info) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(EstilosApp.COLOR_TARJETA);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(EstilosApp.COLOR_BORDE, 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelTitulo.setForeground(EstilosApp.COLOR_TEXTO);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel labelInfo = new JLabel(info);
        labelInfo.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        labelInfo.setForeground(EstilosApp.COLOR_TEXTO_SECUNDARIO);
        labelInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelInfo.add(labelTitulo);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(labelInfo);
        
        JButton btnVer = new JButton("Ver contenido");
        btnVer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVer.setForeground(EstilosApp.COLOR_PRIMARIO);
        btnVer.setBackground(EstilosApp.COLOR_TARJETA);
        btnVer.setBorder(BorderFactory.createLineBorder(EstilosApp.COLOR_PRIMARIO, 1, true));
        btnVer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVer.setFocusPainted(false);
        btnVer.setPreferredSize(new Dimension(130, 35));
        
        btnVer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnVer.setBackground(EstilosApp.COLOR_PRIMARIO);
                btnVer.setForeground(Color.WHITE);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btnVer.setBackground(EstilosApp.COLOR_TARJETA);
                btnVer.setForeground(EstilosApp.COLOR_PRIMARIO);
            }
        });
        
        btnVer.addActionListener(e -> {
            JOptionPane.showMessageDialog(panelPrincipal, 
                "Esta funcionalidad estará disponible pronto",
                "Próximamente", JOptionPane.INFORMATION_MESSAGE);
        });
        
        panel.add(panelInfo, BorderLayout.CENTER);
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
