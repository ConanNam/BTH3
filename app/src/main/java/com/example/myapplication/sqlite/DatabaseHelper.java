package com.example.myapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Constants_DB;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.User;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;


    public DatabaseHelper(Context context){
        super(context, Constants_DB.DATABASE_NAME,null,1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery1 = "CREATE TABLE "+Constants_DB.TABLE_USER +" (" +
                Constants_DB.USER_ID +" integer primary key, "+
                Constants_DB.USERNAME + " TEXT, "+
                Constants_DB.PASSWORD +" TEXT, "+
                Constants_DB.FULL_NAME+" TEXT, "+
                Constants_DB.ADDRESS+" TEXT)";
        db.execSQL(sqlQuery1);

        String sqlQuery2 = "CREATE TABLE "+Constants_DB.TABLE_BOOK +" (" +
                Constants_DB.BOOK_ID +" integer primary key, "+
                Constants_DB.BOOK_TITLE + " TEXT, "+
                Constants_DB.BOOK_ABOUT +" TEXT, "+
                Constants_DB.BOOK_AUTHOR+" TEXT, "+
                Constants_DB.BOOK_CATEGORY+" TEXT, "+
                Constants_DB.BOOK_PRICE+ " integer)";
        db.execSQL(sqlQuery2);

        Toast.makeText(context, "Create Database successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constants_DB.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+Constants_DB.TABLE_BOOK);
        onCreate(db);
        Toast.makeText(context, "Drop successfully", Toast.LENGTH_SHORT).show();
    }

    public void signUp(User user){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants_DB.USERNAME,user.getUsername());
        values.put(Constants_DB.PASSWORD,user.getPassword());
        values.put(Constants_DB.FULL_NAME,user.getFullname());
        values.put(Constants_DB.ADDRESS,user.getAddress());

        long rs = db.insert(Constants_DB.TABLE_USER,null,values);
        if(rs > 0){
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public User checkLogin(User user){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select * from tbluser where username=? and password =?";
        Cursor cursor = db.rawQuery(sql,new String[]{user.getUsername(),user.getPassword()});
        if (cursor != null){
            cursor.moveToFirst();
        }
        assert cursor != null;
        User u = new User(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        cursor.close();
        db.close();
        return u;

    }

    public void addBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants_DB.BOOK_TITLE,book.getTitle());
        values.put(Constants_DB.BOOK_ABOUT,book.getAbout());
        values.put(Constants_DB.BOOK_AUTHOR,book.getAuthor());
        values.put(Constants_DB.BOOK_CATEGORY,book.getCategory());
        values.put(Constants_DB.BOOK_PRICE,book.getPrice());

        long rs = db.insert(Constants_DB.TABLE_BOOK,null,values);
        if(rs > 0){
            Toast.makeText(context,"Add Book Success",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Add Book Failure",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public long updateBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants_DB.BOOK_TITLE,book.getTitle());
        values.put(Constants_DB.BOOK_ABOUT,book.getAbout());
        values.put(Constants_DB.BOOK_AUTHOR,book.getAuthor());
        values.put(Constants_DB.BOOK_CATEGORY,book.getCategory());
        values.put(Constants_DB.BOOK_PRICE,book.getPrice());


        long rs = db.update(Constants_DB.TABLE_BOOK,values,
                "id=?",
                new String[]{book.getId()+""});
        Log.d("Update",book.getId()+": "+ rs+"");
        db.close();
        return rs;
    }

    public long deleteBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants_DB.BOOK_TITLE,book.getTitle());
        values.put(Constants_DB.BOOK_ABOUT,book.getAbout());
        values.put(Constants_DB.BOOK_AUTHOR,book.getAuthor());
        values.put(Constants_DB.BOOK_CATEGORY,book.getCategory());
        values.put(Constants_DB.BOOK_PRICE,book.getPrice());


        long rs = db.delete(Constants_DB.TABLE_BOOK,
                "id=?",
                new String[]{String.valueOf(book.getId())});
        Log.d("Update",book.getId()+": "+ rs+"");
        db.close();
        db.close();
        return rs;
    }

    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        String sql = "select * from "+ Constants_DB.TABLE_BOOK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                Book book = new Book();
                book.setId(cursor.getInt(0));
                book.setTitle(cursor.getString(1));
                book.setAbout(cursor.getString(2));
                book.setAuthor(cursor.getString(3));
                book.setCategory(cursor.getString(4));
                book.setPrice(cursor.getInt(5));
                list.add(book);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

}
