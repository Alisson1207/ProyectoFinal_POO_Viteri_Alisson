import javax.swing.*;
import java.awt.*;

public class Form_login extends JFrame {
    private JPanel panel;
    private JLabel lb_fondo;
    private JPanel panel_login;
    private JLabel lb_titulo;
    private JLabel lb_usuario;
    private JTextField tf_usuario;
    private JLabel lb_contrasena;
    private JPasswordField pf_contrasena;
    private JButton bt_ingresar;

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
        panel_login.setBounds(100, 75, 400, 300);
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
        lb_titulo.setForeground(Color.WHITE); // Texto blanco para el título
        lb_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        lb_titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        lb_usuario = new JLabel("Usuario:");
        lb_usuario.setFont(labelFont);
        lb_usuario.setForeground(Color.WHITE);

        tf_usuario = new JTextField();
        tf_usuario.setFont(fieldFont);
        // Ajusta el campo de texto para que se expanda en el GridBagLayout
        gbc.weightx = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Hace que el campo ocupe toda la columna

        // Ajustar el espaciado para que los campos de texto lleguen a 2 cm antes del borde blanco
        gbc.insets = new Insets(10, 20, 10, 20); // 20 píxeles de margen izquierdo y derecho

        lb_contrasena = new JLabel("Contraseña:");
        lb_contrasena.setFont(labelFont);
        lb_contrasena.setForeground(Color.WHITE);

        pf_contrasena = new JPasswordField();
        pf_contrasena.setFont(fieldFont);
        // Ajustar el campo de contraseña para que se expanda en el GridBagLayout
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Hace que el campo ocupe toda la columna

        // Ajustar el espaciado para que los campos de texto lleguen a 2 cm antes del borde blanco
        gbc.insets = new Insets(10, 20, 10, 20); // 20 píxeles de margen izquierdo y derecho

        bt_ingresar = new JButton("Login");
        bt_ingresar.setFont(new Font("Arial", Font.BOLD, 14));
        bt_ingresar.setBackground(new Color(0x007BFF));
        bt_ingresar.setForeground(Color.WHITE);
        bt_ingresar.setFocusPainted(false);
        bt_ingresar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

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

        panel_login.add(lb_usuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel_login.add(tf_usuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel_login.add(lb_contrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        panel_login.add(pf_contrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // El botón de ingresar ocupa dos columnas
        gbc.anchor = GridBagConstraints.CENTER;

        panel_login.add(bt_ingresar, gbc);

        // Agrega el panel del login al principal
        layeredPane.add(panel_login, JLayeredPane.PALETTE_LAYER);
        pack();
        setVisible(true);
    }

}
