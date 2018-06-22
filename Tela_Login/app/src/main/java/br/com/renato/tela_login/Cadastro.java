package br.com.renato.tela_login;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Cadastro extends AppCompatActivity {

    private TextView txt_nome,txt_sobrenome,txt_senha,txt_repete_senha;
    private EditText edt_nome,edt_sobrenome,edt_senha,edt_repete_senha;
    private Button btn_salvar;
    private ImageView img_foto;

    String nome,sobrenome,senha,senha2;
    byte[] foto;
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        txt_nome = findViewById(R.id.txt_nome);
        txt_sobrenome = findViewById(R.id.txt_sobrenome);
        txt_senha = findViewById(R.id.txt_senha);
        txt_repete_senha = findViewById(R.id.txt_repete_senha);

        edt_nome = findViewById(R.id.edt_nome);
        edt_sobrenome = findViewById(R.id.edt_sobrenome);
        edt_senha = findViewById(R.id.edt_senha);
        edt_repete_senha = findViewById(R.id.edt_repete_senha);

        btn_salvar = findViewById(R.id.btn_salvar);
        img_foto = findViewById(R.id.img_foto);

        //HABILITA A CAMERA
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
                chamarTela();
            }
        });
        img_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });
    }
    //TIRA A FOTO E MOSTRA NO IMAGEVIEW
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
            img_foto.setImageBitmap(imagen);
        }
    }

    void salvar(){
        nome = edt_nome.getText().toString();
        sobrenome = edt_sobrenome.getText().toString();
        senha = edt_senha.getText().toString();
        senha2 = edt_repete_senha.getText().toString();

        // METODO PARA CONVERTER E SALVAR A FOTO
        Bitmap bitmap = ((BitmapDrawable) img_foto.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte imageByte[] = stream.toByteArray();

        foto = imageByte;

        db.AddDados(new Dados(nome,sobrenome,senha,foto));
    }

    void chamarTela(){
        Intent intent = new Intent(Cadastro.this,MainActivity.class);
        startActivity(intent);
    }
}
