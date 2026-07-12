package avance.poo;

public class Producto extends ItemMenu {
    private String categoria;
    private int stockDisponible;

    public Producto(String codigo, String nombre, double precioBase, String categoria, int stockDisponible) {
        super(codigo, nombre, precioBase);
        this.categoria = categoria;
        this.stockDisponible = stockDisponible;
    }

    @Override
    public String getTipoItem() {
        return "Producto";
    }

    @Override
    public boolean hayStock(int cantidad) {
        return stockDisponible >= cantidad;
    }

    @Override
    public void reducirStock(int cantidad) {
        if (stockDisponible >= cantidad) {
            stockDisponible = stockDisponible - cantidad;
        }
    }

    @Override
    public String mostrarDatos() {
        return "\nCódigo:"+codigo + "\nNombre: " + nombre + "\nPrecio: S/" + precio + "\nCategoría: " + categoria + "\nStock: " + stockDisponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precio;
    }

    public void setPrecioBase(double precioBase) {
        this.precio = precioBase;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }
}
