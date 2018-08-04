package com.arbresystems.appoint.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBD extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "glossario.db";
    public static final int VERSAO = 1;
    public static final String TABELA = "termo";
    public static final String ID_TERMO = "_idTermo";
    public static final String NOME = "nome";
    public static final String DEFINICAO = "definicao";
    public static final String LEGENDA = "legenda";
    public static final String LINK_IMG = "linkImg";


    public CriaBD(Context context) {
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + TABELA + "("
                        + ID_TERMO + " integer primary key autoincrement, "
                        + NOME + " text,"
                        + LEGENDA + " text,"
                        + LINK_IMG + " text,"
                        + DEFINICAO  + " text"
                        + ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
