import greenfoot.*;

public class MyWorld extends World
{
    // Lienzos transparentes para alojar los textos
    private static final int DNI_ANCHO = 160, DNI_ALTO = 150;
    private static final int FICHA_ANCHO = 170, FICHA_ALTO = 200;
    private static final int CUADERNILLO_ANCHO = 400, CUADERNILLO_ALTO = 200;

    // Centros exactos de las cajas en la imagen de fondo
    private static final int DNI_X = 80, DNI_Y = 570;
    private static final int FICHA_X = 242, FICHA_Y = 592;
    private static final int CUADERNILLO_X = 520, CUADERNILLO_Y = 592;

    // Posiciones de los botones de validación (éstos estaban perfectos)
    private static final int INVALIDAR_X = 48, INVALIDAR_Y = 630;
    private static final int VALIDAR_X = 112, VALIDAR_Y = 630;

    private static final int ESTUDIANTE_X = 260, ESTUDIANTE_Y = 310;

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
        super(1000, 680, 1); 
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

        RelojTimer reloj = new RelojTimer();
        addObject(reloj, 600, 100);

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
            verificarLimiteAciertos(aciertos);
        } else {
            errores++;
            verificarLimiteFallos(errores);
            
        }
        siguienteEstudiante();
    }
    
    private void verificarLimiteFallos(int contador){
        if (contador == 5) {
            cambiarPantalla(new GameOver());
        }
    }
    
    private void verificarLimiteAciertos(int contador){
        if (contador == 15) {
            cambiarPantalla(new GameFinished());
        }
    }
    
    public void cambiarPantalla(Mundo siguientePantalla){
        Greenfoot.setWorld(siguientePantalla);
        Greenfoot.delay(20);
    }
    
    public int getAciertos() { return aciertos; }
    public int getErrores() { return errores; }
}
