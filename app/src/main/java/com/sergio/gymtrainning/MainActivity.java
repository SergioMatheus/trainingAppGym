package com.sergio.gymtrainning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.Serializable;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private Banco bd = new Banco(this);
    private Exercicio[] exercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imagem = this.findViewById(R.id.imageView);
        imagem.setImageResource(R.drawable.imagem_inicial);

        TextView textView = (TextView) this.findViewById(R.id.textView1);
        textView.setText(R.string.tipo_treino);

        final ExercicioDao exercicio = new ExercicioDao(new Banco(this));

        if (exercicio.listar().isEmpty()) {
            Exercicio e = new Exercicio("10", "Supino Reto", "O press de peito deitado sobre o banco (o supino reto) " +
                    "é um dos cinco exercícios básicos, isto é, aqueles movimentos que mais efetivamente trabalham toda a musculatura corporal em conjunto. " +
                    "Os músculos trabalhados principalmente ao executar este exercício são os peitorais, embora os músculos secundários que participam sejam os ombros e os tríceps.\n" +
                    "\n" +
                    "Este exercício é a chave para trabalhar a parte superior do corpo, assim como para esculpir uma caixa torácica larga e atlética. " +
                    "O press de peito é muito perto do movimento anatômico executado ao fazer flexões no chão; sem dúvida, o uso de barra e/ou halteres no primeiro" +
                    " caso permite levantar maior peso adicional.", "http://www.exercicios-com-halteres.com/exercicios/peito/images/Bench-Press.gif");
            exercicio.insert(e);

        }

        final List<Exercicio> exerciciosList = exercicio.listar();

        final ArrayAdapter<Exercicio> adapter = new ArrayAdapter<Exercicio>(MainActivity.this, android.R.layout.simple_list_item_1, exerciciosList);


        ListView listView = (ListView) this.findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://raw.githubusercontent.com/SergioMatheus/trainingAppGym/master/Json/JsonGym.json",
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("AsyncHttpClient", "onFailure response = " + responseString);
                        Log.d("AsyncHttpClient", throwable.toString());
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d("AsyncHttpClient", "onSuccess response = " + responseString);
                        Gson gson = new GsonBuilder().create();
                        exercicios = gson.fromJson(responseString, Exercicio[].class);
                        adapter.clear();
                        for (Exercicio exercicio1 : exercicios) {
                            adapter.add(exercicio1);
                            if (exercicio.listar().isEmpty()) {
                                exercicio.insert(exercicio1);
                            }
                        }
                    }
                });

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