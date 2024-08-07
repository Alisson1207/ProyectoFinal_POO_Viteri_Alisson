package Class;

import java.io.File;

public class Compra extends Productos{
    int id_pedido;
    int cantidad_producto;
    float precio_total;
    String fecha;

    /**
     * Se almacena los datos de la compra de los clientes".
     *
     * @param id_pedido Id pedido.
     * @param cantidad_producto Cantidad del producto a comprar.
     * @param precio_total Precio total de la compra.
     *
     *
     */

    public Compra(int id_pedido, int cantidad_producto, float precio_total, String fecha) {
        this.id_pedido = id_pedido;
        this.cantidad_producto = cantidad_producto;
        this.precio_total = precio_total;
        this.fecha = fecha;
    }

    public Compra(String codigo, String nombre_producto, int stock, float precio, String categoria, File imagen, int id_pedido, int cantidad_producto, float precio_total, String fecha) {
        super(codigo, nombre_producto, stock, precio, categoria, imagen);
        this.id_pedido = id_pedido;
        this.cantidad_producto = cantidad_producto;
        this.precio_total = precio_total;
        this.fecha = fecha;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public float getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(float precio_total) {
        this.precio_total = precio_total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}


