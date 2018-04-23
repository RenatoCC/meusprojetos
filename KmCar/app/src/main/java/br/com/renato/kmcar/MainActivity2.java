package br.com.renato.kmcar;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private ListView lst_dados;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private Button btn_mostrar;

    Database db = new Database(this);



    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_dados);

        Log.i("RENATO_PROGRAMADOR", "MainActivity2");

        lst_dados = findViewById(R.id.lst_dados);
        btn_mostrar = (Button)findViewById(R.id.btn_mostrar);

        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Dados> dados = db.Cadastro();
                list = new ArrayList<>();
                adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, list);
                lst_dados.setAdapter(adapter);

               for(Dados d : dados){
                   list.add(
                   d.getId() + "º) " + "\n " + "\n "
                   + "KM INICIAL " + d.getKm_inicial() + " \n "
                   + " KM FINAL " + d.getKm_final() + " \n "
                   + "OLEO " + d.getNome_oleo() + " \n "
                   + "FILTRO_TROCADO " + d.getFiltro_trocado() + " \n "
                   + "NOME PROPRIETARIO " + d.getNome_proprietario() + "\n");
               }
            }
        });

    }
    @Override
    public void onClick(View v) {

    }
}
