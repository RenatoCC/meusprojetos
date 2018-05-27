package br.com.renato.kmcar;



import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ListView lst_dados;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private Button btn_pesquisa;
    private EditText edt_placa3, edt_pesquisa;
    private ImageView img_carro;
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_dados);

        lst_dados = findViewById(R.id.lst_dados);
        btn_pesquisa = findViewById(R.id.btn_pesquisa);
        edt_pesquisa = findViewById(R.id.edt_pesquisa);
        img_carro = findViewById(R.id.img_carro);

        registerForContextMenu(lst_dados);

        List<Dados> dados = db.Cadastro();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, list);
        lst_dados.setAdapter(adapter);
        for(Dados d : dados){
            list.add(
                    d.placa + "\n " + "\n"
                            + "PROPRIETARIO: " + d.nome_proprietario + "\n " + "\n"
                            + "MODELO: " + d.modelo + " \n " + "\n "
                            + "KM INICIAL: " + d.km_inicial + " \n " + "\n "
                            + "KM FINAL: " + d.km_final + " \n " + "\n "
                            + "ÓLEO: " + d.nome_oleo + " \n " + "\n "
                            + "FILTRO_TROCADO: " + d.filtro_trocado + " \n ");
        }
        img_carro.setImageResource(0);
        adapter.notifyDataSetChanged();


    //--------------------------------------------------------------------------------------------------
        btn_pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dados dados = new Dados();
                String placa = edt_pesquisa.getText().toString();
                dados.setPlaca(placa);
                    db.pesquisa(dados);
                    edt_pesquisa.setText("");
                    resultado(dados);
            }
        });
//--------------------------------------------------------------------------------------------------
        lst_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) lst_dados.getItemAtPosition(position);
                String placa = conteudo.substring(0, conteudo.indexOf("\n"));

                Intent intent = new Intent(MainActivity2.this, Atualizar.class);
                intent.putExtra("placa", placa);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
//--------------------------------------------------------------------------------------------------
        edt_pesquisa.addTextChangedListener(valida);
    }
//--------------------------------------------------------------------------------------------------
    private TextWatcher valida = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String placa  = edt_pesquisa.getText().toString().trim();

            btn_pesquisa.setEnabled(!placa.isEmpty());

        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };

//--------------------------------------------------------------------------------------------------
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu3,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final int position = info.position;

        switch (item.getItemId()){
            case R.id.id_editar:
                 String conteudo = (String) lst_dados.getItemAtPosition(position);

                 String placa = conteudo.substring(0, conteudo.indexOf("\n"));
                 Intent intent = new Intent(MainActivity2.this, Atualizar.class);
                 intent.putExtra("placa", placa);
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);
                 finish();

                 break;
            case R.id.id_excluir:
                final String conteudo2 = (String) lst_dados.getItemAtPosition(position);
                AlertDialog.Builder msg = new AlertDialog.Builder(this);

                msg.setTitle("Alerta!!!");
                msg.setMessage("Tem certeza que deseja pagar");
                msg.setIcon(android.R.drawable.ic_dialog_alert);
                msg.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                String placa2 = conteudo2.substring(0, conteudo2.indexOf("\n"));
                Dados dados = new Dados();
                dados.setPlaca(placa2);
                db.apagar(dados);
                mostrar();
                Toast.makeText(MainActivity2.this,"Item Excluido",Toast.LENGTH_LONG).show();
                finish();
                    }
                });
                msg.show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    //--------------------------------------------------------------------------------------------------
   void mostrar(){


   }

    void resultado(Dados dados) {
        List<Dados> dados1 = db.pesquisa(dados);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, list);
        lst_dados.setAdapter(adapter);
        for (Dados d : dados1) {
                list.add(
                         d.placa + "\n " + "\n"
                                + "PROPRIETARIO: " + d.nome_proprietario + "\n " + "\n"
                                + "MODELO: " + d.modelo + " \n " + "\n "
                                + "KM INICIAL: " + d.km_inicial + " \n " + "\n "
                                + "KM FINAL: " + d.km_final + " \n " + "\n "
                                + "ÓLEO: " + d.nome_oleo + " \n " + "\n "
                                + "FILTRO_TROCADO: " + d.filtro_trocado + " \n ");


           byte[] outImage = d.getFoto();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
            Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
            img_carro.setImageBitmap(imageBitmap);

        adapter.notifyDataSetChanged();
        }
    }
 //-------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
    void alerta() {
        AlertDialog.Builder msg = new AlertDialog.Builder(this);

        msg.setTitle("Alerta!!!");
        msg.setMessage("Tem certeza que deseja pagar");
        msg.setIcon(android.R.drawable.ic_dialog_alert);
        msg.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Dados dados = new Dados();
                String placa = edt_placa3.getText().toString();

                dados.setPlaca(placa);
                db.apagar(dados);
                edt_placa3.setText("");
                Toast.makeText(MainActivity2.this,"Carro apagado",Toast.LENGTH_LONG).show();
                mostrar();
            }
        });
        msg.show();
        }
//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.carros) {
            mostrar();
        }
        if(id == R.id.deleta){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);

            msg.setTitle("Alerta!!!");
            msg.setMessage("Tem certeza que deseja pagar");
            msg.setIcon(android.R.drawable.ic_dialog_alert);
            msg.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                        db.ApagaTudo();
                        LimpaCampos();
                        Toast.makeText(MainActivity2.this, "Cadastro apagado", Toast.LENGTH_LONG).show();

                }
            });
            msg.show();
        }
        return true;
    }
    void LimpaCampos(){
       try{
           if(adapter.isEmpty()){

           }else {
               adapter.clear();
               edt_pesquisa.setText("");
               img_carro.setImageResource(0);
           }
       }catch (NullPointerException ex){

       }
    }
}

