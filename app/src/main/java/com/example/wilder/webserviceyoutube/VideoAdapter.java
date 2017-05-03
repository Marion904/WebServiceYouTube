package com.example.wilder.webserviceyoutube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wilder on 03/05/17.
 */

public class VideoAdapter extends BaseAdapter {

    private ArrayList<Video> videos;
    private int layout;
    private Context context;
    private TextView title;
    private TextView description;

    public VideoAdapter(ArrayList<Video> array,int layout,Context c){
        this.videos = array;
        this.layout = layout;
        this.context = c;
    }



    public int getCount() {
        return videos.size();
    }

    @Override
    public Video getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,parent,false);
        }
        title =(TextView) convertView.findViewById(R.id.videoTitle);
        description = (TextView) convertView.findViewById(R.id.videoDescription);

        Video vivi = getItem(position);
        title.setText(vivi.getTitle());
        description.setText(vivi.getDescription());


        return convertView;
    }
}
