package com.example.dietkuapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dietkuapps.Kelas.AppController;
import com.example.dietkuapps.Kelas.ConfigApi;
import com.example.dietkuapps.Kelas.SharedVariabel;
import com.example.dietkuapps.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class KonsulActivity extends AppCompatActivity {
Spinner spinJenkel, spinTujuan;
Calendar myCalendar;
EditText etNama, etTgl, etUsia, etPekerjaan,etBerat, etTinggi, etRiwayat;
String strdate1;
Button btnKons;
    ProgressDialog pDialog;
    int success;
    ConnectivityManager conMgr;
    private static final String TAG = KonsulActivity.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";
    private static final int STORAGE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsul);
        spinJenkel = (Spinner) findViewById(R.id.etDetJenkel);
        spinTujuan = (Spinner) findViewById(R.id.etDetTujuan);
        etTgl = (EditText) findViewById(R.id.etDetTgl);
        etNama = (EditText) findViewById(R.id.etDetNama);
        etUsia = (EditText) findViewById(R.id.etDetUsia);
        etPekerjaan = (EditText) findViewById(R.id.etDetPekerjaan);
        etBerat = (EditText) findViewById(R.id.etDetBerat);
        etTinggi = (EditText) findViewById(R.id.etDetTinggi);
        etRiwayat = (EditText) findViewById(R.id.etDetRiwayat);
        btnKons = (Button) findViewById(R.id.btnKons);

        etUsia.setEnabled(false);
        etNama.setEnabled(false);
        etNama.setText(SharedVariabel.NamaLengkap);
        myCalendar = Calendar.getInstance();

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        strdate1 = sdf1.format(myCalendar.getTime());

        etTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        etTgl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    showDateDialog();
                }

            }
        });

        btnKons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPekerjaan.getText().toString().equals(""))
                {
                    etPekerjaan.setError("Pekerjaan harus disi !");
                }
                else if (etTgl.getText().toString().equals(""))
                {
                    etTgl.setError("Tanggal Lahir harus diisi !");
                }
                else if(etUsia.getText().toString().equals(""))
                {
                    etUsia.setError("Usia harus diisi !");
                }
                else if(etBerat.getText().toString().equals(""))
                {
                    etBerat.setError("Berat Badan harus diisi !");
                }
                else if(etTinggi.getText().toString().equals(""))
                {
                    etTinggi.setError("Tinggi Badan harus diisi !");
                }
                else if(etRiwayat.getText().toString().equals(""))
                {
                    etRiwayat.setError("Riwayat Penyakit harus diisi !");
                }
                else
                {
                    Konsul(SharedVariabel.NIK,etPekerjaan.getText().toString(),etTgl.getText().toString(),
                            etUsia.getText().toString(),spinJenkel.getSelectedItem().toString(),
                            etBerat.getText().toString(),etTinggi.getText().toString(),
                            etRiwayat.getText().toString(),spinTujuan.getSelectedItem().toString());

                }

            }
        });

    }

    private void showDateDialog(){
        new DatePickerDialog(KonsulActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String formatTanggal = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                etTgl.setText(sdf.format(myCalendar.getTime()));
                int nowyear = Integer.parseInt(strdate1);
                int hasilUsia = nowyear - year;
                if(year > nowyear)
                {
                    etUsia.setText("0");
                }
                else
                {
                    etUsia.setText(Integer.toString(hasilUsia));
                }

            }
        },
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void Konsul(final String nik, final String pekerjaan, final String tgl, final String usia,
                       final String jenkel, final String berat, final String tinggi, final String riwayat, final String tujuan){
        btnKons.setEnabled(false);
        btnKons.setText("Proccessing...");
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Sign Up ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, ConfigApi.KonsulApi, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Konsul!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                            //system OS >= Marshmallow(6.0), check if permission is enabled or not
                            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED){
                                //permission was not granted, request it
                                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                requestPermissions(permissions, STORAGE_CODE);
                            }
                            else {
                                //permission already granted, call save pdf method
                                savePdf();
                            }
                        }
                        else {
                            //system OS < Marshmallow, call save pdf method
                            savePdf();
                        }

                        Intent intent = new Intent(KonsulActivity.this, HomeActivity.class);
                        startActivity(intent);


                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        btnKons.setEnabled(true);
                        btnKons.setText("Sign Up");

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();
                btnKons.setEnabled(true);
                btnKons.setText("Sign Up");

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nik", nik);
                params.put("pekerjaan", pekerjaan);
                params.put("tgl", tgl);
                params.put("usia", usia);
                params.put("jenkel", jenkel);
                params.put("berat", berat);
                params.put("tinggi", tinggi);
                params.put("riwayat", riwayat);
                params.put("tujuan", tujuan);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void savePdf() {
        Document mDoc = new Document();
        String mFileName = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        //pdf file path
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";

        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            mDoc.open();
            String Nama = "Nama \t\t\t: "+etNama.getText().toString();
            String Pekerjaan = "Pekerjaan \t\t\t: "+etPekerjaan.getText().toString();
            String Tanggal = "Tanggal Lahir \t\t\t:"+etTgl.getText().toString();
            String Usia = "Usia \t\t\t: "+etUsia.getText().toString()+" Tahun";
            String Jenkel = "Jenis Kelamin \t\t\t: "+spinJenkel.getSelectedItem().toString();
            String Berat = "Berat Badan \t\t\t: "+etBerat.getText().toString()+" Kg";
            String Tinggi = "Tinggi Badan \t\t\t: "+etTinggi.getText().toString()+" Cm";
            String Riwayat = "Riwayat Penyakit \t\t\t: "+etRiwayat.getText().toString();
            String Tujuan = "Tujuan Diet \t\t\t: "+spinTujuan.getSelectedItem().toString();

            mDoc.addAuthor("Diet Apps");
            mDoc.addHeader("Header","Isi");

            mDoc.add(new Paragraph(Nama));
            mDoc.add(new Paragraph(Pekerjaan));
            mDoc.add(new Paragraph(Tanggal));
            mDoc.add(new Paragraph(Usia));
            mDoc.add(new Paragraph(Jenkel));
            mDoc.add(new Paragraph(Berat));
            mDoc.add(new Paragraph(Tinggi));
            mDoc.add(new Paragraph(Riwayat));
            mDoc.add(new Paragraph(Tujuan));

            mDoc.close();
            //Toast.makeText(this, mFileName +".pdf\nis saved to\n"+ mFilePath, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            //if any thing goes wrong causing exception, get and show exception message
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    //handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted from popup, call savepdf method
                    savePdf();
                }
                else {
                    //permission was denied from popup, show error message
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
