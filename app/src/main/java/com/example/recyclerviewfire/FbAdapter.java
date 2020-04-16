package com.example.recyclerviewfire;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FbAdapter extends FirestoreRecyclerAdapter<modelclass,FbAdapter.FbViewHolder> {


    public FbAdapter(@NonNull FirestoreRecyclerOptions<modelclass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FbViewHolder fbViewHolder, int i, @NonNull modelclass modelclass) {
String username=getSnapshots().getSnapshot(i).getId();
        fbViewHolder.usernameTV.setText(username);

        fbViewHolder.userstatusTV.setText(modelclass.getStatus());
    }

    @NonNull
    @Override
    public FbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new FbViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row,parent, false));
    }

    public class FbViewHolder extends  RecyclerView.ViewHolder
    {

        TextView usernameTV,userstatusTV;
        public FbViewHolder(@NonNull View singleRow) {
            super(singleRow);

            usernameTV=singleRow.findViewById(R.id.sr_username);
            userstatusTV=singleRow.findViewById(R.id.sr_userstatus);

        }
    }
}
