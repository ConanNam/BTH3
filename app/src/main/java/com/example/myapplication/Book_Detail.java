package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.model.Book;
import com.example.myapplication.sqlite.DatabaseHelper;

public class Book_Detail extends AppCompatActivity {
    private Button btnUpdate, btnDelete;
    private EditText txtTitle, txtAbout, txtAuthor, txtCategory, txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Intent i = getIntent();
        final Book book = (Book) i.getSerializableExtra("book");
        btnUpdate = findViewById(R.id.button4);
        btnDelete = findViewById(R.id.button5);
        txtTitle = findViewById(R.id.txtTitle_);
        txtAbout = findViewById(R.id.txtAbout_);
        txtAuthor = findViewById(R.id.txtAuthor_);
        txtCategory = findViewById(R.id.txtCategory_);
        txtPrice = findViewById(R.id.txtPrice_);

        setData(book);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitle.getText().toString();
                String about = txtAbout.getText().toString();
                String author = txtAuthor.getText().toString();
                String category = txtCategory.getText().toString();
                int price = Integer.parseInt(txtPrice.getText().toString());
                Book book1 = new Book(title,about,author,category,price);
                book1.setId(book.getId());
                DatabaseHelper db = new DatabaseHelper(getBaseContext());
                long rs = db.updateBook(book1);
                if (rs > 0){
                    setResult(RESULT_OK,null);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"Update failure",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(getBaseContext());
                db.deleteBook(book);
                setResult(RESULT_OK,null);
                finish();
            }
        });
    }

    protected void setData(Book book){
        txtTitle.setText(book.getTitle());
        txtAbout.setText(book.getAbout());
        txtAuthor.setText(book.getAuthor());
        txtCategory.setText(book.getCategory());
        txtPrice.setText(book.getPrice()+"");
    }
}