package freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.pendientes.EditarPendiente;

public class EditarRecordatorioAtv extends AppCompatActivity {
    private LinearLayout lyFecha, lyHora;
    private TextView tvFecha, tvHora;
    private CheckBox cbAlarma;
    private EditText etNotificacion;
    private Button btnGuardarContinuar, btnGuardarCerrar;
    private int id;

    private Date hoy=new Date();
    private static final String CERO="0";
    private static final String BARRA="/";
    private Calendar calendar=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_recordatorio_atv);

        lyFecha=(LinearLayout) findViewById(R.id.lyFecha);
        tvFecha=(TextView) findViewById(R.id.tvFecha);
        lyHora  =(LinearLayout) findViewById(R.id.lyHora);
        tvHora=(TextView) findViewById(R.id.tvHora);
        cbAlarma=(CheckBox) findViewById(R.id.cbAlarma);
        etNotificacion=(EditText) findViewById(R.id.etNotificacion);
        btnGuardarContinuar=(Button) findViewById(R.id.btnGuardarContinuar);
        btnGuardarCerrar=(Button) findViewById(R.id.btnGuardarCerrar);


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

                DatePickerDialog recogerFecha = new DatePickerDialog(EditarRecordatorioAtv.this, new DatePickerDialog.OnDateSetListener() {
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


    }
}
