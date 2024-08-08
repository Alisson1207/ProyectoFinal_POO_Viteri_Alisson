package Class;

import java.util.Date;

public class Ventas  extends Personal{
    int numero_venta;
    Date fecha;

    public Ventas(int numero_venta, Date fecha) {
        this.numero_venta = numero_venta;
        this.fecha = new Date();
    }

    public Ventas(String nombre, String rol, String usuario, String contrasenia, Date fechaIngreso, String cedula, int numero_venta, Date fecha) {
        super(nombre, rol, usuario, contrasenia, fechaIngreso, cedula);
        this.numero_venta = numero_venta;
        this.fecha = new Date();
    }

    public int getNumero_venta() {
        return numero_venta;
    }

    public void setNumero_venta(int numero_venta) {
        this.numero_venta = numero_venta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
