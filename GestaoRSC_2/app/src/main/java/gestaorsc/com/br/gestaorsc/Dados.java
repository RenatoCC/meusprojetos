package gestaorsc.com.br.gestaorsc;

public class Dados {

    int id_pessoa, id_escolaridade;
    int idade,quantidade,quantidade2,quantidade3;
   String sexo, escolaridade;
   String q1,q2,q3,q4,q5,q6,q7;

   public Dados(){

   }


    public Dados(String sexo, int idade, String escolaridade,int quantidade3, int quantidade,int quantidade2, String q1, String q2, String q3,
                 String q4, String q5, String q6, String q7) {

       this.sexo = sexo;
       this.idade = idade;
       this.escolaridade = escolaridade;
       this.quantidade = quantidade;
       this.quantidade2 = quantidade2;
       this.quantidade3 = quantidade3;
       this.q1 = q1;
       this.q2 = q2;
       this.q3 = q3;
       this.q4 = q4;
       this.q5 = q5;
       this.q6 = q6;
       this.q7 = q7;
    }

    public int getQuantidade3() {
        return quantidade3;
    }

    public void setQuantidade3(int quantidade3) {
        this.quantidade3 = quantidade3;
    }

    public int getQuantidade2() {
        return quantidade2;
    }

    public void setQuantidade2(int quantidade2) {
        this.quantidade2 = quantidade2;
    }

    public void setId_escolaridade(int id_escolaridade) {
        this.id_escolaridade = id_escolaridade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public String getQ5() {
        return q5;
    }

    public void setQ5(String q5) {
        this.q5 = q5;
    }

    public String getQ6() {
        return q6;
    }

    public void setQ6(String q6) {
        this.q6 = q6;
    }

    public String getQ7() {
        return q7;
    }

    public void setQ7(String q7) {
        this.q7 = q7;
    }




}



