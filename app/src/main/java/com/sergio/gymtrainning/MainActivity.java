package com.sergio.gymtrainning;

import android.content.ContentValues;
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

        ExercicioDao exercicio = new ExercicioDao(new Banco(this));

        if (exercicio.listar().isEmpty()){
            Exercicio e = new Exercicio("10","Supino Reto","O press de peito deitado sobre o banco (o supino reto) " +
                    "é um dos cinco exercícios básicos, isto é, aqueles movimentos que mais efetivamente trabalham toda a musculatura corporal em conjunto. " +
                    "Os músculos trabalhados principalmente ao executar este exercício são os peitorais, embora os músculos secundários que participam sejam os ombros e os tríceps.\n" +
                    "\n" +
                    "Este exercício é a chave para trabalhar a parte superior do corpo, assim como para esculpir uma caixa torácica larga e atlética. " +
                    "O press de peito é muito perto do movimento anatômico executado ao fazer flexões no chão; sem dúvida, o uso de barra e/ou halteres no primeiro" +
                    " caso permite levantar maior peso adicional.");
            exercicio.insert(e);

        }

        List<Exercicio> exercicios = exercicio.listar();


        ArrayAdapter<Exercicio> adapter = new ArrayAdapter<Exercicio>(MainActivity.this, android.R.layout.simple_list_item_1, exercicios);


        ListView listView = (ListView) this.findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detalheActivity = new Intent(MainActivity.this, DetalheActivity.class);
                detalheActivity.putExtra("exercicio", (Serializable) adapterView.getItemAtPosition(position));
                startActivity(detalheActivity);
            }
        });

    }
}

