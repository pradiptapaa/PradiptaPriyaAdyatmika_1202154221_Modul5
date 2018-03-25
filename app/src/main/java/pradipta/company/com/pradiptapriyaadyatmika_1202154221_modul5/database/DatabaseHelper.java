package pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.model.Todo;

/**
 * Created by monoc on 3/24/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase db;

    // Database Name
    private static final String DATABASE_NAME = "todo_db";
    // Database Version
    private static final int DATABASE_VERSION = 1;
   // Nama tabel
    public static final String TABLE_NAME = "TodoTable";

    //Nama kolom database
    public static final String COLUMN_TODO= "todo";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRIORITY = "priority";


    //konstruktor class
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS "+DATABASE_NAME+" (TODO VARCHAR(35) PRIMARY KEY, DESCRIPTION VARCHAR(50), PRIORITY VARCHAR(3))");

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create todos table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (TODO VARCHAR(35) PRIMARY KEY, DESCRIPTION VARCHAR(50), PRIORITY VARCHAR(3))");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    //memasukkan data ke database
    public boolean insertTodo(Todo todo) {
        //mencocokkan kolom beserta isinya yang ada di database
        ContentValues val = new ContentValues();
        val.put(COLUMN_TODO, todo.getTodo());
        val.put(COLUMN_DESCRIPTION, todo.getDescription());
        val.put(COLUMN_PRIORITY, todo.getPriority());
        long hasil = db.insert(TABLE_NAME, null, val);
        if (hasil==-1) {
            return false;
        }else {
            return true;
        }
    }

    //method untuk menghapus data pada database
    public boolean removedata(String ToDo) {
        return db.delete(TABLE_NAME, COLUMN_TODO+"=\""+ToDo+"\"", null)>0;
    }

    //method untuk mengakses dan membaca data dari database
    public void readdata(ArrayList<Todo> read){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT TODO, DESCRIPTION, PRIORITY FROM "+TABLE_NAME, null);
        while (cursor.moveToNext()){
            read.add(new Todo(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}
