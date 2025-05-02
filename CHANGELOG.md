# Changelog

## 01/05/2025 - Creación nuevos métodos (last commit)
- se creo el método calcularDescuento para poder modificar los descuentos y su aplicación de forma mas sencilla.
- se modifico el método promocionesDisponibles para facilitar la creación y/0 modificación de promociones listadas.

### 01/05/2025 - Implementación opción volver (commit a5ec53e)
- se implemento una opción "volver" dentro de los distintos menus del programa para facilitar la
  navegación entre ellos y poder moverse sin verse obligado a completar una acción o cancelar todo.

### 01/05/2025 - Modificación sistema de compra (commit 588fe09)
- se modifico el método procesarPago agregando una validación que obligue al usuario a pagar la
  transacción actual antes de poder hacer una nueva, de esta forma se evita que se pueda comprar mas de 5 entradas por transacción. Ademas se incluye la opción de realizar una nueva compra después de pagar.
- se modifico el método generarBoleta para que ahora incluya el numero de la venta.

### 01/05/2025 - Modificación plano y métodos asociados (commit 8832cca)
- se modifica la clase Entrada y otras partes del código para incluir un idVenta en las transacciones realizadas.
- se modifica el arreglo que representa la distribución de asientos para trabajar con valores booleanos.
- Se modifican los siguientes métodos para poder trabajar con valores booleanos:
    - eliminarEntrada
    - reservarAsiento
    - agregarEntradas
    - seleccionarEntradas

## 30/04/2025 - Carga Inicial Archivos (commit 50c63ea)
- Creación de repositorio base utilizando como base el proyecto anterior.
- Creación fichero README con información básica del proyecto.
- Creación fichero CHANGELOG con un historial de modificaciones.