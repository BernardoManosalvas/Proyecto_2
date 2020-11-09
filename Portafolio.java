/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_bolsavalores;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alejandro
 */
public class Portafolio {
    private Mercado mercado; // Pertenece a un mercado
    private static Fecha fecha;
    private double capitalOriginal;
    private double capitalDisp;
    private double inversionTotal;
    private double valorActualTotal;
    private double rentabilidadTotal;

    private ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
    private ArrayList<EmpresaAccion> empresas = new ArrayList <>();


    // Porcentaje

    public Portafolio(){}

    public Portafolio (Mercado mercado){
        this.mercado = mercado;
        this.fecha = mercado.getFecha();
    }

    public void inicializarPortafolio(){
        System.out.print("Ingrese el capital original del Portafolio: ");
        do{
            Scanner input = new Scanner(System.in);
            double capOriginal = input.nextDouble(); System.out.println("");
            setCapital(capOriginal);
        }while(capitalOriginal<=0);
    }


    private void setCapital(double capital){
        if(capital>0){
            this.capitalOriginal = capital;
            capitalDisp = capital;
        } else {
            System.out.println("Capital invalido");
            System.out.print("Porfavor ingrese otro valor: ");
        }
    }

    public void agregarEmpresa(Empresa empresa, int  numAcciones, double inversion){
        boolean enPortafolio = false;
        // Comprueba si ya existe la empresa en el portafolio
        for(EmpresaAccion emp : empresas){
            if(emp.getEmpresa() == empresa){
                enPortafolio = true;
                emp.actualizar(numAcciones, inversion);
            }
        }
        if(!enPortafolio){
            if(numAcciones >0 ){
                EmpresaAccion empresaNueva = new EmpresaAccion(empresa, numAcciones, inversion);
                empresas.add(empresaNueva);
            }
        }
    }


    public void comprarAcciones(){
        Scanner input = new Scanner(System.in);
        Transaccion compra;

        String info = "Capital Disponible $" + capitalDisp;
        info += "\nEmpresas disponibles en el mercado \n";
        info += mercado.empresasComoOpciones();
        System.out.println(info);

        System.out.print("Ingrese el numero de la empresa: ");
        int indexEmpresa = input.nextInt() - 1;
        Empresa empresa = mercado.getEmpresas().get(indexEmpresa);
        System.out.println("Valor por Accion de " + empresa.getNombre() + ": " + empresa.getpActual());
        System.out.print("Ingrese el numero de de acciones que quiere comprar: ");
        int numAcciones = input.nextInt(); System.out.println("");
        double inversion = empresa.getpActual()*numAcciones;

        if(numAcciones>0){
            if(capitalDisp>=inversion){
                if(!mismoPeriodo(empresa)){
                    capitalDisp = capitalDisp - inversion;
                    compra = new Transaccion(Transaccion.compra, empresa, numAcciones, this.capitalDisp);
                    transacciones.add(compra);
                    System.out.println("Transaccion exitosa\n");
                    this.agregarEmpresa(empresa, numAcciones, inversion);
                }else{
                    System.out.println("Ya compró acciones a " + empresa.getNombre() + " en este período.\n");
                }
            }else{
                System.out.println("No tiene el dinero suficiente\n");
            }
            this.calcInversionTotal();
        }else{
            System.out.println("Ingrese un numero de acciones valido\n");
        }
    }


    public void venderAcciones(){
        if(empresas.size()>0){
            Scanner input = new Scanner(System.in);
            Transaccion venta;
            consultarDatos();
            System.out.print("Ingrese el numero de la empresa: ");
            int indexEmpresa = input.nextInt() - 1;
            EmpresaAccion empA = empresas.get(indexEmpresa);
            Empresa emp = mercado.getEmpresas().get(indexEmpresa);
            System.out.println("Valor por Accion de " + empA.getEmpresa().getNombre() + ": " + empA.getEmpresa().getpActual());
            System.out.print("Ingrese el numero de de acciones que quiere vender: ");
            int numAcciones = input.nextInt(); System.out.println("");

            if(numAcciones>0){
                if(empA.getTotalAcciones()>=numAcciones){
                    double inversion = emp.getpActual()*numAcciones;
                    capitalDisp = capitalDisp + inversion;
                    if(numAcciones>0){
                        venta = new Transaccion(Transaccion.venta, emp, numAcciones, this.capitalDisp);
                        transacciones.add(venta);
                        empA.actualizar(-numAcciones, -inversion);
                    }
                    if(empA.getTotalAcciones() ==0){
                        empresas.remove(indexEmpresa);
                    }



                    System.out.println("Transaccion exitosa\n");
                }else{
                    System.out.println("No puede vender mas acciones de las que posee");
                }
            }else{
                System.out.println("Ingrese un numero valido de acciones");
            }
        }else {
            System.out.println("No tiene acciones para vender\n");
        }
        this.calcInversionTotal();
    }


    private boolean mismoPeriodo(Empresa empresa){
        for(Transaccion trans : transacciones){
            if(trans.getPeriodo().equals(fecha.toString()) && trans.getEmpresa() == empresa.getNombre()){
                return true;
            }
        }
        return false;
    }


    public void consultarDatos(){
        this.calcInversionTotal();
        this.calcValorActualTotal();
        this.calcRentabilidadTotal();
        if(empresas.size()>0){
            System.out.printf("%-50s %-80s %n", "PORTAFOLIO", "Fecha:" + mercado.getFecha());
            System.out.printf("%-5s %-20s %-10s %-20s %-20s %-20s %-20s  %-20s %n",
                    "#", "Empresa", "Num.", "$ Inversion", "$ Valor accion", "$ Valor actual", "$ Rentabilidad", "%\n");

            int i =1;
            for(EmpresaAccion emp: empresas){
                String nombreEmpresa = emp.getEmpresa().getNombre();
                double valorAccion = emp.getEmpresa().getpActual();
                double inversion = emp.getInversionTotal();
                int totalAcciones = emp.getTotalAcciones();
                double valorActual = valorAccion * totalAcciones;
                double rentabilidad = emp.rentabilidad(valorActual);
                double porcentajeRentabildad = emp.rentabilidadP(valorActual);
                System.out.printf("%-5d %-20s %-10s %-20.2f %-20.2f %-20.2f %-20.2f %-20.2f", i, nombreEmpresa, totalAcciones, inversion,
                        valorAccion, valorActual, rentabilidad, porcentajeRentabildad);System.out.println();
                i += 1;
            }
            System.out.printf("%-37s %-20.2f %-20s %-20.2f %-20.2f %-20.2f %n",  "Inversion Total: " ,
                    this.inversionTotal , "Portafolio: " ,  this.valorActualTotal , this.rentabilidadTotal,
                    this.rentabilidadTotal/this.inversionTotal*100 );
            System.out.printf("%-37s %-20.2f %n%n", "Capital disponible" , this.capitalDisp);
        }else {
            System.out.println("No posee acciones para mostrar datos");
        }
        System.out.println("");
    }


    public void mostrarTransacciones(){

        if(transacciones.size()>0){
            System.out.printf("%-60s %-45s %n", "Transacciones", "Capital Original: $" + capitalOriginal);
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %n",
                    "Periodo", "Tipo", "Empresa", "Num", "$ Valor Accion", "$ Inversion", "$ Saldo");

            for(Transaccion trans : transacciones){
                System.out.printf("%-20s %-20s %-20s %-20d %-20.2f %-20s % -20.2f %n", trans.getPeriodo(), trans.getTipo(), trans.getEmpresa(),
                        trans.getCantidad(), trans.getValorAccion(), trans.getInversion(), trans.getSaldo());
            }
        }else{
            System.out.println("Aun no ha realizado transacciones para mostrar datos\n");
        }
        System.out.println("");
    }


    public void mostrarAccionesEmpresas(){
        System.out.println("Acciones por empresa");
        for(EmpresaAccion emp : empresas){
            System.out.println(emp.toString());
        }
    }


    public ArrayList<EmpresaAccion> getEmpresas(){ return  empresas; }

    public ArrayList<Transaccion> getTransacciones(){ return  transacciones; }



    public void calcInversionTotal(){
        this.inversionTotal = this.capitalOriginal - this.capitalDisp;
    }

    public void calcValorActualTotal(){
        this.valorActualTotal = 0;
        for(EmpresaAccion emp: this.empresas){
            this.valorActualTotal += emp.getEmpresa().getpActual() * emp.getTotalAcciones();
        }
    }

    public void calcRentabilidadTotal(){
        this.rentabilidadTotal = this.valorActualTotal -  this.inversionTotal;

    }
}

