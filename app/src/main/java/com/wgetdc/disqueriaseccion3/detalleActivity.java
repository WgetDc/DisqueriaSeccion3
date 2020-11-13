package com.wgetdc.disqueriaseccion3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class detalleActivity extends AppCompatActivity {

    EditText edtDetalle_id;
    TextView txtDetalle_artista, txtDetalle_album, txtDetalle_fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        edtDetalle_id =  findViewById(R.id.edtDetalle_id);
        txtDetalle_artista = findViewById(R.id.txtDetalle_artista);
        txtDetalle_album = findViewById(R.id.txtDetalle_album);
        txtDetalle_fecha = findViewById(R.id.txtDetalle_fecha);
    }

    public void buscarDisco(View view){
        gestorBaseDeDatos gestor = new gestorBaseDeDatos(this, "disqueria", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String id = edtDetalle_id.getText().toString();
        Cursor datos = db.rawQuery("select artista, album, fecha from discos where id=" + id , null);
        if (datos.moveToFirst()){
            txtDetalle_artista.setText(datos.getString(0).toString());
            txtDetalle_album.setText(datos.getString(1).toString());
            txtDetalle_fecha.setText(datos.getString(2));
            db.close();

            edtDetalle_id.setText("");
        }else {
            Toast.makeText(this, "No existen discos asociados a este id", Toast.LENGTH_SHORT).show();
            edtDetalle_id.setText("");
        }
        db.close();
    }
}