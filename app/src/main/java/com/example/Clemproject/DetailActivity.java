package com.example.Clemproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView txtDetail;
    private TextView txtDetail2;
    private TextView txtDetail3;
    private TextView txtDetail4;
    private TextView txtDetail5;
    private TextView txtDetail6;
    private TextView txtDetail7;
    private TextView txtDetail8;
    private TextView txtDetail9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        txtDetail2 = findViewById(R.id.detail2_txt);
        txtDetail3 = findViewById(R.id.detail3_txt);
        txtDetail4 = findViewById(R.id.detail4_txt);
        txtDetail5 = findViewById(R.id.detail5_txt);
        txtDetail6 = findViewById(R.id.detail6_txt);
        txtDetail7 = findViewById(R.id.detail7_txt);
        txtDetail8 = findViewById(R.id.detail8_txt);
        txtDetail9 = findViewById(R.id.detail9_txt);


        Intent intent = getIntent();
        String donneesJson = intent.getStringExtra("donneesKey");
        Donnees donnees = Singletons.getGson().fromJson(donneesJson, Donnees.class);
        showDetail(donnees);
    }

 @SuppressLint("DefaultLocale")
    private void showDetail(Donnees donnees) {

    txtDetail.setText(String.format("Pays : %s", donnees.getCountry()));
    txtDetail2.setText(String.format("Country Code : %s", donnees.getCountryCode()));
    txtDetail3.setText(String.format("Nouvelles personnes infectées : %d", donnees.getNewConfirmed()));
    txtDetail4.setText(String.format("Nombre total de personnes infectées :  %d", donnees.getTotalConfirmed()));
    txtDetail5.setText(String.format("Nouveaux décès liés au virus :  %d", donnees.getNewDeaths()));
    txtDetail6.setText(String.format("Nombre total de décès liés au virus :  %d", donnees.getTotalDeaths()));
    txtDetail7.setText(String.format("Nouvelles personnes décontaminées :  %d", donnees.getNewRecovered()));
    txtDetail8.setText(String.format("Nombre total de personnes décontaminées :  %d", donnees.getTotalRecovered()));
    txtDetail9.setText(String.format("Date de mise à jour des données :  %s", donnees.getDate()));
    }
}

