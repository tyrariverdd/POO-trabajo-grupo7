package avance.poo;

public class GestionAdmin {
    private Producto[] listaProductos;
    private Combo[] listaCombos;
    private Empleado[] listaEmpleados;
    private int contadorProductos;
    private int contadorCombos;
    private int contadorEmpleados;

    public GestionAdmin() {
        this.listaProductos = new Producto[100];
        this.listaCombos = new Combo[50];
        this.listaEmpleados = new Empleado[100];
        this.contadorProductos = 0;
        this.contadorCombos = 0;
        this.contadorEmpleados = 0;
    }

    //CODIGO DE EMPLEADOS
    public void registrarEmpleado(Empleado empleado) {
        if (empleado != null && contadorEmpleados < listaEmpleados.length) {
            if (buscarEmpleadoPorDni(empleado.getDni()) == null) {
                listaEmpleados[contadorEmpleados] = empleado;
                contadorEmpleados++;
            }
        }
    }

    public void registrarEmpleado(int dni, String nombres, String apellidos, String password,
                                  String rol, GestionCajero gestionCajero) {
        Empleado nuevo = null;

        if (rol.equalsIgnoreCase("Administrador")) {
            nuevo = new Administrador(dni, nombres, apellidos, password, this);
        } else if (rol.equalsIgnoreCase("Cajero")) {
            nuevo = new Cajero(dni, nombres, apellidos, password, gestionCajero);
        } else if (rol.equalsIgnoreCase("Repartidor")) {
            nuevo = new Repartidor(dni, nombres, apellidos, password);
        }

        registrarEmpleado(nuevo);
    }

    public void modificarEmpleado(int dni, String nombres, String apellidos, String password, boolean activo) {
        Empleado existente = buscarEmpleadoPorDni(dni);
        if (existente != null) {
            existente.setNombres(nombres);
            existente.setApellidos(apellidos);
            existente.setPassword(password);
            existente.setActivo(activo);
        }
    }

    public void eliminarEmpleado(int dni) {
        int indice = -1;
        for (int i = 0; i < contadorEmpleados; i++) {
            if (listaEmpleados[i].getDni() == dni) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            for (int i = indice; i < contadorEmpleados - 1; i++) {
                listaEmpleados[i] = listaEmpleados[i + 1];
            }
            listaEmpleados[contadorEmpleados - 1] = null;
            contadorEmpleados--;
        }
    }

    public Empleado buscarEmpleadoPorDni(int dni) {
        for (int i = 0; i < contadorEmpleados; i++) {
            if (listaEmpleados[i].getDni() == dni) {
                return listaEmpleados[i];
            }
        }
        return null;
    }

    public Empleado login(int dni, String password) {
        Empleado empleado = buscarEmpleadoPorDni(dni);
        if (empleado != null && empleado.autenticar(password)) {
            return empleado;
        }
        return null;
    }

    //CODIGO DE PRODUCTOS
    public void registrarProducto(String codigo, String nombre, double precio, String categoria, int stock) {
        if (contadorProductos < listaProductos.length) {
            if (buscarProductoPorCodigo(codigo) == null) {
                listaProductos[contadorProductos] = new Producto(codigo, nombre, precio, categoria, stock);
                contadorProductos++;
            }
        }
    }

    public void registrarProducto(Producto producto) {
        if (producto != null && contadorProductos < listaProductos.length) {
            if (buscarProductoPorCodigo(producto.getCodigo()) == null) {
                listaProductos[contadorProductos] = producto;
                contadorProductos++;
            }
        }
    }

    public void modificarProducto(String codigo, String nombre, double precio, String categoria, int stock) {
        Producto producto = buscarProductoPorCodigo(codigo);
        if (producto != null) {
            producto.setNombre(nombre);
            producto.setPrecioBase(precio);
            producto.setCategoria(categoria);
            producto.setStockDisponible(stock);
        }
    }

    public void eliminarProducto(String codigo) {
        int indice = -1;
        for (int i = 0; i < contadorProductos; i++) {
            if (listaProductos[i].getCodigo().equals(codigo)) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            for (int i = indice; i < contadorProductos - 1; i++) {
                listaProductos[i] = listaProductos[i + 1];
            }
            listaProductos[contadorProductos - 1] = null;
            contadorProductos--;
        }
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        for (int i = 0; i < contadorProductos; i++) {
            if (listaProductos[i].getCodigo().equals(codigo)) {
                return listaProductos[i];
            }
        }
        return null;
    }

    //CODIGO DE COMBOSS
    public void registrarCombo(String codigo, String nombre, double precioPromocional) {
        if (contadorCombos < listaCombos.length) {
            if (buscarComboPorCodigo(codigo) == null) {
                listaCombos[contadorCombos] = new Combo(codigo, nombre, precioPromocional);
                contadorCombos++;
            }
        }
    }

    public void registrarCombo(Combo combo) {
        if (combo != null && contadorCombos < listaCombos.length) {
            if (buscarComboPorCodigo(combo.getCodigo()) == null) {
                listaCombos[contadorCombos] = combo;
                contadorCombos++;
            }
        }
    }

    public void agregarProductoACombo(String codigoCombo, String codigoProducto) {
        Combo combo = buscarComboPorCodigo(codigoCombo);
        Producto producto = buscarProductoPorCodigo(codigoProducto);

        if (combo != null && producto != null) {
            combo.agregarProducto(producto);
        }
    }

    public void modificarCombo(String codigo, String nombre, double precioPromocional) {
        Combo combo = buscarComboPorCodigo(codigo);
        if (combo != null) {
            combo.setNombre(nombre);
            combo.setPrecioPromocional(precioPromocional);
        }
    }

    public void eliminarCombo(String codigo) {
        int indice = -1;
        for (int i = 0; i < contadorCombos; i++) {
            if (listaCombos[i].getCodigo().equals(codigo)) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            for (int i = indice; i < contadorCombos - 1; i++) {
                listaCombos[i] = listaCombos[i + 1];
            }
            listaCombos[contadorCombos - 1] = null;
            contadorCombos--;
        }
    }

    public Combo buscarComboPorCodigo(String codigo) {
        for (int i = 0; i < contadorCombos; i++) {
            if (listaCombos[i].getCodigo().equals(codigo)) {
                return listaCombos[i];
            }
        }
        return null;
    }

    public ItemMenu buscarItemMenu(String codigo) {
        Producto producto = buscarProductoPorCodigo(codigo);
        if (producto != null) {
            return producto;
        }
        return buscarComboPorCodigo(codigo);
    }

    public void agregarProductoDirecto(Producto producto) {
        registrarProducto(producto);
    }

    public void agregarComboDirecto(Combo combo) {
        registrarCombo(combo);
    }

    public void agregarEmpleadoDirecto(Empleado empleado) {
        registrarEmpleado(empleado);
    }

    public Producto[] getListaProductos() {
        return listaProductos;
    }

    public int getContadorProductos() {
        return contadorProductos;
    }

    public Combo[] getListaCombos() {
        return listaCombos;
    }

    public int getContadorCombos() {
        return contadorCombos;
    }

    public Empleado[] getListaEmpleados() {
        return listaEmpleados;
    }

    public int getContadorEmpleados() {
        return contadorEmpleados;
    }
}
