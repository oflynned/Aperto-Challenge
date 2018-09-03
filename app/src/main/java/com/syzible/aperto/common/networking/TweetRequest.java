package com.syzible.aperto.common.networking;

import org.json.JSONArray;
import org.json.JSONException;

public class TweetRequest extends JsonArrayRequest<TweetCallback> {

    public TweetRequest(TweetCallback callback) {
        super(callback);
    }

    @Override
    protected void onPostExecute(JSONArray object) {
        super.onPostExecute(object);
        try {
            super.getCallback().onSuccess(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
