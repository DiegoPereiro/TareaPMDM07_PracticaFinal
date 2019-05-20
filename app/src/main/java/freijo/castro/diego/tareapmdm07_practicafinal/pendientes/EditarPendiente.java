package freijo.castro.diego.tareapmdm07_practicafinal.pendientes;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;
import freijo.castro.diego.tareapmdm07_practicafinal.clientes.ClientesAtv;
import freijo.castro.diego.tareapmdm07_practicafinal.partidas.PartidasAtv;

public class EditarPendiente extends AppCompatActivity {
    private int id, idcliente;
    private LinearLayout lyFecha, lyCliente;
    private TextView tvFecha, tvCifCliente, tvNombreCliente, tvDireccionCliente, tvFormaPago, tvPlazosPagos, tvTotal;
    private EditText etReferencia, etConcepto, etCantidad, etPrecio;
    private ImageView btnBuscarPartida;
    private Button btnGuardarContinuar, btnGuardarCerrar;


    private Date hoy=new Date();
    private static final String CERO="0";
    private static final String BARRA="/";
    private Calendar calendar=Calendar.getInstance();

    private final int SLCCLIENTE=0;
    private final int SLCPARTIDA=1;

    private SQLiteDatabase baseDatos;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_pendiente);

        lyFecha=(LinearLayout) findViewById(R.id.lyFecha);
        tvFecha=(TextView) findViewById(R.id.tvFecha);
        lyCliente=(LinearLayout) findViewById(R.id.lyCliente);
        tvCifCliente = (TextView) findViewById(R.id.tvCifCliente);
        tvNombreCliente = (TextView) findViewById(R.id.tvNombreCliente);
        tvDireccionCliente = (TextView) findViewById(R.id.tvDireccionCliente);
        tvFormaPago = (TextView) findViewById(R.id.tvFormaPago);
        tvPlazosPagos = (TextView) findViewById(R.id.tvPlazosPagos);
        btnBuscarPartida=(ImageView) findViewById(R.id.btnBuscarPartida);
        etReferencia=(EditText) findViewById(R.id.etReferencia);
        etConcepto=(EditText) findViewById(R.id.etConcepto);
        etCantidad=(EditText) findViewById(R.id.etCatidad);
        etPrecio=(EditText) findViewById(R.id.etPrecio);
        tvTotal=(TextView) findViewById(R.id.tvTotal);
        btnGuardarContinuar=(Button) findViewById(R.id.btnGuardarContinuar);
        btnGuardarCerrar=(Button) findViewById(R.id.btnGuardarCerrar);

        BaseDatos dbatos = new BaseDatos(this, "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();


        if (id==0){
            tvFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(hoy));

        }

        controlComponentes();
    }

       private void controlComponentes() {
        lyFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int anio = calendar.get(Calendar.YEAR);

                DatePickerDialog recogerFecha = new DatePickerDialog(EditarPendiente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final int mesActual = month + 1;
                        String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                        String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                        tvFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                    }
                }, anio, mes, dia);
                recogerFecha.show();

            }
        });
        lyCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditarPendiente.this, ClientesAtv.class);
                startActivityForResult(intent, SLCCLIENTE);
            }
        });

        btnBuscarPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditarPendiente.this, PartidasAtv.class);
                startActivityForResult(intent, SLCPARTIDA);
                etCantidad.requestFocus();
            }
        });
        etCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etCantidad.getText().toString().equals("") && !etPrecio.getText().toString().equals("")){
                    float cantidad= Float.parseFloat(etCantidad.getText().toString().replace(",","."));
                    float precio=Float.parseFloat(etPrecio.getText().toString().replace(",","."));
                    tvTotal.setText(decimalFormat.format(cantidad*precio));
                }else{
                    tvTotal.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnGuardarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPendiente();
                etReferencia.setText("");
                etConcepto.setText("");
                etCantidad.setText("");
                etPrecio.setText("");
                etReferencia.requestFocus();
            }
        });
        btnGuardarCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPendiente();
                finish();
            }
        });



    }

    private void guardarPendiente() {
        if (idcliente!=0 && !etConcepto.getText().toString().equals("") && !etCantidad.getText().toString().equals("") && !etPrecio.getText().toString().equals("") ){


            ContentValues registros=new ContentValues();
            registros.put("fecha", tvFecha.getText().toString());
            registros.put("idcliente", idcliente);
            registros.put("referencia", etReferencia.getText().toString());
            registros.put("concepto", etConcepto.getText().toString());
            registros.put("cantidad", Float.parseFloat(etCantidad.getText().toString().replace(",",".")));
            registros.put("precio", Float.parseFloat(etPrecio.getText().toString().replace(",",".")));

            if (id==0){
                baseDatos.insert("pendientes", null, registros);
                Log.e("sdf", "guardado");
            }else {

            }

        }else {
            Toast.makeText(this, "Debe poner el cliente, el concepto, cantidad y el precio", Toast.LENGTH_LONG).show();
        }












    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== RESULT_OK){
            switch (requestCode){
                case 0:
                    idcliente= data.getIntExtra("idCliente", 0);
                    cargarCliente(idcliente);
                    break;
                case 1:

                    String referencia=data.getStringExtra("referencia");
                    String concepto=data.getStringExtra("concepto");
                    float precio=data.getFloatExtra("precio", 0);

                    etReferencia.setText(referencia);
                    etConcepto.setText(concepto);
                    etPrecio.setText(decimalFormat.format(precio).replace(".", ","));

                    break;

            }

        }

    }

    private void cargarCliente(int idCliente) {
        Cursor cliente = baseDatos.rawQuery("select * from clientes where id='" + idCliente + "'", null);
        if (cliente.moveToFirst()) {
            tvCifCliente.setText(cliente.getString(1));
            tvNombreCliente.setText(cliente.getString(2));


            String strDireccion = "";
            if (cliente.getString(3) != "") {
                strDireccion = strDireccion + cliente.getString(3);
            }
            if (cliente.getString(4) != "") {
                strDireccion = strDireccion + " " + cliente.getString(4);
            }
            if (cliente.getString(5) != "") {
                strDireccion = strDireccion + ", " + cliente.getString(5);
            }
            if (cliente.getString(6) != "") {
                strDireccion = strDireccion + " Esc." + cliente.getString(6);
            }
            if (cliente.getString(7) != "") {
                strDireccion = strDireccion + " " + cliente.getString(7);
            }
            if (cliente.getString(8) != "") {
                strDireccion = strDireccion + cliente.getString(8);
            }
            strDireccion = strDireccion + " - ";
            if (cliente.getString(9) != "") {
                strDireccion = strDireccion + cliente.getString(9);
            }
            if (cliente.getString(10) != "") {
                strDireccion = strDireccion + " " + cliente.getString(10);
            }
            if (cliente.getString(11) != "") {
                strDireccion = strDireccion + " " + cliente.getString(11);
            }
            tvDireccionCliente.setText(strDireccion);

            tvFormaPago.setText(cliente.getString(23));
            String plazos = "";

            if (cliente.getInt(18) != 0) {
                plazos = String.valueOf(cliente.getInt(18) + " plazos de ");


                if (!cliente.getString(19).equals("")) {
                    plazos = plazos + cliente.getString(19);
                }
                if (!cliente.getString(20).equals("")) {
                    plazos = plazos + ", " + cliente.getString(20);
                }
                if (!cliente.getString(21).equals("")) {
                    plazos = plazos + ", " + cliente.getString(21);
                }
                if (!cliente.getString(22).equals("")) {
                    plazos = plazos + ", " + cliente.getString(22);
                }
                plazos = plazos + " dias";
            }
            tvPlazosPagos.setText(plazos);
        }

    }


}
