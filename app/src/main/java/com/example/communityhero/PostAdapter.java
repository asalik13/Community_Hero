package com.example.communityhero;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private Context context;
    List<Post> postList, filterList;
    Filter filter;
    PostAdapter adapter;


    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
        this.filterList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view, null);
        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
    }

    //Assigns post class information to the cardview
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);

        holder.textViewTitle.setText(post.getTitle());
        holder.textViewDesc.setText(post.getDesc());
        holder.textViewContributors.setText(String.valueOf(post.getContributors()));
        holder.textViewDate.setText(String.valueOf(post.getDate()));

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewDesc, textViewContributors, textViewDate;

        public PostViewHolder(@NonNull final View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewContributors = itemView.findViewById(R.id.textViewContributors);
            textViewDate = itemView.findViewById(R.id.textViewDate);

            //On selection of a post
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Pop up asking you if you want to join
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("DO YOU WANNA JOIN?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Log.i("info", "yes");
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Log.i("info", "no");
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

        }
    }
}
