import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends World
{

     public Start()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1352, 1000, 1);
        
        BotonStart inicio = new BotonStart();
        addObject(inicio, 700,600);
    }
    
    public void cambiarPantalla(){
        Greenfoot.setWorld(new MyWorld());
        Greenfoot.delay(20);
    }
}
