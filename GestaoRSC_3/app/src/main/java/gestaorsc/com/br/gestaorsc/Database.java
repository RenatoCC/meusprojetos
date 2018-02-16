package gestaorsc.com.br.gestaorsc;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final  int VERSAO_BANCO = 1;
    private static final String BANCO_GESTAO = "db_clientes";

    //TABELA_PESSOA
    private static final String TABELA_PESSOA = "tb_pessoa";
    private static final String COLUNA_ID_PESSOA = "id";
    private static final String COLUNA_SEXO = "sexo";
    private static final String COLUNA_IDADE = "idade";
    private static final String COLUNA_ESCOLARIDADE = "escolaridade";
    private static final String COLUNA_Q1 = "questão_1";
    private static final String COLUNA_Q2 = "questão_2";
    private static final String COLUNA_Q3 = "questão_3";
    private static final String COLUNA_Q4 = "questão_4";
    private static final String COLUNA_Q5 = "questão_5";
    private static final String COLUNA_Q6 = "questão_6";
    private static final String COLUNA_Q7 = "questão_7";


    public Database(Context context) {
        super(context, BANCO_GESTAO, null, VERSAO_BANCO);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_TABELA_PESSOA = "CREATE TABLE " + TABELA_PESSOA + " ("
                + COLUNA_ID_PESSOA + " INTEGER PRIMARY KEY, " + COLUNA_SEXO + " TEXT, "
                + COLUNA_IDADE + " INTEGER," +  COLUNA_ESCOLARIDADE + " TEXT, " + COLUNA_Q1 + " TEXT, "
                + COLUNA_Q2 + " TEXT, " + COLUNA_Q3 + " TEXT, " + COLUNA_Q4 + " TEXT, "
                + COLUNA_Q5 + " TEXT, " + COLUNA_Q6 + " TEXT, " + COLUNA_Q7 + " TEXT)";

                   db.execSQL(QUERY_TABELA_PESSOA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //db.execSQL("DROP TABLE tb_pessoa");
        //onCreate(db);
    }


    void addDados(Dados dados) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_SEXO, dados.getSexo());
        values.put(COLUNA_IDADE, dados.getIdade());
        values.put(COLUNA_ESCOLARIDADE, dados.getEscolaridade());
        values.put(COLUNA_Q1, dados.getQ1());
        values.put(COLUNA_Q2, dados.getQ2());
        values.put(COLUNA_Q3, dados.getQ3());
        values.put(COLUNA_Q4, dados.getQ4());
        values.put(COLUNA_Q5, dados.getQ5());
        values.put(COLUNA_Q6, dados.getQ6());
        values.put(COLUNA_Q7, dados.getQ7());

        db.insert(TABELA_PESSOA, null, values);
        db.close();


    }

    public List<Dados>consulta4(){
        List<Dados> consulta4 = new ArrayList<Dados>();
        String QUERY_CONSULTA4 = "SELECT COUNT (id) FROM " + TABELA_PESSOA ;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor b = db.rawQuery(QUERY_CONSULTA4, null);
        if(b.moveToFirst()){
            do{
                Dados dados = new Dados();
                dados.setQuantidade_total(Integer.parseInt(b.getString(0)));
                consulta4.add(dados);
            }while (b.moveToNext());
        }
        return consulta4;
    }
    public List<Dados> consulta(){
        List<Dados> consulta = new ArrayList<Dados>();

        String QUERY_CONSULTA = "SELECT COUNT (id) FROM " + TABELA_PESSOA + " WHERE questão_1 LIKE 'b' AND questão_2 LIKE 'a'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor d = db.rawQuery(QUERY_CONSULTA,null);
        if(d.moveToFirst()){
            do {
                Dados dados= new Dados();
                dados.setQuantidade(Integer.parseInt(d.getString(0)));
                consulta.add(dados);
            }while (d.moveToNext());
        }
        return consulta;
    }

    public List<Dados>consulta2(){
        List<Dados> consulta2 = new ArrayList<Dados>();
        String QUERY_CONSULTA2 = "SELECT COUNT (id) FROM " + TABELA_PESSOA + " WHERE escolaridade LIKE 'Analfabeto'";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor b = db.rawQuery(QUERY_CONSULTA2, null);
        if(b.moveToFirst()){
            do{
                Dados dados = new Dados();
                dados.setQuantidade2(Integer.parseInt(b.getString(0)));
                consulta2.add(dados);
            }while (b.moveToNext());
        }
            return consulta2;
    }
    public List<Dados>consulta3() {
        List<Dados> consulta3 = new ArrayList<Dados>();

        String QUERY_CONSULTA3 = "SELECT COUNT (id) FROM " + TABELA_PESSOA + " WHERE questão_7 LIKE 'a'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor h = db.rawQuery(QUERY_CONSULTA3, null);

        if (h.moveToFirst()) {
            do {
                Dados dados = new Dados();
                dados.setQuantidade3(Integer.parseInt(h.getString(0)));
                consulta3.add(dados);
            } while (h.moveToNext());
        }
        return consulta3;
    }

        public List<Dados> mostrarTabela(){

            List<Dados> listTabela = new ArrayList<Dados>();

            String QUERY_TABELA_PESSOA = "SELECT * FROM " + TABELA_PESSOA;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery(QUERY_TABELA_PESSOA, null);

            if (c.moveToFirst()) {
                do {
                    Dados dados = new Dados();
                    dados.setId_pessoa(Integer.parseInt(c.getString(0)));
                    dados.setSexo(c.getString(1));
                    dados.setIdade(Integer.parseInt(c.getString(2)));
                    dados.setEscolaridade(c.getString(3));
                    dados.setQ1(c.getString(4));
                    dados.setQ2(c.getString(5));
                    dados.setQ3(c.getString(6));
                    dados.setQ4(c.getString(7));
                    dados.setQ5(c.getString(8));
                    dados.setQ6(c.getString(9));
                    dados.setQ7(c.getString(10));

                    listTabela.add(dados);
                } while (c.moveToNext());
            }
            return listTabela;
        }

    void deleta() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_PESSOA, null, null);

            String QUERY_DELETA = "DELETE FROM " + TABELA_PESSOA;
            db.execSQL(QUERY_DELETA);
            db.close();
        }
    }

