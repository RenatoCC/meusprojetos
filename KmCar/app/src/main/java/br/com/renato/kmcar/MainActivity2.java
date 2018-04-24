package br.com.renato.kmcar;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private ListView lst_dados;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private Button btn_mostrar,btn_apagar;
    private EditText edt_id;

    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_dados);

        lst_dados = findViewById(R.id.lst_dados);
        btn_mostrar = findViewById(R.id.btn_mostrar);
        btn_apagar = findViewById(R.id.btn_apagar);
        edt_id = findViewById(R.id.edt_id);


        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrar();
            }
        });

        btn_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText().toString();

                if(id.isEmpty()){
                    Toast.makeText(MainActivity2.this,"Nenhum carro selecionado",Toast.LENGTH_LONG).show();
                }else{
                    Dados dados = new Dados();
                    dados.setId(Integer.parseInt(id));
                    db.apagar(dados);
                    mostrar();
                }
            }

        });

        edt_id.addTextChangedListener(valida2);
    }
        private TextWatcher valida2 = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

           String id = edt_id.getText().toString().trim();

            btn_apagar.setEnabled(!id.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    void mostrar(){

        List<Dados> dados = db.Cadastro();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, list);
        lst_dados.setAdapter(adapter);

        for(Dados d : dados){
            list.add(
                    "\n " +
                    "NUMERO " + d.id + "\n " + "\n"
                            + "PROPRIETARIO: " + d.nome_proprietario + "\n " + "\n"
                            + "MODELO: " + d.modelo + " \n " + "\n "
                            + "KM INICIAL: " + d.km_inicial + " \n " + "\n "
                            + "KM FINAL: " + d.km_final + " \n " + "\n "
                            + "ÓLEO: " + d.nome_oleo + " \n " + "\n "
                            + "FILTRO_TROCADO: " + d.filtro_trocado + " \n ");

        }
    }

    @Override
    public void onClick(View v) {

    }
}
