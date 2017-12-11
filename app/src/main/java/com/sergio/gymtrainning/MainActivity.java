package com.sergio.gymtrainning;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Banco bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imagem = this.findViewById(R.id.imageView);
        imagem.setImageResource(R.drawable.imagem_inicial);

        TextView textView = (TextView) this.findViewById(R.id.textView1);
        textView.setText(R.string.tipo_treino);


        List<Exercicio> lista = new ArrayList<>();
        Cursor c = bd.getReadableDatabase().query("filmes", new String[]{"id,nomeExercicio,descricao,imagem"}, null, null, null, null, null);
        if (c.moveToNext()) {
            c.moveToFirst();
            do {
                Exercicio f = new Exercicio(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
                lista.add(f);
            } while (c.moveToNext());
            bd.getWritableDatabase().close();
        }

        ArrayAdapter adapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,lista);


        ListView listView = (ListView) this.findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detalheActivity = new Intent(MainActivity.this,DetalheActivity.class);
                detalheActivity.putExtra("exercicio", (Serializable) adapterView.getItemAtPosition(position));
                startActivity(detalheActivity);
            }
        });

    }
}

