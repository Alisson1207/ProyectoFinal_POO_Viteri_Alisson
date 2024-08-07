import Vistas.Form_login;

/**
 * Clase principal que inicia la aplicación.
 *
 * @author Alisson Viteri
 */
public class Main {
    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        Form_login formLogin = new Form_login();
        formLogin.setVisible(true);
    }
}
