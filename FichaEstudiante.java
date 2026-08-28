import greenfoot.*;

public class FichaEstudiante extends Documento
{
    public FichaEstudiante(int ancho, int alto)
    {
        super(ancho, alto);
        tamañoFuente = 8;
    }

    @Override
    public void mostrarDatos(DatosEstudiante datos)
    {
        GreenfootImage imagen = nuevaImagenLimpia();

        // 1. LEGAJO
        dibujarCampo(imagen, String.valueOf(datos.getLegajo()),        66, 60);
        // 2. NOMBRE Y APELLIDO
        dibujarCampo(imagen, datos.getNombre() + " " + datos.getApellido(), 95, 83);
        // 3. DNI
        dibujarCampo(imagen, datos.getDni(),                           41, 98);
        // 4. TURNO
        dibujarCampo(imagen, datos.getTurno(),                         61, 113);
        // 5. REGULARIDAD (Si/No)
        dibujarCampo(imagen, datos.getRegularidad(),                   110, 138);
        // 6. CADUCIDAD
        dibujarCampo(imagen, datos.getCaducidad(),                     91, 155);
        // 7. CARRERA
        dibujarCampo(imagen, datos.getCarrera(),                       60, 173);

        setImage(imagen);
    }
}
