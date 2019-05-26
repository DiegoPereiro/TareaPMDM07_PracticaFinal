package freijo.castro.diego.tareapmdm07_practicafinal;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.alarma.Alarma;

public class MainActivity extends AppCompatActivity {
    public static final int version = 1;
    private SQLiteDatabase baseDatos;

    private EditText etUsuario, etcontraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etcontraseña = (EditText) findViewById(R.id.etContraseña);


        BaseDatos dbatos = new BaseDatos(MainActivity.this, "bdPmdm", null, version);
        baseDatos = dbatos.getWritableDatabase();

        Cursor usuarios = baseDatos.rawQuery("SELECT * FROM usuarios", null);
        if (!usuarios.moveToFirst()) {
            baseDatos.execSQL("INSERT INTO usuarios (usuario, contraseña) VALUES ('DIEGO', '1234')");
        }


    }

    public void iniciarSesion(View view) {
        String usuario = etUsuario.getText().toString();
        String constraseña = etcontraseña.getText().toString();


        if (!usuario.equals("") && !constraseña.equals("")) {
            Cursor csUsuario = baseDatos.rawQuery("select * from usuarios where usuario='" + usuario + "' and contraseña='" + constraseña + "'", null);
            if (csUsuario.moveToFirst()) {
                //abrir pantalla principal
                Intent itPrincipal = new Intent(this, Principal.class);
                startActivity(itPrincipal);
                finish();
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe poner el usuario y la contraseña", Toast.LENGTH_SHORT).show();
        }

    }
}
