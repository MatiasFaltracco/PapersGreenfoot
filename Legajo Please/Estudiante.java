import greenfoot.*;

public class Estudiante extends Actor
{   
    //tamaños iniciales de los sprites antes de reescalar y al momento 0 de instanciarse por MyWorld
    private int anchoActual = 250;
    private int altoActual = 430;
    
    // Tamaños finales/objetivos del reescalado para simular un acercamiento al escritorio.
    private final int tamaño_final = 400;
    private final int posicion_X_final = 600; // Posición X frente al escritorio
    private final int velocidad_crecimiento = 2; 

    // Se guarda la posición inicial donde aparece en el mundo
    private int posicion_X_inicial;
    private boolean posicionInicialGuardada = false;

    // Se guarda la imagen original en memoria como plantilla
    private final GreenfootImage imagenOriginal = elegirSprite();
    
    // Bandera para controlar si entra o se va
    private boolean saliendo = false;

    public Estudiante() {
        reescalar();
    }
    
    // lista con las direcciones de las imagenes/sprites de los personajes
    public GreenfootImage elegirSprite(){
        GreenfootImage[] imagenes = {
            new GreenfootImage("images/Personajes/P1.png"),
            new GreenfootImage("images/Personajes/P2.png"),
            new GreenfootImage("images/Personajes/P3.png"),
            new GreenfootImage("images/Personajes/P4.png"),
            new GreenfootImage("images/Personajes/P5.png"),
            new GreenfootImage("images/Personajes/P6.png"),
            new GreenfootImage("images/Personajes/P7.png"),
            new GreenfootImage("images/Personajes/P8.png"),
            new GreenfootImage("images/Personajes/P9.png"),
            new GreenfootImage("images/Personajes/P10.png"),
            new GreenfootImage("images/Personajes/P11.png"),
            new GreenfootImage("images/Personajes/P12.png"),
            new GreenfootImage("images/Personajes/P13.png"),
           
        };
        
        //eleccion aleatoria de los sprites de personajes en lista
        int indice = Greenfoot.getRandomNumber(imagenes.length);
        return imagenes[indice];
    }
    
    public void reescalar() {
        // Se clona la  imagen original y se escala 
        GreenfootImage copia = new GreenfootImage(imagenOriginal);
        copia.scale(anchoActual, altoActual);
        setImage(copia);
    }

    // Metodo usado para retirar la imagen del personaje en MyWorld si y solo si no se cumplen condiciones de victoria o derrota
    public void salir() {
        saliendo = true;
    }

    public void act() 
    {
        // Guarda la coordenada X donde apareció en el primer ciclo
        if (!posicionInicialGuardada) {
            posicion_X_inicial = getX();
            posicionInicialGuardada = true;
        }

        if (!saliendo) {
            // Animacion de Entrada mediante reescalado (Ver metodo reescalar)
            if (anchoActual < tamaño_final) {
                anchoActual += velocidad_crecimiento;
                altoActual += velocidad_crecimiento;
                reescalar();

                // Sube el centro para compensar la expansión
                setLocation(getX(), getY() - (velocidad_crecimiento / 2));
            }
            else {
                if (getX() < posicion_X_final) {
                    setLocation(getX() + velocidad_crecimiento, getY());
                } else if (getX() > posicion_X_final) {
                    setLocation(getX() - velocidad_crecimiento, getY());
                } 
            }
        } 
        else {
            // Animacion de Salida (la inversa)
            if (getX() > posicion_X_inicial) {
                setLocation(getX() - velocidad_crecimiento, getY());
            } 
            else if (anchoActual > 250) {
                anchoActual -= velocidad_crecimiento;
                altoActual -= velocidad_crecimiento;
                reescalar();

                // Baja el centro para compensar la reducción
                setLocation(getX(), getY() + (velocidad_crecimiento / 2));
            } 
            else {
                // Al finalizar la retirada, le avisa a MyWorld para cargar el siguiente personaje
                MyWorld mundo = (MyWorld) getWorld();
                if (mundo != null) {
                    mundo.siguienteEstudiante();
                }
            }
        }
    }
}

