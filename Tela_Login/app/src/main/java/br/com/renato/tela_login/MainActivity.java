package br.com.renato.tela_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txt_registro;
    private EditText edt_usuario,edt_senha_login;
    private CardView card;
    Database db = new Database(this);
    Dados dados = new Dados();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_registro = findViewById(R.id.txt_registro);
        edt_usuario = findViewById(R.id.edt_usuario);
        edt_senha_login = findViewById(R.id.edt_senha_login);
        card = findViewById(R.id.card);

        txt_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Cadastro.class);
                startActivity(intent);
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_usuario.getText().toString();
                String senha = edt_senha_login.getText().toString();
                dados.setEmail(email);
                dados.setSenha(senha);

//               if (edt_usuario.getText().toString().isEmpty()){
//                   Toast.makeText(MainActivity.this,"digite um email",Toast.LENGTH_SHORT).show();
//               }else
//                   if (edt_senha_login.getText().toString().isEmpty()){
//                       Toast.makeText(MainActivity.this,"digite um senha",Toast.LENGTH_SHORT).show();
//                   }else
//                if (db.pesquisa(dados) == true){
//                    Intent intent = new Intent(MainActivity.this, Logado.class);
//                      startActivity(intent);
//                }else
//                    if (db.pesquisa(dados) == !true){
//                    Toast.makeText(MainActivity.this,"Usuario não cadastrado",Toast.LENGTH_SHORT).show();
//                    }
            }
        });
    }
}
