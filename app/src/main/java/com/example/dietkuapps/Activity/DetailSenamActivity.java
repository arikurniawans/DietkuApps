package com.example.dietkuapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dietkuapps.Kelas.SharedVariabel;
import com.example.dietkuapps.R;

public class DetailSenamActivity extends AppCompatActivity {
ImageView imgSenam;
TextView txtGerakan, txtDeskripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_senam);
        setTitle("Detail Gerakan Senam");
        imgSenam = (ImageView) findViewById(R.id.imgSenam);
        txtGerakan = (TextView) findViewById(R.id.txtGerakan);
        txtDeskripsi = (TextView) findViewById(R.id.txtDeskripsi);
        Glide.with(this)
                .load(SharedVariabel.FotoSenam) // image url
                .placeholder(R.drawable.ic_dietku) // any placeholder to load at start
                .error(R.drawable.ic_dietku)  // any image in case of error
                .override(500, 500)
        .into(imgSenam); // resizing
        txtGerakan.setText(SharedVariabel.NamaSenam);
        txtDeskripsi.setText(SharedVariabel.Deskripsi);
    }
}