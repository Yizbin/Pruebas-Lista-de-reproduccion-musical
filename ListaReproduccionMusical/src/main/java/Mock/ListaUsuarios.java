/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mock;

import Entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public class ListaUsuarios {

    private static final List<Usuario> usuariosRegistrados = new ArrayList<>();

    public ListaUsuarios() {
        if (usuariosRegistrados.isEmpty()) {
            cargarUsuarios();
        }
    }

    private void cargarUsuarios() {
        Usuario u1 = new Usuario("admin", "1234", new ArrayList<>());
        usuariosRegistrados.add(u1);
    }

    public Usuario buscarUsuario(String usuario, String contra) {
        for (Usuario u : usuariosRegistrados) {
            if (u.validarUsuario(usuario, contra)) {
                return u;
            }
        }

        return null;
    }

    public boolean agregarUsuario(Usuario usuarioNuevo) {
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsuario().equals(usuarioNuevo.getUsuario())) {
                return false;
            }
        }
        usuariosRegistrados.add(usuarioNuevo);
        return true;
    }

}
