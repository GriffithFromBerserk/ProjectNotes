package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.notes.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button newNoteButton;
    private Button toArchiveButton;
    private Button toCreditsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNoteButton = findViewById(R.id.buttonMake);
        toArchiveButton = findViewById(R.id.buttonToArchive);
        toCreditsButton = findViewById(R.id.buttonToCredits);

        newNoteButton.setOnClickListener(this);
        toArchiveButton.setOnClickListener(this);
        toCreditsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
if (v.getId() == R.id.buttonMake){
    startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
    MainActivity.this.finish();
} else if (v.getId() == R.id.buttonToArchive) {
    startActivity(new Intent(MainActivity.this, ViewActivity.class));
    MainActivity.this.finish();
} else if (v.getId() == R.id.buttonToCredits) {
    startActivity(new Intent(MainActivity.this, CreditsView.class));
    MainActivity.this.finish();
}
    }
}