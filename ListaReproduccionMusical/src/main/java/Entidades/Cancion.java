package Entidades;

import java.io.File;

/**
 *
 * @author Abraham Coronel
 */
public class Cancion {

    private File archivo;
    private String titulo;
    private String artista;
    private long duracion;
    private byte[] caratula;

    /**
     * Constructor completo para inicializar una nueva cancion.
     * @param archivo
     * @param titulo  El nombre o titulo de la canción.
     * @param artista El nombre del interprete o creador de la cancion.
     * @param duracion La longitud de la cancion en milisegundos.
     * @param caratula Arreglo de bytes que representa la imagen de portada.
     */
    public Cancion(File archivo, String titulo, String artista, long duracion, byte[] caratula) {
        this.archivo = archivo;
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.caratula = caratula;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
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

    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    public byte[] getCaratula() {
        return caratula;
    }

    public void setCaratula(byte[] caratula) {
        this.caratula = caratula;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista;
    }
}
