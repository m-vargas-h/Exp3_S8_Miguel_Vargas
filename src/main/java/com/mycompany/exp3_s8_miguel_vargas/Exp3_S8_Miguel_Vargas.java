/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package com.mycompany.exp3_s8_miguel_vargas;

 import java.util.Scanner;
 import java.util.List;
 import java.util.ArrayList;

 /**
  * S8 - Optimizando listas y arreglos en Java
  * author Miguel Vargas
  */

 public class Exp3_S8_Miguel_Vargas {
     
    //variables estáticas
    static int entradaAcumulada = 0;
    static double totalAcumulado = 0;
    static double descuento = 0;
    static String ultimaEntradaComprada = "No hay entradas compradas aún."; //almacenara los datos de la ultima entrada que se compre
    static int idVentaGlobal = 1; //id inicial para ventas
     
    //estas variables servirán para poder eliminar la ultima entrada comprada
    static int ultimaZonaSeleccionada = -1; // Zona de la última compra (1 = VIP, 2 = Normal, 3 = Palco)
    static int ultimaFila = -1;             //fila del asiento
    static int ultimaColumna = -1;          //columna del asiento
    static double precioBaseUltimaEntrada = 0;
 
    //distribución de asientos por zona. true = asiento ocupado, false = asiento libre
    static boolean[][] zonaVip = new boolean[2][6];
    static boolean[][] zonaNormal = new boolean[4][6];
    static boolean[][] zonaPalco = new boolean[3][6];

    //precios base por zona
    static double precioVip = 20000;
    static double precioNormal = 7000;
    static double precioPalco = 12000;
    static double precioFinal;
 
    //listas para almacenar entradas compradas y reservas de asientos
    static List<Entrada> entradasCompradas = new ArrayList<>();
    static List<Entrada> reservasAsientos = new ArrayList<>();
 
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
 
        //saludo de bienvenida
        System.out.println("---------------------");
        System.out.println("     TEATRO MORO");
        System.out.println("---------------------");
        System.out.println("Bienvenido a nuestro sistema de compra");
        System.out.println("Actualmente tenemos el siguiente show disponible:");
        System.out.println("-- De vuelta a clases con el GOTH --");
 
        //inicio del menu
        while (continuar) {
 
            menu();
 
            if (scanner.hasNextInt()) {
 
                int opcion = scanner.nextInt();
 
                switch (opcion) {
                    case 1:
                        seleccionarEntradas(scanner);
                        break;
 
                    case 2:
                        System.out.println("\n--- Plano de asientos disponibles ---");
                        System.out.println("--------------");
                        System.out.println("  ESCENARIO");
                        System.out.println("--------------");
                 
                        System.out.println("\nZona VIP:");
                        mostrarPlano(zonaVip);
                 
                        System.out.println("\nZona Normal:");
                        mostrarPlano(zonaNormal);
                 
                        System.out.println("\nZona Palco:");
                        mostrarPlano(zonaPalco);
                        break;
 
                    case 3:
                        promocionesDisponibles();
                        break;
 
                    case 4:
                        modificarCompra(scanner);
                        break;
 
                    case 5:
                        if (!entradasCompradas.isEmpty()) {         //solo permitir pagos si hay entradas acumuladas
                            procesarPago(scanner);
                            continuar = false;
                        } else {
                            System.out.println("No hay compras realizadas. Por favor, compre sus entradas antes de proceder al pago.");
                        }
                        break;
 
                    case 6:
                        salirMenu(scanner);
                        continuar = false;
                        break;
                 
                    default:
                        System.out.println("Opción invalida");
                        scanner.next();
                        break;
                }
 
            } else {
 
                System.out.println("Opción invalida, por favor intente nuevamente");
                scanner.next();
            }
 
        }
 
        scanner.close();
         
    }
     
    //método para opción 6 - salir
    public static void salirMenu(Scanner scanner) {
        if (entradasCompradas.isEmpty()) {
            System.out.println("\nGracias por usar nuestro sistema. ¡Hasta luego!");
        } else {
            System.out.println("\nTiene compras pendientes de pago.");
            System.out.println("Se le redirigirá automáticamente al menú de pago.\n");
             
            procesarPago(scanner);
        }
    }

     public static void procesarPago(Scanner scanner) {
        System.out.println("\nSu compra es de " + entradaAcumulada + " entradas, por un total de $" + totalAcumulado);
        System.out.println("Seleccione el medio de pago:");
        System.out.println("1. Débito\n2. Crédito\n3. Transferencia\n4. Cancelar compra");
    
        int confirmaCompra;
        do {
            confirmaCompra = scanner.nextInt();
        } while (confirmaCompra < 1 || confirmaCompra > 5);
    
        switch (confirmaCompra) {
            case 1:
                generarBoleta();
                System.out.println("Pago con tarjeta de débito. Procesando...");
                Entrada nuevaEntrada = new Entrada(1, 2, 0, 0, 7000, 'A', false, 0.1);
                confirmarCompra(scanner, "Débito");
                break;
    
            case 2:
                generarBoleta();
                System.out.println("Pago con tarjeta de crédito.");
                System.out.print("Indique la cantidad de cuotas (1 a 12 cuotas): ");
                int cuotas = scanner.nextInt();
    
                if (cuotas < 1 || cuotas > 12) {
                    System.out.println("Número de cuotas seleccionado inválido.");
                } else {
                    System.out.println("Tu compra será cargada en tu tarjeta en " + cuotas + " cuotas.");
                }
                nuevaEntrada = new Entrada(1, 2, 0, 0, 7000, 'A', false, 0.1);
                confirmarCompra(scanner, "Crédito");
                break;
    
            case 3:
                generarBoleta();
                System.out.println("Pago mediante transferencia.");
                System.out.println("Recuerda que recibirás las instrucciones para la transferencia en tu correo.");
                nuevaEntrada = new Entrada(1, 2, 0, 0, 7000, 'A', false, 0.1);
                confirmarCompra(scanner, "Transferencia");
                break;
    
            case 4:
                System.out.println("Compra cancelada. Vuelve pronto.");
                System.exit(0); // Finaliza el programa
                break;
        }
    
        //preguntar si desea hacer otra compra
        System.out.println("\n¿Desea realizar otra compra?");
        System.out.println("1. Sí, quiero comprar más entradas.");
        System.out.println("2. No, quiero salir.");
    
        int opcion = scanner.nextInt();
    
        if (opcion == 1) {
            entradasCompradas.clear(); //limpiar la lista solo después del pago
            iniciarNuevaCompra(); //generará un nuevo ID para la proxima compra
            return;
            //System.out.println("\nRedirigiendo al menú principal...");
            //main(null); //reinicia el bucle para poder ejecutar nuevamente el menu
        } else {
            System.out.println("\nGracias por usar nuestro sistema. ¡Hasta luego!");
            System.exit(0); //cierra el programa
        }
    }
 
    public static double obtenerUltimoPrecio() {
         
        return precioBaseUltimaEntrada; 
    }
 
    //método de código opción 6 - pago
    public static void confirmarCompra(Scanner scanner, String metodoPago) {
        System.out.println("\nHas elegido el método de pago: " + metodoPago);
        System.out.println("Estamos procesando la compra...");
        
        try {
            Thread.sleep(2000);         //simulación de espera durante el procesamiento (2 segundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
        System.out.println("¡Compra confirmada!");
        System.out.print("Para finalizar, ingrese su correo: ");
        scanner.nextLine();         //limpiar entrada
        String correo = scanner.nextLine();

        System.out.println("\nSu boleta y entradas serán enviadas al correo " + correo);
        System.out.println("Gracias por usar nuestro sistema. ¡Hasta luego!");
    
    }
 
    //método para opción 4 - modificar compra
    public static void modificarCompra(Scanner scanner) {
        System.out.println("\n--- Modificar Compra ---");
        System.out.println("1. Cambiar entrada");
        System.out.println("2. Eliminar entrada");
        System.out.println("3. Agregar más entradas");
        System.out.println("4. Regresar al menú principal");
        System.out.print("Seleccione una opción: ");
     
        int opcion = scanner.nextInt();
     
        switch (opcion) {
            
            case 1: 
                cambiarEntrada(scanner); 
                break;
             
            case 2: 
                eliminarEntrada(scanner); 
                break;
            
            case 3: 
                agregarEntradas(scanner); 
                break;
            
            case 4:
                return; //regresa al menú principal
             
            default: System.out.println("Opción inválida. Intente nuevamente.");
        }
    }
 
    //método para cambio de entradas
    public static void cambiarEntrada(Scanner scanner) {
        if (entradasCompradas.isEmpty()) {
            System.out.println("No hay entradas compradas para cambiar.");
            return;
        }
     
        mostrarPlanoGeneral();
     
        // mostrar todas las entradas compradas
        System.out.println("\n--- Entradas Compradas ---");
        for (int i = 0; i < entradasCompradas.size(); i++) {
            System.out.println((i + 1) + ". " + entradasCompradas.get(i));
        }
     
        System.out.print("\nSeleccione el número de la entrada que desea cambiar: ");
        int indice = scanner.nextInt() - 1;
     
        if (indice >= 0 && indice < entradasCompradas.size()) {
            Entrada entrada = entradasCompradas.get(indice);
            // Liberar el asiento anterior
            boolean[][] antiguaZona = (entrada.getZonaSeleccionada() == 1) ? zonaVip 
                                      : (entrada.getZonaSeleccionada() == 2) ? zonaNormal : zonaPalco;
            antiguaZona[entrada.getFila()][entrada.getColumna()] = false; 
     
            // Solicitar la nueva zona y usar el método auxiliar para reservar el nuevo asiento.
            System.out.println("\nSeleccione una nueva zona (1. VIP / 2. Normal / 3. Palco):");
            int nuevaZona = scanner.nextInt();
            boolean[][] nuevaZonaActual = (nuevaZona == 1) ? zonaVip 
                                      : (nuevaZona == 2) ? zonaNormal : zonaPalco;
            double nuevoPrecioBase = (nuevaZona == 1) ? 20000 : (nuevaZona == 2) ? 7000 : 12000;
    
            // Mostrar el plano de la nueva zona
            mostrarPlano(nuevaZonaActual);
     
            // Reservar el nuevo asiento usando el método auxiliar
            int[] nuevoAsiento = reservarAsiento(scanner, nuevaZonaActual);
            int nuevaFila = nuevoAsiento[0];
            int nuevaColumna = nuevoAsiento[1];
            char nuevaFilaChar = (char) ('A' + nuevaFila);
     
            // Actualizar los datos de la entrada
            entrada.setPrecioBase(nuevaZona);
            entrada.setFila(nuevaFila);
            entrada.setColumna(nuevaColumna);
            entrada.setPrecioBase(nuevoPrecioBase);
            entrada.setFilaChar(nuevaFilaChar);
     
            System.out.println("Entrada modificada exitosamente.");
        } else {
            System.out.println("Selección inválida.");
        }
    }
 
    //método para eliminación de entradas
    public static void eliminarEntrada(Scanner scanner) {
        if (entradasCompradas.isEmpty()) {
            System.out.println("No hay entradas compradas para eliminar.");
            return;
        }
 
        //plano general
        mostrarPlanoGeneral();
     
        //mostrar todas las entradas compradas
        System.out.println("\n--- Entradas Compradas ---");
        for (int i = 0; i < entradasCompradas.size(); i++) {
            System.out.println((i + 1) + ". " + entradasCompradas.get(i));
        }
     
        System.out.print("\nSeleccione el número de la entrada que desea eliminar: ");
        int indice = scanner.nextInt() - 1;
     
        if (indice >= 0 && indice < entradasCompradas.size()) {
            Entrada entrada = entradasCompradas.get(indice);
            boolean[][] zonaActual = (entrada.getZonaSeleccionada() == 1) ? zonaVip : (entrada.getZonaSeleccionada() == 2) ? zonaNormal : zonaPalco;
            zonaActual[entrada.getFila()][entrada.getColumna()] = false; //liberar asiento ocupado
            totalAcumulado -= entrada.getPrecioBase(); //ajustar total acumulado
    
            entradasCompradas.remove(indice); //eliminar entrada de la lista
            System.out.println("Entrada eliminada exitosamente.");
        } else {
            System.out.println("Selección inválida.");
        }
    }
 
    //método para agrear entradas a una reserva vigente (con un limite de 5 entradas en el total de compra)
    public static void agregarEntradas(Scanner scanner) {
        if (entradasCompradas.size() >= 5) {
            System.out.println("Ya ha alcanzado el límite de 5 entradas por compra.");
            return;
        }
         
        // Mostrar el plano general.
        mostrarPlanoGeneral();
         
        System.out.println("¿Cuántas entradas desea agregar? (Máximo " + (5 - entradasCompradas.size()) + ")");
        int cantidad = scanner.nextInt();
         
        if (cantidad > 0 && (entradasCompradas.size() + cantidad) <= 5) {
            for (int i = 0; i < cantidad; i++) {
                System.out.println("\n--- Entrada #" + (entradasCompradas.size() + 1) + " ---");
                boolean compraExitosa = false;
                 
                while (!compraExitosa) {
                    System.out.println("\nEntradas disponibles:");
                    System.out.println("   Entrada - Precio");
                    System.out.println("--------------------");
                    System.out.println("1. VIP       $20.000");
                    System.out.println("2. Normal    $ 7.000");
                    System.out.println("3. Palco     $12.000");
                    System.out.println("--------------------");
                    System.out.print("Seleccione una zona: ");
        
                    int zonaSeleccionada = scanner.nextInt();
                    boolean[][] zonaActual = null;
                    double precioBase = 0;
         
                    if (zonaSeleccionada == 1) {
                        zonaActual = zonaVip;
                        precioBase = precioVip;
                        System.out.println("\nEntrada VIP seleccionada.");
                    } else if (zonaSeleccionada == 2) {
                        zonaActual = zonaNormal;
                        precioBase = precioNormal;
                        System.out.println("\nEntrada normal seleccionada.");
                    } else if (zonaSeleccionada == 3) {
                        zonaActual = zonaPalco;
                        precioBase = precioPalco;
                        System.out.println("\nEntrada palco seleccionada.");
                    } else {
                        System.out.println("Selección inválida. Intente nuevamente.");
                        continue;
                    }
         
                    // Mostrar el plano correspondiente.
                    mostrarPlano(zonaActual);
         
                    // Llamar al método auxiliar para reservar el asiento.
                    int[] asiento = reservarAsiento(scanner, zonaActual);
                    int fila = asiento[0];
                    int columna = asiento[1];
                    char filaChar = (char) ('A' + fila);
         
                    double descuentoLocal = 0;
                    boolean esReserva = false; // Por defecto, es una venta finalizada

                    Entrada nuevaEntrada = new Entrada(idVentaGlobal, zonaSeleccionada, fila, columna, precioBase, filaChar, esReserva, descuentoLocal);
                    entradasCompradas.add(nuevaEntrada);
                    totalAcumulado += precioBase;
                    entradaAcumulada++;
                    compraExitosa = true;
         
                    System.out.println("Entrada agregada exitosamente.");
                }
            }
        } else {
            System.out.println("Cantidad inválida. Intente nuevamente.");
        }
    }
 
    //menu principal
    public static void menu() {
        System.out.println("\n--- Menu principal ---");
        System.out.println("1. Selección entradas entradas");
        System.out.println("2. Ver asientos disponibles");
        System.out.println("3. Promociones disponibles");
        System.out.println("4. Modificar compra");
        System.out.println("5. Pagar");
        System.out.println("6. Salir");
        System.out.println("8. Ver registro compras"); //provisorio
        System.out.println("Seleccione una de las opciones disponibles: ");
    }
 
    public static void mostrarDetalleCompra() {
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
 
    //método para la selección de asiento y calculo de precios 
    public static void seleccionarEntradas(Scanner scanner) {

        /* este nuevo bloque de código verifica que no exista una transacción pendiente, de esta forma evitamos que el cliente
        pueda repetir el proceso de compra de forma indefinida y burlar el limite de entradas establecido */
        if (!entradasCompradas.isEmpty()) { //si hay entradas en la lista, obliga al pago antes de otra compra
            System.out.println("\nTiene una compra pendiente de pago.");
            procesarPago(scanner); // Redirigir al proceso de pago
            return; // Bloquear la nueva compra hasta que se complete el pago
        }
    
        System.out.println("¿Cuántas entradas desea comprar? (Máximo 5)");
        int cantidad = scanner.nextInt();
     
        if (cantidad > 0 && cantidad <= 5) {
            for (int i = 0; i < cantidad; i++) {
                System.out.println("\n--- Entrada #" + (i + 1) + " ---");
                boolean compraExitosa = false;
         
                while (!compraExitosa) {
                    System.out.println("\nEntradas disponibles:");
                    System.out.println("   Entrada - Precio");
                    System.out.println("--------------------");
                    System.out.println("1. VIP       $20.000");
                    System.out.println("2. Normal    $ 7.000");
                    System.out.println("3. Palco     $12.000");
                    System.out.println("--------------------");
                    System.out.print("Seleccione una zona: ");
         
                    int zonaSeleccionada = scanner.nextInt();
                    boolean[][] zonaActual = null;
                    double precioBase = 0;
         
                    if (zonaSeleccionada == 1) {
                        zonaActual = zonaVip;
                        precioBase = precioVip;
                        System.out.println("\nEntrada VIP seleccionada.");
                    } else if (zonaSeleccionada == 2) {
                        zonaActual = zonaNormal;
                        precioBase = precioNormal;
                        System.out.println("\nEntrada normal seleccionada.");
                    } else if (zonaSeleccionada == 3) {
                        zonaActual = zonaPalco;
                        precioBase = precioPalco;
                        System.out.println("\nEntrada palco seleccionada.");
                    } else {
                        System.out.println("Selección inválida. Intente nuevamente.");
                        continue;
                    }
         
                    // Mostrar el plano de asientos para la zona seleccionada.
                    mostrarPlano(zonaActual);
         
                    // Llamar al método auxiliar para reservar el asiento.
                    int[] asiento = reservarAsiento(scanner, zonaActual);
                    int fila = asiento[0];
                    int columna = asiento[1];
                    char filaChar = (char) ('A' + fila);
         
                    // Solicitar la edad para determinar el descuento.
                    System.out.print("Ingrese su edad: ");
                    if (scanner.hasNextInt()) {
                        int edad = scanner.nextInt();

                        double descuentoAplicado = (precioBase - calcularDescuento(edad, precioBase)) / precioBase;
                        double precioFinalCalculado = calcularDescuento(edad, precioBase);
         
                        // Crear y añadir la entrada a la lista.
                        int idVenta = entradasCompradas.size() + 1; // Generar un ID de venta único
                        boolean esReserva = false; // Indicar si es una reserva

                        Entrada nuevaEntrada = new Entrada(idVenta, zonaSeleccionada, fila, columna, precioBase, filaChar, esReserva, descuentoAplicado);
                        entradasCompradas.add(nuevaEntrada);
                        totalAcumulado += precioFinalCalculado;
                        entradaAcumulada++;
                        compraExitosa = true;
                    } else {
                        System.out.println("Edad inválida. Intente nuevamente.");
                        scanner.next(); // Limpiar entrada no válida.
                    }
                }
         
                System.out.println("Entrada #" + (i + 1) + " reservada exitosamente.");
            }
         
            // Mostrar el detalle completo de la compra.
            mostrarDetalleCompra();
        } else {
            System.out.println("Cantidad inválida. Intente nuevamente.");
        }
    }
 
    //método para la opción 2 - ver asientos disponibles
    public static void mostrarPlano(boolean[][] zona) {
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
 
    //plano general del teatro
    public static void mostrarPlanoGeneral() {
        System.out.println("--------------");
        System.out.println("  ESCENARIO");
        System.out.println("--------------");
         
        System.out.println("\nZona VIP:");
        mostrarPlano(zonaVip);
    
        System.out.println("\nZona Normal:");
        mostrarPlano(zonaNormal);
    
        System.out.println("\nZona Palco:");
        mostrarPlano(zonaPalco);
    }

    static List<String> promociones = List.of(
    "10% de descuento para estudiantes.",
    "15% de descuento para personas de la tercera edad."
    );

    //método para la opción 3 - promociones
    static void promocionesDisponibles() {
        System.out.println("\n--- Promociones Disponibles ---");
        promociones.forEach(System.out::println);
    }
 
    //método para generar boleta de compra
    public static void generarBoleta() { //BREAKPOINT: agregar un punto para verificar que las entradas y sus valores se cargaron correctamente
        
        System.out.println("\n--------------- BOLETA ---------------");
        System.out.println("                Nº 000" + idVentaGlobal);
        System.out.println("             TEATRO MORO");
        System.out.println(" SHOW: De vuelta a clases con el GOTH");
        System.out.println("--------------------------------------");
     
        double totalNeto = 0;
        double totalIVA = 0;
        double totalFinal = 0;
     
        for (int i = 0; i < entradasCompradas.size(); i++) {
            Entrada entrada = entradasCompradas.get(i);
            String zona = (entrada.getZonaSeleccionada() == 1) ? "VIP" : (entrada.getZonaSeleccionada() == 2) ? "Normal" : "Palco";
    
            double precioBase = (entrada.getZonaSeleccionada() == 1) ? 20000 : 
                            (entrada.getZonaSeleccionada() == 2) ? 7000 : 12000; //precio sin descuento
            double ivaPorEntrada = entrada.getPrecioBase() * 0.19;
            double precioNetoEntrada = entrada.getPrecioBase() - ivaPorEntrada;
     
            System.out.println("Entrada #" + (i + 1));
            System.out.println("Zona         : " + zona);
            System.out.println("Asiento      : " + entrada.getFilaChar() + (entrada.getColumna() + 1));
            System.out.println("Precio Base  : $" + entrada.getPrecioBase());
            System.out.println("Descuento    : " + (entrada.getDescuentoAplicado() * 100) + "%");
            System.out.println("Precio final : $" + totalFinal);
     
            System.out.println("--------------------------------------");
     
            totalNeto += precioNetoEntrada;
            totalIVA += ivaPorEntrada;
            totalFinal += entrada.getPrecioBase() * (1 - entrada.getDescuentoAplicado());
        }
     
        System.out.println("-------- RESUMEN DE LA COMPRA --------");
        System.out.println("Subtotal (Neto): $" + totalNeto);
        System.out.println("IVA (19%)      : $" + totalIVA);
        System.out.println("TOTAL          : $" + totalFinal);
        System.out.println("--------------------------------------");
        System.out.println("¡Gracias por tu compra!");
    }
 
    //método para reserva de asientos
    private static int[] reservarAsiento(Scanner scanner, boolean[][] zonaActual) {
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

    public static void agregarVenta(int idVenta, int zona, int fila, int columna, double precio, char filaChar, int idCliente, double descuentoAplicado) {
        Entrada nuevaVenta = new Entrada(idVenta, zona, fila, columna, precio, filaChar, false, descuentoAplicado);
        entradasCompradas.add(nuevaVenta);
        System.out.println("Venta registrada: " + nuevaVenta);
    }

    public static void agregarReserva(int idReserva, int zona, int fila, int columna, double precio, char filaChar, int idCliente) {
        Entrada nuevaReserva = new Entrada(idReserva, zona, fila, columna, precio, filaChar, true, 0);
        reservasAsientos.add(nuevaReserva);
        System.out.println("Reserva registrada: " + nuevaReserva);
    }

    public static void confirmarReserva(int idReserva, double descuentoAplicado) {
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

    public static void iniciarNuevaCompra() {
        idVentaGlobal++; //incrementar el id al iniciar una nueva compra desde el menu para diferenciar cada venta
    }

    //método para el calculo del descuento
    public static double calcularDescuento(int edad, double precioBase) {
        double descuento = 0.0;

        if (edad >= 60) {
            descuento = 0.15; // 15% de descuento para tercera edad
        } else if (edad >= 18 && edad <= 25) {
            descuento = 0.10; // 10% de descuento para estudiantes
        }

        return precioBase * (1 - descuento); // Aplicar descuento al precio base
    }

     
 }