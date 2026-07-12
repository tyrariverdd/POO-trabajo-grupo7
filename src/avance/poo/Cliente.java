package avance.poo;

public class Cliente implements Mostrable {
    private String tipoDocumento;
    private String documento;
    private String nombres;
    private String apellidos;
    private String direccionEntrega;
    private String telefono;

    public Cliente(String tipoDocumento, String documento, String nombres, String apellidos, String direccionEntrega, String telefono) {
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccionEntrega = direccionEntrega;
        this.telefono = telefono;
    }

    public Cliente(int dni, String nombres, String apellidos, String direccionEntrega, String telefono) {
        this("DNI", String.valueOf(dni), nombres, apellidos, direccionEntrega, telefono);
    }

    @Override
    public String mostrarDatos() {
        return tipoDocumento + ": " + documento + "\nNombre Completo: " + getNombreCompleto() + "\nDirección: " + direccionEntrega + "\nTelefono: " + telefono;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public int getDni() {
        try {
            return Integer.parseInt(documento);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        if (apellidos == null || apellidos.trim().isEmpty()) {
            return nombres;
        }
        return nombres + " " + apellidos;
    }
}
