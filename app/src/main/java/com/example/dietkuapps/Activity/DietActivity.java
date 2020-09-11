package com.example.dietkuapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dietkuapps.Adapter.AdapterDiet;
import com.example.dietkuapps.Adapter.AdapterSenam;
import com.example.dietkuapps.Kelas.ClassDiet;
import com.example.dietkuapps.Kelas.ClassSenam;
import com.example.dietkuapps.Kelas.ConfigApi;
import com.example.dietkuapps.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietActivity extends AppCompatActivity {
    ProgressDialog loading;
    private RecyclerView recyclerView;
    AdapterDiet adapterDiet;
    List<ClassDiet> listDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        setTitle("Diet");
        listDiet = new ArrayList<ClassDiet>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewDiet);
        adapterDiet = new AdapterDiet(DietActivity.this,listDiet);

        recyclerView.setLayoutManager(new LinearLayoutManager(DietActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterDiet);
        getData();
    }

    private void getData() {

        loading = ProgressDialog.show(DietActivity.this, "Mohon Tunggu...", "Sedang Proses...", false, false);

        String url = ConfigApi.DietApi;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //Toast.makeText(JadwalActivity.this,response,Toast.LENGTH_LONG).show();
                showDiet(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DietActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(DietActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showDiet(String response){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        listDiet.clear();
        adapterDiet.notifyDataSetChanged();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id_diet = jo.getString("id_diet");
                String nama_diet = jo.getString("nama_diet");
                String panduan = jo.getString("panduan");
                String manfaat = jo.getString("manfaat");
                String makanan = jo.getString("makanan");

                ClassDiet diet = new ClassDiet(
                        id_diet,
                        nama_diet,
                        panduan,
                        manfaat,
                        makanan
                );
                listDiet.add(diet);
                adapterDiet.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(DietActivity.this
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }

}