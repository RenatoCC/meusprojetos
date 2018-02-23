

public class Calculo {

    int i = 1;
    int v = 5;
    int x = 10;
    int l = 50;
    int c = 100;
    int d = 500;
    int m = 1000;
    int resultado;
    int v1,v2;
    String operador;

    public Calculo(){

    }
    public Calculo(int i, int v, int x, int l, int c, int d, int m, int v1, int v2, int resultado,String operador) {
        this.i = i;
        this.v = v;
        this.x = x;
        this.l = l;
        this.c = c;
        this.d = d;
        this.m = m;
        this.v1 = v1;
        this.v2 = v2;
        this.resultado = resultado;
        this.operador = operador;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getV1() {
        return v1;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public int getV2() {
        return v2;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }
}
