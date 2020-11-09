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
public class Fecha {
    private static final int [] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String [] meses = {"ene",  "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"};
    private int dia;
    private int mes;
    private String fechaS;

    public Fecha(int dia, int mes){
        this.setMes(mes);
        this.setDia(dia);
        calcularFecha();
    }

    @Override
    public String toString(){
        return fechaS;
    }

    public void cambiarPeriodo(){
        int diasDelMes = dias[mes-1];
        if(dia+7>diasDelMes){
            dia =  7 - (diasDelMes - dia);
            if(mes<12){
                mes += 1;
            }else if(mes == 12){ //Diciembre
                mes = 0;
            }

        }else{
            dia = dia + 7;
        }
        calcularFecha();

    }

    public void calcularFecha(){
        if(dia/10 == 0){
            fechaS = "0" + dia + "-" + meses[mes-1];
        }else{
            fechaS = dia + "-" + meses[mes-1];
        }

    }

    public void setMes(int mes) {
        if(mes>0 && mes<=12){
            this.mes = mes;
        }else{
            mes = 1;
        }
    }

    public void setDia(int dia) {
        if(dia>0 && dia<=dias[mes-1]){
            this.dia = dia;
        }else{
            dia = 0;
        }



    }




}
