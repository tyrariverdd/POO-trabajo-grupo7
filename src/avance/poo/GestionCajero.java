package avance.poo;

public class GestionCajero {
    private Cliente[] listaClientes;
    private Pedido[] listaPedidos;
    private Comprobante[] listaComprobantes;
    private int contadorClientes;
    private int contadorPedidos;
    private int contadorComprobantes;
    private GestionAdmin gestionAdmin;

    public GestionCajero(GestionAdmin gestionAdmin) {
        this.listaClientes = new Cliente[100];
        this.listaPedidos = new Pedido[200];
        this.listaComprobantes = new Comprobante[200];
        this.contadorClientes = 0;
        this.contadorPedidos = 0;
        this.contadorComprobantes = 0;
        this.gestionAdmin = gestionAdmin;
    }

    public GestionCajero(Producto[] listaProductos, int contadorProductos, Combo[] listaCombos, int contadorCombos) {
        this(new GestionAdmin());
        for (int i = 0; i < contadorProductos; i++) {
            gestionAdmin.registrarProducto(listaProductos[i]);
        }
        for (int i = 0; i < contadorCombos; i++) {
            gestionAdmin.registrarCombo(listaCombos[i]);
        }
    }

    // ======================== CLIENTES (RF03) ========================
    public void registrarCliente(String tipoDocumento, String documento, String nombres, String apellidos,
                                 String direccion, String telefono) {
        if (contadorClientes < listaClientes.length) {
            if (buscarClientePorDocumento(documento) == null) {
                listaClientes[contadorClientes] = new Cliente(tipoDocumento, documento, nombres, apellidos, direccion, telefono);
                contadorClientes++;
            }
        }
    }

    public void registrarCliente(int dni, String nombres, String apellidos, String direccion, String telefono) {
        registrarCliente("DNI", String.valueOf(dni), nombres, apellidos, direccion, telefono);
    }

    public void modificarCliente(String documento, String nombres, String apellidos, String direccion, String telefono) {
        Cliente cliente = buscarClientePorDocumento(documento);
        if (cliente != null) {
            cliente.setNombres(nombres);
            cliente.setApellidos(apellidos);
            cliente.setDireccionEntrega(direccion);
            cliente.setTelefono(telefono);
        }
    }

    public void eliminarCliente(String documento) {
        int indice = -1;
        for (int i = 0; i < contadorClientes; i++) {
            if (listaClientes[i].getDocumento().equals(documento)) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            for (int i = indice; i < contadorClientes - 1; i++) {
                listaClientes[i] = listaClientes[i + 1];
            }
            listaClientes[contadorClientes - 1] = null;
            contadorClientes--;
        }
    }

    public Cliente buscarClientePorDocumento(String documento) {
        for (int i = 0; i < contadorClientes; i++) {
            if (listaClientes[i].getDocumento().equals(documento)) {
                return listaClientes[i];
            }
        }
        return null;
    }

    public Cliente buscarClientePorDni(int dni) {
        return buscarClientePorDocumento(String.valueOf(dni));
    }

    // ======================== PEDIDOS (RF05 Y RF06) ========================
    public Pedido crearPedido(Cajero cajero, String documentoCliente, String tipoEntrega) {
        Cliente cliente = buscarClientePorDocumento(documentoCliente);
        if (cliente != null && cajero != null && contadorPedidos < listaPedidos.length) {
            Pedido pedido = new Pedido(cliente, cajero, tipoEntrega);
            listaPedidos[contadorPedidos] = pedido;
            contadorPedidos++;
            cajero.sumarPedidoAtendido();
            return pedido;
        }
        return null;
    }

    public void agregarItemAPedido(int numeroPedido, String codigoItem, int cantidad) {
        Pedido pedido = buscarPedidoPorNumero(numeroPedido);
        ItemMenu item = gestionAdmin.buscarItemMenu(codigoItem);

        if (pedido != null && item != null) {
            if (pedido.getEstado().equalsIgnoreCase("En Cocina")) {
                pedido.agregarItem(item, cantidad);
            }
        }
    }

    public void asignarRepartidor(int numeroPedido, int dniRepartidor) {
        Pedido pedido = buscarPedidoPorNumero(numeroPedido);
        Empleado empleado = gestionAdmin.buscarEmpleadoPorDni(dniRepartidor);

        if (pedido != null && empleado instanceof Repartidor) {
            if (pedido.getTipoEntrega().equalsIgnoreCase("Delivery") && pedido.getEstado().equalsIgnoreCase("En Cocina")) {
                Repartidor repartidor = (Repartidor) empleado;
                repartidor.asignarPedido(pedido);
            }
        }
    }

    public void cerrarPedidoRecojo(int numeroPedido) {
        Pedido pedido = buscarPedidoPorNumero(numeroPedido);
        if (pedido != null) {
            if (pedido.getTipoEntrega().equalsIgnoreCase("Recojo en Tienda") && pedido.getEstado().equalsIgnoreCase("En Cocina")) {
                pedido.cambiarEstado("Entregado");
            }
        }
    }

    public Pedido buscarPedidoPorNumero(int numero) {
        for (int i = 0; i < contadorPedidos; i++) {
            if (listaPedidos[i].getNumeroCorrelativo() == numero) {
                return listaPedidos[i];
            }
        }
        return null;
    }

    // ======================== FACTURACION (RF08) ========================
    public Comprobante emitirComprobante(int numeroPedido, String tipoComprobante,
                                         String ruc, String razonSocial) {
        Pedido pedido = buscarPedidoPorNumero(numeroPedido);

        if (pedido != null && contadorComprobantes < listaComprobantes.length) {
            if (pedido.getEstado().equalsIgnoreCase("Entregado")) {
                Comprobante comprobante = null;

                if (tipoComprobante.equalsIgnoreCase("Factura")) {
                    comprobante = new Factura(pedido, ruc, razonSocial);
                } else if (tipoComprobante.equalsIgnoreCase("Boleta")) {
                    comprobante = new Boleta(pedido);
                }

                if (comprobante != null) {
                    listaComprobantes[contadorComprobantes] = comprobante;
                    contadorComprobantes++;
                    return comprobante;
                }
            }
        }

        return null;
    }

    public Cliente[] getListaClientes() {
        return listaClientes;
    }

    public int getContadorClientes() {
        return contadorClientes;
    }

    public Pedido[] getListaPedidos() {
        return listaPedidos;
    }

    public int getContadorPedidos() {
        return contadorPedidos;
    }

    public Comprobante[] getListaComprobantes() {
        return listaComprobantes;
    }

    public int getContadorComprobantes() {
        return contadorComprobantes;
    }

    public GestionAdmin getGestionAdmin() {
        return gestionAdmin;
    }
}
