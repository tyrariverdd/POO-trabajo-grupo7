package avance.poo;

public class Boleta extends Comprobante {
    public Boleta(Pedido pedido) {
        super(pedido);
    }

    @Override
    public String getTipoComprobante() {
        return "Boleta";
    }
}
