package Vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Class.Compra;
import Class.Productos;

public class Form_cajero {
    public JPanel panel_cajero;
    private JTabbedPane menu_cajero;
    private JPanel panel_catalogo;
    private JButton bt_bebidas;
    private JButton bt_vegetales;
    private JButton bt_snacks;
    private JButton bt_panaderia;
    private JButton bt_carnes;
    private JButton bt_lacteos;
    private JButton bt_regresar_cat;
    private JPanel panel_bebidas;
    private JLabel lb_titulo_bebidas;
    private JButton bt_ver_carrito;
    private JPanel panel_carrito;
    private JTable tabla_carrito;
    private DefaultTableModel modeloTablaCarrito;
    private List<Compra> carrito;
    private JLabel icono;
    private JLabel lb_catalogo;
    private JButton bt_generar_factura;
    private JButton bt_guardar_cambios;
    private JButton bt_eliminar_producto;
    private JButton bt_editar_producto; // Añadido botón de editar
    private JButton bt_regresar_carrito;
    private JButton bt_salir;
    private JButton bt_cerrar_sesion;
    private JPanel panel_cerrar_sesion;

    public Form_cajero() {
        carrito = new ArrayList<>();
        panel_cajero = new JPanel(new BorderLayout());
        menu_cajero = new JTabbedPane();

        // Configuración del panel de catálogo y botones de categorías
        panel_catalogo = new JPanel(new BorderLayout());
        panel_catalogo.setLayout(new GridLayout(3, 2, 10, 10));

        bt_bebidas = new JButton("Bebidas");
        bt_vegetales = new JButton("Vegetales");
        bt_snacks = new JButton("Snacks");
        bt_panaderia = new JButton("Panadería");
        bt_carnes = new JButton("Carnes");
        bt_lacteos = new JButton("Lácteos");
        bt_regresar_cat = new JButton("Regresar");

        panel_catalogo.add(bt_bebidas);
        panel_catalogo.add(bt_vegetales);
        panel_catalogo.add(bt_snacks);
        panel_catalogo.add(bt_panaderia);
        panel_catalogo.add(bt_carnes);
        panel_catalogo.add(bt_lacteos);
        panel_catalogo.add(bt_regresar_cat);

        // Panel de bebidas
        panel_bebidas = new JPanel(new BorderLayout());
        lb_titulo_bebidas = new JLabel("Catálogo de Bebidas", SwingConstants.CENTER);
        panel_bebidas.add(lb_titulo_bebidas, BorderLayout.NORTH);

        // Panel para el carrito de compras
        panel_carrito = new JPanel(new BorderLayout());
        modeloTablaCarrito = new DefaultTableModel(new Object[]{"Nombre del Producto", "Cantidad", "Precio Total"}, 0);
        tabla_carrito = new JTable(modeloTablaCarrito);
        panel_carrito.add(new JScrollPane(tabla_carrito), BorderLayout.CENTER);

        bt_ver_carrito = new JButton("Ver Carrito");
        bt_generar_factura = new JButton("Generar Factura");
        bt_guardar_cambios = new JButton("Guardar Cambios");
        bt_eliminar_producto = new JButton("Eliminar Producto");
        bt_editar_producto = new JButton("Editar Producto"); // Añadido botón de editar
        bt_regresar_carrito = new JButton("Regresar");

        JPanel botoneraCarrito = new JPanel();
        botoneraCarrito.add(bt_editar_producto); // Añadido botón de editar
        botoneraCarrito.add(bt_guardar_cambios);
        botoneraCarrito.add(bt_eliminar_producto);
        botoneraCarrito.add(bt_generar_factura);
        botoneraCarrito.add(bt_regresar_carrito);

        panel_carrito.add(botoneraCarrito, BorderLayout.SOUTH);

        // Panel de cerrar sesión
        panel_cerrar_sesion = new JPanel(new BorderLayout());
        bt_salir = new JButton("Salir");
        bt_cerrar_sesion = new JButton("Cerrar Sesión");

        JPanel botoneraCerrarSesion = new JPanel();
        botoneraCerrarSesion.add(bt_cerrar_sesion);
        botoneraCerrarSesion.add(bt_salir);

        panel_cerrar_sesion.add(new JLabel("Cerrar Sesión", SwingConstants.CENTER), BorderLayout.NORTH);
        panel_cerrar_sesion.add(botoneraCerrarSesion, BorderLayout.CENTER);

        // Añadir los paneles al TabbedPane
        menu_cajero.addTab("Catálogo", panel_catalogo);
        menu_cajero.addTab("Productos", panel_bebidas);
        menu_cajero.addTab("Carrito", panel_carrito);
        menu_cajero.addTab("Cerrar Sesión", panel_cerrar_sesion);

        panel_cajero.add(menu_cajero, BorderLayout.CENTER);

        // Configuración de los eventos de los botones
        bt_bebidas.addActionListener(e -> {
            configurarPanelCatalogo("Bebidas");
            menu_cajero.setSelectedIndex(1);
        });

        bt_vegetales.addActionListener(e -> {
            configurarPanelCatalogo("Vegetales");
            menu_cajero.setSelectedIndex(1);
        });

        bt_snacks.addActionListener(e -> {
            configurarPanelCatalogo("Snack");
            menu_cajero.setSelectedIndex(1);
        });

        bt_panaderia.addActionListener(e -> {
            configurarPanelCatalogo("Panadería");
            menu_cajero.setSelectedIndex(1);
        });

        bt_carnes.addActionListener(e -> {
            configurarPanelCatalogo("Carnes");
            menu_cajero.setSelectedIndex(1);
        });

        bt_lacteos.addActionListener(e -> {
            configurarPanelCatalogo("Lacteos");
            menu_cajero.setSelectedIndex(1);
        });

        bt_regresar_cat.addActionListener(e -> menu_cajero.setSelectedIndex(0));

        bt_ver_carrito.addActionListener(e -> {
            actualizarTablaCarrito();
            menu_cajero.setSelectedIndex(2);
        });

        bt_regresar_carrito.addActionListener(e -> menu_cajero.setSelectedIndex(0));

        bt_guardar_cambios.addActionListener(e -> guardarCambiosCarrito());

        bt_eliminar_producto.addActionListener(e -> eliminarProductoCarrito());

        bt_editar_producto.addActionListener(e -> editarProductoCarrito()); // Añadido evento para editar

        bt_generar_factura.addActionListener(e -> generarFactura());

        bt_salir.addActionListener(e -> System.exit(0));

        bt_cerrar_sesion.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel_cajero, "Sesión cerrada.");
            System.exit(0);
        });
    }

    private void configurarPanelCatalogo(String categoria) {
        panel_bebidas.removeAll();
        panel_bebidas.setLayout(new BorderLayout());
        lb_titulo_bebidas.setText("Catálogo de " + categoria);
        panel_bebidas.add(lb_titulo_bebidas, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        panel_bebidas.add(scrollPane, BorderLayout.CENTER);
        panel_bebidas.add(bt_ver_carrito, BorderLayout.SOUTH);

        // Obtener productos desde la base de datos según la categoría
        List<Productos> productos = obtenerProductosPorCategoria(categoria);

        for (Productos producto : productos) {
            JPanel productoPanel = new JPanel(new BorderLayout());

            // Redimensionar imagen
            ImageIcon imgIcon = new ImageIcon(producto.getImagen().getPath());
            Image img = imgIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imgIcon = new ImageIcon(img);

            JLabel imgLabel = new JLabel(imgIcon);
            JLabel nameLabel = new JLabel(producto.getNombre_producto());
            JLabel priceLabel = new JLabel("Precio: $" + producto.getPrecio());
            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, producto.getStock(), 1));
            JButton addButton = new JButton("Agregar");

            addButton.addActionListener(e -> {
                int cantidad = (int) quantitySpinner.getValue();
                Compra compra = new Compra(producto.getCodigo(), producto.getNombre_producto(), cantidad, producto.getPrecio() * cantidad, producto.getCategoria(), producto.getImagen(), 0, cantidad, producto.getPrecio() * cantidad, "");
                carrito.add(compra);
                JOptionPane.showMessageDialog(panel_cajero, "Producto agregado al carrito.");
            });

            JPanel infoPanel = new JPanel(new GridLayout(0, 1));
            infoPanel.add(nameLabel);
            infoPanel.add(priceLabel);
            infoPanel.add(quantitySpinner);
            infoPanel.add(addButton);

            productoPanel.add(imgLabel, BorderLayout.CENTER);
            productoPanel.add(infoPanel, BorderLayout.SOUTH);

            gridPanel.add(productoPanel);
        }

        panel_bebidas.revalidate();
        panel_bebidas.repaint();
    }

    private List<Productos> obtenerProductosPorCategoria(String categoria) {
        List<Productos> productos = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
            MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
            MongoCollection<Document> collection = db.getCollection("Productos");

            // Filtrar productos por categoría
            List<Document> documents = collection.find(new Document("categoria", categoria)).into(new ArrayList<>());
            for (Document doc : documents) {
                productos.add(new Productos(
                        doc.getString("codigo"),
                        doc.getString("nombre"),
                        doc.getInteger("stock"),
                        doc.getDouble("precio").floatValue(),
                        doc.getString("categoria"),
                        new File(doc.getString("imagen")) // Asume que la ruta de la imagen es un String
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    private void actualizarTablaCarrito() {
        modeloTablaCarrito.setRowCount(0); // Limpiar la tabla

        for (Compra compra : carrito) {
            modeloTablaCarrito.addRow(new Object[]{
                    compra.getNombre_producto(),
                    compra.getCantidad_producto(),
                    compra.getPrecio_total()
            });
        }
    }

    private void guardarCambiosCarrito() {
        // Lógica para guardar cambios en el carrito
        JOptionPane.showMessageDialog(panel_cajero, "Cambios guardados.");
    }

    private void eliminarProductoCarrito() {
        int selectedRow = tabla_carrito.getSelectedRow();
        if (selectedRow >= 0) {
            carrito.remove(selectedRow);
            actualizarTablaCarrito();
            JOptionPane.showMessageDialog(panel_cajero, "Producto eliminado del carrito.");
        } else {
            JOptionPane.showMessageDialog(panel_cajero, "Selecciona un producto para eliminar.");
        }
    }

    private void editarProductoCarrito() {
        int selectedRow = tabla_carrito.getSelectedRow();
        if (selectedRow >= 0) {
            String nombreProducto = (String) modeloTablaCarrito.getValueAt(selectedRow, 0);
            String nuevaCantidadStr = JOptionPane.showInputDialog("Ingrese nueva cantidad para " + nombreProducto);
            if (nuevaCantidadStr != null && !nuevaCantidadStr.isEmpty()) {
                try {
                    int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);
                    if (nuevaCantidad > 0) {
                        Compra compra = carrito.get(selectedRow);
                        compra.setCantidad_producto(nuevaCantidad);
                        compra.setPrecio_total(compra.getPrecio() * nuevaCantidad);
                        actualizarTablaCarrito();
                        JOptionPane.showMessageDialog(panel_cajero, "Cantidad actualizada.");
                    } else {
                        JOptionPane.showMessageDialog(panel_cajero, "Cantidad debe ser mayor a cero.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel_cajero, "Ingrese un valor numérico válido.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(panel_cajero, "Selecciona un producto para editar.");
        }
    }

    private void generarFactura() {
        // Lógica para generar factura
        JOptionPane.showMessageDialog(panel_cajero, "Factura generada.");
    }

    public JPanel getPanel_cajero() {
        return panel_cajero;
    }
}
