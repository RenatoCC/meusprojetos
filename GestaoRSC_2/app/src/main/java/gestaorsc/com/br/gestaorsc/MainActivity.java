package gestaorsc.com.br.gestaorsc;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_iniciar, btn_salvar, btn_pesquisa,btn_limpatela;
    private ListView lst_resultado;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    //VARIAVEIS CRIADAS PARA CONVERTER OS VALORES, POIS O RESULTADO POSSUIA MUITOS ALGARISMOS DECIMAIS
    double res, qtt;

    //INSTANCIA DA CLASSE DO BANCO DE DADOS
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        btn_iniciar = findViewById(R.id.btn_iniciar);
        btn_iniciar.setOnClickListener(this);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_pesquisa = findViewById(R.id.btn_pesquisa);
        btn_limpatela = findViewById(R.id.btn_limpatela);
        lst_resultado = findViewById(R.id.lst_resultado);

        //METODO QUE RETORNA TODOS OS DADOS DO BANCO
        btn_pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Dados> dados = db.mostrarTabela();
                list = new ArrayList<>();
                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                lst_resultado.setAdapter(adapter);

        //RETORNA OS DADOS DO BANCO REPONSAVEL POR  ESSE METODO
                for (Dados d : dados) {
                            list.add(d.getId_pessoa() + "º) " + "---------------------------------------------------------------" + " \n " + "\n"
                                    + " SEXO: " + d.getSexo() + " " + "\n "
                                    + "IDADE: " + d.getIdade() + " " + "Anos" + "\n"
                                    + " ESCOLARIDADE: " + d.getEscolaridade() + " " + "\n "
                                    + "Q_1 = " + d.q1 + " " + "\n "
                                    + "Q_2 = " + d.q2 + " " + "\n "
                                    + "Q_3 = " + d.q3 + " " + "\n "
                                    + "Q_4 = " + d.q4 + " " + "\n "
                                    + "Q_5 = " + d.q5 + " " + "\n "
                                    + "Q_6 = " + d.q6 + " " + "\n "
                                    + "Q_7 = " + d.q7 + "\n");
                    }
                }
        });

        //METODO QUE LIMPA OS DADOS MOSTRADOS NO SCROLLVIEW DA TELA INICIAL
        btn_limpatela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//TRATAMENTO DE ERRO EXCESSOES
                try{
                    if (adapter.isEmpty()) {

                    }else{
                        adapter.clear();
                    }
                }	catch(NullPointerException ex){

                }
        }});

       }

    //RESPONSAVEL POR CHAMAR A TELA DE QUESTIONARIO
    public void onClick(View view) {

        Intent it = new Intent(this, Main2Activity.class);
        startActivity(it);
    }
    //APAGA A PESQUISA REALIZADA
    public void excluiPesquisa(){

    //RETORNA OS DADOS DO BANCO
        final List<Dados> dados4 = db.consulta4();

    //MOSTRA UMA CAIXA DE DIALOGO DE CONFIRMAÇÃO DE EXCLUSÃO DA PESQUISA
        AlertDialog.Builder msg = new AlertDialog.Builder(this);

        msg.setTitle("Alerta!!!");
        msg.setMessage("Tem certeza que deseja apagar toda a pesquisa?");
        msg.setIcon(android.R.drawable.ic_menu_delete);
        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

    //TRATAMENTO DE ERRO DE EXCESSOES
                try {
                    for (Dados h : dados4) {
                        if (h.getQuantidade_total() == 0) {
                            Toast.makeText(MainActivity.this, "NÃO EXISTE PESQUISA CADASTRADA", Toast.LENGTH_LONG).show();
                        } else {
                            db.deleta();
                            adapter.clear();
                            Toast.makeText(MainActivity.this, "PESQUISA APAGADA", Toast.LENGTH_LONG).show();
                        }
                    }
                }catch (NullPointerException ex) {
                    for (Dados h : dados4) {
                        if (h.getQuantidade_total() > 0) {
                            db.deleta();
                            Toast.makeText(MainActivity.this, "PESQUISA APAGADA", Toast.LENGTH_LONG).show();
                        } else {
                            adapter.clear();
                            Toast.makeText(MainActivity.this, "NÃO EXISTE PESQUISA CADASTRADA", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        msg.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        msg.show();
    }
    //METODO QUE CRIA MENUS NA TELA
    public void relatorio(){
        //RESPONSAVEL POR TRAZER OS DADOS DO BANCO
        List<Dados> dados1 = db.consulta();
        List<Dados> dados2 = db.consulta2();
        List<Dados> dados3 = db.consulta3();
        List<Dados> dados4 = db.consulta4();

        //CARREGA AS INFORMAÇÕES NO SCROLLVIEW
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        lst_resultado.setAdapter(adapter);

        //CONVERTE OS VALORES PARA UM PADRÃO COM
        for (Dados d : dados4){
            DecimalFormat df = new DecimalFormat("0");
            qtt = d.getQuantidade_total();
            String dx = df.format(qtt);
            list.add(String.valueOf("TOTAL DE PESSOAS PESQUISADAS E: " + dx));
        }
                /*
                for(Dados t : dados1){
                    list.add(String.valueOf("A QUANTIDADE DE PESSOAS QUE RESPONDERAM A LETRA (A) DA QUESTÃO 1 E LETRA (B) DA QUESTÃO 2 É: " + t.getQuantidade()));
                }
                for (Dados b : dados2 ){
                    list.add(String.valueOf("A QUANTIDDE DE PESSOA COM ESCOLARIDADE ANALFABETO É " + b.getQuantidade2()));
                }*/
        for (Dados c : dados3) {
            for (Dados d : dados4) {
                DecimalFormat df = new DecimalFormat("0.00");
                res = c.getQuantidade3() / 100 * d.getQuantidade_total();
                String dx = df.format(res);
                list.add((dx + "% " + "-> " + " ACHAM QUE O LIXO PREJUDICA O MEIO AMBIENTE"));

            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            excluiPesquisa();

        }
        if(id == R.id.sair){
            finish();
        }
        if(id == R.id.id_relatorio){
            relatorio();
        }
        return true;
    }

    }

