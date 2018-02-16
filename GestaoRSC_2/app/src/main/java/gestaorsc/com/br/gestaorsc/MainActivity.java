package gestaorsc.com.br.gestaorsc;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_iniciar, btn_salvar, btn_tabela,btn_limpatela, btn_relatorio, btn_apaga;
    private ListView lst_resultado;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);
        btn_iniciar.setOnClickListener(this);
        btn_salvar = (Button) findViewById(R.id.btn_salvar);
        btn_tabela = (Button) findViewById(R.id.btn_tabela);
        btn_limpatela = (Button) findViewById(R.id.btn_limpatela);
        lst_resultado = (ListView) findViewById(R.id.lst_resultado);
        btn_relatorio = (Button) findViewById(R.id.btn_relatorio);
        btn_apaga = (Button)findViewById(R.id.btn_apaga);


        btn_tabela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Dados> dados = db.mostrarTabela();
                list = new ArrayList<String>();
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);

                lst_resultado.setAdapter(adapter);
                for (Dados d : dados) {
                    list.add(d.getId_pessoa() + "º) " + "---------------------------------------------------------------" + " \n " + "\n"
                            +  " SEXO: " + d.getSexo() + " " + "\n "
                            + "IDADE: " + d.getIdade() + " " + "Anos" + "\n"
                            + " ESCOLARIDADE: " + d.getEscolaridade() + " " +  "\n "
                            + "Q_1 = " + d.q1 + " " +  "\n "
                            + "Q_2 = " + d.q2 + " " +  "\n "
                            + "Q_3 = " + d.q3 + " " +  "\n "
                            + "Q_4 = " + d.q4 + " " +  "\n "
                            + "Q_5 = " + d.q5 + " " +  "\n "
                            + "Q_6 = " + d.q6 + " " +  "\n "
                            + "Q_7 = " + d.q7+  "\n");
                }
            }
        });

        btn_limpatela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(adapter.isEmpty()){

                }else
                    {
                        adapter.clear();
                    }
        }});

       btn_relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               List<Dados> dados = db.consulta2();
                /* List<Dados> dados = db.consulta();
                list = new ArrayList<String>();

                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);

                lst_resultado.setAdapter(adapter);
                for(Dados t : dados){
                    list.add(String.valueOf("A QUANTIDADE DE PESSOAS QUE RESPONDERAM A LETRA (A) DA QUESTÃO 1 E LETRA (B) DA QUESTÃO 2 É: " + t.getQuantidade()));
                }*/
                list = new ArrayList<String>();

                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);

                lst_resultado.setAdapter(adapter);
                for (Dados b : dados ){
                    list.add(String.valueOf("A QUANTIDDE DE PESSOA COM ESCOLARIDADE ANALFABETO É " + b.getQuantidade2()));
                }
            }
        });
       btn_apaga.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               confimação();
           }
       });
    }
    public void onClick(View view) {

        Intent it = new Intent(this, Main2Activity.class);
        startActivity(it);
    }
    public void confimação(){
        AlertDialog.Builder msg = new AlertDialog.Builder(this);

        msg.setTitle("Alerta!!!");
        msg.setMessage("Tem certeza que deseja apagar toda a pesquisa?");
        msg.setIcon(android.R.drawable.ic_menu_delete);
        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleta();
            }
        });
        msg.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        msg.show();

    }

}