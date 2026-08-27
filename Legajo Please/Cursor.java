import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cursor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Cursor extends Actor 
{
    public Cursor() 
    {
        // Usamos una imagen interna que Greenfoot SIEMPRE tiene disponible
    }

    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if (mouse == null) 
        {
            // Si el mouse es null, saldrá este mensaje en la consola de Greenfoot
            System.out.println("ERROR: El juego no detecta el raton en la ventana.");
        } 
        else 
        {
            // Si entra aquí, el ratón sí funciona y el objeto DEBE moverse
            System.out.println("Mouse detectado en X: " + mouse.getX() + " Y: " + mouse.getY());
            setLocation(mouse.getX(), mouse.getY());
        }
    }
}

