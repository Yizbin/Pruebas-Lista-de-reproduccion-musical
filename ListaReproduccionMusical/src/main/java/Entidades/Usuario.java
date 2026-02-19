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

    /**
     * Constructor por defecto de la clase Usuario.
     */
    public Usuario() {
    }

    /**
     * Crea un nuevo usuario con credenciales básicas.
     * @param usuario Nombre del usuario.
     * @param contrasenia Contraseña del usuario.
     */
    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * Crea un nuevo usuario especificando sus credenciales y sus listas.
     * @param usuario Nombre del usuario.
     * @param contrasenia Contraseña del usuario.
     * @param listaReproduccion Lista inicial de reproducción (se inicializa vacía por defecto).
     */
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

    //Metodos
    
    /**
     * Valida si las credenciales proporcionadas coinciden con las del usuario.
     * @param usuario Nombre de usuario a verificar.
     * @param contra Contraseña a verificar.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    public boolean validarUsuario(String usuario, String contra) {
        return this.usuario.equals(usuario) && this.contrasenia.equals(contra);
    }

    /**
     * Agrega una nueva lista de reproducción al perfil del usuario.
     * Si la colección actual es nula, la inicializa antes de agregar la lista.
     * @param nuevaLista La nueva lista de reproducción a agregar.
     */
    public void agregarListaReproduccion(ListaReproduccion nuevaLista) {
        if (this.listaReproduccion == null) {
            this.listaReproduccion = new ArrayList<>();
        }
        this.listaReproduccion.add(nuevaLista);
    }

}
