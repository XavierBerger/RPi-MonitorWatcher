package io.hotkey.rpi_monitorwatcher.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.hotkey.rpi_monitorwatcher.R;

/**
 * Created by Tobias Fiechter on 23.11.2015.
 */
public class StatisticsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.content_statistics, container, false);
    }
}
