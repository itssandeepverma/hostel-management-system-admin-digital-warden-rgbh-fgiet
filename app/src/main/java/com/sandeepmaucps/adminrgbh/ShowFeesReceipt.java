package com.sandeepmaucps.adminrgbh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class ShowFeesReceipt extends AppCompatActivity {

    ZoomageView imageview;
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fees_receipt);
        getSupportActionBar().hide();
        imageview=findViewById(R.id.myZoomageView);
           progressbar=findViewById(R.id.progressbar);

        String emailid = getIntent().getStringExtra("emailid");
        //Toast.makeText(this, emailid , Toast.LENGTH_SHORT).show();
        String url="http://lostboyjourney.000webhostapp.com/rgbh/receipt/" + emailid + ".jpg";
        Picasso.get().load(url)
                .memoryPolicy(MemoryPolicy.NO_CACHE).into(imageview);

    }
}
