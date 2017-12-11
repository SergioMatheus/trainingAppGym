package com.sergio.gymtrainning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    public static final String BANCO = "Nome_Banco";
    public static final int VERSAO = 14;
    private Context context;

    public static final String TABELA = "EXERCICIOS";
    public static final String COL_ID = "id";
    public static final String COL_NOME_EXERCICIO = "NOME_EXERCICIO";
    public static final String COL_DESCRICAO = "DESCRICAO";
    public static final String COL_IMAGEM = "IMAGEM";

    private static final String DATABASE_CREATE = "create table " + TABELA + "(" + COL_ID + " Integer primary key autoincrement ,"
            + COL_NOME_EXERCICIO + "" +
            " text not null," + COL_DESCRICAO + " text not null, "+ COL_IMAGEM + " text not null, "+");";

    public Banco(Context context) {
        super(context, BANCO, null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+Banco.TABELA);
        onCreate(db);
    }
}
