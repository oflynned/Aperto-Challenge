package com.syzible.aperto.common.networking;

import org.json.JSONObject;

interface NetworkCallback {
    void onFailure(int statusCode, JSONObject error);
}
