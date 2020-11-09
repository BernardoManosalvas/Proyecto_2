/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_bolsavalores;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author alejandro
 */
public class Mercado {
    private ArrayList<Empresa> empresas;
    private Fecha fecha;

    public Mercado(){
        empresas = new ArrayList <Empresa>();
        fecha = new Fecha(4, 9);   // 4 de Septiembre
        Empresa.setPeriodo(fecha); //Inicializar clase empresa con la misma fecha que el mercado
        //Portafolio.setPeriodo(fecha);
    }


    public void inicializarMercado(){
        System.out.print("Ingrese el número de empresa que van a participar en este mercado: ");
        Scanner input = new Scanner(System.in);
        int totalEmpresas = input.nextInt(); System.out.println("");

        for(int i=0 ; i<totalEmpresas; i++){
            do{
                System.out.println("Empresa " + (i+1));
            }while(!crearEmpresa());
        }
        System.out.println("");
    }

    public boolean crearEmpresa(){
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese los datos de la empresa");
        System.out.print("Nombre de la empresa: ");
        String nombre = input.nextLine();
        System.out.print("Precio Original de la accion: ");
        double pOriginal = input.nextDouble(); System.out.println("");

        Empresa empresa;
        empresa = new Empresa(nombre, pOriginal);
        if(empresa.getpOriginal()>0){
            this.agregarEmpresa(empresa);
            return true;
        }else{
            System.out.println("Valor de accion debe ser positivo");
        }
        return false;
    }

    public void agregarEmpresa(Empresa empresa){
        empresas.add(empresa);
    }

    /*
    // Agregar cualquier numero de empresas nuevas
    public void agregarEmpresas(Empresa... empNuevas){
        for(Empresa emp : empNuevas){
            this.empresas.add(emp);
        }
    }
    */


    public void listarEmpresas(){
        if(empresas.size()!=0){
            System.out.println("Empresas disponibles en el mercado \n");
            Collections.sort(empresas);
            System.out.printf("%-31s %-60s %n", "Fecha:" + fecha, " Acciones");
            System.out.printf("%-10s \t %-10s \t %-10s \t %-10s \t %-10s \t\t %-10s %n",
                    "Empresa", " Original", "Inicio", " Actual", " Variacion", " %");

            for(Empresa empresa : empresas){
                empresa.mostrarInfo();
            }
            System.out.println("");

        }else{
            System.out.println("No hay empresas en el mercado");
        }

    }


    public void consultarHistorialEmpresas(){
        if(empresas.size()!=0){
            System.out.println("Consultar historial de empresas \n");
            Scanner input = new Scanner(System.in);
            int indexEmpresa;
            String t = "De qué empresa desea conocer los datos? \n";
            t += this.empresasComoOpciones();
            System.out.println(t);
            System.out.print("Escoja una opción: ");
            indexEmpresa = input.nextInt() - 1; System.out.println("");
            Empresa empresa = empresas.get(indexEmpresa);
            empresa.mostrarHistorial();
            System.out.println("\n");
            empresa.mostrarHistograma();
            System.out.println("");System.out.println("");

        }else{
            System.out.println("No hay empresas en el mercado");
        }
    }

    public void cambiarPeriodo(){
        fecha.cambiarPeriodo();
        for(Empresa empresa: empresas){
            empresa.cambiarPeriodo();
            if(empresa.getpActual()<=0){
                empresas.remove(empresa);
            }
        }
    }

    /*
    //Cambiar n períodos
    public void cambiarPeriodo(int n){
        for(int i=0; i<n; i++){
            fecha.cambiarPeriodo();
            for(Empresa empresa: empresas){
                empresa.cambiarPeriodo();
            }
        }
    }
*/
    public String empresasComoOpciones(){
        String t = "" ;
        for (int i=0; i<empresas.size(); i++){
            t += (i+1) + ". " + empresas.get(i).getNombre() + ". \n";
        }
        return t;

    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public  Fecha getFecha() {
        return fecha;
    }

}
