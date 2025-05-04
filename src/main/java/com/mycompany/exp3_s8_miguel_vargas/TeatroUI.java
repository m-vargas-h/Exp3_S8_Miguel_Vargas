
package com.mycompany.exp3_s8_miguel_vargas;

import java.util.Scanner;

public class TeatroUI {
    private EntradaManager entradaManager;
    private Scanner scanner;

    public TeatroUI(EntradaManager entradaManager) {
        this.entradaManager = entradaManager;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean continuar = true;
        System.out.println("---------------------");
        System.out.println("     TEATRO MORO");
        System.out.println("---------------------");
        System.out.println("Bienvenido a nuestro sistema de compra");

        while (continuar) {
            menu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            continuar = procesarOpcion(opcion);
        }
    }

    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> seleccionarEntradas();
            case 2 -> mostrarPlanoGeneral();
            case 3 -> promocionesDisponibles();
            case 4 -> modificarCompra();
            case 5 -> procesarPago();
            case 6 -> {
                salirMenu();
                return false;
            }
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
        return true;
    }

    private void salirMenu() {
        if (entradaManager.obtenerEntradasCompradas().isEmpty()) {
            System.out.println("\nGracias por usar nuestro sistema. ¡Hasta luego!");
        } else {
            System.out.println("\nTiene compras pendientes de pago.");
            System.out.println("Se le redirigirá automáticamente al menú de pago.");
            procesarPago();
        }
    }

    private void procesarPago() {
        entradaManager.procesarPago(scanner);
    }

    private void modificarCompra() {
        entradaManager.modificarCompra(scanner);
    }

    private void seleccionarEntradas() {
        entradaManager.seleccionarEntradas(scanner);
    }

    private void mostrarPlanoGeneral() {
        System.out.println("\n--- Plano de asientos disponibles ---");
        System.out.println("--------------");
        System.out.println("  ESCENARIO");
        System.out.println("--------------");

        System.out.println("\nZona VIP:");
        entradaManager.mostrarPlano(entradaManager.getZonaVip());

        System.out.println("\nZona Normal:");
        entradaManager.mostrarPlano(entradaManager.getZonaNormal());

        System.out.println("\nZona Palco:");
        entradaManager.mostrarPlano(entradaManager.getZonaPalco());
    }

    private void promocionesDisponibles() {
        entradaManager.promocionesDisponibles();
    }

    private void menu() {
        System.out.println("\n--- Menu principal ---");
        System.out.println("1. Comprar entradas");
        System.out.println("2. Ver asientos disponibles");
        System.out.println("3. Promociones disponibles");
        System.out.println("4. Modificar compra");
        System.out.println("5. Pagar");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }
}