package com.piro.dbinit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class formClientes extends AppCompatActivity
{

    EditText campoNombre;
    EditText campoTelefono;
    Button btnRegistraAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_clientes);

        campoNombre =findViewById(R.id.campoNombre);
        campoTelefono =findViewById(R.id.campoTelefono);
        btnRegistraAgenda =findViewById(R.id.btnRegistrasAgenda);

        btnRegistraAgenda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AccionesTablaCliente datoRegistro = new AccionesTablaCliente(formClientes.this);
                //
                long codigoNuevoRegistro = datoRegistro.insertaAgenda(campoNombre.getText().toString(),campoTelefono.getText().toString());

                if(codigoNuevoRegistro != 0)
                {
                    Toast.makeText(formClientes.this, "Registro Guardado con exito", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(formClientes.this, "Algo sucedio intente de nuevo", Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(formClientes.this, MainActivity.class);
                startActivity(i);

            }
        });
    }

}