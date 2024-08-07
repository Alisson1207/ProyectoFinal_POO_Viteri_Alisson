package Class;

import java.io.File;

public class Productos extends Personal{
    private String codigo;
    private String nombre_producto;
    private int stock;
    private float precio;
    private String categoria;
    File imagen;

    /**
     * Constructor por defecto de la clase Productos.
     */
    public Productos() {
    }

    /**
     * Constructor con parámetros para inicializar un objeto de la clase Productos.
     *
     * @param codigo          Código del producto.
     * @param nombre_producto Nombre del producto.
     * @param stock           Cantidad en stock del producto.
     * @param precio          Precio del producto.
     * @param categoria       Categoría del producto.
     * @param imagen          Imagen del producto
     */
    public Productos(String codigo, String nombre_producto, int stock, float precio, String categoria, File imagen) {
        this.codigo = codigo;
        this.nombre_producto = nombre_producto;
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    /**
     * Obtiene el código del producto.
     *
     * @return El código del producto.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del producto.
     *
     * @param codigo El código del producto.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre_producto() {
        return nombre_producto;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre_producto El nombre del producto.
     */
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    /**
     * Obtiene la cantidad en stock del producto.
     *
     * @return La cantidad en stock del producto.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece la cantidad en stock del producto.
     *
     * @param stock La cantidad en stock del producto.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio El precio del producto.
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la categoría del producto.
     *
     * @return La categoría del producto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del producto.
     *
     * @param categoria La categoría del producto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    /**
     * Obtiene la imagen del producto.
     *
     * @return La imagen del producto.
     */
    public File getImagen() {
        return imagen;
    }
    /**
     * Establece la imagen del producto.
     *
     * @param imagen La categoría del producto.
     */
    public void setImagen(File imagen) {
        this.imagen = imagen;
    }
}
