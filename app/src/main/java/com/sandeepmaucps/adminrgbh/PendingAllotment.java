package com.sandeepmaucps.adminrgbh;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendingAllotment extends AppCompatActivity {


    TextView name,email,mobile,bed,room,branch,year,address,fees,rollno;

    ListView lv;
    Button check;
    List<Hero> heroList;
    Spinner searchroom,searchfloor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_allotment);
        getSupportActionBar().hide();
        name=findViewById(R.id.room_name);
        address=findViewById(R.id.room_address);
        mobile=findViewById(R.id.room_mobile);
        bed=findViewById(R.id.room_bed);
        room=findViewById(R.id.room_room);
        fees=findViewById(R.id.room_fees);
        email=findViewById(R.id.room_email);
        year=findViewById(R.id.room_year);
        branch=findViewById(R.id.room_branch);
        rollno=findViewById(R.id.room_rollno);

        lv=findViewById(R.id.lv);
        final ProgressDialog progressDialog = new ProgressDialog(PendingAllotment.this);
        progressDialog.setMessage("Fetching..."); // Setting Message
        progressDialog.setTitle("Please Wait !"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(true);
                String url="http://lostboyjourney.000webhostapp.com/rgbh/pendingallotment.php";  // fetching data
                StringRequest sr= new StringRequest(1, url, new Response.Listener<String>() {   //Response listener interface
                    @Override
                    public void onResponse(String response) { //contain the complete json data in onresponse
                        heroList = new ArrayList<>();

                        try {
                            int i=1;
                            JSONObject jo=new JSONObject(response);
                            JSONArray ja= jo.getJSONArray("data");
                            for( i=0;i<ja.length();i++) {

                                JSONObject job = ja.getJSONObject(i);
                                String sname = job.getString("name");
                                String smobile = job.getString("mobile");
                                String syear = job.getString("year");
                                String sbranch = job.getString("branch");
                                String saddress = job.getString("address");
                                String srollno = job.getString("rollno");
                                String sroom = job.getString("room");
                                String sbed = job.getString("bed");
                                String sfees = job.getString("fees");
                                String semail = job.getString("emailid");
                                String sallot = job.getString("allot");
                              //  Toast.makeText(PendingAllotment.this, sname, Toast.LENGTH_SHORT).show();
                                // adding some values to our list
                                heroList.add(new Hero(sname, semail, smobile, sbranch, syear, saddress, sroom, sbed, sfees, srollno,sallot));

                            }
                            progressDialog.dismiss();
                            MyListAdapter2 adapter = new MyListAdapter2(getApplicationContext(), R.layout.lvrooms, heroList);
                            adapter.notifyDataSetChanged();
                            //attaching adapter to the listview
                            lv.setAdapter(adapter);

                            int j=0;
                            for(j=0;j<ja.length();j++)
                            {
                                int k=0;
                            }
                            if(j==0) {

                                progressDialog.dismiss();
                                final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(PendingAllotment.this).create();
                                alertDialog.setTitle("No allocation pending ");
                                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        alertDialog.dismiss();
                                    }
                                });
                                alertDialog.show();  //show karna na bhulo
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //  Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        }

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> map = new HashMap<>();

                        map.put("room","1");
                        return map;
                    }
                };

                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(sr); // we need http protocol queue means fcfs  // queue makes all data request in a sequence like 1,url,
            }
}
