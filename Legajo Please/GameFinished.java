import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameFinished here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameFinished extends Mundo
{

    /**
     * Constructor for objects of class GameFinished.
     * 
     */
    public GameFinished()
    {    
        Boton inicio = new Boton(new GreenfootImage("Reset.png"));
        addObject(inicio, 750,900);
    }
    @Override
    public void cambiarPantalla(){
         Greenfoot.setWorld(new MyWorld());
         Greenfoot.delay(20);
    }
}
