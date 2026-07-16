package avance.poo;

public class ReporteGerencial {

    public String generarReporteProductividad(Empleado[] empleados, int contadorEmpleados) {
        String reporte = "REPORTE DE PRODUCTIVIDAD\n";
        for (int i = 0; i < contadorEmpleados; i++) {
            Empleado empleado = empleados[i];
            reporte = reporte + "\nDNI: "+empleado.getDni() + "\nNombre Completo: " + empleado.getNombreCompleto()
                    + "\nRol: " + empleado.getRol()
                    + "\nAtendidos: " + empleado.getPedidosAtendidos();

            if (empleado instanceof Repartidor) {
                Repartidor repartidor = (Repartidor) empleado;
                reporte = reporte + "\nEntregas: " + repartidor.getEntregasRealizadas();
            }
            reporte = reporte + "\n---------"+"\n";
        }
        return reporte;
    }

    public String generarReporteIngresos(Pedido[] pedidos, int contadorPedidos) {
        double ingresosProductos = 0;
        double ingresosCombos = 0;
        double ingresosRecargos = 0;
        double ingresosTotales = 0;
        int pedidosContados = 0;

        for (int i = 0; i < contadorPedidos; i++) {
            Pedido pedido = pedidos[i];
            if (pedido.getEstado().equalsIgnoreCase("Entregado")) {
                ingresosProductos = ingresosProductos + pedido.calcularVentaProductos();
                ingresosCombos = ingresosCombos + pedido.calcularVentaCombos();
                ingresosRecargos = ingresosRecargos + pedido.getRecargosAdicionales();
                ingresosTotales = ingresosTotales + pedido.calcularTotal();
                pedidosContados++;
            }
        }

        String reporte = "REPORTE DE INGRESOS\n";
        reporte = reporte + "Pedidos entregados: " + pedidosContados + "\n";
        reporte = reporte + "Venta menu regular: S/" + ingresosProductos + "\n";
        reporte = reporte + "Venta combos: S/" + ingresosCombos + "\n";
        reporte = reporte + "Recargos delivery: S/" + ingresosRecargos + "\n";
        reporte = reporte + "Total con IGV: S/" + ingresosTotales;
        return reporte;
    }
}
