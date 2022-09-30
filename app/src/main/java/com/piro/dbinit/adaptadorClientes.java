package com.piro.dbinit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

// 1. clase adaptador que se extienda RecyclerView.Adapter<adaptadorClientes.ClientesViewHolder>
// 2. lo que esta entre simbolos de mayor que y menor que es una clase necesaria
// 3. solicita los metodos requeridos por heredados de RecyclerView.Adapter
// 4. La subclase aun da error, y generamos la subclase , la crea en el mismo archivo
// 5. la subclase que necesita heredar algunos metodos
// 6. solicita luego crear el constructor en la subclase
// 7. se puede solucionar con auxiliar de codigo de android studio
// 8. necesitamos una variable de arraylist para recibir los datos que presentara
// 9. a la clase principal se crea el constructor
//10. getItemCount modificar para retornar  cuantos elementos tiene el array que recibimos en el contructor
//11. instanciamos los campos que estan en la pantalla se va reperitr, los textview etc.
//12. Editar el metodo onBindViewHolder

public class adaptadorClientes extends RecyclerView.Adapter<adaptadorClientes.ClientesViewHolder>
{
    ArrayList<metodosCliente> listadoClientes;

    public adaptadorClientes(ArrayList<metodosCliente> listadoClientes)
    {
        this.listadoClientes = listadoClientes;
    }

    @NonNull
    @Override
    public adaptadorClientes.ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Se instancia la pantalla que se utilizar para replicar o repetir
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listado_clientes,null);


        // estammos a la subclase lo q se recibio en vista,
        return new  ClientesViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptadorClientes.ClientesViewHolder holder, int position)
    {
        // aqui es donde le damos valores a la campos de la pantalla q se repetiran
        holder.vistaNombre.setText(listadoClientes.get(position).getName());
        holder.vistaTelefono.setText(listadoClientes.get(position).getPhone_number());
    }

    @Override
    public int getItemCount() {
        return listadoClientes.size() ;
    }


    // clase requerida
    public class ClientesViewHolder extends RecyclerView.ViewHolder
    {
        TextView vistaNombre;
        TextView vistaTelefono;

        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);

            // aqui se puede confirmar que se contruyo correctamente la clase.
            vistaNombre = itemView.findViewById(R.id.vistaNombre);
            vistaTelefono = itemView.findViewById(R.id.vistaTelefono);
            // aqui se contruye el evento onclic para nos dirija a la pantalla para editar el registro

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context pantallaOrigen = view.getContext();
                    Intent i = new Intent(pantallaOrigen,verClienteIndividual.class);
                    // con la variable i se envia el valor llave
                    i.putExtra("llaveCliente",listadoClientes.get(getAdapterPosition()).getId());
                    pantallaOrigen.startActivity(i);

                }
            });


        }
    }
}
