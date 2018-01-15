package com.example.android.SearchPlaces.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.SearchPlaces.Adapter.DataListAdapter;
import com.example.android.SearchPlaces.Adapter.ItemClickListener;
import com.example.android.SearchPlaces.POJO.DataList;
import com.example.android.SearchPlaces.R;

import java.util.List;

public class DataListActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView recyclerView;
    DataListAdapter adapter;
    GridLayoutManager layoutManager;
    List<DataList> dataLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        dataLists = getIntent().getParcelableArrayListExtra("data");

        recyclerView = (RecyclerView) findViewById(R.id.dataList);
        layoutManager = new GridLayoutManager(DataListActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DataListAdapter(this, DataListActivity.this, dataLists);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

    }

    @Override
    public void onClick(View view, int position) {
        DataList dl = dataLists.get(position);
        Intent intent = new Intent(DataListActivity.this, FragmentDetail.class);
        intent.putExtra("data", dl);
        startActivity(intent);

    }
}
