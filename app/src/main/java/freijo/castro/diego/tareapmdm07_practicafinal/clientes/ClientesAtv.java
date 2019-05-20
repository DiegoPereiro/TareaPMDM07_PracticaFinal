package freijo.castro.diego.tareapmdm07_practicafinal.clientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;
import freijo.castro.diego.tareapmdm07_practicafinal.partidas.Partida;

public class ClientesAtv extends AppCompatActivity {
    private EditText etBuscar;
    private ListView lvClientes;
    private FloatingActionButton btnNuevo;


    private SQLiteDatabase baseDatos;

    private Cliente cliente;
    private ArrayList<Cliente> list = new ArrayList<>();
    private ClientesAdaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes_atv);

        etBuscar = (EditText) findViewById(R.id.etBuscar);
        lvClientes = (ListView) findViewById(R.id.lvClientes);
        btnNuevo = (FloatingActionButton) findViewById(R.id.btnNuevoPendiente);


        BaseDatos dbatos = new BaseDatos(this, "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lyInicio, new NuevoCliente()).addToBackStack(null).commit();
            }
        });


        cargarDatos("%");
    }

    private void cargarDatos(String buscar) {
        list.clear();

        Cursor csClientes = baseDatos.rawQuery("SELECT * FROM clientes where clientes.nombre like '" + buscar + "' ORDER BY nombre", null);
        while (csClientes.moveToNext()) {
//            cliente=new Cliente(csClientes.getString(1), csClientes.getString(2), csClientes.getString(3), csClientes.getString(4), csClientes.getString(5),csClientes.getString(6), csClientes.getString(7), csClientes.getString(8), csClientes.getString(9), csClientes.getString(10), csClientes.getString(11),csClientes.getString(12), csClientes.getString(13), csClientes.getString(14), csClientes.getString(15), csClientes.getString(16), csClientes.getString(17), csClientes.getString(18));

            cliente = new Cliente(csClientes.getInt(0), csClientes.getString(1), csClientes.getString(2), csClientes.getString(3), csClientes.getString(4),
                    csClientes.getString(5), csClientes.getString(6), csClientes.getString(7), csClientes.getString(8), csClientes.getString(9), csClientes.getString(10),
                    csClientes.getString(11), csClientes.getString(12), csClientes.getString(13), csClientes.getString(14), csClientes.getString(15), csClientes.getString(16),
                    csClientes.getString(17));

            list.add(cliente);
        }
        adapter = new ClientesAdaptador(this, list);
        lvClientes.setAdapter(adapter);
        lvClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente seleccion=(Cliente) adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("idCliente", seleccion.getId());
                setResult(RESULT_OK, intent);
                finish();


            }
        });


    }
}
