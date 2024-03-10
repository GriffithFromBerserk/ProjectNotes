package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.database.Cursor;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.model.Notebook;
import com.example.notes.viewmodel.Adapter;
import com.example.notes.viewmodel.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private List<Notebook> notebookList;
    private DatabaseHelper database;
    private FloatingActionButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.recyclerView);
        buttonBack = findViewById(R.id.fabBack3);

        buttonBack.setOnClickListener(this);

        notebookList = new ArrayList<>();
        database = new DatabaseHelper(this);

        fetchAllNotes();

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // задание структуры вывода данных в recyclerView
        Adapter adapter = new Adapter(this, ViewActivity.this, notebookList); // инициализация адаптера и передача в рего данных из БД
        recyclerView.setAdapter(adapter); // передача в recyclerView адаптер
    }

    public void fetchAllNotes(){

        Cursor cursor = database.readNotes();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Заметок нет", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                notebookList.add(new Notebook(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intentBack = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentBack);
        finish();
    }
}
