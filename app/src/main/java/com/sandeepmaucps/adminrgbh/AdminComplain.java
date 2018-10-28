package com.sandeepmaucps.adminrgbh;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminComplain extends AppCompatActivity {



    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complain);
          getSupportActionBar().hide();
            lv=findViewById(R.id.lv);

        String url = "http://lostboyjourney.000webhostapp.com/rgbh/admincomplainrgbh.php";
        final ProgressDialog progressDialog = new ProgressDialog(AdminComplain.this);
        progressDialog.setMessage("Fetching..."); // Setting Message
        progressDialog.setTitle("Please Wait !"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(true);
        StringRequest stringRequest = new StringRequest(1, url, new com.android.volley.Response.Listener<String>() {
            @Override ///1 for post method
            public void onResponse(String response) {
                ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
                int i;
                try {
                    JSONObject jo=new JSONObject(response);
                    JSONArray ja= jo.getJSONArray("data");
                    for( i=0;i<ja.length();i++) {

                        JSONObject job = ja.getJSONObject(i);
                        String sname=job.getString("name");
                        String  sbed=job.getString("bed");
                        String sroom=job.getString("room");
                        String scomplaintype=job.getString("complaintype");
                        String scomplaindesc=job.getString("complaindesc");

                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("namekey",  "Name     : " +sname);
                        hashMap.put("roomkey","Room   : " +sroom);
                        hashMap.put("complaintypekey",  "Complaintype     : " +scomplaintype);
                        hashMap.put("bedkey",   "Bed      : " +sbed);
                        hashMap.put("complaindesckey",  "Complaindesc : " +scomplaindesc);
                        arrayList.add(hashMap);
                        // Toast.makeText(getContext(), sname+sbed, Toast.LENGTH_SHORT).show();//to set content in lv
                       // Toast.makeText(getApplicationContext(),sname , Toast.LENGTH_SHORT).show();


                    }
                    String[] from={"namekey","bedkey","roomkey","complaindesckey","complaintypekey"};
                    int[] to={R.id.complain_name,R.id.complain_bed,R.id.complain_room,R.id.lv_complaindesc,R.id.lv_complaintype}; //taken always in integer string coz id is always in integer form
                    SimpleAdapter sa=new SimpleAdapter(AdminComplain.this,arrayList,R.layout.lvcomplain,from,to);
                    lv.setAdapter(sa);
                    progressDialog.dismiss();
                   //to set content in lv
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("flag","1" );
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}
