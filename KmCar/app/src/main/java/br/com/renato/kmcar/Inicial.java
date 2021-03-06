package br.com.renato.kmcar;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Inicial extends AppCompatActivity {

    private FloatingActionButton btn_floating2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inicial);

        btn_floating2 = findViewById(R.id.btn_floating2);
        btn_floating2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicial.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicial,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.id_cadastro){
            Intent intent = new Intent(Inicial.this,MainActivity2.class);
            startActivity(intent);
        }
        if(id == R.id.id_autor){
            Intent intent = new Intent(Inicial.this,Autor.class);
            startActivity(intent);
        }
        if (id == R.id.id_sobre){
            Intent intent = new Intent(Inicial.this,Sobre.class);
            startActivity(intent);
        }
        if(id == R.id.id_sair){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
