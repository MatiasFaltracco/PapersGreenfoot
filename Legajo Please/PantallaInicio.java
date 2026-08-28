import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PantallaInicio extends Mundo
{

    /**
     * Constructor for objects of class Start.
     * 
     */
    public PantallaInicio()
    {    

        Boton inicio = new Boton(new GreenfootImage("BotonStart.png"));
        addObject(inicio, 700,600);
    }
    
    @Override
    public void cambiarPantalla(){
        Greenfoot.setWorld(new Historia());
        Greenfoot.delay(20);
    }
}

