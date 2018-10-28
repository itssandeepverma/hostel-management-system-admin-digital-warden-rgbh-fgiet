package com.sandeepmaucps.adminrgbh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Belal on 9/14/2017.
 */

//we need to extend the ArrayAdapter class as we are building an adapter
public class MyListAdapter extends ArrayAdapter<Hero> {

    //the list values in the List of type hero
    List<Hero> heroList;
    TextView name, email, mobile, bed, room, branch, year, address, fees, rollno;
    //activity context
    Context context;
    String flag;
Button feesreceipt;
    //the layout resource file for the list items
    int resource,a=0;
    Button allot;
ImageView room_image;
    //constructor initializing the values
    public MyListAdapter(Context context, int resource, List<Hero> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = R.layout.lvrooms;
        this.heroList = heroList;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(R.layout.lvrooms, null, false);

        //getting the view elements of the list from the view
        name = view.findViewById(R.id.room_name);
        address = view.findViewById(R.id.room_address);
        mobile = view.findViewById(R.id.room_mobile);
        bed = view.findViewById(R.id.room_bed);
        room = view.findViewById(R.id.room_room);
        fees = view.findViewById(R.id.room_fees);
        email = view.findViewById(R.id.room_email);
        year = view.findViewById(R.id.room_year);
        branch = view.findViewById(R.id.room_branch);
        rollno = view.findViewById(R.id.room_rollno);
        room_image=view.findViewById(R.id.room_image);
        allot=view.findViewById(R.id.allot);
        feesreceipt=view.findViewById(R.id.room_receipt);//isne bhot preshan kiya so always do the mapping with correct id

        //getting the hero of the specified position
        Hero hero = heroList.get(position);

        //adding values to the list item
        //imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));

        name.setText("Name : "+hero.getName());
        address.setText("Address : "+hero.getAddress());
        mobile.setText("Mobile : "+hero.getMobile());
        bed.setText("Bed : "+hero.getBed());
        room.setText("Room : "+hero.getRoom());
        rollno.setText("Rollno : "+hero.getRollno());
        fees.setText("Fees : "+hero.getFees());
        branch.setText("Branch : "+hero.getBranch());
        year.setText("Year : "+hero.getYear());
        allot.setText(hero.getAllot());
        email.setText(hero.getEmail());
        final String emailid = (String) ((TextView) view.findViewById(R.id.room_email)).getText();
        final String flag= (String) ((Button) view.findViewById(R.id.allot)).getText();
        final String room = (String) ((TextView) view.findViewById(R.id.room_room)).getText();

        //to get the particular value of listview
        String url="http://lostboyjourney.000webhostapp.com/rgbh/uploads/" + emailid + ".jpg";
        Picasso.get().load(url)
                .memoryPolicy(MemoryPolicy.NO_CACHE).into(room_image);

        //adding a click listener to the button to remove item from the list
        feesreceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method
               // view.getContext().startActivity(new Intent(context, ShowFeesReceipt.class));
                Intent intent=new Intent(context.getApplicationContext(), ShowFeesReceipt.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("emailid", emailid);
                context.getApplicationContext().startActivity(intent);
            }
        });



        allot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                AlertDialog.Builder alert = new AlertDialog.Builder(view.getRootView().getContext());
                alert.setMessage("DO YOU WANT TO REMOVE ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final ProgressDialog progressDialog = new ProgressDialog(view.getRootView().getContext());
                                progressDialog.setMessage("Removing..."); // Setting Message
                                progressDialog.setTitle("Please Wait !"); // Setting Title
                                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                                progressDialog.show(); // Display Progress Dialog
                                progressDialog.setCancelable(true);
                                String url = "http://lostboyjourney.000webhostapp.com/rgbh/allotrgbh.php";

                                StringRequest stringRequest=new StringRequest(1, url, new com.android.volley.Response.Listener<String>() {
                                    @Override ///1 for post method
                                    public void onResponse(String response) {

                                        progressDialog.dismiss();
                                        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(view.getRootView().getContext()).create();
                                        alertDialog.setTitle("Removed Successfully");
                                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                alertDialog.dismiss();


                                            }
                                        });
                                        alertDialog.show();
                                        //Toast.makeText(context.getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                                    }
                                }, new com.android.volley.Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {

                                        Map<String,String> map=new HashMap<>();
                                        map.put("emailid",emailid);
                                        map.put("flag",flag);
                                        return map;
                                    }
                                };

                                RequestQueue requestQueue= Volley.newRequestQueue(context.getApplicationContext());
                                requestQueue.add(stringRequest);

                            }
                        }).setNegativeButton("CANCEL", null);

                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });


        //finally returning the view
        return view;
    }
}