/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp3_s8_miguel_vargas;

/**
 *
 * @author mvarg
 */
public class Entrada {
    private int idVenta;
    private int zonaSeleccionada;
    private int fila;
    private int columna;
    private double precioBase;
    private char filaChar;
    private boolean esReserva;
    private double descuentoAplicado;

    public Entrada(int idVenta, int zonaSeleccionada, int fila, int columna, double precioBase, char filaChar, boolean esReserva, double descuentoAplicado) {
        this.idVenta = idVenta;
        this.zonaSeleccionada = zonaSeleccionada;
        this.fila = fila;
        this.columna = columna;
        this.precioBase = precioBase;
        this.filaChar = filaChar;
        this.esReserva = esReserva;
        this.descuentoAplicado = descuentoAplicado;
    }

    public boolean isReserva() {
        return esReserva;
    }

    public String getEntradaActualizada() {
        return "Venta ID: " + idVenta + " | Zona: " + zonaSeleccionada + " | Asiento: " + filaChar + (columna + 1) +
               " | Precio final: $" + (precioBase * (1 - descuentoAplicado));
    }

    //retorna la zona del siento
    public int getZonaSeleccionada() {
        return zonaSeleccionada;
    }

    //devuelve la fila del asiento como un carácter
    public char getFilaChar() {
        return filaChar;
    }

    //devuelve el indice de la fila
    public int getFila() {
        return fila;
    }
    
    //devuelve el indice de la columna
    public int getColumna() {
        return columna;
    }

    //obtiene el precio base de la entrada según la selección del usuario
    public double getPrecioBase() {
        return precioBase;
    }

    //devuelve el porcentaje de descuento aplicado
    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    //devuelve el identificador de la venta
    public int getIdVenta() {
        return idVenta;
    }

    //modifica la zona de la entrada seleccionada
    public void setZonaSeleccionada(int nuevaZona) {
        this.zonaSeleccionada = nuevaZona;
    }
    
    //modifica la posición de la fila en la zona
    public void setFila(int nuevaFila) {
        this.fila = nuevaFila;
    }
    
    //modifica la posición de la columna en la zona
    public void setColumna(int nuevaColumna) {
        this.columna = nuevaColumna;
    }
    
    //modifica el precio de la entrada seleccionada
    public void setPrecioBase(double nuevoPrecioBase) {
        this.precioBase = nuevoPrecioBase;
    }
    
    //modifica la fila, pero esta vez en formato de carácter (A, B, C...)
    public void setFilaChar(char nuevaFilaChar) {
        this.filaChar = nuevaFilaChar;
    }

    //modifica el estado de la entrada entre reservada o no
    public void setReserva(boolean esReserva) {
        this.esReserva = esReserva;
    }

    //modifica el porcentaje de descuento aplicado
    public void setDescuentoAplicado(double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    } 

    //muestra la información de la entrada de forma clara y detallada en el resumen de la operación
    @Override
    public String toString() {
        return "Venta ID: " + idVenta + " | Zona: " + zonaSeleccionada + " | Asiento: " + filaChar + (columna + 1) +
               " | Precio final: $" + (precioBase * (1 - descuentoAplicado)) + " | Descuento aplicado: " + (descuentoAplicado * 100) + "%";
    }
}
