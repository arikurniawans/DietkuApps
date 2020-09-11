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
import com.example.dietkuapps.Adapter.AdapterSenam;
import com.example.dietkuapps.Kelas.ClassSenam;
import com.example.dietkuapps.Kelas.ConfigApi;
import com.example.dietkuapps.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SenamActivity extends AppCompatActivity {
    ProgressDialog loading;
    private RecyclerView recyclerView;
    AdapterSenam adapterSenam;
    List<ClassSenam> listSenam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senam);
        setTitle("Senam");
        listSenam = new ArrayList<ClassSenam>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewSenam);
        adapterSenam = new AdapterSenam(SenamActivity.this,listSenam);

        recyclerView.setLayoutManager(new LinearLayoutManager(SenamActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterSenam);
        getData();
    }

    private void getData() {

        loading = ProgressDialog.show(SenamActivity.this, "Mohon Tunggu...", "Sedang Proses...", false, false);

        String url = ConfigApi.SenamApi;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //Toast.makeText(JadwalActivity.this,response,Toast.LENGTH_LONG).show();
                showSenam(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SenamActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(SenamActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showSenam(String response){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        listSenam.clear();
        adapterSenam.notifyDataSetChanged();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id_senam = jo.getString("id_senam");
                String nama_senam = jo.getString("nama_senam");
                String foto_senam = jo.getString("foto_senam");
                String deskripsi = jo.getString("deskripsi");

                ClassSenam senam = new ClassSenam(
                        id_senam,
                        nama_senam,
                        foto_senam,
                        deskripsi
                );
                listSenam.add(senam);
                adapterSenam.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(SenamActivity.this
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }


}