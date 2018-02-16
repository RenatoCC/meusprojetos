package gestaorsc.com.br.gestaorsc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_salvar;
    private EditText edt_idade;
    private TextView txt_sexo, txt_idade, txt_escola, txt_questao;
    private TextView txt_q1,txt_q2,txt_q3,txt_q4,txt_q5,txt_q6,txt_q7;
    private RadioButton rb_q1_a,rb_q1_b, rb_q1_c, rb_q1_d, rb_q1_e;
    private RadioButton rb_q2_a, rb_q2_b;
    private RadioButton rb_q3_a, rb_q3_b;
    private RadioButton rb_q4_a,rb_q4_b,rb_q4_c,rb_q4_d;
    private RadioButton rb_q5_a,rb_q5_b,rb_q5_c,rb_q5_d,rb_q5_e;
    private RadioButton rb_q6_a,rb_q6_b,rb_q6_c,rb_q6_d,rb_q6_e,rb_q6_f;
    private RadioButton rb_m, rb_f, rb_anal, rb_efi,rb_efc,rb_emi,rb_emc,rb_esi,rb_esc;
    private RadioButton rb_q7_a, rb_q7_b;
    String q1,q2,q3,q4,q5,q6,q7;
    String sexo;
    int idade;
    int quantidade,quantidade2, quantidade3;
    String escolaridade;


    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_questao);

        //RADIOBUTTON SEXO
        rb_f = (RadioButton)findViewById(R.id.rb_f);
        rb_m = (RadioButton)findViewById(R.id.rb_m);

        //Button
        btn_salvar = (Button)findViewById(R.id.btn_salvar);
        btn_salvar.setOnClickListener(this);

        //TEXTVIEW
        txt_sexo = (TextView) findViewById(R.id.txt_sexo);
        txt_escola = (TextView) findViewById(R.id.txt_escola);
        txt_idade = (TextView) findViewById(R.id.txt_idade);
        txt_questao = (TextView) findViewById(R.id.txt_questao);

        //TEXTVIEW QUESTÃO
        txt_q1 = (TextView)findViewById(R.id.txt_q1);
        txt_q2 = (TextView)findViewById(R.id.txt_q2);
        txt_q3 = (TextView)findViewById(R.id.txt_q3);
        txt_q4 = (TextView)findViewById(R.id.txt_q4);
        txt_q5 = (TextView)findViewById(R.id.txt_q5);
        txt_q6 = (TextView)findViewById(R.id.txt_q6);
        txt_q7 = (TextView)findViewById(R.id.txt_q7);


        //EDITTEXT
        edt_idade = (EditText) findViewById(R.id.edt_idade);

        //RADIOBUTTON ESCOLARIDADE
        rb_anal = (RadioButton)findViewById(R.id.rb_anal);
        rb_efi = (RadioButton)findViewById(R.id.rb_efi);
        rb_efc = (RadioButton)findViewById(R.id.rb_efc);
        rb_emi = (RadioButton)findViewById(R.id.rb_emi);
        rb_emc = (RadioButton)findViewById(R.id.rb_emc);
        rb_esi = (RadioButton)findViewById(R.id.rb_esi);
        rb_esc = (RadioButton)findViewById(R.id.rb_esc);

        //RADIOBUTTON Q1
        rb_q1_a =  (RadioButton)findViewById(R.id.rb_q1_a);
        rb_q1_b =  (RadioButton)findViewById(R.id.rb_q1_b);
        rb_q1_c =  (RadioButton)findViewById(R.id.rb_q1_c);
        rb_q1_d =  (RadioButton)findViewById(R.id.rb_q1_d);
        rb_q1_e =  (RadioButton)findViewById(R.id.rb_q1_e);

        //RADIOBUTTON Q2
        rb_q2_a = (RadioButton)findViewById(R.id.rb_q2_a);
        rb_q2_b = (RadioButton)findViewById(R.id.rb_q2_b);

        //RADIOBUTTON Q3
        rb_q3_a = (RadioButton)findViewById(R.id.rb_q3_a);
        rb_q3_b = (RadioButton)findViewById(R.id.rb_q3_b);

        //RADIOBUTTON Q4
        rb_q4_a = (RadioButton)findViewById(R.id.rb_q4_a);
        rb_q4_b = (RadioButton)findViewById(R.id.rb_q4_b);
        rb_q4_c = (RadioButton)findViewById(R.id.rb_q4_c);
        rb_q4_d = (RadioButton)findViewById(R.id.rb_q4_d);

        //RADIOBUTTON Q5
        rb_q5_a = (RadioButton)findViewById(R.id.rb_q5_a);
        rb_q5_b = (RadioButton)findViewById(R.id.rb_q5_b);
        rb_q5_c = (RadioButton)findViewById(R.id.rb_q5_c);
        rb_q5_d = (RadioButton)findViewById(R.id.rb_q5_d);
        rb_q5_e = (RadioButton)findViewById(R.id.rb_q5_e);

        //RADIOBUTTON Q6
        rb_q6_a = (RadioButton)findViewById(R.id.rb_q6_a);
        rb_q6_b = (RadioButton)findViewById(R.id.rb_q6_b);
        rb_q6_c = (RadioButton)findViewById(R.id.rb_q6_c);
        rb_q6_d = (RadioButton)findViewById(R.id.rb_q6_d);
        rb_q6_e = (RadioButton)findViewById(R.id.rb_q6_e);
        rb_q6_f = (RadioButton)findViewById(R.id.rb_q6_f);

        //RADIOBUTTON Q7
        rb_q7_a = (RadioButton)findViewById(R.id.rb_q7_a);
        rb_q7_b = (RadioButton)findViewById(R.id.rb_q7_b);

    }

    void salvaDados() {

        if (rb_f.isChecked()) {
            sexo = "Feminino";
        } else if (rb_m.isChecked()) {
            sexo = "Masculino";
        }

         idade = Integer.parseInt(edt_idade.getText().toString());


        if (rb_anal.isChecked()) {
            escolaridade = "Analfabeto";
        } else if (rb_efi.isChecked()) {
            escolaridade = "Ensino  Fundamental Incompleto";

        } else if (rb_efc.isChecked()) {
            escolaridade = "Ensino Fundamental Completo";

        } else if (rb_emi.isChecked()) {

            escolaridade = "Ensimo Medio Incompleto";

        } else if (rb_emc.isChecked()) {
            escolaridade = "Ensino Medio Completo";

        } else if (rb_esi.isChecked()) {
            escolaridade = "Ensino Superior Incompleto";

        } else if (rb_esc.isChecked()) {
            escolaridade = "Ensino Superior Completo";

        }

        if (rb_q1_a.isChecked()) {
            q1 = "a";

        } else if (rb_q1_b.isChecked()) {
            q1 = "b";

        } else if (rb_q1_c.isChecked()) {
            q1 = "c";

        } else if (rb_q1_d.isChecked()) {
            q1 = "d";

        } else if (rb_q1_e.isChecked()) {
            q1 = "e";
        }

        if (rb_q2_a.isChecked()) {
            q2 = "a";
        } else if (rb_q2_b.isChecked()) {
            q2 = "b";
        }
        if (rb_q3_a.isChecked()) {
            q3 = "c";

        } else if (rb_q3_b.isChecked()) {
            q3 = "d";
        }

        if (rb_q4_a.isChecked()) {
            q4 = "a";

        } else if (rb_q4_b.isChecked()) {
            q4 = "b";
        } else if (rb_q4_c.isChecked()) {
            q4 = "c";
        } else if (rb_q4_d.isChecked()) {
            q4 = "d";
        }

        if (rb_q5_a.isChecked()) {
            q5 = "a";
        } else if (rb_q5_b.isChecked()) {
            q5 = "b";

        } else if (rb_q5_c.isChecked()) {
            q5 = "c";

        } else if (rb_q5_d.isChecked()) {
            q5 = "d";

        } else if (rb_q5_e.isChecked()) {
            q5 = "e";
        }

        if (rb_q6_a.isChecked()) {
            q6 = "a";
        } else if (rb_q6_b.isChecked()) {
            q6 = "b";

        } else if (rb_q6_c.isChecked()) {
            q6 = "c";

        } else if (rb_q6_d.isChecked()) {
            q6 = "d";

        } else if (rb_q6_e.isChecked()) {
            q6 = "e";

        } else if (rb_q6_f.isChecked()) {
            q6 = "f";
        }
        if (rb_q7_a.isChecked()) {
            q7 = "a";
        } else if (rb_q7_b.isChecked()) {
            q7 = "b";
        }
        if(sexo == null){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);

            msg.setTitle("Alerta!!!");
            msg.setMessage("Campo sexo não selecionado");
            msg.setIcon(android.R.drawable.ic_dialog_alert);
            msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    rb_m.requestFocus();

                }
            });
            msg.show();
        }else

        if( idade > 120) {
            AlertDialog.Builder msg = new AlertDialog.Builder(this);

            msg.setTitle("Alerta!!!");
            msg.setMessage("Idade não permitida");
            msg.setIcon(android.R.drawable.ic_dialog_alert);
            msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            msg.show();
        }else
            if(escolaridade == null) {

                AlertDialog.Builder msg = new AlertDialog.Builder(this);

                msg.setTitle("Alerta!!!");
                msg.setMessage("Selecione uma escolaridade");
                msg.setIcon(android.R.drawable.ic_dialog_alert);
                msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                msg.show();
        }else
            if( q1 == null || q2 == null || q3 == null || q4 == null || q5 == null || q6 == null || q7 == null){

                AlertDialog.Builder msg = new AlertDialog.Builder(this);

                msg.setTitle("Alerta!!!");
                msg.setMessage("Existem questões sem responder");
                msg.setIcon(android.R.drawable.ic_dialog_alert);
                msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                msg.show();

            }else
                {

            db.addDados(new Dados(sexo, idade, escolaridade, quantidade,quantidade2,quantidade3, q1, q2, q3, q4, q5, q6, q7));

            Toast.makeText(Main2Activity.this, "Salvo com Sucesso", Toast.LENGTH_LONG).show();

            finish();
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
    }
    /*public void pegaPosição(){
       if(rb_m.isChecked()){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);

            msg.setTitle("Alerta!!!");
            msg.setMessage("Idade não permitida");
            msg.setIcon(android.R.drawable.ic_menu_delete);
            msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    txt_sexo.requestLayout();
                }
            });
            msg.show();
        }else
            if(rb_anal.equals(false) || rb_efi.equals(null) || rb_efc.equals(null) || rb_emi.equals(null) || rb_emc.equals(null) || rb_esi.equals(null) || rb_esc.equals(null)){

                AlertDialog.Builder msg = new AlertDialog.Builder(this);

                msg.setTitle("Alerta!!!");
                msg.setMessage("Idade não permitida");
                msg.setIcon(android.R.drawable.ic_menu_delete);
                msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txt_escola.requestLayout();
                    }
                });
                msg.show();
        }
    }*/

    @Override
    public void onClick(View view) {
        salvaDados();
    }
}


