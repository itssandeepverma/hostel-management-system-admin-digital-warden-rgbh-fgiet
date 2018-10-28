package com.sandeepmaucps.adminrgbh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adminlogin extends AppCompatActivity {


    EditText adminemail,adminpassword;
    Button adminlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        getSupportActionBar().hide();
        adminemail=findViewById(R.id.adminlogin_email);
        adminpassword=findViewById(R.id.adminlogin_password);
        adminlogin=findViewById(R.id.adminlogin);
    adminlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(adminemail.getText().toString().equals("admin") && adminpassword.getText().toString().equals("admin"))
            {
                Intent i=new Intent(Adminlogin.this,AdminProfile.class);
                startActivity(i);
                finish();
            }
            else
            {

                adminpassword.setText("");
                Toast.makeText(getApplicationContext(), "Enter Correct details", Toast.LENGTH_SHORT).show();

            }
        }
    });

    }
}
