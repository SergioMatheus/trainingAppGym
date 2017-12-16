package com.sergio.gymtrainning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetalheActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_activity);

        Exercicio exercicio = (Exercicio) getIntent().getSerializableExtra("exercicio");

        ImageView imagem = (ImageView)
                findViewById(R.id.imageView);
        TextView nomeExercicio = (TextView)
                findViewById(R.id.textView);
        TextView descricao = (TextView)
                findViewById(R.id.textView1);

        if(exercicio.getCategoria()==Categoria.SUPINO_RETO){
            Glide.with(this)
                    .load(R.drawable.supino_deitado)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imagem);
        }

        nomeExercicio.setText(exercicio.getNomeExercicio());
        descricao.setText(exercicio.getDescricao());
    }
}
