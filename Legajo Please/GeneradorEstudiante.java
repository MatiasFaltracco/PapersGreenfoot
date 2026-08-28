import greenfoot.Greenfoot;
import java.util.ArrayList;
import java.util.List;

/*
 * Contiene las listas base de datos, genera y guarda las 22 filas del Cuadernillo (fuente de verdad, fija durante toda la partida),
 * y produce estudiantes nuevos al azar cada vez que se le pide uno.
 * Es agnóstico a la cantidad de estudiantes que se vayan a mostrar, cada llamada a generarSiguiente() arma un DatosEstudiante independiente.
 */
public class GeneradorEstudiante
{
    // Datos posibles a tomar
    
    private static final String[] NOMBRES = {
        "Nicolás", "Mateo", "Joaquín", "Lucas", "Santiago", "Agustína", "Facundo",
        "Gonzalo", "Tomás", "Ignacio", "Valentina", "Martín", "Franco", "Ramiro",
        "Bautista", "Jeremías", "Julia", "Ezequiel", "Gabriela", "Thiago"
    };

    private static final String[] APELLIDOS = {
        "González", "Rodríguez", "Gómez", "Fernández", "López", "Díaz",
        "Martínez", "Pérez", "Sánchez", "Romero", "Sosa", "Torres", "Álvarez",
        "Ruiz", "Ramírez", "Flores", "Benítez", "Acosta", "Medina", "Herrera"
    };

    private static final String[] CARRERAS = {
        "Ing. Civil", "Ing. en Sistemas", "Ing. Electrónica",
        "Ing. Mecánica", "Ing. Química"
    };

    private static final String[] PROVINCIAS_ARGENTINAS = {
        "Buenos Aires", "Córdoba", "Santa Fe", "Mendoza", "Entre Ríos",
        "Tucumán", "Salta", "Chaco", "Corrientes", "Misiones", "San Juan",
        "Neuquén", "Río Negro", "La Pampa", "Jujuy"
    };

    private static final String[] TURNOS = { "Mañana", "Mediodía", "Tarde", "Noche" };

    private static final String[] CAMPOS_FALSEABLES = {
        "NOMBRE", "APELLIDO", "DNI", "LEGAJO", "CADUCIDAD", "CARRERA", "TURNO"
    };

    // Rango de legajos. Los plausibles y los falsos tienen rangos distintos.
    private static final int legajo_plausible_min = 12000;
    private static final int legajo_plausible_max = 17999;
    private static final int legajo_falso_min = 18000;
    private static final int legajo_falso_max = 18999;
    
    /* En el Cuadernillo, solamente 8 legajos (todos plausibles porque es el documento que usa el jugador para comparar)
     * pertenecen a estudiantes que apareceran, los otros 14 son de relleno para despistar al jugador. 
     */
    private static final int cantidad_legajos_jugables = 8;
    private static final int cantidad_legajos_relleno = 14;

    // ---------- Estado fijo generado al construir el Cuadernillo----------
    private List<FilaCuadernillo> cuadernillo;       // 22 filas, fuente de verdad
    private List<DatosEstudiante> estudiantesJugables; // los 8 reales completos

    public GeneradorEstudiante()
    {
        generarCuadernilloYJugables();
    }

    /*
     * Fila del Cuadernillo: Legajo, Carrera, Turno, Condición.
     * No lleva nombre ni apellido (el Cuadernillo nunca revela identidad, si el legajo es verdadero, se navega por el legajo (en el juego).
     */
    public static class FilaCuadernillo
    {
        public final int legajo;
        public final String carrera;
        public final String turno;
        public final String condicion; // "Cursando" o "Libre"

        public FilaCuadernillo(int legajo, String carrera, String turno, String condicion)
        {
            this.legajo = legajo;
            this.carrera = carrera;
            this.turno = turno;
            this.condicion = condicion;
        }
    }

    private void generarCuadernilloYJugables()
    {
        cuadernillo = new ArrayList<FilaCuadernillo>();
        estudiantesJugables = new ArrayList<DatosEstudiante>();

        // 22 legajos plausibles únicos, sorteados sin repetición
        List<Integer> legajosPlausibles = legajosUnicosEnRango(
            legajo_plausible_min, legajo_plausible_max, cantidad_legajos_jugables + cantidad_legajos_relleno
        );

        // Los primeros 8 son jugables (verdaderos), el resto es relleno
        List<Integer> legajosJugables = legajosPlausibles.subList(0, cantidad_legajos_jugables);
        List<Integer> legajosRelleno = legajosPlausibles.subList(cantidad_legajos_jugables, legajosPlausibles.size());

        // Generar los 8 estudiantes jugables completos (siempre Cursando, nunca Libre)
        for (int legajo : legajosJugables) {
            String nombre = elegirAlAzar(NOMBRES);
            String apellido = elegirAlAzar(APELLIDOS);
            String dni = generarDni();
            String fechaNac = generarFechaNacimientoValida();
            String provincia = elegirAlAzar(PROVINCIAS_ARGENTINAS);
            String caducidad = generarFechaCaducidad();
            String carrera = elegirAlAzar(CARRERAS);
            String turno = elegirAlAzar(TURNOS);

            DatosEstudiante jugable = new DatosEstudiante(
                nombre, apellido, dni, fechaNac, "Argentina", provincia, caducidad,
                legajo, carrera, turno, "Sí", true, null
            );
            estudiantesJugables.add(jugable);

            // Su fila correspondiente en el cuadernillo (Condición = Cursando)
            cuadernillo.add(new FilaCuadernillo(legajo, carrera, turno, "Cursando"));
        }

        // Filas de relleno: solo cuadernillo, sin identidad, pueden ser Libre
        for (int legajo : legajosRelleno) {
            String carrera = elegirAlAzar(CARRERAS);
            String turno = elegirAlAzar(TURNOS);
            String condicion = Greenfoot.getRandomNumber(2) == 0 ? "Cursando" : "Libre";
            cuadernillo.add(new FilaCuadernillo(legajo, carrera, turno, condicion));
        }
    }

    //Metodo Generador de un estudiante nuevo al azar: puede ser uno de los 8 jugables verdaderos tal cual, o una copia de uno de ellos con uncampo alterado (falso).
    
    public DatosEstudiante generarSiguiente()
    {
    DatosEstudiante base = estudiantesJugables.get(Greenfoot.getRandomNumber(estudiantesJugables.size()));
    boolean esVerdadero = Greenfoot.getRandomNumber(2) == 0; // 50/50
    if (esVerdadero) {
        // Se devuelve una copia idéntica (mismos datos, mismo legajo real)
        System.out.println("Es verdadero");
        return new DatosEstudiante(                                                             //ver gets al final de la clase DatosEstudiante)
            base.getNombre(), base.getApellido(), base.getDni(), base.getFechaNacimiento(),
            base.getPais(), base.getProvincia(), base.getCaducidad(), base.getLegajo(),
            base.getCarrera(), base.getTurno(), base.getRegularidad(), true, null
        );
    }
    String campoFalso = elegirAlAzar(CAMPOS_FALSEABLES);
    System.out.println("Es Falso");
    return generarConCampoFalso(base, campoFalso);
    }
    
    
    //Metodo generador de un campo falso para un estudiante con esVerdadero = False
    private DatosEstudiante generarConCampoFalso(DatosEstudiante base, String campoFalso)
    {   
        // Se obtienen los datos de DatosEstudiante (ver gets al final de esa clase
        String nombre = base.getNombre();
        String apellido = base.getApellido();
        String dni = base.getDni();
        String fechaNac = base.getFechaNacimiento();
        String pais = base.getPais();
        String provincia = base.getProvincia();
        String caducidad = base.getCaducidad();
        int legajo = base.getLegajo();
        String carrera = base.getCarrera();
        String turno = base.getTurno();
        String regularidad = base.getRegularidad();

        switch (campoFalso) {
            case "NOMBRE":
                nombre = elegirOtro(NOMBRES, nombre);
                break;
            case "APELLIDO":
                apellido = elegirOtro(APELLIDOS, apellido);
                break;
            case "DNI":
                dni = generarDni(); // otro número de 8 dígitos, sin regla especial
                break;
            case "LEGAJO":
                // Legajo falso: no figura en ninguna fila del cuadernillo
                legajo = legajo_falso_min + Greenfoot.getRandomNumber(
                    legajo_falso_max - legajo_falso_min + 1
                );
                break;
            case "CADUCIDAD":
                caducidad = generarFechaCaducidad(); // distinta a la del DNI real
                break;
            case "CARRERA":
                carrera = elegirOtro(CARRERAS, carrera);
                break;
            case "TURNO":
                turno = elegirOtro(TURNOS, turno);
                break;
        }

        return new DatosEstudiante(
            nombre, apellido, dni, fechaNac, pais, provincia, caducidad,
            legajo, carrera, turno, regularidad, false, campoFalso
        );
    }

    // Funciones auxiliares de generacion:

    private String elegirAlAzar(String[] lista)
    {
        return lista[Greenfoot.getRandomNumber(lista.length)];
    }

    private String elegirOtro(String[] lista, String actual)
    {
        String elegido;
        do {
            elegido = elegirAlAzar(lista);
        } while (elegido.equals(actual) && lista.length > 1);
        return elegido;
    }

    private String generarDni()
    {
        int numero = 30000000 + Greenfoot.getRandomNumber(15000000);
        return String.valueOf(numero);
    }

    private String generarFechaCaducidad()
    {
        int dia = 1 + Greenfoot.getRandomNumber(28);
        int mes = 1 + Greenfoot.getRandomNumber(12);
        int año = 2026 + Greenfoot.getRandomNumber(5); // 2026-2030
        return formatearFecha(dia, mes, año);
    }

    //Genera una fecha de nacimiento que no supera el 21/07/2010, la pista para que el jugador se de cuenta esta en el calendario de la imagen de la escena
    
    private String generarFechaNacimientoValida()
    {
        int dia = 1 + Greenfoot.getRandomNumber(28);
        int mes = 1 + Greenfoot.getRandomNumber(12);
        int año = 1985 + Greenfoot.getRandomNumber(16); // 1985-2010

        if (año == 2010 && (mes > 7 || (mes == 7 && dia > 21))) {
            año = 2009;
        }
        return formatearFecha(dia, mes, año);
    }

    private String formatearFecha(int dia, int mes, int año)
    {
        return String.format("%02d/%02d/%d", dia, mes, año);
    }

    private List<Integer> legajosUnicosEnRango(int min, int max, int cantidad)
    {
        List<Integer> disponibles = new ArrayList<Integer>();
        for (int i = min; i <= max; i++) {
            disponibles.add(i);
        }
        List<Integer> resultado = new ArrayList<Integer>();
        for (int i = 0; i < cantidad && !disponibles.isEmpty(); i++) {
            int indice = Greenfoot.getRandomNumber(disponibles.size());
            resultado.add(disponibles.remove(indice));
        }
        return resultado;
    }

    // Acceso para el Cuadernillo
    public List<FilaCuadernillo> getCuadernillo()
    {
        return cuadernillo;
    }
}
