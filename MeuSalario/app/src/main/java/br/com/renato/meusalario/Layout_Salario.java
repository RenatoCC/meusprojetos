package br.com.renato.meusalario;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Layout_Salario extends AppCompatActivity {

    private TextView txt_salario;
    private Button btn_salvar_salario;
    private EditText edt_salario;
    Database db = new Database(this);

    double salario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salario);

        txt_salario = findViewById(R.id.txt_salario);
        btn_salvar_salario = findViewById(R.id.btn_salvar_salario);
        edt_salario = findViewById(R.id.edt_salario);


        btn_salvar_salario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar_salario();
                Intent intent = new Intent(Layout_Salario.this,Saldo.class);
                startActivity(intent);
            }
        });
    }

    void salvar_salario(){
        salario = Double.parseDouble(edt_salario.getText().toString());
        db.Add_Salario(new Dados(salario));

    }
}
