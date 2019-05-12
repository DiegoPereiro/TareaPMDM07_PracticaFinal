package freijo.castro.diego.tareapmdm07_practicafinal.inicio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import freijo.castro.diego.tareapmdm07_practicafinal.R;


public class Inicio extends Fragment {
    private View vista;

    private Button btnListaClientes, btnNuevoCliente, btnListaFacturas, btnNuevaFactura;




    private OnFragmentInteractionListener mListener;

    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.inicio, container, false);
        btnListaClientes=(Button) vista.findViewById(R.id.btnListaClientes);
        btnNuevoCliente=(Button) vista.findViewById(R.id.btnNuevoCliente);
        btnListaFacturas=(Button) vista.findViewById(R.id.btnListaFacturas);
        btnNuevaFactura=(Button) vista.findViewById(R.id.btnNuevaFactura);

        return vista;
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
