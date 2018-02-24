package br.com.renato.testetromano;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_res;
    private EditText edt_tela;
    private TextView txt_res,txt_texto;

    char c;
    String roma;
    String t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_res = (Button) findViewById(R.id.btn_res);
        txt_res = (TextView) findViewById(R.id.txt_res);
        edt_tela = (EditText) findViewById(R.id.edt_tela);
        txt_texto = (TextView) findViewById(R.id.txt_texto);

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                roma = edt_tela.getText().toString();
                txt_texto.setText(roma);
                //c = roma;
               // txt_res.setText(roma.charAt(0));

            }
        });


    }
    @Override
    public void onClick(View v) {

    }
}
