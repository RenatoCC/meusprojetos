package br.com.renato.meusalario;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Historico extends AppCompatActivity {

    private ListView lst_despesas;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    Database db = new Database(this);

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historico);


        lst_despesas = findViewById(R.id.lst_despesas);
        registerForContextMenu(lst_despesas);
        mostrar();
    }
    void mostrar() {
        List<Dados> dados = db.despesas();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(Historico.this, android.R.layout.simple_list_item_1, list);
        lst_despesas.setAdapter(adapter);

        for (Dados d : dados ){
            list.add(d.getDespesa() + "   <---- " + "  item " + "\n " +
                     "VALOR: " + "    " + d.getSinal()+d.getValor() + "\n " +
                     "DATA: " + "       " + d.getData() + " \n");
        }
    }
//--------------------------------------------------------------------------------------------------

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu2,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final int position = info.position;

        switch (item.getItemId()){
            case R.id.id_editar:
               // String conteudo = (String) lst_despesas.getItemAtPosition(position);

                break;

            case R.id.id_excluir:
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
                        String conteudo = (String) lst_despesas.getItemAtPosition(position);
                        String despesa = conteudo.substring(0, conteudo.indexOf("   <---- "));
                        Dados dados = new Dados();
                        dados.setDespesa(despesa);
                        db.apagar(dados);
                        Toast.makeText(Historico.this,"Item Excluido",Toast.LENGTH_SHORT).show();
                        mostrar();
                    }
                });
                msg.show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
