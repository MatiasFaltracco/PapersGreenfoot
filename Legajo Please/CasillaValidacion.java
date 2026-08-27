import greenfoot.*;

/**
 * Casilla clickeable del recuadro "Validación". Se instancian dos objetos
 * de esta misma clase: uno para Validado y otro para Invalidado, indicando
 * con el flag esValidar cuál decisión representa cada uno.
 * 
 * Al hacer click, le informa a MyWorld la decisión tomada para que este
 * la compare contra el estudiante actual y pase al siguiente.
 */
public class CasillaValidacion extends Actor
{
    private boolean esValidar; // true = casilla "Validado", false = "Invalidado"
    private int cooldown = 0;
    private GreenfootImage[] marcas = {
        new GreenfootImage("Sello.png"),
        new GreenfootImage("Marca.png"),
    };
    
    public CasillaValidacion(boolean esValidar, GreenfootImage imagen)
    {
        this.esValidar = esValidar;
        setImage(imagen);
        marcas[1] = imagen; 
    }

public void act() {   
    if (Greenfoot.mouseClicked(this)) {
        MyWorld mundo = (MyWorld) getWorld();
        
        for (int tamano = 300; tamano >= 120; tamano -= 60) {
            
            GreenfootImage sello = marcas[0];
            sello.scale(tamano, tamano / 2);
            setImage(sello);
            
            Greenfoot.delay(5); 
            }
        GreenfootImage marca= marcas[1];
        marca.scale(120,60);
        setImage(marcas[1]);
        mundo.registrarDecision(esValidar);
        
        }
    }
}
