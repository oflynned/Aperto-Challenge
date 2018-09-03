package com.syzible.aperto.common.networking;

import org.json.JSONArray;
import org.json.JSONException;

public class WoeidRequest extends JsonArrayRequest<WoeidCallback> {

    private static final int FALLBACK_WOEID = 1;

    public WoeidRequest(WoeidCallback callback) {
        super(callback);
    }

    @Override
    protected void onPostExecute(JSONArray object) {
        super.onPostExecute(object);
        int woeid = FALLBACK_WOEID;
        if (object != null) {
            try {
                woeid = object.getJSONObject(0).getInt("woeid");
            } catch (JSONException e) {
                e.printStackTrace();
                super.getCallback().onSuccess(FALLBACK_WOEID);
                return;
            }
        }

        super.getCallback().onSuccess(woeid);
    }
}
