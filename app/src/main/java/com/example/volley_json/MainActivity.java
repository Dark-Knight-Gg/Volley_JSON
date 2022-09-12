package com.example.volley_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
     TextView txt1 ;
     private void AnhXa(){
         txt1 =(TextView) findViewById(R.id.txt1);
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        RequestQueue  requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        StringBuilder stringBuilder = new StringBuilder();
                        try {
                            JSONArray arraydanhsach = response.getJSONArray("danhsach");
                            for(int i=0;i<arraydanhsach.length();i++){
                                JSONObject khoahoc = arraydanhsach.getJSONObject(i);
                                String content = khoahoc.getString("khoahoc");
                                stringBuilder.append(content+"\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        txt1.setText(stringBuilder);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Lỗi+\'"+error);
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }
}