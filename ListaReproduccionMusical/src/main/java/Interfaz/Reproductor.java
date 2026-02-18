package Interfaz;

import Entidades.ListaReproduccion;
import Entidades.Usuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.BorderLayout;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;

/**
 *
 * @author NetoLpz05
 */
public class Reproductor extends JFrame {

    private final JSlider progreso;
    private final JSlider volumen;
    private final JButton btnPlay;

    private final Usuario usuarioActual;

    private final DefaultListModel<String> modeloLista;
    private final JList<String> lista;
    private final JComboBox<String> comboListas;

    private Clip clip; // Objeto nativo de Java para reproducir audio

    public Reproductor(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
        setTitle("Reproductor de Musica de: " + usuarioActual.getUsuario());
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

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(30, 30, 30));

        comboListas = new JComboBox<>();
        comboListas.setBackground(new Color(50, 50, 50));
        comboListas.setForeground(Color.WHITE);
        comboListas.setFont(new Font("Segoe UI", Font.BOLD, 14));

        comboListas.addActionListener(e -> cargarCancionesDeListaSeleccionada());

        panelDerecho.add(comboListas, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();

        // INICIALIZAMOS LA LISTA GLOBAL
        lista = new JList<>(modeloLista);
        lista.setBackground(new Color(45, 45, 45));
        lista.setForeground(Color.WHITE);
        lista.setSelectionBackground(new Color(0, 200, 120));
        lista.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Si es doble clic
                    reproducirCancionSeleccionada();
                }
            }
        });

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(200, 0));

        panelDerecho.add(scroll, BorderLayout.CENTER);

        // PANEL PARA LOS BOTONES INFERIORES
        JPanel panelBotonesDerecho = new JPanel(new GridLayout(2, 1, 5, 5));
        panelBotonesDerecho.setBackground(new Color(30, 30, 30));

        // BOTÓN 1: CREAR LISTA
        JButton btnCrearLista = new JButton("➕ Crear Nueva Lista");
        btnCrearLista.setFocusPainted(false);
        btnCrearLista.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCrearLista.setForeground(Color.WHITE);
        btnCrearLista.setBackground(new Color(0, 150, 90));
        btnCrearLista.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCrearLista.addActionListener(e -> crearListaInteractivo());

        // BOTÓN 2: AGREGAR CANCIÓN
        JButton btnAgregarCancion = new JButton("🎵 Agregar Canción");
        btnAgregarCancion.setFocusPainted(false);
        btnAgregarCancion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAgregarCancion.setForeground(Color.WHITE);
        btnAgregarCancion.setBackground(new Color(70, 130, 180));
        btnAgregarCancion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregarCancion.addActionListener(e -> agregarCancionArchivo());

        panelBotonesDerecho.add(btnCrearLista);
        panelBotonesDerecho.add(btnAgregarCancion);

        // Lo agregamos en la parte de abajo (SOUTH)
        panelDerecho.add(panelBotonesDerecho, BorderLayout.SOUTH);

        mainPanel.add(panelDerecho, BorderLayout.EAST);
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
        if (clip == null || !clip.isOpen()) {
            reproducirCancionSeleccionada();
            return;
        }

        if (clip.isRunning()) {
            clip.stop();
            btnPlay.setText("▶");
        } else {
            clip.start();
            btnPlay.setText("⏸");
        }
    }

    private void crearListaInteractivo() {
        String nombreLista = JOptionPane.showInputDialog(
                this,
                "Ingresa el nombre de la nueva lista:",
                "Crear Lista de Reproducción",
                JOptionPane.PLAIN_MESSAGE
        );

        if (nombreLista != null && !nombreLista.trim().isEmpty()) {

            ListaReproduccion nuevaLista = new ListaReproduccion(nombreLista);
            usuarioActual.agregarListaReproduccion(nuevaLista);

            // --- NUEVO: Agregamos el nombre visualmente al ComboBox ---
            comboListas.addItem(nombreLista);

            // Si es la primera lista que creas, la seleccionamos automáticamente
            if (comboListas.getItemCount() == 1) {
                comboListas.setSelectedIndex(0);
            }

            JOptionPane.showMessageDialog(this, "Lista '" + nombreLista + "' creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método que se activa al elegir una lista diferente en el ComboBox
    private void cargarCancionesDeListaSeleccionada() {
        int index = comboListas.getSelectedIndex();
        if (index != -1) { // Verifica que haya una lista seleccionada
            ListaReproduccion listaSeleccionada = usuarioActual.getListaReproduccion().get(index);
            actualizarListaVisual(listaSeleccionada);
        }
    }

    private void agregarCancionArchivo() {
        int index = comboListas.getSelectedIndex();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Primero debes crear y seleccionar una Lista de Reproducción.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ListaReproduccion listaActiva = usuarioActual.getListaReproduccion().get(index);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Canción");

        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de Audio WAV (*.wav)", "wav"));

        int seleccion = fileChooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            java.io.File archivoSeleccionado = fileChooser.getSelectedFile();

            Entidades.Cancion nuevaCancion = new Entidades.Cancion(
                    archivoSeleccionado,
                    archivoSeleccionado.getName(),
                    "Desconocido",
                    0,
                    null
            );

            listaActiva.agregarCancion(nuevaCancion);

            actualizarListaVisual(listaActiva);
        }
    }

    private void actualizarListaVisual(ListaReproduccion listaActiva) {
        modeloLista.clear();

        for (Entidades.Cancion c : listaActiva.getListaCanciones()) {
            modeloLista.addElement(c.getTitulo());
        }
    }

    private void reproducirCancionSeleccionada() {
        int indexCancion = lista.getSelectedIndex();
        int indexLista = comboListas.getSelectedIndex();

        if (indexLista == -1 || indexCancion == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una lista y una canción para reproducir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ListaReproduccion listaActiva = usuarioActual.getListaReproduccion().get(indexLista);
            Entidades.Cancion cancionActual = listaActiva.getListaCanciones().get(indexCancion);
            File archivoAudio = cancionActual.getArchivo();

            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoAudio);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            btnPlay.setText("⏸");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al reproducir el audio. Asegúrate de que sea un archivo .wav válido.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
