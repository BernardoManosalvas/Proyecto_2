/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_bolsavalores;

import java.util.Scanner;

/**
 *
 * @author alejandro
 */
public class Proyecto1_BolsaValores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        menu();
    }

    /*
    public static void test(){
        Mercado mercadoTec = new Mercado();
        //Portafolio portafolio = new Portafolio();


        Empresa microsoft = new Empresa("Microsoft", 130000);
        Empresa apple = new Empresa("Apple", 180000);
        Empresa windows = new Empresa("Windows", 175000);
        Empresa sony = new Empresa ("Sony", 800000);

        mercadoTec.agregarEmpresas(microsoft, apple, windows, sony);
        mercadoTec.cambiarPeriodo(12);
        mercadoTec.listarEmpresas();     System.out.println("");System.out.println("");
        mercadoTec.consultarEmpresas();

    }
    */



    public static void menu(){
        System.out.println("Bienvenido a la bolsa de valores");
        Mercado mercado = new Mercado();
        mercado.inicializarMercado();
        Portafolio portafolio = new Portafolio(mercado);
        portafolio.inicializarPortafolio();



        Scanner input = new Scanner(System.in);
        int opcion = 0;
        do{
            System.out.println("MENU");
            String t = "";
            t += "1. Crear empresas \n";
            t += "2. Consultar la lista de todas las empresas disponibles en el mercado. \n";
            t += "3. Consultar los datos de una empresa (historial de precios e mostrarHistograma) \n";
            t += "4. Consultar los datos del portafolio con todas las transacciones y los datos de rentabilidad. \n";
            t += "5. Comprar acciones. \n";
            t += "6. Vender acciones. \n" ;
            t += "7. Cambiar de períodos de tiempo. \n";
            t += "8. Salir. ";


            System.out.println(t);
            System.out.print("Escoja una opción: ");
            opcion = input.nextInt(); System.out.println("");
            switch(opcion){
                case 1:
                    mercado.crearEmpresa();
                    break;

                case 2:
                    mercado.listarEmpresas();
                    break;

                case 3:
                    mercado.consultarHistorialEmpresas();
                    break;

                case 4:
                    System.out.println("Datos del portafolio \n");
                    portafolio.consultarDatos();
                    portafolio.mostrarTransacciones();
                    //portafolio.mostrarAccionesEmpresas();
                    break;
                case 5:
                    System.out.println("Comprar acciones \n");
                    portafolio.comprarAcciones();

                    break;
                case 6:
                    System.out.println("Vender acciones \n");
                    portafolio.venderAcciones();
                    break;

                case 7:
                    mercado.cambiarPeriodo();
                    System.out.println("NUEVO PERIODO " + mercado.getFecha() + "\n");

                    break;

                case 8:
                    break;


                default:
                    System.out.println("Ingrese una opción válida");
            }
        }while(opcion!=8);

    }
    /*
    Pattern expression = Pattern.compile("regex");

    */


}
