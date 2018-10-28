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

public class SearchByRoom extends AppCompatActivity {


    TextView name,email,mobile,bed,room,branch,year,address,fees,rollno;
    String send_floor,send_room;
    ListView lv;
    Button check;
    List<Hero> heroList;
    Spinner searchroom,searchfloor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_room);
         getSupportActionBar().hide();

        searchfloor =findViewById(R.id.searchfloor);
        searchroom =findViewById(R.id.searchroom);
        check=findViewById(R.id.check);
        lv=findViewById(R.id.lv);

        searchfloor.setAdapter(new ArrayAdapter<String>(SearchByRoom.this, android.R.layout.simple_spinner_dropdown_item, Details.floor));
        searchfloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // On selecting a spinner item
                send_floor=Details.floor[position];
                if(position==0)
                    searchroom.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Details.ground));
                else if(position==1)
                    searchroom.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Details.first));
                else
                    searchroom.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Details.second));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        searchroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // On selecting a spinner item
                if (send_floor.equals("ground"))
                    send_room=Details.ground[position];


                else  if (send_floor.equals("first"))
                    send_room=Details.first[position];

                else
                    send_room=Details.second[position];

                // Showing selected spinner item
                //Toast.makeText(getContext(), "Selected branch : " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                final ProgressDialog progressDialog = new ProgressDialog(SearchByRoom.this);
                progressDialog.setMessage("Fetching..."); // Setting Message
                progressDialog.setTitle("Please Wait !"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(true);
        String url="http://lostboyjourney.000webhostapp.com/rgbh/adminsearchroomrgbh.php";  // fetching data
        StringRequest sr= new StringRequest(1, url, new Response.Listener<String>() {   //Response listener interface
            @Override
            public void onResponse(String response) { //contain the complete json data in onresponse
                heroList = new ArrayList<>();

                try {
                    int i=1;
                    JSONObject jo=new JSONObject(response);
                    JSONArray ja= jo.getJSONArray("data");



                        for (i = 0; i < ja.length(); i++) {

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
                            // adding some values to our list
                            heroList.add(new Hero(sname, semail, smobile, sbranch, syear, saddress, sroom, sbed, sfees, srollno, sallot));

                        }

                    progressDialog.dismiss();
                        MyListAdapter adapter = new MyListAdapter(getApplicationContext(), R.layout.lvrooms, heroList);
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
                        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(SearchByRoom.this).create();
                        alertDialog.setTitle("No one is in this room");
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

/*

                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("namekey",  "Name     : " +sname);
                        hashMap.put("branchkey","Branch   : " +sbranch);
                        hashMap.put("yearkey",  "Year     : " +syear);
                        hashMap.put("bedkey",   "Bed      : " +sbed);
                        hashMap.put("mobilekey",  "Mobile no. : " +smobile);
                        hashMap.put("emailkey",  "Emailid : " +semail);
                        hashMap.put("roomkey",  "Room : " +sroom);
                        hashMap.put("addresskey",  "Address : " +saddress);
                        hashMap.put("feeskey",  "Fees paid : " +sfees);
                        hashMap.put("rollnokey",  "Roll no. : " +srollno);

                        arrayList.add(hashMap);
                        // Toast.makeText(getContext(), sname+sbed, Toast.LENGTH_SHORT).show();//to set content in lv


                    }
                    if(i==0)
                        Toast.makeText(getApplicationContext(), "NO one is there in this room", Toast.LENGTH_SHORT).show();

                    String[] from={"namekey","bedkey","branchkey","yearkey",
                            "rollnokey","emailkey","mobilekey","feeskey","roomkey","addresskey"};

                    int[] to={R.id.room_name,R.id.room_bed,R.id.room_branch,R.id.room_year,R.id.room_rollno,
                            R.id.room_email,R.id.room_mobile, R.id.room_fees,R.id.room_room,R.id.room_address}; //taken always in integer string coz id is always in integer form
                    SimpleAdapter sa=new SimpleAdapter(getApplicationContext(),arrayList,R.layout.lvrooms,from,to);

                    lv.setAdapter(sa);*/
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

                map.put("room",send_room );
                return map;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(sr); // we need http protocol queue means fcfs  // queue makes all data request in a sequence like 1,url,
            }
        });

    }

}
