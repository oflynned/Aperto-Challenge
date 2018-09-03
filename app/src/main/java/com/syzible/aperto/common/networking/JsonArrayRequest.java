package com.syzible.aperto.common.networking;

import android.os.AsyncTask;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

abstract class JsonArrayRequest<T> extends AsyncTask<String, Void, JSONArray> {
    private static final int READ_TIMEOUT = 1000 * 60;
    private static final int CONNECT_TIMEOUT = 1000 * 20;
    private static final String CHARSET = "iso-8859-1";

    private static final String CONSUMER_KEY = "5lDHdHaswRaxlv4OrDAWs9BXa";
    private static final String CONSUMER_SECRET = "ovqrpoEfotMJil82c5XgjyRn14q1ePWrGjg9vLZ1GBk0Oa3vKZ";

    private HttpURLConnection conn;
    private T callback;

    JsonArrayRequest(T callback) {
        this.callback = callback;
    }

    @Override
    protected JSONArray doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            String accessCredential = CONSUMER_KEY + ":" + CONSUMER_SECRET;
            String authorization = "Basic " + Base64.encodeToString(accessCredential.getBytes(), Base64.NO_WRAP);
            conn.addRequestProperty("Authorization", authorization);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.connect();

            String param = "grant_type=client_credentials";
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(param.getBytes());
            outputStream.flush();
            outputStream.close();

            switch (conn.getResponseCode()) {
                case 200:
                    String line;
                    StringBuilder sb = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET), 8);
                    while ((line = reader.readLine()) != null) sb.append(line).append("\n");
                    return new JSONArray(sb.toString());
                default:
                    break;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return null;
    }

    T getCallback() {
        return callback;
    }
}
