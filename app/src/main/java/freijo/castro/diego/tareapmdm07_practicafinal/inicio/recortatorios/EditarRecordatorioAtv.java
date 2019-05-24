package freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;
import freijo.castro.diego.tareapmdm07_practicafinal.pendientes.EditarPendiente;

public class EditarRecordatorioAtv extends AppCompatActivity {
    private LinearLayout lyFecha, lyHora;
    private TextView tvFecha, tvHora;
    private CheckBox cbAlarma;
    private EditText etNotificacion;
    private Button btnGuardarContinuar, btnGuardarCerrar;
    private int id;

    private Date hoy=new Date();
    private Calendar calendar=Calendar.getInstance();
    private int dia, mes, anio, hora, minuto, segundo;

    private SQLiteDatabase baseDatos;

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

        BaseDatos dbatos = new BaseDatos(this, "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();

        if (id==0){
            tvFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(hoy));
            tvHora.setText(new SimpleDateFormat("HH:mm").format(hoy));
        }

        controlComponentes();
    }

    private void controlComponentes() {
        lyFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dia = calendar.get(Calendar.DAY_OF_MONTH);
                 mes = calendar.get(Calendar.MONTH);
                 anio = calendar.get(Calendar.YEAR);

                DatePickerDialog recogerFecha = new DatePickerDialog(EditarRecordatorioAtv.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final int mesActual = month + 1;
                        String diaFormateado = (dayOfMonth < 10) ? "0" + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                        String mesFormateado = (mesActual < 10) ? "0" + String.valueOf(mesActual) : String.valueOf(mesActual);
                        tvFecha.setText(diaFormateado + "/" + mesFormateado + "/" + year);
                    }
                }, anio, mes, dia);
                recogerFecha.show();

            }
        });
        lyHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora=calendar.get(Calendar.HOUR_OF_DAY);
                minuto=calendar.get(Calendar.MINUTE);
                TimePickerDialog recogerHora=new TimePickerDialog(EditarRecordatorioAtv.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String horaFormateada = (hourOfDay < 10) ? "0" + String.valueOf(hourOfDay) : String.valueOf(hourOfDay);
                        String minutoFormateado = (minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute);

                        tvHora.setText(horaFormateada+":"+minutoFormateado);
                    }
                }, hora, minuto, true);
                recogerHora.show();
            }
        });


        btnGuardarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarRecordatorio();
                etNotificacion.setText("");
                etNotificacion.requestFocus();
            }
        });
        btnGuardarCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarRecordatorio();
                finish();
            }
        });


    }

    private void guardarRecordatorio() {
        if (!etNotificacion.getText().toString().equals("")){
            ContentValues registros=new ContentValues();
            registros.put("fecha", tvFecha.getText().toString());
            registros.put("hora", tvHora.getText().toString());
            registros.put("notificacion", etNotificacion.getText().toString());
            registros.put("alarma", String.valueOf(cbAlarma.isChecked()));

            if (id==0){
                baseDatos.insert("recordatorios", null, registros);
            }else {

            }

        }else {
            Toast.makeText(this, "Debe poner el cliente, el concepto, cantidad y el precio", Toast.LENGTH_LONG).show();
        }

    }
}
