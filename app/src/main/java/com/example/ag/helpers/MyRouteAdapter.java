package com.example.ag.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ag.R;
import com.example.ag.ui.notifications.NotificationsFragment;

import java.util.ArrayList;

public class MyRouteAdapter extends ArrayAdapter<Route> {

    public MyRouteAdapter(Context context, ArrayList<Route> arr) {
        super(context, R.layout.adapter_route_item, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Route route = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_route_item, null);
        }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.textView)).setText(route.getName());
        ((TextView) convertView.findViewById(R.id.textView2)).setText(String.valueOf(route.getC_Name()));
        ((TextView) convertView.findViewById(R.id.textView3)).setText(String.valueOf(route.getN_Limit()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return convertView;
    }
}