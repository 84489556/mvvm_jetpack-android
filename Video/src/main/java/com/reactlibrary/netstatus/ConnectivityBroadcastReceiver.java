package com.reactlibrary.netstatus;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Class that receives intents whenever the connection type changes. NB: It is possible on some
 * devices to receive certain connection type changes multiple times.
 */
public class ConnectivityBroadcastReceiver extends BroadcastReceiver {

    // TODO: Remove registered check when source of crash is found. t9846865
    private boolean isRegistered = false;
    private ConnectivityManager mConnectivityManager;

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (mConnectivityManager == null) {
                mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            }
            updateAndSendConnectionType();
        }
    }

    @SuppressLint("MissingPermission")
    private void updateAndSendConnectionType() {
        ConnectionType connectionType = ConnectionType.UNKNOWN;
        boolean isInternetReachable = false;

        try {
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected()) {
                connectionType = ConnectionType.NONE;
            } else {
                // Check if the internet is reachable
                isInternetReachable = networkInfo.isConnected();

                // Get the connection type
                int networkType = networkInfo.getType();
                switch (networkType) {
                    case ConnectivityManager.TYPE_BLUETOOTH:
                        connectionType = ConnectionType.BLUETOOTH;
                        break;
                    case ConnectivityManager.TYPE_ETHERNET:
                        connectionType = ConnectionType.ETHERNET;
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                    case ConnectivityManager.TYPE_MOBILE_DUN:
                        connectionType = ConnectionType.CELLULAR;
                        break;
                    case ConnectivityManager.TYPE_WIFI:
                        connectionType = ConnectionType.WIFI;
                        break;
                    case ConnectivityManager.TYPE_WIMAX:
                        connectionType = ConnectionType.WIMAX;
                        break;
                    case ConnectivityManager.TYPE_VPN:
                        connectionType = ConnectionType.VPN;
                        break;
                }
            }
        } catch (SecurityException e) {
            connectionType = ConnectionType.UNKNOWN;
        }

    }
}