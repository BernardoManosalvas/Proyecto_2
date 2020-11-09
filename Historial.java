/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_bolsavalores;

/**
 *
 * @author alejandro
 */
public class Historial {
    private String periodo;
    private double pInicial;
    private double pActual;
    private double variacion;
    private double variacionP; // Porcentual

    public Historial(){}

    public Historial(String periodo, double pInicial, double pActual, double variacion, double variacionP){
        this.setPeriodo(periodo);
        this.setpInicial(pInicial);
        this.setpActual(pActual);
        this.setVariacion(variacion);
        this.setVariacionP (variacionP);

    }

    public void mostrarInfo(){
        System.out.printf("%10s \t$ %10s \t$ %10s \t$ %10s \t %10s %n",
                periodo, pInicial, pActual, variacion, variacionP + " %");

    }


    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setpInicial(double pInicial) {
        this.pInicial = approx(pInicial);
    }

    public void setpActual(double pActual) {
        this.pActual = approx(pActual);
    }

    public void setVariacion(double variacion) {
        this.variacion = approx(variacion);
    }

    public void setVariacionP(double variacionP) {
        this.variacionP = approx(variacionP);
    }

    public String getPeriodo() {
        return periodo;
    }

    public double getpInicial() {
        return pInicial;
    }

    public double getpActual() {
        return pActual;
    }

    public double getVariacion() {
        return variacion;
    }

    public double getVariacionP() {
        return variacionP;
    }
    public double approx(double x){
        double approx_x;
        approx_x = Math.floor(x*100)/100.0;
        return  approx_x;
    }


}
