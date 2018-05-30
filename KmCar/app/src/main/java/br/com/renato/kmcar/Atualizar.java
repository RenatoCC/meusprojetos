package br.com.renato.kmcar;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class Atualizar extends AppCompatActivity {
    private Button btn_atualizar;
    private TextView txt_placa2,txt_modelo2,txt_km_inicio2,txt_km_final2,txt_oleo2,txt_proprietario,txt_filtro2,txt_id_placa;
    private EditText edt_modelo2,edt_km_1_2,edt_km_2_2,edt_oleo2,edt_proprietario2;
    private RadioButton rb_sim2,rb_nao2;
    private ImageButton img_botao2;
    private ImageView img_imagem2;

    String  filtro_trocado2;

    Database db = new Database(this);
    Dados dados = new Dados();
    MainActivity2 main = new MainActivity2();


    @Override
    protected void onCreate( Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_atualizar);


        btn_atualizar = findViewById(R.id.btn_atualizar);
        txt_filtro2 = findViewById(R.id.txt_filtro2);
        txt_id_placa = findViewById(R.id.txt_id_placa);
        txt_km_final2 = findViewById(R.id.txt_km_final2);
        txt_km_inicio2 = findViewById(R.id.txt_km_inicio2);
        txt_oleo2 = findViewById(R.id.txt_oleo2);
        txt_modelo2 = findViewById(R.id.txt_modelo2);
        txt_proprietario = findViewById(R.id.txt_proprietario);
        txt_placa2 = findViewById(R.id.txt_placa2);

        img_botao2 = findViewById(R.id.img_botao2);
        img_imagem2 = findViewById(R.id.img_imagem2);
        edt_km_1_2 = findViewById(R.id.edt_km_1_2);
        edt_km_2_2 = findViewById(R.id.edt_km_2_2);
        edt_oleo2 = findViewById(R.id.edt_oleo2);
       // edt_placa2 = findViewById(R.id.edt_placa2);
        edt_proprietario2 = findViewById(R.id.edt_proprietario2);
        edt_modelo2 = findViewById(R.id.edt_modelo2);
        rb_nao2 = findViewById(R.id.rb_nao2);
        rb_sim2 = findViewById(R.id.rb_sim2);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }
            String placa = getIntent().getStringExtra("placa");
            dados.setPlaca(placa);

            final List<Dados> dados1 = db.pesquisa(dados);

            for (Dados d : dados1) {
                txt_id_placa.setText(d.getPlaca());
                edt_modelo2.setText(d.getModelo());
                edt_km_1_2.setText(String.valueOf(d.getKm_inicial()));
                edt_km_2_2.setText(String.valueOf(d.getKm_final()));
                edt_oleo2.setText(d.getNome_oleo());
                edt_proprietario2.setText(d.getNome_proprietario());

                if (d.getFiltro_trocado().equals("Sim")) {
                    rb_sim2.setChecked(true);
                } else {
                    rb_nao2.setChecked(true);
                }

                byte[] outImage = d.getFoto();
                ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
                Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
                img_imagem2.setImageBitmap(imageBitmap);
            }

            img_botao2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tirarFoto();
                }
            });
//--------------------------------------------------------------------------------------------------
            btn_atualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dados.setPlaca(txt_id_placa.getText().toString());
                    dados.setModelo(edt_modelo2.getText().toString());
                    dados.setKm_inicial(Integer.parseInt(edt_km_1_2.getText().toString()));
                    dados.setKm_final(Integer.parseInt(edt_km_2_2.getText().toString()));
                    dados.setNome_oleo(edt_oleo2.getText().toString());

                    if (rb_sim2.isChecked()) {
                        filtro_trocado2 = "Sim";
                    } else
                    if (rb_nao2.isChecked()) {
                        filtro_trocado2 = "Não";
                    }
                    dados.setFiltro_trocado(filtro_trocado2);
                    dados.setNome_proprietario(edt_proprietario2.getText().toString());

                    // METODO PARA CONVERTER E SALVAR A FOTO
                    Bitmap bitmap = ((BitmapDrawable) img_imagem2.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                    byte imageByte[] = stream.toByteArray();

                    dados.setFoto(imageByte);

                    db.atualizaCarro(dados);
                    chamaTela();
                    Toast.makeText(Atualizar.this,"Carro Atualizado",Toast.LENGTH_LONG).show();
                    //finish();
                }
            });
        }
//--------------------------------------------------------------------------------------------------
    void tirarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagen = (Bitmap) extras.get("data");
            img_imagem2.setImageBitmap(imagen);
        }
    }

//--------------------------------------------------------------------------------------------------

     void chamaTela(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
             finish();
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }

        }
