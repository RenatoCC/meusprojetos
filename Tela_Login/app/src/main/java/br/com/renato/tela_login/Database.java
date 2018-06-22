package br.com.renato.tela_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_CADASTRO = "db_cadastro";

    //TABELA_CADASTRO
    private static final String TABELA_USUARIO = "tb_usuario";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_SOBRENOME = "sobrenome";
    private static final String COLUNA_SENHA = "senha";
    private static final String COLUNA_FOTO = "foto";


    public Database(Context context) {
        super(context, BANCO_CADASTRO, null, VERSAO_BANCO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_TABELA_USUARIO = "CREATE TABLE " +
                TABELA_USUARIO + " (" +
                COLUNA_NOME + " TEXT, " +
                COLUNA_SOBRENOME + " TEXT, " +
                COLUNA_SENHA + " TEXT, " +
                COLUNA_FOTO + " BLOB)";

        db.execSQL(QUERY_TABELA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        onCreate(db);
    }

    void AddDados(Dados dados) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, dados.getNome());
        values.put(COLUNA_SOBRENOME, dados.getSobrenome());
        values.put(COLUNA_SENHA, dados.getSenha());
        values.put(COLUNA_FOTO, dados.getFoto());

        db.insert(TABELA_USUARIO, null, values);
        db.close();
    }
}
