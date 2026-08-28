import greenfoot.*;

public class DNI extends Documento
{
    public DNI(int ancho, int alto)
    {
        super(ancho, alto);
        tamañoFuente = 10;
    }

    @Override
    public void mostrarDatos(DatosEstudiante datos)  //Se toman los datos de GenerarEstudiante y se emplea y redefine el Metodo heredado dibujarCampo
    {
        GreenfootImage imagen = nuevaImagenLimpia();

        dibujarCampo(imagen, datos.getNombre(),                170, 57);
        dibujarCampo(imagen, datos.getApellido(),              170, 77);
        dibujarCampo(imagen, datos.getDni(),                   170, 95);
        dibujarCampo(imagen, datos.getFechaNacimiento(),       195, 113);
        dibujarCampo(imagen, datos.getPais(),                  170,  130);
        dibujarCampo(imagen, datos.getProvincia(),             185, 147);
        dibujarCampo(imagen, datos.getCaducidad(),             185, 164);
        setImage(imagen);
    }
}