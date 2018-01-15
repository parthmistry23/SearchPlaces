package com.example.android.SearchPlaces.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.SearchPlaces.UI.DataListActivity;
import com.example.android.SearchPlaces.POJO.DataList;
import com.example.android.SearchPlaces.R;

import java.util.List;

/**
 * Created by parth on 12/16/2017.
 */
public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.DataListViewHolder> {
    private List<DataList> dataLists;
    private Context context;
    private ItemClickListener clickListener;


    public DataListAdapter(Context context,DataListActivity dataListActivity, List<DataList> dataLists) {
        this.dataLists = dataLists;
        this.context = context;
    }

    @Override
    public DataListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.grid_data,parent,false);
        return new DataListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataListViewHolder holder, int position) {
        DataList dataList= dataLists.get(position);
        if(dataList != null){
            if(!dataList.getIcon().isEmpty()) Glide.with(context).load(dataList.getIcon()).into(holder.icon);
            if(!dataList.getPlaceName().isEmpty())holder.placeName.setText(dataList.getPlaceName());
            holder.placeAddress.setText(dataList.getPlaceAddress());
            holder.rating.setText(String.valueOf("Ratings-"+dataList.getRating()));

        }

    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }
    public List<DataList> getAllItems(){
        return dataLists;
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class DataListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView icon;
        TextView placeName,placeAddress,rating;
        public DataListViewHolder(View itemView) {
            super(itemView);
            icon=(ImageView)itemView.findViewById(R.id.icon);
            placeName= (TextView)itemView.findViewById(R.id.placeName);
            placeAddress= (TextView)itemView.findViewById(R.id.placeAddress);
            rating=(TextView)itemView.findViewById(R.id.rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }
}
