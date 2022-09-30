package com.piro.dbinit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class verClienteIndividual extends AppCompatActivity
{

    EditText txtCampoNombre;
    EditText txtCampoTelefono;
    EditText txtCampoKey;
    Button btnActualizarCliente;
    FloatingActionButton btnEnviarEdicion;
    FloatingActionButton btnEliminar;

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
        btnEnviarEdicion = findViewById(R.id.btnEnviarEdicion);
        btnActualizarCliente = findViewById(R.id.btnActualizarCliente);
        btnEliminar = findViewById(R.id.btnEliminar);

        // Se recupera el parametro
        if (savedInstanceState== null)
        {
            // para versiones nuevas de android, el parametro se carga en bundle
            Bundle datosAdicionales = getIntent().getExtras();
            if (datosAdicionales != null )
            {
                codigoCliente = datosAdicionales.getInt("llaveCliente");
            }
        }
        else //  para recuperar en versiones antiguas de android
        {
            codigoCliente = (int)savedInstanceState.getSerializable("llaveCliente");
        }

        AccionesTablaCliente tablaCliente = new AccionesTablaCliente(verClienteIndividual.this);
        myCliente = tablaCliente.verCliente(codigoCliente);

        if (myCliente!= null)
        {
            txtCampoKey.setText(myCliente.getId() + "");
            txtCampoNombre.setText(myCliente.getName());
            txtCampoTelefono.setText(myCliente.getPhone_number());

            txtCampoKey.setInputType(InputType.TYPE_NULL);
            txtCampoNombre.setInputType(InputType.TYPE_NULL);
            txtCampoTelefono.setInputType(InputType.TYPE_NULL);
            btnActualizarCliente.setVisibility(View.INVISIBLE);
        }
        else
        {
            Toast.makeText(verClienteIndividual.this, "Algo sucedio intente de nuevo", Toast.LENGTH_LONG).show();
        }

        btnEnviarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(verClienteIndividual.this,editaCliente.class);
                //recordemos que la clase VERCLIENTE, recibe un valor, la clase EDITAR CLIENTE, es
                //una replica de VERCLIENTE, por lo tanto tambien necesita recibir un valor..
                //asi que aca se de lo debemos de enviar...
                i.putExtra("identificacionCliente",codigoCliente);
                startActivity(i);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Se generara el dialogo para confirmar que sea eliminar
                AlertDialog.Builder notificacionEliminaCliente = new AlertDialog.Builder(verClienteIndividual.this);
                notificacionEliminaCliente.setMessage("Esta seguro de eliminar Client")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                // aqui se hace el llamado eliminar cliente
                                if (tablaCliente.eliminaClient(codigoCliente))
                                {
                                    Toast.makeText(verClienteIndividual.this, "Cliente Eliminado!!", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(verClienteIndividual.this, "Algo sucedio intente de nuevo", Toast.LENGTH_LONG).show();
                                }
                                Intent x = new Intent(verClienteIndividual.this,MainActivity.class);
                                startActivity(x);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                            }
                        }).show();
            }
        });
    }// fin del onCreate

    public void RegresarMain (View view)
    {
        Intent i = new Intent(verClienteIndividual.this,MainActivity.class);
        startActivity(i);
    }
}