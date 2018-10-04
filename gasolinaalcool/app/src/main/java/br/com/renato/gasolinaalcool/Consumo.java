package br.com.renato.gasolinaalcool;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Consumo extends AppCompatActivity {

    private TextView txt_apresntacao,txt_km_rodados,txt_qtd_litros,txt_apro,txt_litro,txt_resultado,txt_verde,txt_vermelho,txt_laranja,
    txt_ok,txt_alto,txt_medio;
    private Button btn_calcular;
    private EditText edt_km_rodados,edt_qtd_litros;
    private Switch abastecimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumo);


        abastecimento = findViewById(R.id.abastecimento);
        txt_apresntacao = findViewById(R.id.txt_apresentacao);
        txt_km_rodados = findViewById(R.id.txt_km_rodados);
        txt_qtd_litros = findViewById(R.id.txt_qtd_litros);
        txt_apro = findViewById(R.id.txt_apro);
        txt_litro = findViewById(R.id.txt_litro);
        txt_resultado = findViewById(R.id.txt_resultado);
        txt_verde = findViewById(R.id.txt_verde);
        txt_vermelho = findViewById(R.id.txt_vermelho);
        txt_laranja = findViewById(R.id.txt_laranja);
        txt_ok = findViewById(R.id.txt_ok);
        txt_alto = findViewById(R.id.txt_alto);
        txt_medio = findViewById(R.id.txt_medio);

        btn_calcular = findViewById(R.id.btn_calcular);
        edt_km_rodados = findViewById(R.id.edt_km_rodados);
        edt_qtd_litros = findViewById(R.id.edt_qtd_litros);

        abastecimento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Intent intent = new Intent(Consumo.this,MainActivity.class);
                    startActivity(intent);
                    abastecimento.setChecked(false);

                    finish();
                }else{

                }
            }
        });
        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double quantidade_rodados;
                double quantidade_abast;
                double resultado;

                if (edt_km_rodados.getText().toString().isEmpty() || edt_qtd_litros.getText().toString().isEmpty()) {
                    Toast.makeText(Consumo.this, "preencha os campos", Toast.LENGTH_LONG).show();
                } else {
                    quantidade_rodados = Double.parseDouble(edt_km_rodados.getText().toString());
                    quantidade_abast = Double.parseDouble(edt_qtd_litros.getText().toString());

                    resultado = quantidade_rodados / quantidade_abast;
                    DecimalFormat format = new DecimalFormat("#.##");

                if (resultado >= 12) {
                    txt_resultado.setText(String.valueOf(format.format(resultado)));
                    txt_apro.setVisibility(View.VISIBLE);
                    txt_litro.setVisibility(View.VISIBLE);
                    txt_vermelho.setVisibility(View.INVISIBLE);
                    txt_laranja.setVisibility(View.INVISIBLE);
                    txt_alto.setVisibility(View.INVISIBLE);
                    txt_medio.setVisibility(View.INVISIBLE);
                    txt_verde.setVisibility(View.VISIBLE);
                    txt_ok.setVisibility(View.VISIBLE);
                    } else
                        if (resultado > 10.5 & resultado < 12){
                            txt_resultado.setText(String.valueOf(format.format(resultado)));
                            txt_apro.setVisibility(View.VISIBLE);
                            txt_litro.setVisibility(View.VISIBLE);
                            txt_verde.setVisibility(View.INVISIBLE);
                            txt_ok.setVisibility(View.INVISIBLE);
                            txt_alto.setVisibility(View.INVISIBLE);
                            txt_vermelho.setVisibility(View.INVISIBLE);
                            txt_laranja.setVisibility(View.VISIBLE);
                            txt_medio.setVisibility(View.VISIBLE);
                    }else
                        if (resultado <= 10.5){
                            txt_resultado.setText(String.valueOf(format.format(resultado)));
                            txt_apro.setVisibility(View.VISIBLE);
                            txt_litro.setVisibility(View.VISIBLE);
                            txt_verde.setVisibility(View.INVISIBLE);
                            txt_laranja.setVisibility(View.INVISIBLE);
                            txt_ok.setVisibility(View.INVISIBLE);
                            txt_medio.setVisibility(View.INVISIBLE);
                            txt_vermelho.setVisibility(View.VISIBLE);
                            txt_alto.setVisibility(View.VISIBLE);
                        }
                }
            }
        });
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
