import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Mundo
{

    /**
     * Constructor for objects of class GameOver.
     * 
     */
    
    // Se extrae y posiciona la imagen para el boton Reset
    public GameOver()
    {    
        Boton inicio = new Boton(new GreenfootImage("Reset.png"));
        addObject(inicio, 750,900);
    }
    
    @Override
    // Disparado por la clase Boton. Metodo para volver al juego en caso de GameOver
    public void cambiarPantalla(){
         Greenfoot.setWorld(new MyWorld());
         Greenfoot.delay(20);
    }
}