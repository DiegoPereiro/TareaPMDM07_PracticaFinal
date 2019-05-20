package freijo.castro.diego.tareapmdm07_practicafinal.clientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.R;

public class ClientesAdaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Cliente> arrayList;

    public ClientesAdaptador(Context context, ArrayList<Cliente> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String strDireccion = "";


        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.clientes_fila, null);

        }

        TextView cif=(TextView) convertView.findViewById(R.id.tvCif);
        TextView nombre=(TextView) convertView.findViewById(R.id.tvNombre);
        TextView direccion=(TextView) convertView.findViewById(R.id.tvDireccion);



        cif.setText(arrayList.get(position).getCif());
        nombre.setText(arrayList.get(position).getNombre());

        if (arrayList.get(position).getVia()!=""){strDireccion=strDireccion+ arrayList.get(position).getVia();}
        if (arrayList.get(position).getDireccion()!=""){strDireccion=strDireccion+" "+ arrayList.get(position).getDireccion();}
        if (arrayList.get(position).getNumero()!=""){strDireccion=strDireccion+", "+ arrayList.get(position).getNumero();}
        if (arrayList.get(position).getEscalera()!=""){strDireccion=strDireccion+" Esc."+ arrayList.get(position).getEscalera();}
        if (arrayList.get(position).getPiso()!=""){strDireccion=strDireccion+" "+ arrayList.get(position).getPiso();}
        if (arrayList.get(position).getPuerta()!=""){strDireccion=strDireccion+ arrayList.get(position).getPuerta();}
        strDireccion=strDireccion+" - ";
        if (arrayList.get(position).getCodigopostal()!=""){strDireccion=strDireccion+ arrayList.get(position).getCodigopostal();}
        if (arrayList.get(position).getPoblacion()!=""){strDireccion=strDireccion+" "+ arrayList.get(position).getPoblacion();}
        if (arrayList.get(position).getProvincia()!=""){strDireccion=strDireccion+" "+ arrayList.get(position).getProvincia();}
        direccion.setText(strDireccion);


        return convertView;
    }

}
