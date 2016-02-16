package io.hotkey.rpi_monitorwatcher.ui.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

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
                showAddRaspberryDialog();
            }
        });

        // Inflate the layout for this fragment
        return root;
    }


    private void showAddRaspberryDialog()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.status_add_raspi_dialog_title);

        LinearLayout inputLayout = new LinearLayout(getContext());
        inputLayout.setOrientation(LinearLayout.VERTICAL);

        final EditText raspiTitle = new EditText(getContext());
        raspiTitle.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        raspiTitle.setHint(R.string.status_add_raspi_title);
        inputLayout.addView(raspiTitle);

        final EditText raspiAddress = new EditText(getContext());
        raspiAddress.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        raspiAddress.setHint(R.string.status_add_raspi_ip);
        inputLayout.addView(raspiAddress);

        builder.setView(inputLayout);

        // Add the buttons
        builder.setPositiveButton(android.R.string.ok, null);

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        final AlertDialog addRaspberryDialog = builder.create();
        addRaspberryDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = addRaspberryDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        InetSocketAddress address = null;

                        try {
                            // WORKAROUND: add any scheme to make the resulting URI valid.
                            URI uri = new URI("http://"+raspiAddress.getText().toString()); // may throw URISyntaxException

                            if (uri.getHost() == null || uri.getPort() == -1) {
                                throw new URISyntaxException(uri.toString(),
                                        "URI must have host and port parts");
                            }

                            address = new InetSocketAddress(uri.getHost(), uri.getPort());

                            //TODO: remove dummy raspis creation
                            Raspberry raspToAdd = new Raspberry(raspiTitle.getText().toString(), address,
                                    (float)Math.random()*5, (float)Math.random()*5, (float)Math.random()*5,
                                    (float)Math.random()*5, (float)Math.random()*5, (int)(Math.random()*5));
                            raspToAdd.save();

                            mAdapter.reloadData();
                            addRaspberryDialog.dismiss();

                        } catch (Exception ex) {
                            Toast.makeText(getActivity(), R.string.status_add_raspi_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        addRaspberryDialog.show();
    }
}
