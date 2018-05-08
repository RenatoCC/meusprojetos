package br.com.renato.kmcar;


public class Dados {
    int id,km_inicial,km_final;
    String nome_proprietario,nome_oleo,filtro_trocado,modelo,placa;
    byte[] foto;
//--------------------------------------------------------------------------------------------------
    public Dados(){

    }
//--------------------------------------------------------------------------------------------------
    public Dados(int km_inicial,int km_final, String placa,String nome_oleo,String filtro_trocado,String nome_proprietario,String modelo,byte[] foto){

        this.placa = placa;
        this.filtro_trocado = filtro_trocado;
        this.nome_oleo = nome_oleo;
        this.nome_proprietario = nome_proprietario;
        this.km_inicial = km_inicial;
        this.km_final = km_final;
        this.modelo = modelo;
        this.foto = foto;
    }
//--------------------------------------------------------------------------------------------------


    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKm_inicial() {
        return km_inicial;
    }

    public void setKm_inicial(int km_inicial) {
        this.km_inicial = km_inicial;
    }

    public int getKm_final() {
        return km_final;
    }

    public void setKm_final(int km_final) {
        this.km_final = km_final;
    }

    public String getNome_proprietario() {
        return nome_proprietario;
    }

    public void setNome_proprietario(String nome_proprietario) {
        this.nome_proprietario = nome_proprietario;
    }

    public String getNome_oleo() {
        return nome_oleo;
    }

    public void setNome_oleo(String nome_oleo) {
        this.nome_oleo = nome_oleo;
    }

    public String getFiltro_trocado() {
        return filtro_trocado;
    }

    public void setFiltro_trocado(String filtro_trocado) {
        this.filtro_trocado = filtro_trocado;
    }
}


