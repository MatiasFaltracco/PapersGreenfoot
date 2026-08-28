import greenfoot.*;

/**
 * Padre común de los documentos que muestran datos superpuestos sobre la
 * escena (DNI, FichaEstudiante, Cuadernillo). Cada hijo mantiene su propia
 * imagen base (la casilla/documento vacío o transparente) y redibuja todos
 * sus campos de texto sobre una copia de esa imagen cada vez que cambian
 * los datos a mostrar.
 */
public abstract class Documento extends Actor
{
    protected GreenfootImage imagenBase;

    // Color y fuente por defecto para los campos. Los hijos pueden pisar
    // estos valores si un documento necesita otro estilo puntual.
    protected Color colorTexto = Color.BLACK;
    protected int tamañoFuente = 16;

    public Documento(int ancho, int alto)
    {
        // Se toma una base transparente debido a que las imagenes de los documentos ya estan integradas al escenario, no son clases con imagenes seteadas y superpuestas en instanciacion. 
        
        this.imagenBase = new GreenfootImage(ancho, alto);
        setImage(new GreenfootImage(imagenBase));
    }

    //Reinicia la imagen visible a una copia limpia de la base, para podervolver a dibujar todos los campos desde cero sin acumular texto viejo.
    
    protected GreenfootImage nuevaImagenLimpia()
    {
        return new GreenfootImage(imagenBase);
    }

    //Dibuja un campo de texto en una posición interna. No tiene en cuenta la imagen del mundo sino el ancho y alto definido en la imagen base de cada documento.
     
    protected void dibujarCampo(GreenfootImage imagen, String texto, int x, int y)
    {
        imagen.setColor(colorTexto);
        imagen.setFont(new Font("Arial", false, false, tamañoFuente));
        imagen.drawString(texto, x, y);
    }

    //Cada documento define cómo volcar los datos del estudiante actualsobre sus propias casillas (polimorfismo, tal como pasa con la clase Mundo).
     
    public abstract void mostrarDatos(DatosEstudiante datos);
}
