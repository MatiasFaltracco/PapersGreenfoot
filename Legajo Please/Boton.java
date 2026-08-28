import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boton extends Actor
{
    /* Metodo Constructor. Boton recibe una GreenfootImage por cualquiera de los escenarios que lo instancie y se la asigna
     * Boton es generico para cualquier imagen, totalmente agnostico al escenario, y solo toma clicks y delega en CambiarPantalla(). 
     * Ver clases Mundo, GameFinished, GameOver, Historia y PantallaInicio
     */
    public Boton(GreenfootImage imagen){
        setImage(imagen);
        
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
               World mundo = getWorld();
               if (mundo instanceof Mundo){
                   ((Mundo)mundo).cambiarPantalla();
               }
        }
    }
}
