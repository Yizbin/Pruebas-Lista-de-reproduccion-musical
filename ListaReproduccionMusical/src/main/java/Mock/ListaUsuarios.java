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

    /**
     * Constructor de la clase. Verifica si la base de datos simulada está
     * vacía, y de ser así, inserta usuarios por defecto para pruebas.
     */
    public ListaUsuarios() {
        if (usuariosRegistrados.isEmpty()) {
            cargarUsuarios();
        }
    }

    /**
     * Carga un usuario administrador predeterminado ("admin", "1234") para
     * facilitar las pruebas del sistema sin necesidad de registro previo.
     */
    private void cargarUsuarios() {
        Usuario u1 = new Usuario("admin", "1234", new ArrayList<>());
        usuariosRegistrados.add(u1);
    }

    /**
     * Busca un usuario en los registros simulados que coincida con las
     * credenciales proporcionadas.
     *
     * @param usuario Nombre de usuario a buscar.
     * @param contra Contraseña del usuario.
     * @return El objeto Usuario si las credenciales son válidas, o null si no
     * se encuentra.
     */
    public Usuario buscarUsuario(String usuario, String contra) {
        for (Usuario u : usuariosRegistrados) {
            if (u.validarUsuario(usuario, contra)) {
                return u;
            }
        }

        return null;
    }

    /**
     * Registra un nuevo usuario en el sistema. Valida que el nombre de usuario
     * no esté ya ocupado por otro registro.
     *
     * @param usuarioNuevo Objeto Usuario a registrar.
     * @return true si se registró exitosamente, false si el nombre de usuario
     * ya existe.
     */
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
