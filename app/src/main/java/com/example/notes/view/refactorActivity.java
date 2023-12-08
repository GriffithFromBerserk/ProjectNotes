package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.viewmodel.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class refactorActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editHeading;
    private EditText editDescription;
    private String id;
    private FloatingActionButton save;
    private FloatingActionButton delete;
    private FloatingActionButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refactor);

        editHeading = findViewById(R.id.addHeadingView);
        editDescription = findViewById(R.id.addDescriptionView);
        save = findViewById(R.id.fabSave);
        delete = findViewById(R.id.fabDelete);
        back = findViewById(R.id.fabBack3);

        Intent intent = getIntent();
        // запись этих данных на экран активности
        editHeading.setText(intent.getStringExtra("heading"));
        editDescription.setText(intent.getStringExtra("description"));
        id = intent.getStringExtra("id");

        // обработка нажатия кнопки
        save.setOnClickListener(this);
        delete.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!TextUtils.isEmpty(editHeading.getText().toString()) && !TextUtils.isEmpty(editDescription.getText().toString())) {
            DatabaseHelper database = new DatabaseHelper(refactorActivity.this); // создание объекта БД в текущей активности
            // переключение обратно в активность демонстрации всех записей
            if (view.getId() == R.id.fabSave) {
                database.editNote(editHeading.getText().toString(), editDescription.getText().toString(), id);
                refactorActivity.this.finish();
            } else if (view.getId() == R.id.fabDelete) {
                database.deleteSingleNote(id);
                refactorActivity.this.finish();
            } else if (view.getId() == R.id.fabBack3) {
                finish();
            }
        } else { // иначе просто тост об отсутствии изменений
            Toast.makeText(refactorActivity.this, "Изменений не внесено, для начала обновите данные.", Toast.LENGTH_SHORT).show();
        }
    }
}