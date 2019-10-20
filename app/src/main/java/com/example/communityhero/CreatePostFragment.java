package com.example.communityhero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreatePostFragment extends Fragment {
    Bundle bundle;
    EditText title;
    EditText desc;
    //Fragment for creating posts
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createpost, container, false);
        title = (EditText) view.getRootView().findViewById(R.id.title);
        desc = (EditText) view.getRootView().findViewById(R.id.desc);

        Button btn = view.findViewById(R.id.create);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double lat = bundle.getDouble("lat", 0);
                Double lng = bundle.getDouble("lng", 0);

                String pattern = "dd MMMM yyyy";
                SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("us", "US"));
                String date = simpleDateFormat.format(new Date());


                MongoJDBCDriver mongo = new MongoJDBCDriver();
                Post myPost = new Post(
                        0,
                        title.getText().toString(),
                        desc.getText().toString(),
                        "Contributors: 2",
                        date,
                        lat,
                        lng
                );
                mongo.insertOnePostCollection("postCollection", myPost );
            }
        });
        return view;
    }
}
