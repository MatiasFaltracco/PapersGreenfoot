/*
 * Agrupa todos los datos de un estudiante que aparece frente al mostrador. No hace nada, solamente contenedor de datos.
 * Si el estudiante es falso, campoFalso indica cuál de sus campos no es consistente con lo que debería ser (usado para saber si el inspector
 * debe Validar o Invalidar). Si es verdadero, campoFalso es null.
 */
public class DatosEstudiante
{
    // Datos que aparecen en el DNI
    private String nombre;
    private String apellido;
    private String dni;          // 8 dígitos, como String
    private String fechaNacimiento; // formato dd/MM/yyyy
    private String pais;
    private String provincia;
    private String caducidad;    // formato dd/MM/yyyy (compartido con Ficha)

    // Datos que aparecen en la Ficha de Estudiante
    private int legajo;
    private String carrera;
    private String turno;        // "Mañana", "Mediodía", "Tarde", "Noche"
    private String regularidad;  // "Sí" o "No"

    // datos de generación (para saber si el jugador acierta o no)
    private boolean esVerdadero;    // Con cada generacion (GenerarEstudiante) ya se determinara si se es valido o no, no hay una verificacion de todos los valores al momento de la validacion
    
    // contendor de los posibles datos a presentarse como falsos
    private String campoFalso;   // "NOMBRE","APELLIDO","DNI","LEGAJO","CADUCIDAD","CARRERA","TURNO" o null
    
    //Constructor. Se asignan los datos para un Estudiante
    public DatosEstudiante(String nombre, String apellido, String dni, String fechaNacimiento,
                            String pais, String provincia, String caducidad, int legajo,
                            String carrera, String turno, String regularidad,
                            boolean esVerdadero, String campoFalso)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
        this.provincia = provincia;
        this.caducidad = caducidad;
        this.legajo = legajo;
        this.carrera = carrera;
        this.turno = turno;
        this.regularidad = regularidad;
        this.esVerdadero = esVerdadero;
        this.campoFalso = campoFalso;
    }

    // Gets usados por los Documentos (FichaEstudiante, DNI, Cuadernillo) para dibujar los datos
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getPais() { return pais; }
    public String getProvincia() { return provincia; }
    public String getCaducidad() { return caducidad; }
    public int getLegajo() { return legajo; }
    public String getCarrera() { return carrera; }
    public String getTurno() { return turno; }
    public String getRegularidad() { return regularidad; }

    public boolean esVerdadero() { return esVerdadero; }
    public String getCampoFalso() { return campoFalso; }

    /*
     * El jugador "acierta" si valida a un estudiante verdadero
     * o si invalida a uno falso. Asi no se verifica la validez comparando campo por campo. Como se comento arriba, ya se sabe desde que se genera el estudiante
     * Ver metodo registrarDecision en la clase MyWorld
     */
    public boolean deberiaSerValidado()
    {
        return esVerdadero;
    }
}