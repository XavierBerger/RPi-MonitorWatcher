package io.hotkey.rpi_monitorwatcher.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Tobias Fiechter on 23.11.2015.
 */
public class PiMonitorWatcherService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
