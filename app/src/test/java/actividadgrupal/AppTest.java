package actividadgrupal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void testRegistrarUsuario() {
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        assertEquals(true, gestionUsuarios.registrarUsuario("Paco", "paco@paco.com"));
    }

    @Test
    public void testEliminarUsuario() {
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        gestionUsuarios.registrarUsuario("Paco", "paco@paco.com");
        assertEquals(true, gestionUsuarios.eliminarUsuario("Paco"));
    }

    @Test
    public void testRegistrarHabitacion() {
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        assertEquals(true, gestionHabitaciones.registrarHabitacion(123, TipoHabitacion.DOBLE, 123.23));
    }

    @Test
    public void testEliminarHabitacion() {
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        gestionHabitaciones.registrarHabitacion(123, TipoHabitacion.DOBLE, 123.23);
        assertEquals(true, gestionHabitaciones.eliminarHabitacion(123));
    }

    @Test
    public void testListarHabitaciones() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        gestionHabitaciones.registrarHabitacion(123, TipoHabitacion.DOBLE, 123.23);
        gestionHabitaciones.registrarHabitacion(321, TipoHabitacion.CUADRUPLE, 321.12);
        gestionHabitaciones.listarHabitaciones();
        assertEquals("--------------------\r\n" + //
                    "Número: 321\r\n" + //
                    "Tipo: CUADRUPLE\r\n" + //
                    "Precio: 321.12\r\n" + //
                    "--------------------\r\n" + //
                    "Número: 123\r\n" + //
                    "Tipo: DOBLE\r\n" + //
                    "Precio: 123.23", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testHacerReserva() {
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        GestionReservas gestionReservas = new GestionReservas();

        gestionUsuarios.registrarUsuario("Paco", "paco@paco.com");
        gestionHabitaciones.registrarHabitacion(123, TipoHabitacion.DOBLE, 123.23);

        assertEquals(true, gestionReservas.hacerReserva(123, gestionUsuarios.obtenerUsuario("Paco")));
    }

    @Test
    public void testVisualizarReservas() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        GestionReservas gestionReservas = new GestionReservas();

        gestionUsuarios.registrarUsuario("Paco", "paco@paco.com");
        gestionHabitaciones.registrarHabitacion(123, TipoHabitacion.DOBLE, 123.23);
        gestionHabitaciones.registrarHabitacion(321, TipoHabitacion.CUADRUPLE, 321.12);

        gestionReservas.hacerReserva(123, gestionUsuarios.obtenerUsuario("Paco"));
        gestionReservas.hacerReserva(321, gestionUsuarios.obtenerUsuario("Paco"));

        gestionReservas.visualizarReservas("Paco");
        assertEquals("Habitaciones reservadas por Paco\r\n" + 
                        "Número de habitación: 321\r\n" + 
                        "Número de habitación: 123", outputStreamCaptor.toString().trim());
    }
}
