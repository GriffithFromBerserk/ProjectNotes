package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    final LocalTime MORNING_TIME = LocalTime.of(5,0);
    final LocalTime DAY_TIME = LocalTime.of(11,0);
    final LocalTime EVENING_TIME = LocalTime.of(18,0);
    final LocalTime NIGHT_TIME = LocalTime.of(23,0);
    LocalTime curTime = LocalTime.now();

    private RecyclerView recyclerView;
    private List<Notebook> notebookList;
    private DatabaseHelper database;
    private ImageButton newNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        newNoteButton = findViewById(R.id.addFAB);

        notebookList = new ArrayList<>();
        database = new DatabaseHelper(this);

        fetchAllNotes();

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // задание структуры вывода данных в recyclerView
        Adapter adapter = new Adapter(this, MainActivity.this, notebookList); // инициализация адаптера и передача в рего данных из БД
        recyclerView.setAdapter(adapter); // передача в recyclerView адаптер

        // обработка нажатия кнопки создания новой заметки
        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // переключение на новую активность
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
                MainActivity.this.finish();
            }
        });
    }

    // метод считывания из БД всех записей
    public void fetchAllNotes(){
        // чтение БД и запись данных в курсор
        Cursor cursor = database.readNotes();

        if (cursor.getCount() == 0) { // если данные отсутствую, то вывод на экран об этом тоста
            Toast.makeText(this, "Заметок нет", Toast.LENGTH_SHORT).show();
        } else { // иначе помещение их в контейнер данных notesList
            while (cursor.moveToNext()){
                // помещение в контейнер notesList из курсора данных
                notebookList.add(new Notebook(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }
}
