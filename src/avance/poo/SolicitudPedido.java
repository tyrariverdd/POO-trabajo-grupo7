package avance.poo;

public class SolicitudPedido implements Mostrable {
    private ItemMenu item;
    private String codigo;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public SolicitudPedido(ItemMenu item, int cantidad) {
        this.item = item;
        this.codigo = item.getCodigo();
        this.cantidad = cantidad;
        this.precioUnitario = item.getPrecio();
        this.subtotal = precioUnitario * cantidad;
    }

    public SolicitudPedido(String codigo, int cantidad, double precioUnitario) {
        this.item = null;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = precioUnitario * cantidad;
    }

    @Override
    public String mostrarDatos() {
        return codigo + " x" + cantidad + " = S/" + subtotal;
    }

    public ItemMenu getItem() {
        return item;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public boolean esProducto() {
        return item instanceof Producto;
    }

    public boolean esCombo() {
        return item instanceof Combo;
    }
}
