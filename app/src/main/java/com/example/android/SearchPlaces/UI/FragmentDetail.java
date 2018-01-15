package com.example.android.SearchPlaces.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.SearchPlaces.POJO.DataList;
import com.example.android.SearchPlaces.R;

/**
 * Created by parth on 12/18/2017.
 */
public class FragmentDetail extends AppCompatActivity {
    DataList dl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fragment);
        Intent i = getIntent();
        dl= i.getParcelableExtra("data");
        ImageView imageView= (ImageView)findViewById(R.id.imageView);
        TextView pName= (TextView)findViewById(R.id.placeName);
        TextView pAddress= (TextView)findViewById(R.id.placeAddress);
        TextView rating= (TextView)findViewById(R.id.rating);

        if(!dl.getIcon().isEmpty())  Glide.with(this).load(dl.getIcon()).into(imageView);
        pName.setText(dl.getPlaceName());
        pAddress.setText(dl.getPlaceAddress());
        rating.setText(String.valueOf("Ratings-"+dl.getRating()));
    }


}
