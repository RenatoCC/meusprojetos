package br.com.renato.gasolinaalcool;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView txt_gasolina,txt_alcool,txt_resultado,txt_info,txt_razao;
    private EditText edt_preco;
    private Button btn_ver;
    private ImageView img_razao,img_marca;
    private Switch mudar;
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
        txt_razao = findViewById(R.id.txt_razao);
        img_razao = findViewById(R.id.img_razao);
        img_marca = findViewById(R.id.img_marca);
        mudar = findViewById(R.id.mudar);


        mudar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Intent intent = new Intent(MainActivity.this,Consumo.class);
                    startActivity(intent);
                mudar.setChecked(false);
                finish();
                }else{

                }
            }
        });
        btn_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_preco.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Digite um valor", Toast.LENGTH_LONG).show();
                } else {
                    double gasolina;
                    double alcool;
                    double valor = 0.70;
                   // double razao;

                    gasolina = Double.parseDouble(edt_preco.getText().toString());
                    alcool = gasolina * valor;
                    //razao = alcool / gasolina;
                    DecimalFormat format = new DecimalFormat("#.##");

                    txt_resultado.setText(String.valueOf(format.format(alcool)));
                    //txt_razao.setText(String.valueOf(format.format(razao)));
                    txt_info.setVisibility(View.VISIBLE);
                    img_marca.setVisibility(View.VISIBLE);
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
//  CONTROLA O EVENTO DO BOTÃO VOLTAR

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sair?");
        builder.setMessage("Deseja realmente sair?");
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        AlertDialog alerta = builder.create();
        alerta.show();
    }
}
