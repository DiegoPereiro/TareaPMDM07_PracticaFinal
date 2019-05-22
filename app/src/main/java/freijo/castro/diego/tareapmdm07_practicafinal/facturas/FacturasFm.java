package freijo.castro.diego.tareapmdm07_practicafinal.facturas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import freijo.castro.diego.tareapmdm07_practicafinal.partidas.EditarPartidaAtv;

public class FacturasFm extends Fragment {
    private View vista;
    private EditText etBuscar;
    private ListView lvFacturas;
    private FloatingActionButton btnNuevo;

    private SQLiteDatabase baseDatos;

    private Factura factura;
    private ArrayList<Factura> list = new ArrayList<>();
    private FacturasAdaptador adapter;


    private OnFragmentInteractionListener mListener;

    public FacturasFm() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.facturas_fm, container, false);

        lvFacturas=(ListView) vista.findViewById(R.id.lvFacturas);
        btnNuevo=(FloatingActionButton) vista.findViewById(R.id.btnNuevaFactura);


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

        Cursor csFacturas = baseDatos.rawQuery("select facturas.id, facturas.numero, facturas.idcliente, facturas.fecha, clientes.cif, clientes.nombre," +
                " facturas.base, facturas.iva, facturas.total from facturas, clientes where facturas.idcliente=clientes.id order by facturas.fecha, facturas.numero", null);
        while (csFacturas.moveToNext()) {
            factura=new Factura(csFacturas.getInt(0), csFacturas.getInt(1), csFacturas.getInt(2), csFacturas.getString(3),
                    csFacturas.getString(4), csFacturas.getString(5), csFacturas.getFloat(6), csFacturas.getFloat(7), csFacturas.getFloat(8));
            list.add(factura);
        }
        adapter = new FacturasAdaptador(getContext(), list);

        lvFacturas.setAdapter(adapter);
        lvFacturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
