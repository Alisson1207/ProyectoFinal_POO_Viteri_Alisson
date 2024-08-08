
# Escuela Politecnica Nacional



## Autor

- Alisson Viteri
## Minimarket Pro
### Descripción del Proyecto
Es un sistema integral diseñado para optimizar las operaciones diarias en un minimarket. Este sistema facilita la gestión de ventas, control de inventario y administración de usuarios mediante una solución integrada en Java.

### Objetivo
El objetivo principal de MiniMarketPro es proporcionar una plataforma que simplifique las tareas cotidianas en un minimarket, incluyendo la gestión de transacciones, el control de stock, y la administración de usuarios.

## Estuctura del Proyecto

## Clases Principales
El sistema está diseñado utilizando los principios de Programación Orientada a Objetos (POO).

### Compra
Representa una transacción de compra en el sistema. Registra detalles como el identificador del pedido, la cantidad de producto, el precio total y la fecha de la compra.

#### Funcionalidad
Esta clase hereda de la clase Productos, permitiendo obtener la información del producto al realizar la compra.



### Personal
En esta clase se realiza el registro de los usuarios gestionando sus roles y permisos dentro del sistema.

### Productos
Maneja la información de los productos en inventario, incluyendo el nombre del producto y su respectiva imagen, además proporciona métodos para su gestión.

### Ventas
Registra las ventas realizadas, asociando la venta con el cajero que la realizó. Incluye información sobre el número de venta y la fecha.

#### Funcionalidad
Hereda de la clase Personal, permitiendo vincular cada venta con el usuario que la registró.

## Base de Datos
* MongoDB Atlas: Utilizado para almacenar datos en la nube.
#### Colecciones
* Ventas: Almacena información sobre las transacciones de venta.
* Productos: Contiene datos sobre los productos y su stock.
* Usuarios: Registra la información de los usuarios del sistema.

## Librerías
* iText PDF (versión 5.5.9): Utilizada para la generación de notas de venta en formato PDF.

## Vistas
* Login: Interfaz para el inicio de sesión de usuarios.
* Cajero: Vista para la gestión de ventas y visualización del catálogo de productos.
* Administrador: Vista para la gestión de productos, usuarios y ventas.
A






