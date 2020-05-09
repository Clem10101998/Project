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

    private void showDetail(Donnees donnees) {

    txtDetail.setText("Pays :"+" "+donnees.getCountry());
    txtDetail2.setText("Country Code :"+" "+donnees.getCountryCode());
    txtDetail3.setText("Nouvelles personnes infectées :"+" "+donnees.getNewConfirmed());
    txtDetail4.setText("Nombre total de personnes infectées : "+" "+donnees.getTotalConfirmed());
    txtDetail5.setText("Nouveaux décès liés au virus : "+" "+donnees.getNewDeaths());
    txtDetail6.setText("Nombre total de décès liés au virus : "+" "+donnees.getTotalDeaths());
    txtDetail7.setText("Nouvelles personnes décontaminées : "+" "+donnees.getNewRecovered());
    txtDetail8.setText("Nombre total de personnes décontaminées : "+" "+donnees.getTotalRecovered());
    txtDetail9.setText("Date de mise à jour des données : "+" "+donnees.getDate());
    }
}

