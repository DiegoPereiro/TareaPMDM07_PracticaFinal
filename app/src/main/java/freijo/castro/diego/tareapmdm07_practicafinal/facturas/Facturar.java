package freijo.castro.diego.tareapmdm07_practicafinal.facturas;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;

public class Facturar extends AsyncTask<Void, Integer, Boolean> {
    private final Context context;
    private ProgressDialog progreso;
    private SQLiteDatabase baseDatos;
    private Date hoy = new Date();


    public Facturar(Context context) {
        this.context = context;
        BaseDatos dbatos = new BaseDatos(context, "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getWritableDatabase();
    }

    @Override
    protected void onPreExecute() {
        progreso = new ProgressDialog(context);
        progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progreso.setMessage("Facturando...");
        progreso.setMax(100);
        progreso.setProgress(0);
        progreso.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        int progreso, numero, idFactura;
        String fecha;
        boolean resultado = false;
        float tipoIva=21;


        try {
            Cursor csClientesFacturas = baseDatos.rawQuery("select idcliente from pendientes group by idcliente", null);
            if (csClientesFacturas.moveToFirst()){
                do {
                    Thread.sleep(50);
                    publishProgress(10);
                    progreso=70/csClientesFacturas.getCount();


                    int idCliente = csClientesFacturas.getInt(0);
                    //numero de factura
                    Cursor csNumero = baseDatos.rawQuery("select max(numero) from facturas", null);
                    numero = 1;
                    if (csNumero.moveToFirst()) {
                        numero = csNumero.getInt(0) + 1;
                    }
                    //fecha de la factura
                    fecha = new SimpleDateFormat("dd/MM/yyyy").format(hoy);

                    //crear la factura y coger su id
                    baseDatos.execSQL("insert into facturas (numero, fecha, idcliente, base, iva, total)" +
                            " values (" + numero + ", '" + fecha + "'," + idCliente + ", 0,0,0)");
                    Cursor csIdFactura = baseDatos.rawQuery("select max(id) from facturas\n", null);
                    csIdFactura.moveToFirst();
                    idFactura = csIdFactura.getInt(0);

                    //consultas todas las partidas pendientes por cliente
                    Cursor csPartidasPendientes = baseDatos.rawQuery("select * from pendientes where idcliente=" + idCliente, null);
                    while (csPartidasPendientes.moveToNext()) {
                        ContentValues registros = new ContentValues();
                        registros.put("idfactura", idFactura);
                        registros.put("referencia", csPartidasPendientes.getString(3));
                        registros.put("concepto", csPartidasPendientes.getString(4));
                        registros.put("cantidad", csPartidasPendientes.getFloat(5));
                        registros.put("precio", csPartidasPendientes.getFloat(6));
                        registros.put("tipoiva", tipoIva);

                        baseDatos.insert("facturaspartidas", null, registros);
                    }

                    Cursor csTotales=baseDatos.rawQuery("select sum(cantidad*precio) total from facturaspartidas  where idfactura=1", null);
                    if (csTotales.moveToFirst()){
                        float base =csTotales.getFloat(0);
                        float total=(base*tipoIva)+base;


                        ContentValues registros = new ContentValues();
                        registros.put("base", base );
                        registros.put("iva", tipoIva);
                        registros.put("total", total);
                        baseDatos.update("facturas", registros, "id=" + idFactura, null);
                    }

                    Thread.sleep(50);
                    publishProgress(progreso);
                    progreso=progreso+progreso;


                }while (csClientesFacturas.moveToNext());
                resultado=true;                
            }
                
            
            



            
            

        } catch (InterruptedException e) {
            resultado=false;
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progreso.setProgress(values[0].intValue());
    }

    @Override
    protected void onPostExecute(Boolean resultado) {
        super.onPostExecute(resultado);
        if (resultado){
//            baseDatos.execSQL("delete from pendientes");
            Toast.makeText(context, "Facturación correcta", Toast.LENGTH_SHORT).show();
        }
        progreso.dismiss();
    }


}
