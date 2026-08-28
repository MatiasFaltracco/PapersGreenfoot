import greenfoot.Greenfoot;
import java.util.ArrayList;
import java.util.List;

/**
 * Contiene las listas base de datos, genera y guarda las 22 filas del
 * Cuadernillo (fuente de verdad, fija durante toda la partida), y produce
 * estudiantes nuevos al azar cada vez que se le pide uno.
 * 
 * Es agnóstico a la cantidad de estudiantes que se vayan a mostrar: cada
 * llamada a generarSiguiente() arma un DatosEstudiante independiente.
 */
public class GeneradorEstudiante
{
    // ---------- Listas base ----------
    private static final String[] NOMBRES = {
        "Nicolás", "Mateo", "Joaquín", "Lucas", "Santiago", "Agustín", "Facundo",
        "Gonzalo", "Tomás", "Ignacio", "Valentín", "Martín", "Franco", "Ramiro",
        "Bautista", "Jeremías", "Julián", "Ezequiel", "Gabriel", "Thiago"
    };

    private static final String[] APELLIDOS = {
        "González", "Rodríguez", "Gómez", "Fernández", "López", "Díaz",
        "Martínez", "Pérez", "Sánchez", "Romero", "Sosa", "Torres", "Álvarez",
        "Ruiz", "Ramírez", "Flores", "Benítez", "Acosta", "Medina", "Herrera"
    };

    private static final String[] CARRERAS = {
        "Ing. Civil", "Ing. en Sistemas de Información", "Ing. Electrónica",
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

    // Rango de legajos
    private static final int LEGAJO_PLAUSIBLE_MIN = 12000;
    private static final int LEGAJO_PLAUSIBLE_MAX = 17999;
    private static final int LEGAJO_INEXISTENTE_MIN = 18000;
    private static final int LEGAJO_INEXISTENTE_MAX = 18999;

    private static final int CANTIDAD_JUGABLES = 8;
    private static final int CANTIDAD_RELLENO = 14;

    // ---------- Estado fijo generado al construir ----------
    private List<FilaCuadernillo> cuadernillo;       // 22 filas, fuente de verdad
    private List<DatosEstudiante> estudiantesJugables; // los 8 reales completos

    public GeneradorEstudiante()
    {
        generarCuadernilloYJugables();
    }

    /**
     * Fila del Cuadernillo: Legajo, Carrera, Turno, Condición.
     * No lleva nombre ni apellido (el Cuadernillo nunca revela identidad).
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
            LEGAJO_PLAUSIBLE_MIN, LEGAJO_PLAUSIBLE_MAX, CANTIDAD_JUGABLES + CANTIDAD_RELLENO
        );

        // Los primeros 8 son jugables (verdaderos), el resto es relleno
        List<Integer> legajosJugables = legajosPlausibles.subList(0, CANTIDAD_JUGABLES);
        List<Integer> legajosRelleno = legajosPlausibles.subList(CANTIDAD_JUGABLES, legajosPlausibles.size());

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

    /**
     * Genera un estudiante nuevo al azar: puede ser uno de los 8 jugables
     * verdaderos tal cual, o una copia de uno de ellos con exactamente un
     * campo alterado (falso).
     */
    public DatosEstudiante generarSiguiente()
    {
        DatosEstudiante base = estudiantesJugables.get(Greenfoot.getRandomNumber(estudiantesJugables.size()));
        
        
        
        boolean esVerdadero = Greenfoot.getRandomNumber(2) == 0; // 50/50, ajustable
        if (esVerdadero) {
            // Se devuelve una copia idéntica (mismos datos, mismo legajo real)
            System.out.println("Es verdarero");
            return new DatosEstudiante(
                base.getNombre(), base.getApellido(), base.getDni(), base.getFechaNacimiento(),
                base.getPais(), base.getProvincia(), base.getCaducidad(), base.getLegajo(),
                base.getCarrera(), base.getTurno(), base.getRegularidad(), true, null
            );
        }

        String campoFalso = elegirAlAzar(CAMPOS_FALSEABLES);
        System.out.println("Es Falso");
        return generarConCampoFalso(base, campoFalso);
    }

    private DatosEstudiante generarConCampoFalso(DatosEstudiante base, String campoFalso)
    {
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
                nombre = otroDistinto(NOMBRES, nombre);
                break;
            case "APELLIDO":
                apellido = otroDistinto(APELLIDOS, apellido);
                break;
            case "DNI":
                dni = generarDni(); // otro número de 8 dígitos, sin regla especial
                break;
            case "LEGAJO":
                // Legajo inexistente: no figura en ninguna fila del cuadernillo
                legajo = LEGAJO_INEXISTENTE_MIN + Greenfoot.getRandomNumber(
                    LEGAJO_INEXISTENTE_MAX - LEGAJO_INEXISTENTE_MIN + 1
                );
                break;
            case "CADUCIDAD":
                caducidad = generarFechaCaducidad(); // distinta a la del DNI real
                break;
            case "CARRERA":
                carrera = otroDistinto(CARRERAS, carrera);
                break;
            case "TURNO":
                turno = otroDistinto(TURNOS, turno);
                break;
        }

        return new DatosEstudiante(
            nombre, apellido, dni, fechaNac, pais, provincia, caducidad,
            legajo, carrera, turno, regularidad, false, campoFalso
        );
    }

    // ---------- Utilidades de generación ----------

    private String elegirAlAzar(String[] lista)
    {
        return lista[Greenfoot.getRandomNumber(lista.length)];
    }

    private String otroDistinto(String[] lista, String actual)
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
        int anio = 2026 + Greenfoot.getRandomNumber(5); // 2026-2030
        return formatearFecha(dia, mes, anio);
    }

    /**
     * Genera una fecha de nacimiento que respeta la regla fija:
     * no debe superar el 21/07/2010.
     */
    private String generarFechaNacimientoValida()
    {
        int dia = 1 + Greenfoot.getRandomNumber(28);
        int mes = 1 + Greenfoot.getRandomNumber(12);
        int anio = 1995 + Greenfoot.getRandomNumber(16); // 1995-2010

        if (anio == 2010 && (mes > 7 || (mes == 7 && dia > 21))) {
            anio = 2009;
        }
        return formatearFecha(dia, mes, anio);
    }

    private String formatearFecha(int dia, int mes, int anio)
    {
        return String.format("%02d/%02d/%d", dia, mes, anio);
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

    // ---------- Acceso para los Documentos ----------

    public List<FilaCuadernillo> getCuadernillo()
    {
        return cuadernillo;
    }
}
