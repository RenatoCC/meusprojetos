        package br.com.renato.kmcar;

        import android.Manifest;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.provider.MediaStore;
        import android.support.v4.app.ActivityCompat;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.ByteArrayOutputStream;
        import java.util.ArrayList;


public  class MainActivity extends AppCompatActivity{

    private Button btn_salvar,btn_foto;
    private TextView txt_km_inicio, txt_km_final, txt_oleo, txt_filtro,txt_modelo,txt_placa;
    private EditText edt_km_1, edt_km_2, edt_oleo, edt_proprietario,edt_modelo,edt_placa;
    private RadioButton rb_sim, rb_nao;
    private ImageView img_imagem;
    private ImageButton img_botao;
    private ArrayList<String> list;
    private LinearLayout linear;


    int km_inicial, km_final;
    String nome_proprietario, nome_oleo, filtro_trocado,modelo,placa;
    byte[] foto;
    Database db = new Database(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_salvar = findViewById(R.id.btn_salvar);
        txt_filtro = findViewById(R.id.txt_filtro);
        txt_km_final = findViewById(R.id.txt_km_final);
        txt_km_inicio = findViewById(R.id.txt_km_inicio);
        txt_oleo = findViewById(R.id.txt_oleo);
        txt_modelo = findViewById(R.id.txt_modelo);

        img_botao = findViewById(R.id.img_botao);
        img_imagem = findViewById(R.id.img_imagem);
        edt_km_1 = findViewById(R.id.edt_km_1);
        edt_km_2 = findViewById(R.id.edt_km_2);
        edt_oleo = findViewById(R.id.edt_oleo);
        edt_placa = findViewById(R.id.edt_placa);
        edt_proprietario = findViewById(R.id.edt_proprietario);
        edt_modelo = findViewById(R.id.edt_modelo);
        rb_nao = findViewById(R.id.rb_nao);
        rb_sim = findViewById(R.id.rb_sim);

        linear = findViewById(R.id.linear);


     //HABILITA A CAMERA
    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},0 );
        }

//--------------------------------------------------------------------------------------------------

      //BOTÃO SALVAR COM OS METODOS SALVAR E LIMPAR OS CAMPOS
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvaDados();
                LimpaCampos();
                img_imagem.setImageResource(R.drawable.carro2);
            }
        });
        //BOTÃO DE FOTO COM O METODO DE TIRAR A FOTO
        img_botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });
//--------------------------------------------------------------------------------------------------
        //METODO QUE LIGA O BOTÃO SALVAR SOMENTE SE OS CAMPOS FOREM PREECHIDOS
        edt_oleo.addTextChangedListener(valida);
        edt_proprietario.addTextChangedListener(valida);
    }
//--------------------------------------------------------------------------------------------------
    private TextWatcher valida = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        nome_oleo = edt_oleo.getText().toString().trim();
        nome_proprietario = edt_proprietario.getText().toString().trim();

        btn_salvar.setEnabled(!nome_oleo.isEmpty() && !nome_oleo.isEmpty());
    }

    @Override
    public void afterTextChanged(Editable s) {

         }
    };

//--------------------------------------------------------------------------------------------------
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
            img_imagem.setImageBitmap(imagen);
        }
    }
//--------------------------------------------------------------------------------------------------
   //SALVA OS DADOS NO BANCO
    void SalvaDados() {
            placa = edt_placa.getText().toString();
            modelo = edt_modelo.getText().toString();
            km_inicial = Integer.parseInt(edt_km_1.getText().toString());
            km_final = Integer.parseInt(edt_km_2.getText().toString());
            nome_oleo = edt_oleo.getText().toString();
            if (rb_sim.isChecked()){

                filtro_trocado = "Sim";
            }
            if (rb_nao.isChecked()){
                filtro_trocado = "Não";
            }

            nome_proprietario = edt_proprietario.getText().toString();

    // METODO PARA CONVERTER E SALVAR A FOTO
        Bitmap bitmap = ((BitmapDrawable) img_imagem.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte imageByte[] = stream.toByteArray();

            foto = imageByte;

//--------------------------------------------------------------------------------------------------
        //TESTA OS VALORES ANTES DE SALVAR
        if(placa.equals("") || modelo.equals("") || String.valueOf(km_inicial).equals("") ||
                String.valueOf(km_final).equals("") || nome_oleo.equals("") || filtro_trocado.equals("")
                || nome_proprietario.equals("") || foto.equals(null)){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Alerta!!!");
            msg.setMessage("Preencha todos os campos");
            msg.setIcon(android.R.drawable.ic_dialog_alert);
            msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            msg.show();
        }
            //ADICIONAR OS DADOS NO BANCO
            db.AddDados(new Dados(km_inicial, km_final, placa, nome_oleo, filtro_trocado, nome_proprietario, modelo, foto));
            Toast.makeText(MainActivity.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();

    }
//--------------------------------------------------------------------------------------------------
   //LIMPA OS CAMPOS APOS SALVAR OS DADOS
    void LimpaCampos() {
        edt_placa.setText("");
        edt_proprietario.setText("");
        edt_oleo.setText("");
        edt_modelo.setText("");
        edt_km_2.setText("");
        edt_km_1.setText("");
        rb_sim.setChecked(false);
        rb_nao.setChecked(false);
        img_imagem.setImageResource(0);
        edt_placa.requestFocus();
    }

//--------------------------------------------------------------------------------------------------
  //CRIA O MENU DE OPÇÕES
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Cadastro) {
            ChamaTela();
            finish();
        }
        if (id == R.id.sair) {
            finish();
        }
        return true;
    }
//--------------------------------------------------------------------------------------------------
    //INICIA A TELA DE CONNSULTA
    void ChamaTela(){
        Intent it = new Intent(this,MainActivity2.class);
        startActivity(it);

    }

//--------------------------------------------------------------------------------------------------


}
