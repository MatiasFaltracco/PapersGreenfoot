import greenfoot.*;

public class MyWorld extends World
{
    // Tamaños de los documentos en la escena (son uno con la imagen asique estos son "marcos" invisibles para delimitar los datos de texto de cada uno)
    private static final int DNI_ANCHO = 280, DNI_ALTO = 200;
    private static final int FICHA_ANCHO = 230, FICHA_ALTO = 310;
    private static final int CUADERNILLO_ANCHO = 520, CUADERNILLO_ALTO = 310;

    // Centros X e Y 
    private static final int DNI_X = 151, DNI_Y = 837;
    private static final int FICHA_X = 466, FICHA_Y = 848;
    private static final int CUADERNILLO_X = 856, CUADERNILLO_Y = 850;

    // Botones de validación (Tambien "marcos transparentes")
    private static final int INVALIDAR_X = 1180, INVALIDAR_Y = 828;
    private static final int VALIDAR_X = 1282, VALIDAR_Y = 828;
    
    // Coordenadas de los sprites de los Estudiantes (Posicion donde se instancian)
    private static final int ESTUDIANTE_X = 380, ESTUDIANTE_Y = 456;

    // Condiciones de Fin de partida
    private static final int limite_errores = 5;
    private static final int limite_aciertos = 15;

    private GeneradorEstudiante generador;
    private DatosEstudiante estudianteActual;

    private DNI dni;
    private FichaEstudiante ficha;
    private Cuadernillo cuadernillo;
    private Estudiante spriteActual;

    private int aciertos = 0;
    private int errores = 0;

    // Impedimiento para evitar que el jugador spamee clicks cuando se esta "animando" un estudiante
    private boolean procesandoDecision = false;

    public MyWorld()
    {
        super(1352, 1000, 1);

        // Instanciamiento de Actores en la escena
        
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

    // Metodo empleado por laa clase Estudiante para llamar a un nuevo estudiante cuando la animacion del vigente termine
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

        // Se libera el impedimiento de clicks para que se vuelvan a tomar las validaciones para el nuevo estudiante entrante
        procesandoDecision = false;
    }

    public void registrarDecision(boolean jugadorValido)
    {
        // Si ya estamos animando una salida o el juego terminó, ignorar clics
        if (procesandoDecision || errores >= limite_errores || aciertos >= limite_aciertos) return;

        procesandoDecision = true; // Se bloquean los validar

        boolean deberiaValidarse = estudianteActual.deberiaSerValidado();
        
        /* jugadorValido es el click del jugador
            Si se clickeo Valido, es True
            Si se clickeo Invalido es Falso
            y deberiaValidarse es el booleano de si el estudiante es valido o no  
        /*/
        
        if (jugadorValido == deberiaValidarse) {
            aciertos++;
            if (verificarLimiteAciertos(aciertos)) {
                return; // Se cambia a la pantalla de Victoria, no sigue con la animación de salida
            }
        } else {
            errores++;
            actualizarMarcadorStrikes();
            if (verificarLimiteFallos(errores)) {
                return; // Se cambia a la pantalla de GameOver, no sigue con la animación de salida
            }
        }

        // Si no se cambio de pantalla porque el juego no se gano o se perdio, el sprite del estudiante se retira
        if (spriteActual != null) {
            spriteActual.salir();
        }
    }
    
    
    // Metodo que muestra el conteo de los strikes/errores en pantalla, cumpliendo uno de los requisitos que tiene que tener el juego segun la actividad en el campus.
    private void actualizarMarcadorStrikes() {
        showText("STRIKES: " + errores + " / " + limite_errores, 1200, 50);
    }

    // Metodo que cambia a la pantalla de GameOver si el conteo de strikes alcanza el limite que definimos como atributo
    private boolean verificarLimiteFallos(int contador){
        if (contador >= limite_errores) {
            Greenfoot.setWorld(new GameOver());
            return true;
        }
        return false;
    }

    // Metodo que cambia a la pantalla de Victoria si se alcanza la cantidad de aciertos de validacion que definimos como atributo
    private boolean verificarLimiteAciertos(int contador){
        if (contador >= limite_aciertos) {
            Greenfoot.setWorld(new GameFinished());
            return true;
        }
        return false;
    }

}
