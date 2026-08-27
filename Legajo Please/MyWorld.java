import greenfoot.*;

public class MyWorld extends World
{
    // Tamaños de los lienzos
    private static final int DNI_ANCHO = 234, DNI_ALTO = 221;
    private static final int FICHA_ANCHO = 249, FICHA_ALTO = 294;
    private static final int CUADERNILLO_ANCHO = 585, CUADERNILLO_ALTO = 294;

    // Centros X e Y de los documentos
    private static final int DNI_X = 117, DNI_Y = 838;
    private static final int FICHA_X = 354, FICHA_Y = 871;
    private static final int CUADERNILLO_X = 761, CUADERNILLO_Y = 871;

    // Botones de validación
    private static final int INVALIDAR_X = 70, INVALIDAR_Y = 941;
    private static final int VALIDAR_X = 179, VALIDAR_Y = 941;

    // Posición del estudiante (sprite)
    private static final int ESTUDIANTE_X = 380, ESTUDIANTE_Y = 456;

    private GeneradorEstudiante generador;
    private DatosEstudiante estudianteActual;
    

    private DNI dni;
    private FichaEstudiante ficha;
    private Cuadernillo cuadernillo;
    private Estudiante spriteActual;

    private int aciertos = 0;
    private int errores = 0;

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
        
        //Posicion del Reloj
        RelojTimer reloj = new RelojTimer();
        addObject(reloj, 800, 150);

        siguienteEstudiante();
    }

    private void siguienteEstudiante()
    {
        estudianteActual = generador.generarSiguiente();
        dni.mostrarDatos(estudianteActual);
        ficha.mostrarDatos(estudianteActual);

        if (spriteActual != null) {
            removeObject(spriteActual);
        }
        spriteActual = new Estudiante();
        addObject(spriteActual, ESTUDIANTE_X, ESTUDIANTE_Y);
    }

    public void registrarDecision(boolean jugadorValido)
    {
        boolean deberiaValidarse = estudianteActual.deberiaSerValidado();
        if (jugadorValido == deberiaValidarse) {
            aciertos++;
        } else {
            errores++;
        }
        siguienteEstudiante();
    }

    public int getAciertos() { return aciertos; }
    public int getErrores() { return errores; }
}
