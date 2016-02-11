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
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.content_status, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.raspberry_listing);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);


        // specify an adapter (see also next example)
        String[] data = {"Cheese", "Pepperoni", "Black Olives"};
        mAdapter = new StatusAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return root;
    }

    public class RaspberryAdapter extends RecyclerView.Adapter<RaspberryViewHolder> {

        List<Raspberry> list = Collections.emptyList();
        Context context;

        public RaspberryAdapter(List<Raspberry> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public RaspberryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Inflate the layout, initialize the View Holder
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.raspberry_listing_entry, parent, false);
            RaspberryViewHolder holder = new RaspberryViewHolder(v);
            return holder;

        }

        @Override
        public void onBindViewHolder(RaspberryViewHolder holder, int position) {

            //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
            holder.statusUptime.setText(list.get(position).getStatusUptime());
            holder.statusCPULoad.setText(list.get(position).getStatusCPULoad());
            holder.statusTemperature.setText(list.get(position).getStatusTemperature());
            holder.statusSwap.setText(list.get(position).getStatusSwap());
            holder.statusSDCard.setText(list.get(position).getStatusSDCard());
            holder.statusNetwork.setText(list.get(position).getStatusNetwork());

            //animate(holder);

        }

        @Override
        public int getItemCount() {
            //returns the number of elements the RecyclerView will display
            return list.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        // Insert a new item to the RecyclerView on a predefined position
        public void insert(int position, Raspberry data) {
            list.add(position, data);
            notifyItemInserted(position);
        }

        // Remove a RecyclerView item containing a specified Data object
        public void remove(Raspberry data) {
            int position = list.indexOf(data);
            list.remove(position);
            notifyItemRemoved(position);
        }

    }

    public class RaspberryViewHolder extends RecyclerView.ViewHolder {

        TextView statusUptime;
        TextView statusCPULoad;
        TextView statusTemperature;
        TextView statusSwap;
        TextView statusSDCard;
        TextView statusNetwork;

        RaspberryViewHolder(View itemView) {
            super(itemView);
            statusUptime = (TextView) itemView.findViewById(R.id.status_uptime);
            statusCPULoad = (TextView) itemView.findViewById(R.id.status_cpu_load);
            statusTemperature = (TextView) itemView.findViewById(R.id.status_temperature);
            statusSwap = (TextView) itemView.findViewById(R.id.status_swap);
            statusSDCard = (TextView) itemView.findViewById(R.id.status_sdcard);
            statusNetwork = (TextView) itemView.findViewById(R.id.status_network);
        }
    }
}
