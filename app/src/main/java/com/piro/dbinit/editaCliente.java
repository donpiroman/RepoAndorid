package com.piro.dbinit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editaCliente extends AppCompatActivity
{

    EditText txtCampoNombre;
    EditText txtCampoTelefono;
    EditText txtCampoKey;
    Button btnActualizarCliente;
    FloatingActionButton btnEditarCliente;


    // la pantalla recibe un codigo de cliente
    int codigoCliente =0;
    metodosCliente myCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente_individual);

        txtCampoKey = findViewById(R.id.vistaCampoKey);
        txtCampoNombre = findViewById(R.id.vistaCampoNombre);
        txtCampoTelefono = findViewById(R.id.vistaCampoTelefono);
        btnEditarCliente = findViewById(R.id.btnEnviarEdicion);
        btnActualizarCliente = findViewById(R.id.btnActualizarCliente);

        // Se recupera el parametro
        if (savedInstanceState== null)
        {
            // para versiones nuevas de android, el parametro se carga en bundle
            Bundle datosAdicionales = getIntent().getExtras();
            if (datosAdicionales != null )
            {
                codigoCliente = datosAdicionales.getInt("identificacionCliente");
            }
        }
        else //  para recuperar en versiones antiguas de android
        {
            codigoCliente = (int)savedInstanceState.getSerializable("identificacionCliente");
        }

        AccionesTablaCliente tablaCliente = new AccionesTablaCliente(editaCliente.this);
        myCliente = tablaCliente.verCliente(codigoCliente);

        if (myCliente!= null)
        {
            txtCampoKey.setText(myCliente.getId() + "");
            txtCampoNombre.setText(myCliente.getName());
            txtCampoTelefono.setText(myCliente.getPhone_number());

        }

        btnActualizarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                boolean actualizoBien = tablaCliente.actualizarClient(codigoCliente,txtCampoNombre.getText().toString(), txtCampoTelefono.getText().toString());

                if (actualizoBien)
                {
                    Toast.makeText(editaCliente.this, "Actualizado con Exito1!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(editaCliente.this, "Algo sucedio intente de nuevo", Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(editaCliente.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void RegresarMain (View view)
    {
        Intent i = new Intent(editaCliente.this,MainActivity.class);
        startActivity(i);
    }
}