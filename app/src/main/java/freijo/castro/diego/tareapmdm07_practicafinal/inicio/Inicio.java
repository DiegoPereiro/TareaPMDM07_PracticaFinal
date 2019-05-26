package freijo.castro.diego.tareapmdm07_practicafinal.inicio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.BoringLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;
import freijo.castro.diego.tareapmdm07_practicafinal.facturas.Facturar;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios.EditarRecordatorioAtv;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios.Recordatorio;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios.RecordatoriosAdaptador;
import freijo.castro.diego.tareapmdm07_practicafinal.pendientes.Pendiente;
import freijo.castro.diego.tareapmdm07_practicafinal.pendientes.Pendientes;


public class Inicio extends Fragment {
    private View vista;
    private Date hoy = new Date();
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:MM");


    private Button btnPendientes, btnFacturar;
    private TextView tvFecha;
    private ListView lvRecordatorios;
    private FloatingActionButton btnNuevoRecordatorio;

    private SQLiteDatabase baseDatos;


    private OnFragmentInteractionListener mListener;

    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.inicio, container, false);


        btnPendientes=(Button) vista.findViewById(R.id.btnPendientes);
        btnFacturar=(Button) vista.findViewById(R.id.btnFacturar);
        tvFecha=(TextView) vista.findViewById(R.id.tvFecha);
        lvRecordatorios=(ListView) vista.findViewById(R.id.lvReecordatorios);
        btnNuevoRecordatorio=(FloatingActionButton) vista.findViewById(R.id.btnNuevoRecordatorio);

        BaseDatos dbatos = new BaseDatos(getContext(), "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();


        controlComponentes();
        return vista;
    }

    private void controlComponentes() {
        btnPendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lyInicio, new Pendientes()).addToBackStack(null).commit();
            }
        });
        btnFacturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facturar();
            }
        });
        tvFecha.setText(formatoFecha.format(hoy));
        btnNuevoRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditarRecordatorioAtv.class);
                startActivity(intent);
            }
        });

    }
    private void facturar(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
        alerta.setMessage("¿Seguro que desea facturar la partidas pendientes?").setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Facturar(getContext()).execute();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alerta.setTitle("Aviso");
        alerta.show();
    }


    @Override
    public void onResume() {
        super.onResume();
        cargarRecordatorios();
    }

    private void cargarRecordatorios() {
        ArrayList<Recordatorio> list=new ArrayList<>();
        Recordatorio recordatorio;
        RecordatoriosAdaptador adaptador;

        Date fecha = null, hora = null;
        Boolean alarma = null;

        list.clear();
        try {
            fecha=hoy;
            hora=formatoHora.parse("00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //partidas pendientes de facturas
        Cursor csPartidasFacuras=baseDatos.rawQuery("select * from pendientes where pendientes.fecha<='"+ formatoFecha.format(fecha) +"'", null);
        if (csPartidasFacuras.moveToFirst()){
            recordatorio=new Recordatorio(fecha, hora, "Existen partidas pendientes de facturar", "facturar", false, 0);
            list.add(recordatorio);
        }


        //recordatorios creados
         Cursor csRecordatorios=baseDatos.rawQuery("select * from recordatorios where recordatorios.fecha<='"+ formatoFecha.format(fecha) +"' order by recordatorios.fecha, recordatorios.hora", null);
        while (csRecordatorios.moveToNext()){
            fecha = null;
            hora = null;
            alarma = null;

            try {
                fecha=formatoFecha.parse(csRecordatorios.getString(1));
                hora=formatoHora.parse(csRecordatorios.getString(2));
                alarma= Boolean.parseBoolean(csRecordatorios.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            recordatorio=new Recordatorio(fecha, hora, csRecordatorios.getString(3), csRecordatorios.getString(4), alarma, csRecordatorios.getInt(0));
            list.add(recordatorio);
        }
        adaptador=new RecordatoriosAdaptador(getContext(), list);
        lvRecordatorios.setAdapter(adaptador);
        selectorRecordatorio(adaptador);


    }

    private void selectorRecordatorio(final Adapter adaptador) {
        lvRecordatorios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recordatorio seleccion=(Recordatorio)  adaptador.getItem(position);
                switch (seleccion.getDestino()){
                    case "facturar":
                        facturar();
                        break;
                    case "recordatorio":
                        Intent intent=new Intent(getContext(), EditarRecordatorioAtv.class);
                        intent.putExtra("id", seleccion.getId());
                        startActivity(intent);
                        break;


                }



            }
        });
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
