package br.com.renato.kmcar;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Sobre extends AppCompatActivity{

    private TextView txt_texto;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sobre);

        txt_texto = findViewById(R.id.txt_texto);
    }
}
