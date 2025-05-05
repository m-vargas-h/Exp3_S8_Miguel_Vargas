# 🎭 **Sistema de Venta de Entradas para Teatro**

Este proyecto es un sistema de gestión de venta de entradas para un teatro, que permite a los usuarios **comprar, modificar y agregar boletos**, además de procesar pagos y generar una boleta con el resumen de compra.

---

## 📌 **Clases y Métodos**

### 🎟️ **Clase `Exp3_S8_Miguel_Vargas` (Punto de partida)**
**Descripción:**  
Esta es la clase principal del sistema, es la encargada de iniciar la interfaz de usuario, y gestionar el ciclo
completo de ejecución del código.

**Métodos:**  
- `public static void main(String[] args)` → Inicia la aplicación, creando una instancia de `TeatroUI` y ejecutando `mostrarMenu()`.  

---

### 🎟️ **Clase `Entrada`**
**Descripción:**  
Representa una entrada de teatro, almacenando detalles como la zona, asiento, precio, y estado de reserva.

**Atributos:**
- `idVenta` → ID único de la venta.
- `zonaSeleccionada` → Zona de la entrada (**VIP, Normal, Palco**).
- `fila`, `columna` → Posición del asiento.
- `precioBase` → Precio original de la entrada.
- `filaChar` → Representación de la fila (`A, B, C...`).
- `esReserva` → Estado de la reserva.
- `descuentoAplicado` → Porcentaje de descuento aplicado.

**Métodos:**
- `Entrada(...)` → Constructor.
- `getZonaSeleccionada()` → Retorna la zona de la entrada.
- `getFilaChar()` → Obtiene la fila como carácter.
- `getFila(), int getColumna()` → Devuelve la posición del asiento.
- `getPrecioBase(), double getDescuentoAplicado()` → Obtiene precio y descuento.
- `setZonaSeleccionada(int nuevaZona)` → Modifica la zona.
- `setFila(int nuevaFila), setColumna(int nuevaColumna)` → Cambia la posición.
- `setPrecioBase(double nuevoPrecioBase)` → Ajusta el precio.
- `setReserva(boolean esReserva)` → Modifica el estado de reserva.
- `String toString()` → Representación textual de la entrada.

---

### 🎟️ **Clase `EntradaManager`**
**Descripción:**  
Esta clase es la encargada de gestionar la reserva y modificación de entradas, ademas de generar la boleta.

**Atributos:**  
- `entradasCompradas`, `reservasAsientos` → Listas de compras y reservas.
- `idVentaGlobal`, `idCompraActual` → Identificadores únicos.
- `zonaVip`, `zonaNormal`, `zonaPalco` → Matrices de ocupación.

**Métodos:**
- `mostrarPlano(boolean[][] zona)` → Muestra los asientos disponibles.
- `List<Entrada> obtenerEntradasCompradas(), obtenerReservasAsientos()` → Retorna listas de compras y reservas.
- `iniciarNuevaCompra()` → Asigna un nuevo `idCompraActual`.
- `agregarVenta(...)` → Registra una entrada comprada.
- `modificarCompra(Scanner scanner)` → Permite modificar o agregar entradas.
- `cambiarEntrada(Scanner scanner)` → Modifica una entrada existente.
- `agregarEntradas(Scanner scanner)` → Agrega nuevas entradas.
- `procesarPago(Scanner scanner)` → Procesa el pago con distintas opciones.
- `generarBoleta()` → Genera un resumen de compra.

---

### 🎭 **Clase `TeatroUI`**
**Descripción:**  
Maneja la interfaz de usuario y el flujo del menú.

**Atributos:**  
- `entradaManager` → Administra la lógica de compra.
- `scanner` → Recibe la interacción del usuario.

**Métodos:**
- `mostrarMenu()` → Muestra el menú principal.
- `procesarOpcion(int opcion)` → Procesa la opción seleccionada.
- `seleccionarEntradas()` → Inicia la compra de entradas.
- `mostrarPlanoGeneral()` → Muestra los asientos disponibles.
- `modificarCompra()` → Permite modificar una compra.
- `procesarPago()` → Inicia el proceso de pago.