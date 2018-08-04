package com.arbresystems.appoint.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBD extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "appoint.db";
    public static final int VERSAO = 1;

    public static final String TABELA_ATENDIMENTO = "atendimento";
    public static final String ID_ATENDIMENTO = "_idAtendimento";
    public static final String ID_ESTABELECIMENTO_ATENDIMENTO = "_idEstabelecimento_Atendimento";
    public static final String DATA_HORARIO_INICIO = "dataHorarioInicio";
    public static final String DATA_HORARIO_FIM = "dataHorarioFim";
    public static final String VALOR = "valor";
    public static final String CONCLUIDO = "concluido";
    public static final String ACEITO = "aceito";
    public static final String AVISO_EM_MINUTOS = "avisoEmMinutos";

    public static final String TABELA_ESTABELECIMENTO = "estabelecimento";
    public static final String ID_ESTABELECIMENTO = "_idEstabelecimento";
    public static final String NOME = "nome";
    public static final String CNPJ = "cnpj";
    public static final String EMAIL = "email";
    public static final String TELEFONE = "telefone";


    public CriaBD(Context context) {
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String sql =
                "CREATE TABLE " + TABELA_ATENDIMENTO + "("
                        + ID_ATENDIMENTO + " integer primary key autoincrement, "
                        + NOME + " text,"
                        + LEGENDA + " text,"
                        + LINK_IMG + " text,"
                        + DEFINICAO  + " text"
                        + ");";

        db.execSQL(sql);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
