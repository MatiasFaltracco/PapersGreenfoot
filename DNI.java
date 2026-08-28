import greenfoot.*;

public class DNI extends Documento
{
    public DNI(int ancho, int alto)
    {
        super(ancho, alto);
        tamañoFuente = 8;
    }

    @Override
    public void mostrarDatos(DatosEstudiante datos)
    {
        GreenfootImage imagen = nuevaImagenLimpia();

        dibujarCampo(imagen, datos.getNombre(),                90, 11);
        dibujarCampo(imagen, datos.getApellido(),              93, 21);
        dibujarCampo(imagen, datos.getDni(),                   90, 31);
        dibujarCampo(imagen, datos.getFechaNacimiento(),       105, 41);
        dibujarCampo(imagen, datos.getPais(),                  90, 49);
        dibujarCampo(imagen, datos.getProvincia(),             95, 59);
        dibujarCampo(imagen, datos.getCaducidad(),             98, 68);

        setImage(imagen);
    }
}