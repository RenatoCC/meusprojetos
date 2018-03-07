package br.com.renato.testeconcurso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_1, txt_2, txt_3;
    private Button btn_res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_1 = (TextView) findViewById(R.id.txt_1);
        txt_2 = (TextView) findViewById(R.id.txt_2);
        txt_3 = (TextView) findViewById(R.id.txt_3);

        btn_res = (Button) findViewById(R.id.btn_res);


        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 1;
                int b = 3;
                int c = 5;

                while (b != a & c < 20){

                    if (a > c){
                        c = c -2;
                    }else{
                        c = c + 2;
                        if (a + b < c){
                            a = b - a;
                            b = b + 2;
                        }
                    }
                }
                txt_1.setText(String.valueOf(a));
                txt_2.setText(String.valueOf(b));
                txt_3.setText(String.valueOf(c));

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}