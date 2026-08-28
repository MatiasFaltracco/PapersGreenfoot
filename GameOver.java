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
    public GameOver()
    {    
        Boton inicio = new Boton(new GreenfootImage("Reset.png"));
        addObject(inicio, 500,600);
    }
    
    @Override
    public void cambiarPantalla(){
         Greenfoot.setWorld(new MyWorld());
         Greenfoot.delay(20);
    }
}
