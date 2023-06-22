package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Consumir(View view){
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://dummyjson.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }


    @Override
    public void processFinish(String result) throws JSONException {
        //txtSaludo.setText("Resp: " + result );
        TextView Respuesta = (TextView)findViewById(R.id.txtRespuesta);
        Log.i("Resultado",result);

        String Name="";
       JSONObject jsonUsers = new JSONObject(result);
       JSONArray users = jsonUsers.getJSONArray("users");

       for(int i=0; i<users.length();i++){
           JSONObject firstName = users.getJSONObject(i);
           Name = Name+ "\n" + firstName.getString("firstName").toString() +", " +firstName.getString("age").toString() + ", "+ firstName.getString("email").toString();
        }
        Respuesta.setText(Name);
        /*
        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = banco.getString("access_token").toString();
        }
        txtS.setText("Token: " + lstBancos);

         */
    }
}