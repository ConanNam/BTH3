package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.model.Book;
import com.example.myapplication.sqlite.DatabaseHelper;

public class AddBook extends AppCompatActivity {

    private Button btnAddBook;
    private EditText txtTitle, txtAbout, txtAuthor, txtCategory, txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        btnAddBook = findViewById(R.id.btnAddBook_);
        txtAbout = findViewById(R.id.txtAbout);
        txtTitle = findViewById(R.id.txtTitle);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtCategory = findViewById(R.id.txtCategory);
        txtPrice = findViewById(R.id.txtPrice);


        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitle.getText().toString();
                String about = txtAbout.getText().toString();
                String author = txtAuthor.getText().toString();
                String category = txtCategory.getText().toString();
                int price = Integer.parseInt(txtPrice.getText().toString());
                Book book = new Book(title,about,author,category,price);
                DatabaseHelper db = new DatabaseHelper(getBaseContext());
                db.addBook(book);
                setResult(RESULT_OK,null);
                finish();
            }
        });
    }
}