package com.example.michael.timecontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiChangeStateReceiver extends BroadcastReceiver {

    private static boolean connected = true;
    private static String currentSSID = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        WifiManager wifiManager = (WifiManager) context.getSystemService (Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String ssid  = info.getSSID();

        if( connected && ssid =="<unknown ssid>"){
            currentSSID = "";
            connected = false;
            Log.d("WifiChangeStateReceiver", "Wifi disconnected");
        }
        else if (!connected && ssid !="<unknown ssid>") {
            currentSSID = ssid;
            connected = true;
            Log.d("WifiChangeStateReceiver", "Wifi connected: " + ssid);
        }
    }
}
