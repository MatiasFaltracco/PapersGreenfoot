import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mundo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Mundo extends World
{

    /**
     * Constructor for objects of class Mundo.
     * 
     */
    public Mundo()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1352, 1000, 1); 
    }
    
    // Metodo a ser definido a su manera por las clases hijas (las distintas pantallas y redirecciones de escenario). Se dispara por la clase Boton. Osea, aprovecha el polimorfismo
    public abstract void cambiarPantalla();
}