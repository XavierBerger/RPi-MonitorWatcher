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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.content_status, container, false);
        List<Raspberry> data = Raspberry.listAll(Raspberry.class);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.raspberry_listing);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new StatusAdapter(data, getContext());
        mRecyclerView.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return root;
    }
}
