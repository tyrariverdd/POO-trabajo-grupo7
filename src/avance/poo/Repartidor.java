package avance.poo;

public class Repartidor extends Empleado {
    private String unidadTransporte;
    private boolean estadoUnidad;
    private boolean disponible;
    private Pedido[] pedidosAsignados;
    private int contadorPedidosAsignados;
    private int entregasRealizadas;

    public Repartidor(int dni, String nombres, String apellidos, String password) {
        super(dni, nombres, apellidos, password, "Repartidor");
        this.unidadTransporte = "No asignada";
        this.estadoUnidad = true;
        this.disponible = true;
        this.pedidosAsignados = new Pedido[10];
        this.contadorPedidosAsignados = 0;
        this.entregasRealizadas = 0;
    }

    @Override
    public String mostrarRol() {
        return "Repartidor";
    }

    public void asignarPedido(Pedido pedido) {
        if (disponible && estadoUnidad && pedido != null && contadorPedidosAsignados < pedidosAsignados.length) {
            pedidosAsignados[contadorPedidosAsignados] = pedido;
            contadorPedidosAsignados++;
            disponible = false;
            pedido.setRepartidorAsignado(this);
            pedido.cambiarEstado("En Camino");
        }
    }

    public void registrarEntrega(int numeroPedido) {
        Pedido pedido = buscarPedidoPorNumero(numeroPedido);
        if (pedido != null && pedido.getEstado().equalsIgnoreCase("En Camino")) {
            pedido.cambiarEstado("Entregado");
            disponible = true;
            entregasRealizadas++;
            sumarPedidoAtendido();
            eliminarPedidoAsignado(numeroPedido);
        }
    }

    public void reportarIncidente(int numeroPedido, String incidente) {
        Pedido pedido = buscarPedidoPorNumero(numeroPedido);
        if (pedido != null) {
            pedido.agregarIncidente(incidente);
        }
    }

    public void reportarRecargo(int numeroPedido, double recargo) {
        Pedido pedido = buscarPedidoPorNumero(numeroPedido);
        if (pedido != null) {
            pedido.agregarRecargo(recargo);
        }
    }

    public Pedido buscarPedidoPorNumero(int numero) {
        for (int i = 0; i < contadorPedidosAsignados; i++) {
            if (pedidosAsignados[i].getNumeroCorrelativo() == numero) {
                return pedidosAsignados[i];
            }
        }
        return null;
    }

    private void eliminarPedidoAsignado(int numero) {
        int indice = -1;
        for (int i = 0; i < contadorPedidosAsignados; i++) {
            if (pedidosAsignados[i].getNumeroCorrelativo() == numero) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            for (int i = indice; i < contadorPedidosAsignados - 1; i++) {
                pedidosAsignados[i] = pedidosAsignados[i + 1];
            }
            pedidosAsignados[contadorPedidosAsignados - 1] = null;
            contadorPedidosAsignados--;
        }
    }

    public String getUnidadTransporte() {
        return unidadTransporte;
    }

    public void setUnidadTransporte(String unidadTransporte) {
        this.unidadTransporte = unidadTransporte;
    }

    public boolean isEstadoUnidad() {
        return estadoUnidad;
    }

    public void setEstadoUnidad(boolean estadoUnidad) {
        this.estadoUnidad = estadoUnidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Pedido[] getPedidosAsignados() {
        return pedidosAsignados;
    }

    public int getContadorPedidosAsignados() {
        return contadorPedidosAsignados;
    }

    public int getEntregasRealizadas() {
        return entregasRealizadas;
    }
}
