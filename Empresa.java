/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_bolsavalores;

import java.util.ArrayList;

/**
 *
 * @author alejandro
 */
public class Empresa implements Comparable<Empresa> {
    private static Fecha periodo;
    private String nombre;
    private double pOriginal;
    private double pActual;
    private ArrayList <Historial> historial;
    private double pMax = 0;


    public Empresa(){
        historial = new ArrayList <Historial>();
    }


    public Empresa(String nombre, double pOriginal){
        this.nombre = nombre;
        this.setPOriginal(pOriginal);
        this.pActual = this.pOriginal;
        historial = new ArrayList <Historial>();
        this.cambiarPeriodo();

    }

    public void setPOriginal(double pOriginal){
        if(pOriginal>=0){
            this.pOriginal = pOriginal;
        }
        else{
            this.pOriginal = 0;
        }
    }


    public void mostrarInfo(){
        if(historial.size()!=0){
            Historial hActual = historial.get(historial.size()-1);
            System.out.printf("%-10s \t$ %10s \t$ %10s \t$ %10s \t$ %10s \t %10s %n",
                    nombre, pOriginal, hActual.getpInicial() , hActual.getpActual(),
                    hActual.getVariacion(), hActual.getVariacionP() + " %");
        }else{
            System.out.printf("%-10s \t$ %10s \t$ %10s \t$ %10s \t$ %10s \t %10s %n",
                    nombre, pOriginal, pOriginal , "n/d",
                    "n/d", "n/d");
        }
    }


    public void mostrarHistorial(){
        System.out.println("HISTORIAL");
        System.out.printf("%-50s %-30s %n", nombre, "Original: " + pOriginal);
        System.out.printf("%-7s %-10s \t %-10s \t %-10s \t %-10s \t\t %-10s %n",
                "", "Fecha", "Inicio", "Actual", "Variacion", " %");

        for(int i=0; i<historial.size(); i++){
            System.out.printf("%-5d", i+1);
            historial.get(i).mostrarInfo();
        }
    }


    public void mostrarHistograma(){
        System.out.println("HISTOGRAMA");
        String barra = "";
        int totalX = 0;
        for(Historial historialTemp : historial){
            System.out.printf("%8s", historialTemp.getPeriodo());
            System.out.printf("%15.2f", historialTemp.getpInicial());
            totalX = (int) (historialTemp.getpInicial()/pMax *50); // Maximo 50 "x"
            for(int i=0; i<totalX; i++){
                barra += "X";
            }
            System.out.printf("%10s", "  ");
            System.out.printf("%-1s",  barra);
            System.out.println("");
            barra = "";
        }

    }


    public void cambiarPeriodo(){
        double pInicial, variacion, variacionP; // Porcentual
        pInicial = pActual;
        variacionP = Math.random()*19.99;
        if(Math.random()<0.5){
            variacionP = -variacionP;
        }
        variacion = pInicial*variacionP/100;
        pActual += variacion;
        if(pActual >pMax){
            pMax = pActual;
        }
        Historial historialTemp = new Historial(periodo.toString(), pInicial, pActual, variacion, variacionP);
        historial.add(historialTemp);
    }


    @Override
    public int compareTo(Empresa emp) {
        return this.getNombre().compareTo(emp.getNombre());
    }

    public static void setPeriodo(Fecha periodo) {
        Empresa.periodo = periodo;
    }

    public static Fecha getPeriodo() {
        return periodo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getpOriginal() {
        return pOriginal;
    }

    public double getpActual() {
        return pActual;
    }


}
