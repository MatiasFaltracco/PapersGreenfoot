import greenfoot.*;

public class Estudiante extends Actor
{
    private int anchoActual = 250;
    private int altoActual = 430;
    private final int tamaño_final = 400;
    private final int posicion_X_final = 600; // Posición X frente al escritorio
    private final int velocidad_crecimiento = 2; 

    // se guarda la imagen original en memoria como plantilla
    private final GreenfootImage imagenOriginal = elegirSprite();

    public Estudiante() {
        reescalar();
    }
    
    public GreenfootImage elegirSprite(){
        GreenfootImage[] imagenes = {
            new GreenfootImage("images/Personajes/P1.png"),
            new GreenfootImage("images/Personajes/P2.png"),
            new GreenfootImage("images/Personajes/P3.png"),
            new GreenfootImage("images/Personajes/P4.png"),
        };
        
        int indice = Greenfoot.getRandomNumber(imagenes.length);
        return imagenes[indice];
    }
    
    public void reescalar() {
        // se clona la original y se escala 
        GreenfootImage copia = new GreenfootImage(imagenOriginal);
        copia.scale(anchoActual, altoActual);
        setImage(copia);
    }

    public void act() 
    {
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
}
