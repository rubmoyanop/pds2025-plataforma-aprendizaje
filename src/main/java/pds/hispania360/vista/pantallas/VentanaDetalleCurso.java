package pds.hispania360.vista.pantallas;

import pds.hispania360.controlador.Controlador;
import pds.hispania360.modelo.Bloque;
import pds.hispania360.modelo.Curso;
import pds.hispania360.sesion.Sesion;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.Recargable;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.core.Ventana;
import pds.hispania360.vista.util.EstilosApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ventana que muestra el detalle de un curso específico.
 */
public class VentanaDetalleCurso implements Ventana, Recargable {
    private JPanel panelPrincipal;
    private Curso cursoActual;
    
    public VentanaDetalleCurso() {
        cursoActual = Sesion.INSTANCIA.getCursoActual();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        if (panelPrincipal == null) {
            panelPrincipal = new JPanel(new BorderLayout());
        } else {
            panelPrincipal.removeAll();
            panelPrincipal.setLayout(new BorderLayout()); 
        }
        panelPrincipal.setBackground(EstilosApp.COLOR_FONDO);
        
        if(this.cursoActual == null) {
            // Mostrar mensaje si no hay curso actual
            JPanel panelMensaje = new JPanel(new BorderLayout());
            panelMensaje.setBackground(EstilosApp.COLOR_FONDO);
            panelMensaje.add(new JLabel("No hay curso actual", SwingConstants.CENTER), BorderLayout.CENTER);
            panelPrincipal.add(panelMensaje, BorderLayout.CENTER);
            return;
        }
        
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
        
        // Panel de navegación para botones
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
            GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.CURSOS);
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

        // Verificar si el usuario ya está inscrito en el curso
        JButton btnEstadoInscripcion;
        
        if (Controlador.INSTANCIA.existeProgresoCurso()) {
            // Botón "Inscrito" (deshabilitado)
            btnEstadoInscripcion = new JButton("Inscrito");
            btnEstadoInscripcion.setFont(EstilosApp.FUENTE_BOTON);
            btnEstadoInscripcion.setContentAreaFilled(true);
            btnEstadoInscripcion.setBackground(new Color(158, 158, 158)); // Color gris
            btnEstadoInscripcion.setForeground(Color.WHITE);
            btnEstadoInscripcion.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            btnEstadoInscripcion.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnEstadoInscripcion.addActionListener(e -> {
                if(Controlador.INSTANCIA.cursoEmpezado()) {
                    int opcion = JOptionPane.showConfirmDialog(panelPrincipal,
                        "¿Estás seguro de que deseas desinscribirte de este curso? Perderás todo tu progreso.",
                        "Confirmar desinscripción", 
                        JOptionPane.YES_NO_OPTION);
                    
                    if (opcion == JOptionPane.YES_OPTION) {
                        Controlador.INSTANCIA.eliminarProgresoCurso();
                        //Esto es para que de tiempo a actualizarse antes de recargar
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(panelPrincipal, 
                                "Te has desinscrito del curso correctamente.", 
                                "Desinscripción completada", JOptionPane.INFORMATION_MESSAGE);
                            recargar();
                        });
                    }
                } else {
                    Controlador.INSTANCIA.eliminarProgresoCurso();
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(panelPrincipal, 
                            "Te has desinscrito del curso correctamente.", 
                            "Desinscripción completada", JOptionPane.INFORMATION_MESSAGE);
                        recargar();
                    });
                }
            });
        } else {
            // Botón "Inscribirse al curso" (habilitado)
            btnEstadoInscripcion = new JButton("Inscribirse al curso");
            btnEstadoInscripcion.setFont(EstilosApp.FUENTE_BOTON);
            btnEstadoInscripcion.setContentAreaFilled(true);
            btnEstadoInscripcion.setBackground(new Color(46, 125, 50)); // Color verde para destacarlo
            btnEstadoInscripcion.setForeground(Color.WHITE);
            btnEstadoInscripcion.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            btnEstadoInscripcion.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnEstadoInscripcion.addActionListener(e -> {
                Controlador.INSTANCIA.crearProgresoCurso();
                JOptionPane.showMessageDialog(panelPrincipal, 
                    "¡Te has inscrito con éxito al curso!", 
                    "Inscripción completada", JOptionPane.INFORMATION_MESSAGE);
                recargar();
            });
            
            // Hover effect para el botón de inscripción
            btnEstadoInscripcion.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btnEstadoInscripcion.setBackground(new Color(27, 94, 32)); // Verde más oscuro al hover
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    btnEstadoInscripcion.setBackground(new Color(46, 125, 50)); // Volver al verde original
                }
            });
        }
        
        // Usar FlowLayout con LEFT para que los botones estén uno al lado del otro
        panelNavegacion.add(btnVolver);
        panelNavegacion.add(btnEstadoInscripcion);
        
        panelContenedor.add(panelNavegacion);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Panel de título del curso
        JLabel labelTitulo = new JLabel(cursoActual.getTitulo());
        labelTitulo.setFont(EstilosApp.FUENTE_TITULO);
        labelTitulo.setForeground(EstilosApp.COLOR_PRIMARIO);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelContenedor.add(labelTitulo);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Panel de información del curso
        JPanel panelInfoCurso = new JPanel();
        panelInfoCurso.setLayout(new BoxLayout(panelInfoCurso, BoxLayout.Y_AXIS));
        panelInfoCurso.setBackground(EstilosApp.COLOR_TARJETA);
        panelInfoCurso.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(EstilosApp.COLOR_BORDE, 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        panelInfoCurso.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Título de la sección descripción
        JLabel labelDescTitulo = new JLabel("Descripción del curso");
        labelDescTitulo.setFont(EstilosApp.FUENTE_TARJETA_TITULO);
        labelDescTitulo.setForeground(EstilosApp.COLOR_PRIMARIO);
        labelDescTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Descripción
        JTextArea areaDescripcion = new JTextArea(cursoActual.getDescripcion());
        areaDescripcion.setEditable(false);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setOpaque(false);
        areaDescripcion.setFont(EstilosApp.FUENTE_TARJETA_TEXTO);
        areaDescripcion.setForeground(EstilosApp.COLOR_TEXTO);
        areaDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelInfoCurso.add(labelDescTitulo);
        panelInfoCurso.add(Box.createRigidArea(new Dimension(0, 10)));
        panelInfoCurso.add(areaDescripcion);
        // NUEVO: Mostrar información del creador
        panelInfoCurso.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel labelCreador = new JLabel("Creador: " + cursoActual.getCreador());
        labelCreador.setFont(EstilosApp.FUENTE_TARJETA_TEXTO);
        labelCreador.setForeground(EstilosApp.COLOR_TEXTO);
        panelInfoCurso.add(labelCreador);
        
        panelContenedor.add(panelInfoCurso);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Sección de estadísticas del curso
        JPanel panelEstadisticas = new JPanel(new GridLayout(1, 2, 10, 0));
        panelEstadisticas.setOpaque(false);
        panelEstadisticas.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEstadisticas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        // Actualizamos la tarjeta de bloques usando el número real
        añadirTarjetaEstadistica(panelEstadisticas, "Bloques", String.valueOf(cursoActual.getBloques() != null ? cursoActual.getBloques().size() : 0), EstilosApp.COLOR_PRIMARIO);

        // Calcular total de ejercicios a partir de los bloques
        int totalEjercicios = 0;
        if(cursoActual.getBloques() != null) {
            for (Bloque bloque : cursoActual.getBloques()) {
                totalEjercicios += (bloque.getEjercicios() != null ? bloque.getEjercicios().size() : 0);
            }
        }
        añadirTarjetaEstadistica(panelEstadisticas, "Total Ejercicios", String.valueOf(totalEjercicios), new Color(46, 125, 50));
        
        panelContenedor.add(panelEstadisticas);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Sección de módulos (bloques)
        JLabel labelModulosTitulo = new JLabel("Bloques del curso");
        labelModulosTitulo.setFont(EstilosApp.FUENTE_TARJETA_TITULO);
        labelModulosTitulo.setForeground(EstilosApp.COLOR_PRIMARIO);
        labelModulosTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelContenedor.add(labelModulosTitulo);
        panelContenedor.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Reemplazamos los datos demo con los bloques extraídos del curso
        if(cursoActual.getBloques() != null) {
            for (int i = 0; i < cursoActual.getBloques().size(); i++) {
                Bloque bloque = cursoActual.getBloques().get(i);
                int numEjercicios = (bloque.getEjercicios() != null ? bloque.getEjercicios().size() : 0);
                JPanel panelBloque = crearPanelBloque(
                    "Bloque " + (i+1) + ": " + bloque.getTitulo(),
                    numEjercicios + " ejercicios", 
                    i+1
                );
                panelContenedor.add(panelBloque);
                if (i < cursoActual.getBloques().size() - 1) {
                    panelContenedor.add(Box.createRigidArea(new Dimension(0, 15)));
                }
            }
        }
        
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void añadirTarjetaEstadistica(JPanel panel, String titulo, String valor, Color colorDestaque) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(EstilosApp.COLOR_TARJETA);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(3, 0, 0, 0, colorDestaque),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel labelValor = new JLabel(valor);
        labelValor.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelValor.setForeground(colorDestaque);
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelTitulo.setForeground(EstilosApp.COLOR_TEXTO);
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        tarjeta.add(labelValor);
        tarjeta.add(Box.createRigidArea(new Dimension(0, 5)));
        tarjeta.add(labelTitulo);
        
        panel.add(tarjeta);
    }
    
    private JPanel crearPanelBloque(String titulo, String ejercicios, int numBloque) {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
        panel.setBackground(EstilosApp.COLOR_TARJETA);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(EstilosApp.COLOR_BORDE, 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Número del bloque en círculo
        JPanel panelNumero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(EstilosApp.COLOR_PRIMARIO);
                g2d.fillOval(0, 0, 40, 40);
                g2d.dispose();
            }
        };
        panelNumero.setOpaque(false);
        panelNumero.setPreferredSize(new Dimension(40, 40));
        
        JLabel labelNumero = new JLabel(String.valueOf(numBloque));
        labelNumero.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelNumero.setForeground(Color.WHITE);
        labelNumero.setHorizontalAlignment(SwingConstants.CENTER);
        panelNumero.setLayout(new BorderLayout());
        panelNumero.add(labelNumero, BorderLayout.CENTER);
        
        // Panel central con información
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelTitulo.setForeground(EstilosApp.COLOR_TEXTO);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Eliminamos el panel de detalles de lecciones y mostramos solo ejercicios
        JPanel panelDetalles = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelDetalles.setOpaque(false);
        JLabel labelEjercicios = new JLabel(ejercicios);
        labelEjercicios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelEjercicios.setForeground(EstilosApp.COLOR_TEXTO_SECUNDARIO);
        panelDetalles.add(labelEjercicios);
        
        panelInfo.add(labelTitulo);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(panelDetalles);
        
        // Botón de acceso al bloque
        JButton btnAcceder = new JButton("Acceder");
        btnAcceder.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAcceder.setForeground(Color.WHITE);
        btnAcceder.setBackground(EstilosApp.COLOR_PRIMARIO);
        btnAcceder.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btnAcceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAcceder.setFocusPainted(false);
        
        btnAcceder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAcceder.setBackground(EstilosApp.COLOR_PRIMARIO.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btnAcceder.setBackground(EstilosApp.COLOR_PRIMARIO);
            }
        });
        
        btnAcceder.addActionListener(e -> {
            if(!Controlador.INSTANCIA.existeProgresoCurso()) {
                JOptionPane.showMessageDialog(panelPrincipal, 
                        "Debes inscribirte primero para poder realizar el curso.",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
            else{
                if(Controlador.INSTANCIA.isRealizado(numBloque)) {
                    // Si el bloque ya ha sido realizado, mostramos un mensaje
                    JOptionPane.showMessageDialog(panelPrincipal, 
                        "Ya has completado este bloque.",
                        "Bloque Completado", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            if(!Controlador.INSTANCIA.isSiguienteBloque(numBloque)) {
                // Si el bloque es el siguiente a realizar, mostramos un mensaje
                JOptionPane.showMessageDialog(panelPrincipal, 
                    "Todavía no puedes acceder a este bloque, completa los bloques anteriores",
                    "Siguiente Bloque", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            else{
                //Suponemos que se elige la estrategia al empezar cada bloque
                GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.ESTRATEGIA);
            }
        });
        
        panel.add(panelNumero, BorderLayout.WEST);
        panel.add(panelInfo, BorderLayout.CENTER);
        panel.add(btnAcceder, BorderLayout.EAST);
        
        return panel;
    }
    
    @Override
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    @Override
    public void alMostrar() {
        cursoActual = Sesion.INSTANCIA.getCursoActual();
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

    @Override
    public void recargar() {
        // Recargar la ventana si es necesario
        cursoActual = Sesion.INSTANCIA.getCursoActual();
        panelPrincipal.removeAll();
        inicializarComponentes();
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
}
