package com.mycompany.exp3_s8_miguel_vargas;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class EntradaManager {
    private List<Entrada> entradasCompradas;
    private List<Entrada> reservasAsientos;
    private int idVentaGlobal = 1; // ID de venta inicial
    private int idCompraActual = 1; // Inicializar ID de compra

    //distribución de asientos por zona. true = asiento ocupado, false = asiento libre
    static boolean[][] zonaVip = new boolean[2][6];
    static boolean[][] zonaNormal = new boolean[4][6];
    static boolean[][] zonaPalco = new boolean[3][6];

    public void mostrarPlano(boolean[][] zona) {
        System.out.print("  "); // Espacio inicial para alinear columnas
        for (int col = 0; col < zona[0].length; col++) {
            System.out.print((col + 1) + " "); // Etiquetas de columnas
        }
        System.out.println();
    
        for (int fila = 0; fila < zona.length; fila++) {
            System.out.print((char) ('A' + fila) + " "); // Etiquetas de filas
            for (int col = 0; col < zona[fila].length; col++) {
                System.out.print(zona[fila][col] ? 'X' : 'O'); // 'X' ocupado, 'O' libre
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public boolean[][] getZonaVip() {
        return zonaVip;
    }
    
    public boolean[][] getZonaNormal() {
        return zonaNormal;
    }
    
    public boolean[][] getZonaPalco() {
        return zonaPalco;
    }

    public EntradaManager() {
        this.entradasCompradas = new ArrayList<>();
        this.reservasAsientos = new ArrayList<>();
    }

    public void iniciarNuevaCompra() {
    idCompraActual = idVentaGlobal++; // Asignar nuevo ID de compra
}

    public void agregarVenta(int zona, int fila, int columna, double precio, char filaChar, double descuentoAplicado) {
        Entrada nuevaVenta = new Entrada(idVentaGlobal++, zona, fila, columna, precio, filaChar, false, descuentoAplicado);
        entradasCompradas.add(nuevaVenta);
        System.out.println("Venta registrada: " + nuevaVenta);
    }

    public void agregarReserva(int zona, int fila, int columna, double precio, char filaChar) {
        Entrada nuevaReserva = new Entrada(idVentaGlobal++, zona, fila, columna, precio, filaChar, true, 0);
        reservasAsientos.add(nuevaReserva);
        System.out.println("Reserva registrada: " + nuevaReserva);
    }

    public void confirmarReserva(int idReserva, double descuentoAplicado) {
        for (Entrada reserva : reservasAsientos) {
            if (reserva.getIdVenta() == idReserva) {
                reserva.setReserva(false);
                reserva.setDescuentoAplicado(descuentoAplicado);
                entradasCompradas.add(reserva);
                reservasAsientos.remove(reserva);
                System.out.println("Reserva confirmada como venta: " + reserva);
                return;
            }
        }
        System.out.println("Reserva no encontrada.");
    }

    public double calcularDescuento(int edad, double precioBase) {
        double descuento = 0.0;
    
        if (edad >= 60) {
            descuento = 0.15; // 15% descuento para tercera edad
        } else if (edad >= 18 && edad <= 25) {
            descuento = 0.10; // 10% descuento para estudiantes
        }
    
        return descuento; // Devuelve solo el porcentaje para aplicarlo al precio base
    }

    public List<Entrada> obtenerEntradasCompradas() {
        return entradasCompradas;
    }

    public List<Entrada> obtenerReservasAsientos() {
        return reservasAsientos;
    }

    public void procesarPago(Scanner scanner) {
        if (entradasCompradas.isEmpty()) {
            System.out.println("No hay compras realizadas. Por favor, compre sus entradas antes de proceder al pago.");
            return;
        }
    
        System.out.println("\nSu compra es de " + entradasCompradas.size() + " entradas.");
        System.out.println("Seleccione el medio de pago:");
        System.out.println("1. Débito\n2. Crédito\n3. Transferencia\n4. Cancelar compra");
    
        int opcionPago = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
    
        switch (opcionPago) {
            case 1 -> procesarPagoDebito(scanner);
            case 2 -> procesarPagoCredito(scanner);
            case 3 -> procesarPagoTransferencia(scanner);
            case 4 -> {
                System.out.println("Compra cancelada. Vuelve pronto.");
                return;
            }
            default -> System.out.println("Opción inválida.");
        }
    
        generarBoleta(); // Se genera la boleta después del pago exitoso
        entradasCompradas.clear();
        System.out.println("Compra completada y procesada correctamente.");
    }

    private void procesarPagoDebito(Scanner scanner) {
        System.out.print("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();
    
        System.out.println("Procesando pago con tarjeta de débito...");
        esperarProcesamiento();
    
        System.out.println("Pago confirmado. Se enviará un comprobante a " + correo);
    }

    private void procesarPagoCredito(Scanner scanner) {
        System.out.print("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();
    
        int cuotas;
        do {
            System.out.print("Seleccione el número de cuotas (1 a 12): ");
            cuotas = scanner.nextInt();
            if (cuotas < 1 || cuotas > 12) {
                System.out.println("Número de cuotas inválido. Intente nuevamente.");
            }
        } while (cuotas < 1 || cuotas > 12);
    
        System.out.println("Procesando pago con tarjeta de crédito en " + cuotas + " cuotas...");
        esperarProcesamiento();
    
        System.out.println("Pago confirmado en " + cuotas + " cuotas. Se enviará un comprobante a " + correo);
    }

    private void procesarPagoTransferencia(Scanner scanner) {
        System.out.print("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();
    
        System.out.println("Procesando solicitud de pago por transferencia...");
        esperarProcesamiento();
    
        System.out.println("Las instrucciones para completar su pago han sido enviadas a " + correo);
        System.out.println("Revise su correo y siga los pasos indicados para finalizar la compra.");
    }

    private void esperarProcesamiento() {
        try {
            Thread.sleep(2000); // Espera de 2 segundos
        } catch (InterruptedException e) {
            System.out.println("Error en la simulación de pago.");
        }
    }

    public void generarBoleta() {
        System.out.println("\n--------------- BOLETA ---------------");
        System.out.println("                Nº 000" + idCompraActual);
        System.out.println("             TEATRO MORO");
        System.out.println(" SHOW: De vuelta a clases con el GOTH");
        System.out.println("--------------------------------------");
    
        double totalNeto = 0;
        double totalIVA = 0;
        double totalFinal = 0;
    
        for (Entrada entrada : entradasCompradas) {
            String zona = switch (entrada.getZonaSeleccionada()) {
                case 1 -> "VIP";
                case 2 -> "Normal";
                case 3 -> "Palco";
                default -> "Desconocida";
            };
    
            double precioConDescuento = entrada.getPrecioBase() * (1 - entrada.getDescuentoAplicado());
            double ivaPorEntrada = precioConDescuento * 0.19;
            double precioNetoEntrada = precioConDescuento - ivaPorEntrada;
    
            totalNeto += precioNetoEntrada;
            totalIVA += ivaPorEntrada;
            totalFinal += precioConDescuento;
    
            System.out.println("Entrada: Zona " + zona + ", Asiento: " + entrada.getFilaChar() + (entrada.getColumna() + 1));
            System.out.println("Precio base: $" + entrada.getPrecioBase());
            System.out.println("Descuento aplicado: " + (entrada.getDescuentoAplicado() * 100) + "%");
            System.out.println("--------------------------------------");
        }
    
        System.out.println("-------- RESUMEN DE LA COMPRA --------");
        System.out.println("Subtotal (Neto)     : $" + totalNeto);
        System.out.println("IVA (19%)           : $" + totalIVA);
        System.out.println("TOTAL FINAL A PAGAR : $" + totalFinal);
        System.out.println("--------------------------------------");
        System.out.println("¡Gracias por tu compra!");
    }

    public void promocionesDisponibles() {
        System.out.println("\n--- Promociones Disponibles ---");
        List<String> promociones = List.of(
            "10% de descuento para estudiantes.",
            "15% de descuento para personas de la tercera edad."
        );
    
        promociones.forEach(System.out::println);
    }

    private void confirmarCompra(String metodoPago) {
        System.out.println("Has elegido el método de pago: " + metodoPago);
        System.out.println("Procesando la compra...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("¡Compra confirmada!");
    }

    public void modificarCompra(Scanner scanner) {
        System.out.println("\n--- Modificar Compra ---");
        System.out.println("1. Cambiar entrada");
        System.out.println("2. Eliminar entrada");
        System.out.println("3. Agregar más entradas");
        System.out.println("4. Regresar al menú principal");
        System.out.print("Seleccione una opción: ");
    
        int opcion = scanner.nextInt();
    
        switch (opcion) {
            case 1 -> cambiarEntrada(scanner);
            case 2 -> eliminarEntrada(scanner);
            case 3 -> agregarEntradas(scanner);
            case 4 -> System.out.println("Regresando al menú principal...");
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    }
    
    public void cambiarEntrada(Scanner scanner) {
        if (entradasCompradas.isEmpty()) {
            System.out.println("No hay entradas compradas para modificar.");
            return;
        }
    
        System.out.println("\nListado de entradas compradas:");
        for (int i = 0; i < entradasCompradas.size(); i++) {
            Entrada entrada = entradasCompradas.get(i);
            System.out.println((i + 1) + ". Zona: " + entrada.getZonaSeleccionada() + ", Asiento: " + entrada.getFilaChar() + (entrada.getColumna() + 1));
        }
    
        System.out.print("Seleccione el número de la entrada que desea cambiar: ");
        int indiceEntrada = scanner.nextInt() - 1;
    
        if (indiceEntrada < 0 || indiceEntrada >= entradasCompradas.size()) {
            System.out.println("Selección inválida.");
            return;
        }
    
        Entrada entradaActual = entradasCompradas.get(indiceEntrada);
        
        // Liberar asiento anterior
        boolean[][] zonaActual = switch (entradaActual.getZonaSeleccionada()) {
            case 1 -> zonaVip;
            case 2 -> zonaNormal;
            case 3 -> zonaPalco;
            default -> null;
        };
    
        if (zonaActual != null) {
            zonaActual[entradaActual.getFila()][entradaActual.getColumna()] = false;
        }
    
        System.out.println("Seleccione la nueva zona (1. VIP / 2. Normal / 3. Palco): ");
        int nuevaZona = scanner.nextInt();
        
        boolean[][] nuevaZonaAsientos = switch (nuevaZona) {
            case 1 -> zonaVip;
            case 2 -> zonaNormal;
            case 3 -> zonaPalco;
            default -> {
                System.out.println("Zona inválida.");
                yield null;
            }
        };
    
        if (nuevaZonaAsientos == null) return;
    
        mostrarPlano(nuevaZonaAsientos);
        System.out.println("Seleccione el nuevo asiento:");
        int[] nuevoAsiento = reservarAsiento(scanner, nuevaZonaAsientos);
    
        entradaActual.setZonaSeleccionada(nuevaZona);
        entradaActual.setFila(nuevoAsiento[0]);
        entradaActual.setColumna(nuevoAsiento[1]);
        entradaActual.setFilaChar((char) ('A' + nuevoAsiento[0]));
    
        System.out.println("Entrada modificada exitosamente.");
    }
    
    private void eliminarEntrada(Scanner scanner) {
        if (entradasCompradas.isEmpty()) {
            System.out.println("No hay entradas compradas para eliminar.");
            return;
        }
    
        // Mostrar entradas y permitir eliminación
        System.out.println("\n--- Entradas Compradas ---");
        for (int i = 0; i < entradasCompradas.size(); i++) {
            System.out.println((i + 1) + ". " + entradasCompradas.get(i));
        }
    
        System.out.print("\nSeleccione la entrada que desea eliminar: ");
        int indice = scanner.nextInt() - 1;
    
        if (indice >= 0 && indice < entradasCompradas.size()) {
            entradasCompradas.remove(indice);
            System.out.println("Entrada eliminada exitosamente.");
        } else {
            System.out.println("Selección inválida.");
        }
    }

    public int[] reservarAsiento(Scanner scanner, boolean[][] zonaActual) {
        int fila, columna;
        while (true) {
            System.out.print("Seleccione fila (A, B, C...): ");
            char filaChar = scanner.next().toUpperCase().charAt(0);
            fila = filaChar - 'A';
            System.out.print("Seleccione columna (1, 2, 3...): ");
            columna = scanner.nextInt() - 1;
    
            if (fila < 0 || fila >= zonaActual.length || columna < 0 || columna >= zonaActual[0].length) {
                System.out.println("Selección inválida. Intente nuevamente.");
            } else if (zonaActual[fila][columna]) { // Verifica si está ocupado
                System.out.println("El asiento ya está ocupado. Intente nuevamente.");
            } else {
                zonaActual[fila][columna] = true; // Marcar asiento como reservado
                break;
            }
        }
        return new int[]{fila, columna};
    }
    
    public void seleccionarEntradas(Scanner scanner) {
        iniciarNuevaCompra(); // Generar un nuevo ID de compra
    
        System.out.println("¿Cuántas entradas desea comprar? (Máximo 5)");
        int cantidad = scanner.nextInt();
    
        if (cantidad > 0 && cantidad <= 5) {
            for (int i = 0; i < cantidad; i++) {
                System.out.println("\n--- Entrada #" + (i + 1) + " ---");
                System.out.println("Seleccione una zona (1. VIP / 2. Normal / 3. Palco): ");
                int zonaSeleccionada = scanner.nextInt();
    
                boolean[][] zonaActual = switch (zonaSeleccionada) {
                    case 1 -> zonaVip;
                    case 2 -> zonaNormal;
                    case 3 -> zonaPalco;
                    default -> {
                        System.out.println("Zona inválida, intente nuevamente.");
                        yield null;
                    }
                };
    
                if (zonaActual == null) continue;
    
                mostrarPlano(zonaActual);
                System.out.println("Seleccione su asiento:");
                int[] asiento = reservarAsiento(scanner, zonaActual);
                int fila = asiento[0];
                int columna = asiento[1];
                char filaChar = (char) ('A' + fila);
    
                double precioBase = switch (zonaSeleccionada) {
                    case 1 -> 20000;
                    case 2 -> 7000;
                    case 3 -> 12000;
                    default -> 0;
                };
    
                System.out.print("Ingrese su edad: ");
                int edad = scanner.nextInt();
    
                double descuentoAplicado = calcularDescuento(edad, precioBase);
                double precioFinal = precioBase * (1 - descuentoAplicado);
    
                Entrada nuevaEntrada = new Entrada(idCompraActual, zonaSeleccionada, fila, columna, precioFinal, filaChar, false, descuentoAplicado);
                entradasCompradas.add(nuevaEntrada);
            }
    
            mostrarDetalleCompra();
        } else {
            System.out.println("Cantidad inválida. Intente nuevamente.");
        }
    }

    public void mostrarDetalleCompra() {
        if (entradasCompradas.isEmpty()) {
            System.out.println("\nNo hay entradas compradas.");
            return;
        }
    
        System.out.println("\n--- Detalle de la Compra ---");
        System.out.println("--------------------------------------");
        System.out.println("ID de compra: " + idVentaGlobal);
    
        double total = 0;
    
        for (Entrada entrada : entradasCompradas) {
            System.out.println(entrada); // Usa el `toString()` de `Entrada` para mostrar la información
            total += entrada.getPrecioBase() * (1 - entrada.getDescuentoAplicado());
        }
    
        System.out.println("--------------------------------------");
        System.out.printf("TOTAL DE LA COMPRA: $%.2f%n", total);
    }

    public void agregarEntradas(Scanner scanner) {
        if (entradasCompradas.isEmpty()) {
            System.out.println("No hay una compra activa para agregar entradas.");
            return;
        }
    
        int entradasDisponibles = 5 - entradasCompradas.size(); // 🔹 Determinar cuántas más se pueden comprar
    
        if (entradasDisponibles <= 0) {
            System.out.println("Ya ha alcanzado el límite de 5 entradas. No puede agregar más.");
            return;
        }
    
        System.out.println("¿Cuántas entradas adicionales desea agregar? (Máximo " + entradasDisponibles + ")");
        int cantidad = scanner.nextInt();
    
        if (cantidad <= 0 || cantidad > entradasDisponibles) {
            System.out.println("Cantidad inválida. Intente nuevamente.");
            return;
        }
    
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\n--- Entrada Adicional #" + (i + 1) + " ---");
            System.out.println("Seleccione una zona (1. VIP / 2. Normal / 3. Palco): ");
            int zonaSeleccionada = scanner.nextInt();
    
            boolean[][] zonaActual = switch (zonaSeleccionada) {
                case 1 -> zonaVip;
                case 2 -> zonaNormal;
                case 3 -> zonaPalco;
                default -> {
                    System.out.println("Zona inválida, intente nuevamente.");
                    yield null;
                }
            };
    
            if (zonaActual == null) continue;
    
            mostrarPlano(zonaActual); // Mostrar el mapa de asientos antes de elegir
            System.out.println("Seleccione su asiento:");
            int[] asiento = reservarAsiento(scanner, zonaActual);
            int fila = asiento[0];
            int columna = asiento[1];
            char filaChar = (char) ('A' + fila);
    
            double precioBase = switch (zonaSeleccionada) {
                case 1 -> 20000;
                case 2 -> 7000;
                case 3 -> 12000;
                default -> 0;
            };
    
            System.out.print("Ingrese su edad: ");
            int edad = scanner.nextInt();
            double descuentoAplicado = calcularDescuento(edad, precioBase);
            double precioFinal = precioBase * (1 - descuentoAplicado);
    
            Entrada nuevaEntrada = new Entrada(idCompraActual, zonaSeleccionada, fila, columna, precioFinal, filaChar, false, descuentoAplicado);
            entradasCompradas.add(nuevaEntrada);
    
            System.out.println("Entrada agregada exitosamente con un descuento de " + (descuentoAplicado * 100) + "%.");
        }
    
        mostrarDetalleCompra(); // Actualizar la boleta después de agregar nuevas entradas
    }

    
}