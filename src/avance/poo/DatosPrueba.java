package avance.poo;

public class DatosPrueba {

    public static void cargar(GestionAdmin gestionAdmin, GestionCajero gestionCajero) {
        Producto p1 = new Producto("H1", "Gigante", 15, "Hamburguesas", 20);
        Producto p2 = new Producto("H2", "Premium", 18, "Hamburguesas", 15);
        Producto p3 = new Producto("B1", "Inka Kola 1L", 5, "Bebidas", 30);
        Producto p4 = new Producto("B2", "Frugo 1L", 7, "Bebidas", 20);
        Producto p5 = new Producto("A1", "Papitas medium", 5, "Acompaniamientos", 25);
        Producto p6 = new Producto("A2", "Helado", 7, "Acompaniamientos", 15);

        gestionAdmin.agregarProductoDirecto(p1);
        gestionAdmin.agregarProductoDirecto(p2);
        gestionAdmin.agregarProductoDirecto(p3);
        gestionAdmin.agregarProductoDirecto(p4);
        gestionAdmin.agregarProductoDirecto(p5);
        gestionAdmin.agregarProductoDirecto(p6);

        Combo combo1 = new Combo("C1", "Combo Familiar", 25.0);
        combo1.agregarProducto(p1);
        combo1.agregarProducto(p3);
        combo1.agregarProducto(p5);
        gestionAdmin.agregarComboDirecto(combo1);

        Combo combo2 = new Combo("C2", "Combo Súper Hiper mega Especial", 20.0);
        combo2.agregarProducto(p2);
        combo2.agregarProducto(p4);
        gestionAdmin.agregarComboDirecto(combo2);

        Administrador admin = new Administrador(11111111, "Tyra", "Rivera", "admin123", gestionAdmin);
        Cajero cajero = new Cajero(22222222, "Daniela", "Nuñez", "cajero123", gestionCajero);
        Repartidor repartidor = new Repartidor(33333333, "Santiago", "Pegorari", "repartidor123");
        repartidor.setUnidadTransporte("Moto 1");
        repartidor.setEstadoUnidad(true);
        repartidor.setDisponible(true);

        gestionAdmin.agregarEmpleadoDirecto(admin);
        gestionAdmin.agregarEmpleadoDirecto(cajero);
        gestionAdmin.agregarEmpleadoDirecto(repartidor);
    }
}
