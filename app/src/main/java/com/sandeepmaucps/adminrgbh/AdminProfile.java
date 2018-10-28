package com.sandeepmaucps.adminrgbh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.squareup.picasso.Picasso;

public class AdminProfile extends AppCompatActivity {

    Button searchbyroom,complain,pending,notices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        searchbyroom=findViewById(R.id.searchbyroom);
        getSupportActionBar().hide();
        notices=findViewById(R.id.notices);
        complain=findViewById(R.id.allcomplain);
        pending=findViewById(R.id.pending);
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),AdminNotices.class);
                startActivity(i);

            }
        });
        searchbyroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),SearchByRoom.class);
                startActivity(i);

            }
        });
        complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),AdminComplain.class);
                startActivity(i);

            }
        });
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),PendingAllotment.class);
                startActivity(i);

            }
        });


    }
}
