package io.hotkey.rpi_monitorwatcher.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.net.InetSocketAddress;

/**
 * Created by Tobias Fiechter on 26.11.2015.
 */
public class Raspberry extends SugarRecord {

    @Unique
    InetSocketAddress address;
    String title;
    float statusUptime;
    float statusCPULoad;
    float statusTemperature;
    float statusSwap;
    float statusSDCard;
    int statusNetworkReceived;
    int statusNetworkSent;

    public Raspberry() {

    }

    public Raspberry(String title, float statusUptime, float statusCPULoad, float statusTemperature, float statusSwap, float statusSDCard, int statusNetworkReceived) {
        this.title = title;
        this.statusUptime = statusUptime;
        this.statusCPULoad = statusCPULoad;
        this.statusTemperature = statusTemperature;
        this.statusSwap = statusSwap;
        this.statusSDCard = statusSDCard;
        this.statusNetworkReceived = statusNetworkReceived;
    }

    public String getTitle() {
        return title;
    }

    public float getStatusUptime() {
        return statusUptime;
    }

    public float getStatusCPULoad() {
        return statusCPULoad;
    }

    public float getStatusTemperature() {
        return statusTemperature;
    }

    public float getStatusSwap() {
        return statusSwap;
    }

    public float getStatusSDCard() {
        return statusSDCard;
    }

    public int getStatusNetwork() {
        return statusNetworkReceived;
    }

}
