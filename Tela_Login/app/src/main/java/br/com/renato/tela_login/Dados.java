package br.com.renato.tela_login;


public class Dados {

    String nome,sobrenome,senha;
    byte[] foto;


    public Dados(){

    }
    public Dados(String nome,String sobrenome,String senha,byte[] foto){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}

