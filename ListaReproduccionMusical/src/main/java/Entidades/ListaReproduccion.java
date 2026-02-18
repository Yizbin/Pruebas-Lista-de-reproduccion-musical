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

    public ListaReproduccion() {
    }

    public ListaReproduccion(String nombreLista, List<Cancion> listaCanciones) {
        this.nombreLista = nombreLista;
        this.listaCanciones = new ArrayList<>();
    }

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
    public void agregarCancion(Cancion cancion) {
        this.listaCanciones.add(cancion);
    }

    public void eliminarCancion(Cancion cancion) {
        this.listaCanciones.remove(cancion);
    }

    public int getCantidadCanciones() {
        return this.listaCanciones.size();
    }

}
