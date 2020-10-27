package com.example.cafeorderapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnManager,btnKasir,btnKoki,btnPelayan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManager = findViewById(R.id.btn_manager);
        btnKasir = findViewById(R.id.btn_kasir);
        btnKoki = findViewById(R.id.btn_koki);
        btnPelayan = findViewById(R.id.btn_pelayan);

        btnPelayan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }
}
