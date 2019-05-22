package freijo.castro.diego.tareapmdm07_practicafinal.facturas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;

public class EditarFacturaAtv extends AppCompatActivity {
    private int id;

    private ImageView btnEliminar, ivBuscarFecha, ivBuscarCliente, ivBuscarProductos;
    private TextView tvTitulo, tvCifCliente, tvNumero, tvSerie;
    private TextView tvFecha, tvNombreCliente, tvDireccionCliente, tvFormaPago, tvPlazosPagos, tvBaseImponible, tvCuotaIva, tvTotalAutoventa;
    private ListView lvDetalle;
    private Button btnGuardarCerrar, btnGuardarEnviar;
    private LinearLayout lyBotonesAccion;
    private Spinner spDiaReparto;

    private SQLiteDatabase baseDatos;
    private PartidaFactura partidaFactura;
    private ArrayList<PartidaFactura> list = new ArrayList<>();
    private PartidasFacturaAdaptador adapter;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_factura_atv);


        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvCifCliente = (TextView) findViewById(R.id.tvCifCliente);
        tvNombreCliente = (TextView) findViewById(R.id.tvNombreCliente);
        tvDireccionCliente = (TextView) findViewById(R.id.tvDireccionCliente);
        tvFormaPago = (TextView) findViewById(R.id.tvFormaPago);
        tvPlazosPagos = (TextView) findViewById(R.id.tvPlazosPagos);
        tvNumero = (TextView) findViewById(R.id.tvNumero);
        lvDetalle = (ListView) findViewById(R.id.lvDetalle);
        tvBaseImponible = (TextView) findViewById(R.id.tvBaseImponible);
        tvCuotaIva = (TextView) findViewById(R.id.tvCuotaIva);
        tvTotalAutoventa = (TextView) findViewById(R.id.tvTotalFactura);
        btnGuardarCerrar = (Button) findViewById(R.id.btnGuardarCerrar);


        id = getIntent().getIntExtra("id", 0);


        BaseDatos dbatos = new BaseDatos(this, "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();

        cargarDatos();
    }

    private void cargarDatos() {
        Cursor csFactura = baseDatos.rawQuery("select * from facturas where id=" + id, null);
        if (csFactura.moveToFirst()) {
            tvFecha.setText(csFactura.getString(2));
            tvNumero.setText(csFactura.getString(1));
            cargarCliente(csFactura.getInt(3));
            cargarPartidas();
        }
    }

    private void cargarPartidas() {
        Log.e("sdf", "select * from facturaspartidas where idfactura="+id);
        Cursor csPartidas=baseDatos.rawQuery("select * from facturaspartidas where idfactura="+id, null);
        while (csPartidas.moveToNext()){
            partidaFactura=new PartidaFactura(csPartidas.getInt(0),csPartidas.getInt(1),csPartidas.getString(2),csPartidas.getString(3),
                    csPartidas.getFloat(4), csPartidas.getFloat(5), csPartidas.getFloat(4)*csPartidas.getFloat(5));

            list.add(partidaFactura);
        }
        adapter = new PartidasFacturaAdaptador(this, list);
        lvDetalle.setAdapter(adapter);
        cargarTotales();
    }

    private void cargarTotales() {
        Cursor csTotales=baseDatos.rawQuery("select sum(cantidad*precio)base, sum(((cantidad*precio)*tipoiva)/100) cuotaiva, sum((cantidad*precio)+(((cantidad*precio)*tipoiva)/100)) total  from facturaspartidas where idfactura="+id, null);
        if (csTotales.moveToFirst()){
            tvBaseImponible.setText(decimalFormat.format(csTotales.getFloat(0)).replace(".", ","));
            tvCuotaIva.setText(decimalFormat.format(csTotales.getFloat(1)).replace(".", ","));
            tvTotalAutoventa.setText(decimalFormat.format(csTotales.getFloat(2)).replace(".", ","));
        }
    }

    private void cargarCliente(int idcliente) {
        Cursor cliente = baseDatos.rawQuery("select * from clientes where id="+idcliente, null);
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
