package freijo.castro.diego.tareapmdm07_practicafinal.partidas;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;

public class EditarPartidaAtv extends AppCompatActivity {
    private EditText etReferencia, etConcepto, etPrecio;
    private Button btnGuardarContinuar, btnGuardarCerrar;

    private SQLiteDatabase baseDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_partida_atv);

        etReferencia=(EditText) findViewById(R.id.etReferencia);
        etConcepto=(EditText) findViewById(R.id.etConcepto);
        etPrecio=(EditText) findViewById(R.id.etPrecio);
        btnGuardarContinuar=(Button) findViewById(R.id.btnGuardarContinuar);
        btnGuardarCerrar=(Button) findViewById(R.id.btnGuardarCerrar);

        BaseDatos dbatos = new BaseDatos(this, "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();

        controlComponentes();
    }
    private void controlComponentes() {
        btnGuardarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPartida();
                etReferencia.setText("");
                etConcepto.setText("");
                etPrecio.setText("");
                etReferencia.requestFocus();
            }
        });
        btnGuardarCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPartida();
                finish();
            }
        });



    }

    private void guardarPartida() {
        String referencia=etReferencia.getText().toString();
        String concepto=etConcepto.getText().toString();
        String precio=etPrecio.getText().toString();

        if (!referencia.equals("") && !concepto.equals("") && !precio.equals("")){
            ContentValues registros=new ContentValues();
            registros.put("referencia", referencia);
            registros.put("concepto", concepto);
            registros.put("precio", precio);

            baseDatos.insert("partidas", null, registros);
        }else {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }


}
