package com.sergio.gymtrainning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_activity);

        Intent intent = DetalheActivity.this.getIntent();

        Exercicio exercicio = (Exercicio) getIntent().getSerializableExtra("exercicio");


        ImageView imagem = (ImageView)
                findViewById(R.id.imageView);
        TextView nomeExercicio = (TextView)
                findViewById(R.id.textView);
        TextView descricao = (TextView)
                findViewById(R.id.textView1);

        Log.d("nome", "nome = " + exercicio.getNomeExercicio());

        Log.d("url", "url = " + exercicio.getUrl());

        Glide.with(this)
                .load(exercicio.getUrl())
                .into(imagem);

        nomeExercicio.setText(exercicio.getNomeExercicio());
        descricao.setText(exercicio.getDescricao());
    }
}
