package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.viewmodel.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText addHeading;
    private EditText addDescription;
    private FloatingActionButton addNoteButton;
    private FloatingActionButton cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        addHeading = findViewById(R.id.addHeadingView);
        addDescription = findViewById(R.id.addDescriptionView);
        addNoteButton = findViewById(R.id.fabAdd);
        cancelButton = findViewById(R.id.fabCancel);

        addNoteButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(addHeading.getText().toString()) && !TextUtils.isEmpty(addDescription.getText().toString()) && v.getId() == R.id.fabAdd){

            DatabaseHelper database = new DatabaseHelper(AddNoteActivity.this); // создание объекта БД в текущей активности
            database.addNotes(addHeading.getText().toString(), addDescription.getText().toString()); // создание записи в БД

            // создание намерения переключения активности
            Intent intent = new Intent(AddNoteActivity.this, ViewActivity.class); // переключение обратно в активность демонстрации всех записей
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // установления флага экономии ресурсов
            startActivity(intent);
            finish(); // при нажатии на кнопку назад действие уничтожается и проиходит переход в активность SecondActivity

        } else if (v.getId() == R.id.fabCancel) {
            Intent intentBack = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentBack);
            finish();
        } else { // иначе просто тост об отсутствии изменений
            Toast.makeText(AddNoteActivity.this, "Вы не заполнили оба поля, пустую заметку нельзя создать.", Toast.LENGTH_LONG).show();
        }
    };
    }
