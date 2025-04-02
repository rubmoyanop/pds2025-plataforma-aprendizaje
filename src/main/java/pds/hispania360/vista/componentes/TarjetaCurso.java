package pds.hispania360.vista.componentes;

import pds.hispania360.modelo.Curso;
import pds.hispania360.sesion.Sesion;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.util.EstilosApp;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Componente que representa una tarjeta de curso en el catálogo.
 */
public class TarjetaCurso extends JPanel {
    private static final Color COLOR_HOVER = new Color(248, 249, 250);
    private Curso curso;
    
    /**
     * Constructor para la tarjeta de curso.
     * 
     * @param titulo Título del curso
     * @param descripcion Descripción breve del curso
     * @param rutaImagen Ruta de la imagen del curso
     * @param categoria Categoría del curso
     */
    public TarjetaCurso(Curso curso) {
        this.curso = curso;
        String titulo = curso.getTitulo();
        String descripcion = curso.getDescripcion();
        inicializar(titulo, descripcion);
    }
    
    private void inicializar(String titulo, String descripcion) {
        setLayout(new BorderLayout(15, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // Efecto hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(COLOR_HOVER);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                Sesion.INSTANCIA.setCursoActual(curso);
                GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.DETALLE_CURSO);
            }
        });
        
        // Panel de información
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        
        // Título del curso
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelTitulo.setForeground(EstilosApp.COLOR_TEXTO);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Descripción del curso
        JTextArea areaDescripcion = new JTextArea(descripcion);
        areaDescripcion.setEditable(false);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setOpaque(false);
        areaDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaDescripcion.setForeground(EstilosApp.COLOR_TEXTO_SECUNDARIO);
        areaDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
        areaDescripcion.setBorder(null);
        
        // Panel para metadatos del curso (valoración, duración)
        JPanel panelMetadatos = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelMetadatos.setOpaque(false);
        panelMetadatos.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Botón de acceso al curso
        CustomButton btnVerCurso = new CustomButton("Ver curso", e -> {
            GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.DETALLE_CURSO);
        });
        btnVerCurso.setPreferredSize(new Dimension(120, 35));
        btnVerCurso.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Agregar componentes al panel de información
        panelInfo.add(labelTitulo);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(areaDescripcion);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        panelInfo.add(panelMetadatos);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 15)));
        panelInfo.add(btnVerCurso);
        
        // Agregar paneles principales a la tarjeta
        add(panelInfo, BorderLayout.CENTER);
        
        // Hacer toda la tarjeta clickeable
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
