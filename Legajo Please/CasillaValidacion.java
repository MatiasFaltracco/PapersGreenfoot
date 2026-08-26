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

    public CasillaValidacion(boolean esValidar, GreenfootImage imagen)
    {
        this.esValidar = esValidar;
        setImage(imagen);
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            MyWorld mundo = (MyWorld) getWorld();
            mundo.registrarDecision(esValidar);
        }
    }
}
