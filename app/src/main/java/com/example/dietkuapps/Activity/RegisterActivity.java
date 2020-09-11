package com.example.dietkuapps.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dietkuapps.Kelas.ConfigApi;
import com.example.dietkuapps.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
Button btnRegister;
EditText etNik, etNama, etTelp, etUsername, etPassword;
TextView txtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnRegister = (Button) findViewById(R.id.btnRegis);
        etNik = (EditText) findViewById(R.id.etNik);
        etNama = (EditText) findViewById(R.id.etNama);
        etTelp = (EditText) findViewById(R.id.etTelp);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        txtLogin = (TextView) findViewById(R.id.txtLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNik.getText().toString().equals(""))
                {
                    etNik.setError("Nik harus di isi !");
                }
                else if(etNama.getText().toString().equals(""))
                {
                    etNama.setError("Nama harus di isi !");
                }
                else if(etTelp.getText().toString().equals(""))
                {
                    etTelp.setError("Telepon harus di isi !");
                }
                else if(etUsername.getText().toString().equals(""))
                {
                    etUsername.setError("Username harus di isi !");
                }
                else if(etPassword.getText().toString().equals(""))
                {
                    etPassword.setError("Password harus di isi !");
                }
                else
                {
                    Register(etNik.getText().toString(),etNama.getText().toString()
                    ,etTelp.getText().toString(),etUsername.getText().toString(),etPassword.getText().toString());
                }
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void Register(final String nik, final String nama, final String nope, final String username, final String password){
    btnRegister.setEnabled(false);
    btnRegister.setText("Proccessing...");
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("nik", nik));
                nameValuePairs.add(new BasicNameValuePair("nama", nama));
                nameValuePairs.add(new BasicNameValuePair("telp", nope));
                nameValuePairs.add(new BasicNameValuePair("username", username));
                nameValuePairs.add(new BasicNameValuePair("password", password));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            ConfigApi.RegisApi);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                }
                return "success";
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equalsIgnoreCase("success")){
                    Toast.makeText(getApplication(),"Berhasil register",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplication(),"Gagal Simpan Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(nik, nama, nope, username, password);

    }

}