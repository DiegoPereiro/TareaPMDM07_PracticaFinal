package freijo.castro.diego.tareapmdm07_practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etUsuario, etcontraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario=(EditText) findViewById(R.id.etUsuario);
        etcontraseña=(EditText) findViewById(R.id.etContraseña);
    }

    public void iniciarSesion(View view) {


    }
}
