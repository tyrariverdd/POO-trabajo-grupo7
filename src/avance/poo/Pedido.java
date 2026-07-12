package avance.poo;

public class Pedido implements Cobrable, ControlEstado, Mostrable {
    private static int contadorGlobal = 0;

    private int numeroCorrelativo;
    private String tipoEntrega;
    private String estado;
    private Cliente cliente;
    private Cajero cajero;
    private Repartidor repartidorAsignado;
    private SolicitudPedido[] solicitudes;
    private int contadorSolicitudes;
    private double recargosAdicionales;
    private String[] incidentes;
    private int contadorIncidentes;
    
    private String tipoComprobanteSolicitado;
    private String rucComprobante;
    private String razonSocialComprobante;
    
    public Pedido(Cliente cliente, Cajero cajero, String tipoEntrega) {
        contadorGlobal++;
        this.numeroCorrelativo = contadorGlobal;
        this.cliente = cliente;
        this.cajero = cajero;
        this.tipoEntrega = tipoEntrega;
        this.estado = "En Cocina";
        this.repartidorAsignado = null;
        this.solicitudes = new SolicitudPedido[50];
        this.contadorSolicitudes = 0;
        
        this.recargosAdicionales = 0.0;
        this.incidentes = new String[10];
        this.contadorIncidentes = 0;
        this.tipoComprobanteSolicitado = "";
        this.rucComprobante = "";
        this.razonSocialComprobante = "";
    }

    public void agregarItem(ItemMenu item, int cantidad) {
        if (item != null && cantidad > 0 && contadorSolicitudes < solicitudes.length) {
            if (item.hayStock(cantidad)) {
                solicitudes[contadorSolicitudes] = new SolicitudPedido(item, cantidad);
                contadorSolicitudes++;
                item.reducirStock(cantidad);
            }
        }
    }

    public void agregarIncidente(String incidente) {
        if (incidente != null && incidente.length() > 0 && contadorIncidentes < incidentes.length) {
            incidentes[contadorIncidentes] = incidente;
            contadorIncidentes++;
        }
    }

    public void agregarRecargo(double recargo) {
        if (recargo > 0) {
            recargosAdicionales = recargosAdicionales + recargo;
        }
    }
    public void solicitarComprobante(String tipoComprobante, String ruc, String razonSocial) {
    this.tipoComprobanteSolicitado = tipoComprobante;
    this.rucComprobante = ruc;
    this.razonSocialComprobante = razonSocial;
}

    public String getTipoComprobanteSolicitado() {
        return tipoComprobanteSolicitado;
    }

    public String getRucComprobante() {
        return rucComprobante;
    }

    public String getRazonSocialComprobante() {
        return razonSocialComprobante;
    }
    @Override
    public double calcularSubtotal() {
        double total = 0;
        for (int i = 0; i < contadorSolicitudes; i++) {
            total = total + solicitudes[i].getSubtotal();
        }
        return total;
    }

    @Override
    public double calcularIGV() {
        return calcularSubtotal() * 0.18;
    }

    @Override
    public double calcularTotal() {
        return calcularSubtotal() + calcularIGV() + recargosAdicionales;
    }

    public double calcularVentaProductos() {
        double total = 0;
        for (int i = 0; i < contadorSolicitudes; i++) {
            if (solicitudes[i].esProducto()) {
                total = total + solicitudes[i].getSubtotal();
            }
        }
        return total;
    }

    public double calcularVentaCombos() {
        double total = 0;
        for (int i = 0; i < contadorSolicitudes; i++) {
            if (solicitudes[i].esCombo()) {
                total = total + solicitudes[i].getSubtotal();
            }
        }
        return total;
    }

    @Override
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String mostrarDatos() {
        return "Pedido #" + numeroCorrelativo + " | " + cliente.getNombreCompleto()
                + " | " + tipoEntrega + " | " + estado + " | S/" + calcularTotal();
    }

    public String generarDetalleCuenta() {
        String texto = "Pedido #" + numeroCorrelativo + "\n";
        texto = texto + "Cliente: " + cliente.getNombreCompleto() + "\n";
        texto = texto + "Tipo de entrega: " + tipoEntrega + "\n";
        texto = texto + "Estado: " + estado + "\n";
        texto = texto + "Detalle:\n";
        for (int i = 0; i < contadorSolicitudes; i++) {
            texto = texto + solicitudes[i].mostrarDatos() + "\n";
        }
        texto = texto + "Subtotal: S/" + calcularSubtotal() + "\n";
        texto = texto + "IGV: S/" + calcularIGV() + "\n";
        texto = texto + "Recargos: S/" + recargosAdicionales + "\n";
        texto = texto + "Total: S/" + calcularTotal();
        return texto;
    }

    public int getNumeroCorrelativo() {
        return numeroCorrelativo;
    }


    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public Repartidor getRepartidorAsignado() {
        return repartidorAsignado;
    }

    public void setRepartidorAsignado(Repartidor repartidorAsignado) {
        this.repartidorAsignado = repartidorAsignado;
    }

    public SolicitudPedido[] getSolicitudes() {
        return solicitudes;
    }

    public int getContadorSolicitudes() {
        return contadorSolicitudes;
    }

    public double getRecargosAdicionales() {
        return recargosAdicionales;
    }

    public String[] getIncidentes() {
        return incidentes;
    }

    public int getContadorIncidentes() {
        return contadorIncidentes;
    }
}
