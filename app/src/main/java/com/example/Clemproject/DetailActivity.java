package com.example.Clemproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView txtDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String donneesJson = intent.getStringExtra("donneesKey");
        Donnees donnees = Singletons.getGson().fromJson(donneesJson, Donnees.class);
        showDetail(donnees);
    }

    private void showDetail(Donnees donnees) {
    txtDetail.setText(donnees.getCountry());
    }
}

