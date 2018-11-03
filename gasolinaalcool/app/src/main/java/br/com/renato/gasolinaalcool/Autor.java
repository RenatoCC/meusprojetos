package br.com.renato.gasolinaalcool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Autor extends AppCompatActivity {

    private ImageView img_black_left,img_white_esquerdo,foto,img_white_direito,img_black_direito,
            img_esquerdo_pequeno,img_direito_pequeno,img_baixo;

    private TextView txt_nome,txt_nome_faculdade,txt_ano_formacao,txt_telefone,txt_email,txt_linkedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autor);

        img_black_left = findViewById(R.id.img_black_left);
        img_black_direito = findViewById(R.id.img_black_direito);
        img_white_esquerdo = findViewById(R.id.img_white_esquerdo);
        img_white_direito = findViewById(R.id.img_white_direito);
        foto = findViewById(R.id.foto);
        img_esquerdo_pequeno = findViewById(R.id.img_esquerdo_pequeno);
        img_direito_pequeno= findViewById(R.id.img_direito_pequeno);
        img_baixo = findViewById(R.id.img_baixo);

        txt_nome = findViewById(R.id.txt_nome);
        txt_nome_faculdade = findViewById(R.id.txt_nome_faculdade);
        txt_ano_formacao = findViewById(R.id.txt_ano_formacao);
        txt_telefone = findViewById(R.id.txt_telefone);
        txt_email = findViewById(R.id.txt_email);
        txt_linkedin = findViewById(R.id.txt_linkedin);
    }
}