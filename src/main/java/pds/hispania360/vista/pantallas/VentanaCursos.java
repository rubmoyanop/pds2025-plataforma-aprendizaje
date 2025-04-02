package pds.hispania360.vista.pantallas;

import pds.hispania360.controlador.Controlador;
import pds.hispania360.repositorio.GestorCurso;
import pds.hispania360.sesion.Sesion;
import pds.hispania360.vista.componentes.TarjetaCurso;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.core.Ventana;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.Recargable;
import pds.hispania360.vista.util.EstilosApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ventana que muestra el listado de cursos disponibles.
 */
public class VentanaCursos implements Ventana, Recargable {
    private JPanel panelPrincipal;
    private JPanel panelCursos;
    private JScrollPane scrollCursos; // Añadir referencia al scroll
    
    // Datos de ejemplo para los cursos (como prueba)
    // Se deberían cargar más adelante desde los cursos importados en JSON
    private String[][] datosCursos = GestorCurso.INSTANCIA.obtenerCursos().stream()
        .map(curso -> new String[] {
            curso.getTitulo(),
            curso.getDescripcion()
        })
        .toArray(String[][]::new);
    
    public VentanaCursos() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout(0, 0));
        panelPrincipal.setBackground(EstilosApp.COLOR_FONDO);
        
        // Panel de contenido principal
        JPanel panelContenido = new JPanel(new BorderLayout(0, 20));
        panelContenido.setBackground(EstilosApp.COLOR_FONDO);
        panelContenido.setBorder(new EmptyBorder(30, 40, 30, 40));
        
        // Panel superior para título y botón de importar
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setOpaque(false);
        
        // Panel de título y descripción
        panelSuperior.add(crearPanelTitulo());
        panelSuperior.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Panel para el botón Importar Curso
        panelSuperior.add(crearPanelImportar());
        panelSuperior.add(Box.createRigidArea(new Dimension(0, 30)));
        
        panelContenido.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel de cursos 
        panelCursos = new JPanel();
        panelCursos.setLayout(new BoxLayout(panelCursos, BoxLayout.Y_AXIS));
        panelCursos.setOpaque(false);
        
        // Scroll para los cursos 
        scrollCursos = new JScrollPane(panelCursos); 
        scrollCursos.setBorder(null);
        scrollCursos.setOpaque(false);
        scrollCursos.getViewport().setOpaque(false);
        scrollCursos.getVerticalScrollBar().setUnitIncrement(16); 
        
        panelContenido.add(scrollCursos, BorderLayout.CENTER);
        
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);
    }
    
    private JPanel crearPanelTitulo() {
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        panelTitulo.setOpaque(false);
        panelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel labelTitulo = new JLabel("Explora nuestros cursos de Historia de España");
        labelTitulo.setFont(EstilosApp.FUENTE_TITULO);
        labelTitulo.setForeground(EstilosApp.COLOR_PRIMARIO);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea descripcion = new JTextArea(
            "Descubre la fascinante historia de España a través de nuestros cursos interactivos. " +
            "Desde la prehistoria hasta la historia contemporánea, tenemos todo lo que necesitas para " +
            "sumergirte en el pasado de manera amena y educativa."
        );
        descripcion.setEditable(false);
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        descripcion.setOpaque(false);
        descripcion.setFont(EstilosApp.FUENTE_SUBTITULO);
        descripcion.setForeground(EstilosApp.COLOR_TEXTO_SECUNDARIO);
        descripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
        descripcion.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        panelTitulo.add(labelTitulo);
        panelTitulo.add(descripcion);
        
        return panelTitulo;
    }

    private void solicitarImportacion() {
        //Hacer condicwional, si es != null entonces añadimos a datosCursos
        if(Controlador.INSTANCIA.importarCurso()){
            JOptionPane.showMessageDialog(null, "Importación realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.CURSOS);
        }
        else{
            JOptionPane.showMessageDialog(null, "Error en la importación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel crearPanelImportar() {
        JPanel panelImportar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelImportar.setOpaque(false);
        panelImportar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Solo mostramos el botón si el usuario es creador
        if (pds.hispania360.sesion.Sesion.INSTANCIA.haySesion() && 
            pds.hispania360.sesion.Sesion.INSTANCIA.esCreador()) {
            
            JButton btnImportar = new JButton("Importar Curso");
            btnImportar.setFont(EstilosApp.FUENTE_BOTON);
            btnImportar.setForeground(Color.WHITE);
            btnImportar.setBackground(EstilosApp.COLOR_PRIMARIO);
            btnImportar.setBorder(new EmptyBorder(10, 20, 10, 20));
            btnImportar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnImportar.setFocusPainted(false);
            
            btnImportar.addActionListener(e -> solicitarImportacion());
            
            // Añadir efecto hover
            btnImportar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btnImportar.setBackground(EstilosApp.COLOR_PRIMARIO.darker());
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    btnImportar.setBackground(EstilosApp.COLOR_PRIMARIO);
                }
            });
            
            panelImportar.add(btnImportar);
            
            // Ya que el botón está presente, agregamos el listener al panel
            panelImportar.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    solicitarImportacion();
                }
            });
        }
        
        return panelImportar;
    }

    private void actualizarCursos() {
        panelCursos.removeAll();

        datosCursos = GestorCurso.INSTANCIA.obtenerCursos().stream()
        .map(curso -> new String[] {
            curso.getTitulo(),
            curso.getDescripcion()
        })
        .toArray(String[][]::new);
        
        for (String[] curso : datosCursos) {
            String titulo = curso[0];
            String descripcion = curso[1];
            
            TarjetaCurso tarjeta = new TarjetaCurso(
                titulo, 
                descripcion
            );
            
            // Configurar la tarjeta para ocupar el ancho completo pero altura fija
            tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
            tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            // Panel contenedor para añadir margen entre tarjetas
            JPanel panelTarjeta = new JPanel(new BorderLayout());
            panelTarjeta.setOpaque(false);
            panelTarjeta.setBorder(new EmptyBorder(0, 0, 20, 0));  // Margen inferior entre tarjetas
            panelTarjeta.add(tarjeta, BorderLayout.CENTER);
            panelTarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelTarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 320));  // Altura = altura tarjeta + margen
            
            panelCursos.add(panelTarjeta);
        }
        
        panelCursos.revalidate();
        panelCursos.repaint();
    }

    /**
     * Recarga la ventana de cursos para reflejar cambios en la sesión del usuario.
     * Especialmente útil cuando el usuario inicia o cierra sesión para mostrar u ocultar
     * el botón de importar curso basado en su rol.
     */
    @Override
    public void recargar() {
        // Eliminar todos los componentes
        panelPrincipal.removeAll();

        actualizarCursos();

        // Reinicializar todos los componentes
        inicializarComponentes();
        
        // Revalidar y repintar
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    @Override
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    @Override
    public void alMostrar() {
        if(!Sesion.INSTANCIA.haySesion()) {
            // Si no hay sesión, redirigir al login
            GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.LOGIN);
            return;
        }

        // Actualizar cursos al mostrar la ventana
        actualizarCursos();
        
        // Reiniciar la posición del scroll al principio
        SwingUtilities.invokeLater(() -> {
            // Usar invokeLater para asegurar que esto ocurra después de que los componentes se han actualizado
            JScrollBar verticalScrollBar = scrollCursos.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMinimum());
            
            // En algunos casos, puede ser necesario un segundo intento para asegurar el reset
            SwingUtilities.invokeLater(() -> {
                verticalScrollBar.setValue(verticalScrollBar.getMinimum());
            });
        });
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
