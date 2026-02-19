/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import Entidades.Usuario;
import Mock.ListaUsuarios;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abraham Coronel
 */
public class IniciarSesion extends JFrame {

    private final ListaUsuarios listaUsuarios;

    private JTextField textfieldUsuario;
    private JPasswordField passfieldContra;

    public IniciarSesion() {
        this.listaUsuarios = new ListaUsuarios();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Inicio de Sesión");
        setSize(600, 800); 
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicializarComponentes() {
        Color colorFondo = new Color(15, 15, 15);
        Color colorMorado = new Color(138, 43, 226);
        Color colorAscua = new Color(226, 113, 43);
        Font fuente = new Font("Honoka Mincho", Font.BOLD, 18);
        Font fuenteTitulo = new Font("Honoka Mincho", Font.BOLD, 36);


        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(colorFondo);
        panelFondo.setLayout(null);

        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setForeground(colorMorado);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(150, 120, 300, 50); // x, y, ancho, alto
        panelFondo.add(lblTitulo);

        JLabel labelUsuario = new JLabel("Ingresa tu usuario:");
        labelUsuario.setForeground(colorAscua);
        labelUsuario.setFont(fuente);
        labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        labelUsuario.setBounds(150, 230, 300, 30);
        panelFondo.add(labelUsuario);

        textfieldUsuario = new JTextField();
        textfieldUsuario.setBackground(new Color(30, 30, 30));
        textfieldUsuario.setForeground(Color.WHITE);
        textfieldUsuario.setFont(fuente);
        textfieldUsuario.setHorizontalAlignment(JTextField.CENTER);
        textfieldUsuario.setBounds(150, 270, 300, 40);
        textfieldUsuario.setBorder(BorderFactory.createLineBorder(colorMorado, 1));
        panelFondo.add(textfieldUsuario);

        JLabel labelContra = new JLabel("Ingresa tu contraseña:");
        labelContra.setForeground(colorAscua);
        labelContra.setFont(fuente);
        labelContra.setHorizontalAlignment(SwingConstants.CENTER);
        labelContra.setBounds(150, 340, 300, 30);
        panelFondo.add(labelContra);

        passfieldContra = new JPasswordField();
        passfieldContra.setBackground(new Color(30, 30, 30));
        passfieldContra.setForeground(Color.WHITE);
        passfieldContra.setFont(fuente);
        passfieldContra.setHorizontalAlignment(JPasswordField.CENTER);
        passfieldContra.setBounds(150, 380, 300, 40);
        passfieldContra.setBorder(BorderFactory.createLineBorder(colorMorado, 1));
        panelFondo.add(passfieldContra);

        // --- BOTONES ---
        JButton btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.setBackground(colorMorado);
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFont(fuente);
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIniciarSesion.setBounds(200, 470, 200, 45);
        btnIniciarSesion.addActionListener(e -> iniciarSesion());
        panelFondo.add(btnIniciarSesion);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBackground(colorAscua);
        btnRegistrarse.setForeground(Color.WHITE);
        btnRegistrarse.setFont(fuente);
        btnRegistrarse.setFocusPainted(false);
        btnRegistrarse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrarse.setBounds(200, 540, 200, 45);
        btnRegistrarse.addActionListener(e -> abrirRegistro());
        panelFondo.add(btnRegistrarse);

        add(panelFondo);
    }

    private void iniciarSesion() {
        String usuarioIngresado = this.textfieldUsuario.getText();
        char[] arrayContra = this.passfieldContra.getPassword();
        String contraIngresada = new String(arrayContra);

        Usuario usuarioEncontrado = listaUsuarios.buscarUsuario(usuarioIngresado, contraIngresada);

        if (usuarioEncontrado != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido: " + usuarioIngresado);
            Reproductor ventanaReproductor = new Reproductor(usuarioEncontrado);
            ventanaReproductor.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error: Usuario o contraseña incorrectos, ingrésalos de nuevo", "Credenciales Inválidas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRegistro() {
        RegistrarUsuario ventanaRegistro = new RegistrarUsuario();
        ventanaRegistro.setVisible(true);
        this.dispose();
    }
}
