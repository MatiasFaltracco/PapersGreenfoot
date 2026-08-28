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
    
    // Se extrae y posiciona la imagen para el boton Start
    public PantallaInicio()
    {    

        Boton inicio = new Boton(new GreenfootImage("BotonStart.png"));
        addObject(inicio, 700,600);
    }
    
    @Override
    // Metodo disparado por Boton. Tras apretar Start en la pantalla de inicio, se pasa a la pantalla de contexto/historia
    public void cambiarPantalla(){
        Greenfoot.setWorld(new Historia());
        Greenfoot.delay(20);
    }
}

