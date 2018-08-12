package com.marivr.dbazure;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marivr.dbazure.Adapters.RecyclerViewClickListener;
import com.marivr.dbazure.Adapters.RecyclerViewCustomAdapter;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.Query;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOperations;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

public class MainActivity extends AppCompatActivity {

    private MobileServiceClient mClient;

    private Button bNuevo;

    private MobileServiceTable<TodoItem> tabla;
    private ArrayList<TodoItem> items;

    private RecyclerView lista;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewCustomAdapter adapter;

    /**
     * PARA SINCRONIZACIÓN OFFLINE
     */
    //TODO: a) definir objeto tabla para sincronizar
    private MobileServiceSyncTable<TodoItem> tablaSync;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bNuevo = (Button) findViewById(R.id.bNuevo);

        items = new ArrayList<TodoItem>();

        lista = (RecyclerView) findViewById(R.id.lista);
        lista.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayoutManager);

        adapter  = new RecyclerViewCustomAdapter(items, this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                opcionesElementoDialog(position);
            }
        });
        lista.setAdapter(adapter);

        try{
            mClient = new MobileServiceClient("https://androidapp01.azurewebsites.net", this);


        }catch (MalformedURLException mue){
        }

        //tabla = mClient.getTable(TodoItem.class);
        // TODO: b) creamos el vínculo con la tabla
        tablaSync = mClient.getSyncTable("TodoItem", TodoItem.class);

        //Init local storage
        try{
            initLocalStore().get();
        }catch (Exception e){
            Log.d("onCreate", e.getMessage());
        }

        obtenerItems();

        bNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoElementoDialog();
            }
        });
    }

    public void obtenerItems(){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try{
                    items.clear();
                    //ArrayList<TodoItem> res = tabla.execute().get();

                    //TODO: c) realizar consulta
                    sync().get();
                    final ArrayList<TodoItem> res = tablaSync.read(null).get();
                    for (TodoItem item: res){
                        items.add(item);
                    }
                }catch (Exception e){
                    Log.d("obtenerItems",e.getMessage());
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

    public void nuevoItem(String nombre){
        new AsyncTask<String, Void, Void>(){
            @Override
            protected Void doInBackground(String... strings) {
                TodoItem item = new TodoItem(strings[0]);
                TodoItem res = null;
                try{
                    res = mClient.getTable(TodoItem.class).insert(item).get();
                    items.add(res);
                }catch (Exception e){
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                return null;
            }
        }.execute(nombre);
    }

    public void eliminarItem(int position){
        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... integers) {
                final int i = integers[0];
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
        }.execute(position);
    }

    public void actualizarItem(int position, String text){
        TodoItem item = items.get(position);
        item.setText(text);

        new AsyncTask<TodoItem, Void, Void>(){
            @Override
            protected Void doInBackground(TodoItem... todoItems) {;
                tabla.update(todoItems[0]);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

                return null;
            }
        }.execute(item);
    }

    public void nuevoElementoDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Nuevo elemento");
        alert.setMessage("Ingresa nombre de elemento");

        final EditText input = new EditText(MainActivity.this);

        alert.setView(input);
        alert.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                nuevoItem(input.getText().toString());
            }
        });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    public void editarElementoDialog(final int i) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Editar elemento");
        alert.setMessage("Ingresa nombre de elemento");

        final EditText input = new EditText(MainActivity.this);
        input.setText(items.get(i).getText());
        input.selectAll();

        alert.setView(input);
        alert.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                actualizarItem(i, input.getText().toString());
            }
        });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    public void opcionesElementoDialog(final int position){
        String[] opciones = new String[]{"Editar Elemento","Eliminar Elemento"};
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Acciones para elemento");
        alert.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        editarElementoDialog(position);
                        break;
                    case 1:
                        eliminarItem(position);
                        break;
                }
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
    // TODO: d) método sync()
    private AsyncTask<Void, Void, Void> sync() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    MobileServiceSyncContext syncContext = mClient.getSyncContext();
                    syncContext.push().get();
                    tablaSync.pull(null).get();
                } catch (final Exception e) {
                }
                return null;
            }
        };
        return runAsyncTask(task);
    }
    // TODO: e) método runAsyncTask()
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    private AsyncTask<Void, Void, Void> initLocalStore() {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    MobileServiceSyncContext syncContext = mClient.getSyncContext();

                    if (syncContext.isInitialized())
                        return null;

                    SQLiteLocalStore localStore = new SQLiteLocalStore(mClient.getContext(), "OfflineStore", null, 1);

                    Map<String, ColumnDataType> tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("Text", ColumnDataType.String);

                    localStore.defineTable("TodoItem", tableDefinition);

                    SimpleSyncHandler handler = new SimpleSyncHandler();

                    syncContext.initialize(localStore, handler).get();

                } catch (final Exception e) {
                    Log.d("initStorage", e.getMessage());
                }

                return null;
            }
        };

        return runAsyncTask(task);
    }


}
