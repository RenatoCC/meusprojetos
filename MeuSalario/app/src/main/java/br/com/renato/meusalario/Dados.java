package br.com.renato.meusalario;


public class Dados {

    double valor,saldo,soma,salario;
    String despesa,data,sinal;

    public Dados() {

    }

//    public Dados(double salario){
//
//        this.salario = salario;
//    }



    public Dados(double valor, String despesa, String data, double soma, String sinal,double salario){

        this.valor = valor;
        this.despesa = despesa;
        this.data = data;
        this.soma = soma;
        this.sinal = sinal;
        this.salario = salario;
    }

    public Dados(double salario) {
        this.salario = salario;

    }


    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public String getSinal() {
        return sinal;
    }

    public void setSinal(String sinal) {
        this.sinal = sinal;
    }

    public double getSoma() {
        return soma;
    }

    public void setSoma(double soma) {
        this.soma = soma;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDespesa() {
        return despesa;
    }

    public void setDespesa(String despesa) {
        this.despesa = despesa;
    }
}


