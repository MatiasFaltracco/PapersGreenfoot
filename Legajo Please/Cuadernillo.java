import greenfoot.*;
import java.util.List;

public class Cuadernillo extends Documento
{
    private static final int FILAS_POR_PAGINA = 11; 

    // =========================================================
    // ZONA DE CALIBRACIÓN MANUAL
    // Modifica estos números si necesitas mover los textos.
    // =========================================================
    
    // Y: Altura y espacio
    private int yInicial = 47; 
    private int alturaFila = 22; 
    
    // X: Posiciones de la PÁGINA IZQUIERDA
    private int[] xColPagIzquierda = { 35, 85, 132, 185 };
    
    // X: Posiciones de la PÁGINA DERECHA (Saltando el espiral central)
    private int[] xColPagDerecha   = { 300, 342, 390, 440 };

    // =========================================================

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

            boolean esPaginaDerecha = i >= FILAS_POR_PAGINA;
            int indiceEnPagina = esPaginaDerecha ? i - FILAS_POR_PAGINA : i;
            
            int[] xCols = esPaginaDerecha ? xColPagDerecha : xColPagIzquierda;
            int y = yInicial + (indiceEnPagina * alturaFila);

            // 1. Legajo
            dibujarCampo(imagen, String.valueOf(fila.legajo), xCols[0], y);
            
            // 2. Carrera (Usamos el método que las acorta para que entren)
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
    
    /**
     * Método auxiliar para resumir carreras largas y evitar que 
     * el texto invada la columna de al lado o el espiral central.
     */
    private String acortarCarrera(String nombreCompleto) {
        if (nombreCompleto == null) return "";
        
        String texto = nombreCompleto.toLowerCase();

        // Al buscar "elec" evitamos cualquier problema con la tilde de "Electrónica"
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
