import greenfoot.*;

public class DNI extends Documento
{
    public DNI(int ancho, int alto)
    {
        super(ancho, alto);
        tamañoFuente = 10;
    }

    @Override
    public void mostrarDatos(DatosEstudiante datos)
    {
        GreenfootImage imagen = nuevaImagenLimpia();

        dibujarCampo(imagen, datos.getNombre(),                132, 14);
        dibujarCampo(imagen, datos.getApellido(),              136, 28);
        dibujarCampo(imagen, datos.getDni(),                   132, 42);
        dibujarCampo(imagen, datos.getFechaNacimiento(),       154, 56);
        dibujarCampo(imagen, datos.getPais(),                  132, 68);
        dibujarCampo(imagen, datos.getProvincia(),             139, 82);
        dibujarCampo(imagen, datos.getCaducidad(),             143, 96);
        setImage(imagen);
    }
}