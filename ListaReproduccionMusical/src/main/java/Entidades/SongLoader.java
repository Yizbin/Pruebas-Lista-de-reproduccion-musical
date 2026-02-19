/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class SongLoader{

    /**
     * Explora una carpeta específica y carga las canciones contenidas en ella.
     * (Nota: La implementación actual requiere lógica para leer el directorio).
     * @param rutaCarpeta Ruta absoluta o relativa del directorio a explorar.
     * @return Una lista de objetos Cancion encontrados en la carpeta.
     */
    public static List<Cancion> cargar(String rutaCarpeta) {
        List<Cancion> lista = new ArrayList<>();

        File carpeta = new File(rutaCarpeta);

        
        return lista;
    }
}
