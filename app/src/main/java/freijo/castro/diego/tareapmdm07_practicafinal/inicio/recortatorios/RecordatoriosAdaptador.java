package freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import freijo.castro.diego.tareapmdm07_practicafinal.R;

public class RecordatoriosAdaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Recordatorio> arrayList;

    public RecordatoriosAdaptador(Context context, ArrayList<Recordatorio> arrayList) {
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
            convertView = layoutInflater.inflate(R.layout.recordatorio_fila, null);
        }


        TextView tvFechaHora = (TextView) convertView.findViewById(R.id.tvFechaHora);
        TextView tvNotificacion = (TextView) convertView.findViewById(R.id.tvNotificacion);

        tvFechaHora.setText(new SimpleDateFormat("dd/MM/yyyy").format( arrayList.get(position).getFecha()));
        tvNotificacion.setText(arrayList.get(position).getNotificacion());

        return convertView;
    }


}
