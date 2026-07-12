package avance.poo;

public abstract class Comprobante implements Mostrable, Cobrable {
    protected static int contadorComprobantes = 0;

    protected int numero;
    protected Pedido pedido;

    public Comprobante(Pedido pedido) {
        contadorComprobantes++;
        this.numero = contadorComprobantes;
        this.pedido = pedido;
    }

    public abstract String getTipoComprobante();

    @Override
    public double calcularSubtotal() {
        return pedido.calcularSubtotal();
    }

    @Override
    public double calcularIGV() {
        return pedido.calcularIGV();
    }

    @Override
    public double calcularTotal() {
        return pedido.calcularTotal();
    }

    @Override
    public String mostrarDatos() {
        String texto = getTipoComprobante() + " #" + numero + "\n";
        texto = texto + pedido.generarDetalleCuenta();
        return texto;
    }

    public int getNumero() {
        return numero;
    }


    public Pedido getPedido() {
        return pedido;
    }
}
