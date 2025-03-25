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
 * Ventana de inicio de sesión.
 */
public class VentanaLogin implements Ventana {
    private JPanel panelPrincipal;
    private JTextField campoUsuario;
    private JPasswordField campoPassword;
    
    public VentanaLogin() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBackground(App.COLOR_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(50, 100, 50, 100));
        
        // Panel de login
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
        panelLogin.setBackground(Color.WHITE);
        panelLogin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(App.COLOR_PRIMARIO, 2),
            new EmptyBorder(30, 30, 30, 30)
        ));
        
        // Título
        JLabel labelTitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        labelTitulo.setFont(App.FUENTE_TITULO);
        labelTitulo.setForeground(App.COLOR_PRIMARIO);
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Campos de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 15, 15));
        panelFormulario.setOpaque(false);
        
        JLabel labelUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(20);
        
        JLabel labelPassword = new JLabel("Contraseña:");
        campoPassword = new JPasswordField(20);
        
        panelFormulario.add(labelUsuario);
        panelFormulario.add(campoUsuario);
        panelFormulario.add(labelPassword);
        panelFormulario.add(campoPassword);
        
        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setOpaque(false);
        
        CustomButton btnLogin = new CustomButton("Iniciar Sesión", e -> {
            // Simulación de autenticación
            if (campoUsuario.getText().equals("admin") && 
                    String.valueOf(campoPassword.getPassword()).equals("admin")) {
                GestorVentanas.getInstancia().mostrarVentana(TipoVentana.CURSOS);
            } else {
                JOptionPane.showMessageDialog(panelPrincipal, 
                        "Credenciales inválidas. Prueba con admin/admin", 
                        "Error de autenticación", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        CustomButton btnVolver = new CustomButton("Volver", e -> {
            GestorVentanas.getInstancia().mostrarVentana(TipoVentana.PRINCIPAL);
        });
        
        panelBotones.add(btnLogin);
        panelBotones.add(btnVolver);
        
        // Armar el panel de login
        panelLogin.add(labelTitulo);
        panelLogin.add(Box.createRigidArea(new Dimension(0, 30)));
        panelLogin.add(panelFormulario);
        panelLogin.add(Box.createRigidArea(new Dimension(0, 30)));
        panelLogin.add(panelBotones);
        
        // Agregar el panel de login al panel principal
        panelPrincipal.add(Box.createVerticalGlue(), BorderLayout.NORTH);
        panelPrincipal.add(panelLogin, BorderLayout.CENTER);
        panelPrincipal.add(Box.createVerticalGlue(), BorderLayout.SOUTH);
    }
    
    @Override
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    @Override
    public void alMostrar() {
        campoUsuario.setText("");
        campoPassword.setText("");
        campoUsuario.requestFocus();
    }
    
    @Override
    public void alOcultar() {
        // No se requiere acción especial al ocultar
    }
    
    @Override
    public TipoVentana getTipo() {
        return TipoVentana.LOGIN;
    }
}
