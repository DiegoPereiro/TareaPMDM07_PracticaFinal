package freijo.castro.diego.tareapmdm07_practicafinal.partidas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.R;

public class PartidasAdaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Partida> arrayList;

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

        TextView referencia=(TextView) convertView.findViewById(R.id.tvReferencia);
        TextView concepto=(TextView) convertView.findViewById(R.id.tvConcepto);
        TextView precio=(TextView) convertView.findViewById(R.id.tvPrecio);

        return convertView;
    }

}
