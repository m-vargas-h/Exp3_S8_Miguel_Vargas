/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package com.mycompany.exp3_s8_miguel_vargas;

 /**
  * S8 - Optimizando listas y arreglos en Java
  * author Miguel Vargas
  */

  public class Exp3_S8_Miguel_Vargas {

    public static void main(String[] args) {

        //llamamos a la clase entradaManager para gestionar todo el proceso de compra
        EntradaManager entradaManager = new EntradaManager();
  
        //llamamos a la clase teatroUI para gestionar la interfaz 
        TeatroUI teatroUI = new TeatroUI(entradaManager);
          
        //llamamos al método mostrarMenu para comenzar la ejecución del programa
        teatroUI.mostrarMenu();

   
    }
  }