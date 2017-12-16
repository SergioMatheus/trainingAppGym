package com.sergio.gymtrainning;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class ExercicioDao {

    private Banco bd;

    public ExercicioDao(Banco bd) {
        this.bd = bd;
    }

    public void insert(Exercicio exercicio) {
        ContentValues values = new ContentValues();
        values.put(Banco.COL_NOME_EXERCICIO, exercicio.getNomeExercicio());
        values.put(Banco.COL_DESCRICAO, exercicio.getDescricao());
        bd.getWritableDatabase().insert("EXERCICIOS", null, values);
        bd.getWritableDatabase().close();
    }

    public List<Exercicio> listar() {
        List<Exercicio> lista = new ArrayList<>();

        Cursor c = bd.getReadableDatabase().query("EXERCICIOS", new String[]{"_id,nome_exercicio,descricao"}, null, null, null, null, null);
        if (c.moveToNext()) {
            c.moveToFirst();
            do {
                Exercicio f = new Exercicio(c.getString(0), c.getString(1), c.getString(2));
                lista.add(f);
            } while (c.moveToNext());
            bd.getWritableDatabase().close();
        }
        return lista;
    }
}
