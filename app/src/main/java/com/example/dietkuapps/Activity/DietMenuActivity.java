package com.example.dietkuapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dietkuapps.R;

public class DietMenuActivity extends AppCompatActivity {
Button btnDiet1, btnDiet2, btnDiet3, btnDiet4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_menu);
        setTitle("Jenis Diet");
        btnDiet1 = (Button) findViewById(R.id.btndiet1);
        btnDiet2 = (Button) findViewById(R.id.btndiet2);
        btnDiet3 = (Button) findViewById(R.id.btndiet3);
        btnDiet4 = (Button) findViewById(R.id.btndiet4);

        btnDiet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DetailInfoDietActivity.class);
                intent.putExtra("no_diet","1");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnDiet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DetailInfoDietActivity.class);
                intent.putExtra("no_diet","2");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnDiet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DetailInfoDietActivity.class);
                intent.putExtra("no_diet","3");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnDiet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DetailInfoDietActivity.class);
                intent.putExtra("no_diet","4");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}