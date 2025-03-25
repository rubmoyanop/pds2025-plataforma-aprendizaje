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
 * Ventana que muestra el listado de cursos disponibles.
 */
public class VentanaCursos implements Ventana {
    private JPanel panelPrincipal;
    
    public VentanaCursos() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBackground(App.COLOR_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));
        
        // Panel superior con título
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setOpaque(false);
        
        JLabel labelTitulo = new JLabel("Catálogo de Cursos", SwingConstants.CENTER);
        labelTitulo.setFont(App.FUENTE_TITULO);
        labelTitulo.setForeground(App.COLOR_PRIMARIO);
        
        panelSuperior.add(labelTitulo);
        
        // Panel central con lista de cursos
        JPanel panelCentral = new JPanel(new GridLayout(2, 2, 20, 20));
        panelCentral.setOpaque(false);
        
        // Tarjetas de curso
        panelCentral.add(crearTarjetaCurso("Prehistoria Ibérica", "Desde los primeros asentamientos hasta los íberos", "/images/prehistoria.png", 1));
        panelCentral.add(crearTarjetaCurso("Hispania Romana", "Conquista y romanización de la península", "/images/romana.png", 2));
        panelCentral.add(crearTarjetaCurso("Al-Ándalus", "La presencia musulmana en la península", "/images/alandalus.png", 3));
        panelCentral.add(crearTarjetaCurso("Reconquista", "Los reinos cristianos y la Reconquista", "/images/reconquista.png", 4));
        
        // Panel inferior con botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelInferior.setOpaque(false);
        
        CustomButton btnVolver = new CustomButton("Volver a Inicio", e -> {
            GestorVentanas.getInstancia().mostrarVentana(TipoVentana.PRINCIPAL);
        });
        
        panelInferior.add(btnVolver);
        
        // Ensamblar panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel crearTarjetaCurso(String titulo, String descripcion, String rutaImagen, int idCurso) {
        JPanel tarjeta = new JPanel(new BorderLayout(10, 10));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(App.COLOR_PRIMARIO, 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // Imagen del curso
        JLabel labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
        Image imagen = App.cargarImagen(rutaImagen);
        if (imagen != null) {
            labelImagen.setIcon(new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } else {
            labelImagen.setText("[Imagen no disponible]");
        }
        
        // Información del curso
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(App.FUENTE_SUBTITULO);
        labelTitulo.setForeground(App.COLOR_PRIMARIO);
        
        JTextArea areaDescripcion = new JTextArea(descripcion);
        areaDescripcion.setEditable(false);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setOpaque(false);
        
        CustomButton btnVerCurso = new CustomButton("Ver Detalles", e -> {
            GestorVentanas.getInstancia().mostrarVentana(TipoVentana.DETALLE_CURSO);
        });
        btnVerCurso.setPreferredSize(new Dimension(150, 40));
        
        panelInfo.add(labelTitulo);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        panelInfo.add(areaDescripcion);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 15)));
        panelInfo.add(btnVerCurso);
        
        // Ensamblaje final
        tarjeta.add(labelImagen, BorderLayout.WEST);
        tarjeta.add(panelInfo, BorderLayout.CENTER);
        
        // Hacer la tarjeta clicable
        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GestorVentanas.getInstancia().mostrarVentana(TipoVentana.DETALLE_CURSO);
            }
        });
        
        return tarjeta;
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
        return TipoVentana.CURSOS;
    }
}
