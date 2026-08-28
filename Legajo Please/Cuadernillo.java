import greenfoot.*;
import java.util.List;

/* A diferencia de DNI y FichaEstudiante que se dibujan/actualizan por cada nuevo Estudiante, Cuadernillo dibuja sus datos una unica vez
 * Esto es debido a que Cuadernillo contiene todos los legajos plausibles o validos a comparar. No se encuentra ningun dato falso en el
 * Es la herramienta principal del juego
 */

public class Cuadernillo extends Documento
{
    private static final int filas_por_pagina = 11; // La imagen del cuadernillo (una con la imagen de la escena) tiene once renglones


    // Calibracion manual para que los datos coincidan sobre las imagenes
    
    // Y: Altura y espacio
    private int yInicial = 47; 
    private int alturaFila = 22; 
    
    // X: Posiciones de la pagina izquierda. Se maneja por columnas en el orden: legajo, especialidad, turno, condicion
    private int[] xColPagIzquierda = { 35, 85, 132, 185 };
    
    // X: Posiciones de la pagina derecha. Mismo orden de columnas que en la izquierda
    private int[] xColPagDerecha   = { 300, 342, 390, 440 };

    // =========================================================
    
    // Se define para cuadernillo el ancho y alto de su Imagen base transparente (los limites replica de la imagen en escena). Ver clase Documento.
    public Cuadernillo(int ancho, int alto)
    {
        super(ancho, alto);
        tamañoFuente = 10;
    }
    
    
    public void mostrarFilas(List<GeneradorEstudiante.FilaCuadernillo> filas)
    {
        GreenfootImage imagen = nuevaImagenLimpia(); 

        for (int i = 0; i < filas.size(); i++) {
            GeneradorEstudiante.FilaCuadernillo fila = filas.get(i);

            boolean esPaginaDerecha = i >= filas_por_pagina;
            int indiceEnPagina = esPaginaDerecha ? i - filas_por_pagina : i;
            
            int[] xCols = esPaginaDerecha ? xColPagDerecha : xColPagIzquierda;
            int y = yInicial + (indiceEnPagina * alturaFila);
            
        // Dibujo de columnas sobre la imagen base. El metodo dibujarCampo es heredado de Documento y cada hijo lo redefine con sus valores y coordenadas (polimorfismo)

            // 1. Legajo
            dibujarCampo(imagen, String.valueOf(fila.legajo), xCols[0], y);
            
            // 2. Carrera. Se usa un metodo de acortarmiento (esta ubicado al fondo)
            String carreraCorta = acortarCarrera(fila.carrera);
            dibujarCampo(imagen, carreraCorta, xCols[1], y);
            
            // 3. Turno
            dibujarCampo(imagen, fila.turno, xCols[2], y);
            
            // 4. Condición
            dibujarCampo(imagen, fila.condicion, xCols[3], y);
        }

        setImage(imagen);
    }

    @Override
    public void mostrarDatos(DatosEstudiante datos)
    {
        // El Cuadernillo se dibuja una sola vez al inicio.
    }
    
    // Metodo auxiliar de acortamiento de nombres de carreras
    private String acortarCarrera(String nombreCompleto) {
        if (nombreCompleto == null) return "";
        
        String texto = nombreCompleto.toLowerCase();

        if (texto.contains("elec")) {
            return "Elec";
        }
        if (texto.contains("sist") || texto.contains("info")) {
            return "Sistemas";
        }
        if (texto.contains("quim")) {
            return "Química";
        }
        if (texto.contains("mec")) {
            return "Mecánica";
        }
        if (texto.contains("civ")) {
            return "Civil";
        }

        return nombreCompleto.replace("Ing. ", "").replace("en ", "").trim();
    }
}
