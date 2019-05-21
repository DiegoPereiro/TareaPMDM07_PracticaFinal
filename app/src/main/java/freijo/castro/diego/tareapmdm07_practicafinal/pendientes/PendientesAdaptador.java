package freijo.castro.diego.tareapmdm07_practicafinal.pendientes;

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

public class PendientesAdaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Pendiente> arrayList;
    private DecimalFormat decimalFormat=new DecimalFormat("0.00");

    public PendientesAdaptador(Context context, ArrayList<Pendiente> arrayList) {
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


        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.pendientes_fila, null);
        }

        TextView tvFecha = (TextView) convertView.findViewById(R.id.tvFecha);
        TextView tvCliente = (TextView) convertView.findViewById(R.id.tvCliente);
        TextView tvReferencia = (TextView) convertView.findViewById(R.id.tvReferencia);
        TextView tvConcepto = (TextView) convertView.findViewById(R.id.tvConcepto);
        TextView tvCantidad = (TextView) convertView.findViewById(R.id.tvCantidad);
        TextView tvPrecio = (TextView) convertView.findViewById(R.id.tvPrecio);
        TextView tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);

        tvFecha.setText(arrayList.get(position).getFecha());
        tvCliente.setText(arrayList.get(position).getCifCliente()+" "+arrayList.get(position).getNombreCliente());
        tvReferencia.setText(arrayList.get(position).getReferencia());
        tvConcepto.setText(arrayList.get(position).getConcepto());
        tvCantidad.setText(decimalFormat.format(arrayList.get(position).getCantidad()).replace(".",","));
        tvPrecio.setText(decimalFormat.format(arrayList.get(position).getPrecio()).replace(".",","));
        float total=arrayList.get(position).getCantidad()*arrayList.get(position).getPrecio();
        tvTotal.setText(decimalFormat.format(total).replace(".",","));


        return convertView;
    }
}
