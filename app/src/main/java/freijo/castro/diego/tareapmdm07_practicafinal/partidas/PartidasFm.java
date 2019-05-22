package freijo.castro.diego.tareapmdm07_practicafinal.partidas;

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;


public class PartidasFm extends Fragment {
    private View vista;
    private EditText etBuscar;
    private ListView lvPartidas;
    private FloatingActionButton btnNuevo;

    private SQLiteDatabase baseDatos;

    private Partida partida;
    private ArrayList<Partida> list = new ArrayList<>();
    private PartidasAdaptador adapter;


    private OnFragmentInteractionListener mListener;

    public PartidasFm() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.partidas_fm, container, false);

        etBuscar = (EditText) vista.findViewById(R.id.etBuscar);
        lvPartidas = (ListView) vista.findViewById(R.id.lvPartidas);
        btnNuevo = (FloatingActionButton) vista.findViewById(R.id.btnNuevaPartida);

        BaseDatos dbatos = new BaseDatos(getContext(), "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditarPartidaAtv.class);
                startActivity(intent);
            }
        });

        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();
        cargarDatos("%");
    }
    private void cargarDatos(String buscar) {
        list.clear();

        Cursor csPartidas = baseDatos.rawQuery("select * from partidas where concepto like '" + buscar + "' ORDER BY concepto", null);
        while (csPartidas.moveToNext()) {
            partida = new Partida(csPartidas.getString(1),csPartidas.getString(2),0,csPartidas.getFloat(3));

            list.add(partida);
        }
        adapter = new PartidasAdaptador(getContext(), list);

        lvPartidas.setAdapter(adapter);
        lvPartidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //codigo para modificar

            }
        });
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
