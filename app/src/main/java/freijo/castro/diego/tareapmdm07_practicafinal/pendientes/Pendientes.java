package freijo.castro.diego.tareapmdm07_practicafinal.pendientes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;
import freijo.castro.diego.tareapmdm07_practicafinal.clientes.Cliente;
import freijo.castro.diego.tareapmdm07_practicafinal.clientes.ClientesAdaptador;

public class Pendientes extends Fragment {
    private View vista;
    private FloatingActionButton btnNuevoPendiente;
    private ListView lvPendientes;

    private SQLiteDatabase baseDatos;

    private Pendiente pendiente;
    private ArrayList<Pendiente> list=new ArrayList<>();
    private PendientesAdaptador adapter;

    private OnFragmentInteractionListener mListener;



    public Pendientes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.pendientes, container, false);

        lvPendientes=(ListView) vista.findViewById(R.id.lvPendientes);

        btnNuevoPendiente=(FloatingActionButton) vista.findViewById(R.id.btnNuevoPendiente);

        BaseDatos dbatos = new BaseDatos(getContext(), "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();

        btnNuevoPendiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditarPendiente.class);
                startActivity(intent);
            }
        });
        cargarDatos();
        return vista;
    }
    private void cargarDatos() {
        list.clear();

        Cursor csPendientes=baseDatos.rawQuery("select pendientes.id, clientes.id, pendientes.fecha, clientes.cif, clientes.nombre, pendientes.referencia," +
                " pendientes.concepto, pendientes.cantidad, pendientes.precio from pendientes, clientes where pendientes.idcliente=clientes.id order by pendientes.fecha", null);
        while (csPendientes.moveToNext()){
            pendiente=new Pendiente(csPendientes.getInt(0), csPendientes.getInt(1), csPendientes.getString(2), csPendientes.getString(3),
                    csPendientes.getString(4), csPendientes.getString(5),  csPendientes.getString(6), csPendientes.getFloat(7),  csPendientes.getFloat(8));

            list.add(pendiente);
        }
        adapter=new PendientesAdaptador(getContext(), list);
        lvPendientes.setAdapter(adapter);

    }


    // TODO: Rename method, update argument and hook method into UI event
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
