package com.example.oreoclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {

    TextView oreoCount, oreosPerSecond, textViewName;
    ImageView backButton;

    List<Oreos> oreos;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        textViewName = findViewById(R.id.textView4);
        oreoCount = findViewById(R.id.textView);
        oreosPerSecond = findViewById(R.id.textView8);
        backButton = findViewById(R.id.imageView2);
        listView = findViewById(R.id.listView);
        oreos = new ArrayList<>();
        oreos = getIntent().getParcelableArrayListExtra("oreos");

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        textViewName.setText(sharedPreferences.getString("name", "User"));
        oreoCount.setText(sharedPreferences.getInt("oreos", 0) + " Oreos");
        oreosPerSecond.setText(sharedPreferences.getInt("OPS", 0) + " OPS");

        android.os.Handler customHandler = new android.os.Handler();
        customHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
                oreoCount.setText(sharedPreferences.getInt("oreos", 0) + " Oreos");
                customHandler.postDelayed(this, 1000);
            }
        }, 1000);

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_layout, oreos);
        listView.setAdapter(customAdapter);

        backButton.setOnClickListener(v -> {
            final RotateAnimation rotateAnim = new RotateAnimation(0.0f, 180f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            rotateAnim.setDuration(250);
            backButton.startAnimation(rotateAnim);

            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("oreos", (ArrayList<Oreos>) oreos);
            setResult(Shop.RESULT_OK, intent);
            finish();

        });
    }

    public void updateOreoCount(int oreos) {
        oreoCount.setText(oreos + " Oreos");
    }

    public void updateOPS(int OPS) {
        oreosPerSecond.setText(OPS + " OPS");
    }
}