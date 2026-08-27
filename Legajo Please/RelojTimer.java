import greenfoot.*;

public class RelojTimer extends Actor {

    private long tiempoInicio;
    private int minutoActual;
    private final long miliseg_por_minuto = 60000L;
    
    // Define aquí el tamaño fijo que quieres para el reloj
    private static final int ancho_reloj = 150;
    private static final int alto_reloj = 150;

    // Nombres de los archivos
    private String[] nombresImagenes = {
        "reloj inicio.png", // Minuto 0
        "reloj 8 am.png",   // Minuto 1
        "reloj 12.png",     // Minuto 2
        "reloj 3 pm.png",   // Minuto 3
        "reloj 7 pm.png"    // Minuto 4
    };

    // Arreglo con las imágenes ya reescaladas en memoria
    private GreenfootImage[] imagenesEscaladas;

    public RelojTimer() {
        minutoActual = 0;
        tiempoInicio = System.currentTimeMillis();
        
        // se cargan y reescalan las imagees(una sola vez)
        imagenesEscaladas = new GreenfootImage[nombresImagenes.length];
        for (int i = 0; i < nombresImagenes.length; i++) {
            GreenfootImage img = new GreenfootImage(nombresImagenes[i]);
            img.scale(ancho_reloj, alto_reloj);
            imagenesEscaladas[i] = img;
        }

        // se establece la imagen incial
        setImage(imagenesEscaladas[0]);
    }

    public void act() {
        if (minutoActual < imagenesEscaladas.length - 1) {
            long transcurrido = System.currentTimeMillis() - tiempoInicio;
            int minutosCalculados = (int) (transcurrido / miliseg_por_minuto);

            if (minutosCalculados != minutoActual) {
                minutoActual = minutosCalculados;

                if (minutoActual >= imagenesEscaladas.length) {
                    minutoActual = imagenesEscaladas.length - 1;
                }

                // Se setea una nueva imagen
                setImage(imagenesEscaladas[minutoActual]);
            }
        }
    }
}

