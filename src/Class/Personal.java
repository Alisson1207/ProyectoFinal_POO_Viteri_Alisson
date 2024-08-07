package Class;

import java.util.Date;

public class Personal {
    private String nombre;
    private String rol;
    private String usuario;
    private String contrasenia;
    private Date fechaIngreso;
    private String cedula;

    /**
     * Constructor por defecto de la clase Personal.
     */
    public Personal() {
    }

    /**
     * Constructor con parámetros para inicializar un objeto de la clase Personal.
     *
     * @param nombre       Nombre del usuario.
     * @param rol          Rol del usuario.
     * @param usuario      Nombre de usuario para iniciar sesión.
     * @param contrasenia  Contraseña del usuario.
     * @param fechaIngreso Fecha de ingreso del usuario en el sistema.
     * @param cedula       Cédula del usuario.
     */
    public Personal(String nombre, String rol, String usuario, String contrasenia, Date fechaIngreso, String cedula) {
        this.nombre = nombre;
        this.rol = rol;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.fechaIngreso = fechaIngreso;
        this.cedula = cedula;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Obtiene el nombre de usuario para iniciar sesión.
     *
     * @return El nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario para iniciar sesión.
     *
     * @param usuario El nombre de usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasenia La contraseña del usuario.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene la fecha de ingreso del usuario en el sistema.
     *
     * @return La fecha de ingreso del usuario.
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Establece la fecha de ingreso del usuario en el sistema.
     *
     * @param fechaIngreso La fecha de ingreso del usuario.
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Obtiene la cédula del usuario.
     *
     * @return La cédula del usuario.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del usuario.
     *
     * @param cedula La cédula del usuario.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
