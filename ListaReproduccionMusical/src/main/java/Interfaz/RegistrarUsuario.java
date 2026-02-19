/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import Entidades.Usuario;
import javax.swing.JFrame;
import Mock.ListaUsuarios;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abraham Coronel
 */
public class RegistrarUsuario extends JFrame {

    private final JTextField textfieldUsuario;
    private final JPasswordField passfieldContra;
    private final JPasswordField passfieldConfirmar;
    private final ListaUsuarios listaUsuarios;

    public RegistrarUsuario() {
        this.listaUsuarios = new ListaUsuarios();

        setTitle("Registro de Nuevo Usuario");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Color colorFondo = new Color(15, 15, 15);
        Color colorMorado = new Color(138, 43, 226);
        Color colorAscua = new Color(226, 113, 43);
        Font fuente = new Font("Honoka Mincho", Font.BOLD, 18);
        Font fuenteTitulo = new Font("Honoka Mincho", Font.BOLD, 28);

        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(colorFondo);
        panelFondo.setLayout(null); 

        
        JLabel lblTitulo = new JLabel("Registrar Usuario");
        lblTitulo.setForeground(colorMorado);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(150, 80, 300, 40);
        panelFondo.add(lblTitulo);

        JLabel lblUser = new JLabel("Nuevo Usuario:");
        lblUser.setForeground(colorAscua);
        lblUser.setFont(fuente);
        lblUser.setBounds(150, 180, 300, 30);
        panelFondo.add(lblUser);

        textfieldUsuario = new JTextField();
        textfieldUsuario.setBackground(new Color(30, 30, 30));
        textfieldUsuario.setForeground(Color.WHITE);
        textfieldUsuario.setFont(fuente);
        textfieldUsuario.setBounds(150, 220, 300, 35);
        panelFondo.add(textfieldUsuario);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setForeground(colorAscua);
        lblPass.setFont(fuente);
        lblPass.setBounds(150, 280, 300, 30);
        panelFondo.add(lblPass);

        passfieldContra = new JPasswordField();
        passfieldContra.setBackground(new Color(30, 30, 30));
        passfieldContra.setForeground(Color.WHITE);
        passfieldContra.setFont(fuente);
        passfieldContra.setBounds(150, 320, 300, 35);
        panelFondo.add(passfieldContra);

        JLabel lblConfirm = new JLabel("Confirmar Contraseña:");
        lblConfirm.setForeground(colorAscua);
        lblConfirm.setFont(fuente);
        lblConfirm.setBounds(150, 380, 300, 30);
        panelFondo.add(lblConfirm);

        passfieldConfirmar = new JPasswordField();
        passfieldConfirmar.setBackground(new Color(30, 30, 30));
        passfieldConfirmar.setForeground(Color.WHITE);
        passfieldConfirmar.setFont(fuente);
        passfieldConfirmar.setBounds(150, 420, 300, 35);
        panelFondo.add(passfieldConfirmar);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(colorMorado);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(fuente);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBounds(150, 500, 140, 40);
        btnRegistrar.addActionListener(e -> registrarUsuario());
        panelFondo.add(btnRegistrar);

 
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(colorAscua);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(fuente);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBounds(310, 500, 140, 40);
        btnCancelar.addActionListener(e -> volverALogin());
        panelFondo.add(btnCancelar);

        add(panelFondo);
    }

    private void registrarUsuario() {
        String usuario = textfieldUsuario.getText();
        String contra = new String(passfieldContra.getPassword());
        String confirmar = new String(passfieldConfirmar.getPassword());

        if (usuario.trim().isEmpty() || contra.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor llena todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!contra.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(usuario, contra);
        boolean agregado = listaUsuarios.agregarUsuario(nuevoUsuario);

        if (agregado) {
            JOptionPane.showMessageDialog(this, "¡Usuario registrado exitosamente!\nYa puedes iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            volverALogin();
        } else {
            JOptionPane.showMessageDialog(this, "El nombre de usuario ya existe. Intenta con otro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverALogin() {
        IniciarSesion login = new IniciarSesion();
        login.setVisible(true);
        this.dispose();
    }
}
