package io.hotkey.rpi_monitorwatcher.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import io.hotkey.rpi_monitorwatcher.R;
import io.hotkey.rpi_monitorwatcher.model.Raspberry;

/**
 * Created by Tobias Fiechter on 24.01.2016.
 */
public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.RaspberryViewHolder> {

    List<Raspberry> list = Collections.emptyList();
    Context context;

    public StatusAdapter(Context context) {
        reloadData();
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
        holder.title.setText(list.get(position).getTitle());
        float uptimeInSeconds = list.get(position).getStatusUptime();
        String uptime = uptimeInSeconds+" sec.";
        holder.statusUptime.setText(uptime);
        holder.statusCPULoad.setProgress((int)list.get(position).getStatusCPULoad());
        holder.statusTemperature.setProgress((int)list.get(position).getStatusTemperature());
        holder.statusSwap.setText(list.get(position).getStatusSwap()+" MB");
        holder.statusSDCard.setText(list.get(position).getStatusSDCard()+" MB");
        holder.statusNetworkReceived.setText(list.get(position).getStatusNetwork()+"s");

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

    public void reloadData() {
        list = Raspberry.listAll(Raspberry.class);
        this.notifyDataSetChanged();
    }

    public class RaspberryViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView statusUptime;
        ProgressBar statusCPULoad;
        ProgressBar statusTemperature;
        TextView statusSwap;
        TextView statusSDCard;
        TextView statusNetworkReceived;

        RaspberryViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.status_title);
            statusUptime = (TextView) itemView.findViewById(R.id.status_uptime);
            statusCPULoad = (ProgressBar) itemView.findViewById(R.id.status_cpu_load);
            statusTemperature = (ProgressBar) itemView.findViewById(R.id.status_temperature);
            statusSwap = (TextView) itemView.findViewById(R.id.status_swap);
            statusSDCard = (TextView) itemView.findViewById(R.id.status_sdcard);
            statusNetworkReceived = (TextView) itemView.findViewById(R.id.status_network_received);
        }
    }

}


