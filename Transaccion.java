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
public class Transaccion {
    private String periodo;
    private String tipo; // Compra / Venta
    private Empresa empresa;
    private int cantidad;
    private double valorAccion;
    private double inversion;
    private double saldo;
    public static final String compra = "COMPRA";
    public static final String venta = "VENTA";

    public Transaccion(){}

    public Transaccion( String tipo,  Empresa empresa, int numeroAcciones, double saldo){
        this.periodo = Empresa.getPeriodo().toString();
        this.setTipo(tipo);
        this.empresa = empresa;
        this.setCantidad(numeroAcciones);
        this.valorAccion = empresa.getpActual();
        this.setInversion();
        this.saldo = saldo;
    }

    public void setTipo(String tipo) {
        if(tipo.equals(Transaccion.compra)){
            this.tipo = Transaccion.compra;
        }
        else if(tipo.equals(Transaccion.venta)){
            this.tipo = Transaccion.venta;
        }
        else{
            System.out.println("Tipo no válido");
        }
    }

    private void setCantidad(int acciones){
        if(acciones>=0){
            cantidad = acciones;
        } else {
            System.out.println("Numero de acciones invalido");
        }
    }

    public void setInversion(){
        inversion = cantidad * valorAccion;
    }


    @Override
    public String toString(){
        String t = "";
        t += "Periodo: " + this.getPeriodo() + "\n";
        t+= "Tipo: " + this.getTipo() + "\n";
        t += "Empresa: " + this.getEmpresa() + "\n";
        t += "Cantidad: " + this.getCantidad() + "\n";
        t += "Valor accion : " + this.getValorAccion() + "\n";
        t += "Inversion: " + this.getInversion() + "\n";
        t += "Saldo: " + this.getSaldo() + "\n";
        return t;

    }

    public String getPeriodo() {
        return periodo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmpresa() {
        return empresa.getNombre();
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getValorAccion() {
        return valorAccion;
    }

    public String getInversion() {
        String s = ""+Math.floor(inversion*100)/100.0 ;
        if(tipo.equals(Transaccion.venta)){
            return "(" + s + ")";

        }

        return s;
    }

    public double getSaldo() {
        return saldo;
    }



}