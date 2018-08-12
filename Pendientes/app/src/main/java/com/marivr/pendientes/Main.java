package com.marivr.pendientes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marivr.pendientes.Adapters.RecyclerViewClickListener;
import com.marivr.pendientes.Adapters.RecyclerViewCustomAdapter;
import com.marivr.pendientes.Model.Pendiente;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class Main extends AppCompatActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<Pendiente> tabla;

    private RecyclerView lista;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewCustomAdapter adapter;

    private Button bNuevo;
    private EditText etTexto;

    private ArrayList<Pendiente> items;
    private int id = 0;

    private enum Prioridad{ALTO, MEDIO, BAJO};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bNuevo = (Button)findViewById(R.id.bNuevo);
        etTexto = (EditText) findViewById(R.id.etTexto);

        items = new ArrayList<>();

        lista = (RecyclerView) findViewById(R.id.lista);
        lista.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                lista.getContext(),
                linearLayoutManager.getOrientation()
        );

        lista.addItemDecoration(mDividerItemDecoration);
        adapter  = new RecyclerViewCustomAdapter(items, this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        });
        lista.setAdapter(adapter);

        bNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoItem();
            }
        });

        initAzureClient();

    }

    private void initAzureClient(){
        try{
            mClient = new MobileServiceClient("http://app-pendinetes.azurewebsites.net", this);
            Log.d("initAzureClient" , "cliente de Azure inicializado**");
        }catch (MalformedURLException mue){
            Log.d("initClientAzure", mue.getMessage());
        }

        tabla = mClient.getTable(Pendiente.class);

        obtenerItems();
    }

    public void eliminarItem(final int i){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                tabla.delete(items.get(i));
                items.remove(i);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemRemoved(i);
                        adapter.notifyItemRangeChanged(i, items.size());
                    }
                });

                return null;
            }
        }.execute();
    }



    public void actualizarItem(){

    }

    public void completarItem(final int i){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                try{
                    Pendiente item = items.get(i);
                    tabla.update(item).get();
                    items.remove(i);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyItemRemoved(i);
                            adapter.notifyItemRangeChanged(i, items.size());
                        }
                    });
                }catch (Exception e){
                }
                return null;
            }
        }.execute();
    }

    public void notificacion(String texto){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Elementos eliminaros");
        mBuilder.setContentText("Has eliminado " + texto);

        Intent resultIntent = new Intent(this, Main.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Main.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);


        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(++id, mBuilder.build());
    }

    public Pendiente parserMensaje(String mensaje){
        StringTokenizer st = new StringTokenizer(mensaje, " ");
        ArrayList<String> stack = new ArrayList<String>();
        String finalString = "";
        Pendiente item = new Pendiente();
        int i = 0;

        item.setCompleta(false);
        item.setFecha(Util.now());
        item.setPrioridad(2);

        while (st.hasMoreTokens()){
            stack.add(st.nextToken());
        }

        while(i < stack.size()){
            String code = stack.get(i);
            String value = null;

            if(code.equals("-p") && (i + 1) < stack.size()){
                value = stack.get(i + 1);
                if(value.equals("alto") || value.equals("alta")){
                    item.setPrioridad(0);
                    stack.remove(i);
                    stack.remove(i);
                    break;
                }else if(value.equals("medio") || value.equals("media")){
                    item.setPrioridad(1);
                    stack.remove(i);
                    stack.remove(i);
                    break;
                }else if(value.equals("bajo") || value.equals("baja")){
                    item.setPrioridad(2);
                    stack.remove(i);
                    stack.remove(i);
                    break;
                }
            }
            i++;
        }
        for (String s: stack){
            finalString += s + " ";
        }
        item.setTexto(finalString);

        i = 0;
        while(i < stack.size()){
            String value = stack.get(i);

            if(value.equals("hoy")){
                item.setFecha(Util.now());
                break;
            }else if(value.equals("mañana")){
                item.setFecha(Util.sumarDia(1));
                break;
            }else if(value.equals("pasado")){
                item.setFecha(Util.sumarDia(2));
                break;
            }
            i++;
        }

        return item;
    }

    public void obtenerItems(){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try{
                    items.clear();
                    //ArrayList<Pendiente> res = tabla.execute().get();
                    ArrayList<Pendiente> res = tabla
                            .where()
                            .field("completa")
                            .eq("false")
                            .execute()
                            .get();

                    for (Pendiente item: res){
                        Log.d("obtenerItems", item.getTexto());
                        items.add(item);
                    }
                }catch (Exception e){
                    Log.d("obtenerItems", e.getMessage());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ordenar();
                        adapter.notifyDataSetChanged();
                    }
                });
                return null;
            }
        }.execute();
    }
    public void nuevoItem(){
        String texto = etTexto.getText().toString();
        final Pendiente item = parserMensaje(texto);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                Pendiente res = null;
                try{
                    res = mClient.getTable(Pendiente.class).insert(item).get();
                    items.add(res);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etTexto.setText("");
                        }
                    });

                }catch (Exception e){
                    Log.d("nuevoItem", e.getMessage());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                return null;
            }
        }.execute();
    }

    public void ordenar(){
        Collections.sort(items, new Comparator<Pendiente>() {
            @Override
            public int compare(Pendiente pendiente, Pendiente t1) {
                int p1 = pendiente.getPrioridad();
                int p2 = t1.getPrioridad();
                if(p1 == p2){
                    return 0;
                }else if(p1 < p2){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
    }
}
