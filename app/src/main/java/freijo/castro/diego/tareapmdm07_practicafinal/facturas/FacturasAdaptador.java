package freijo.castro.diego.tareapmdm07_practicafinal.facturas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.R;

public class FacturasAdaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Factura> arrayList;
    private DecimalFormat decimalFormat=new DecimalFormat("0.00");

    public FacturasAdaptador(Context context, ArrayList<Factura> arrayList) {
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

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.facturas_fila, null);
        }

        TextView tvFecha = (TextView) convertView.findViewById(R.id.tvFecha);
        TextView tvNumero = (TextView) convertView.findViewById(R.id.tvNumero);
        TextView tvCliente = (TextView) convertView.findViewById(R.id.tvCliente);
        TextView tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);

        tvFecha.setText(arrayList.get(position).getFecha());
        tvNumero.setText( String.valueOf(arrayList.get(position).getNumero()));
        tvCliente.setText(arrayList.get(position).getCifCliente()+" "+arrayList.get(position).getNombreCliente());
        tvTotal.setText(decimalFormat.format(arrayList.get(position).getTotal()).replace(".",","));


        return convertView;
    }












}
