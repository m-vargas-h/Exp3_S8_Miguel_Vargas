# Changelog

## 05/05/2025 - Inclusion Breakpoints (last commit)
- Inclusion de comentarios con Breakpoints en el código para verificar la correcta ejecución del código.

### 05/05/2025 - Ajustes finales (commit 4672cdd)
- Se realizan ajustes menores de los mensajes imprimidos por consola para facilitar la comprensión de las
  instrucciones entregadas al usuario.
- Se actualizan comentarios finales dentro del código para indiciar el uso de cada método.
- Se actualiza el fichero Readme con una explicación del funcionamiento del código.
- Creación de un fichero Métodos con una explicación del funcionamiento de los métodos utilizados en cada clase.

### 04/05/2025 Creación clases adicionales (commit acbdfc6)
- Se creo la clase EntradaManager para mover la gestión de entradas ahi, esta clase se encarga de la reserva y
  validación de asientos, ademas de la modificación de compras.
- Se creo la clase TeatroUI que maneja la interfaz de usuario y el flujo del menu, esta clase permite y gestiona
  la navegación entre las distintas opciones del menu.

### 02/05/2025 - Creación nueva clase (commit 65f790f)
- Se creo la clase Entrada para presentar las compras de forma mas estructurada ya que facilita el almacenamiento
  y modificación de datos. Se configuraron diversos getter y setter para poder leer y modificar la información.


### 01/05/2025 - Creación nuevos métodos (commit a8cb6ef)
- se creo el método calcularDescuento para poder modificar los descuentos y su aplicación de forma mas sencilla.
- se modifico el método promocionesDisponibles para facilitar la creación y/0 modificación de promociones listadas.

### 01/05/2025 - Implementación opción volver (commit a5ec53e)
- se implemento una opción "volver" dentro de los distintos menus del programa para facilitar la
  navegación entre ellos y poder moverse sin verse obligado a completar una acción o cancelar todo.

### 01/05/2025 - Modificación sistema de compra (commit 588fe09)
- se modifico el método procesarPago agregando una validación que obligue al usuario a pagar la
  transacción actual antes de poder hacer una nueva, de esta forma se evita que se pueda comprar mas de 5 entradas por 
  transacción. Ademas se incluye la opción de realizar una nueva compra después de pagar.
- se modifico el método generarBoleta para que ahora incluya el numero de la venta.

### 01/05/2025 - Modificación plano y métodos asociados (commit 8832cca)
- se modifica la clase Entrada y otras partes del código para incluir un idVenta en las transacciones realizadas.
- se modifica el arreglo que representa la distribución de asientos para trabajar con valores booleanos.
- Se modifican los siguientes métodos para poder trabajar con valores booleanos:
    - eliminarEntrada
    - reservarAsiento
    - agregarEntradas
    - seleccionarEntradas

### 30/04/2025 - Carga Inicial Archivos (commit 50c63ea)
- Creación de repositorio base utilizando como base el proyecto anterior.
- Creación fichero README con información básica del proyecto.
- Creación fichero CHANGELOG con un historial de modificaciones.