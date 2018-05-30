package br.com.renato.kmcar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

public class Apresentacao extends AppCompatActivity{
private TextView txt_placa,txt_p_placa,txt_veiculo,txt_v_veiculo,txt_km_inicial,txt_k1_inicial,txt_km_final,
    txt_k2_final,txt_oleo,txt_o_oleo,txt_prop,txt_prop_prop,txt_filtro,txt_f_filtro;

private ImageView img_foto;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apresentacao);

        txt_p_placa = findViewById(R.id.txt_p_placa);
        txt_placa = findViewById(R.id.txt_placa);
        txt_veiculo = findViewById(R.id.txt_veiculo);
        txt_v_veiculo = findViewById(R.id.txt_v_veiculo);
        txt_km_inicial = findViewById(R.id.txt_km_inicial);
        txt_k1_inicial = findViewById(R.id.txt_k1_inical);
        txt_km_final = findViewById(R.id.txt_km_final);
        txt_k2_final = findViewById(R.id.txt_k2_final);
        txt_oleo = findViewById(R.id.txt_oleo);
        txt_o_oleo = findViewById(R.id.txt_o_oleo);
        txt_veiculo = findViewById(R.id.txt_veiculo);
        txt_prop = findViewById(R.id.txt_prop);
        txt_prop_prop = findViewById(R.id.txt_pro_prop);
        txt_filtro = findViewById(R.id.txt_filtro);
        txt_f_filtro = findViewById(R.id.txt_f_filtro);
        img_foto = findViewById(R.id.img_foto);

        Dados dados = new Dados();
        Database db = new Database(this);


        String placa = getIntent().getStringExtra("placa");
        dados.setPlaca(placa);

        final List<Dados> dados1 = db.pesquisa(dados);

        for (Dados d : dados1) {
            txt_p_placa.setText(d.getPlaca());
            txt_v_veiculo.setText(d.getModelo());
            txt_k1_inicial.setText(String.valueOf(d.getKm_inicial()));
            txt_k2_final.setText(String.valueOf(d.getKm_final()));
            txt_o_oleo.setText(d.getNome_oleo());
            txt_prop_prop.setText(d.getNome_proprietario());
            txt_f_filtro.setText(d.getFiltro_trocado());

            byte[] outImage = d.getFoto();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
            Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
            img_foto.setImageBitmap(imageBitmap);

//--------------------------------------------------------------------------------------------------
        }
        //Intent intent = new Intent();
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}


