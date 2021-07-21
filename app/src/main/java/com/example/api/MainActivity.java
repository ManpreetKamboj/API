package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    String res;
    String API = "https://api.androidhive.info/contacts/";
    String s1;
    ListView listView;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    String phonedtaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        listView = findViewById(R.id.lv);
        arrayList = new ArrayList();
    }

    public void gett(View view) {

        Detaill det = new Detaill();

        det.execute();

    }

    private class Detaill extends AsyncTask {


        @Override
        protected Object doInBackground(Object[] objects) {

            GettDtt gt = new GettDtt();
            res = gt.makeServiceCall(API);
            try {
                JSONObject wholeobj = new JSONObject(res);
                JSONArray jsarr = wholeobj.getJSONArray("contacts");

                for(int  i = 0;i< jsarr.length();i++)
                {
                    JSONObject arrobj2 = jsarr.getJSONObject(i);
                    s1 = arrobj2.getString("name");
                    JSONObject phobj2 = arrobj2.getJSONObject("phone");
                    //JSONObject phonee = phobj2.getJSONObject("mobile");
                    phonedtaa = phobj2.getString("mobile");
                    arrayList.add(phonedtaa);
                    //arrayList.add(s1);



                }



            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            tv.setText(s1);

            arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(arrayAdapter);




        }
    }
}