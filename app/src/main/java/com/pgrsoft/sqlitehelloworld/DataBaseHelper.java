package com.pgrsoft.sqlitehelloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "amigos.db"; //nombre de la base de datos

    public static final String AMIGOS_TABLE = "AMIGOS"; //nombre de la tabla.

    public static final String COL_1 = "ID"; //nombre de la primera columna que es el ID
    public static final String COL_2 = "NOMBRE"; //nombre de la seguna columna que es el NOMBRE
    public static final String COL_3 = "APELLIDO1"; //nombre de la tercera columna que es el apellido1
    public static final String COL_4 = "APELLIDO2"; //nombre de la seguna columna que es el

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("*******", "DATABASEOPENHELPER");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Entramos en este metodo cuando la BBDD se crea por primera vez
        // se tendra que ejecutar una sentencia de DDL (Data Definition Language)
        //CREACION DE LA TABLA:
        /*CREATE TABLE AMIGOS(
            ID INTEGER PRIMARY KEY AUTOINCREMENT,
            NOMBRE TEXT NOT NULL,
            APELLIDO1 TEXT NOT NULL,
            APPELLIDO2 TEXT
        )*/
        Log.d("*********","onCreate()");

        StringBuilder strSQL = new StringBuilder();
        strSQL.append("CREATE TABLE ")
                .append(AMIGOS_TABLE).append(" (")
                .append(COL_1).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(COL_2).append(" TEXT NOT NULL,")
                .append(COL_3).append(" TEXT NOT NULL,")
                .append(COL_4).append(" TEXT)");

        Log.d("*********", strSQL.toString());

        db.execSQL(strSQL.toString());


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // ENTRAMOS AQUI CUANDO SE DETECTA UN CAMBIO DE VERSION EN LA BBDD
        // Normalment esto conlleva a la creacion de nuevas tablas o columnas en
        // tablas ya existentes
        // DROP TABLE elimina la tabla y onCreate() vuelve a crear el esquema desde cero.

        db.execSQL("DROP TABLE IF EXISTS " + AMIGOS_TABLE);
        onCreate(db);

    }

    //Metodos para realizar operaciones CRUD, obtencion de lista, etc...
    public boolean insertarData(String nombre, String apellido1, String apellido2) {
        // Necesito una refenrencia a la base de datos " como tal"...

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, apellido1);
        contentValues.put(COL_4, apellido2);

        long resultado = db.insert(AMIGOS_TABLE, null, contentValues); // si el resultadi = -1 es que algo ha ido mal
        // si el resulado <= 1 nos indica que el numero de registros afectados

        return resultado == -1 ? false: true;

    }

    public Cursor getAll(){

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + AMIGOS_TABLE, null);

        //Log.d("CONSULTA ***** ", cursor.toString());

        //ESTE CODIGO FUNCIONA Cursor cursor1 = db.rawQuery("SELECT * FROM " + AMIGOS_TABLE + " WHERE " + COL_2 + " IN ('Jordi', 'Prudencio')", null);

        //Log.d("CONSULTA ***** ", cursor1.toString());

        // Que es selectionArgs ???
        // es una String []
        // En la consulta pueden haber ?s que seran substituidos por los valores de este
        // array de string
        // por ejemplo:
        // SELECT * FROM AMIGOS_TABLE WHERE nombre=? AND apellido1 LIKE?%
        //String [] = {"Adolfo", "D"}
        return cursor;
    }

}
