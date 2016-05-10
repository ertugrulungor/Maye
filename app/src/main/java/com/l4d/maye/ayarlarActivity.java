package com.l4d.maye;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ayarlarActivity extends Activity {

    Button guncelle;
    EditText mail,kullaniciadi,sifre;
    TextView txtUser;
    private List<String> liste = new ArrayList<>();
    private final String serverUrl = "http://dijitaloyunzirvesi.com/android/edit.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);

        guncelle = (Button)findViewById(R.id.btnGuncelle);

        mail = (EditText)findViewById(R.id.editMail);
        sifre = (EditText)findViewById(R.id.editSifre);
        kullaniciadi = (EditText)findViewById(R.id.editKullanici);
        txtUser = (TextView) findViewById(R.id.txtUser);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        mail.setText(preferences.getString("mail", "mail"));
        sifre.setText(preferences.getString("sifre", "sifre"));
        kullaniciadi.setText(preferences.getString("kullanici", "kullanici"));
        final String id = preferences.getString("i","i");

        txtUser.setText(preferences.getString("kullanici", "kullanici"));

        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liste.add(serverUrl);
                liste.add(mail.getText().toString());
                liste.add(kullaniciadi.getText().toString());
                liste.add(sifre.getText().toString());
                liste.add(id);

                JsonParcala j=new JsonParcala(ayarlarActivity.this,liste,0);
                j.guncelle();
            }
        });
    }
}

