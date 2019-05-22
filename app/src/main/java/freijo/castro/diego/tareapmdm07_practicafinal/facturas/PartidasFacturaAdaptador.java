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
import freijo.castro.diego.tareapmdm07_practicafinal.partidas.Partida;

public class PartidasFacturaAdaptador extends BaseAdapter {
    private Context context;
    private ArrayList<PartidaFactura> arrayList;
    private DecimalFormat decimalFormat=new DecimalFormat("0.00");

    public PartidasFacturaAdaptador(Context context, ArrayList<PartidaFactura> arrayList) {
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

        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.editar_factura_partida_fila, null);
        }
        TextView tvConcepto=(TextView) convertView.findViewById(R.id.tvConcepto);
        TextView tvCantidad=(TextView) convertView.findViewById(R.id.tvCantidad);
        TextView tvPrecio=(TextView) convertView.findViewById(R.id.tvPrecio);
        TextView tvTotal=(TextView) convertView.findViewById(R.id.tvTotal);

        tvConcepto.setText(arrayList.get(position).getConcepto());
        tvCantidad.setText( String.valueOf(arrayList.get(position).getCantidad()));
        tvPrecio.setText(decimalFormat.format(arrayList.get(position).getPrecio()).replace(".",","));
        tvTotal.setText(decimalFormat.format(arrayList.get(position).getTotal()).replace(".",","));


        return convertView;
    }
}
