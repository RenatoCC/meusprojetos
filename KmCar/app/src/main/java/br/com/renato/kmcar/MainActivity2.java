package br.com.renato.kmcar;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private ListView lst_dados;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private Button btn_apagar, btn_pesquisa;
    private EditText edt_placa, edt_pesquisa;

    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_dados);

        lst_dados = findViewById(R.id.lst_dados);
        btn_apagar = findViewById(R.id.btn_apagar);
        btn_pesquisa = findViewById(R.id.btn_pesquisa);
        edt_placa = findViewById(R.id.edt_placa);
        edt_pesquisa = findViewById(R.id.edt_pesquisa);

    //-------------------------------------------------------------------------------------------------
     /*   List<Dados> dados = db.Cadastro();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, list);
        lst_dados.setAdapter(adapter);
        for (Dados d : dados) {
            list.add(
                    "\n " +
                            "                         " + "PLACA: " + d.placa + "\n " + "\n"
                            + "PROPRIETARIO: " + d.nome_proprietario + "\n " + "\n"
                            + "MODELO: " + d.modelo + " \n " + "\n "
                            + "KM INICIAL: " + d.km_inicial + " \n " + "\n "
                            + "KM FINAL: " + d.km_final + " \n " + "\n "
                            + "ÓLEO: " + d.nome_oleo + " \n " + "\n "
                            + "FILTRO_TROCADO: " + d.filtro_trocado + " \n ");
        }*/
//--------------------------------------------------------------------------------------------------
        btn_pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dados dados = new Dados();
                String placa = edt_pesquisa.getText().toString();

                dados.setPlaca(placa);
                db.pesquisa(dados);
                edt_pesquisa.setText("");
             resultado(dados);
            }
        });

//--------------------------------------------------------------------------------------------------
        btn_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta();
            }
        });

//--------------------------------------------------------------------------------------------------
        edt_placa.addTextChangedListener(valida);
    }
//--------------------------------------------------------------------------------------------------

    private TextWatcher valida = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String id = edt_placa.getText().toString().trim();
            btn_apagar.setEnabled(!id.isEmpty());
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };
//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
   void mostrar(){

        List<Dados> dados = db.Cadastro();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, list);
        lst_dados.setAdapter(adapter);
        for(Dados d : dados){
            list.add(
                    "\n " +
                             "                       " + "PLACA: " + d.placa + "\n " + "\n"
                            + "PROPRIETARIO: " + d.nome_proprietario + "\n " + "\n"
                            + "MODELO: " + d.modelo + " \n " + "\n "
                            + "KM INICIAL: " + d.km_inicial + " \n " + "\n "
                            + "KM FINAL: " + d.km_final + " \n " + "\n "
                            + "ÓLEO: " + d.nome_oleo + " \n " + "\n "
                            + "FILTRO_TROCADO: " + d.filtro_trocado + " \n ");
        }
    }
    void resultado(Dados dados) {
       List<Dados> dados1 = db.pesquisa(dados);
      // Dados dados = new Dados();


        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, list);
        lst_dados.setAdapter(adapter);
        for (Dados d : dados1) {
            list.add(
                    "\n " +
                            "                         " + "PLACA: " + d.placa + "\n " + "\n"
                            + "PROPRIETARIO: " + d.nome_proprietario + "\n " + "\n"
                            + "MODELO: " + d.modelo + " \n " + "\n "
                            + "KM INICIAL: " + d.km_inicial + " \n " + "\n "
                            + "KM FINAL: " + d.km_final + " \n " + "\n "
                            + "ÓLEO: " + d.nome_oleo + " \n " + "\n "
                            + "FILTRO_TROCADO: " + d.filtro_trocado + " \n ");
        }
    }
//--------------------------------------------------------------------------------------------------
    void alerta() {
        AlertDialog.Builder msg = new AlertDialog.Builder(this);

        msg.setTitle("Alerta!!!");
        msg.setMessage("Tem certeza que deseja pagar");
        msg.setIcon(android.R.drawable.ic_dialog_alert);
        msg.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Dados dados = new Dados();
                String placa = edt_placa.getText().toString();

                dados.setPlaca(placa);
                db.apagar(dados);
                edt_placa.setText("");
                mostrar();
            }
        });
        msg.show();
        }
//--------------------------------------------------------------------------------------------------

    @Override
    public void onClick(View v) {

    }
}
