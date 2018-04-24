package br.com.renato.kmcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_salvar;
    private TextView txt_km_inicio, txt_km_final, txt_oleo, txt_filtro;
    private EditText edt_km_1, edt_km_2, edt_oleo, edt_outro;
    private RadioButton rb_outro, rb_voce, rb_sim, rb_nao;

    int km_inicial, km_final;
    String nome_proprietario, nome_oleo, filtro_trocado;

    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Log.i("Programador_Renato", "Ciclo de vida - omCreate");


        btn_salvar =  findViewById(R.id.btn_salvar);
        txt_filtro =  findViewById(R.id.txt_filtro);
        txt_km_final =  findViewById(R.id.txt_km_final);
        txt_km_inicio =  findViewById(R.id.txt_km_inicio);
        txt_oleo = findViewById(R.id.txt_oleo);

        edt_km_1 = findViewById(R.id.edt_km_1);
        edt_km_2 = findViewById(R.id.edt_km_2);
        edt_oleo = findViewById(R.id.edt_oleo);
        edt_outro = findViewById(R.id.edt_outro);
        rb_nao = findViewById(R.id.rb_nao);
        rb_outro = findViewById(R.id.rb_outro);
        rb_sim = findViewById(R.id.rb_sim);
        rb_voce = findViewById(R.id.rb_voce);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvaDados();
                LimpaCampos();
            }
        });
    }

    void SalvaDados() {

        km_inicial = Integer.parseInt(edt_km_1.getText().toString());
        km_final = Integer.parseInt(edt_km_2.getText().toString());
        nome_oleo = edt_oleo.getText().toString();

        if (rb_sim.isChecked()) {
            filtro_trocado = "Sim";
        } else
            if (rb_nao.isChecked()) {
                filtro_trocado = "Não";
            }
            if (rb_voce.isChecked()) {
                nome_proprietario = "Você";
            }else
                if (rb_outro.isChecked()) {
                nome_proprietario = edt_outro.getText().toString();
            }

            db.AddDados(new Dados(km_inicial,km_final,nome_oleo,filtro_trocado,nome_proprietario));
            Toast.makeText(MainActivity.this, "Cadastrado", Toast.LENGTH_LONG).show();
        }

    void LimpaCampos() {
        edt_outro.setText("");
        edt_oleo.setText("");
        edt_km_2.setText("");
        edt_km_1.setText("");
        rb_outro.setChecked(false);
        rb_voce.setChecked(false);
        rb_sim.setChecked(false);
        rb_nao.setChecked(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Dados) {
            ChamaTela();
        }
        if (id == R.id.sair) {
            finish();
        }
        return true;
    }

    void ChamaTela(){
        Intent it = new Intent(this,MainActivity2.class);
        startActivity(it);
    }
    @Override
    public void onClick(View v) {

    }
}
