package Interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.BorderLayout;

/**
 *
 * @author NetoLpz05
 */

public class Reproductor extends JFrame {

    private JSlider progreso;
    private JSlider volumen;
    private JButton btnPlay;

    public Reproductor() {
        setTitle("Reproductor de Música");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        //PANEL PRINCIPAL
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel);

        //PANEL IZQUIERDO (CARÁTULA)
        JPanel panelAlbum = new JPanel();
        panelAlbum.setBackground(new Color(30, 30, 30));
        panelAlbum.setLayout(new BorderLayout());

        JLabel albumArt = new JLabel();
        albumArt.setPreferredSize(new Dimension(300, 300));
        albumArt.setOpaque(true);
        albumArt.setBackground(new Color(60, 60, 60));
        albumArt.setHorizontalAlignment(SwingConstants.CENTER);
        albumArt.setText("Arte del Album");
        albumArt.setForeground(Color.WHITE);

        panelAlbum.add(albumArt, BorderLayout.CENTER);
        mainPanel.add(panelAlbum, BorderLayout.WEST);

        //PANEL CENTRO (INFO + CONTROLES)
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new Color(30, 30, 30));
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Nombre de la Canción");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel lblArtista = new JLabel("Artista");
        lblArtista.setForeground(Color.LIGHT_GRAY);
        lblArtista.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        panelCentro.add(lblTitulo);
        panelCentro.add(Box.createVerticalStrut(5));
        panelCentro.add(lblArtista);
        panelCentro.add(Box.createVerticalStrut(30));

        //Barra de progreso
        progreso = new JSlider();
        progreso.setValue(30);
        progreso.setBackground(new Color(30, 30, 30));
        progreso.setForeground(new Color(0, 200, 120));
        panelCentro.add(progreso);

        panelCentro.add(Box.createVerticalStrut(25));

        //BOTONES DE CONTROL
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(30, 30, 30));

        JButton btnPrev = crearBoton("⏮");
        btnPlay = crearBoton("▶");
        JButton btnNext = crearBoton("⏭");

        btnPlay.addActionListener(e -> togglePlay());

        panelBotones.add(btnPrev);
        panelBotones.add(btnPlay);
        panelBotones.add(btnNext);

        panelCentro.add(panelBotones);
        panelCentro.add(Box.createVerticalStrut(25));

        //VOLUMEN
        JPanel panelVolumen = new JPanel(new BorderLayout());
        panelVolumen.setBackground(new Color(30, 30, 30));

        JLabel lblVol = new JLabel("Volumen");
        lblVol.setForeground(Color.WHITE);

        volumen = new JSlider(0, 100, 70);
        volumen.setBackground(new Color(30, 30, 30));

        panelVolumen.add(lblVol, BorderLayout.WEST);
        panelVolumen.add(volumen, BorderLayout.CENTER);

        panelCentro.add(panelVolumen);

        mainPanel.add(panelCentro, BorderLayout.CENTER);

        // LISTA DE REPRODUCCIÓN
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Canción 1");
        modeloLista.addElement("Canción 2");
        modeloLista.addElement("Canción 3");
        modeloLista.addElement("Canción 4");

        JList<String> lista = new JList<>(modeloLista);
        lista.setBackground(new Color(45, 45, 45));
        lista.setForeground(Color.WHITE);
        lista.setSelectionBackground(new Color(0, 200, 120));
        lista.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(200, 0));

        mainPanel.add(scroll, BorderLayout.EAST);
    }

    //Método para crear botones
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(50, 50, 50));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    //Cambia entre Play y Pause
    private void togglePlay() {
        if (btnPlay.getText().equals("▶")) {
            btnPlay.setText("⏸");
        } else {
            btnPlay.setText("▶");
        }
    }
}

