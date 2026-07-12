package avance.poo;

public abstract class Empleado implements Autenticable, Mostrable {
    protected int dni;
    protected String nombres;
    protected String apellidos;
    protected String password;
    protected String rol;
    protected boolean activo;
    protected int pedidosAtendidos;

    public Empleado(int dni, String nombres, String apellidos, String password, String rol) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.rol = rol;
        this.activo = true;
        this.pedidosAtendidos = 0;
    }

    public abstract String mostrarRol();

    @Override
    public boolean autenticar(String passwordIngresada) {
        return activo && password != null && password.equals(passwordIngresada);
    }

    @Override
    public String mostrarDatos() {
        return "DNI: "+dni + "\nNombre Completo: " + getNombreCompleto() + "\nRol: " + rol;
    }

    public void sumarPedidoAtendido() {
        pedidosAtendidos++;
    }

    public int getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getPedidosAtendidos() {
        return pedidosAtendidos;
    }

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
