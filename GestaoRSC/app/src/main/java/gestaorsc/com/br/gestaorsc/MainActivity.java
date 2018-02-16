package gestaorsc.com.br.gestaorsc;

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
                    list.add(d.getId_pessoa() + "º) " + " - " +  " SEXO: " + d.getSexo() + " " + "/" + " " + "IDADE: " + d.getIdade()
                            + " " + "/" + " " + " ESCOLARIDADE: " + d.getEscolaridade()
                            + " " + "/" + " " + "Q_1 = " + d.q1 + " " + "/" + " " + "Q_2 = " + d.q2 + " " + "/" + " " + "Q_3 = " + d.q3 + " " + "/" + " " + "Q_4 = " + d.q4
                            + " " + "/" + " " + "Q_5 = " + d.q5 + " " + "/" + " " + "Q_6 = " + d.q6 + " " + "/" + " " + "Q_7 = " + d.q7);
                }
            }
        });

        btn_limpatela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(adapter.isEmpty()){
                    Toast.makeText(MainActivity.this," Limpo ",Toast.LENGTH_LONG).show();
                }else
                {
                    adapter.clear();
                }
            }});

        btn_relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Dados> dados = db.consulta();
                list = new ArrayList<String>();

                adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);

                lst_resultado.setAdapter(adapter);
                for(Dados t : dados){
                    list.add(String.valueOf("A QUANTIDADE DE PESSOAS QUE RESPONDERAM A LETRA (A) DA QUESTÃO 1 E LETRA (B) DA QUESTÃO 2 É: " + t.getQuantidade()));
                }
            }
        });
        btn_apaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.deleta();
                adapter.clear();
            }
        });
    }
    public void onClick(View view) {

        Intent it = new Intent(this, Main2Activity.class);
        startActivity(it);
    }

}