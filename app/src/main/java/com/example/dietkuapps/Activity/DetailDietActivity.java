package com.example.dietkuapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.dietkuapps.Kelas.SharedVariabel;
import com.example.dietkuapps.R;

public class DetailDietActivity extends AppCompatActivity {
TextView txtNamaDiet, txtPanduan, txtManfaat, txtMakanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diet);
        setTitle("Detail Informasi Diet");
        txtNamaDiet = (TextView) findViewById(R.id.txtNamaDiets);
        txtPanduan = (TextView) findViewById(R.id.txtPanduan);
        txtManfaat = (TextView) findViewById(R.id.txtManfaat);
        txtMakanan = (TextView) findViewById(R.id.txtMakanan);

        txtNamaDiet.setText(SharedVariabel.NamaDiet);
        txtPanduan.setText(SharedVariabel.Panduan);
        txtManfaat.setText(SharedVariabel.Manfaat);
        txtMakanan.setText(SharedVariabel.Makanan);
    }
}