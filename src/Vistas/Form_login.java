package Vistas;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_login extends JFrame {
    public JPanel panel;
    private JLabel lb_fondo;
    private JPanel panel_login;
    private JLabel lb_titulo;
    private JLabel lb_usuario;
    private JTextField tf_usuario;
    private JLabel lb_contrasena;
    private JPasswordField pf_contrasena;
    private JButton bt_ingresar;
    private JButton bt_cancelar;

    public Form_login() {
        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true); // Quita la barra de cerrar/minimizar

        // Panel principal
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 400));
        setContentPane(layeredPane);

        // Fondo
        lb_fondo = new JLabel(new ImageIcon("src/Imagenes/Fondo.jpg"));
        lb_fondo.setBounds(0, 0, 600, 400); // Posición y medidas del componente
        layeredPane.add(lb_fondo, JLayeredPane.DEFAULT_LAYER);

        // Panel de login
        panel_login = new JPanel();
        panel_login.setBounds(100, 50, 400, 300); 
        panel_login.setOpaque(true); // Fondo opaco
        panel_login.setBackground(new Color(0, 0, 0, 130));
        panel_login.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Borde
        panel_login.setLayout(new GridBagLayout()); // Disposición

        // Estilos de Grid
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Los componentes se expanden horizontalmente
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        // Login
        lb_titulo = new JLabel("Login");
        lb_titulo.setFont(new Font("Arial", Font.BOLD, 28));
        lb_titulo.setForeground(Color.WHITE);
        lb_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        lb_titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        lb_usuario = new JLabel("Usuario:");
        lb_usuario.setFont(labelFont);
        lb_usuario.setForeground(Color.WHITE);

        tf_usuario = new JTextField(15); //tamaño del campo de texto
        tf_usuario.setFont(fieldFont);

        lb_contrasena = new JLabel("Contraseña:");
        lb_contrasena.setFont(labelFont);
        lb_contrasena.setForeground(Color.WHITE);

        pf_contrasena = new JPasswordField(15); 
        pf_contrasena.setFont(fieldFont);

        bt_ingresar = new JButton("Ingresar");
        bt_ingresar.setFont(new Font("Arial", Font.BOLD, 14));
        bt_ingresar.setBackground(new Color(0x007BFF));
        bt_ingresar.setForeground(Color.WHITE);
        bt_ingresar.setFocusPainted(false);
        bt_ingresar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        bt_cancelar = new JButton("Cancelar");
        bt_cancelar.setFont(new Font("Arial", Font.BOLD, 14));
        bt_cancelar.setBackground(new Color(0xFF0015));
        bt_cancelar.setForeground(Color.WHITE);
        bt_cancelar.setFocusPainted(false);
        bt_cancelar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Agregar los componentes al panel
        GridBagConstraints gbc_title = new GridBagConstraints();
        gbc_title.gridx = 0;
        gbc_title.gridy = 0;
        gbc_title.gridwidth = 2;
        gbc_title.insets = new Insets(20, 10, 20, 10);
        panel_login.add(lb_titulo, gbc_title);

        gbc.gridx = 0; // Ubicación en la cuadrícula del grid (columna 1)
        gbc.gridy = 1; // Ubicación en la cuadrícula del grid (fila 2)
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END; //Alinea el tf
        panel_login.add(lb_usuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START; 
        panel_login.add(tf_usuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel_login.add(lb_contrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel_login.add(pf_contrasena, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        //Panel botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(bt_ingresar);
        buttonPanel.add(bt_cancelar);

        panel_login.add(buttonPanel, gbc);

        // Agrega el panel del login al principal
        layeredPane.add(panel_login, JLayeredPane.PALETTE_LAYER);
        pack();
        setVisible(true);


        //Accion de los botones
        bt_cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bt_ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Conexion a la nube
                try (MongoClient mongoClient = MongoClients.create("mongodb+srv://alissonviteri01:123456poo24a@cluster0.f0q39vt.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")) {
                    MongoDatabase database = mongoClient.getDatabase("MinimarketPro");
                    MongoCollection<Document> collection = database.getCollection("Usuarios");
                    Document query = new Document("usuario", tf_usuario.getText()).append("contrasena", pf_contrasena.getText());
                    Document user = collection.find(query).first();

                    if (user != null) {
                        String rol = user.getString("rol");
                        if ("administrador".equals(rol)) {
                            JFrame frame = new JFrame("Administrador");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setContentPane(new Form_administrador().panel_principal);
                            frame.pack();
                            frame.setSize(1400, 700);
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                        } else if ("cajero".equals(rol)) {
                            JFrame cajeroFrame = new JFrame("Cajero");
                            cajeroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            cajeroFrame.setContentPane(new Form_cajero().panel_cajero);
                            cajeroFrame.pack();
                            cajeroFrame.setSize(900, 600);
                            cajeroFrame.setLocationRelativeTo(null);
                            cajeroFrame.setVisible(true);
                        }
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                        tf_usuario.setText("");
                        pf_contrasena.setText("");
                    }
                } catch (Exception e1) {
                    System.out.println("Error al conectar a MongoDB Atlas: " + e1.getMessage());
                }
            }
        });
    }

}
