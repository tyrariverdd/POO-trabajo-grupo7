package avance.poo;

public class Cajero extends Empleado {
    private GestionCajero gestionCajero;

    public Cajero(int dni, String nombres, String apellidos, String password, GestionCajero gestionCajero) {
        super(dni, nombres, apellidos, password, "Cajero");
        this.gestionCajero = gestionCajero;
    }

    public Cajero(int dni, String nombres, String apellidos, String password,
                  Producto[] listaProductos, int contadorProductos,
                  Combo[] listaCombos, int contadorCombos) {
        super(dni, nombres, apellidos, password, "Cajero");
        this.gestionCajero = new GestionCajero(listaProductos, contadorProductos, listaCombos, contadorCombos);
    }

    @Override
    public String mostrarRol() {
        return "Cajero";
    }

    public void registrarCliente(String tipoDocumento, String documento, String nombres, String apellidos,
                                 String direccion, String telefono) {
        gestionCajero.registrarCliente(tipoDocumento, documento, nombres, apellidos, direccion, telefono);
    }

    public void modificarCliente(String documento, String nombres, String apellidos, String direccion, String telefono) {
        gestionCajero.modificarCliente(documento, nombres, apellidos, direccion, telefono);
    }

    public void eliminarCliente(String documento) {
        gestionCajero.eliminarCliente(documento);
    }

    public Pedido crearPedido(String documentoCliente, String tipoEntrega) {
        return gestionCajero.crearPedido(this, documentoCliente, tipoEntrega);
    }

    public void agregarItemAPedido(int numeroPedido, String codigoItem, int cantidad) {
        gestionCajero.agregarItemAPedido(numeroPedido, codigoItem, cantidad);
    }

    public void asignarRepartidor(int numeroPedido, int dniRepartidor) {
        gestionCajero.asignarRepartidor(numeroPedido, dniRepartidor);
    }

    public void cerrarPedidoRecojo(int numeroPedido) {
        gestionCajero.cerrarPedidoRecojo(numeroPedido);
    }

    public Comprobante emitirComprobante(int numeroPedido, String tipoComprobante,
                                         String ruc, String razonSocial) {
        return gestionCajero.emitirComprobante(numeroPedido, tipoComprobante, ruc, razonSocial);
    }

    public GestionCajero getGestionCajero() {
        return gestionCajero;
    }
}
