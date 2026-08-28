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
    
    // Se extrae la imagen del boton de Reset y se posiciona en coordenadas
    public GameFinished()
    {    
        Boton inicio = new Boton(new GreenfootImage("Reset.png"));
        addObject(inicio, 750,900);
    }
    @Override
    
    // Metodo dispardo por Boton. Metodo para volver a la pantalla de juego en caso de Victoria
    public void cambiarPantalla(){
         Greenfoot.setWorld(new MyWorld());
         Greenfoot.delay(20);
    }
}
