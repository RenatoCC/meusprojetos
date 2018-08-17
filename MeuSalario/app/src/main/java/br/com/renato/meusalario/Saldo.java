package br.com.renato.meusalario;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Saldo extends AppCompatActivity {

    double valor, saldo, soma,salario;
    String despesa, data;
    String sinal;
    // double salario = 1400;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;
    Database db = new Database(this);
    private TextView txt_saldo;
    private EditText edt_despesa, edt_valor;
    private Button btn_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saldo);

        txt_saldo = findViewById(R.id.txt_saldo);
        edt_despesa = findViewById(R.id.edt_despesa);
        edt_valor = findViewById(R.id.edt_valor);
        btn_salvar = findViewById(R.id.btn_salvar);

        calcular();

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarDados();
                LimparCampos();
                calcular();
                // txt_saldo.setText(String.valueOf(saldo));
            }
        });
    }
    void calcular() {
        try {
            List<Dados> dados = db.soma();

            for (Dados d : dados) {
                // txt_saldo.setTextColor(Color.parseColor("#FF0000"));
                saldo = Double.parseDouble(String.valueOf(d.getSoma()));
                txt_saldo.setText(String.valueOf(saldo));
            }
        } catch (NullPointerException xe) {
        }
    }

    void SalvarDados() {

        despesa = edt_despesa.getText().toString();

        if (edt_despesa.getHint().equals("Despesa")) {
            sinal = "-";

        } else if (edt_despesa.getHint().equals("Receita")) {
            sinal = "+";

        }

        valor = Double.parseDouble(edt_valor.getText().toString());

        calendar = Calendar.getInstance();

        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        data = simpleDateFormat.format(calendar.getTime());


        db.AddDados(new Dados(valor, despesa, data, soma, sinal, salario));

        // Toast.makeText(Saldo.this,"Adicionado",Toast.LENGTH_LONG).show();

    }

    void LimparCampos() {
        edt_valor.setText("");
        edt_despesa.setText("");
    }

    void ChamaHistorico() {
        Intent intent = new Intent(Saldo.this, Historico.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.historico) {
            ChamaHistorico();
        }
        if (id == R.id.add) {
            edt_despesa.setVisibility(View.VISIBLE);
            edt_valor.setVisibility(View.VISIBLE);
            btn_salvar.setVisibility(View.VISIBLE);
            edt_despesa.setHint("Despesa");

        }
        if (id == R.id.add_receita) {
            edt_despesa.setVisibility(View.VISIBLE);
            edt_valor.setVisibility(View.VISIBLE);
            btn_salvar.setVisibility(View.VISIBLE);
            edt_despesa.setHint("Receita");
        }
        return super.onOptionsItemSelected(item);
    }
}
