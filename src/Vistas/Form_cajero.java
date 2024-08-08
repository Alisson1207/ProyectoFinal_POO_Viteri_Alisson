package Vistas;

import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.MongoException;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Class.Compra;
import Class.Productos;
import Class.Personal;

/**
 * Esta clase representa el formulario del cajero en el sistema del minimarket.
 * Incluye la interfaz de usuario para gestionar productos, carrito de compras,
 * y operaciones de finalización de compra y generación de facturas.
 */


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
    private JButton bt_eliminar_producto;
    private JButton bt_editar_producto; // Añadido botón de editar
    private JButton bt_regresar_carrito;
    private JButton bt_salir;
    private JButton bt_cerrar_sesion;
    private JButton bt_finalizar_compra;
    private JPanel panel_cerrar_sesion;

    /**
     * Constructor de la clase Form_cajero.
     * Inicializa la interfaz de usuario y configura los componentes.
     */

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


        panel_catalogo.add(bt_bebidas);
        panel_catalogo.add(bt_vegetales);
        panel_catalogo.add(bt_snacks);
        panel_catalogo.add(bt_panaderia);
        panel_catalogo.add(bt_carnes);
        panel_catalogo.add(bt_lacteos);


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
        bt_eliminar_producto = new JButton("Eliminar Producto");
        bt_editar_producto = new JButton("Editar Producto"); // Añadido botón de editar
        bt_regresar_carrito = new JButton("Regresar");
        bt_finalizar_compra = new JButton("Finalizar Compra");

        JPanel botoneraCarrito = new JPanel();
        botoneraCarrito.add(bt_editar_producto); // Añadido botón de editar
        botoneraCarrito.add(bt_eliminar_producto);
        botoneraCarrito.add(bt_generar_factura);
        botoneraCarrito.add(bt_regresar_carrito);
        botoneraCarrito.add(bt_finalizar_compra);

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


        menu_cajero.addTab("Catálogo", panel_catalogo);
        menu_cajero.addTab("Productos", panel_bebidas);
        menu_cajero.addTab("Carrito", panel_carrito);
        menu_cajero.addTab("Cerrar Sesión", panel_cerrar_sesion);

        panel_cajero.add(menu_cajero, BorderLayout.CENTER);


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


        bt_ver_carrito.addActionListener(e -> {
            actualizarTablaCarrito();
            menu_cajero.setSelectedIndex(2);
        });

        bt_regresar_carrito.addActionListener(e -> menu_cajero.setSelectedIndex(0));
        bt_finalizar_compra.addActionListener(e -> finalizarCompra());

        bt_eliminar_producto.addActionListener(e -> eliminarProductoCarrito());

        bt_editar_producto.addActionListener(e -> editarProductoCarrito()); // Añadido evento para editar

        bt_generar_factura.addActionListener(e -> {
            // Suponiendo que tienes una forma de obtener el nombre del cajero
            String nombreCajero = "nombre"; // Reemplaza esto con el método para obtener el nombre del cajero actual

            try {
                // Llama al método generarFactura con el nombre del cajero
                generarFactura(nombreCajero);

                // Mensaje de éxito (opcional)
                JOptionPane.showMessageDialog(panel_cajero, "Factura generada exitosamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel_cajero, "Error al generar la factura: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (DocumentException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel_cajero, "Error al crear el documento PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        bt_salir.addActionListener(e -> System.exit(0));

        bt_cerrar_sesion.addActionListener(e -> {
            JOptionPane.showMessageDialog(panel_cajero, "Sesión cerrada.");
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(bt_cerrar_sesion);
            frame.dispose();
            Form_login formLogin = new Form_login();
            formLogin.setVisible(true);
        });
    }

        /**
         * Configura el panel de catálogo con productos de una categoría específica.
         *
         * @param categoria La categoría de productos a mostrar.
         */

    private void configurarPanelCatalogo(String categoria) {
        panel_bebidas.removeAll();
        panel_bebidas.setLayout(new BorderLayout());
        lb_titulo_bebidas.setText("Catálogo de " + categoria);
        panel_bebidas.add(lb_titulo_bebidas, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        panel_bebidas.add(scrollPane, BorderLayout.CENTER);
        panel_bebidas.add(bt_ver_carrito, BorderLayout.SOUTH);


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

    /**
     * Obtiene una lista de productos filtrados por una categoría específica.
     *
     * @param categoria La categoría para filtrar los productos.
     * @return Lista de productos que pertenecen a la categoría especificada.
     */

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
                        new File(doc.getString("imagen"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    /**
     * Actualiza la tabla del carrito de compras con los productos actuales.
     */

    private void actualizarTablaCarrito() {
        modeloTablaCarrito.setRowCount(0);

        for (Compra compra : carrito) {
            modeloTablaCarrito.addRow(new Object[]{
                    compra.getNombre_producto(),
                    compra.getCantidad_producto(),
                    compra.getPrecio_total()
            });
        }
    }

    /**
     * Elimina el producto seleccionado del carrito de compras.
     */

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
    /**
     * Edita el producto seleccionado en el carrito de compras.
     */

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
    private String obtenerNombreUsuario(String usuario, String contrasena) {
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
            MongoDatabase database = mongoClient.getDatabase("MinimarketPro");
            MongoCollection<Document> collection = database.getCollection("Usuarios");
            Document query = new Document("usuario", usuario).append("contrasenia", contrasena);
            Document user = collection.find(query).first();
            if (user != null) {
                return user.getString("nombre");
            } else {
                return null; // Usuario no encontrado o contraseña incorrecta
            }
        } catch (MongoException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Finaliza la compra actual, genera una factura y guarda la compra en la base de datos.
     */
    private void finalizarCompra() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(panel_cajero, "El carrito está vacío. Agregue productos antes de finalizar.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(panel_cajero, "¿Está seguro de que desea finalizar la compra?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                MongoDatabase database = mongoClient.getDatabase("MinimarketPro");
                MongoCollection<Document> productosCollection = database.getCollection("Productos");
                MongoCollection<Document> ventasCollection = database.getCollection("Ventas");
                MongoCollection<Document> usuariosCollection = database.getCollection("Usuarios");

                // Lista de cédulas fijas para pruebas
                List<String> cedulasCajero = Arrays.asList("1726304256");

                // Obtener el nombre del cajero desde la base de datos
                String nombreCajero = null;
                for (String cedulaCajero : cedulasCajero) {
                    nombreCajero = obtenerNombreCajero(usuariosCollection, cedulaCajero);
                    if (nombreCajero != null) {
                        break; // Salir del bucle si encontramos el nombre del cajero
                    }
                }

                if (nombreCajero == null) {
                    JOptionPane.showMessageDialog(panel_cajero, "Nombre del cajero no disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar el stock de los productos
                boolean stockValido = true;
                for (Compra compra : carrito) {
                    Document filtro = new Document("codigo", compra.getCodigo());
                    Document producto = productosCollection.find(filtro).first();

                    if (producto != null) {
                        int stockActual = producto.getInteger("stock");
                        int cantidadSolicitada = compra.getCantidad_producto();

                        if (cantidadSolicitada > stockActual) {
                            stockValido = false;
                            JOptionPane.showMessageDialog(panel_cajero, "No hay suficiente stock para el producto: " + compra.getNombre_producto() + ". Stock disponible: " + stockActual, "Advertencia", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    } else {
                        stockValido = false;
                        JOptionPane.showMessageDialog(panel_cajero, "Producto con código: " + compra.getCodigo() + " no encontrado en la base de datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                }

                if (stockValido) {
                    // Actualizar el stock
                    for (Compra compra : carrito) {
                        Document filtro = new Document("codigo", compra.getCodigo());
                        Document producto = productosCollection.find(filtro).first();

                        if (producto != null) {
                            int stockActual = producto.getInteger("stock");
                            int cantidadSolicitada = compra.getCantidad_producto();

                            if (cantidadSolicitada <= stockActual) {
                                Document actualizacion = new Document("$inc", new Document("stock", -cantidadSolicitada));
                                UpdateResult resultado = productosCollection.updateOne(filtro, actualizacion);

                                if (resultado.getModifiedCount() == 0) {
                                    JOptionPane.showMessageDialog(panel_cajero, "No se pudo actualizar el stock para el producto con código: " + compra.getCodigo(), "Advertencia", JOptionPane.WARNING_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(panel_cajero, "No hay suficiente stock para el producto con código: " + compra.getCodigo(), "Advertencia", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }

                    // Generar la factura en PDF con el nombre del cajero
                    generarFactura(nombreCajero);

                    // Convertir el carrito a una lista de documentos
                    List<Document> productosList = new ArrayList<>();
                    for (Compra compra : carrito) {
                        Document productoDoc = new Document("codigo", compra.getCodigo())
                                .append("nombre_producto", compra.getNombre_producto())
                                .append("cantidad_producto", compra.getCantidad_producto())
                                .append("precio_total", compra.getPrecio_total());
                        productosList.add(productoDoc);
                    }

                    // Guardar la venta en la colección de ventas
                    Document ventaDoc = new Document("numero_factura", generarNumeroFactura())
                            .append("nombre", nombreCajero)
                            .append("fecha_venta", new Date())
                            .append("productos", productosList);

                    // Verificar y guardar en la colección
                    try {
                        ventasCollection.insertOne(ventaDoc);
                        System.out.println("Venta guardada correctamente: " + ventaDoc.toJson());
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(panel_cajero, "Error al guardar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // Limpiar el carrito y actualizar la tabla
                    carrito.clear();
                    modeloTablaCarrito.setRowCount(0);
                    JOptionPane.showMessageDialog(panel_cajero, "Compra finalizada, stock actualizado y factura generada.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(panel_cajero, "Error al finalizar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    // Método para obtener el nombre del cajero desde la colección usuarios
    private String obtenerNombreCajero(MongoCollection<Document> usuariosCollection, String cedula) {
        Document filtro = new Document("cedula", cedula);  // Usa el campo adecuado para encontrar al cajero
        Document usuario = usuariosCollection.find(filtro).first();
        if (usuario != null) {
            return usuario.getString("nombre");  // Ajusta el campo según el nombre en tu colección
        }
        return null;
    }


    private void generarFactura(String nombreCajero) throws IOException, DocumentException {
        // Crear un documento PDF con iText
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        String filePath = "./src/Facturas/factura_" + generarNumeroFactura() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Crear fuentes
        com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font normalFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.NORMAL);
        com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph("Factura de Compra", titleFont);
        title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        document.add(title);

        document.add(new com.itextpdf.text.Paragraph(" "));

        // Crear tabla para los detalles de la factura
        com.itextpdf.text.pdf.PdfPTable detailsTable = new com.itextpdf.text.pdf.PdfPTable(2);
        detailsTable.setWidthPercentage(100);
        detailsTable.setSpacingBefore(10f);
        detailsTable.setSpacingAfter(10f);


        detailsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph("Número de Factura: " + generarNumeroFactura(), normalFont)));
        detailsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph("Fecha de Venta: " + new Date(), normalFont)));
        detailsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph("Nombre del Cajero: " + nombreCajero, normalFont)));
        detailsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(" ", normalFont)));

        document.add(detailsTable);

        document.add(new com.itextpdf.text.Paragraph(" "));


        com.itextpdf.text.Paragraph productsHeader = new com.itextpdf.text.Paragraph("Productos:", headerFont);
        document.add(productsHeader);


        com.itextpdf.text.pdf.PdfPTable productsTable = new com.itextpdf.text.pdf.PdfPTable(3);
        productsTable.setWidthPercentage(100);
        productsTable.setSpacingBefore(10f);
        productsTable.setSpacingAfter(10f);


        productsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph("Nombre del Producto", headerFont)));
        productsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph("Cantidad", headerFont)));
        productsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph("Precio Total", headerFont)));

        // Agregar filas para cada producto
        for (Compra compra : carrito) {
            productsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(compra.getNombre_producto(), normalFont)));
            productsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.valueOf(compra.getCantidad_producto()), normalFont)));
            productsTable.addCell(new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph("$" + compra.getPrecio_total(), normalFont)));
        }

        document.add(productsTable);
        document.close();
    }
    private String generarNumeroFactura() {
        // Genera un número de factura único, por ejemplo usando un UUID o un contador
        return String.valueOf(System.currentTimeMillis());
    }

    public JPanel getPanel_cajero() {
        return panel_cajero;
    }
}