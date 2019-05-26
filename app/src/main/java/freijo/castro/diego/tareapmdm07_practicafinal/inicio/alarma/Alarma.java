package freijo.castro.diego.tareapmdm07_practicafinal.inicio.alarma;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class Alarma extends BroadcastReceiver {
    private int dia, mes, anio, hora, minuto, segundo;
    private String fechaSistema, horaSistema;

    private NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);





        Calendar calendar=Calendar.getInstance();
        dia=calendar.get(Calendar.DAY_OF_MONTH);
        mes=calendar.get(Calendar.MONTH);
        anio=calendar.get(Calendar.YEAR);
        hora=calendar.get(Calendar.HOUR_OF_DAY);
        minuto=calendar.get(Calendar.MINUTE);

        fechaSistema=dia+"/"+mes+"/"+anio;
        horaSistema=hora+":"+minuto;

        Log.e("fe", fechaSistema + " "+ horaSistema);

    }
}
