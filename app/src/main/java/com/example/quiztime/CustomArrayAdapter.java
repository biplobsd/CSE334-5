package com.example.quiztime;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quiztime.model.User;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<User> {
    private LayoutInflater inflater;

    public CustomArrayAdapter(Context context, List<User> userList) {
        super(context, 0, userList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.leaderboard_listview_item, parent, false);
        }

        // Retrieve the object at the current position
        User currentItem = getItem(position);

        // Find the TextViews within the custom layout
        TextView no = convertView.findViewById(R.id.position);
        TextView displayName = convertView.findViewById(R.id.displayName);
        TextView score = convertView.findViewById(R.id.score);

        // Set the object data on the TextViews
        no.setText(String.valueOf(position + 1));
        displayName.setText(currentItem.getDisplayName());
        score.setText(String.valueOf(currentItem.getScore()));

        return convertView;
    }
}
