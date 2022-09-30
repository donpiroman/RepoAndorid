package com.piro.dbinit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView listadoClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Aqui instanciamos
        listadoClientes = findViewById(R.id.listaClientes);
        listadoClientes.setLayoutManager(new LinearLayoutManager(this));

        AccionesTablaCliente recuperClient = new AccionesTablaCliente(MainActivity.this);

        adaptadorClientes adaptador = new adaptadorClientes(recuperClient.visualizarClientes());

        listadoClientes.setAdapter(adaptador);
    }

    public void TrasladoFormularioRegistro(View view)
    {
        Intent i = new Intent(MainActivity.this, formClientes.class);
        startActivity(i);
    }

    public void inicializarDB(View view)
    {
        DatabaseHandler myDb = new DatabaseHandler(MainActivity.this);
        SQLiteDatabase db = myDb.getWritableDatabase();

        if (db != null )
        {
            Toast.makeText(this, "Base De datos Creada", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Error al Crear la Base de Datos", Toast.LENGTH_LONG).show();
        }
    }
}