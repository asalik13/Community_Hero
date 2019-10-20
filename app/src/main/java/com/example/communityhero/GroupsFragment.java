package com.example.communityhero;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

//Fragment for settings
public class GroupsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        /*MongoJDBCDriver mongo = new MongoJDBCDriver();
        mongo.insertCollectionGroups("groupCollection");
        ArrayList<String> arr = mongo.findCollection("groupCollection", "groupName");

        arr.add("You belong to Group Blah");
        arr.add("You belong to Group Blah");
        arr.add("You belong to Group Blah");
        arr.add("You belong to Group Blah");
        arr.add("You belong to Group Blah");

        ListView listView = view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);*/
        return view;
    }
}
