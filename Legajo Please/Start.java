import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends World
{

    /**
     * Constructor for objects of class Start.
     * 
     */
    public Start()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(924, 680, 1);
        
        BotonStart inicio = new BotonStart();
        addObject(inicio, 462,335);
    }
    
    public void cambiarPantalla(){
        Greenfoot.setWorld(new MyWorld());
        Greenfoot.delay(20);
    }
}
