package avance.poo;

public class Factura extends Comprobante {
    private String ruc;
    private String razonSocial;

    public Factura(Pedido pedido, String ruc, String razonSocial) {
        super(pedido);
        this.ruc = ruc;
        this.razonSocial = razonSocial;
    }

    @Override
    public String getTipoComprobante() {
        return "Factura";
    }

    @Override
    public String mostrarDatos() {
        String texto = getTipoComprobante() + " #" + numero + "\n";
        texto = texto + "RUC: " + ruc + "\n";
        texto = texto + "Razon social: " + razonSocial + "\n";
        texto = texto + pedido.generarDetalleCuenta();
        return texto;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}
