package com.piro.dbinit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// 1 Make extend to DataBasehandler class
// 2 It will ask create a constructor similar to the DataBaseHandler  constructor.
// 3 Need a variable base on Context type, variable will be received on the constructor, Context will help to use on databasehandler
// 4 Create method that will insert a new row, the method will return the table key .
// 5 use SQL lite method insert to avoid written insert statement.

public class AccionesTablaCliente extends DatabaseHandler
{
    Context pantallaUsoContext;

    public AccionesTablaCliente(@Nullable Context context)
    {
        super(context);
        this.pantallaUsoContext = context;

    }

    public long insertaAgenda(String Name , String PhoneNumber)
    {
        long KeyValue = 0 ;

        try
        {
            DatabaseHandler myDBConexion = new DatabaseHandler(pantallaUsoContext);
            SQLiteDatabase MyDb = myDBConexion.getWritableDatabase();

            //Variable is base on SQLite, Content values contain tables field, it contain insert method.
            ContentValues datosAgenda = new ContentValues();
            datosAgenda.put("name",Name);
            datosAgenda.put("phone_number",PhoneNumber);

            KeyValue = MyDb.insert(TABLE_CONTACTS,null,datosAgenda);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return  KeyValue;
    }

    // nuevo metodo para recuperar datos datos de la DB
    public ArrayList<metodosCliente> visualizarClientes()
    {
        DatabaseHandler myDBConexion = new DatabaseHandler(pantallaUsoContext);
        SQLiteDatabase MyDb = myDBConexion.getWritableDatabase();

        ArrayList<metodosCliente> respuestaMetodo = new ArrayList<metodosCliente>();

        // devuelve los datos uno por uno, no retorna en bloque
        metodosCliente clienteRecuperado = null;

        Cursor cursorClientes = MyDb.rawQuery("SELECT * FROM " + TABLE_CONTACTS + " ORDER BY " + KEY_ID + " DESC",null);
        if (cursorClientes.moveToFirst())
        {
            // se se puede mover al primera registro, indica que podemos movernos en el cursor
            do
            {
                clienteRecuperado = new metodosCliente();
                clienteRecuperado.setId(cursorClientes.getInt(0)); // id
                clienteRecuperado.setName(cursorClientes.getString(1)); // nombre
                clienteRecuperado.setPhone_number(cursorClientes.getString(2)); // telefono

                respuestaMetodo.add(clienteRecuperado);

            }while (cursorClientes.moveToNext());
        }
        cursorClientes.close();

        return respuestaMetodo;
    }

    public metodosCliente verCliente(int clientid)
    {
        metodosCliente clienteRecuperado = null;
        DatabaseHandler myDBConexion = new DatabaseHandler(pantallaUsoContext);
        SQLiteDatabase MyDb = myDBConexion.getWritableDatabase();

        Cursor cursorClientes = MyDb.rawQuery("SELECT * FROM " + TABLE_CONTACTS + " WHERE " +KEY_ID  + " = " + clientid,null);
        if (cursorClientes.moveToFirst())
        {
            clienteRecuperado = new metodosCliente();
            clienteRecuperado.setId(cursorClientes.getInt(0)); // id
            clienteRecuperado.setName(cursorClientes.getString(1)); // nombre
            clienteRecuperado.setPhone_number(cursorClientes.getString(2)); // telefono
        }
        cursorClientes.close();
        return  clienteRecuperado;
    }
    
    public boolean actualizarClient(int clientID ,String Name , String PhoneNumber)
    {
        DatabaseHandler myDBConexion = new DatabaseHandler(pantallaUsoContext);
        SQLiteDatabase myDb = myDBConexion.getWritableDatabase();
        boolean respuestaActualiza= false ;
        String sqlQuery ="";
        try
        {
            sqlQuery =  "UPDATE " + TABLE_CONTACTS + " "  +
                        "SET " + KEY_NAME  + "= '" + Name + "', " +
                                KEY_PH_NO + "= '" + PhoneNumber + "'" +
                        "WHERE " + KEY_ID + "= " + clientID + "";

            myDb.execSQL(sqlQuery);
            respuestaActualiza = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return  respuestaActualiza;
    }

    public boolean eliminaClient(int clientID)
    {
        DatabaseHandler myDBConexion = new DatabaseHandler(pantallaUsoContext);
        SQLiteDatabase myDb = myDBConexion.getWritableDatabase();
        boolean respuestaEliminar= false ;
        String sqlQuery ="";
        try
        {
            sqlQuery =  "DELETE FROM " + TABLE_CONTACTS + " "  +
                        "WHERE " + KEY_ID + "= " + clientID + "";

            myDb.execSQL(sqlQuery);
            respuestaEliminar = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return  respuestaEliminar;
    }

}
