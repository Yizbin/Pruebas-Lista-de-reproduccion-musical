/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Entidades.Usuario;
import Mock.ListaUsuarios;
import javax.swing.JOptionPane;

/**
 *
 * @author Abraham Coronel
 */
public class IniciarSesion extends javax.swing.JFrame {

    private final ListaUsuarios listaUsuarios;

    public IniciarSesion() {
        initComponents();
        configurarVentana();
        this.listaUsuarios = new ListaUsuarios();
    }

    private void configurarVentana() {
        this.setLocationRelativeTo(null);
    }

    private void iniciarSesion() {
        String usuarioIngresado = this.textfieldUsuario.getText();
        char[] arrayContra = this.passfieldContra.getPassword();
        String contraIngresada = new String(arrayContra);
        
        Usuario usuarioEncontrado = listaUsuarios.buscarUsuario(usuarioIngresado, contraIngresada);
        
        if (usuarioEncontrado != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido: " + usuarioIngresado);
            //Aqui seria abrir la otra ventana
        } else {
            JOptionPane.showMessageDialog(this, "Erro: Usuario o contraseña incorrectos, ingreselos de nuevo");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        PanelFormulario = new javax.swing.JPanel();
        labelUsuario = new javax.swing.JLabel();
        textfieldUsuario = new javax.swing.JTextField();
        labelContra = new javax.swing.JLabel();
        passfieldContra = new javax.swing.JPasswordField();
        btnIniciarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 800));
        setSize(new java.awt.Dimension(0, 0));

        panelFondo.setBackground(new java.awt.Color(102, 102, 102));
        panelFondo.setLayout(new java.awt.BorderLayout());

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 102, 102));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Iniciar Sesion");
        panelFondo.add(Titulo, java.awt.BorderLayout.PAGE_START);

        PanelFormulario.setOpaque(false);
        PanelFormulario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelUsuario.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 102, 102));
        labelUsuario.setText("Ingresa tu usuario:");
        labelUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        PanelFormulario.add(labelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 240, 90));

        textfieldUsuario.setBackground(new java.awt.Color(153, 153, 153));
        textfieldUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textfieldUsuario.setForeground(new java.awt.Color(255, 102, 102));
        textfieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldUsuarioActionPerformed(evt);
            }
        });
        PanelFormulario.add(textfieldUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 210, -1));

        labelContra.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelContra.setForeground(new java.awt.Color(255, 102, 102));
        labelContra.setText("Ingresa tu contraseña:");
        PanelFormulario.add(labelContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        passfieldContra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        passfieldContra.setForeground(new java.awt.Color(255, 102, 102));
        PanelFormulario.add(passfieldContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 210, -1));

        btnIniciarSesion.setBackground(new java.awt.Color(153, 153, 153));
        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 102, 102));
        btnIniciarSesion.setText("Iniciar sesion");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        PanelFormulario.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, -1, -1));

        panelFondo.add(PanelFormulario, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelFondo, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 820, 617);
    }// </editor-fold>//GEN-END:initComponents

    private void textfieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfieldUsuarioActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelFormulario;
    private javax.swing.JLabel Titulo;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JLabel labelContra;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPasswordField passfieldContra;
    private javax.swing.JTextField textfieldUsuario;
    // End of variables declaration//GEN-END:variables
}
