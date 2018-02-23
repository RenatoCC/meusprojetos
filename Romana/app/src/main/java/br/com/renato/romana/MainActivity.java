package br.com.renato.romana;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_igual,btn_i,btn_v,btn_x,btn_l,btn_c,btn_d,btn_m,btn_apaga,btn_mais,btn_menos,btn_multiplica,btn_divide;
    private TextView txt_v1,txt_v2;
    private EditText edt_v1,edt_v2;
    private TextView txt_resultado;
    int i = 1;
    int v = 5;
    int x = 10;
    int l = 50;
    int c = 100;
    int d = 500;
    int m = 1000;
    int resultado;
    int v1,v2;
    String operador;
    int[] vetor = new int[5];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_igual = (Button) findViewById(R.id.btn_igual);
        btn_apaga = (Button) findViewById(R.id.btn_apaga);
        btn_i = (Button) findViewById(R.id.btn_i);
        btn_v = (Button) findViewById(R.id.btn_v);
        btn_x = (Button) findViewById(R.id.btn_x);
        btn_l = (Button) findViewById(R.id.btn_l);
        btn_c = (Button) findViewById(R.id.btn_c);
        btn_d = (Button) findViewById(R.id.btn_d);
        btn_m = (Button) findViewById(R.id.btn_m);
        btn_mais = (Button)findViewById(R.id.btn_mais);
        btn_menos = (Button)findViewById(R.id.btn_menos);
        btn_multiplica = (Button)findViewById(R.id.btn_multiplica);
        btn_divide = (Button)findViewById(R.id.btn_divide);

        edt_v1 = (EditText) findViewById(R.id.edt_v1);
        txt_resultado = (TextView)findViewById(R.id.txt_resultado);

        btn_igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v2 = Integer.parseInt(edt_v1.getText().toString());
                if (operador == "+") {
                    resultado = v1 + v2;
                    txt_resultado.setText(String.valueOf(resultado));
                }else
                    if(operador == "-"){
                        resultado = v1 - v2;
                        txt_resultado.setText(String.valueOf(resultado));
                    }else
                        if(operador == "*"){
                            resultado = v1 * v2;
                            txt_resultado.setText(String.valueOf(resultado));
                        }else
                            if(operador == "/"){
                                resultado = v1 / v2;
                                txt_resultado.setText(String.valueOf(resultado));
                            }
            }
        });
        btn_apaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_v1.setText("");
                txt_resultado.setText("");
            }
        });
        btn_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_v1.setText(edt_v1.getText() + "I");

            }
        });
        btn_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_v1.setText(edt_v1.getText() + "V");
            }
        });
        btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_v1.setText(edt_v1.getText() + "X");
            }
        });
        btn_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_v1.setText(edt_v1.getText() + "L");
            }
        });
        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_v1.setText(edt_v1.getText() + "C");
            }
        });
        btn_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_v1.setText(edt_v1.getText() + "D");
            }
        });
        btn_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_v1.setText(edt_v1.getText() + "M");
            }
        });
        btn_mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "+";
                v1 = Integer.parseInt(edt_v1.getText().toString());
                edt_v1.setText("");
            }
        });
        btn_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operador = "-";
                v1 = Integer.parseInt(edt_v1.getText().toString());
                edt_v1.setText("");
            }
        });
        btn_multiplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "*";
                v1 = Integer.parseInt(edt_v1.getText().toString());
                edt_v1.setText("");
            }
        });
        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operador = "/";
                v1 = Integer.parseInt(edt_v1.getText().toString());
                edt_v1.setText("");
            }
        });
    }
    public void teste(){
        if(edt_v1.getText().toString().equals("")){

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Alerta!!!");
            msg.setMessage("Numero incorreto");
            msg.setIcon(android.R.drawable.ic_dialog_alert);
            msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            msg.show();
        }else{
            edt_v1.setText("");
        }
    }

    @Override
    public void onClick(View view) {

    }

}
