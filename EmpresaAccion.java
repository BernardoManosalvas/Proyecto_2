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
public class EmpresaAccion {
    private Empresa empresa;
    private int totalAcciones=0;
    private double inversionTotal=0;
    private double valorActual;

    public EmpresaAccion() { }

    public EmpresaAccion(Empresa empresa) {
        this.empresa = empresa;
    }

    public EmpresaAccion(Empresa empresa, int numAcciones, double inversion) {
        this.empresa = empresa;
        this.actualizar(numAcciones, inversion);
    }

    @Override
    public String toString(){
        String t = "";
        t += "Empresa: " + empresa.getNombre() + "\n";
        t += "Acciones: " + this.totalAcciones + "\n";
        t += "Inversion: " + this.inversionTotal + "\n";
        return t;
    }


    public void actualizar(int numAcciones, double inversion){
        this.totalAcciones += numAcciones;
        this.inversionTotal += inversion;
    }

    public double rentabilidad(double valorActual){
        return (valorActual-inversionTotal);
    }

    public double rentabilidadP(double valorActual){
        return ((valorActual-inversionTotal)/inversionTotal*100);

    }


    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void settotalAcciones(int numAcciones) {
        this.totalAcciones = numAcciones;
    }

    public void setInversionTotal(int inversionTotal) {
        this.inversionTotal = inversionTotal;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public int getTotalAcciones() {
        return totalAcciones;
    }

    public double getInversionTotal() {
        return inversionTotal;
    }

}
