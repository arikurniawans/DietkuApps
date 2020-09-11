package com.example.dietkuapps.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dietkuapps.Kelas.SharedVariabel;
import com.example.dietkuapps.R;

public class ProfilActivity extends AppCompatActivity {
TextView txtNik, txtNama;
ImageView imgProfil;
Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        setTitle("Profil");
        txtNik = (TextView) findViewById(R.id.txtNik);
        txtNama = (TextView) findViewById(R.id.txtNama);
        imgProfil = (ImageView) findViewById(R.id.imgProfil);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        txtNik.setText(SharedVariabel.NIK);
        txtNama.setText(SharedVariabel.NamaLengkap);

        Glide.with(this)
                .load(R.drawable.ic_profil) // image url
                .placeholder(R.drawable.ic_dietku) // any placeholder to load at start
                .error(R.drawable.ic_dietku)  // any image in case of error
                .override(500, 500)
                .into(imgProfil); // resizing

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfilActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Anda yakin keluar aplikasi ?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }
}