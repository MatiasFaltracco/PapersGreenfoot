import greenfoot.*;

public class FichaEstudiante extends Documento
{
    public FichaEstudiante(int ancho, int alto)
    {
        super(ancho, alto);
        tamañoFuente = 14;
    }

    @Override
    public void mostrarDatos(DatosEstudiante datos)
    {
        GreenfootImage imagen = nuevaImagenLimpia();

        // 1. LEGAJO
        dibujarCampo(imagen, String.valueOf(datos.getLegajo()),        96, 115);
        // 2. NOMBRE Y APELLIDO
        dibujarCampo(imagen, datos.getNombre() + " " + datos.getApellido(), 96, 150);
        // 3. DNI
        dibujarCampo(imagen, datos.getDni(),                           60, 175);
        // 4. TURNO
        dibujarCampo(imagen, datos.getTurno(),                         89, 200);
        // 5. REGULARIDAD (Si/No)
        dibujarCampo(imagen, datos.getRegularidad(),                   165, 235);
        // 6. CADUCIDAD
        dibujarCampo(imagen, datos.getCaducidad(),                     120, 260);
        // 7. CARRERA
        dibujarCampo(imagen, datos.getCarrera(),                       90, 285);
        setImage(imagen);
    }
}
