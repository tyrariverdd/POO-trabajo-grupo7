package avance.poo;

public class Combo extends ItemMenu {
    private Producto[] productosEnCombo;
    private int contadorProductos;

    public Combo(String codigo, String nombre, double precioPromocional) {
        super(codigo, nombre, precioPromocional);
        this.productosEnCombo = new Producto[10];
        this.contadorProductos = 0;
    }

    public Combo(String nombre, double precioPromocional) {
        this(nombre, nombre, precioPromocional);
    }

    @Override
    public String getTipoItem() {
        return "Combo";
    }

    public void agregarProducto(Producto producto) {
        if (producto != null && contadorProductos < productosEnCombo.length) {
            productosEnCombo[contadorProductos] = producto;
            contadorProductos++;
        }
    }

    @Override
    public boolean hayStock(int cantidad) {
        if (contadorProductos == 0) {
            return false;
        }
        for (int i = 0; i < contadorProductos; i++) {
            if (!productosEnCombo[i].hayStock(cantidad)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void reducirStock(int cantidad) {
        if (hayStock(cantidad)) {
            for (int i = 0; i < contadorProductos; i++) {
                productosEnCombo[i].reducirStock(cantidad);
            }
        }
    }

    @Override
    public String mostrarDatos() {
        return "Codigo: "+codigo + "\nNombre: " + nombre + "\nPrecio: S/" + precio + "\nProductos: " + contadorProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioPromocional() {
        return precio;
    }

    public void setPrecioPromocional(double precioPromocional) {
        this.precio = precioPromocional;
    }

    public Producto[] getProductosEnCombo() {
        return productosEnCombo;
    }

    public int getContadorProductos() {
        return contadorProductos;
    }
}
