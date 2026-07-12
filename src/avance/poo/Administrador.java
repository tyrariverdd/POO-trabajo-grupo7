package avance.poo;

public class Administrador extends Empleado {
    private GestionAdmin gestionAdmin;

    public Administrador(int dni, String nombres, String apellidos, String password, GestionAdmin gestionAdmin) {
        super(dni, nombres, apellidos, password, "Administrador");
        this.gestionAdmin = gestionAdmin;
    }

    @Override
    public String mostrarRol() {
        return "Administrador";
    }

    public void registrarEmpleado(int dni, String nombres, String apellidos, String password,
                                  String rol, GestionCajero gestionCajero) {
        gestionAdmin.registrarEmpleado(dni, nombres, apellidos, password, rol, gestionCajero);
    }

    public void modificarEmpleado(int dni, String nombres, String apellidos, String password, boolean activo) {
        gestionAdmin.modificarEmpleado(dni, nombres, apellidos, password, activo);
    }

    public void eliminarEmpleado(int dni) {
        gestionAdmin.eliminarEmpleado(dni);
    }

    public void registrarProducto(String codigo, String nombre, double precio, String categoria, int stock) {
        gestionAdmin.registrarProducto(codigo, nombre, precio, categoria, stock);
    }

    public void modificarProducto(String codigo, String nombre, double precio, String categoria, int stock) {
        gestionAdmin.modificarProducto(codigo, nombre, precio, categoria, stock);
    }

    public void eliminarProducto(String codigo) {
        gestionAdmin.eliminarProducto(codigo);
    }

    public void registrarCombo(String codigo, String nombre, double precioPromocional) {
        gestionAdmin.registrarCombo(codigo, nombre, precioPromocional);
    }

    public void agregarProductoACombo(String codigoCombo, String codigoProducto) {
        gestionAdmin.agregarProductoACombo(codigoCombo, codigoProducto);
    }

    public void modificarCombo(String codigo, String nombre, double precioPromocional) {
        gestionAdmin.modificarCombo(codigo, nombre, precioPromocional);
    }

    public void eliminarCombo(String codigo) {
        gestionAdmin.eliminarCombo(codigo);
    }

    public GestionAdmin getGestionAdmin() {
        return gestionAdmin;
    }
}
