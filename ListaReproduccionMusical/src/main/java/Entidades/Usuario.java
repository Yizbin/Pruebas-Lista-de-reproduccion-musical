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
public class Usuario {

    private String usuario;

    private String contrasenia;

    private List<ListaReproduccion> listaReproduccion;

    public Usuario() {
    }

    public Usuario(String usuario, String contrasenia, List<ListaReproduccion> listaReproduccion) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.listaReproduccion = new ArrayList<>();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<ListaReproduccion> getListaReproduccion() {
        return listaReproduccion;
    }

    public void setListaReproduccion(List<ListaReproduccion> listaReproduccion) {
        this.listaReproduccion = listaReproduccion;
    }

}
