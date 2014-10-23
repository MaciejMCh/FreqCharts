package com.example.freqcharts;

import android.content.*;
import android.util.*;

import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.*;



public class DBAdapter {
	
	private static final String DEBUG_TAG = "SqLiteTodoManager";
	 
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String DB_TABLE = "func";
    
    public static final String KEY_ID = "_id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;
    public static final String KEY_NAME = "name";
    public static final String NAME_OPTIONS = "TEXT NOT NULL";
    public static final int NAME_COLUMN = 1;
    public static final String KEY_FUNCTION = "function";
    public static final String FUNCTION_OPTIONS = "TEXT NOT NULL";
    public static final int FUNCTION_COLUMN = 2;
    
    public static final String DB_CREATE_TODO_TABLE =
            "CREATE TABLE " + DB_TABLE + "( " +
            KEY_ID + " " + ID_OPTIONS + ", " +
            KEY_NAME + " " + NAME_OPTIONS + ", " +
            KEY_FUNCTION + " " + FUNCTION_OPTIONS +
            ");";
    private static final String DROP_TODO_TABLE =
            "DROP TABLE IF EXISTS " + DB_TABLE;    
    
    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;
    
    public DBAdapter(Context context){
    	this.context=context;
    }
    
    public DBAdapter open(){
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        //DB_VERSION++;
        return this;
    }
    
    public void close() {
        dbHelper.close();
    }
    
    public long insertTodo(DBRecord aRecord) {
        ContentValues newTodoValues = new ContentValues();
        newTodoValues.put(KEY_NAME, aRecord.getName());
        newTodoValues.put(KEY_FUNCTION, aRecord.getFunction().toString());
        return db.insert(DB_TABLE, null, newTodoValues);
    }
     
   
    
    public boolean deleteTodo(long id){
        String where = KEY_ID + "=" + id;
        return db.delete(DB_TABLE, where, null) > 0;
    }
    
    public Cursor getAllTodos() {
        String[] columns = {KEY_ID, KEY_NAME, KEY_FUNCTION};
        return db.query(DB_TABLE, columns, null, null, null, null, KEY_NAME);
    }
   
    
    
    
    
    
    
    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
     
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_TODO_TABLE);
     
            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_TABLE + " ver." + DB_VERSION + " created");
        }
     
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TODO_TABLE);
     
            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");
     
            onCreate(db);
        }
    }
    

}
