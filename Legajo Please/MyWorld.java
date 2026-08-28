import greenfoot.*;

public class MyWorld extends World
{
    // Tamaños de los lienzos ajustados a la nueva proporción
    private static final int DNI_ANCHO = 280, DNI_ALTO = 200;
    private static final int FICHA_ANCHO = 230, FICHA_ALTO = 310;
    private static final int CUADERNILLO_ANCHO = 520, CUADERNILLO_ALTO = 310;

    // Centros X e Y (Notarás que Validación ahora está a la derecha)
    private static final int DNI_X = 151, DNI_Y = 837;
    private static final int FICHA_X = 466, FICHA_Y = 848;
    private static final int CUADERNILLO_X = 856, CUADERNILLO_Y = 850;

    // Botones de validación en su nueva ubicación extrema derecha
    private static final int INVALIDAR_X = 1180, INVALIDAR_Y = 828;
    private static final int VALIDAR_X = 1282, VALIDAR_Y = 828;

    private static final int ESTUDIANTE_X = 380, ESTUDIANTE_Y = 456;

    // Umbrales de fin de partida
    private static final int LIMITE_ERRORES = 5;
    private static final int LIMITE_ACIERTOS = 15;

    private GeneradorEstudiante generador;
    private DatosEstudiante estudianteActual;

    private DNI dni;
    private FichaEstudiante ficha;
    private Cuadernillo cuadernillo;
    private Estudiante spriteActual;

    private int aciertos = 0;
    private int errores = 0;

    // Candado para evitar que el jugador spammee clics mientras hay animación
    private boolean procesandoDecision = false;

    public MyWorld()
    {
        super(1352, 1000, 1);

        generador = new GeneradorEstudiante();

        dni = new DNI(DNI_ANCHO, DNI_ALTO);
        addObject(dni, DNI_X, DNI_Y);

        ficha = new FichaEstudiante(FICHA_ANCHO, FICHA_ALTO);
        addObject(ficha, FICHA_X, FICHA_Y);

        cuadernillo = new Cuadernillo(CUADERNILLO_ANCHO, CUADERNILLO_ALTO);
        addObject(cuadernillo, CUADERNILLO_X, CUADERNILLO_Y);
        cuadernillo.mostrarFilas(generador.getCuadernillo());

        Color trasparente = new Color(0,0,0,0);
        GreenfootImage imgInvalidar = new GreenfootImage(50, 50);
        imgInvalidar.setColor(trasparente);
        imgInvalidar.fill();
        CasillaValidacion casillaInvalidar = new CasillaValidacion(false, imgInvalidar);
        addObject(casillaInvalidar, INVALIDAR_X, INVALIDAR_Y);

        GreenfootImage imgValidar = new GreenfootImage(50, 50);
        imgValidar.setColor(trasparente);
        imgValidar.fill();
        CasillaValidacion casillaValidar = new CasillaValidacion(true, imgValidar);
        addObject(casillaValidar, VALIDAR_X, VALIDAR_Y);

        siguienteEstudiante();
        actualizarMarcadorStrikes();
    }

    // Público para que Estudiante.java pueda llamarlo al terminar de irse
    public void siguienteEstudiante()
    {
        estudianteActual = generador.generarSiguiente();
        dni.mostrarDatos(estudianteActual);
        ficha.mostrarDatos(estudianteActual);

        if (spriteActual != null) {
            removeObject(spriteActual);
        }
        spriteActual = new Estudiante();
        addObject(spriteActual, ESTUDIANTE_X, ESTUDIANTE_Y);

        // Liberamos el candado para permitir volver a jugar
        procesandoDecision = false;
    }

    public void registrarDecision(boolean jugadorValido)
    {
        // Si ya estamos animando una salida o el juego terminó, ignorar clics
        if (procesandoDecision || errores >= LIMITE_ERRORES || aciertos >= LIMITE_ACIERTOS) return;

        procesandoDecision = true; // Bloquea los botones temporalmente

        boolean deberiaValidarse = estudianteActual.deberiaSerValidado();
        if (jugadorValido == deberiaValidarse) {
            aciertos++;
            if (verificarLimiteAciertos(aciertos)) {
                return; // Ya se cambió de pantalla, no sigue con la animación de salida
            }
        } else {
            errores++;
            actualizarMarcadorStrikes();
            if (verificarLimiteFallos(errores)) {
                return; // Ya se cambió de pantalla, no sigue con la animación de salida
            }
        }

        // Si el juego no terminó, le decimos al sprite actual que se retire
        if (spriteActual != null) {
            spriteActual.salir();
        }
    }

    private void actualizarMarcadorStrikes() {
        showText("STRIKES: " + errores + " / " + LIMITE_ERRORES, 1200, 50);
    }

    private boolean verificarLimiteFallos(int contador){
        if (contador >= LIMITE_ERRORES) {
            Greenfoot.setWorld(new GameOver());
            return true;
        }
        return false;
    }

    private boolean verificarLimiteAciertos(int contador){
        if (contador >= LIMITE_ACIERTOS) {
            Greenfoot.setWorld(new GameFinished());
            return true;
        }
        return false;
    }

    public int getAciertos() { return aciertos; }
    public int getErrores() { return errores; }
}
