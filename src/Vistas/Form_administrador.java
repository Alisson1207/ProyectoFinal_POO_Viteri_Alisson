package Vistas;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import Class.Personal;
import Class.Productos;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

/**
 * Form_administrador formulario para la gestión de administradores.
 * Permite registrar nuevos usuarios en la base de datos y mostrar una lista de administradores y cajeros.
 * Permite ver el inventario de los productos y su stock.
 * Revisar las ventas de cada uno de los empleados y en general.
 */
public class Form_administrador extends Frame {
    public JPanel panel_principal;
    private JTabbedPane menu;
    private JPanel bt_personal;
    private JPanel bt_productos;
    private JPanel bt_ventas;
    private JPanel bt_cerrar;
    private JLabel icono;
    private JLabel lb_cedula;
    private JTextField tf_cedula_buscar;
    private JButton bt_buscar;
    private JPanel panel_registro;
    private JLabel lb_registro;
    private JLabel lb_cedula_registro;
    private JTextField tf_cedula_registro;
    private JLabel lb_nombreapellido;
    private JTextField tf_nombre;
    private JTextField tf_rol;
    private JLabel lb_rol;
    private JLabel lb_usuario;
    private JTextField tf_usuario;
    private JLabel lb_contrasenia_ingreso;
    private JPasswordField pf_contrasenia;
    private JTextField tf_fecha;
    private JLabel lb_fecha;
    private JButton bt_registrar;
    private JPanel panel_mostrar;
    private JTable table_mostrar_personal;
    private JLabel lb_mensaje;
    private JButton bt_eliminar;
    private JButton bt_editar;
    private JButton bt_guardar;
    private JTextField tf_codigo_buscar;
    private JButton buscarButton;
    private JTextField tf_nombre_registro;
    private JTextField tf_precio_producto;
    private JLabel lb_codigo_buscar;
    private JLabel lb_ingreso;
    private JLabel lb_codigo_registro;
    private JTextField tf_codigo_registro;
    private JLabel lb_nombre_registro;
    private JLabel lb_categoria;
    private JTextField tf_nombre_producto;
    private JTextField tf_stock;
    private JTextField tf_precio;
    private JTable table_productos;
    private JButton bt_registrar_producto;
    private JLabel lb_mensaje_productos;
    private JTextField tf_categoria;
    private JLabel lb_stock;
    private JLabel lb_precio_producto;
    private JPanel panel_productos;
    private JButton bt_editar_producto;
    private JButton bt_guardar_producto;
    private JButton bt_eliminar_producto;
    private JComboBox combo_categoria;
    private JButton bt_salir;
    private JButton bt_cerrar_sesion;
    private JPanel panel_cerrar;
    private JTextField tf_cedula_venta;
    private JButton bt_buscar_venta;
    private JLabel lb_cedula_venta_buscar;
    private JPanel panel_mostrar_venta;
    private JTable table_ventas;
    private JButton verVentasButton;
    private JLabel lb_imagenproducto;
    private JButton bt_subir_imagen;
    private JPanel panel_mostrar_imagen;
    private JLabel lb_imagen;
    private File imagenSeleccionada;

    /**
     * Constructor de la clase Form_administrador. Configura el formulario y los eventos.
     * Inicializa los componentes y establece las acciones para los botones.
     */
    public Form_administrador() {
        // Configuración del icono
        Icon icon = icono.getIcon();
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();
            Image resizedImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            icono.setIcon(new ImageIcon(resizedImage));
        }

        // Diseño de la tabla
        DefaultTableModel model = new DefaultTableModel(new String[]{"Cédula", "Nombre y Apellido", "Rol", "Usuario", "Fecha de Registro"}, 0);
        table_mostrar_personal.setModel(model);
        table_mostrar_personal.setFont(new Font("Arial", Font.PLAIN, 14));
        table_mostrar_personal.setRowHeight(30);
        table_mostrar_personal.setBackground(new Color(255, 255, 255)); // Color de fondo de la tabla
        table_mostrar_personal.setForeground(Color.BLACK); // Color del texto
        table_mostrar_personal.setSelectionBackground(new Color(70, 130, 180)); // Color de fondo cuando se selecciona una fila
        table_mostrar_personal.setSelectionForeground(Color.WHITE); // Color de texto cuando se selecciona una fila
        table_mostrar_personal.setGridColor(new Color(200, 200, 200)); // Color de las líneas de la cuadrícula
        table_mostrar_personal.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // Borde de la tabla

        JScrollPane scrollPane = new JScrollPane(table_mostrar_personal);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // Borde del JScrollPane
        panel_mostrar.setLayout(new BorderLayout());
        panel_mostrar.add(scrollPane, BorderLayout.CENTER);

        // Estilo de los botones
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        Color buttonColor = new Color(70, 130, 180);
        Color buttonTextColor = Color.WHITE;

        JButton[] buttons = {bt_registrar, bt_editar, bt_guardar, bt_eliminar};
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(buttonColor);
            button.setForeground(buttonTextColor);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(100, 35)); // Tamaño de los botones
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(buttonColor, 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
        }

        // Estilo de las cajas de texto
        JTextField[] textFields = {tf_cedula_registro, tf_nombre, tf_rol, tf_usuario, tf_fecha};
        for (JTextField textField : textFields) {
            textField.setPreferredSize(new Dimension(220, 30)); // Tamaño de las cajas de texto
        }
        pf_contrasenia.setPreferredSize(new Dimension(220, 30)); // Tamaño del campo de contraseña


        // Diseño del formulario de registro
        panel_registro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST;

        // Configurar diseño del formulario de registro
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        lb_registro.setFont(new Font("Arial", Font.BOLD, 16));
        lb_registro.setForeground(buttonColor);
        panel_registro.add(lb_registro, gbc);

        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel_registro.add(new JSeparator(), gbc); // Línea separadora

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel_registro.add(lb_cedula_registro, gbc);
        gbc.gridx = 1;
        panel_registro.add(tf_cedula_registro, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel_registro.add(lb_nombreapellido, gbc);
        gbc.gridx = 1;
        panel_registro.add(tf_nombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel_registro.add(lb_rol, gbc);
        gbc.gridx = 1;
        panel_registro.add(tf_rol, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel_registro.add(lb_usuario, gbc);
        gbc.gridx = 1;
        panel_registro.add(tf_usuario, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel_registro.add(lb_contrasenia_ingreso, gbc);
        gbc.gridx = 1;
        panel_registro.add(pf_contrasenia, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel_registro.add(lb_fecha, gbc);
        gbc.gridx = 1;
        panel_registro.add(tf_fecha, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel_registro.add(lb_mensaje, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        panel_registro.add(bt_registrar, gbc);
        gbc.gridx++;
        panel_registro.add(bt_editar, gbc);
        gbc.gridx++;
        panel_registro.add(bt_guardar, gbc);
        gbc.gridx++;
        panel_registro.add(bt_eliminar, gbc);

        // Estilo del panel de registro
        panel_registro.setBackground(new Color(240, 248, 255)); // Color de fondo del panel de registro
        panel_registro.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1)); // Borde del panel de registro

        // Estilo del JTabbedPane
        menu.setBackground(new Color(240, 248, 255)); // Color de fondo del JTabbedPane
        menu.setForeground(new Color(70, 130, 180)); // Color de texto del JTabbedPane

        /**
         * try catch para obtener los datos de la BD
         */
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
            MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
            MongoCollection<Document> collection = db.getCollection("Usuarios");
            List<Document> documents = collection.find().into(new Vector<>());
            for (Document doc : documents) {
                Vector<Object> row = new Vector<>();
                row.add(doc.getString("cedula"));
                row.add(doc.getString("nombre"));
                row.add(doc.getString("rol"));
                row.add(doc.getString("usuario"));
                Date fechaIngreso = doc.getDate("fechaIngreso");
                row.add(fechaIngreso != null ? new SimpleDateFormat("yyyy-MM-dd").format(fechaIngreso) : "");
                row.add(doc.getString("imagen"));
                model.addRow(row);
            }
        } catch (MongoException exception) {
            lb_mensaje.setText("Error al cargar datos");
        }

        bt_registrar.addActionListener(new ActionListener() {
            /**
             * Evento de clic en el botón de registro.
             * Valida los campos del formulario y registra un nuevo usuario en la base de datos.
             *
             * @param e El evento de acción que se produjo.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar campos vacíos
                if (tf_cedula_registro.getText().isEmpty() || tf_nombre.getText().isEmpty()
                        || tf_rol.getText().isEmpty() || tf_usuario.getText().isEmpty() || pf_contrasenia.getText().isEmpty() ||
                        tf_fecha.getText().isEmpty()) {
                    lb_mensaje.setText("Es obligatorio llenar todos los campos");
                    return;
                }

                // Validar cédula
                try {
                    Integer.parseInt(tf_cedula_registro.getText());
                } catch (NumberFormatException ex) {
                    lb_mensaje.setText("La cédula no es válida, se debe ingresar números");
                    return;
                }

                // Creación de objeto
                Personal personal = new Personal();
                personal.setCedula(tf_cedula_registro.getText());
                personal.setNombre(tf_nombre.getText());
                personal.setRol(tf_rol.getText());
                personal.setUsuario(tf_usuario.getText());
                personal.setContrasenia(pf_contrasenia.getText());


                // Formato de la fecha válido
                try {
                    personal.setFechaIngreso(new SimpleDateFormat("yyyy-MM-dd").parse(tf_fecha.getText()));
                    lb_mensaje.setText("Registro exitoso");
                } catch (ParseException ex) {
                    lb_mensaje.setText("Formato de fecha inválido, use yyyy-MM-dd");
                    return;
                }

                // Inserción de datos
                try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                    MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
                    MongoCollection<Document> collection = db.getCollection("Usuarios");
                    Document registro_personal = new Document("nombre", personal.getNombre())
                            .append("cedula", personal.getCedula())
                            .append("rol", personal.getRol())
                            .append("usuario", personal.getUsuario())
                            .append("contrasenia", personal.getContrasenia())
                            .append("fechaIngreso", personal.getFechaIngreso());
                    collection.insertOne(registro_personal);
                    lb_mensaje.setText("Registro exitoso");

                    tf_cedula_registro.setText("");
                    tf_nombre.setText("");
                    tf_rol.setText("");
                    tf_usuario.setText("");
                    pf_contrasenia.setText("");
                    tf_fecha.setText("");

                    // Actualizar la tabla
                    model.setRowCount(0);
                    List<Document> documents = collection.find().into(new Vector<>());
                    for (Document doc : documents) {
                        Vector<Object> row = new Vector<>();
                        row.add(doc.getString("cedula"));
                        row.add(doc.getString("nombre"));
                        row.add(doc.getString("rol"));
                        row.add(doc.getString("usuario"));
                        Date fechaIngreso = doc.getDate("fechaIngreso");
                        row.add(fechaIngreso != null ? new SimpleDateFormat("yyyy-MM-dd").format(fechaIngreso) : "");
                        model.addRow(row);
                    }
                } catch (MongoException exception) {
                    lb_mensaje.setText("Error en el registro");
                }
            }
        });

        bt_editar.addActionListener(new ActionListener() {
            /**
             * Evento de clic en el botón de editar.
             * Carga los datos del registro seleccionado en los campos de texto para que puedan ser modificados.
             *
             * @param e El evento de acción que se produjo.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lb_mensaje.setText("");

                // Verificar si se ha seleccionado una fila
                if (table_mostrar_personal.getSelectedRow() == -1) {
                    lb_mensaje.setText("No se ha seleccionado ningún personal para editar");
                } else {
                    // Obtener la fila seleccionada
                    int selectedRow = table_mostrar_personal.getSelectedRow();

                    // Cargar los datos de la fila seleccionada en los campos de texto
                    tf_cedula_registro.setText(table_mostrar_personal.getValueAt(selectedRow, 0).toString());
                    tf_nombre.setText(table_mostrar_personal.getValueAt(selectedRow, 1).toString());
                    tf_rol.setText(table_mostrar_personal.getValueAt(selectedRow, 2).toString());
                    tf_usuario.setText(table_mostrar_personal.getValueAt(selectedRow, 3).toString());
                    tf_fecha.setText(table_mostrar_personal.getValueAt(selectedRow, 4).toString());
                }
            }
        });

        bt_guardar.addActionListener(new ActionListener() {
            /**
             * Evento de clic en el botón de guardar cambios.
             * Actualiza el registro seleccionado en la base de datos con los nuevos datos ingresados en los campos de texto.
             *
             * @param e El evento de acción que se produjo.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lb_mensaje.setText("");
                if (table_mostrar_personal.getSelectedRow() == -1) {
                    lb_mensaje.setText("No se ha seleccionado ningún personal para guardar los cambios");
                    return;
                }

                if (tf_cedula_registro.getText().isEmpty() || tf_nombre.getText().isEmpty()
                        || tf_rol.getText().isEmpty() || tf_usuario.getText().isEmpty() || tf_fecha.getText().isEmpty()) {
                    lb_mensaje.setText("Es obligatorio llenar todos los campos");
                    return;
                }

                String cedula = tf_cedula_registro.getText();
                String nombre = tf_nombre.getText();
                String rol = tf_rol.getText();
                String usuario = tf_usuario.getText();
                String fecha = tf_fecha.getText();

                // Actualizar el registro en la base de datos
                try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                    MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
                    MongoCollection<Document> collection = db.getCollection("Usuarios");

                    // Obtener el valor de la celda de la fila seleccionada para identificar el registro
                    int selectedRow = table_mostrar_personal.getSelectedRow();
                    String cedulaOriginal = table_mostrar_personal.getValueAt(selectedRow, 0).toString();

                    Document filtro = new Document("cedula", cedulaOriginal);
                    Document actualizacion = new Document("$set", new Document("cedula", cedula)
                            .append("nombre", nombre)
                            .append("rol", rol)
                            .append("usuario", usuario)
                            .append("fechaIngreso", new SimpleDateFormat("yyyy-MM-dd").parse(fecha)));

                    collection.updateOne(filtro, actualizacion);
                    lb_mensaje.setText("Registro actualizado correctamente");

                    tf_cedula_registro.setText("");
                    tf_nombre.setText("");
                    tf_rol.setText("");
                    tf_usuario.setText("");
                    tf_fecha.setText("");

                    // Actualizar la tabla
                    DefaultTableModel model = (DefaultTableModel) table_mostrar_personal.getModel();
                    model.setRowCount(0);
                    List<Document> documents = collection.find().into(new Vector<>());
                    for (Document doc : documents) {
                        Vector<Object> row = new Vector<>();
                        row.add(doc.getString("cedula"));
                        row.add(doc.getString("nombre"));
                        row.add(doc.getString("rol"));
                        row.add(doc.getString("usuario"));
                        Date fechaIngreso = doc.getDate("fechaIngreso");
                        row.add(fechaIngreso != null ? new SimpleDateFormat("yyyy-MM-dd").format(fechaIngreso) : "");
                        model.addRow(row);
                    }
                } catch (Exception ex) {
                    lb_mensaje.setText("Error al actualizar el registro: " + ex.getMessage());
                }
            }
        });

        bt_eliminar.addActionListener(new ActionListener() {
            /**
             * Evento de clic en el botón de eliminar.
             * Elimina el registro seleccionado de la base de datos y actualiza la tabla.
             *
             * @param e El evento de acción que se produjo.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table_mostrar_personal.getSelectedRow() == -1) {
                    lb_mensaje.setText("No se ha seleccionado ningún personal");
                } else {
                    lb_mensaje.setText("");
                    try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                        MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
                        MongoCollection<Document> collection = db.getCollection("Usuarios");

                        // Obtener el valor de la celda antes de eliminar la fila
                        String cedula = table_mostrar_personal.getValueAt(table_mostrar_personal.getSelectedRow(), 0).toString();
                        Document filtro = new Document("cedula", cedula);
                        DeleteResult resultado = collection.deleteOne(filtro);

                        if (resultado.getDeletedCount() > 0) {
                            DefaultTableModel modelo = (DefaultTableModel) table_mostrar_personal.getModel();
                            modelo.removeRow(table_mostrar_personal.getSelectedRow());
                            lb_mensaje.setText("Se ha eliminado correctamente");
                        } else {
                            lb_mensaje.setText("No se encontró ningún registro con la cédula especificada");
                        }
                    } catch (Exception ex) {
                        lb_mensaje.setText("Error al eliminar el registro: " + ex.getMessage());
                    }
                }
            }
        });

        bt_buscar.addActionListener(new ActionListener() {
            /**
             * Evento de clic en el botón de búsqueda.
             * Busca un documento en la base de datos por la cédula proporcionada y muestra el resultado en la tabla.
             *
             * @param e El evento de acción que se produjo.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lb_mensaje.setText("");

                if (tf_cedula_buscar.getText().trim().isEmpty()) {
                    lb_mensaje.setText("Debe ingresar una cédula para buscar");
                    return;
                }

                try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                    MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
                    MongoCollection<Document> collection = db.getCollection("Usuarios");

                    // Buscar el documento con la cédula especificada
                    Document filtro = new Document("cedula", tf_cedula_buscar.getText().trim());
                    Document documentoEncontrado = collection.find(filtro).first();

                    // Limpiar la tabla
                    DefaultTableModel model = (DefaultTableModel) table_mostrar_personal.getModel();
                    model.setRowCount(0);

                    if (documentoEncontrado != null) {
                        // Añadir el documento encontrado a la tabla
                        Vector<Object> row = new Vector<>();
                        row.add(documentoEncontrado.getString("cedula"));
                        row.add(documentoEncontrado.getString("nombre"));
                        row.add(documentoEncontrado.getString("rol"));
                        row.add(documentoEncontrado.getString("usuario"));
                        Date fechaIngreso = documentoEncontrado.getDate("fechaIngreso");
                        row.add(fechaIngreso != null ? new SimpleDateFormat("yyyy-MM-dd").format(fechaIngreso) : "");
                        model.addRow(row);
                    } else {
                        lb_mensaje.setText("No se encontró ningún registro con la cédula especificada");
                        tf_cedula_buscar.setText(" ");
                    }
                } catch (MongoException exception) {
                    lb_mensaje.setText("Error al buscar el registro: " + exception.getMessage());
                }
            }
        });

        //PRODUCTOS

        String[] columnNames = {"Código", "Nombre", "Categoría", "Stock", "Precio", "Imagen"};
        DefaultTableModel model2 = new DefaultTableModel(columnNames, 0);
        table_productos.setModel(model2);

        table_productos.setFont(new Font("Arial", Font.PLAIN, 14));
        table_productos.setRowHeight(30);
        table_productos.setBackground(new Color(255, 255, 255)); // Color de fondo de la tabla
        table_productos.setForeground(Color.BLACK); // Color del texto
        table_productos.setSelectionBackground(new Color(70, 130, 180)); // Color de fondo cuando se selecciona una fila
        table_productos.setSelectionForeground(Color.WHITE); // Color de texto cuando se selecciona una fila
        table_productos.setGridColor(new Color(200, 200, 200)); // Color de las líneas de la cuadrícula
        table_productos.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // Borde de la tabla

// Añadir la tabla a un JScrollPane
        JScrollPane scrollPaneProductos = new JScrollPane(table_productos);
        scrollPaneProductos.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // Borde del JScrollPane
        panel_productos.setLayout(new BorderLayout());
        panel_productos.add(scrollPaneProductos, BorderLayout.CENTER);

        bt_subir_imagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        String extension = getFileExtension(file);
                        return file.isDirectory() || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png");
                    }

                    @Override
                    public String getDescription() {
                        return "Image files (*.jpg, *.jpeg, *.png)";
                    }
                });

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    imagenSeleccionada = fileChooser.getSelectedFile();
                    String rutaImagen = imagenSeleccionada.getAbsolutePath();
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(rutaImagen).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                    lb_imagen.setIcon(imageIcon);
                }
            }

            /**
             * Obtiene la extensión del archivo.
             *
             * @param file El archivo del cual se obtiene la extensión.
             * @return La extensión del archivo en minúsculas.
             */
            private String getFileExtension(File file) {
                String fileName = file.getName();
                int dotIndex = fileName.lastIndexOf('.');
                if (dotIndex > 0) {
                    return fileName.substring(dotIndex + 1).toLowerCase();
                }
                return "";
            }
        });


        /**
         * Configura el botón para registrar un producto.
         */
        bt_registrar_producto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tf_codigo_registro.getText().isEmpty() || tf_nombre_registro.getText().isEmpty() || tf_categoria.getText().isEmpty()
                        || tf_stock.getText().isEmpty() || tf_precio_producto.getText().isEmpty() || imagenSeleccionada == null) {
                    lb_mensaje_productos.setText("Es obligatorio llenar todos los campos y subir una imagen");
                    return;
                }

                Document registro_producto = new Document("codigo", tf_codigo_registro.getText())
                        .append("nombre", tf_nombre_registro.getText())
                        .append("categoria", tf_categoria.getText())
                        .append("stock", Integer.parseInt(tf_stock.getText()))
                        .append("precio", Float.parseFloat(tf_precio_producto.getText()))
                        .append("imagen", imagenSeleccionada.getAbsolutePath());

                try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                    MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
                    MongoCollection<Document> collection = db.getCollection("Productos");

                    collection.insertOne(registro_producto);
                    lb_mensaje_productos.setText("Producto registrado exitosamente");

                    // Limpiar campos y la imagen seleccionada
                    tf_codigo_registro.setText("");
                    tf_nombre_registro.setText("");
                    tf_categoria.setText("");
                    tf_stock.setText("");
                    tf_precio_producto.setText("");
                    imagenSeleccionada = null;
                    lb_imagen.setIcon(null);

                    // Actualizar la tabla
                    DefaultTableModel productosModel = (DefaultTableModel) table_productos.getModel();
                    productosModel.setRowCount(0);
                    List<Document> documents = collection.find().into(new Vector<>());
                    for (Document doc : documents) {
                        Vector<Object> row = new Vector<>();
                        row.add(doc.getString("codigo"));
                        row.add(doc.getString("nombre"));
                        row.add(doc.getString("categoria"));
                        row.add(doc.getInteger("stock"));
                        row.add(doc.getDouble("precio"));

                        // Convertir la ruta de la imagen en un ImageIcon
                        String rutaImagen = doc.getString("imagen");
                        ImageIcon imageIcon = new ImageIcon(new ImageIcon(rutaImagen).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                        row.add(imageIcon);

                        productosModel.addRow(row);
                    }
                } catch (Exception exception) {
                    lb_mensaje_productos.setText("Error al registrar producto: " + exception.getMessage());
                }
            }
        });
// Configurar el renderizador para la columna de imágenes
        table_productos.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    return new JLabel((ImageIcon) value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });


        // Configuración del renderizador de imágenes para la columna de la tabla
        table_productos.setDefaultRenderer(ImageIcon.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    return new JLabel((ImageIcon) value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

// Acción del botón editar producto
        bt_editar_producto.addActionListener(new ActionListener() {
            /**
             * Evento de clic en el botón de editar producto.
             * Carga los datos del producto seleccionado en los campos de texto para su edición.
             *
             * @param e El evento de acción que se produjo.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lb_mensaje_productos.setText("");

                // Verificar si se ha seleccionado una fila
                if (table_productos.getSelectedRow() == -1) {
                    lb_mensaje_productos.setText("No se ha seleccionado ningún producto para editar");
                } else {
                    // Obtener la fila seleccionada
                    int selectedRow = table_productos.getSelectedRow();

                    // Cargar los datos de la fila seleccionada en los campos de texto
                    tf_codigo_registro.setText(table_productos.getValueAt(selectedRow, 0).toString());
                    tf_nombre_registro.setText(table_productos.getValueAt(selectedRow, 1).toString());
                    tf_categoria.setText(table_productos.getValueAt(selectedRow, 2).toString());
                    tf_stock.setText(table_productos.getValueAt(selectedRow, 3).toString());
                    tf_precio_producto.setText(table_productos.getValueAt(selectedRow, 4).toString());
                    String imagenSeleccionada = table_productos.getValueAt(selectedRow, 5).toString();
                }
            }
        });

// Acción del botón guardar producto
        bt_guardar_producto.addActionListener(new ActionListener() {
            /**
             * Evento de clic en el botón de guardar cambios.
             * Actualiza el registro seleccionado en la base de datos con los nuevos datos ingresados en los campos de texto.
             *
             * @param e El evento de acción que se produjo.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lb_mensaje_productos.setText("");
                if (tf_codigo_registro.getText().isEmpty() || tf_nombre_registro.getText().isEmpty() || tf_categoria.getText().isEmpty()
                        || tf_stock.getText().isEmpty() || tf_precio_producto.getText().isEmpty()) {
                    lb_mensaje_productos.setText("Es obligatorio llenar todos los campos");
                    return;
                }

                // Actualizar el registro en la base de datos
                try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                    MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
                    MongoCollection<Document> collection = db.getCollection("Productos");
                    int selectedRow = table_productos.getSelectedRow();
                    String codigoOriginal = table_productos.getValueAt(selectedRow, 0).toString();

                    Document filtro = new Document("codigo", codigoOriginal);
                    Document actualizacion = new Document("$set", new Document("codigo", tf_codigo_registro.getText())
                            .append("nombre", tf_nombre_registro.getText())
                            .append("categoria", tf_categoria.getText())
                            .append("stock", Integer.parseInt(tf_stock.getText()))
                            .append("precio", Float.parseFloat(tf_precio_producto.getText())))
                            .append("imagen", imagenSeleccionada);


                    collection.updateOne(filtro, actualizacion);
                    lb_mensaje_productos.setText("Registro actualizado correctamente");


                    tf_codigo_registro.setText("");
                    tf_nombre_registro.setText("");
                    tf_categoria.setText("");
                    tf_stock.setText("");
                    tf_precio_producto.setText("");
                    imagenSeleccionada = null;

                    DefaultTableModel model1 = (DefaultTableModel) table_productos.getModel();
                    model1.setRowCount(0); // Limpiar la tabla antes de llenarla

                    List<Document> documents = collection.find().into(new ArrayList<>());
                    for (Document doc : documents) {
                        Vector<Object> row = new Vector<>();
                        row.add(doc.getString("codigo"));
                        row.add(doc.getString("nombre"));
                        row.add(doc.getString("categoria"));
                        row.add(doc.getInteger("stock"));
                        row.add(doc.getDouble("precio"));
                        row.add(doc.getString("imagen"));
                        model1.addRow(row);
                    }
                } catch (Exception ex) {
                    lb_mensaje_productos.setText("Error al actualizar el registro: " + ex.getMessage());
                }
            }
        });

/**
 * Cargar productos al iniciar la aplicación o abrir el panel de productos.
 */
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
            MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
            MongoCollection<Document> collection = db.getCollection("Productos");

            DefaultTableModel model1 = (DefaultTableModel) table_productos.getModel();
            model1.setRowCount(0); // Limpiar la tabla antes de llenarla

            List<Document> documents = collection.find().into(new ArrayList<>());
            for (Document doc : documents) {
                Vector<Object> row = new Vector<>();
                row.add(doc.getString("codigo"));
                row.add(doc.getString("nombre"));
                row.add(doc.getString("categoria"));
                row.add(doc.getInteger("stock"));
                row.add(doc.getDouble("precio"));
                row.add(doc.getString("imagen"));
                model1.addRow(row);
            }
        } catch (Exception ex) {
            lb_mensaje_productos.setText("Error al cargar los productos: " + ex.getMessage());
        }

        bt_eliminar_producto.addActionListener(new ActionListener() {
            /**
             * Evento de clic en el botón de eliminar producto.
             * Elimina el producto seleccionado de la base de datos y actualiza la tabla.
             *
             * @param e El evento de acción que se produjo.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lb_mensaje_productos.setText("");
                if (table_productos.getSelectedRow() == -1) {
                    lb_mensaje_productos.setText("No se ha seleccionado ningún producto para eliminar");
                    return;
                }

                try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                    MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
                    MongoCollection<Document> collection = db.getCollection("Productos");
                    int selectedRow = table_productos.getSelectedRow();
                    String codigo = table_productos.getValueAt(selectedRow, 0).toString();

                    Document filtro = new Document("codigo", codigo);
                    collection.deleteOne(filtro);
                    lb_mensaje_productos.setText("Producto eliminado correctamente");


                    DefaultTableModel model1 = (DefaultTableModel) table_productos.getModel();
                    model1.setRowCount(0);

                    List<Document> documents = collection.find().into(new ArrayList<>());
                    for (Document doc : documents) {
                        Vector<Object> row = new Vector<>();
                        row.add(doc.getString("codigo"));
                        row.add(doc.getString("nombre"));
                        row.add(doc.getString("categoria"));
                        row.add(doc.getInteger("stock"));
                        row.add(doc.getDouble("precio"));
                        row.add(doc.getString("imagen"));
                        model1.addRow(row);
                    }
                } catch (Exception ex) {
                    lb_mensaje_productos.setText("Error al eliminar el producto: " + ex.getMessage());
                }
            }
        });

        /**
         * Acción del botón de búsqueda que busca un producto por código y actualiza la tabla con el resultado.
         */
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codigoBuscado = tf_codigo_buscar.getText().trim();


                if (codigoBuscado.isEmpty()) {
                    lb_mensaje_productos.setText("Por favor, ingrese un código de producto para buscar.");
                    return;
                }

                try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                    MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
                    MongoCollection<Document> collection = db.getCollection("Productos");


                    Document query = new Document("codigo", codigoBuscado);
                    Document resultado = collection.find(query).first();

                    // Limpiar la tabla antes de mostrar los resultados
                    DefaultTableModel productosModel = (DefaultTableModel) table_productos.getModel();
                    productosModel.setRowCount(0); // Limpiar la tabla

                    if (resultado != null) {
                        // Agregar el resultado a la tabla
                        Vector<Object> row = new Vector<>();
                        row.add(resultado.getString("codigo"));
                        row.add(resultado.getString("nombre"));
                        row.add(resultado.getString("categoria"));
                        row.add(resultado.getInteger("stock"));
                        row.add(String.format("%.2f", resultado.getDouble("precio")));

                        // Convertir la ruta de la imagen en un ImageIcon
                        String rutaImagen = resultado.getString("imagen");
                        ImageIcon imageIcon = new ImageIcon(new ImageIcon(rutaImagen).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                        row.add(imageIcon);

                        productosModel.addRow(row); // Agregar la fila a la tabla

                        lb_mensaje_productos.setText("Producto encontrado.");
                        tf_codigo_buscar.setText("");
                    } else {
                        lb_mensaje_productos.setText("Producto no encontrado.");
                        tf_codigo_buscar.setText("");
                    }
                } catch (MongoException exception) {
                    lb_mensaje_productos.setText("Error al buscar producto.");
                    tf_codigo_buscar.setText("");
                }
            }
        });
        /**
         * Acción del JComboBox para seleccionar una categoría de producto.
         * Este método se ejecuta cuando el usuario selecciona una categoría del JComboBox.
         * Realiza una búsqueda de productos en la base de datos que coincidan con la categoría seleccionada
         * y muestra los resultados en la tabla.
         */
        combo_categoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la categoría seleccionada
                String categoriaSeleccionada = (String) combo_categoria.getSelectedItem();

                try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                    MongoDatabase db = mongoClient.getDatabase("MinimarketPro");
                    MongoCollection<Document> collection = db.getCollection("Productos");

                    Document query;
                    if ("Todas".equals(categoriaSeleccionada)) {
                        query = new Document();
                    } else {
                        query = new Document("categoria", categoriaSeleccionada);
                    }

                    List<Document> resultados = collection.find(query).into(new ArrayList<>());


                    DefaultTableModel productosModel = (DefaultTableModel) table_productos.getModel();
                    productosModel.setRowCount(0);

                    for (Document doc : resultados) {
                        Vector<Object> row = new Vector<>();
                        row.add(doc.getString("codigo"));
                        row.add(doc.getString("nombre"));
                        row.add(doc.getString("categoria"));
                        row.add(doc.getInteger("stock"));
                        row.add(doc.getDouble("precio"));

                        // Convertir la ruta de la imagen en un ImageIcon
                        String rutaImagen = doc.getString("imagen");
                        ImageIcon imageIcon = new ImageIcon(rutaImagen);
                        Image img = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                        row.add(new ImageIcon(img)); // Usar el ImageIcon directamente en la fila

                        productosModel.addRow(row);
                    }

                    lb_mensaje_productos.setText("Productos filtrados por categoría: " + categoriaSeleccionada);

                } catch (MongoException exception) {
                    lb_mensaje_productos.setText("Error al buscar productos por categoría.");
                }
            }
        });


        //VENTAS

        verVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        bt_salir.addActionListener(new ActionListener() {
            /**
             * Evento de acción para el botón de salir.
             * Este método cierra toda la aplicación al hacer clic en el botón.
             *
             * @param e El evento de acción que ha ocurrido al hacer clic en el botón de salir.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra toda la aplicación
                System.exit(0);
            }
        });

// ActionListener para el botón de cerrar sesión
        bt_cerrar_sesion.addActionListener(new ActionListener() {
            /**
             * Evento de acción para el botón de cerrar sesión.
             * Este método cierra la ventana actual y abre el formulario de inicio de sesión.
             *
             * @param e El evento de acción que ha ocurrido al hacer clic en el botón de cerrar sesión.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(bt_cerrar_sesion);
                frame.dispose();
                Form_login formLogin = new Form_login();
                formLogin.setVisible(true);
            }
        });
    }
}