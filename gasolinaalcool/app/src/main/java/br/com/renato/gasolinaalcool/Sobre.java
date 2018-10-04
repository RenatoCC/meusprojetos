package br.com.renato.gasolinaalcool;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Sobre extends AppCompatActivity {

    private TextView txt_sobre,txt_exp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sobre);

        txt_sobre = findViewById(R.id.txt_sobre);
        txt_exp = findViewById(R.id.txt_exp);
    }
}
