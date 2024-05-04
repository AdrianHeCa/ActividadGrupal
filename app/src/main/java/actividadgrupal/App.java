package actividadgrupal;

import java.util.*;

public class App{

    public static class Usuario {
        private String nombre;
        private String email;
    
        public Usuario(String nombre, String email) {
            this.nombre = nombre;
            this.email = email;
        }
    
        public String getNombre() {
            return this.nombre;
        }
    
        public String getEmail() {
            return this.email;
        }
    }

    public static class GestionUsuarios {
        private HashMap<String, Usuario> usuarios = new HashMap<>();

        public boolean registrarUsuario(String nombre, String email) {
            usuarios.put(nombre, new Usuario(nombre, email));
            return true;
        }

        public boolean eliminarUsuario(String nombre) {
            return usuarios.remove(nombre) != null;
        }

        public Usuario obtenerUsuario(String nombre) {
            return usuarios.get(nombre);
        }
    }

    public static class Habitacion {
        private int numero;
        private TipoHabitacion tipo;
        private double precio;
    
        public Habitacion(int numero, TipoHabitacion tipo, double precio) {
            this.numero = numero;
            this.tipo = tipo;
            this.precio = precio;
        }
    
        public int getNumero() {
            return this.numero;
        }
    
        public TipoHabitacion getTipo() {
            return this.tipo;
        }
    
        public double getPrecio() {
            return this.precio;
        }
    }

    public enum TipoHabitacion {
        DOBLE, CUADRUPLE
    }
    public static class GestionHabitaciones {
        private HashMap<Integer, Habitacion> habitaciones = new HashMap<>();

        public boolean registrarHabitacion(int numero, TipoHabitacion tipo, double precio) {
            habitaciones.put(numero, new Habitacion(numero, tipo, precio));
            return true;
        }

        public boolean eliminarHabitacion(int numero) {
            return habitaciones.remove(numero) != null;
        }

        public void listarHabitaciones() {
            for (Habitacion habitacion : habitaciones.values()) {
                System.out.println("--------------------");
                System.out.println("Número: " + habitacion.getNumero());
                System.out.println("Tipo: " + habitacion.getTipo());
                System.out.println("Precio: " + habitacion.getPrecio());
            }
        }
    }

    public static class Reserva {
        private Habitacion habitacion;
        private Usuario usuario;
    
        public Reserva(Habitacion habitacion, Usuario usuario) {
            this.habitacion = habitacion;
            this.usuario = usuario;
        }
    
        public Habitacion getHabitacion() {
            return this.habitacion;
        }
    
        public Usuario getUsuario() {
            return this.usuario;
        }
    
    }

    public static class GestionReservas {
        private List<Reserva> reservas = new ArrayList<>();

        public boolean hacerReserva(int numeroHabitacion, Usuario usuario) {
            Habitacion habitacion = new Habitacion(numeroHabitacion, null, 20);
            reservas.add(new Reserva(habitacion, usuario));
            return true;
        }

        public void visualizarReservas(String nombreUsuario) {
            System.out.println("Habitaciones reservadas por " + nombreUsuario);
            
            List<Reserva> reservasUsuario = new ArrayList<>();
            for (Reserva reserva : reservas) {
                if (reserva.getUsuario().getNombre().equals(nombreUsuario)) {
                    reservasUsuario.add(reserva);
                }
            }
            
            reservasUsuario.sort((Reserva r1, Reserva r2) -> r2.getHabitacion().getNumero() - r1.getHabitacion().getNumero());
        
            for (Reserva reserva : reservasUsuario) {
                System.out.println("Número de habitación: " + reserva.getHabitacion().getNumero());
            }
        }
        
        
    }
}

