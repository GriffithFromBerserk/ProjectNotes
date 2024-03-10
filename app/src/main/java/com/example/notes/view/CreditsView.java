package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.notes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreditsView extends AppCompatActivity implements View.OnClickListener {
private FloatingActionButton back;
private ImageButton EasterEgg;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_view);
        back = findViewById(R.id.fabBack);
        back.setOnClickListener(this);

        EasterEgg = findViewById(R.id.EasterButton);
        EasterEgg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabBack) {
            Intent intentBack = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentBack);
            finish();
        } else if (v.getId() == R.id.EasterButton) {
            count++;
            if (count >= 3) {
                Intent intent = new Intent(CreditsView.this, EasterView.class);
                startActivity(intent);
                Toast.makeText(this, "Активирован секретный протокол!", Toast.LENGTH_LONG).show();
                count = 0;
            }
        }

    }
}