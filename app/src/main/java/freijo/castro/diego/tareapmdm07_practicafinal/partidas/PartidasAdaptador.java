package freijo.castro.diego.tareapmdm07_practicafinal.partidas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.R;

public class PartidasAdaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Partida> arrayList;
    private DecimalFormat decimalFormat=new DecimalFormat("0.00");

    public PartidasAdaptador(Context context, ArrayList<Partida> arrayList) {
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
            convertView=layoutInflater.inflate(R.layout.partidas_fila, null);
        }
        TextView tvReferencia=(TextView) convertView.findViewById(R.id.tvReferencia);
        TextView tvConcepto=(TextView) convertView.findViewById(R.id.tvConcepto);
        TextView tvPrecio=(TextView) convertView.findViewById(R.id.tvPrecio);

        tvReferencia.setText(arrayList.get(position).getReferencia());
        tvConcepto.setText(arrayList.get(position).getConcepto());
        tvPrecio.setText(decimalFormat.format(arrayList.get(position).getPrecio()).replace(".",","));

        return convertView;
    }

}
