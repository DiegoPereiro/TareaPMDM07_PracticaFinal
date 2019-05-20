package freijo.castro.diego.tareapmdm07_practicafinal.basedatos;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {

    private String tbusuarios = "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, contraseña TEXT)";
    private String tbClientes = "CREATE TABLE clientes (id INTEGER PRIMARY KEY AUTOINCREMENT, cif TEXT, nombre TEXT, via TEXT, direccion TEXT, numero TEXT, escalera TEXT, piso TEXT, puerta TEXT, codigopostal TEXT, poblacion TEXT, provincia TEXT, pais TEXT, telefono TEXT, telefono2 TEXT, fax TEXT, email TEXT, iban TEXT, numpagos INTEGER DEFAULT 0, pago1 TEXT, pago2 TEXT, pago3 TEXT, pago4 TEXT, formapago TEXT, reparto TEXT, imprimircuentaempresa INTEGER DEFAULT 0, recargo INTEGER DEFAULT 0)";
    private String tbpartidas = "CREATE TABLE partidas (id INTEGER PRIMARY KEY AUTOINCREMENT, referencia TEXT, concepto TEXT, cantidad REAL, precio REAL)";


    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbusuarios);
        db.execSQL(tbClientes);
        db.execSQL(tbpartidas);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String tabla;
        tabla=tbusuarios;
        try {db.execSQL(tabla);} catch (SQLiteException e) {actualizarCampos(tabla, db);}
        tabla = tbClientes;
        try {db.execSQL(tabla);} catch (SQLiteException e) {actualizarCampos(tabla, db);}
        tabla = tbpartidas;
        try {db.execSQL(tabla);} catch (SQLiteException e) {actualizarCampos(tabla, db);}

    }

    private void actualizarCampos(String tabla, SQLiteDatabase db) {
        String nombreTabla = tabla.substring(12, tabla.indexOf("("));
        String[] campos = tabla.substring(tabla.indexOf("(") + 1, tabla.indexOf(")")).split(",");

        for (int i = 0; i < campos.length; i++) {
            try {
                db.execSQL("alter table " + nombreTabla + " add " + campos[i].trim());
            } catch (SQLiteException e) {
            }
        }
    }
}
