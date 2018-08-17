package br.com.renato.gasolinaalcool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Autor extends AppCompatActivity {

    private TextView txt_nome,txt_nome_faculdade,txt_ano_formacao,txt_linkedin,txt_likedin2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autor);

        txt_nome = findViewById(R.id.txt_nome);
        txt_nome_faculdade = findViewById(R.id.txt_nome_faculdade);
        txt_ano_formacao = findViewById(R.id.txt_ano_formacao);
        txt_linkedin = findViewById(R.id.txt_linkedin);
        txt_likedin2 = findViewById(R.id.txt_likedin2);

    }
}