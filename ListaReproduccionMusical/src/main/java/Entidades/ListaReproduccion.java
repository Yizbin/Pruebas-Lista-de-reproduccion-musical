/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public class ListaReproduccion {

    private String nombreLista;

    private List<Cancion> listaCanciones;

    /**
     * Constructor por defecto de la clase ListaReproduccion.
     */
    public ListaReproduccion() {
    }

    /**
     * Crea una lista de reproducción recibiendo un nombre y una lista previa.
     * (Nota: Inicializa la lista interna como un ArrayList vacío).
     *
     * @param nombreLista Nombre de la lista.
     * @param listaCanciones Colección inicial de canciones.
     */
    public ListaReproduccion(String nombreLista, List<Cancion> listaCanciones) {
        this.nombreLista = nombreLista;
        this.listaCanciones = new ArrayList<>();
    }

    /**
     * Crea una lista de reproducción vacía con el nombre especificado.
     *
     * @param nombreLista Nombre asignado a la nueva lista.
     */
    public ListaReproduccion(String nombreLista) {
        this.nombreLista = nombreLista;
        this.listaCanciones = new ArrayList<>();
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    //Metodos
    /**
     * Agrega una nueva canción a la lista de reproducción.
     *
     * @param cancion Objeto Cancion a agregar.
     */
    public void agregarCancion(Cancion cancion) {
        this.listaCanciones.add(cancion);
    }

    /**
     * Elimina una canción específica de la lista de reproducción.
     *
     * @param cancion Objeto Cancion a eliminar.
     */
    public void eliminarCancion(Cancion cancion) {
        this.listaCanciones.remove(cancion);
    }

    /**
     * Obtiene el número total de canciones contenidas en la lista.
     *
     * @return La cantidad de canciones.
     */
    public int getCantidadCanciones() {
        return this.listaCanciones.size();
    }

}
