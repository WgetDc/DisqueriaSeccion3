package com.wgetdc.disqueriaseccion3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtMain_id, edtMain_artista, edtMain_album, edtMain_fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtMain_id = findViewById(R.id.edtMain_id);
        edtMain_artista = findViewById(R.id.edtMain_artista);
        edtMain_album = findViewById(R.id.edtMain_album);
        edtMain_fecha = findViewById(R.id.edtMain_fecha);
    }

    public void guardarDisco(View view){
        gestorBaseDeDatos gestor = new gestorBaseDeDatos(this, "disqueria", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String id = edtMain_id.getText().toString();
        String artista = edtMain_artista.getText().toString();
        String album = edtMain_album.getText().toString();
        String fecha = edtMain_fecha.getText().toString();

        if (!id.isEmpty() && !artista.isEmpty() && !album.isEmpty() && !fecha.isEmpty()){
            ContentValues fila = new ContentValues();
            fila.put("id", id);
            fila.put("artista", artista);
            fila.put("album", album);
            fila.put("fecha", fecha);

            db.insert("discos", null, fila);
            db.close();

            edtMain_id.setText("");
            edtMain_artista.setText("");
            edtMain_album.setText("");
            edtMain_fecha.setText("");

            Toast.makeText(this, "Disco guardado exitosamente", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Por favor, complete los campos", Toast.LENGTH_SHORT).show();
        }


    }
}