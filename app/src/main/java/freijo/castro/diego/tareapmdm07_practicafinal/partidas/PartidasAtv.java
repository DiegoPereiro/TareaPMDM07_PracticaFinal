package freijo.castro.diego.tareapmdm07_practicafinal.partidas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;
import freijo.castro.diego.tareapmdm07_practicafinal.clientes.Cliente;
import freijo.castro.diego.tareapmdm07_practicafinal.clientes.ClientesAdaptador;

public class PartidasAtv extends AppCompatActivity {
    private EditText etBuscar;
    private ListView lvPartidas;
    private FloatingActionButton btnNuevo;


    private SQLiteDatabase baseDatos;

    private Partida partida;
    private ArrayList<Partida> list = new ArrayList<>();
    private PartidasAdaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partidas_atv);

        etBuscar = (EditText) findViewById(R.id.etBuscar);
        lvPartidas = (ListView) findViewById(R.id.lvPartidas);
        btnNuevo = (FloatingActionButton) findViewById(R.id.btnNuevaPartida);


        BaseDatos dbatos = new BaseDatos(this, "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PartidasAtv.this, EditarPartidaAtv.class);
                startActivity(intent);
            }
        });

        cargarDatos("%");

    }
    private void cargarDatos(String buscar) {
        list.clear();

        Cursor csPartidas = baseDatos.rawQuery("select * from partidas where concepto like '" + buscar + "' ORDER BY concepto", null);
        while (csPartidas.moveToNext()) {
          partida = new Partida(csPartidas.getString(1),csPartidas.getString(2),0,csPartidas.getFloat(3));

            list.add(partida);
        }
        adapter = new PartidasAdaptador(this, list);

        lvPartidas.setAdapter(adapter);
        lvPartidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Partida seleccion=(Partida) adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("referencia", seleccion.getReferencia());
                intent.putExtra("concepto", seleccion.getConcepto());
                intent.putExtra("precio", seleccion.getPrecio());
                setResult(RESULT_OK, intent);
                finish();


            }
        });


    }
}
