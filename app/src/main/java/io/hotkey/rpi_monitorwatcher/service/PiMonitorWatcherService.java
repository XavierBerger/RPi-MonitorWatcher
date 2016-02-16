package io.hotkey.rpi_monitorwatcher.service;

import retrofit.http.GET;

/**
 * Created by Tobias Fiechter on 23.11.2015.
 */
interface PiMonitorWatcherService {


    @GET("/dynamic.json?_=1448530332355") //TODO: what's this timestamp?
    StatusResponse getStatusResponse();

    static class StatusResponse {
        //Time
        int[] localtime;
        String uptime;

        //Memory
        float memory_available;
        float memory_free;
        float swap_used;

        //CPU
        float soc_temp;
        String cpu_voltage;
        int cpu_frequency;
        String load1;
        String load5;
        String load15;

        //SDCARD
        float sdcard_boot_used;
        float sdcard_root_used;
        String scaling_governor;

        //NETWORK
        String net_send;
        float net_received;

        //UPDATES
        String upgrade;
    }

    static class StatisticsResponse {

    }
}

