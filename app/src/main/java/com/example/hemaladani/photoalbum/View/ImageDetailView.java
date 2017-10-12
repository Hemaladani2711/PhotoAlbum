package com.example.hemaladani.photoalbum.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemaladani.photoalbum.Data.Photos;
import com.example.hemaladani.photoalbum.R;
import com.squareup.picasso.Picasso;

/**
 * Created by hemaladani on 10/12/17.
 */

public class ImageDetailView extends AppCompatActivity {
    ImageView imgDetail;
    TextView txtDetailName,txtDetailDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity);
        initialize();
        Bundle bundle=getIntent().getBundleExtra("p");
        Photos photos=(Photos) bundle.getSerializable("photos");
        txtDetailName.setText(photos.getmTitle());
        txtDetailDescription.setText(photos.getmImageName());
        Picasso.with(getApplicationContext()).load(photos.getmUrl()).into(imgDetail);
    }

    public void initialize(){
        imgDetail=(ImageView)findViewById(R.id.imgDetailView);
        txtDetailName=(TextView)findViewById(R.id.txtDetailName);
        txtDetailDescription=(TextView)findViewById(R.id.txtDetailDescription);
    }
}
