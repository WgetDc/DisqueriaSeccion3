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
    
    public void eliminarDisco(View view){
        gestorBaseDeDatos gestor = new gestorBaseDeDatos(this, "disqueria", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();
        
        String id = edtDetalle_id.getText().toString();
        
        if (!id.isEmpty()) {
            int filas = db.delete("discos","id=" + id, null);
            if (filas == 1){
                db.close();
                Toast.makeText(this, "Se ha eliminado el disco exitosamente", Toast.LENGTH_SHORT).show();

                txtDetalle_artista.setText("Artista:");
                txtDetalle_album.setText("Album:");
                txtDetalle_fecha.setText("Fecha:");
                edtDetalle_id.setText("");
            }else{
                Toast.makeText(this, "Este disco no existe", Toast.LENGTH_SHORT).show();
            }
            
        }else {
            Toast.makeText(this, "Por favor, ingrese el id del disco a borrar", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}