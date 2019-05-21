package freijo.castro.diego.tareapmdm07_practicafinal.clientes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;


public class ClientesFm extends Fragment {
    private View vista;
    private EditText etBuscar;
    private ListView lvClientes;
    private FloatingActionButton btnNuevo;


    private SQLiteDatabase baseDatos;

    private Cliente cliente;
    private ArrayList<Cliente> list=new ArrayList<>();
    private ClientesAdaptador adapter;


    private OnFragmentInteractionListener mListener;

    public ClientesFm() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.clientes_fm, container, false);

        etBuscar=(EditText) vista.findViewById(R.id.etBuscar);
        lvClientes=(ListView) vista.findViewById(R.id.lvClientes);
        btnNuevo=(FloatingActionButton) vista.findViewById(R.id.btnNuevoPendiente);


        BaseDatos dbatos = new BaseDatos(getContext(), "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lyInicio, new EditarClienteFm()).addToBackStack(null).commit();
            }
        });

        cargarDatos("%");
        return vista;
    }

    private void cargarDatos(String buscar) {
        list.clear();

        Cursor csClientes=baseDatos.rawQuery("SELECT * FROM clientes where clientes.nombre like '"+buscar+"' ORDER BY nombre", null);
        while (csClientes.moveToNext()){
            cliente = new Cliente(csClientes.getInt(0), csClientes.getString(1), csClientes.getString(2), csClientes.getString(3), csClientes.getString(4),
                    csClientes.getString(5), csClientes.getString(6), csClientes.getString(7), csClientes.getString(8), csClientes.getString(9), csClientes.getString(10),
                    csClientes.getString(11), csClientes.getString(12), csClientes.getString(13), csClientes.getString(14), csClientes.getString(15), csClientes.getString(16),
                    csClientes.getString(17));
             list.add(cliente);
        }
        adapter=new ClientesAdaptador(getContext(), list);
        lvClientes.setAdapter(adapter);


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
