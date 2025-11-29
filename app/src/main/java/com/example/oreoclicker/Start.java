package com.example.oreoclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Start extends AppCompatActivity {

    Button next;
    EditText name;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        next = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        name = findViewById(R.id.autoCompleteTextView);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        if (sharedPreferences.contains("name")) {
            Intent intent = new Intent(Start.this, MainActivity.class);
            startActivity(intent);
        }

        //next.setEnabled(false);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        progressBar.setBackgroundColor(0x00000000);

        next.setBackground(getDrawable(R.drawable.button));

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                next.setEnabled(charSequence.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        next.setOnClickListener(v -> {
            if (name.getText().toString().length() > 0) {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                Intent intent = new Intent(Start.this, MainActivity.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name.getText().toString());
                editor.apply();
                startActivity(intent);
            } else {
                name.setError("Please enter a name");
                Toast.makeText(this, "Please enter a VALID name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}