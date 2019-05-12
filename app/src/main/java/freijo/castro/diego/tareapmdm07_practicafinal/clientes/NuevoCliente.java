package freijo.castro.diego.tareapmdm07_practicafinal.clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import freijo.castro.diego.tareapmdm07_practicafinal.MainActivity;
import freijo.castro.diego.tareapmdm07_practicafinal.R;
import freijo.castro.diego.tareapmdm07_practicafinal.basedatos.BaseDatos;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.Inicio;

public class NuevoCliente extends Fragment {
    private ImageView btnEliminar;
    private TextView tvTitulo;
    private EditText etCif, etNombre, etVia, etDireccion, etNumero, etEscalera, etPiso, etPuerta, etCodigoPostal, etMunicipio, etProvincia, etPais, etTelefono1, etTelefono2, etFax, etEmail, etIban, etPago1, etPago2, etPago3, etPago4;
    private Spinner spFormaPago;
    private LinearLayout lyPagos;
    private CheckBox cbImprimirIbanEmpresa, cbRecargo;
    private Button btnAceptar;

    private SQLiteDatabase baseDatos;

    private OnFragmentInteractionListener mListener;
    private View vista;


    public NuevoCliente() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.nuevo_cliente, container, false);

//        btnEliminar=(ImageView) actionBar.getCustomView().findViewById( R.id.btnEliminar);
//        tvTitulo=(TextView) actionBar.getCustomView().findViewById(R.id.tvTitulo);
        etCif = (EditText) vista.findViewById(R.id.etCif);
        etNombre = (EditText) vista.findViewById(R.id.etNombre);
        etVia = (EditText) vista.findViewById(R.id.etVia);
        etDireccion = (EditText) vista.findViewById(R.id.etDireccion);
        etNumero = (EditText) vista.findViewById(R.id.etNumero);
        etEscalera = (EditText) vista.findViewById(R.id.etEscalera);
        etPiso = (EditText) vista.findViewById(R.id.etPiso);
        etPuerta = (EditText) vista.findViewById(R.id.etPuerta);
        etCodigoPostal = (EditText) vista.findViewById(R.id.etCodidoPostal);
        etMunicipio = (EditText) vista.findViewById(R.id.etPoblacion);
        etProvincia = (EditText) vista.findViewById(R.id.etProvincia);
        etPais = (EditText) vista.findViewById(R.id.etPais);
        etTelefono1 = (EditText) vista.findViewById(R.id.etTelefono1);
        etTelefono2 = (EditText) vista.findViewById(R.id.etTelefono2);
        etFax = (EditText) vista.findViewById(R.id.etFax);
        etEmail = (EditText) vista.findViewById(R.id.etEmail);
        spFormaPago = (Spinner) vista.findViewById(R.id.spFormaPago);
        etIban = (EditText) vista.findViewById(R.id.etIban);
        etPago1 = (EditText) vista.findViewById(R.id.etPago1);
        etPago2 = (EditText) vista.findViewById(R.id.etPago2);
        etPago3 = (EditText) vista.findViewById(R.id.etPago3);
        etPago4 = (EditText) vista.findViewById(R.id.etPago4);
        lyPagos = (LinearLayout) vista.findViewById(R.id.lyPagos);
        cbImprimirIbanEmpresa = (CheckBox) vista.findViewById(R.id.cbImprimirIbanEmpresa);
        cbRecargo = (CheckBox) vista.findViewById(R.id.cbRecargo);
        btnAceptar = (Button) vista.findViewById(R.id.btnAceptar);

        BaseDatos dbatos = new BaseDatos(getContext(), "bdPmdm", null, MainActivity.version);
        baseDatos = dbatos.getReadableDatabase();


        controlComponentes();
        return vista;
    }

    private void controlComponentes() {

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etCif.getText().toString().equals("") && !etNombre.getText().toString().equals("")) {
                    ContentValues registros = new ContentValues();
                    registros.put("cif", etCif.getText().toString());
                    registros.put("nombre", etNombre.getText().toString());
                    registros.put("via", etVia.getText().toString());
                    registros.put("direccion", etDireccion.getText().toString());
                    registros.put("numero", etNumero.getText().toString());
                    registros.put("escalera", etEscalera.getText().toString());
                    registros.put("piso", etPiso.getText().toString());
                    registros.put("puerta", etPuerta.getText().toString());
                    registros.put("codigopostal", etCodigoPostal.getText().toString());
                    registros.put("poblacion", etMunicipio.getText().toString());
                    registros.put("provincia", etProvincia.getText().toString());
                    registros.put("pais", etPais.getText().toString());
                    registros.put("telefono", etTelefono1.getText().toString());
                    registros.put("telefono2", etTelefono2.getText().toString());
                    registros.put("fax", etFax.getText().toString());
                    registros.put("email", etEmail.getText().toString());
                    registros.put("iban", etIban.getText().toString());
                    int numPagos = 0;
                    ;
                    if (!etPago1.getText().toString().equals("")) {
                        numPagos++;
                    }
                    if (!etPago2.getText().toString().equals("")) {
                        numPagos++;
                    }
                    if (!etPago3.getText().toString().equals("")) {
                        numPagos++;
                    }
                    if (!etPago4.getText().toString().equals("")) {
                        numPagos++;
                    }
                    registros.put("numpagos", numPagos);
                    registros.put("pago1", etPago1.getText().toString());
                    registros.put("pago2", etPago2.getText().toString());
                    registros.put("pago3", etPago3.getText().toString());
                    registros.put("pago4", etPago4.getText().toString());
                    registros.put("formapago", spFormaPago.getSelectedItem().toString());
                    registros.put("recargo", String.valueOf(cbRecargo.isChecked()));


                    baseDatos.insert("clientes", null, registros);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.lyInicio, new Inicio()).addToBackStack(null).commit();

                } else {
                    Toast.makeText(getContext(), "El CIF y el nombre son obligatorios", Toast.LENGTH_LONG).show();
                }

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
