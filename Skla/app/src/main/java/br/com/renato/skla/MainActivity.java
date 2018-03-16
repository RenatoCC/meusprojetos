package br.com.renato.skla;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int Ano,Mes,Dia;
private Button btn_cadastra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cadastra = findViewById(R.id.btn_cadastra);

        //btn_cadastra.setOnClickListener(this);

        btn_cadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTela();
            }
        });
    }

    public void chamaTela(){
        setContentView(R.layout.calendario);
    }

    @Override
    public void onClick(View v) {
       // Intent it = new Intent(this,Main2Activity.class);
        //startActivity(it);
    }
}

