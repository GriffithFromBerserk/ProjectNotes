package com.example.notes.viewmodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DatabaseName = "MyNotes"; // поле названия БД
    private static final int DatabaseVersion = 1; // поле версии БД

    private static final String tableName = "myNotes"; // поле названия таблицы в БД
    private static final String columnId = "id";  // поле колонки для идентификаторов записей в таблице в БД
    private static final String columnTitle = "title";  // поле колонки для заголовков записей в таблице в БД
    private static final String columnDescription = "description";  // поле колонки для описаний записей в таблице в БД


    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName,null, DatabaseVersion);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableName + " (" + columnId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + columnTitle + " TEXT, " + columnDescription + " Text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ tableName);

        onCreate(db);
    }

    public void addNotes(String title, String description) {

        /** с помощью getWritableDatabase() мы проверяем есть ли подключение к БД,
         * если есть то им пользуемся, если нет то создаём новое
         */
        SQLiteDatabase db = this.getWritableDatabase();

        /** нужно создать объект класса ContentValues для добавления и обновления существующих записей БД,
         * Данный объект представляет словарь, который содержит набор пар "ключ-значение"
         * Для добавления в этот словарь нового объекта применяется метод put
         */
        ContentValues cv = new ContentValues();

        cv.put(columnTitle,title); // добавление заголовка записи
        cv.put(columnDescription,description); // добавление описания записи

        // добавление новой записи в БД
        long resultValue = db.insert(tableName,null, cv);
    }

    public Cursor readNotes(){

        String query = "SELECT * FROM " +  tableName;

        SQLiteDatabase database= this.getReadableDatabase();

        Cursor cursor = null;

        if (database != null){ // если БД существует, то инициализируем курсор
            cursor = database.rawQuery(query,null);
        }

        return  cursor;
    }


    public void deleteAllNotes() {

        SQLiteDatabase database = this.getWritableDatabase();

        String query = "DELETE FROM " + tableName;
        database.execSQL(query);

    }

    public void editNote(String title, String description , String id){

        SQLiteDatabase database =  this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(columnTitle,title);
        cv.put(columnDescription, description);

        long result  = database.update(tableName, cv,"id=?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Отредактировать не получилось: возникла неизвестная ошибка.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Заметка отредактированана.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteSingleNote(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(tableName,"id=?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Отредактировать не получилось: возникла неизвестная ошибка.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Заметка удалена.", Toast.LENGTH_SHORT).show();
        }
    }
}
