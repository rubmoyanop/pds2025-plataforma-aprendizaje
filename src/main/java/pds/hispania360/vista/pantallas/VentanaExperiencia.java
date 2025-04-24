package pds.hispania360.vista.pantallas;

import pds.hispania360.controlador.Controlador;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.Recargable;
import pds.hispania360.vista.core.TipoVentana;
import pds.hispania360.vista.core.Ventana;
import pds.hispania360.vista.util.EstilosApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Ventana que muestra la experiencia obtenida al completar un bloque.
 */
public class VentanaExperiencia implements Ventana, Recargable {
    private JPanel panelPrincipal;
    private int experienciaObtenida = 100;
    
    public VentanaExperiencia() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(EstilosApp.COLOR_FONDO);
        
        // Panel central con mensaje y animación
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(EstilosApp.COLOR_TARJETA);
        panelCentral.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(EstilosApp.COLOR_BORDE, 1, true),
            new EmptyBorder(40, 40, 40, 40)
        ));
        
        // Mensaje de felicitación
        JLabel lblFelicitacion = new JLabel("¡Enhorabuena!");
        lblFelicitacion.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblFelicitacion.setForeground(EstilosApp.COLOR_PRIMARIO);
        lblFelicitacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Mensaje de completado de bloque
        JLabel lblCompletado = new JLabel("Has completado el bloque correctamente");
        lblCompletado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblCompletado.setForeground(EstilosApp.COLOR_TEXTO);
        lblCompletado.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel para la animación o icono
        JPanel panelIcono = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Dibujar círculo con estrella o símbolo de éxito
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Círculo dorado
                g2d.setColor(new Color(218, 165, 32));
                int size = Math.min(getWidth(), getHeight()) - 20;
                int x = (getWidth() - size) / 2;
                int y = (getHeight() - size) / 2;
                g2d.fillOval(x, y, size, size);
                
                // Borde del círculo
                g2d.setColor(new Color(184, 134, 11));
                g2d.setStroke(new BasicStroke(3));
                g2d.drawOval(x, y, size, size);
                
                // Dibujar una estrella o símbolo en el centro
                g2d.setColor(Color.WHITE);
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                
                // Dibujar "XP" en el centro
                g2d.setFont(new Font("Segoe UI", Font.BOLD, size / 3));
                FontMetrics fm = g2d.getFontMetrics();
                String text = "XP";
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();
                g2d.drawString(text, centerX - textWidth / 2, centerY + textHeight / 3);
                
                g2d.dispose();
            }
        };
        panelIcono.setPreferredSize(new Dimension(120, 120));
        panelIcono.setMaximumSize(new Dimension(120, 120));
        panelIcono.setMinimumSize(new Dimension(120, 120));
        panelIcono.setOpaque(false);
        panelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Mensaje de experiencia obtenida
        JLabel lblExperiencia = new JLabel("+" + experienciaObtenida + " puntos de experiencia");
        lblExperiencia.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblExperiencia.setForeground(new Color(218, 165, 32));
        lblExperiencia.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Botón para volver a la ventana de detalle del curso
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(EstilosApp.FUENTE_BOTON);
        btnAceptar.setBackground(EstilosApp.COLOR_PRIMARIO);
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setBorder(new EmptyBorder(10, 25, 10, 25));
        btnAceptar.setFocusPainted(false);
        btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Efecto hover para el botón
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAceptar.setBackground(EstilosApp.COLOR_PRIMARIO.darker());
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAceptar.setBackground(EstilosApp.COLOR_PRIMARIO);
            }
        });
        
        // Acción del botón: volver a la ventana de detalle del curso
        btnAceptar.addActionListener(e -> {
            Controlador.INSTANCIA.actualizarExperiencia(experienciaObtenida);
            GestorVentanas.INSTANCIA.mostrarVentana(TipoVentana.DETALLE_CURSO);
        });
        
        // Agregar componentes al panel central con espaciado
        panelCentral.add(Box.createVerticalGlue());
        panelCentral.add(lblFelicitacion);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelCentral.add(lblCompletado);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 30)));
        panelCentral.add(panelIcono);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentral.add(lblExperiencia);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 40)));
        panelCentral.add(btnAceptar);
        panelCentral.add(Box.createVerticalGlue());
        
        // Panel contenedor con margen
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setOpaque(false);
        contenedor.add(panelCentral);
        
        panelPrincipal.add(contenedor, BorderLayout.CENTER);
    }
    
    @Override
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    @Override
    public void alMostrar() {
        // Se puede agregar aquí código para reproducir algún sonido o animación
    }
    
    @Override
    public void alOcultar() {
        // Nada específico que hacer al ocultar
    }
    
    @Override
    public TipoVentana getTipo() {
        return TipoVentana.EXPERIENCIA;
    }
    
    @Override
    public void recargar() {
        panelPrincipal.removeAll();
        inicializarComponentes();
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
    
    /**
     * Establece la cantidad de experiencia obtenida para mostrar.
     * @param experiencia Puntos de experiencia obtenidos
     */
    public void setExperienciaObtenida(int experiencia) {
        this.experienciaObtenida = experiencia;
        recargar();
    }
}
