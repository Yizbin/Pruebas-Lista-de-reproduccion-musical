/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Abraham Coronel
 */
public class Cancion {

    private String titulo;

    private String artista;

    private String rutaArchivo;

    private int duracion;

    public Cancion() {
    }

    public Cancion(String titulo, String artista, String rutaArchivo, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.rutaArchivo = rutaArchivo;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    //Metodos
    public String getDuracionMinutos() {
        int minutos = duracion / 60;
        int segundos = duracion % 60;

        return String.format("%02d:%02d", minutos, segundos);
    }

}
