package br.com.renato.kmcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private TextView txt_km_inicio, txt_km_final, txt_oleo, txt_filtro,txt_modelo,txt_placa;
    private EditText edt_km_1, edt_km_2, edt_oleo, edt_proprietario,edt_modelo,edt_placa;
    private RadioButton rb_sim, rb_nao;


    int km_inicial, km_final;
    String nome_proprietario, nome_oleo, filtro_trocado,modelo,placa;

    Database db = new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_salvar = findViewById(R.id.btn_salvar);
        txt_filtro = findViewById(R.id.txt_filtro);
        txt_km_final = findViewById(R.id.txt_km_final);
        txt_km_inicio = findViewById(R.id.txt_km_inicio);
        txt_oleo = findViewById(R.id.txt_oleo);
        txt_modelo = findViewById(R.id.txt_modelo);

        edt_km_1 = findViewById(R.id.edt_km_1);
        edt_km_2 = findViewById(R.id.edt_km_2);
        edt_oleo = findViewById(R.id.edt_oleo);
        edt_placa = findViewById(R.id.edt_placa);
        edt_proprietario = findViewById(R.id.edt_proprietario);
        edt_modelo = findViewById(R.id.edt_modelo);
        rb_nao = findViewById(R.id.rb_nao);
        rb_sim = findViewById(R.id.rb_sim);
//--------------------------------------------------------------------------------------------------
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvaDados();
                LimpaCampos();
            }
        });
//--------------------------------------------------------------------------------------------------
        edt_oleo.addTextChangedListener(valida);
        edt_proprietario.addTextChangedListener(valida);
    }
//--------------------------------------------------------------------------------------------------
    private TextWatcher valida = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        nome_oleo = edt_oleo.getText().toString().trim();
        nome_proprietario = edt_proprietario.getText().toString().trim();

        btn_salvar.setEnabled(!nome_oleo.isEmpty() && !nome_oleo.isEmpty());

    }

    @Override
    public void afterTextChanged(Editable s) {

         }
    };
//--------------------------------------------------------------------------------------------------
    void SalvaDados() {

        placa = edt_placa.getText().toString();
        modelo = edt_modelo.getText().toString();
        km_inicial = Integer.parseInt(edt_km_1.getText().toString());
        km_final = Integer.parseInt(edt_km_2.getText().toString());
        nome_oleo = edt_oleo.getText().toString();

        if (rb_sim.isChecked()) {
            filtro_trocado = "Sim";
        } else
            if (rb_nao.isChecked()) {
                filtro_trocado = "Não";
            }
                nome_proprietario = edt_proprietario.getText().toString();

            db.AddDados(new Dados(km_inicial,km_final,placa,nome_oleo,filtro_trocado,nome_proprietario,modelo));
            Toast.makeText(MainActivity.this, "Cadastrado", Toast.LENGTH_LONG).show();
        }
//--------------------------------------------------------------------------------------------------
    void LimpaCampos() {
        edt_placa.setText("");
        edt_proprietario.setText("");
        edt_oleo.setText("");
        edt_modelo.setText("");
        edt_km_2.setText("");
        edt_km_1.setText("");
        rb_sim.setChecked(false);
        rb_nao.setChecked(false);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Cadastro) {
            ChamaTela();

        }
        if (id == R.id.sair) {
            finish();
        }
        return true;
    }
//--------------------------------------------------------------------------------------------------
    void ChamaTela(){
        Intent it = new Intent(this,MainActivity2.class);
        startActivity(it);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {

    }
}
