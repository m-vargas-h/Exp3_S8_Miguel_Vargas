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
          // Instanciar el sistema de gestión de entradas
          EntradaManager entradaManager = new EntradaManager();
  
          // Pasar la gestión de entradas a la interfaz de usuario
          TeatroUI teatroUI = new TeatroUI(entradaManager);
          
          // Iniciar la interacción con el usuario
          teatroUI.mostrarMenu();
      }
  }