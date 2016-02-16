package io.hotkey.rpi_monitorwatcher.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.hotkey.rpi_monitorwatcher.R;
import io.hotkey.rpi_monitorwatcher.model.Raspberry;
import io.hotkey.rpi_monitorwatcher.ui.adapters.StatusAdapter;

/**
 * Created by Tobias Fiechter on 23.11.2015.
 */
public class StatusFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private StatusAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private FloatingActionButton mAddRaspberryFAB;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.content_status, container, false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.raspberry_listing);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new StatusAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        mAddRaspberryFAB = (FloatingActionButton) root.findViewById(R.id.add_raspberry_fab);
        mAddRaspberryFAB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Raspberry raspToAdd = new Raspberry("myRasp"+ Math.random(),
                        (float)Math.random()*5, (float)Math.random()*5, (float)Math.random()*5,
                        (float)Math.random()*5, (float)Math.random()*5, (int)Math.random());
                raspToAdd.save();
                mAdapter.reloadData();
            }
        });

        // Inflate the layout for this fragment
        return root;
    }
}
