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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TasksFragment extends Fragment {

    //Fragment for tasks
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ArrayList<String> arr = new ArrayList();
        arr.add("Being batman");
        arr.add("Being batman");
        arr.add("Being batman");
        arr.add("Being batman");
        arr.add("Curing cancer");
        arr.add("Being batman");
        ListView listView = view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);
        return view;

    }
}
