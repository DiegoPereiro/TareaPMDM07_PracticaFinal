package freijo.castro.diego.tareapmdm07_practicafinal.clientes;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.Inicio;
import freijo.castro.diego.tareapmdm07_practicafinal.partidas.EditarPartidaAtv;

public class EditarClienteAtv extends AppCompatActivity {
    private ImageView btnEliminar;
    private TextView tvTitulo;
    private EditText etCif, etNombre, etVia, etDireccion, etNumero, etEscalera, etPiso, etPuerta, etCodigoPostal, etMunicipio, etProvincia, etPais, etTelefono1, etTelefono2, etFax, etEmail, etIban, etPago1, etPago2, etPago3, etPago4;
    private Spinner spFormaPago;
    private LinearLayout lyPagos;
    private CheckBox cbImprimirIbanEmpresa, cbRecargo;
    private Button btnAceptar;

    private SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_cliente_atv);

        //        btnEliminar=(ImageView) actionBar.getCustomView().findViewById( R.id.btnEliminar);
//        tvTitulo=(TextView) actionBar.getCustomView().findViewById(R.id.tvTitulo);
        etCif = (EditText) findViewById(R.id.etCif);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etVia = (EditText) findViewById(R.id.etVia);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etNumero = (EditText) findViewById(R.id.etNumero);
        etEscalera = (EditText) findViewById(R.id.etEscalera);
        etPiso = (EditText) findViewById(R.id.etPiso);
        etPuerta = (EditText) findViewById(R.id.etPuerta);
        etCodigoPostal = (EditText) findViewById(R.id.etCodidoPostal);
        etMunicipio = (EditText) findViewById(R.id.etPoblacion);
        etProvincia = (EditText) findViewById(R.id.etProvincia);
        etPais = (EditText) findViewById(R.id.etPais);
        etTelefono1 = (EditText) findViewById(R.id.etTelefono1);
        etTelefono2 = (EditText) findViewById(R.id.etTelefono2);
        etFax = (EditText) findViewById(R.id.etFax);
        etEmail = (EditText) findViewById(R.id.etEmail);
        spFormaPago = (Spinner) findViewById(R.id.spFormaPago);
        etIban = (EditText) findViewById(R.id.etIban);
        etPago1 = (EditText) findViewById(R.id.etPago1);
        etPago2 = (EditText) findViewById(R.id.etPago2);
        etPago3 = (EditText) findViewById(R.id.etPago3);
        etPago4 = (EditText) findViewById(R.id.etPago4);
        lyPagos = (LinearLayout) findViewById(R.id.lyPagos);
        cbImprimirIbanEmpresa = (CheckBox) findViewById(R.id.cbImprimirIbanEmpresa);
        cbRecargo = (CheckBox) findViewById(R.id.cbRecargo);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);

        BaseDatos dbatos = new BaseDatos(this, "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();


        controlComponentes();
    }
    private void controlComponentes() {

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etCif.getText().toString().equals("") && !etNombre.getText().toString().equals("")) {
                    ContentValues registros = new ContentValues();
                    registros.put("cif", etCif.getText().toString());
                    registros.put("nombre", etNombre.getText().toString());
                    registros.put("via", etVia.getText().toString());
                    registros.put("direccion", etDireccion.getText().toString());
                    registros.put("numero", etNumero.getText().toString());
                    registros.put("escalera", etEscalera.getText().toString());
                    registros.put("piso", etPiso.getText().toString());
                    registros.put("puerta", etPuerta.getText().toString());
                    registros.put("codigopostal", etCodigoPostal.getText().toString());
                    registros.put("poblacion", etMunicipio.getText().toString());
                    registros.put("provincia", etProvincia.getText().toString());
                    registros.put("pais", etPais.getText().toString());
                    registros.put("telefono", etTelefono1.getText().toString());
                    registros.put("telefono2", etTelefono2.getText().toString());
                    registros.put("fax", etFax.getText().toString());
                    registros.put("email", etEmail.getText().toString());
                    registros.put("iban", etIban.getText().toString());
                    int numPagos = 0;
                    ;
                    if (!etPago1.getText().toString().equals("")) {
                        numPagos++;
                    }
                    if (!etPago2.getText().toString().equals("")) {
                        numPagos++;
                    }
                    if (!etPago3.getText().toString().equals("")) {
                        numPagos++;
                    }
                    if (!etPago4.getText().toString().equals("")) {
                        numPagos++;
                    }
                    registros.put("numpagos", numPagos);
                    registros.put("pago1", etPago1.getText().toString());
                    registros.put("pago2", etPago2.getText().toString());
                    registros.put("pago3", etPago3.getText().toString());
                    registros.put("pago4", etPago4.getText().toString());
                    registros.put("formapago", spFormaPago.getSelectedItem().toString());
                    registros.put("recargo", String.valueOf(cbRecargo.isChecked()));


                    baseDatos.insert("clientes", null, registros);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "El CIF y el nombre son obligatorios", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}
