package freijo.castro.diego.tareapmdm07_practicafinal;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import freijo.castro.diego.tareapmdm07_practicafinal.clientes.ClientesFm;
import freijo.castro.diego.tareapmdm07_practicafinal.facturas.FacturasFm;
import freijo.castro.diego.tareapmdm07_practicafinal.inicio.Inicio;
import freijo.castro.diego.tareapmdm07_practicafinal.partidas.PartidasFm;
import freijo.castro.diego.tareapmdm07_practicafinal.pendientes.Pendientes;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        Inicio.OnFragmentInteractionListener, ClientesFm.OnFragmentInteractionListener, Pendientes.OnFragmentInteractionListener,
        PartidasFm.OnFragmentInteractionListener, FacturasFm.OnFragmentInteractionListener {

//    private NotificationManager notificationManager;
//    private static final int IDNOTIFICACION=0;
//    private static final String IDCANALNOTIFICACION="primeranotificacion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        crearCanalNotificacion();
//        lanzarNotificacion(this);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyInicio, new Inicio()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager=getSupportFragmentManager();


        switch (id){
            case R.id.nav_principal:
                fragmentManager.beginTransaction().replace(R.id.lyInicio, new Inicio()).commit();
                break;
            case R.id.nav_clientes:
                fragmentManager.beginTransaction().replace(R.id.lyInicio, new ClientesFm()).commit();
                break;
            case R.id.nav_partidas:
                fragmentManager.beginTransaction().replace(R.id.lyInicio, new PartidasFm()).commit();
                break;
            case R.id.nav_factura:
                fragmentManager.beginTransaction().replace(R.id.lyInicio, new FacturasFm()).commit();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }



//    public void crearCanalNotificacion(){
//        notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel=new NotificationChannel(IDCANALNOTIFICACION, "PRUEVA DE NOTIFICACION", NotificationManager.IMPORTANCE_HIGH);
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.enableVibration(true);
//            notificationChannel.setDescription("desctripcion de la notificacion");
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//    }
//    private void lanzarNotificacion(Context context){
//        Intent intent=new Intent(context, Principal.class);
//        PendingIntent pendingIntent= (PendingIntent) PendingIntent.getActivity(context, IDNOTIFICACION, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder=new NotificationCompat.Builder(context, IDCANALNOTIFICACION);
//        builder.setSmallIcon(R.drawable.ic_documento);
//        builder.setContentTitle("Hacer facturación");
//        builder.setContentText("esta pendiente de hace la facturacion del mes");
//        builder.setContentIntent(pendingIntent);
//        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
//        builder.setAutoCancel(true);
//        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
//
//        notificationManager.notify(IDNOTIFICACION, builder.build());
//
//    }

}
