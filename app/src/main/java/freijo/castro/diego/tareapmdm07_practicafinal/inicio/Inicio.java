package freijo.castro.diego.tareapmdm07_practicafinal.inicio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.facturas.Facturar;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios.EditarRecordatorioAtv;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios.Recordatorio;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios.RecordatoriosAdaptador;
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
        });
        tvFecha.setText(formatoFecha.format(hoy));
        cargarRecordatorios();
        btnNuevoRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditarRecordatorioAtv.class);
                startActivity(intent);
            }
        });
    }

    private void cargarRecordatorios() {
        ArrayList<Recordatorio> list=new ArrayList<>();
        Recordatorio recordatorio;
        RecordatoriosAdaptador adaptador;

        Date fecha = null, hora = null;

        list.clear();
        try {
            fecha=formatoFecha.parse("25/06/2019");
            hora=formatoHora.parse("12:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        recordatorio=new Recordatorio(fecha, hora, "prueva de notificacion", "destino", true);
        list.add(recordatorio);
        list.add(recordatorio);
        list.add(recordatorio);
        list.add(recordatorio);
        list.add(recordatorio);









        adaptador=new RecordatoriosAdaptador(getContext(), list);
        lvRecordatorios.setAdapter(adaptador);




//        Cursor csPendientes=baseDatos.rawQuery("select pendientes.id, clientes.id, pendientes.fecha, clientes.cif, clientes.nombre, pendientes.referencia," +
//                " pendientes.concepto, pendientes.cantidad, pendientes.precio from pendientes, clientes where pendientes.idcliente=clientes.id order by pendientes.fecha", null);
//        while (csPendientes.moveToNext()){
//            pendiente=new Pendiente(csPendientes.getInt(0), csPendientes.getInt(1), csPendientes.getString(2), csPendientes.getString(3),
//                    csPendientes.getString(4), csPendientes.getString(5),  csPendientes.getString(6), csPendientes.getFloat(7),  csPendientes.getFloat(8));
//
//            list.add(pendiente);
//        }
//        adapter=new PendientesAdaptador(getContext(), list);
//        lvPendientes.setAdapter(adapter);






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
