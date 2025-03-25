package pds.hispania360.vista.componentes;

import pds.hispania360.App;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.RenderingHints;

public class CustomButton extends JButton {

    private ActionListener action;

    public CustomButton(String texto, ActionListener action) {
        super(texto); // Llama al constructor de JButton con el texto
        this.action = action;
        configurarBoton();
    }

    private void configurarBoton() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(App.FUENTE_BOTON);
        setForeground(Color.WHITE);
        addActionListener(this.action);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(200, 50));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Color de fondo según si el ratón está encima o no
        if (getModel().isRollover()) {
            g2.setColor(App.COLOR_BOTON_HOVER);
        } else {
            g2.setColor(App.COLOR_BOTON);
        }

        // Dibujar fondo redondeado
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

        // Dibujar texto
        FontMetrics fm = g2.getFontMetrics();
        Rectangle stringBounds = fm.getStringBounds(this.getText(), g2).getBounds();

        int textX = (getWidth() - stringBounds.width) / 2;
        int textY = (getHeight() - stringBounds.height) / 2 + fm.getAscent();

        g2.setColor(Color.WHITE);
        g2.setFont(App.FUENTE_BOTON);
        g2.drawString(getText(), textX, textY);
        g2.dispose();
    }
}