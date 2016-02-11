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
    String statusUptime;
    String statusCPULoad;
    String statusTemperature;
    String statusSwap;
    String statusSDCard;
    String statusNetwork;

    public Raspberry() {

    }

    public String getTitle() {
        return title;
    }

    public String getStatusUptime() {
        return statusUptime;
    }

    public String getStatusCPULoad() {
        return statusCPULoad;
    }

    public String getStatusTemperature() {
        return statusTemperature;
    }

    public String getStatusSwap() {
        return statusSwap;
    }

    public String getStatusSDCard() {
        return statusSDCard;
    }

    public String getStatusNetwork() {
        return statusNetwork;
    }

}
