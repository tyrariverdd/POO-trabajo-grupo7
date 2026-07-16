package avance.poo;

public abstract class ItemMenu implements Mostrable {
    protected String codigo;
    protected String nombre;
    protected double precio;

    public ItemMenu(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public abstract String getTipoItem();

    public boolean hayStock(int cantidad) {
        return true;
    }

    public void reducirStock(int cantidad) {
        // Se redefine en Producto y Combo cuando corresponde.
    }

    @Override
    public String mostrarDatos() {
        return codigo + "\nNombre: " + nombre + "\nPrecio: S/" + precio + "\nTipo item: " + getTipoItem();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
