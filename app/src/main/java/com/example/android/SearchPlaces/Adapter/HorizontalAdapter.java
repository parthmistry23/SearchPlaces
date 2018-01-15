package com.example.android.SearchPlaces.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.SearchPlaces.UI.MapsActivity;
import com.example.android.SearchPlaces.POJO.Circle;
import com.example.android.SearchPlaces.R;

import java.util.List;

/**
 * Created by parth on 12/15/2017.
 */
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>{

    private List<Circle> items;

    public HorizontalAdapter(List<Circle> items) {
        this.items = items;
    }

    public HorizontalAdapter(MapsActivity mapsActivity, List<Circle> items) {
        this.items = items;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.item_list,parent,false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, int position) {
        Circle circle=items.get(position);
        holder.txt.setText(circle.getPlaceName());
        holder.txt1.setText(String.valueOf("Rating-"+circle.getRating()));
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public List<Circle> getAllItems(){
        return items;
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder{
        TextView txt,txt1;
        public HorizontalViewHolder(View itemView) {
            super(itemView);
            txt= (TextView)itemView.findViewById(R.id.textView);
            txt1= (TextView)itemView.findViewById(R.id.textView1);
        }
    }
}
