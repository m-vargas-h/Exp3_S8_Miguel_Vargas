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

    public int getZonaSeleccionada() {
        return zonaSeleccionada;
    }

    public char getFilaChar() {
        return filaChar;
    }

    public int getFila() {
        return fila;
    }
    
    public int getColumna() {
        return columna;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setZonaSeleccionada(int nuevaZona) {
        this.zonaSeleccionada = nuevaZona;
    }
    
    public void setFila(int nuevaFila) {
        this.fila = nuevaFila;
    }
    
    public void setColumna(int nuevaColumna) {
        this.columna = nuevaColumna;
    }
    
    public void setPrecioBase(double nuevoPrecioBase) {
        this.precioBase = nuevoPrecioBase;
    }
    
    public void setFilaChar(char nuevaFilaChar) {
        this.filaChar = nuevaFilaChar;
    }

    public void setReserva(boolean esReserva) {
        this.esReserva = esReserva;
    }

    public void setDescuentoAplicado(double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    } 

    @Override
    public String toString() {
        return "Venta ID: " + idVenta + " | Zona: " + zonaSeleccionada + " | Asiento: " + filaChar + (columna + 1) +
               " | Precio final: $" + (precioBase * (1 - descuentoAplicado)) + " | Descuento aplicado: " + (descuentoAplicado * 100) + "%";
    }
}
