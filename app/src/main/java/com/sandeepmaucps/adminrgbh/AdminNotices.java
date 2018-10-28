package com.sandeepmaucps.adminrgbh;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AdminNotices extends AppCompatActivity {
    private final  int IMG_REQUEST=1;
    private Bitmap bitmap;
    EditText description;
    Button upload,uploadimages;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notices);
               getSupportActionBar().hide();
         uploadimages=findViewById(R.id.uploadimages);
         description=findViewById(R.id.description);
          image=findViewById(R.id.image);
          upload=findViewById(R.id.upload);


        final Random rand = new Random();
      final int random=rand.nextInt(10000000);

        uploadimages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMG_REQUEST);
            }
        });




        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://lostboyjourney.000webhostapp.com/rgbh/adminnoticesrgbh.php";
                final ProgressDialog progressDialog = new ProgressDialog(AdminNotices.this);
                progressDialog.setMessage("Sending Notice..."); // Setting Message
                progressDialog.setTitle("Please Wait !"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(true);
                StringRequest stringRequest = new StringRequest(1, url, new com.android.volley.Response.Listener<String>() {
                    @Override ///1 for post method
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AdminNotices.this).create();
                        alertDialog.setTitle("Notices Sent Successfully ");
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> map = new HashMap<>();

                        map.put("image", bitmaptostring(bitmap));
                        map.put("desc", description.getText().toString());
                        map.put("random",String.valueOf(random));
                        return map;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


            }
        });

    }


    public String bitmaptostring(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgbyte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgbyte, Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) ;

        {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), path);
                image.setImageBitmap(bitmap);
                image.setVisibility(View.VISIBLE);
                upload.setVisibility(View.VISIBLE);
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }



}
