package br.com.renato.gasolinaalcool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView txt_gasolina,txt_alcool,txt_resultado,txt_info;
    private EditText edt_preco;
    private Button btn_ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_gasolina = findViewById(R.id.txt_gasolina);
        txt_alcool = findViewById(R.id.txt_alcool);
        txt_resultado = findViewById(R.id.txt_resultado);
        edt_preco = findViewById(R.id.edt_preco);
        btn_ver = findViewById(R.id.btn_Ver);
        txt_info = findViewById(R.id.txt_info);

        btn_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_preco.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Digite um valor", Toast.LENGTH_LONG).show();
                } else {
                    double gasolina;
                    double resultado;
                    double valor = 0.70;
                    gasolina = Double.parseDouble(edt_preco.getText().toString());
                    resultado = gasolina * valor;
                    DecimalFormat format = new DecimalFormat("#.###");

                    txt_resultado.setText(String.valueOf(format.format(resultado)));
                    txt_info.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.id_sair){
           finish();
        }
        if(id == R.id.id_sobre){
            Intent it = new Intent(MainActivity.this,Sobre.class);
            startActivity(it);
        }
        if (id == R.id.id_autor){
            Intent intent = new Intent(MainActivity.this,Autor.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
