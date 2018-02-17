package gestaorsc.com.br.gestaorsc;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button btn_iniciar, btn_salvar, btn_pesquisa,btn_limpatela, btn_relatorio, btn_apaga;
	private ListView lst_resultado;
	private ArrayList<String> list;
	private ArrayAdapter<String> adapter;
	double res,qtt;

	Database db = new Database(this);

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);


		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

		btn_iniciar = (Button) findViewById(R.id.btn_iniciar);
		btn_salvar = (Button) findViewById(R.id.btn_salvar);
		btn_pesquisa = (Button) findViewById(R.id.btn_pesquisa);
		btn_limpatela = (Button) findViewById(R.id.btn_limpatela);
		lst_resultado = (ListView) findViewById(R.id.lst_resultado);
		btn_relatorio = (Button) findViewById(R.id.btn_relatorio);

		btn_iniciar.setOnClickListener(this);


		btn_pesquisa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				List<Dados> dados = db.mostrarTabela();
				List<Dados> dados4 = db.consulta4();
				list = new ArrayList<String>();
				adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
//CORRIGIR BOTÃO PESQUISA
				lst_resultado.setAdapter(adapter);
				for (Dados d4 : dados4) {
					for (Dados d : dados) {

						if (d4.getQuantidade_total() == 0) {

							Toast.makeText(MainActivity.this, "AINDA NÃO EXISTE PESQUISA CADASTRADA", Toast.LENGTH_LONG).show();
						} else

							list.add(d.getId_pessoa() + "º) " + "---------------------------------------------------------------" + " \n " + "\n"
									+ " SEXO: " + d.getSexo() + " " + "\n "
									+ "IDADE: " + d.getIdade() + " " + "Anos" + "\n"
									+ " ESCOLARIDADE: " + d.getEscolaridade() + " " + "\n "
									+ "Q_1 = " + d.q1 + " " + "\n "
									+ "Q_2 = " + d.q2 + " " + "\n "
									+ "Q_3 = " + d.q3 + " " + "\n "
									+ "Q_4 = " + d.q4 + " " + "\n "
									+ "Q_5 = " + d.q5 + " " + "\n "
									+ "Q_6 = " + d.q6 + " " + "\n "
									+ "Q_7 = " + d.q7 + "\n");
					}
				}
			}
		});

		btn_limpatela.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				try{
					if (adapter.isEmpty()) {

					}else{
						adapter.clear();
					}
				}	catch(NullPointerException ex){

				}
			}

		});

	   btn_relatorio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				List<Dados> dados1 = db.consulta();
				List<Dados> dados2 = db.consulta2();
				List<Dados> dados3 = db.consulta3();
				List<Dados> dados4 = db.consulta4();

				list = new ArrayList<String>();

				adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);

				lst_resultado.setAdapter(adapter);
					for (Dados d : dados4){
					DecimalFormat df = new DecimalFormat("0");
					qtt = d.getQuantidade_total();
					String dx = df.format(qtt);
		 			list.add(String.valueOf("TOTAL DE PESSOAS PESQUISADAS E: " + dx));
				}
				for(Dados t : dados1){
					list.add(String.valueOf("A QUANTIDADE DE PESSOAS QUE RESPONDERAM A LETRA (A) DA QUESTÃO 1 E LETRA (B) DA QUESTÃO 2 É: " + t.getQuantidade()));
				}
				for (Dados b : dados2 ){
					list.add(String.valueOf("A QUANTIDDE DE PESSOA COM ESCOLARIDADE ANALFABETO É " + b.getQuantidade2()));
				}
					for (Dados c : dados3) {
						for (Dados d : dados4) {
							DecimalFormat df = new DecimalFormat("0.00");
							res = c.getQuantidade3() / 100 * d.getQuantidade_total();
							String dx = df.format(res);
							list.add((dx + "% " + "-> " + " ACHAM QUE O LIXO PREJUDICA O MEIO AMBIENTE"));

						}
					}
			}

	   });

	}
	public void onClick(View view) {

		Intent it = new Intent(this, Main2Activity.class);
		startActivity(it);

	}
	public void excluiPesquisa(){
		final List<Dados> dados4 = db.consulta4();
		AlertDialog.Builder msg = new AlertDialog.Builder(this);

		msg.setTitle("Alerta!!!");
		msg.setMessage("Tem certeza que deseja apagar toda a pesquisa?");
		msg.setIcon(android.R.drawable.ic_menu_delete);
		msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

				try {
					for (Dados h : dados4) {
						if (h.getQuantidade_total() == 0) {
							Toast.makeText(MainActivity.this, "NÃO EXISTE PESQUISA CADASTRADA", Toast.LENGTH_LONG).show();
						} else {
							db.deleta();
							adapter.clear();
							Toast.makeText(MainActivity.this, "PESQUISA APAGADA", Toast.LENGTH_LONG).show();
						}
					}
				}catch (NullPointerException ex){
					for (Dados h : dados4) {
						if (h.getQuantidade_total() > 0) {
							db.deleta();
							Toast.makeText(MainActivity.this, "PESQUISA APAGADA", Toast.LENGTH_LONG).show();
						} else {
							adapter.clear();
							Toast.makeText(MainActivity.this, "NÃO EXISTE PESQUISA CADASTRADA", Toast.LENGTH_LONG).show();
						}
					}
				}
	}
		});
		msg.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		msg.show();

	}

	@Override
		public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.menu_main,menu);
		return true;
	}
	@Override
		public boolean onOptionsItemSelected(MenuItem item){
	int id = item.getItemId();

	if(id == R.id.action_settings){
	excluiPesquisa();

	}
	if(id == R.id.sair){
		finish();
	}
		return true;
		}
	}



