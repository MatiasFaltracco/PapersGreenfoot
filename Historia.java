import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Historia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Historia extends Mundo
{

   
    public Historia()
    {    
        GreenfootImage botonImagen = new GreenfootImage("Next.png");
        botonImagen.scale(150, 90);
        Boton inicio = new Boton(botonImagen);
    
        addObject(inicio, 500,635);    
    }
    @Override
    public void cambiarPantalla(){
         Greenfoot.setWorld(new MyWorld());
         Greenfoot.delay(20);
    }
}
