package br.com.digitalhouse.sqlitedbopenhelper.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    // Nome do banco de dados
    private static final String NOME_DB = "sqlite_db";

    //Versão do banco de dados
    private static final int VERSAO_DB = 1;

    public Database(Context context) {
        // Chama o contrutor padrão
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table person("
                + "_id integer primary key autoincrement,"
                + "name text not null,"
                + "profession text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table person");
    }
}
