package br.com.renato.kmcar;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {


    private static final int VERSAO_BANCO = 2;
    private static final String BANCO_KM = "db_km";

    //TABELA_TROCA_OLEO
    private static final String TABELA_TROCA_OLEO = "tb_troca_oleo";
    private static final String COLUNA_PLACA = "placa";
    private static final String COLUNA_MODELO = "modelo";
    private static final String COLUNA_KM_INICIAL = "km_inicial";
    private static final String COLUNA_KM_FINAL = "km_final";
    private static final String COLUNA_NOME_OLEO = "oleo";
    private static final String COLUNA_FILTRO = "filtro_trocado";
    private static final String COLUNA_PROPRIETARIO = "proprietario";
    private static final String COLUNA_FOTO = "foto";
    private static final String COLUNA_DATA = "data";


    public Database(Context context) {
        super(context, BANCO_KM, null, VERSAO_BANCO);
    }

//--------------------------------------------------------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_TABELA_TROCA_OLEO = "CREATE TABLE " +
                TABELA_TROCA_OLEO + " (" +
                COLUNA_PLACA + " TEXT PRIMARY KEY, " +
                COLUNA_MODELO + " TEXT, " +
                COLUNA_KM_INICIAL + " INTEGER, " +
                COLUNA_KM_FINAL + " INTEGER, " +
                COLUNA_NOME_OLEO + " TEXT, " +
                COLUNA_FILTRO + " TEXT, " +
                COLUNA_PROPRIETARIO + " TEXT, " +
                COLUNA_FOTO + " BLOB, " +
                COLUNA_DATA + " TEXT)";

        db.execSQL(QUERY_TABELA_TROCA_OLEO);
    }

//--------------------------------------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_TROCA_OLEO);
        onCreate(db);
    }

//--------------------------------------------------------------------------------------------------
    void AddDados(Dados dados) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_PLACA, dados.getPlaca());
        values.put(COLUNA_MODELO, dados.getModelo());
        values.put(COLUNA_KM_INICIAL, dados.getKm_inicial());
        values.put(COLUNA_KM_FINAL, dados.getKm_final());
        values.put(COLUNA_NOME_OLEO, dados.getNome_oleo());
        values.put(COLUNA_FILTRO, dados.getFiltro_trocado());
        values.put(COLUNA_PROPRIETARIO, dados.getNome_proprietario());
        values.put(COLUNA_FOTO, dados.getFoto());
        values.put(COLUNA_DATA,dados.getData());

        db.insert(TABELA_TROCA_OLEO, null, values);
        db.close();
    }

    //----------------------------------------------------------------------------------------------
    void apagar(Dados dados) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_TROCA_OLEO, COLUNA_PLACA + " = ?", new String[]{String.valueOf(dados.getPlaca())});
        db.close();
    }

    void ApagaTudo() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_TROCA_OLEO, null, null);
        String Deleta = "DELETE FROM " + TABELA_TROCA_OLEO;
        db.execSQL(Deleta);
        db.close();
    }

//--------------------------------------------------------------------------------------------------
    public List<Dados> pesquisa(Dados dados) {
        List<Dados> ListaTabela = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABELA_TROCA_OLEO + " WHERE " + COLUNA_PLACA + " = ?",
                new String[]{String.valueOf(dados.getPlaca())});

        if (c.moveToFirst()) {
            do {
                dados.setPlaca(c.getString(0));
                dados.setModelo(c.getString(1));
                dados.setKm_inicial(Integer.parseInt(c.getString(2)));
                dados.setKm_final(Integer.parseInt(c.getString(3)));
                dados.setNome_oleo(c.getString(4));
                dados.setFiltro_trocado(c.getString(5));
                dados.setNome_proprietario(c.getString(6));
                dados.setFoto(c.getBlob(7));
                dados.setData(c.getString(8));

                ListaTabela.add(dados);
            } while (c.moveToNext());
        }
        return ListaTabela;
    }
//--------------------------------------------------------------------------------------------------

 //-------------------------------------------------------------------------------------------------
    void atualizaCarro(Dados dados) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_PLACA, dados.getPlaca());
        values.put(COLUNA_MODELO, dados.getModelo());
        values.put(COLUNA_KM_INICIAL, dados.getKm_inicial());
        values.put(COLUNA_KM_FINAL, dados.getKm_final());
        values.put(COLUNA_NOME_OLEO, dados.getNome_oleo());
        values.put(COLUNA_FILTRO, dados.getFiltro_trocado());
        values.put(COLUNA_PROPRIETARIO, dados.getNome_proprietario());
        values.put(COLUNA_FOTO, dados.getFoto());
        values.put(COLUNA_DATA, dados.getData());


        db.update(TABELA_TROCA_OLEO, values, COLUNA_PLACA + " = ?" ,
                new String[]{String.valueOf(dados.getPlaca())});
    }
 //-------------------------------------------------------------------------------------------------
    public List<Dados> Cadastro() {
        List<Dados> ListaTabela = new ArrayList<>();

        String QUERY_TABELA_TROCA_OLEO = "SELECT * FROM " + TABELA_TROCA_OLEO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(QUERY_TABELA_TROCA_OLEO, null);

        if (c.moveToFirst()) {
            do {
                Dados dados = new Dados();
                dados.setPlaca(c.getString(0));
                dados.setModelo(c.getString(1));
                dados.setKm_inicial(Integer.parseInt(c.getString(2)));
                dados.setKm_final(Integer.parseInt(c.getString(3)));
                dados.setNome_oleo(c.getString(4));
                dados.setFiltro_trocado(c.getString(5));
                dados.setNome_proprietario(c.getString(6));
                dados.setFoto(c.getBlob(7));
                dados.setData(c.getString(8));

                ListaTabela.add(dados);
            } while (c.moveToNext());
        }
        return ListaTabela;
    }
}
 //-------------------------------------------------------------------------------------------------





