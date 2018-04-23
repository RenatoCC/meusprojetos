package br.com.renato.kmcar;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {


    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_KM = "db_km";

    //TABELA_TROCA_OLEO
    private static final String TABELA_TROCA_OLEO = "tb_troca_oleo";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_KM_INICIAL = "km_inicial";
    private static final String COLUNA_KM_FINAL = "km_final";
    private static final String COLUNA_NOME_OLEO = "oleo";
    private static final String COLUNA_FILTRO = "filtro_trocado";
    private static final String COLUNA_PROPRIETARIO = "proprietario";


    public Database(Context context) {
        super(context, BANCO_KM, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_TABELA_TROCA_OLEO = "CREATE TABLE " + TABELA_TROCA_OLEO + " ("
                + COLUNA_ID + " INTEGER PRIMARY KEY, " + COLUNA_KM_INICIAL + " INTEGER, "
                + COLUNA_KM_FINAL + " INTEGER, " + COLUNA_NOME_OLEO + " TEXT, "
                + COLUNA_FILTRO + " TEXT, " + COLUNA_PROPRIETARIO + " TEXT)";

        db.execSQL(QUERY_TABELA_TROCA_OLEO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void AddDados(Dados dados) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_KM_INICIAL, dados.getKm_inicial());
        values.put(COLUNA_KM_FINAL, dados.getKm_final());
        values.put(COLUNA_NOME_OLEO, dados.getNome_oleo());
        values.put(COLUNA_FILTRO, dados.getFiltro_trocado());
        values.put(COLUNA_PROPRIETARIO, dados.getNome_proprietario());

        db.insert(TABELA_TROCA_OLEO, null, values);
        db.close();
    }

    public List<Dados> Cadastro() {

        List<Dados> ListaTabela = new ArrayList<>();

        String QUERY_TABELA_TROCA_OLEO = "SELECT * FROM " + TABELA_TROCA_OLEO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(QUERY_TABELA_TROCA_OLEO, null);

        if (c.moveToFirst()) {
            do {
                Dados dados = new Dados();
                dados.setId(Integer.parseInt(c.getString(0)));
                dados.setKm_inicial(Integer.parseInt(c.getString(1)));
                dados.setKm_final(Integer.parseInt(c.getString(2)));
                dados.setNome_oleo(c.getString(3));
                dados.setFiltro_trocado(c.getString(4));
                dados.setNome_proprietario(c.getString(5));

                ListaTabela.add(dados);
            } while (c.moveToNext());
        }
        return ListaTabela;
    }
}
