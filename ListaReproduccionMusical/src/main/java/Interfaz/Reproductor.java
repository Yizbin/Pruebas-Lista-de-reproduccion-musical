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
import javax.sound.sampled.FloatControl;

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

    private final JLabel lblTitulo;
    private final JLabel lblArtista;

    private Clip clip; // Objeto nativo de Java para reproducir audio
    private Timer timerProgreso;

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
        mainPanel.setBackground(new Color(15, 15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel);

        //PANEL IZQUIERDO (CARÁTULA)
        JPanel panelAlbum = new JPanel();
        panelAlbum.setBackground(new Color(15, 15, 15));
        panelAlbum.setLayout(new BorderLayout());

        JLabel albumArt = new JLabel();
        albumArt.setPreferredSize(new Dimension(300, 300));
        albumArt.setOpaque(true);
        albumArt.setBackground(new Color(30, 30, 30));
        albumArt.setHorizontalAlignment(SwingConstants.CENTER);
        albumArt.setText("Arte del Album");
        albumArt.setForeground(Color.WHITE);

        panelAlbum.add(albumArt, BorderLayout.CENTER);
        mainPanel.add(panelAlbum, BorderLayout.WEST);

        //PANEL CENTRO (INFO + CONTROLES)
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new Color(15, 15, 15));
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(new EmptyBorder(40, 30, 20, 30));

        // INICIALIZAMOS GLOBALES 
        lblTitulo = new JLabel("Selecciona una pista");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Honoka Mincho", Font.BOLD, 32));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblArtista = new JLabel("Artista");
        lblArtista.setForeground(new Color(138, 43, 226)); // Morado
        lblArtista.setFont(new Font("Honoka Mincho", Font.PLAIN, 20));
        lblArtista.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCentro.add(lblTitulo);
        panelCentro.add(Box.createVerticalStrut(10));
        panelCentro.add(lblArtista);
        panelCentro.add(Box.createVerticalStrut(50));

        // Barra de progreso envuelta para no estirarse
        JPanel panelProgreso = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelProgreso.setBackground(new Color(15, 15, 15));

        progreso = new JSlider();
        progreso.setValue(0);
        progreso.setPreferredSize(new Dimension(600, 40));
        progreso.setBackground(new Color(15, 15, 15));
        progreso.setForeground(new Color(138, 43, 226)); // Morado
        timerProgreso = new Timer(100, e -> actualizarBarraProgreso());

        progreso.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (clip != null && clip.isOpen()) {
                    long longitudTotal = clip.getMicrosecondLength();
                    long nuevaPosicion = (progreso.getValue() * longitudTotal) / 100;
                    clip.setMicrosecondPosition(nuevaPosicion);
                }
            }
        });
        panelProgreso.add(progreso);

        panelCentro.add(panelProgreso);
        panelCentro.add(Box.createVerticalStrut(30));

        //BOTONES DE CONTROL
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setBackground(new Color(15, 15, 15));

        JButton btnPrev = crearBoton("⏮");
        btnPlay = crearBoton("▶");
        JButton btnNext = crearBoton("⏭");

        btnPlay.addActionListener(e -> togglePlay());

        panelBotones.add(btnPrev);
        panelBotones.add(btnPlay);
        panelBotones.add(btnNext);

        panelCentro.add(panelBotones);
        panelCentro.add(Box.createVerticalStrut(30));

        // VOLUMEN envuelto para no estirarse
        JPanel panelVolumen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelVolumen.setBackground(new Color(15, 15, 15));

        JLabel lblVol = new JLabel("Volumen");
        lblVol.setForeground(Color.WHITE);
        lblVol.setFont(new Font("Honoka Mincho", Font.BOLD, 14));

        volumen = new JSlider(0, 100, 70);
        volumen.setPreferredSize(new Dimension(200, 40));
        volumen.setBackground(new Color(15, 15, 15));

        // Listener del volumen
        volumen.addChangeListener(e -> actualizarVolumen());

        panelVolumen.add(lblVol);
        panelVolumen.add(volumen);

        panelCentro.add(panelVolumen);

        mainPanel.add(panelCentro, BorderLayout.CENTER);

        // ------------------ PANEL DERECHO (LISTAS Y CANCIONES) ------------------
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(15, 15, 15));

        comboListas = new JComboBox<>();
        comboListas.setBackground(new Color(30, 30, 30));
        comboListas.setForeground(Color.WHITE);
        comboListas.setFont(new Font("Honoka Mincho", Font.BOLD, 14));
        comboListas.addActionListener(e -> cargarCancionesDeListaSeleccionada());

        panelDerecho.add(comboListas, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();

        // INICIALIZAMOS LA LISTA GLOBAL
        lista = new JList<>(modeloLista);
        lista.setBackground(new Color(20, 20, 20)); // Gris oscuro
        lista.setForeground(Color.WHITE);
        lista.setSelectionBackground(new Color(106, 13, 173)); // Morado de selección
        lista.setFont(new Font("Honoka Mincho", Font.PLAIN, 14));

        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Si es doble clic
                    reproducirCancionSeleccionada();
                }
            }
        });

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(250, 0));
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margen interior

        panelDerecho.add(scroll, BorderLayout.CENTER);

        // PANEL PARA LOS BOTONES INFERIORES
        JPanel panelBotonesDerecho = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBotonesDerecho.setBackground(new Color(15, 15, 15));
        panelBotonesDerecho.setBorder(new EmptyBorder(10, 0, 0, 0));

        // BOTÓN 1: CREAR LISTA (Dorado/Ascua)
        JButton btnCrearLista = new JButton("➕ Crear Nueva Lista");
        btnCrearLista.setFocusPainted(false);
        btnCrearLista.setFont(new Font("Honoka Mincho", Font.BOLD, 14));
        btnCrearLista.setForeground(Color.WHITE);
        btnCrearLista.setBackground(new Color(226, 113, 43)); // Ascua
        btnCrearLista.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCrearLista.addActionListener(e -> crearListaInteractivo());

        // BOTÓN 2: AGREGAR CANCIÓN (Morado)
        JButton btnAgregarCancion = new JButton("🎵 Agregar Canción");
        btnAgregarCancion.setFocusPainted(false);
        btnAgregarCancion.setFont(new Font("Honoka Mincho", Font.BOLD, 14));
        btnAgregarCancion.setForeground(Color.WHITE);
        btnAgregarCancion.setBackground(new Color(138, 43, 226)); // Morado
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
            timerProgreso.stop(); // <- PAUSAR RELOJ
        } else {
            clip.start();
            btnPlay.setText("⏸");
            timerProgreso.start(); // <- REANUDAR RELOJ
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

            comboListas.addItem(nombreLista);

            if (comboListas.getItemCount() == 1) {
                comboListas.setSelectedIndex(0);
            }

            JOptionPane.showMessageDialog(this, "Lista '" + nombreLista + "' creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarCancionesDeListaSeleccionada() {
        int index = comboListas.getSelectedIndex();
        if (index != -1) {
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

            clip.start();

            btnPlay.setText("⏸");
            timerProgreso.start();

            lblTitulo.setText(cancionActual.getTitulo());
            lblArtista.setText(cancionActual.getArtista());
            actualizarVolumen();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al reproducir el audio. Asegúrate de que sea un archivo .wav válido.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void actualizarVolumen() {
        if (clip != null && clip.isOpen()) {
            try {
                FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float vol = volumen.getValue() / 100f;
                float db = (vol == 0.0f) ? -80.0f : (float) (Math.log10(vol) * 20f);
                control.setValue(db);
            } catch (IllegalArgumentException ex) {
                System.out.println("No soporta control de volumen maestro.");
            }
        }
    }

    private void actualizarBarraProgreso() {
        if (clip != null && clip.isRunning() && !progreso.getValueIsAdjusting()) {
            long posicionActual = clip.getMicrosecondPosition();
            long longitudTotal = clip.getMicrosecondLength();

            if (longitudTotal > 0) {
                int porcentaje = (int) ((posicionActual * 100) / longitudTotal);
                progreso.setValue(porcentaje);
            }
        }
    }
}
