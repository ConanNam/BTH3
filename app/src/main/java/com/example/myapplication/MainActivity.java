package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.model.Book;
import com.example.myapplication.sqlite.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAddBook;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddBook = findViewById(R.id.btAddbook);
        lv = findViewById(R.id.listBook);
        loadListBook();

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(getBaseContext(),AddBook.class);
                startActivityForResult(intentAdd,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1){
            Intent refresh = new Intent(this,MainActivity.class);
            startActivity(refresh);
            finish();
        }

        if(resultCode == RESULT_OK && requestCode == 2){
            Intent refresh = new Intent(this,MainActivity.class);
            startActivity(refresh);
            finish();
        }
    }

    protected void loadListBook(){
        DatabaseHelper db = new DatabaseHelper(getBaseContext());
        final ArrayList<Book> listBooks = db.getAllBook();
        ArrayAdapter<Book> adapter=new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1,listBooks);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gotoBookDetail(listBooks.get(i));
            }
        });
    }

    protected void gotoBookDetail(Book book){
        Intent intentDetail = new Intent(this,Book_Detail.class);
        intentDetail.putExtra("book",book);
        startActivityForResult(intentDetail,2);
    }
}