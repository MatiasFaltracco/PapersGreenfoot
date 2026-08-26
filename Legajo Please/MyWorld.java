import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    public MyWorld()
    {    
        super(924, 641, 1); 
        Estudiante alumno = new Estudiante();
        addObject(alumno, 260, 310);
        
        RelojTimer reloj = new RelojTimer();
        addObject(reloj, 600, 100);
        
    }
}
