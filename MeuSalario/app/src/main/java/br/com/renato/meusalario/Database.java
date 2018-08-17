package br.com.renato.meusalario;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 5;
    private static final String NOME_BANCO = "db_contas";


    //TABELA CONTAS
    private static final String TABELA_CONTAS = "tb_contas";
    private static final String COLNUNA_VALOR = "valor";
    private static final String COLUNA_DESPESA = "despesa";
    private static final String COLUNA_DATA = "data";
    private static final String COLUNA_SINAL = "sinal";

    //TABELA SALARIO
    private static final String TABELA_SALARIO = "tb_salario";
    private static final String COLUNA__ID = "id";
    private static final String COLUNA_SALARIO = "salario";



    public Database(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_TABELA_CONTAS = "CREATE TABLE " +
                TABELA_CONTAS + "(" +
                COLUNA_DESPESA + " TEXT PRIMARY KEY, " +
                COLNUNA_VALOR + " INTEGER, " +
                COLUNA_DATA + " TEXT, " +
                COLUNA_SINAL +  " TEXT)";


        String QUERY_TABELA_SALARIO = "CREATE TABLE " +
                TABELA_SALARIO + "(" +
                COLUNA__ID + " INTEGER PRIMARY KEY, " +
                COLUNA_SALARIO + " INTEGER)";

        db.execSQL(QUERY_TABELA_CONTAS);
        db.execSQL(QUERY_TABELA_SALARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CONTAS);
        onCreate(db);
    }

    public List<Dados> despesas(){
        List<Dados> ListarDespesas = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String QUERY = "SELECT * FROM " + TABELA_CONTAS;
        Cursor c = db.rawQuery(QUERY,null);

        if (c.moveToFirst()){
            do {
                Dados dados = new Dados();
                dados.setDespesa(c.getString(0));
                dados.setValor(Double.parseDouble(c.getString(1)));
                dados.setData(c.getString(2));
                dados.setSinal(c.getString(3));
                ListarDespesas.add(dados);
            }while (c.moveToNext());
        }
        return ListarDespesas;
    }

    void AddDados(Dados dados){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_DESPESA,dados.getDespesa());
        values.put(COLNUNA_VALOR, dados.getValor());
        values.put(COLUNA_DATA, dados.getData());
        values.put(COLUNA_SINAL,dados.getSinal());

        db.insert(TABELA_CONTAS,null,values);
        db.close();
    }
    void Add_Salario(Dados dados){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_SALARIO,dados.getSalario());

        db.insert(TABELA_SALARIO,null,values);
        db.close();
    }
    void apagar(Dados dados) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_CONTAS, COLUNA_DESPESA + " = ?", new String[]{String.valueOf(dados.getDespesa())});
        db.close();
    }
   /* void soma(Dados dados){
        SQLiteDatabase db = this.getReadableDatabase();

        String QUERY_SOMA = "SELECT SUM(valor) FROM " + TABELA_CONTAS;
         Cursor c = db.execSQL(QUERY_SOMA,null);
         dados.setSoma();
    }*/

   public List<Dados> soma(){
       List<Dados> soma_vaor = new ArrayList<>();

       String QUERY_SOMA = " SELECT SUM(valor) FROM " + TABELA_CONTAS;

       SQLiteDatabase db = this.getReadableDatabase();
       Cursor c = db.rawQuery(QUERY_SOMA,null);

       if (c.moveToFirst()){
           do {
               Dados dados = new Dados();
               dados.setSoma(Double.parseDouble(c.getString(0)));
           soma_vaor.add(dados);
           }while (c.moveToNext());
       }
return soma_vaor;
   }
}
